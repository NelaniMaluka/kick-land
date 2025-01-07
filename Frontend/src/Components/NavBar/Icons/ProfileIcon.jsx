import * as React from "react";
import IconButton from "@mui/material/IconButton";
import AccountCircle from "@mui/icons-material/AccountCircle";
import MenuItem from "@mui/material/MenuItem";
import Menu from "@mui/material/Menu";
import { useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { logoutUser } from "../../../State/Authentication/Action";

export default function ProfileIcon() {
  const [anchorEl, setAnchorEl] = React.useState(null);
  const auth = useSelector((state) => state.auth);
  const dispatch = useDispatch();
  const navigate = useNavigate();

  function handleLogout() {
    dispatch(logoutUser());
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
          <Link
            to="/Dashboard"
            style={{ fontFamily: "arial", textDecoration: "none" }}
          >
            <span
              style={{ float: "left", marginRight: "5px" }}
              className="material-symbols-outlined"
            >
              manage_accounts
            </span>
            Profile
          </Link>
        </MenuItem>
        {!auth.authenticated ? (
          <MenuItem onClick={handleClose}>
            <Link
              to="/Login"
              style={{ fontFamily: "arial", textDecoration: "none" }}
            >
              <span
                style={{ float: "left", marginRight: "5px" }}
                className="material-symbols-outlined"
              >
                login
              </span>
              Log In
            </Link>
          </MenuItem>
        ) : (
          <MenuItem onClick={handleLogout}>
            <span
              style={{ float: "left", marginRight: "5px" }}
              className="material-symbols-outlined"
            >
              logout
            </span>
            Log Out
          </MenuItem>
        )}
      </Menu>
    </div>
  );
}
