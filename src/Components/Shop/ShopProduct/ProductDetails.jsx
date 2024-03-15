import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import { useAuth } from "../../Security/AuthContext";
import Alert from "../../LoginLogout/Alert";
import TransitionsSnackbar from "./TransitionSnackBar";
import ImageCouresel from "./ImageCouresel";
import "./ProductDetails.css";

function ProductDetails() {
  const { category, productName } = useParams();
  const { isProducts, retrieveProducts, addToCart, isAuthenticated, isUser } =
    useAuth();
  const [quantity, setQuantity] = useState(1);
  const [selectedSize, setSelectedSize] = useState("size3"); // Initialize selectedSize to null
  const [showSnackbar, setShowSnackbar] = useState(false);

  // Fetch products when component mounts
  //

  const selectedProduct = isProducts.find(
    (product) => product.category === category && product.name === productName
  );

  const handleAddToCart = () => {
    if (isAuthenticated) {
      if (!selectedProduct.stock <= 0) {
        const userId = isUser.id;
        const productWithUserId = { ...selectedProduct, quantity, userId };
        addToCart(productWithUserId)
          .then((result) => {
            if (result) {
              setShowSnackbar(true);
            } else {
              // Handle error case
            }
          })
          .catch((err) => {
            // Handle error case
          });
      } else {
        Alert({ message: "Out of Stock" });
      }
    } else {
      Alert({ message: "LogIn First" });
    }
  };

  if (!selectedProduct) {
    return <div>Loading...</div>;
  }

  const selectSize = (key) => {
    setSelectedSize(key);
  };

  return (
    <div className="shop-product-container">
      <div className="images">
        <ImageCouresel product={selectedProduct} />
      </div>
      <div className="info">
        <h2 className="sneakerName">{selectedProduct.name}</h2>
        <span>
          {Object.keys(selectedProduct.sizes[0])
            .filter((key) => key !== "id")
            .sort(
              (a, b) =>
                parseInt(a.replace("size", "")) -
                parseInt(b.replace("size", ""))
            )
            .map((key) => {
              const sizeCount = selectedProduct.sizes[0][key];
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
                Math.min(5, selectedProduct.sizes[0][selectedSize])
              ).keys(),
            ].map((number) => (
              <option key={number + 1} value={number + 1}>
                {number + 1}
              </option>
            ))}
          </select>
        </span>
        <span>
          {selectedProduct.sizes[0][selectedSize] > 0 ? (
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
  );
}

export default ProductDetails;
