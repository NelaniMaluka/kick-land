import React, { useState } from "react";
import { Link } from "react-router-dom";
import Cart from "../Icons/CartIcon";
import HamburgerMenu from "../HamburgerMenu/HamburgerMenu";
import Profile from "../Icons/ProfileIcon";

import "./NavBarMainBanner.css";

export default function NavBarMainBanner() {
  const [isHamburgerOpen, setHamburgerOpen] = useState(false);

  const toggleHamburger = () => {
    setHamburgerOpen(!isHamburgerOpen);
  };

  // Sneaker link object array
  const sneakerLinks = [
    { name: "Dunks", path: "/Shop/Dunk" },
    { name: "Air Force", path: "/Shop/AirForce" },
    { name: "Jordan", path: "/Shop/Jordan" },
    { name: "Air Max", path: "/Shop/AirMax" },
  ];

  return (
    <div className="main-nav">
      <nav className=" container2">
        {/* Logo Section */}
        <Link to="/" className="logo">
          Kick Land
        </Link>

        {/* Sneaker Links Section */}
        <ul className="sneakers">
          {sneakerLinks.map((sneaker, index) => (
            <li key={index}>
              <Link to={sneaker.path}>{sneaker.name}</Link>
            </li>
          ))}
        </ul>

        {/* Cart Section */}
        <div className="cart">
          <Profile />
          <Link to="/Dashboard" title="Cart">
            <Cart />
          </Link>

          <img
            id="open-hamburger"
            src="/Images/NavBarImages/o.png"
            className="hamburger"
            onClick={toggleHamburger}
            alt="Menu Icon"
          />
        </div>
      </nav>

      <HamburgerMenu
        isHamburgerOpen={isHamburgerOpen}
        toggleHamburger={toggleHamburger}
      />
    </div>
  );
}
