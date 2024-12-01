import { Link } from "react-router-dom";
import { useAuth } from "../../../Context/AuthContext";

import "../Layers/NavBarMainBanner.css";
import "./HamburgerMenu.css";

function HamburgerMenu({ isHamburgerOpen, toggleHamburger }) {
  const authContext = useAuth();
  const isAuthenticated = authContext.isAuthenticated;

  function handleLogout() {
    authContext.logout();
  }

  function handleCloseMenu() {
    if (isHamburgerOpen) {
      toggleHamburger();
    }
  }

  return (
    <>
      {isHamburgerOpen && (
        <div className={`hamburger-menu ${isHamburgerOpen ? "open" : ""}`}>
          <div className="hamburger-header">
            <h1 className="names  ">Shop</h1>
            <img
              id="close-hamburger"
              src="/Images/NavBarImages/x.png"
              className="shopping-bag icons"
              alt="Close Hamburger"
              onClick={toggleHamburger}
            ></img>
          </div>
          <div className="shop">
            <ul className="sneakers-hamburger">
              <li>
                <Link to="/shop/Dunk " onClick={handleCloseMenu}>
                  Dunks
                </Link>
              </li>
              <li>
                <Link to="/shop/AirForce " onClick={handleCloseMenu}>
                  Air Force
                </Link>
              </li>
              <li>
                <Link to="/shop/Jordan" onClick={handleCloseMenu}>
                  Jordan
                </Link>
              </li>
              <li>
                <Link to="/shop/AirMax" onClick={handleCloseMenu}>
                  Air Max
                </Link>
              </li>
            </ul>
          </div>
          <hr />
          <div className="details">
            <ul>
              {!isAuthenticated && (
                <li>
                  <Link to="/Login" onClick={handleCloseMenu}>
                    Log In
                  </Link>
                </li>
              )}
              {isAuthenticated && (
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
      )}
    </>
  );
}

export default HamburgerMenu;
