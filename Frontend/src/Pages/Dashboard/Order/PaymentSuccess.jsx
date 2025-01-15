import { useEffect } from "react";
import { useDispatch } from "react-redux";
import { addOrder } from "../../../State/Order/Action";
import ErrorMessageAlert from "../../../Components/Alerts/ErrorMessageAlert";
import { useNavigate } from "react-router-dom";
import showSuccessMessage from "../../../Components/Alerts/SuccessMessageAlert";

const PaymentSuccess = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  useEffect(() => {
    const confirmAndCreateOrder = async () => {
      if (localStorage.getItem("session_Id")) {
        const reqData = {
          sessionId: localStorage.getItem("session_Id"),
          orderRequest: JSON.parse(localStorage.getItem("order_data")),
        };

        try {
          const response = await dispatch(
            addOrder(reqData, localStorage.getItem("jwt"))
          );

          if (response?.status >= 200 && response?.status < 300) {
            showSuccessMessage("Success!", "Order created");
            navigate("/Dashboard");
          } else if (response?.status === 400) {
            ErrorMessageAlert({
              message:
                "Invalid input. Please check your order details and try again.",
            });
          } else if (response?.status === 401) {
            ErrorMessageAlert({
              message: "Authentication failed. Please log in and try again.",
            });
          } else if (response?.status === 403) {
            ErrorMessageAlert({
              message: "You are not authorized to confirm this order.",
            });
          } else if (response?.status === 404) {
            ErrorMessageAlert({
              message: "Order service not available. Please try again later.",
            });
          } else if (response?.status >= 500) {
            ErrorMessageAlert({
              message: "A server error occurred. Please try again later.",
            });
          } else {
            ErrorMessageAlert({
              message: "An unexpected error occurred. Please try again.",
            });
          }
        } catch (error) {
          if (error.response) {
            const { status } = error.response;

            if (status === 400) {
              ErrorMessageAlert({
                message: "Invalid input. Please review your order details.",
              });
            } else if (status === 401) {
              ErrorMessageAlert({
                message: "Authentication error. Please log in and try again.",
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
            ErrorMessageAlert({
              message:
                "Network error. Please check your connection and try again.",
            });
          } else {
            ErrorMessageAlert({
              message:
                "An unexpected error occurred while confirming the order. Please try again.",
            });
          }
        }
        localStorage.setItem("order_data", "");
        localStorage.setItem("session_Id", "");
      } else {
        ErrorMessageAlert({
          message: "No active session found. Please try again later.",
        });
      }
    };

    confirmAndCreateOrder();
  }, [dispatch, navigate]);

  const styles = {
    container: {
      display: "flex",
      flexDirection: "column",
      justifyContent: "center",
      alignItems: "center",
      minHeight: "480px",
      padding: "20px",
      textAlign: "center",
    },
  };

  return (
    <div style={styles.container}>
      <h3>Payment Success</h3>
      <p>Your payment is being processed...</p>
    </div>
  );
};

export default PaymentSuccess;
