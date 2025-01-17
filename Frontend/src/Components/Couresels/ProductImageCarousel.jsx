import { Carousel } from "primereact/carousel";

export default function ProductImageCarousel({ product }) {
  const productImages = [
    { image: product.image1 },
    { image: product.image2 },
    { image: product.image3 },
    { image: product.image4 },
  ];

  const responsiveOptions = [
    {
      numVisible: 1,
      numScroll: 1,
    },
  ];

  const imageStyle = {
    width: "100%",
    height: "auto",
  };

  const ImageTemplate = (productImage) => {
    return (
      <div>
        <img style={imageStyle} src={productImage.image} alt="" />
      </div>
    );
  };

  return (
    <div>
      <Carousel
        value={productImages}
        numVisible={1}
        numScroll={1}
        responsiveOptions={responsiveOptions}
        itemTemplate={ImageTemplate}
      />
    </div>
  );
}
