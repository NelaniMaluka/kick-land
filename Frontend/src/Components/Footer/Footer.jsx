import "./Footer.css";
import { Link } from "react-router-dom";
import { useState } from "react";
import showSuccessMessage from "../Alerts/SuccessMessageAlert";
import ErrorMessageAlert from "../Alerts/ErrorMessageAlert";
import { SignForNewsletter } from "../../Context/Api";
import CircularIndeterminate from "../../Utils/LoadingSpinner";

export default function Footer() {
  const [email, setEmail] = useState("");
  const [emailError, setEmailError] = useState("");
  const [loading, setLoading] = useState(false);

  // Updates the email value
  const handleEmailChange = (event) => {
    setEmail(event.target.value);
    // Reset email error message
    setEmailError("");
  };

  // Newsletter Subscription Function
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

    setLoading(true);

    try {
      const result = await SignForNewsletter(email);

      if (result.status === 200) {
        showSuccessMessage("Success!", result.data); // Email successfully subscribed
      } else if (result.status === 400) {
        ErrorMessageAlert({
          message:
            "Invalid email format or missing data. Please check your input.",
        });
      } else if (result.status === 401) {
        ErrorMessageAlert({
          message: "You are not authorized to sign up for the newsletter.",
        });
      } else if (result.status === 404) {
        ErrorMessageAlert({
          message: "The newsletter service could not be found.",
        });
      } else if (result.status === 409) {
        ErrorMessageAlert({
          message: "Email already subscribed to the newsletter.",
        });
      } else if (result.status === 500) {
        ErrorMessageAlert({
          message: "Something went wrong on our side. Please try again later.",
        });
      } else {
        ErrorMessageAlert({ message: "An unexpected error occurred." });
      }
    } catch (error) {
      ErrorMessageAlert({ message: "Could not sign up for newsletter" });
    }

    setEmail("");
  }

  // Validates the email format
  const isValidEmail = (email) => {
    // Basic email format validation
    const emailRegex = /\S+@\S+\.\S+/;
    return emailRegex.test(email);
  };

  // Social Links
  const socialLinks = {
    instagram: {
      title: "Instagram",
      href: "https://www.instagram.com/",
      imgSrc: "/Images/FooterImages/instagram.png",
    },
    facebook: {
      title: "Facebook",
      href: "https://www.facebook.com/?_rdr",
      imgSrc: "/Images/FooterImages/facebook.png",
    },
    youtube: {
      title: "Youtube",
      href: "https://www.youtube.com/",
      imgSrc: "/Images/FooterImages/youtube.png",
    },
  };

  // Gets the current year
  let getYear = () => {
    const currentYear = new Date().getFullYear();
    return currentYear;
  };

  return (
    <div className="footer">
      <div className="container2">
        {/* Text Section */}
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

        {/* Newsletter Section */}
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
                autoComplete="email"
                onChange={handleEmailChange}
              />
              <button type="submit">Send</button>
              {emailError && <div className="error-message">{emailError}</div>}
            </form>
          </li>

          {/* Show loading spinner if loading is true */}
          {loading && (
            <div className="loading-spinner">
              <CircularIndeterminate />
            </div>
          )}

          {/* Social Links */}
          <li>
            {Object.entries(socialLinks).map(
              ([key, { href, imgSrc, title }]) => (
                <a
                  key={key}
                  href={href}
                  target="_blank"
                  rel="noopener noreferrer"
                >
                  <img alt={title} title={title} src={imgSrc} />
                </a>
              )
            )}
          </li>
        </ul>
      </div>

      {/* Copywright section */}
      <div className="copywright container2">
        Copyright &copy; {getYear()} Kick Land
      </div>
    </div>
  );
}
