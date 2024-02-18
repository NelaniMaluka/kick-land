import "./FooterMain.css";
import { Link } from "react-router-dom";
import { useState } from "react";
import { useAuth } from "../Security/AuthContext";
import Swal from "sweetalert2";

function FooterMain() {
  const [email, setEmail] = useState("");

  const authContext = useAuth();

  function handleEmailChange(event) {
    setEmail(event.target.value);
  }

  function handleSubmit() {
    authContext
      .signUpForNewsletter(email)
      .then(function (result) {
        if (result.success) {
          showSuccessMessage();
        } else {
          showErrorMessage(
            "Invalid format or we,ve already recived your subscibtion"
          );
        }
      })
      .catch(function (error) {
        showErrorMessage("Could sign");
      });
    setEmail("");
  }

  function showSuccessMessage() {
    Swal.fire({
      icon: "success",
      title: "Sent",
      text: "We recievd your Email",
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
            <input
              type="email"
              placeholder="Your Email"
              name="email"
              value={email}
              onChange={handleEmailChange}
            />
            <button onClick={handleSubmit}>Send</button>
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
      <div class="copywright container2">
        Copyright &copy; {getYear()} Kick Land
      </div>
    </div>
  );
}

export default FooterMain;
