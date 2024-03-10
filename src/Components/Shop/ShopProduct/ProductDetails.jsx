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
  const [showSnackbar, setShowSnackbar] = useState(false);

  useEffect(() => {
    retrieveProducts();
  }, [retrieveProducts]);

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

  return (
    <div className="shop-product-container">
      <div>
        <ImageCouresel product={selectedProduct} />
      </div>
      <div>
        <h2>{selectedProduct.name}</h2>
        <span>
          {selectedProduct.stock > 0 ? (
            <p>Availability: In Stock</p>
          ) : (
            <p>Availability: Out of Stock</p>
          )}
        </span>
        <span>
          <label htmlFor="quantity">Quantity: </label>
          <select
            id="quantity"
            value={quantity}
            onChange={(e) => setQuantity(Number(e.target.value))}
          >
            {[...Array(Math.min(5, selectedProduct.stock)).keys()].map(
              (number) => (
                <option key={number + 1} value={number + 1}>
                  {number + 1}
                </option>
              )
            )}
          </select>
        </span>
        <span>
          {new Intl.NumberFormat("en-ZA", {
            style: "currency",
            currency: "ZAR",
          }).format(selectedProduct.price)}
        </span>
        <button onClick={handleAddToCart}>Add to Cart</button>
      </div>
      {showSnackbar && <TransitionsSnackbar />}
    </div>
  );
}

export default ProductDetails;
