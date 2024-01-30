import React from "react";
import "./ProductsView.css";

const products = {
  Dunk: [
    {
      id: 1,
      name: "Nike Dunk Low Retro Sneakers",
      price: "R " + 1999,
      image: "/Images/ProductImages/Nike Dunk Low Retro Sneakers.png",
    },
    {
      id: 2,
      name: "Nike Dunk Low SE 85 Double Swoosh Sail Orange",
      price: "R " + 5400,
      image:
        "/Images/ProductImages/Nike Dunk Low SE 85 Double Swoosh Sail Orange.png",
    },
    {
      id: 3,
      name: "Nike Travis Scott X SB Dunk Low PRM QS 'Cactus Jack'",
      price: "R " + 2500,
      image:
        "/Images/ProductImages/Nike Travis Scott X SB Dunk Low PRM QS 'Cactus Jack'.png",
    },
    {
      id: 4,
      name: "Nike Dunk Low Clear Aura",
      price: "R " + 4000,
      image: "/Images/ProductImages/Nike Dunk Low Clear Aura.png",
    },
  ],
  Jordan: [
    {
      id: 1,
      name: "Nike Paris Saint-Germain X Air Jordan 4 Retro 'Bordeaux'",
      price: "R " + 1000,
      image:
        "/Images/ProductImages/Nike Paris Saint-Germain X Air Jordan 4 Retro 'Bordeaux'.png",
    },
    {
      id: 2,
      name: "Nike Air Jordan X Zion Willamson Low OG ''Voodoo'' Sneakers",
      price: "R " + 2000,
      image:
        "/Images/ProductImages/Nike Air Jordan X Zion Willamson Low OG ''Voodoo'' Sneakers.png",
    },
    {
      id: 3,
      name: "Nike Air Jordan 4 SB ''Pine Green'' Sneakers",
      price: "R " + 3000,
      image:
        "/Images/ProductImages/Nike Air Jordan 4 SB ''Pine Green'' Sneakers.png",
    },
    {
      id: 4,
      name: "Nike Air Jordan 1 'Travis Scott' Low",
      price: "R " + 4000,
      image: "/Images/ProductImages/Nike Air Jordan 1 'Travis Scott' Low.png",
    },
  ],
  AirForce: [
    {
      id: 1,
      name: "Nike Air Force '07 Low-Top' Sneakers",
      price: "R " + 1899,
      image: "/Images/ProductImages/Nike Air Force '07 Low-Top' Sneakers.png",
    },
    {
      id: 2,
      name: "Nike Air Force 1 Low ''Panda'' Sneakers",
      price: "R " + 2299,
      image:
        "/Images/ProductImages/Nike Air Force 1 Low ''Panda'' Sneakers.png",
    },
    {
      id: 3,
      name: "Nike Air Force 1 '07 Low VD Sneakers",
      price: "R " + 4989,
      image: "/Images/ProductImages/Nike Air Force 1 '07 Low VD Sneakers.png",
    },
    {
      id: 4,
      name: "Nike Air Force 1 Low 'ST PATRICK'S DAY' Sneakers",
      price: "R " + 4000,
      image:
        "/Images/ProductImages/Nike Air Force 1 Low 'ST PATRICK'S DAY' Sneakers.png",
    },
  ],
  AirMax: [
    {
      id: 1,
      name: "Nike Air Max Plus",
      price: "R " + 3599,
      image: "/Images/ProductImages/Nike Air Max Plus.png",
    },
    {
      id: 2,
      name: "Nike Air Max Pulse",
      price: "R " + 3199,
      image: "/Images/ProductImages/Nike Air Max Pulse.png",
    },
    {
      id: 3,
      name: "Nike Air Max 97",
      price: "R " + 3599,
      image: "/Images/ProductImages/Nike Air Max 97.png",
    },
    {
      id: 4,
      name: "Nike Air Max 1",
      price: "R " + 4000,
      image: "/Images/ProductImages/Nike Air Max 1.png",
    },
  ],
};

function ProductsView({ item }) {
  return (
    <div className="products">
      <div className="container2">
        {products[item].map((product) => (
          <div key={product.id} className="product">
            <img className="productImg" src={product.image} alt="" />
            <h4 className="productName">{product.name}</h4>
            <p className="productPrice">{product.price}</p>
          </div>
        ))}
      </div>
    </div>
  );
}

export default ProductsView;
