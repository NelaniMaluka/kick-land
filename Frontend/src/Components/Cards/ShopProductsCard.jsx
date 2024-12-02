import React from "react";
import { useNavigate } from "react-router-dom";
import "./ShopProductsCard.css";
import { formatCurrency } from "../../Utils/formatCurrency.js";

function ShopProductsCard({ products }) {
  const navigate = useNavigate();

  function handleOnClick(product) {
    // Delay navigation to ensure state is updated
    setTimeout(() => {
      navigate(`/Shop/${product.category}/${product.name}`);
    }, 0);
  }

  return (
    <div className="container2">
      <div className="product">
        {products.map((product) => (
          <div
            className="products-card"
            key={product.id}
            onClick={() => handleOnClick(product)}
          >
            <img src={product.image1} alt="Product" />
            {product.stock === 0 && (
              <span className="out-of-stock">Out Of Stock</span>
            )}
            <h5>{product.name}</h5>
            <span className="category">{product.category}</span>
            <span>{formatCurrency(product.price)}</span>
          </div>
        ))}
      </div>
    </div>
  );
}

export default ShopProductsCard;
