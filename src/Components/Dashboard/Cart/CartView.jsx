import React from "react";
import { useAuth } from "../../Security/AuthContext";
import IconButton from "@mui/material/IconButton";
import DeleteIcon from "@mui/icons-material/Delete";

import "./CartView.css";

function CartView() {
  const authContext = useAuth();
  const cartItems = authContext.isCartItems || [];

  // Function to handle quantity change
  function handleQuantityChange(productId, updatedQuantity) {
    // Parse the updatedQuantity as an integer
    const parsedQuantity = parseInt(updatedQuantity, 10);

    // Assuming you have a function to update the cart item quantity
    const updatedCartItems = cartItems.map((item) => {
      if (item.id === productId) {
        // Calculate the total price based on the unit price and updated quantity
        const totalPrice = item.price * parsedQuantity;
        return { ...item, quantity: parsedQuantity, totalPrice };
      }
      return item;
    });

    // Update the cart items in the context
    authContext.updateCartItem(updatedCartItems);
  }

  // Function to handle delete button click
  function handleDeleteClick(productId) {
    // Assuming you have a function to delete the cart item
    authContext.deleteCartItem(productId);
    authContext.deleteCartItem(productId, authContext.isUser.id);
  }

  return (
    <div>
      {cartItems.map((product) => (
        <div className="cart-card" key={product.id}>
          <div className="cart-img">
            <img src={product.image1} alt="Product Image" />
          </div>
          <div className="divider" />
          <div className="cart-text">
            <span className="category">{product.category}</span>
            <span className="name">{product.name}</span>
            <span className="price">
              R{product.totalPrice || product.price}
            </span>{" "}
            {/* Show initial total price or normal price */}
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
