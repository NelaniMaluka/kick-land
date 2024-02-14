import { IconButton, Badge as StyledBadge } from "@mui/material";
import ShoppingCartIcon from "@mui/icons-material/ShoppingCart";
import { Link } from "react-router-dom";
import { useAuth } from "../Security/AuthContext";

function Cart() {
  const authContext = useAuth();

  return (
    <Link to="/">
      <IconButton aria-label="cart">
        <StyledBadge badgeContent={authContext.isCartItems} color="secondary">
          <ShoppingCartIcon onclick="" />
        </StyledBadge>
      </IconButton>
    </Link>
  );
}

export default Cart;
