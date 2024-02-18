import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../Security/AuthContext";
import { Link } from "react-router-dom";
import Swal from "sweetalert2";

import "./Form.css";

function LoginForm() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [ShowErrormessage, setShowErrormessage] = useState(false);
  const [message, setMessage] = useState("");
  const useContext = useAuth();

  const navigate = useNavigate();

  function handleEmailChange(event) {
    setEmail(event.target.value);
  }

  function handlePasswordChange(event) {
    setPassword(event.target.value);
  }

  function handleSubmit(event) {
    event.preventDefault();
    useContext
      .login(email, password)
      .then(function (result) {
        if (result.success) {
          showSuccessMessage();
          navigate("/");
        } else {
          setMessage("Invalid Credentials");
          setShowErrormessage(true);
        }
      })
      .catch(function (error) {
        setMessage("We are incountering problems Sorry for the inconvinience ");
        setShowErrormessage(true);
      });
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

  return (
    <form className="form container2" onSubmit={handleSubmit}>
      <div className="form-container">
        <div>
          <h2> Login </h2>
        </div>
        {ShowErrormessage && (
          <div className="error">
            LogIn failed. Please check Your credentials
          </div>
        )}
        <div className="field">
          <input
            placeholder="Email"
            type="email"
            name="email"
            value={email}
            onChange={handleEmailChange}
          />
        </div>
        <div className="field">
          <input
            placeholder="Password"
            type="password"
            name="password"
            value={password}
            onChange={handlePasswordChange}
          />
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
            <Link to="/Forgot-Password">Forgot your password?</Link>
          </li>
        </ul>
      </div>
    </form>
  );
}

export default LoginForm;
