import { Link } from "react-router-dom";
import "../Layers/NavBarMainBanner.css";
import "./HamburgerMenu.css";
import { useDispatch, useSelector } from "react-redux";
import { logoutUser } from "../../../State/Authentication/Action";

export default function HamburgerMenu({ isHamburgerOpen, toggleHamburger }) {
  const auth = useSelector((state) => state.auth);
  const dispatch = useDispatch();

  const handleLogout = () => {
    dispatch(logoutUser());
  };

  const handleCloseMenu = () => {
    if (isHamburgerOpen) {
      toggleHamburger();
    }
  };

  // ✅ Sneaker links as objects
  const sneakerLinks = [
    { name: "Dunks", path: "/shop/Dunk" },
    { name: "Air Force", path: "/shop/AirForce" },
    { name: "Jordan", path: "/shop/Jordan" },
    { name: "Air Max", path: "/shop/AirMax" },
  ];

  return (
    <>
      <div className={`hamburger-menu ${isHamburgerOpen ? "open" : ""}`}>
        {/* Header */}
        <div className="hamburger-header">
          <h1 className="names">Shop</h1>
          <img
            id="close-hamburger"
            src="/Images/NavBarImages/x.png"
            className="shopping-bag icons"
            alt="Close Hamburger"
            onClick={toggleHamburger}
          />
        </div>

        {/* Sneaker Links */}
        <div className="shop">
          <ul className="sneakers-hamburger">
            {sneakerLinks.map((sneaker, index) => (
              <li key={index}>
                <Link to={sneaker.path} onClick={handleCloseMenu}>
                  {sneaker.name}
                </Link>
              </li>
            ))}
          </ul>
        </div>

        {/* Details Links */}
        <div className="details">
          <ul>
            {!auth.authenticated && (
              <li>
                <Link to="/Login" onClick={handleCloseMenu}>
                  Log In
                </Link>
              </li>
            )}
            {auth.authenticated && (
              <li>
                <Link onClick={handleLogout}>Log Out</Link>
              </li>
            )}
            <li>
              <Link to="/Info/Help" onClick={handleCloseMenu}>
                Help
              </Link>
            </li>
            <li>
              <Link to="/Info/About-Us" onClick={handleCloseMenu}>
                About Us
              </Link>
            </li>
            <li>
              <Link to="/Info/Terms-of-Service" onClick={handleCloseMenu}>
                Terms of Service
              </Link>
            </li>
            <li>
              <Link to="/Info/Refund-Policy" onClick={handleCloseMenu}>
                Refund Policy
              </Link>
            </li>
          </ul>
        </div>
      </div>
    </>
  );
}
