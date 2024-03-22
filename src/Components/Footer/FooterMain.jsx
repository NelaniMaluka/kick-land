// FooterMain.js

import "./FooterMain.css";
import { Link } from "react-router-dom";
import { useState } from "react";
import { useAuth } from "../Security/AuthContext";
import Swal from "sweetalert2";

function FooterMain() {
  const [email, setEmail] = useState("");
  const [emailError, setEmailError] = useState("");

  const authContext = useAuth();

  function handleEmailChange(event) {
    setEmail(event.target.value);
    // Reset email error message
    setEmailError("");
  }

  async function handleSubmit(event) {
    event.preventDefault(); // Prevent form submission

    // Email validation
    if (!email) {
      setEmailError("Email is required.");
      return;
    } else if (!isValidEmail(email)) {
      setEmailError("Invalid email format.");
      return;
    }

    try {
      const result = await authContext.signUpForNewsletter(email);
      if (result.success) {
        showSuccessMessage();
      } else {
        showErrorMessage("Email already received");
      }
    } catch (error) {
      showErrorMessage("Could not sign up for newsletter");
    }

    setEmail("");
  }

  function isValidEmail(email) {
    // Basic email format validation
    const emailRegex = /\S+@\S+\.\S+/;
    return emailRegex.test(email);
  }

  function showSuccessMessage() {
    Swal.fire({
      icon: "success",
      title: "Sent",
      text: "We received your Email",
    });
  }

  function showErrorMessage(error) {
    Swal.fire({
      icon: "error",
      title: "Oops...",
      text: error,
    });
  }

  let getYear = () => {
    const currentYear = new Date().getFullYear();
    return currentYear;
  };

  return (
    <div className="footer">
      <div className="container2">
        <ul>
          <li>
            <h2>Contact Us</h2>
          </li>
          <li>Do not hesitate to give us a ring!</li>
          <li>
            <label>Email:</label>
            <Link to="mailto:Info@kickplanet.com"> info@kickland.co.za</Link>
          </li>
          <li>
            <label>Phone:</label>
            <Link to="tel:+27 63 396 8709"> +27 63 396 8709</Link>
          </li>
        </ul>
        <ul>
          <li>
            <h2>Info</h2>
          </li>
          <li>
            <Link to="/Info/Terms-of-Service">Terms of Service</Link>
          </li>
          <li>
            <Link to="/Info/Refund-Policy">Refund policy</Link>
          </li>
        </ul>
        <ul>
          <li>
            <h2>Subscribe To Our Newsletter</h2>
          </li>
          <li>
            <form onSubmit={handleSubmit}>
              <input
                type="email"
                placeholder="Your Email"
                name="email"
                value={email}
                onChange={handleEmailChange}
              />
              <button type="submit">Send</button>
              {emailError && <div className="error-message">{emailError}</div>}
            </form>
          </li>
          <li>
            <a href="https://www.instagram.com/">
              <img
                alt="Instagram"
                title="Instagram"
                src="/Images/FooterImages/instagram.png"
              />
            </a>
            <a href="https://www.facebook.com/?_rdr">
              <img
                alt="Facebook"
                title="Facebook"
                src="/Images/FooterImages/facebook.png"
              />
            </a>
            <a href="https://www.youtube.com/">
              <img
                alt="Youtube"
                title="Youtube"
                src="/Images/FooterImages/youtube.png"
              />
            </a>
          </li>
        </ul>
      </div>
      <div className="copywright container2">
        Copyright &copy; {getYear()} Kick Land
      </div>
    </div>
  );
}

export default FooterMain;
