import "./FooterMain.css";
import { Link } from "react-router-dom";

function FooterMain() {
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
            <input title="submit" placeholder="Your Email" />
            <button>Send</button>
          </li>
          <li>
            <Link to="https://www.instagram.com/">
              <img alt="Instagram" src="/Images/FooterImages/instagram.png" />
            </Link>
            <Link to="https://www.facebook.com/?_rdr">
              <img alt="Facebook" src="/Images/FooterImages/facebook.png" />
            </Link>
            <Link to="https://www.youtube.com/">
              <img alt="Youtube" src="/Images/FooterImages/youtube.png" />
            </Link>
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
