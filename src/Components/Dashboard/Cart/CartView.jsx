import React, { useState } from "react";
import { useAuth } from "../../Security/AuthContext";
import IconButton from "@mui/material/IconButton";
import DeleteIcon from "@mui/icons-material/Delete";
import Swal from "sweetalert2";

import "./CartView.css";

function CartView() {
  const authContext = useAuth();
  const [cartItems, setCartItems] = useState(authContext.isCartItems || []);

  // Function to handle quantity changes
  async function handleQuantityChange(productId, updatedQuantity) {
    authContext
      .updateCartItem(authContext.isUser.id, productId, updatedQuantity)
      .then(function (result) {
        if (result.success) {
          setCartItems(result.response.data);
        } else {
          showErrorMessage("Could not update product");
        }
      })
      .catch(function (error) {
        showErrorMessage("Internal Server Error");
      });
  }

  // Function to handle delete button click
  async function handleDeleteClick(productId) {
    authContext
      .deleteCartItem(authContext.isUser.id, productId)
      .then(function (result) {
        if (result.success) {
          setCartItems(result.response.data);
        } else {
          showErrorMessage("Could not remove product");
        }
      })
      .catch(function (error) {
        showErrorMessage("Internal Server Error");
      });
  }

  function showErrorMessage(error) {
    Swal.fire({
      icon: "error",
      title: "Oops...",
      text: error,
    });
  }

  return (
    <div>
      {cartItems.map((product) => (
        <div className="cart-card" key={product.id}>
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
              }).format(product.price * product.quantity)}
            </span>

            <div className="cart-footer">
              <span>
                quantity:
                <input
                  type="number"
                  onChange={(e) =>
                    handleQuantityChange(product.id, e.target.value)
                  }
                  min={1}
                  placeholder={product.quantity}
                />
              </span>
              <span>
                <IconButton
                  aria-label="delete"
                  size="large"
                  onClick={() => handleDeleteClick(product.id)}
                >
                  <DeleteIcon />
                </IconButton>
              </span>
            </div>
          </div>
        </div>
      ))}
    </div>
  );
}

export default CartView;
