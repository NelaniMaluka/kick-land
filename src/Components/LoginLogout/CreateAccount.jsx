import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../Security/AuthContext";
import { Link } from "react-router-dom";
import Swal from "sweetalert2";
import Alert from "./Alert";

import "./Form.css";

function CreateAccount() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [email, setEmail] = useState("");
  const [surname, setSurname] = useState("");
  const [emailError, setEmailError] = useState("");
  const [passwordError, setPasswordError] = useState("");
  const [nameError, setNameError] = useState("");

  const navigate = useNavigate();
  const useContext = useAuth();

  function handleUsernameChange(event) {
    setUsername(event.target.value);
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

  function handleSurnameChange(event) {
    setSurname(event.target.value);
  }

  async function handleSubmit(event) {
    event.preventDefault();
    try {
      // Name validation
      if (!username) {
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

      const result = await useContext.createAccount(
        username,
        surname,
        email,
        password
      );
      if (result.success) {
        showSuccessMessage(username);
        navigate("/");
      } else {
        Alert({ message: `user with this email ${email} already exists` });
      }
    } catch (error) {
      Alert({
        message:
          "We are encountering some problems. Sorry for the inconvenience",
      });
    }
  }

  function showSuccessMessage(username) {
    Swal.fire({
      position: "top-end",
      icon: "success",
      title: "Welcome: " + username,
      showConfirmButton: false,
      timer: 1500,
    });
  }

  function isValidEmail(email) {
    // Basic email format validation
    const emailRegex = /\S+@\S+\.\S+/;
    return emailRegex.test(email);
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
          <h2> Create Account </h2>
        </div>
        <div className="field">
          <input
            placeholder="Name"
            type="text"
            name="username"
            value={username}
            autoComplete="given-name"
            onChange={handleUsernameChange}
          />
          {nameError && <div className="error-message">{nameError}</div>}
        </div>
        <div className="field">
          <input
            placeholder="Surname"
            type="text"
            name="surname"
            value={surname}
            autoComplete="family-name"
            onChange={handleSurnameChange}
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
