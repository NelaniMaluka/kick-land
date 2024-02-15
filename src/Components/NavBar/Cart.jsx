import { IconButton, Badge as StyledBadge } from "@mui/material";
import ShoppingCartIcon from "@mui/icons-material/ShoppingCart";

import { useAuth } from "../Security/AuthContext";

function Cart() {
  const authContext = useAuth();

  return (
    <IconButton aria-label="cart">
      <StyledBadge
        badgeContent={authContext.isCartItems.length}
        color="secondary"
      >
        <ShoppingCartIcon onclick="" />
      </StyledBadge>
    </IconButton>
  );
}

export default Cart;
