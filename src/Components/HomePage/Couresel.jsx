import React from "react";
import { Carousel } from "primereact/carousel";

function HomeCarousel({ products, category }) {
  console.log(products);
  console.log(category);

  const filteredProducts = products.filter(
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
    console.log(products);
    return (
      <div className="products-card couresel">
        <img src={product.imageUrl} alt="" />
        <h5>{product.name}</h5>
        <span>R {product.price}</span>
      </div>
    );
  };

  return (
    <div className="container2">
      <Carousel
        value={filteredProducts} // You might want to change this based on the category you want to display initially
        numVisible={3}
        numScroll={3}
        responsiveOptions={responsiveOptions}
        itemTemplate={productTemplate}
      />
    </div>
  );
}

export default HomeCarousel;
