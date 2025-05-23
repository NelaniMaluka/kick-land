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
import CircularIndeterminate from "../../Utils/LoadingSpinner";

export default function ProductDetails() {
  const { category, productName } = useParams();
  const { isProducts, setCart } = useAuth();
  const auth = useSelector((state) => state.auth);
  const [quantity, setQuantity] = useState(1);
  const [selectedSize, setSelectedSize] = useState("size3"); // Initialize selectedSize to null
  const [showSnackbar, setShowSnackbar] = useState(false);
  const [loading, setLoading] = useState(false);

  const jwt = localStorage.getItem("jwt");
  const dispatch = useDispatch();

  const selectedProduct = isProducts.find(
    (product) => product.category === category && product.name === productName
  );

  const handleAddToCart = async () => {
    const cartData = {
      quantity,
      size: selectedSize,
      productId: selectedProduct.id,
      price: selectedProduct.price,
    };

    if (auth.authenticated) {
      // Check stock for the selected size
      if (selectedProduct.stock[0][selectedSize] > 0) {
        setLoading(true);

        try {
          const result = await dispatch(addCart(cartData, jwt));

          if (result && result.status >= 200 && result.status < 300) {
            // Successfully added to the cart
            setShowSnackbar(true);
          } else if (result?.status === 400) {
            // Bad request: invalid data or parameters
            ErrorMessageAlert({
              message: "Invalid data provided. Please try again.",
            });
          } else if (result?.status === 401) {
            // Unauthorized: Authentication required
            ErrorMessageAlert({
              message: "Authentication failed. Please log in again.",
            });
          } else if (result?.status === 403) {
            // Forbidden: Insufficient permissions
            ErrorMessageAlert({
              message: "You are not allowed to perform this action.",
            });
          } else if (result?.status === 404) {
            // Product or resource not found
            ErrorMessageAlert({
              message: "Product not found. Please check again.",
            });
          } else if (result?.status >= 500) {
            // Internal server error
            ErrorMessageAlert({
              message:
                "An unexpected server error occurred. Please try again later.",
            });
          } else {
            // Fallback for unexpected status codes
            ErrorMessageAlert({
              message: "An unexpected error occurred. Please try again.",
            });
          }
        } catch (error) {
          if (error.response) {
            const { status } = error.response;

            if (status === 400) {
              ErrorMessageAlert({
                message: "Invalid data provided. Please check and try again.",
              });
            } else if (status === 401) {
              ErrorMessageAlert({
                message: "Authentication failed. Please log in again.",
              });
            } else if (status >= 500) {
              ErrorMessageAlert({
                message:
                  "An unexpected server error occurred. Please try again later.",
              });
            } else {
              ErrorMessageAlert({
                message: `Unexpected error: ${status}. Please try again.`,
              });
            }
          } else if (error.request) {
            // Handle network or no response errors
            ErrorMessageAlert({
              message:
                "Network error. Please check your connection and try again.",
            });
          } else {
            // Fallback for other errors
            ErrorMessageAlert({
              message: "An unexpected error occurred. Please try again.",
            });
          }
        }
      } else {
        // Stock unavailable
        ErrorMessageAlert({ message: "Out of Stock" });
      }
    } else {
      // Stock check for unauthenticated users too
      if (selectedProduct.stock[0][selectedSize] > 0) {
        const cartItem = {
          quantity,
          size: selectedSize,
          productId: selectedProduct.id,
          price: selectedProduct.price,
          product: selectedProduct, // you can store full product info if needed
        };

        setCart((prevCart) => {
          const existingItemIndex = prevCart.findIndex(
            (item) =>
              item.productId === cartItem.productId &&
              item.size === cartItem.size
          );

          if (existingItemIndex !== -1) {
            // If the item is already in the cart, update the quantity
            const updatedCart = [...prevCart];
            updatedCart[existingItemIndex].quantity += cartItem.quantity;
            return updatedCart;
          } else {
            // Otherwise, add it as a new item
            return [...prevCart, cartItem];
          }
        });

        setShowSnackbar(true); // show success message
      } else {
        ErrorMessageAlert({ message: "Out of Stock" });
      }
    }

    setLoading(false);
  };

  const selectSize = (key) => {
    setSelectedSize(key);
  };

  if (!Array.isArray(isProducts) || isProducts.length === 0) {
    return (
      <div className="loading-spinner">
        <CircularIndeterminate />
      </div>
    );
  }

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

      {/* Show loading spinner if loading is true */}
      {loading && (
        <div className="loading-spinner">
          <CircularIndeterminate />
        </div>
      )}
    </div>
  );
}
