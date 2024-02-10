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
            <img src={product.image1} alt="" />
            <h5>{product.name}</h5>
            <span>R {product.price}</span>
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
