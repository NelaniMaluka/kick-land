import React, { useState } from "react";
import { useParams } from "react-router-dom";
import { useAuth } from "../../Context/AuthContext";
import ErrorMessageAlert from "../Alerts/ErrorMessageAlert";
import TransitionsSnackbar from "../Alerts/TransitionSnackBar";
import ProductImageCouresel from "../Couresels/ProductImageCarousel";
import "./ProductDetails.css";
import AssuranceBanner from "../Banners/AssuranceBanner";
import { useDispatch, useSelector } from "react-redux";
import { addCart } from "../../State/Cart/Action";

function ProductDetails() {
  const { category, productName } = useParams();
  const { isProducts } = useAuth();
  const auth = useSelector((state) => state.auth);
  const [quantity, setQuantity] = useState(1);
  const [selectedSize, setSelectedSize] = useState("size3"); // Initialize selectedSize to null
  const [showSnackbar, setShowSnackbar] = useState(false);

  const jwt = localStorage.getItem("jwt");
  const dispatch = useDispatch();

  const size = selectedSize;

  const selectedProduct = isProducts.find(
    (product) => product.category === category && product.name === productName
  );

  const handleAddToCart = () => {
    const cartData = {
      quantity,
      size,
      productId: selectedProduct.productId, // Include productId directly
      price: selectedProduct.price,
    };
    if (auth.authenticated) {
      if (!selectedProduct.stock[0][selectedSize] <= 0) {
        dispatch(addCart(cartData, jwt))
          .then((result) => {
            if (result) {
              setShowSnackbar(true);
            } else {
              ErrorMessageAlert({ message: "Couldn't add item to cart" });
            }
          })
          .catch((err) => {
            ErrorMessageAlert({ message: "Internal Server Error" });
          });
      } else {
        ErrorMessageAlert({ message: "Out of Stock" });
      }
    } else {
      ErrorMessageAlert({ message: "LogIn First" });
    }
  };

  if (!selectedProduct) {
    return <div>Loading...</div>;
  }

  const selectSize = (key) => {
    setSelectedSize(key);
  };

  return (
    <div>
      <div className="shop-product-container">
        <div className="images">
          <ProductImageCouresel product={selectedProduct} />
        </div>
        <div className="info">
          <h2 className="sneakerName">{selectedProduct.name}</h2>
          <span>
            {Object.keys(selectedProduct.stock[0])
              .filter((key) => key !== "id")
              .sort(
                (a, b) =>
                  parseInt(a.replace("size", "")) -
                  parseInt(b.replace("size", ""))
              )
              .map((key) => {
                const sizeCount = selectedProduct.stock[0][key];
                const buttonClass = `sizeButton${
                  selectedSize === key ? " selectedSize" : ""
                } ${sizeCount <= 0 ? "disabledSize" : ""}`;
                return (
                  <button
                    className={buttonClass}
                    type="button"
                    key={key}
                    onClick={() => selectSize(key)}
                  >
                    Size {key.replace("size", "")}
                  </button>
                );
              })}
          </span>

          <span className="quantity">
            <label>Quantity: </label>
            <select
              id="quantity"
              value={quantity}
              onChange={(e) => setQuantity(Number(e.target.value))}
            >
              {[
                ...Array(
                  Math.min(5, selectedProduct.stock[0][selectedSize])
                ).keys(),
              ].map((number) => (
                <option key={number + 1} value={number + 1}>
                  {number + 1}
                </option>
              ))}
            </select>
          </span>
          <span>
            {selectedProduct.stock[0][selectedSize] > 0 ? (
              <p className="inStock">Availability: In Stock</p>
            ) : (
              <p className="outStock">Availability: Out of Stock</p>
            )}
          </span>
          <span className="price">
            {new Intl.NumberFormat("en-ZA", {
              style: "currency",
              currency: "ZAR",
            }).format(selectedProduct.price)}
          </span>
          <button className="addToCart" onClick={handleAddToCart}>
            Add to Cart
          </button>
        </div>
        {showSnackbar && <TransitionsSnackbar />}
      </div>
      <AssuranceBanner />
    </div>
  );
}

export default ProductDetails;
