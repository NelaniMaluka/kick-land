import React, { useState } from "react";
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
                <a href="../ ">Dunks</a>
              </li>
              <li>
                <a href="../ ">Air Force</a>
              </li>
              <li>
                <a href="../ ">Jordan</a>
              </li>
              <li>
                <a href="../ ">Air Max</a>
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
                <a href="../home">Help</a>
              </li>
              <li>
                <a href="../home">About Us</a>
              </li>
              <li>
                <a href="../home">Terms of Service</a>
              </li>
              <li>
                <a href="../home">Refund Policy</a>
              </li>
            </ul>
          </div>
        </div>
      )}
    </>
  );
}

export default HamburgerMenu;
