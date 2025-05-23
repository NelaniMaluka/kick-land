import React from "react";
import { useAuth } from "../../../Context/AuthContext";
import IconButton from "@mui/material/IconButton";
import DeleteIcon from "@mui/icons-material/Delete";
import Checkout from "./Checkout";
import ErrorMessageAlert from "../../../Components/Alerts/ErrorMessageAlert.jsx";
import { formatCurrency } from "../../../Utils/formatCurrency.js";
import CircularIndeterminate from "../../../Utils/LoadingSpinner.jsx";

import "./CartView.css";
import { useDispatch, useSelector } from "react-redux";
import { removeCart, updateCart } from "../../../State/Cart/Action.js";

export default function CartView() {
  const authContext = useAuth();
  const cart = useSelector((state) => state.cart);
  const cartItems = Array.isArray(cart.cart) ? cart.cart : [];
  const isProducts = authContext.isProducts;

  const jwt = localStorage.getItem("jwt");
  const dispatch = useDispatch();

  // Function to handle quantity changes
  const handleQuantityChange = async (productId, quantity) => {
    try {
      const updateData = {
        productId,
        quantity,
      };

      const result = await dispatch(updateCart(updateData, jwt));

      if (result && result.status >= 200 && result.status < 300) {
        // Successfully updated the cart
      } else if (result?.status === 400) {
        // Bad request: invalid data or parameters
        ErrorMessageAlert({
          message: "Invalid quantity or product ID. Please try again.",
        });
      } else if (result?.status === 401) {
        // Unauthorized: Authentication required
        ErrorMessageAlert({
          message: "Authentication failed. Please log in again.",
        });
      } else if (result?.status === 403) {
        // Forbidden: Insufficient permissions
        ErrorMessageAlert({
          message: "You are not allowed to update this cart item.",
        });
      } else if (result?.status === 404) {
        // Product or cart item not found
        ErrorMessageAlert({
          message: "Product not found in cart. Please refresh the page.",
        });
      } else if (result?.status >= 500) {
        // Server error
        ErrorMessageAlert({
          message:
            "An unexpected server error occurred while updating the cart. Please try again later.",
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
            message: "Invalid quantity or product ID. Please try again.",
          });
        } else if (status === 401) {
          ErrorMessageAlert({
            message: "Authentication failed. Please log in again.",
          });
        } else if (status >= 500) {
          ErrorMessageAlert({
            message:
              "An unexpected server error occurred while updating the cart. Please try again later.",
          });
        } else {
          ErrorMessageAlert({
            message: `Unexpected error: ${status}. Please try again.`,
          });
        }
      } else if (error.request) {
        // Network or no response error
        ErrorMessageAlert({
          message: "Network error. Please check your connection and try again.",
        });
      } else {
        // Fallback for other errors
        ErrorMessageAlert({
          message: "An unexpected error occurred. Please try again.",
        });
      }
    }
  };

  // Function to handle delete button click
  async function handleDeleteClick(productId) {
    try {
      const result = await dispatch(removeCart(productId, jwt));

      if (result && result.status >= 200 && result.status < 300) {
        // Successfully removed the product from the cart
      } else if (result?.status === 400) {
        // Bad request: Invalid product ID or parameters
        ErrorMessageAlert({
          message: "Invalid request. Please try again.",
        });
      } else if (result?.status === 401) {
        // Unauthorized: Authentication required
        ErrorMessageAlert({
          message: "Authentication failed. Please log in again.",
        });
      } else if (result?.status === 403) {
        // Forbidden: Insufficient permissions
        ErrorMessageAlert({
          message: "You are not authorized to perform this action.",
        });
      } else if (result?.status === 404) {
        // Product or cart item not found
        ErrorMessageAlert({
          message: "Product not found in your cart. Please refresh the page.",
        });
      } else if (result?.status >= 500) {
        // Server error
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
            message: "Invalid request. Please check and try again.",
          });
        } else if (status === 401) {
          ErrorMessageAlert({
            message: "Authentication required. Please log in again.",
          });
        } else if (status >= 500) {
          ErrorMessageAlert({
            message: "A server error occurred. Please try again later.",
          });
        } else {
          ErrorMessageAlert({
            message: `Unexpected error: ${status}. Please try again.`,
          });
        }
      } else if (error.request) {
        // Network or no response error
        ErrorMessageAlert({
          message: "Network error. Please check your connection and try again.",
        });
      } else {
        // Fallback for other unexpected errors
        ErrorMessageAlert({
          message: "An unexpected error occurred. Please try again.",
        });
      }
    }
  }

  if (
    !authContext ||
    !Array.isArray(isProducts) ||
    (isProducts.length === 0) & (cartItems.length === 0)
  ) {
    return (
      <div className="loading-spinner">
        <CircularIndeterminate />
      </div>
    );
  }

  if (cartItems.length === 0) {
    return (
      <div className="no-cart-items">
        <span>
          <span className="material-symbols-outlined">playlist_add</span> No
          cart items available.
        </span>
      </div>
    );
  }

  return (
    <div className="parent-container">
      <div className="cart-container">
        {cartItems.map((cartItem) => {
          // Find the corresponding product in isProducts array
          const product = isProducts.find((p) => p.id === cartItem.productId);

          if (!product) {
            return <span> Could not find product details.</span>;
          }

          return (
            <div className="cart-card" key={cartItem.productId}>
              <div className="cart-img">
                <img src={product.image1} alt="Product" />
              </div>
              <div className="divider" />
              <div className="cart-text">
                <span className="category">{product.category}</span>
                <span className="name">{product.name}</span>
                <span className="price">
                  {formatCurrency(product.price * cartItem.quantity)}
                </span>
                <span className="size">{cartItem.size}</span>

                <div className="cart-footer">
                  <span>
                    Quantity:
                    <input
                      type="number"
                      onChange={(e) =>
                        handleQuantityChange(cartItem.productId, e.target.value)
                      }
                      min={1}
                      placeholder={cartItem.quantity}
                    />
                  </span>
                  <span>
                    <IconButton
                      aria-label="delete"
                      size="large"
                      onClick={() => handleDeleteClick(cartItem.productId)}
                    >
                      <DeleteIcon />
                    </IconButton>
                  </span>
                </div>
              </div>
            </div>
          );
        })}
      </div>
      <div className="checkout-container">
        <Checkout />
      </div>
    </div>
  );
}
