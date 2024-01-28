import React, { useState } from "react";
import { Link } from "react-router-dom";
import LogText from "../../LoginLogout/LogText";

import "../NavBarMain.css";
import "./HamburgerMenu.css";

function HamburgerMenu({ isHamburgerOpen, toggleHamburger }) {
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
                <Link to="/shop/Dunk ">Dunks</Link>
              </li>
              <li>
                <Link to="/shop/Air-Force ">Air Force</Link>
              </li>
              <li>
                <Link to="/shop/Jordan">Jordan</Link>
              </li>
              <li>
                <Link to="/shop/Air-Max">Air Max</Link>
              </li>
            </ul>
          </div>
          <hr />
          <div className="details">
            <ul>
              <li>
                <LogText />
              </li>
              <li>
                <Link to="/Info/Help">Help</Link>
              </li>
              <li>
                <Link to="/Info/About-Us">About Us</Link>
              </li>
              <li>
                <Link to="/Info/Terms-of-Service">Terms of Service</Link>
              </li>
              <li>
                <Link to="/Info/Refund-Policy">Refund Policy</Link>
              </li>
            </ul>
          </div>
        </div>
      )}
    </>
  );
}

export default HamburgerMenu;
