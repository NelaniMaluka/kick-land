import "./NavBarTopBanner.css";
import { useState } from "react";

function NavBarTopBanner() {
  let logIn = (
    <a href="../home" onClick={LogOut}>
      Log In
    </a>
  );
  let logOut = (
    <a href="../home" onClick={LogIn}>
      Log Out
    </a>
  );

  let [log, setlog] = useState(logIn);

  function LogOut(e) {
    e.preventDefault();
    setlog(logOut);
  }

  function LogIn(e) {
    e.preventDefault();
    setlog(logIn);
  }

  return (
    <div class="options">
      <div class="container2">
        <ul>
          <li>
            <a href="../home">Help</a>
          </li>
          <li>
            <a href="../home">About Us</a>
          </li>
          <li>
            <a href="../home">{log}</a>
          </li>
        </ul>
      </div>
    </div>
  );
}

export default NavBarTopBanner;
