import { useEffect } from "react";
import { useLocation } from "react-router-dom";
import { useDispatch } from "react-redux";
import { addOrder } from "../../../State/Order/Action";
import { useAuth } from "../../../Context/AuthContext";

const PaymentSuccess = () => {
  const location = useLocation();
  const dispatch = useDispatch();
  const authContext = useAuth();

  useEffect(() => {
    const queryParams = new URLSearchParams(location.search);
    const paymentId = queryParams.get("session_id"); // Stripe typically appends 'session_id' in URL

    if (paymentId) {
      // Dispatch confirm and create order with the paymentId
      dispatch(
        addOrder(
          paymentId,
          authContext.isOrderData,
          localStorage.getItem("jwt")
        )
      )
        .then((response) => {
          // Handle order creation success
          alert("Order confirmed successfully!");
          // Redirect to the order summary or another page
        })
        .catch((error) => {
          // Handle error on order creation
          console.error("Failed to confirm and create the order:", error);
          alert("Failed to confirm the order, please try again.");
        });
    } else {
      console.error("No session_id found in the URL");
    }
  }, [dispatch, location, authContext.isOrderData]);

  return (
    <div>
      <h3>Payment Success</h3>
      <p>Your payment is being processed...</p>
    </div>
  );
};

export default PaymentSuccess;
