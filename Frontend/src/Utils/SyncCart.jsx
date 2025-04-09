// ✅ Make sure this is clean — no useAuth here!
import { addCart } from "../State/Cart/Action";

// ✅ Function receives setCart as a parameter
// src/Utils/SyncCart.js
export const syncGuestCartToUser = async (cartC, jwt, dispatch, setCart) => {
  if (!Array.isArray(cartC) || cartC.length === 0) return;

  for (const item of cartC) {
    const reqData = {
      quantity: item.quantity,
      size: item.size,
      productId: item.productId,
      price: item.price,
    };
    await dispatch(addCart(reqData, jwt));
  }

  setCart([]); // Clear the cart after syncing
};
