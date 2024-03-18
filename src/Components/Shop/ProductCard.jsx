import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./ProductCard.css";

function ProductCard({ products }) {
  const navigate = useNavigate();
  const [selectedProduct, setSelectedProduct] = useState(null);

  function handleOnClick(product) {
    setSelectedProduct(product);
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
              <span
                className="out-of-stock"
                style={{ color: "red", fontSize: "14px" }}
              >
                Out Of Stock
              </span>
            )}
            <h5>{product.name}</h5>
            <span className="category">{product.category}</span>
            <span>
              {new Intl.NumberFormat("en-ZA", {
                style: "currency",
                currency: "ZAR",
              }).format(product.price)}
            </span>
          </div>
        ))}
      </div>
    </div>
  );
}

export default ProductCard;
