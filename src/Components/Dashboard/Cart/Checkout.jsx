import { useAuth } from "../../Security/AuthContext";
import { loadStripe } from "@stripe/stripe-js";

import "./Checkout.css";

function Checkout() {
  const authContext = useAuth();
  const cartItems = authContext.isCartItems || [];

  function calculateTotal() {
    let total = 0;
    for (let i = 0; i < cartItems.length; i++) {
      total += cartItems[i].price * cartItems[i].quantity;
    }
    return total;
  }

  const redirectToCheckout = async () => {
    const stripe = await loadStripe(
      "pk_test_51Oxki7EU0z74hURXI0dJKR7bjVKOGepQejluncbzq6249lEZmrjYoOMLiCU0muGEH9gkpg20l68aKEm65r50nKFQ000AmUrm1Z"
    );

    const lineItems = cartItems.map((item) => ({
      price: item.priceUrl,
      quantity: item.quantity,
    }));

    const checkoutOptions = {
      lineItems: lineItems,
      mode: "payment",
      successUrl: "http://localhost:3000/Dashboard",
      cancelUrl: "http://localhost:3000/Dashboard",
    };

    const { error } = await stripe.redirectToCheckout(checkoutOptions);
    if (error) {
      console.error("Error redirecting to checkout:", error);
    }
  };

  return (
    <div className="checkoutContainer">
      <h1>Cart</h1>
      <hr />
      <table>
        {cartItems.map((product) => (
          <tr key={product.id}>
            <td className="product-name">{product.name}</td>
            <td>x{product.quantity}</td>
            <td>
              <span className="price">
                {new Intl.NumberFormat("en-ZA", {
                  style: "currency",
                  currency: "ZAR",
                }).format(product.price * product.quantity)}
              </span>
            </td>
          </tr>
        ))}
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
