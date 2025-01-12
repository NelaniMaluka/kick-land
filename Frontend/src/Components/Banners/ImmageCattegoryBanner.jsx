import React from "react";
import { Link } from "react-router-dom";
import { Grid } from "@mui/material";

const Item = ({ imageUrl, children }) => {
  const itemStyle = {
    height: "400px",
    width: "100%",
    overflow: "hidden",
  };

  const imgStyle = {
    width: "100%",
    height: "100%",
    objectFit: "cover",
  };

  return (
    <div style={itemStyle}>
      <img src={imageUrl} alt="Item" style={imgStyle} />
      {children}
    </div>
  );
};

export default function ImmageCattegoryBanner() {
  return (
    <div className="container2">
      <Grid container spacing={2}>
        <Grid item xs={12} md={8}>
          <Link to="/Shop/Dunk">
            <Item imageUrl={"/Images/HomeImages/BannerCardimage1.png"}> </Item>
          </Link>
        </Grid>
        <Grid item xs={12} md={4}>
          <Link to="/Shop/AirForce">
            <Item imageUrl={"/Images/HomeImages/BannerCardimage2.png"}> </Item>
          </Link>
        </Grid>
        <Grid item xs={12} md={4}>
          <Link to="/Shop/AirMax">
            <Item imageUrl={"/Images/HomeImages/BannerCardimage3.png"}> </Item>
          </Link>
        </Grid>
        <Grid item xs={12} md={8}>
          <Link to="/Shop/Jordan">
            <Item imageUrl={"/Images/HomeImages/BannerCardimage4.png"}> </Item>
          </Link>
        </Grid>
      </Grid>
    </div>
  );
}
