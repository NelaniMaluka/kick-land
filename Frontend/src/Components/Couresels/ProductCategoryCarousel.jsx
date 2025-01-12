// HomeCarousel.jsx
import React from "react";
import { Carousel } from "primereact/carousel";
import { useAuth } from "../../Context/AuthContext";
import { useNavigate } from "react-router-dom";

export default function ProductCategoryCarousel({ category }) {
  const { isProducts } = useAuth();
  const navigate = useNavigate();

  const filteredProducts = isProducts.filter(
    (product) => product.category === category
  );

  const responsiveOptions = [
    {
      breakpoint: "1024px",
      numVisible: 3,
      numScroll: 1,
    },
    {
      breakpoint: "768px",
      numVisible: 2,
      numScroll: 1,
    },
    {
      breakpoint: "560px",
      numVisible: 1,
      numScroll: 1,
    },
  ];

  const productTemplate = (product) => {
    return (
      <div
        onClick={() => handleOnClick(product)}
        className="products-card-couresel .couresel"
        style={{ margin: "40px 0" }}
        key={product.id}
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

        <span>
          {new Intl.NumberFormat("en-ZA", {
            style: "currency",
            currency: "ZAR",
          }).format(product.price)}
        </span>
      </div>
    );
  };

  const handleOnClick = (product) =>
    setTimeout(() => {
      navigate(`/Shop/${product.category}/${product.name}`);
    }, 0);

  return (
    <div className="container2">
      <Carousel
        value={filteredProducts}
        numVisible={3}
        numScroll={3}
        responsiveOptions={responsiveOptions}
        itemTemplate={productTemplate}
      />
    </div>
  );
}
