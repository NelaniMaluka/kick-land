import React, { useState } from "react";
import { Link } from "react-router-dom";

import HamburgerMenu from "./HamburgerMenu/HamburgerMenu";

import "./NavBarMain.css";

function NavBarMain() {
  const [isHamburgerOpen, setHamburgerOpen] = useState(false);

  const toggleHamburger = () => {
    setHamburgerOpen(!isHamburgerOpen);
  };

  return (
    <nav>
      <div className="navC container2">
        <Link to="/" className="name  ">
          Kick Land
        </Link>
        <ul className="sneakers">
          <li>
            <Link to="/Shop/Dunks ">Dunks</Link>
          </li>
          <li>
            <Link to="/Shop/Air-Force">Air Force</Link>
          </li>
          <li>
            <Link to="/Shop/Jordan">Jordan</Link>
          </li>
          <li>
            <Link to="/Shop/Air-Max">Air Max</Link>
          </li>
        </ul>

        <div className="cart">
          <Link to="/Favourites">
            <img
              src="/Images/NavBarImages/heart.png"
              title="Favourites"
              alt="favourites icon"
            />
          </Link>
          <Link to="/Cart">
            <img
              src="/Images/NavBarImages/shopping-bag.png"
              title="Bag Items"
              alt="shopping-bag icon"
            ></img>
          </Link>
          <img
            id="open-hamburger"
            src="/Images/NavBarImages/o.png"
            className="hamburger"
            onClick={toggleHamburger}
          ></img>
        </div>
      </div>
      <HamburgerMenu
        isHamburgerOpen={isHamburgerOpen}
        toggleHamburger={toggleHamburger}
      />
    </nav>
  );
}

export default NavBarMain;
