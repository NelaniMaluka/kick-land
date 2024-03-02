// ProductCard.jsx
import "./ProductCard.css";
import ShopProductModal from "../Shop/ShopProduct/ShopProductModal";
import { useState } from "react";

function ProductCard({ products }) {
  const [selectedProduct, setSelectedProduct] = useState(null);

  function handleOnClick(product) {
    setSelectedProduct(product);
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
        {selectedProduct && (
          <ShopProductModal
            modalState={true} // or pass a state variable for modalState
            product={selectedProduct}
            onClose={() => setSelectedProduct(null)}
          />
        )}
      </div>
    </div>
  );
}

export default ProductCard;
