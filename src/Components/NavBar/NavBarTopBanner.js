import LogText from "../LoginLogout/LogText";

import "./NavBarTopBanner.css";

function NavBarTopBanner() {
  return (
    <div className="options">
      <div className="container2">
        <ul>
          <li>
            <a href="../home">Help</a>
          </li>
          <li>
            <a href="../home">About Us</a>
          </li>
          <li>
            <LogText />
          </li>
        </ul>
      </div>
    </div>
  );
}

export default NavBarTopBanner;
