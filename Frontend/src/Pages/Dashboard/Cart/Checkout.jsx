import { useAuth } from "../../../Context/AuthContext";
import { loadStripe } from "@stripe/stripe-js";
import { useNavigate } from "react-router-dom";
import { useEffect } from "react";
import { useLocation } from "react-router-dom";
import { formatCurrency } from "../../../Utils/formatCurrency.js";
import "./Checkout.css";

function Checkout() {
  const authContext = useAuth();
  const cartItems = authContext.isCartItems || [];
  const isProducts = authContext.isProducts || [];
  const navigate = useNavigate();
  const location = useLocation();
  const { pathname } = location;
  const currentUrl = window.location.origin + pathname;

  // Function to calculate the total price of the cart
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

  // Function to handle Stripe checkout redirection
  const redirectToCheckout = async () => {
    const stripe = await loadStripe(
      "pk_test_51Oxki7EU0z74hURXI0dJKR7bjVKOGepQejluncbzq6249lEZmrjYoOMLiCU0muGEH9gkpg20l68aKEm65r50nKFQ000AmUrm1Z"
    );

    // Creating line items for Stripe checkout
    const lineItems = cartItems
      .map((cartItem) => {
        const product = isProducts.find(
          (product) => product.productId === cartItem.productId
        );

        if (product) {
          return {
            price: product.priceUrl, // Make sure this is a valid price ID for Stripe
            quantity: cartItem.quantity,
          };
        } else {
          console.warn(
            `Product not found for cart item with productId: ${cartItem.productId}`
          );
          return null;
        }
      })
      .filter((item) => item !== null); // Filter out null items

    // Success and Cancel URLs for the checkout flow
    const successUrl = `${currentUrl}/success`;
    const cancelUrl = `${currentUrl}/cancel`;

    // Checkout options to pass to Stripe
    const checkoutOptions = {
      lineItems: lineItems,
      mode: "payment",
      successUrl: successUrl,
      cancelUrl: cancelUrl,
    };

    // Redirecting to Stripe checkout
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
            const product = isProducts.find(
              (p) => p.productId === cartItem.productId
            );
            if (!product) return null; // Skip if product is not found
            return (
              <tr key={product.productId}>
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