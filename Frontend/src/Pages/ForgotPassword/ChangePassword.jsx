import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../../Context/AuthContext";
import ErrorMessageAlert from "../../Components/Alerts/ErrorMessageAlert";
import { isValidPassword } from "../../Utils/FormValidations";
import showSuccessMessage from "../../Components/Alerts/SuccessLoginAlert";
import CircularIndeterminate from "../../Utils/LoadingSpinner";

import "../../Components/Styling/Form.css";

// Handles Password Change
export default function ChangePassword() {
  const [repeatPassword, setRepeatPassword] = useState("");
  const [password, setPassword] = useState("");
  const [repeatPasswordError, setRepeatPasswordError] = useState("");
  const [passwordError, setPasswordError] = useState("");
  const [loading, setLoading] = useState(false);
  const useContext = useAuth();

  const navigate = useNavigate();

  const handleRepeatPasswordChange = (event) => {
    setRepeatPassword(event.target.value);
    // Reset error message
    setRepeatPasswordError("");
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
    // Reset password error message
    setPasswordError("");
  };

  async function handleSubmit(event) {
    event.preventDefault();
    setLoading(true);

    try {
      // Email validation
      if (!repeatPassword) {
        setRepeatPasswordError("Password is required.");
        return;
      } else if (!isValidPassword(repeatPassword)) {
        setRepeatPasswordError(
          "Password must contain at least one special character, one capital letter, minimum of 8 characters, and one number."
        );
        return;
      }

      // Password validation
      if (!password) {
        setPasswordError("Password is required.");
        return;
      } else if (!isValidPassword(password)) {
        setPasswordError(
          "Password must contain at least one special character, one capital letter, minimum of 8 characters, and one number."
        );
        return;
      }

      if (password !== repeatPassword) {
        setRepeatPasswordError("Passwords do not match.");
        return;
      }

      const result = await useContext.changePassword(password, repeatPassword);
      if (result.success) {
        showSuccessMessage("Password updated successfully");
        navigate("/");
      } else {
        switch (result.status) {
          case 400:
            ErrorMessageAlert({
              message: "Bad Request. Please check the input fields.",
            });
            break;
          case 401:
            ErrorMessageAlert({
              message: "Unauthorized. Email or session invalid.",
            });
            break;
          case 422:
            ErrorMessageAlert({
              message:
                "Unprocessable Entity. Passwords do not match or do not meet requirements.",
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
        message: "We are encountering problems. Sorry for the inconvenience.",
      });
    }

    setLoading(false);
  }

  return (
    <form className="form container2" onSubmit={handleSubmit}>
      <div className="form-container">
        <div>
          <h2> Change Password </h2>
        </div>
        <div className="field">
          <input
            placeholder="Password"
            type="password"
            name="password"
            value={password}
            autoComplete="current-password"
            onChange={handlePasswordChange}
          />
          {passwordError && (
            <div className="error-message">{passwordError}</div>
          )}
        </div>
        <div className="field">
          <input
            placeholder="Confirm Password"
            type="password"
            name="repeatPassword"
            value={repeatPassword}
            autoComplete="new-password"
            onChange={handleRepeatPasswordChange}
          />
          {repeatPasswordError && (
            <div className="error-message">{repeatPasswordError}</div>
          )}
        </div>

        <div>
          <button type="submit" name="login">
            LogIn
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
