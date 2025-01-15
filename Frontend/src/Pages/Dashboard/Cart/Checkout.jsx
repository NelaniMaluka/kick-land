import { useAuth } from "../../../Context/AuthContext";
import { useNavigate } from "react-router-dom";
import { useEffect } from "react";
import { formatCurrency } from "../../../Utils/formatCurrency.js";
import "./Checkout.css";
import { useSelector } from "react-redux";

export default function Checkout() {
  const authContext = useAuth();
  const cart = useSelector((state) => state.cart);
  const cartItems = cart.cart || [];
  const isProducts = authContext.isProducts || [];
  const navigate = useNavigate();

  // Function to calculate the total price of the cart
  const calculateTotal = () => {
    let total = 0;
    for (let i = 0; i < cartItems.length; i++) {
      const product = isProducts.find((p) => p.id === cartItems[i].productId);
      if (product) {
        total += product.price * cartItems[i].quantity;
      }
    }
    return total;
  };

  // Function to handle Stripe checkout redirection
  const redirectToCheckout = async () => {
    navigate("/Order");
  };

  // useEffect to handle actions on success or cancel URLs
  useEffect(() => {
    const handleSuccess = () => {
      // Your function to run on successful payment
      console.log("Payment was successful!");
      navigate("/Dashboard"); // Navigate to Dashboard page
    };

    const handleCancel = () => {
      // Your function to run on payment cancellation
      console.log("Payment was cancelled.");
      navigate("/Dashboard"); // Navigate to Dashboard page
    };

    const path = window.location.pathname;
    if (path === "/success") {
      handleSuccess();
    } else if (path === "/cancel") {
      handleCancel();
    }
  }, [navigate]);

  return (
    <div className="checkoutContainer">
      <h1>Cart</h1>
      <hr />
      <table>
        <thead>
          <tr>
            <th>Product</th>
            <th>Quantity</th>
            <th>Price</th>
          </tr>
        </thead>
        <tbody>
          {cartItems.map((cartItem) => {
            const product = isProducts.find((p) => p.id === cartItem.productId);
            if (!product) return null; // Skip if product is not found
            return (
              <tr key={cartItem.productId}>
                <td className="product-name">{product.name}</td>
                <td>x{cartItem.quantity}</td>
                <td>
                  <span className="price">
                    {formatCurrency(product.price * cartItem.quantity)}
                  </span>
                </td>
              </tr>
            );
          })}
        </tbody>
      </table>
      <hr />
      <table>
        <tbody>
          <tr>
            <td className="product-name">Total:</td>
            <td>
              <span className="price">{formatCurrency(calculateTotal())}</span>
            </td>
          </tr>
        </tbody>
      </table>
      <button onClick={redirectToCheckout}>Checkout</button>
    </div>
  );
}
