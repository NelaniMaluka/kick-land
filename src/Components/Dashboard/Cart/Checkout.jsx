import { useAuth } from "../../Security/AuthContext";
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

  return (
    <div className="cont">
      <div className="checkoutContainer">
        <h1>Cart</h1>
        <hr />
        <table>
          {cartItems.map((product) => (
            <tr>
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
        <button>Checkout</button>
      </div>
    </div>
  );
}

export default Checkout;
