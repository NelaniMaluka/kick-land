import LogText from "../LoginLogout/LogText";
import { Link } from "react-router-dom";

import "./NavBarTopBanner.css";

function NavBarTopBanner() {
  return (
    <div className="options">
      <div className="container2">
        <ul>
          <li>
            <Link to="/Info/Help">Help</Link>
          </li>
          <li>
            <Link to="/Info/About-Us">About Us</Link>
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
