import React, { useState } from "react";
import { Button, Menu, MenuItem } from "@mui/material";
import "./ShopProductBanner.css";

export default function SortDropdown({ products, setFilteredProducts }) {
  const [anchorEl, setAnchorEl] = useState(null);
  const open = Boolean(anchorEl);

  // Handles open function for the sort button
  const handleOpen = (event) => {
    setAnchorEl(event.currentTarget);
  };

  // Handles close function for the sort button
  const handleClose = () => {
    setAnchorEl(null);
  };

  // Handles sort functionality
  const handleSort = (type) => {
    let sorted = [...products];

    switch (type) {
      case "high-price":
        sorted.sort((a, b) => b.price - a.price);
        break;
      case "low-price":
        sorted.sort((a, b) => a.price - b.price);
        break;
      case "ascending":
        sorted.sort((a, b) => a.name.localeCompare(b.name));
        break;
      case "descending":
        sorted.sort((a, b) => b.name.localeCompare(a.name));
        break;
      default:
        break;
    }

    setFilteredProducts(sorted);
    handleClose(); // close dropdown
  };

  return (
    <div className="container2 shopAll">
      {/* Show sneaker count */}
      <div>
        <p> All Trainers & Shoes ({products.length})</p>
      </div>

      {/* Filter Fuction */}
      <div>
        <Button onClick={handleOpen}>Sort By</Button>

        <Menu anchorEl={anchorEl} open={open} onClose={handleClose}>
          <MenuItem onClick={() => handleSort("high-price")}>
            Price: High-Low
          </MenuItem>
          <MenuItem onClick={() => handleSort("low-price")}>
            Price: Low-High
          </MenuItem>
          <MenuItem onClick={() => handleSort("ascending")}>A-Z</MenuItem>
          <MenuItem onClick={() => handleSort("descending")}>Z-A</MenuItem>
        </Menu>
      </div>
    </div>
  );
}
