import { IconButton, Badge as StyledBadge } from "@mui/material";
import ShoppingCartIcon from "@mui/icons-material/ShoppingCart";

import { useAuth } from "../../../Context/AuthContext";

function CartIcon() {
  const authContext = useAuth();
  const numberOfCartItems = authContext.isCartItems
    ? authContext.isCartItems.length
    : 0;

  return (
    <IconButton aria-label="cart">
      <StyledBadge badgeContent={numberOfCartItems} color="secondary">
        <ShoppingCartIcon />
      </StyledBadge>
    </IconButton>
  );
}

export default CartIcon;
