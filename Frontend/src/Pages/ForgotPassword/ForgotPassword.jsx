import { useState } from "react";
import { useAuth } from "../../Context/AuthContext";
import { Link } from "react-router-dom";
import ErrorMessageAlert from "../../Components/Alerts/ErrorMessageAlert";
import showSuccessMessage from "../../Components/Alerts/SuccessMessageAlert.jsx";

import "../../Components/Styling/Form.css";

function ForgotPassword() {
  const [email, setEmail] = useState("");
  const [EmailError, setEmailError] = useState(false);

  const useContext = useAuth();

  function handleEmailChange(event) {
    setEmail(event.target.value);
  }

  function isValidEmail(email) {
    // Basic email format validation
    const emailRegex = /\S+@\S+\.\S+/;
    return emailRegex.test(email);
  }

  async function handleSubmit(event) {
    // Email validation
    if (!email) {
      setEmailError("Email is required.");
      return;
    } else if (!isValidEmail(email)) {
      setEmailError("Invalid email format.");
      return;
    }

    const result = await useContext.forgotPassword(email);
    try {
      if (result.success) {
        showSuccessMessage("Success!", result.response.data);
      } else {
        ErrorMessageAlert({ message: "Invalid Credentials" });
      }
    } catch (error) {
      ErrorMessageAlert({
        message: "We are encountering problems. Sorry for the inconvenience.",
      });
    }
  }

  return (
    <form className="form container2">
      <div className="form-container">
        <div>
          <h2> Reset Password </h2>
        </div>
        {EmailError && <div className="error">Email field can't be empty</div>}
        <div>We will send you an email to reset your password.</div>
        <div className="field">
          <input
            placeholder="Email"
            type="email"
            name="email"
            value={email}
            autoComplete="email"
            onChange={handleEmailChange}
          />
        </div>
        <div>
          <button type="button" name="send" onClick={handleSubmit}>
            Send
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
