import React, { useState, useEffect, useCallback } from "react";
import NavBarTopBanner from "./Layers/NavBarTopBanner";
import NavBarMainBanner from "./Layers/NavBarMainBanner";
import NavBarBottomBanner from "./Layers/NavBarBottomBanner";
import "./NavBar.css"; // Import the CSS for transitions

export default function NavBar() {
  const [visible, setVisible] = useState(true);
  const [lastScrollY, setLastScrollY] = useState(0);

  // Scroll functionality
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
      <NavBarMainBanner />
      <NavBarBottomBanner />
    </div>
  );
}
