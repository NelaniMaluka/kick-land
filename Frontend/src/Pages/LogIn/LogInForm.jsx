import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";
import ErrorMessageAlert from "../../Components/Alerts/ErrorMessageAlert";
import isValidEmail from "../../Utils/EmailValidation";
import isValidPassword from "../../Utils/PasswordValidation";
import showSuccessMessage from "../../Components/Alerts/SuccessLoginAlert";

import "../../Components/Styling/Form.css";
import { useDispatch } from "react-redux";
import { loginUser } from "../../State/Authentication/Action";

function LoginForm() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [emailError, setEmailError] = useState("");
  const [passwordError, setPasswordError] = useState("");
  const dispatch = useDispatch();

  const navigate = useNavigate();

  const loginData = {
    email,
    password,
  };

  function handleEmailChange(event) {
    setEmail(event.target.value);
    // Reset email error message
    setEmailError("");
  }

  function handlePasswordChange(event) {
    setPassword(event.target.value);
    // Reset password error message
    setPasswordError("");
  }

  async function handleSubmit(event) {
    event.preventDefault();
    try {
      // Email validation
      if (!email) {
        setEmailError("Email is required.");
        return;
      } else if (!isValidEmail(email)) {
        setPasswordError("Invalid email format.");
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

      const result = await dispatch(loginUser(loginData));

      if (result && result.status >= 200 && result.status < 300) {
        // Successful login
        showSuccessMessage("Welcome Back");
        navigate("/");
      } else if (result?.status === 401) {
        // Unauthorized: Invalid credentials
        ErrorMessageAlert({
          message: "Invalid credentials. Please check your email and password.",
        });
      } else if (result?.status === 400) {
        // Bad request: Missing or invalid input
        ErrorMessageAlert({
          message:
            "Invalid input. Please provide all required fields correctly.",
        });
      } else if (result?.status >= 500) {
        // Internal server error
        ErrorMessageAlert({
          message:
            "An unexpected server error occurred. Please try again later.",
        });
      } else {
        // Fallback for unexpected responses
        ErrorMessageAlert({
          message: "An unexpected error occurred. Please try again.",
        });
      }
    } catch (error) {
      if (error.response) {
        // Handle HTTP errors
        const { status } = error.response;

        if (status === 401) {
          ErrorMessageAlert({
            message:
              "Invalid credentials. Please check your email and password.",
          });
        } else if (status === 400) {
          ErrorMessageAlert({
            message:
              "Invalid input. Please provide all required fields correctly.",
          });
        } else if (status >= 500) {
          ErrorMessageAlert({
            message:
              "An unexpected server error occurred. Please try again later.",
          });
        } else {
          ErrorMessageAlert({
            message: `Unexpected error: ${status}. Please try again.`,
          });
        }
      } else if (error.request) {
        // Handle network errors or no response
        ErrorMessageAlert({
          message: "Network error. Please check your connection and try again.",
        });
      } else {
        // Handle unexpected errors in code logic
        ErrorMessageAlert({
          message: "We are encountering problems. Sorry for the inconvenience.",
        });
      }
    }
  }

  return (
    <form className="form container2" onSubmit={handleSubmit}>
      <div className="form-container">
        <div>
          <h2> Login </h2>
        </div>
        <div className="field">
          <input
            placeholder="Email"
            type="email"
            name="email"
            value={email}
            autoComplete="email"
            onChange={handleEmailChange}
          />
          {emailError && <div className="error-message">{emailError}</div>}
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
        <div>
          <button type="submit" name="login">
            LogIn
          </button>
        </div>
        <ul>
          <li>
            <Link to="/Create-Account">Create an Account</Link>
          </li>
          <li>
            <Link to="/Forgot-Password">Forgot your password ?</Link>
          </li>
        </ul>
      </div>
    </form>
  );
}

export default LoginForm;
