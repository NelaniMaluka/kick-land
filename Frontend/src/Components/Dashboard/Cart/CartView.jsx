import React, { useState } from "react";
import { useAuth } from "../../Security/AuthContext";
import IconButton from "@mui/material/IconButton";
import DeleteIcon from "@mui/icons-material/Delete";
import Swal from "sweetalert2";
import Checkout from "./Checkout";

import "./CartView.css";

function CartView() {
  const authContext = useAuth();
  const [cartItems, setCartItems] = useState(authContext.isCartItems || []);
  const isProducts = authContext.isProducts;

  // Function to handle quantity changes
  async function handleQuantityChange(productId, updatedQuantity) {
    try {
      const result = await authContext.updateCartItem(
        authContext.isUser.id,
        productId,
        updatedQuantity
      );
      if (result.success) {
        setCartItems(result.response.data);
      } else {
        showErrorMessage("Could not update product");
      }
    } catch (error) {
      showErrorMessage("Internal Server Error");
    }
  }

  // Function to handle delete button click
  async function handleDeleteClick(productId) {
    try {
      const result = await authContext.deleteCartItem(
        authContext.isUser.id,
        productId
      );
      if (result.success) {
        setCartItems(result.response.data);
      } else {
        showErrorMessage("Could not remove product");
      }
    } catch (error) {
      showErrorMessage("Internal Server Error");
    }
  }

  function showErrorMessage(error) {
    Swal.fire({
      icon: "error",
      title: "Oops...",
      text: error,
    });
  }

  if (cartItems.length === 0) {
    return (
      <div className="no-cart-items">
        <span>
          <span class="material-symbols-outlined">playlist_add</span> No cart
          items available.
        </span>
      </div>
    );
  }

  return (
    <div className="parent-container">
      <div className="cart-container">
        {cartItems.map((cartItem) => {
          // Find the corresponding product in isProducts array
          const product = isProducts.find(
            (p) => p.productId === cartItem.productId
          );

          if (!product) {
            return <span> Could not find product details.</span>;
          }

          return (
            <div className="cart-card" key={cartItem.userCartId}>
              <div className="cart-img">
                <img src={product.image1} alt="Product" />
              </div>
              <div className="divider" />
              <div className="cart-text">
                <span className="category">{product.category}</span>
                <span className="name">{product.name}</span>
                <span className="price">
                  {new Intl.NumberFormat("en-ZA", {
                    style: "currency",
                    currency: "ZAR",
                  }).format(product.price * cartItem.quantity)}
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

export default CartView;
