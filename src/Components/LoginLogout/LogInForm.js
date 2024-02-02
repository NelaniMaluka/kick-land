import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../../Security/AuthContext";
import { Link } from "react-router-dom";

import "./LogInForm.css";

function LoginForm() {
  const [email, setEmail] = useState(null);
  const [password, setPassword] = useState(null);
  const [ShowErrormessage, setShowErrormessage] = useState(false);

  const navigate = useNavigate();
  const authContext = useAuth();

  function handleEmailChange(event) {
    setEmail(event.target.value);
  }

  function handlePasswordChange(event) {
    setPassword(event.target.value);
  }

  function handleSubmit() {
    authContext
      .Login(email, password)
      .then(function (result) {
        if (result === true) {
          navigate("/");
        } else {
          setShowErrormessage(true);
        }
      })
      .catch(function (error) {
        console.error("Error during login:", error);
        setShowErrormessage(true);
      });
  }

  return (
    <form className="login-form container2">
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
          <button type="button" name="login" onClick={handleSubmit}>
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
