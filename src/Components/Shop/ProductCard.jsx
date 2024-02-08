import "./ProductCard.css";

function ProductCard({ products }) {
  return (
    <div className="container2">
      <div className="product ">
        {products.map((product) => (
          <div className="products-card">
            <img src={product.imageUrl} alt="" />
            <h5>{product.name}</h5>
            <span>R {product.price}</span>
          </div>
        ))}
      </div>
    </div>
  );
}

export default ProductCard;
