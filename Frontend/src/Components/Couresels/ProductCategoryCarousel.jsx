import React, { useEffect, useState } from "react";
import { Carousel } from "primereact/carousel";
import { useAuth } from "../../Context/AuthContext";
import { useNavigate } from "react-router-dom";
import CircularIndeterminate from "../../Utils/LoadingSpinner";
import ErrorMessageAlert from "../Alerts/ErrorMessageAlert";

export default function ProductCategoryCarousel({ category }) {
  const { isProducts } = useAuth();
  const [loading, setLoading] = useState(true);
  const [filteredProducts, setFilteredProducts] = useState([]);
  const [numVisible, setNumVisible] = useState(3);
  const navigate = useNavigate();

  // Update numVisible based on window width
  const handleResize = () => {
    const width = window.innerWidth;
    if (width <= 600) setNumVisible(1);
    else if (width <= 768) setNumVisible(2);
    else setNumVisible(3);
  };

  // Handles resizing
  useEffect(() => {
    handleResize(); // Set initial value
    window.addEventListener("resize", handleResize);
    return () => window.removeEventListener("resize", handleResize);
  }, []);

  // Handles loading screen
  useEffect(() => {
    if (!isProducts || isProducts.length === 0) {
      setLoading(true);
      return;
    }

    try {
      const filtered = isProducts.filter(
        (product) => product.category === category
      );
      setFilteredProducts(filtered);
      setLoading(false);
    } catch (err) {
      ErrorMessageAlert({
        message:
          "We're sorry, something went wrong while loading products. Please try refreshing the page or check back later.",
      });
      setLoading(false);
    }
  }, [isProducts, category]);

  // sets the sizes for the caruosel
  const responsiveOptions = [
    { breakpoint: "1024px", numVisible: 3, numScroll: 1 },
    { breakpoint: "768px", numVisible: 2, numScroll: 1 },
    { breakpoint: "600px", numVisible: 1, numScroll: 1 },
  ];

  // Product view to return
  const productTemplate = (product) => (
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

  // Handles redirect to product details page
  const handleOnClick = (product) =>
    setTimeout(() => {
      navigate(`/Shop/${product.category}/${product.name}`);
    }, 0);

  // Returs the loading screen
  if (loading) return <CircularIndeterminate />;

  // returns the carousel
  return (
    <div className="container2">
      <Carousel
        value={filteredProducts}
        numVisible={numVisible}
        numScroll={1}
        responsiveOptions={responsiveOptions}
        itemTemplate={productTemplate}
      />
    </div>
  );
}
