import React, { useState, useEffect, useCallback } from "react";
import NavBarTopBanner from "./NavBarTopBanner";
import NavBarMain from "./NavBarMain";
import NavBarBottomBanner from "./NavBarBottomBanner";
import "./NavBar.css"; // Import the CSS for transitions

function NavBar() {
  const [visible, setVisible] = useState(true);
  const [lastScrollY, setLastScrollY] = useState(0);

  const handleScroll = useCallback(() => {
    const currentScrollY = window.scrollY;

    if (currentScrollY > lastScrollY && currentScrollY > 100) {
      // Scrolling down
      setVisible(false);
    } else if (currentScrollY < lastScrollY) {
      // Scrolling up
      setVisible(true);
    }

    setLastScrollY(currentScrollY);
  }, [lastScrollY]);

  useEffect(() => {
    window.addEventListener("scroll", handleScroll);

    return () => {
      window.removeEventListener("scroll", handleScroll);
    };
  }, [handleScroll]);

  return (
    <div className={`navbar ${visible ? "visible" : "hidden"}`}>
      <NavBarTopBanner />
      <NavBarMain />
      <NavBarBottomBanner />
    </div>
  );
}

export default NavBar;
