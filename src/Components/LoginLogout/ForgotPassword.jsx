import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../Security/AuthContext";
import { Link } from "react-router-dom";

import "./Form.css";

function ForgotPassword() {
  const [email, setEmail] = useState("");
  const [ShowErrormessage, setShowErrormessage] = useState(false);

  const navigate = useNavigate();
  const useContext = useAuth();

  function handleEmailChange(event) {
    setEmail(event.target.value);
  }

  function handleSubmit() {}

  return (
    <form className="form container2">
      <div className="form-container">
        <div>
          <h2> Reset Password </h2>
        </div>
        {ShowErrormessage && (
          <div className="error">Email field can't be empty</div>
        )}
        <div>We will send you an email to reset your password.</div>
        <div className="field">
          <input
            placeholder="Email"
            type="email"
            name="email"
            value={email}
            onChange={handleEmailChange}
          />
        </div>
        <div>
          <button type="button" name="login" onClick={handleSubmit}>
            LogIn
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
    </form>
  );
}
export default ForgotPassword;
