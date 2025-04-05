import * as React from "react";
import IconButton from "@mui/material/IconButton";
import AccountCircle from "@mui/icons-material/AccountCircle";
import MenuItem from "@mui/material/MenuItem";
import Menu from "@mui/material/Menu";
import { useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { logoutUser } from "../../../State/Authentication/Action";
import ErrorMessageAlert from "../../Alerts/ErrorMessageAlert";
import { Link } from "react-router-dom";

export default function ProfileIcon() {
  const [anchorEl, setAnchorEl] = React.useState(null);
  const auth = useSelector((state) => state.auth);
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const handleMenu = (event) => setAnchorEl(event.currentTarget);
  const handleClose = () => setAnchorEl(null);

  // Log-out function
  const handleLogout = () => {
    dispatch(logoutUser());
    handleClose();
    navigate("/");
  };

  // Profile function
  const handleProfileClick = () => {
    handleClose();

    if (auth.authenticated) {
      navigate("/Dashboard");
    } else {
      ErrorMessageAlert({
        message: "Please log in first to access your profile.",
      });
    }
  };

  return (
    <div>
      {/* Icon container */}
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
        {/* Profile Icon */}
        <MenuItem onClick={handleProfileClick}>
          <span
            style={{ float: "left", marginRight: "5px" }}
            className="material-symbols-outlined"
          >
            manage_accounts
          </span>
          <span style={{ fontFamily: "arial" }}>Profile</span>
        </MenuItem>

        {/* Cart Icon */}
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
