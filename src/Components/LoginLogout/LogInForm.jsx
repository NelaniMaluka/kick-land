import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../Security/AuthContext";
import { Link } from "react-router-dom";
import Swal from "sweetalert2";
import Alert from "./Alert";

import "./Form.css";

function LoginForm() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [emailError, setEmailError] = useState("");
  const [passwordError, setPasswordError] = useState("");
  const useContext = useAuth();

  const navigate = useNavigate();

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
      const result = await useContext.login(email, password);
      if (result.success) {
        showSuccessMessage();
        navigate("/");
      } else {
        Alert({ message: "Invalid Credentials" });
      }
    } catch (error) {
      Alert({
        message: "We are encountering problems. Sorry for the inconvenience.",
      });
    }
  }

  function showSuccessMessage() {
    Swal.fire({
      position: "top-end",
      icon: "success",
      title: "Welcome Back",
      showConfirmButton: false,
      timer: 1500,
    });
  }

  function isValidPassword(password) {
    // Password validation regex: at least one special character, one capital letter, one number, and minimum 8 characters
    const passwordRegex = /^(?=.*[!@#$%^&*])(?=.*[A-Z])(?=.*[0-9]).{8,}$/;
    return passwordRegex.test(password);
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
