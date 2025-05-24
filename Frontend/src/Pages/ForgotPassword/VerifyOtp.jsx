import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../../Context/AuthContext.js";
import ErrorMessageAlert from "../../Components/Alerts/ErrorMessageAlert.jsx";
import showSuccessMessage from "../../Components/Alerts/SuccessMessageAlert.jsx";
import { isValidOtp } from "../../Utils/FormValidations.js";
import CircularIndeterminate from "../../Utils/LoadingSpinner.jsx";

import "../../Components/Styling/Form.css";

// Handles OTP Verification
export default function VerifyOtp() {
  const [otp, setOtp] = useState("");
  const [otpError, setOtpError] = useState(false);
  const [loading, setLoading] = useState(false);
  const useContext = useAuth();
  const navigate = useNavigate();

  const handleOtpChange = (event) => {
    setOtp(event.target.value);
  };

  async function handleSubmit(event) {
    setLoading(true);

    // Email validation
    if (!otp) {
      setOtpError("otp is required.");
      return;
    } else if (!isValidOtp(otp)) {
      setOtpError("Invalid otp format.");
      return;
    }

    const result = await useContext.verifyOtp(otp);

    try {
      if (result.success) {
        showSuccessMessage("Success!", result.response.data);
        navigate("/Change-Password");
      } else {
        switch (result.status) {
          case 400:
            ErrorMessageAlert({
              message: "Bad Request. Please enter a valid OTP.",
            });
            break;
          case 401:
            ErrorMessageAlert({
              message: "Unauthorized. Invalid or expired OTP.",
            });
            break;
          case 404:
            ErrorMessageAlert({
              message: "OTP not found or email does not match.",
            });
            break;
          case 500:
          default:
            ErrorMessageAlert({
              message:
                result.message ||
                "We are encountering problems. Sorry for the inconvenience.",
            });
            break;
        }
      }
    } catch (error) {
      ErrorMessageAlert({
        message: "Unexpected error occurred. Please try again later.",
      });
    }

    setLoading(false);
  }

  return (
    <form className="form container2">
      <div className="form-container">
        <div>
          <h2> Verify Otp </h2>
        </div>
        {otpError && <div className="error">Otp field can't be empty</div>}
        <div>Enter the otp that was sent to your email .</div>
        <div className="field">
          <input
            placeholder="otp"
            type="number"
            name="otp"
            value={otp}
            onChange={handleOtpChange}
          />
        </div>
        <div>
          <button type="button" name="send" onClick={handleSubmit}>
            Send
          </button>
        </div>
      </div>

      {/* Show loading spinner if loading is true */}
      {loading && (
        <div className="loading-spinner">
          <CircularIndeterminate />
        </div>
      )}
    </form>
  );
}
