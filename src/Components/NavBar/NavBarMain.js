import React, { useState, useCallback } from "react";
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
        <a href="../home" className="name  ">
          Kick Land
        </a>
        <ul className="sneakers">
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

        <div className="cart">
          <a href="">
            <img
              src="/Images/NavBarImages/heart.png"
              title="Favourites"
              alt="heart icons"
            />
          </a>
          <a href="">
            <img
              src="/Images/NavBarImages/shopping-bag.png"
              title="Bag Items"
              alt="shopping-bag icons"
            ></img>
          </a>
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
