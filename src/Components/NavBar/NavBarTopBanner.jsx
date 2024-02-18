import { Link } from "react-router-dom";
import { useAuth } from "../Security/AuthContext";
import { useNavigate } from "react-router-dom";

import "./NavBarTopBanner.css";

function NavBarTopBanner() {
  const authContext = useAuth();
  const isAuthenticated = authContext.isAuthenticated;

  const navigate = useNavigate();

  function handleLogout() {
    authContext.logout();
    navigate("/");
  }

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
          {!isAuthenticated && (
            <li>
              <Link to="/Login">Log In</Link>
            </li>
          )}
          {isAuthenticated && (
            <li>
              <Link onClick={handleLogout}>Log Out</Link>
            </li>
          )}
        </ul>
      </div>
    </div>
  );
}

export default NavBarTopBanner;
