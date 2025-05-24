import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../../Context/AuthContext";
import { Link } from "react-router-dom";
import ErrorMessageAlert from "../../Components/Alerts/ErrorMessageAlert";
import showSuccessMessage from "../../Components/Alerts/SuccessMessageAlert.jsx";
import { isValidEmail } from "../../Utils/FormValidations.js";
import CircularIndeterminate from "../../Utils/LoadingSpinner.jsx";

import "../../Components/Styling/Form.css";

// Handles Email Verification
export default function ForgotPassword() {
  const [email, setEmail] = useState("");
  const [EmailError, setEmailError] = useState(false);
  const [loading, setLoading] = useState(false);
  const useContext = useAuth();
  const navigate = useNavigate();

  const handleEmailChange = (event) => {
    setEmail(event.target.value);
  };

  async function handleSubmit(event) {
    setLoading(true);

    // Email validation
    if (!email) {
      setEmailError("Email is required.");
      return;
    } else if (!isValidEmail(email)) {
      setEmailError("Invalid email format.");
      return;
    }

    const result = await useContext.forgotPassword(email);

    try {
      if (result.success) {
        showSuccessMessage("Success!", result.response.data);
        navigate("/Verify-Otp");
      } else {
        switch (result.status) {
          case 400:
            ErrorMessageAlert({
              message: "Bad Request. Please check your input.",
            });
            break;
          case 401:
            ErrorMessageAlert({
              message: "Unauthorized. Invalid credentials.",
            });
            break;
          case 404:
            ErrorMessageAlert({
              message: "Email not found. Please try again.",
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
          <h2> Reset Password </h2>
        </div>
        {EmailError && <div className="error">Email field can't be empty</div>}
        <div>We will send you an email to reset your password.</div>
        <div className="field">
          <input
            placeholder="Email"
            type="email"
            name="email"
            value={email}
            autoComplete="email"
            onChange={handleEmailChange}
          />
        </div>
        <div>
          <button type="button" name="send" onClick={handleSubmit}>
            Send
          </button>
        </div>
        <ul>
          <li>
            <Link to="/Login">Login</Link>
          </li>
          <li>
            <Link to="/Create-Account">Create an Account</Link>
          </li>
        </ul>
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
