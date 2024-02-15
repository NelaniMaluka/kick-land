import * as React from "react";
import FormatBoldIcon from "@mui/icons-material/FormatBold";
import FormatItalicIcon from "@mui/icons-material/FormatItalic";
import Box from "@mui/material/Box";
import Divider from "@mui/material/Divider";
import { useAuth } from "../../Security/AuthContext";

import "./CartView.css";

function CartView() {
  const useContext = useAuth();
  const products = useContext.isCartItems || [];
  console.log(useContext.isCartItems);

  return (
    <div>
      {products.map((product) => (
        <div className="cart-card">
          <img src={product.image1} alt="Product Image" />
          <Divider orientation="vertical" variant="middle" flexItem />
          <span>{product.name}</span>
        </div>
      ))}
    </div>
  );
}

export default CartView;
