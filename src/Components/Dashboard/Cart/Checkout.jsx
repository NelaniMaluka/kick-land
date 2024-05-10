import { useAuth } from "../../Security/AuthContext";
import { loadStripe } from "@stripe/stripe-js";
import { useNavigate } from "react-router-dom";
import { useEffect } from "react";
import { useLocation } from "react-router-dom";

import "./Checkout.css";

function Checkout() {
  const authContext = useAuth();
  const cartItems = authContext.isCartItems || [];
  const isProducts = authContext.isProducts || [];
  const navigate = useNavigate();
  const location = useLocation();
  const { pathname } = location;
  const currentUrl = window.location.origin + pathname;

  function calculateTotal() {
    let total = 0;
    for (let i = 0; i < cartItems.length; i++) {
      const product = isProducts.find((p) => p.id === cartItems[i].productId);
      if (product) {
        total += product.price * cartItems[i].quantity;
      }
    }
    return total;
  }

  const redirectToCheckout = async () => {
    const stripe = await loadStripe(
      "pk_test_51Oxki7EU0z74hURXI0dJKR7bjVKOGepQejluncbzq6249lEZmrjYoOMLiCU0muGEH9gkpg20l68aKEm65r50nKFQ000AmUrm1Z"
    );

    const lineItems = cartItems.map((cartItem) => {
      const product = isProducts.find(
        (product) => product.id === cartItem.productId
      );

      if (product) {
        return {
          price: product.priceUrl,
          quantity: cartItem.quantity,
        };
      } else {
        console.warn(
          `Product not found for cart item with productId: ${cartItem.productId}`
        );
        return null;
      }
    });

    const successUrl = `${currentUrl}/success`; // Construct absolute URL for success
    const cancelUrl = `${currentUrl}/cancel`; // Construct absolute URL for cancel

    const checkoutOptions = {
      lineItems: lineItems,
      mode: "payment",
      successUrl: successUrl,
      cancelUrl: cancelUrl,
    };

    const { error } = await stripe.redirectToCheckout(checkoutOptions);
    if (error) {
      console.error("Error redirecting to checkout:", error);
    }
  };

  // useEffect to handle actions on success or cancel URLs
  useEffect(() => {
    const handleSuccess = () => {
      // Your function to run on successful payment
      console.log("Payment was successful!");
      // For example, update order status, send confirmation email, etc.
      navigate("/Dashboard"); // Navigate to Dashboard page while preserving state
    };

    const handleCancel = () => {
      // Your function to run on payment cancellation
      console.log("Payment was cancelled.");
      // For example, show a message to the user
      navigate("/Dashboard"); // Navigate to Dashboard page while preserving state
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
        {cartItems.map((cartItem) => {
          const product = isProducts.find((p) => p.id === cartItem.productId);
          return (
            <tr key={product.id}>
              <td className="product-name">{product.name}</td>
              <td>x{cartItem.quantity}</td>
              <td>
                <span className="price">
                  {new Intl.NumberFormat("en-ZA", {
                    style: "currency",
                    currency: "ZAR",
                  }).format(product.price * cartItem.quantity)}
                </span>
              </td>
            </tr>
          );
        })}
      </table>
      <hr />
      <table>
        <tr>
          <td className="product-name">Total:</td>
          <td>
            <span className="price">
              {new Intl.NumberFormat("en-ZA", {
                style: "currency",
                currency: "ZAR",
              }).format(calculateTotal())}
            </span>
          </td>
        </tr>
      </table>
      <button onClick={redirectToCheckout}>Checkout</button>
    </div>
  );
}

export default Checkout;
