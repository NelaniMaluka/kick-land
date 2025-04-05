import { Link } from "react-router-dom";

import "./NavBarTopBanner.css";
import { useDispatch, useSelector } from "react-redux";
import { logoutUser } from "../../../State/Authentication/Action";

export default function NavBarTopBanner() {
  const auth = useSelector((state) => state.auth);
  const dispatch = useDispatch();

  // Log-out Function
  const handleLogout = () => {
    dispatch(logoutUser());
  };

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

          {/* Checks is user is authenticated */}
          {!auth.authenticated && (
            <li>
              <Link to="/Login">Log In</Link>
            </li>
          )}
          {auth.authenticated && (
            <li>
              <Link onClick={handleLogout}>Log Out</Link>
            </li>
          )}
        </ul>
      </div>
    </div>
  );
}
