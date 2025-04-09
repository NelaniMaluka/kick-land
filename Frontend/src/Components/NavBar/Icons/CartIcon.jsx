import { IconButton, Badge as StyledBadge } from "@mui/material";
import ShoppingCartIcon from "@mui/icons-material/ShoppingCart";
import { useAuth } from "../../../Context/AuthContext";

import { useSelector } from "react-redux";

export default function CartIcon() {
  let numberOfCartItems = 0;
  const cart = useSelector((state) => state.cart);
  const { cartC } = useAuth();

  // Calculates cart item count to display
  if (cart?.success && Array.isArray(cart.cart) && cart.cart.length > 0) {
    numberOfCartItems = cart.cart[0]?.id ? cart.cart.length : 0;
  } else if (Array.isArray(cartC) && cartC.length > 0) {
    numberOfCartItems = cartC.reduce((total, item) => total + item.quantity, 0);
  }

  return (
    <IconButton aria-label="cart">
      <StyledBadge badgeContent={numberOfCartItems} color="secondary">
        <ShoppingCartIcon />
      </StyledBadge>
    </IconButton>
  );
}
