import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";
import ErrorMessageAlert from "../../Components/Alerts/ErrorMessageAlert";
import { isValidEmail } from "../../Utils/FormValidations";
import { isValidPassword } from "../../Utils/FormValidations";

import "../../Components/Styling/Form.css";
import { useDispatch } from "react-redux";
import { registerUser } from "../../State/Authentication/Action";
import showSuccessMessage from "../../Components/Alerts/SuccessLoginAlert";

export default function CreateAccount() {
  const [firstname, setFirstname] = useState("");
  const [password, setPassword] = useState("");
  const [email, setEmail] = useState("");
  const [lastname, setLastname] = useState("");
  const [emailError, setEmailError] = useState("");
  const [passwordError, setPasswordError] = useState("");
  const [nameError, setNameError] = useState("");
  const [signUpForNewsletter, setSignUpForNewslette] = useState(false);

  const dispatch = useDispatch();
  const navigate = useNavigate();

  const registerData = {
    firstname,
    password,
    email,
    lastname,
    role: "ROLE_CUSTOMER",
  };

  function handleFirstnameChange(event) {
    setFirstname(event.target.value);
    // Reset name error message
    setNameError("");
  }

  function handlePasswordChange(event) {
    setPassword(event.target.value);
    // Reset password error message
    setPasswordError("");
  }

  function handleEmailChange(event) {
    setEmail(event.target.value);
    // Reset email error message
    setEmailError("");
  }

  function handleLastnameChange(event) {
    setLastname(event.target.value);
  }

  function handleSignUpForNewsletter() {
    setSignUpForNewslette(!signUpForNewsletter);
  }

  async function handleSubmit(event) {
    event.preventDefault();
    try {
      // Name validation
      if (!firstname) {
        setNameError("Name is required.");
        return;
      }

      // Email validation
      if (!email) {
        setEmailError("Email is required.");
        return;
      } else if (!isValidEmail(email)) {
        setEmailError("Invalid email format.");
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

      const result = await dispatch(
        registerUser(registerData, signUpForNewsletter)
      );

      if (result && result.status >= 200 && result.status < 300) {
        // Successful registration
        showSuccessMessage("Welcome: " + firstname);
        navigate("/");
      } else if (result?.status === 409) {
        // Conflict - email already exists
        ErrorMessageAlert({
          message: `A user with the email ${email} already exists.`,
        });
      } else if (result?.status === 400) {
        // Bad request - invalid input
        ErrorMessageAlert({
          message: "Invalid input. Please check your details and try again.",
        });
      } else if (result?.status === 500) {
        // Internal server error
        ErrorMessageAlert({
          message: "Something went wrong on our end. Please try again later.",
        });
      } else {
        // Fallback for unexpected statuses
        ErrorMessageAlert({
          message: "An unexpected error occurred. Please try again.",
        });
      }
    } catch (error) {
      if (error.response) {
        // Handle HTTP errors based on the response status
        const { status } = error.response;

        if (status === 409) {
          ErrorMessageAlert({
            message: `A user with the email ${email} already exists.`,
          });
        } else if (status === 400) {
          ErrorMessageAlert({
            message: "Invalid input. Please check your details and try again.",
          });
        } else if (status === 500) {
          ErrorMessageAlert({
            message: "Something went wrong on our end. Please try again later.",
          });
        } else {
          ErrorMessageAlert({
            message: `Unexpected error: ${status}. Please try again.`,
          });
        }
      } else if (error.request) {
        // Handle network issues or no server response
        ErrorMessageAlert({
          message: "Network error. Please check your connection and try again.",
        });
      } else {
        // Handle unexpected errors in code logic
        ErrorMessageAlert({
          message:
            "We are encountering some problems. Sorry for the inconvenience.",
        });
      }
    }
  }

  return (
    <form className="form container2" onSubmit={handleSubmit}>
      <div className="form-container">
        <div>
          <h2> Create Account </h2>
        </div>
        <div className="field">
          <input
            placeholder="Name"
            type="text"
            name="username"
            value={firstname}
            autoComplete="given-name"
            onChange={handleFirstnameChange}
          />
          {nameError && <div className="error-message">{nameError}</div>}
        </div>
        <div className="field">
          <input
            placeholder="Surname"
            type="text"
            name="lastname"
            value={lastname}
            autoComplete="family-name"
            onChange={handleLastnameChange}
          />
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
        <div
          style={{
            display: "flex",
            justifyContent: "center", // Centers horizontally
            alignItems: "center", // Centers everything vertically
          }}
        >
          <label
            htmlFor="newsletter"
            style={{
              display: "flex",
              alignItems: "center", // Keeps checkbox and text in one line
              fontSize: "16px",
              cursor: "pointer",
              textAlign: "center",
            }}
          >
            <input
              type="checkbox"
              id="newsletter"
              name="newsletter"
              value="yes"
              checked={signUpForNewsletter}
              onChange={handleSignUpForNewsletter}
              style={{
                width: "20px",
                height: "20px",
                marginRight: "10px", // Adds spacing between checkbox and text
                cursor: "pointer",
                accentColor: "#4CAF50",
              }}
            />
            <span style={{ position: "relative", top: "-7px" }}>
              Subscribe to our newsletter
            </span>
          </label>
        </div>

        <div>
          <button type="submit" name="login">
            Create
          </button>
        </div>
        <ul>
          <li>
            <Link to="/Login">Login</Link>
          </li>
        </ul>
      </div>
    </form>
  );
}
