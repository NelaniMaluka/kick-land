import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../../Security/AuthContext";
import { Link } from "react-router-dom";

import "./LogInForm.css";

function LoginForm() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [ShowErrormessage, setShowErrormessage] = useState(false);

  const navigate = useNavigate();
  const authContext = useAuth();

  function handleUsernameChange(event) {
    setUsername(event.target.value);
  }

  function handlePasswordChange(event) {
    setPassword(event.target.value);
  }

  function handleSubmit() {
    if (authContext.Login(username, password)) {
      console.log(username);
      console.log(password);
      navigate("/");
    } else {
      console.log(username);
      console.log(password);
      setShowErrormessage(true);
    }
  }

  return (
    <form className="login-form container2">
      <div className="login-container">
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
            type="text"
            name="username"
            value={username}
            onChange={handleUsernameChange}
          />
        </div>
        <div className="field">
          <input
            placeholder="Password"
            type="text"
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
