import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";
import ErrorMessageAlert from "../../Components/Alerts/ErrorMessageAlert";
import isValidEmail from "../../Utils/EmailValidation";
import isValidPassword from "../../Utils/PasswordValidation";

import "../../Components/Styling/Form.css";
import { useDispatch } from "react-redux";
import { registerUser } from "../../State/Authentication/Action";
import showSuccessMessage from "../../Components/Alerts/SuccessLoginAlert";

function CreateAccount() {
  const [firstname, setFirstname] = useState("");
  const [password, setPassword] = useState("");
  const [email, setEmail] = useState("");
  const [lastname, setLastname] = useState("");
  const [emailError, setEmailError] = useState("");
  const [passwordError, setPasswordError] = useState("");
  const [nameError, setNameError] = useState("");

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

      const result = dispatch(registerUser(registerData, navigate));
      if (result) {
        showSuccessMessage("Welcome: " + firstname);
        navigate("/");
      } else {
        ErrorMessageAlert({
          message: `user with this email ${email} already exists`,
        });
      }
    } catch (error) {
      ErrorMessageAlert({
        message:
          "We are encountering some problems. Sorry for the inconvenience",
      });
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

export default CreateAccount;
