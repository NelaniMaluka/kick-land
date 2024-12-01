import * as React from "react";
import IconButton from "@mui/material/IconButton";
import AccountCircle from "@mui/icons-material/AccountCircle";
import MenuItem from "@mui/material/MenuItem";
import Menu from "@mui/material/Menu";
import { useAuth } from "../../../Context/AuthContext";
import { useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";

export default function ProfileIcon() {
  const [anchorEl, setAnchorEl] = React.useState(null);
  const authContext = useAuth();
  const isAuthenticated = authContext.isAuthenticated;

  const navigate = useNavigate();

  function handleLogout() {
    authContext.logout();
    navigate("/");
  }

  const handleMenu = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  return (
    <div>
      <IconButton
        size="large"
        aria-label="account of current user"
        aria-controls="menu-appbar"
        aria-haspopup="true"
        onClick={handleMenu}
        color="inherit"
      >
        <AccountCircle />
      </IconButton>
      <Menu
        id="menu-appbar"
        anchorEl={anchorEl}
        anchorOrigin={{
          vertical: "top",
          horizontal: "right",
        }}
        keepMounted
        transformOrigin={{
          vertical: "top",
          horizontal: "right",
        }}
        open={Boolean(anchorEl)}
        onClose={handleClose}
      >
        <MenuItem onClick={handleClose}>
          <Link to="/Dashboard" style={{ fontFamily: "arial" }}>
            <span
              style={{ float: "left", marginRight: "5px" }}
              className="material-symbols-outlined"
            >
              manage_accounts
            </span>
            Profile
          </Link>
        </MenuItem>
        <MenuItem onClick={handleClose}>
          {!isAuthenticated && (
            <li>
              <Link to="/Login" style={{ fontFamily: "arial" }}>
                <span
                  style={{ float: "left", marginRight: "5px" }}
                  className="material-symbols-outlined"
                >
                  login
                </span>
                Log In
              </Link>
            </li>
          )}
          {isAuthenticated && (
            <li>
              <Link onClick={handleLogout} style={{ fontFamily: "arial" }}>
                <span
                  style={{ float: "left", marginRight: "5px" }}
                  className="material-symbols-outlined"
                >
                  logout
                </span>
                Log Out
              </Link>
            </li>
          )}
        </MenuItem>
      </Menu>
    </div>
  );
}
