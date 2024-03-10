// HomeCarousel.jsx
import React, { useEffect, useState } from "react";
import { Carousel } from "primereact/carousel";
import { useAuth } from "../Security/AuthContext";
import { useNavigate } from "react-router-dom";

function HomeCarousel({ category }) {
  const { isProducts, setProducts } = useAuth();
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch("your_backend_api/products");
        const data = await response.json();
        setProducts(data);
      } catch (error) {
        console.error("Error fetching data:", error);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, [setProducts]);

  if (loading) {
    return <div>Loading...</div>;
  }

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
        onClick={() => HandleOnClick(product)}
        className="products-card couresel"
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

  function HandleOnClick(product) {
    navigate(`/Shop/Sneakers/${product.name}`);
  }

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

export default HomeCarousel;
