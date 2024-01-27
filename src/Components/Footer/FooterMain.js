import "./FooterMain.css";

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
            <a href="mailto:Info@kickplanet.com"> info@kickland.co.za</a>
          </li>
          <li>
            <label>Phone:</label>
            <a href="tel:+27 63 396 8709"> +27 63 396 8709</a>
          </li>
        </ul>
        <ul>
          <li>
            <h2>Info</h2>
          </li>
          <li>
            <a href="">Terms of Service</a>
          </li>
          <li>
            <a href="">Refund policy</a>
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
            <a href="https://www.instagram.com/">
              <img alt="Instagram" src="/Images/FooterImages/instagram.png" />
            </a>{" "}
            <a href="https://www.facebook.com/?_rdr">
              <img alt="Facebook" src="/Images/FooterImages/facebook.png" />
            </a>{" "}
            <a href="https://www.youtube.com/">
              <img alt="Youtube" src="/Images/FooterImages/youtube.png" />
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
