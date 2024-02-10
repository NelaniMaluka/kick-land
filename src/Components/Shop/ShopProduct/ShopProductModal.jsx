// ShopProductModal.jsx
import { Modal, Box } from "@mui/material";
import { useState } from "react";
import { useEffect } from "react";
import ImageCouresel from "./ImageCouresel";

function ShopProductModal({ modalState, product, onClose }) {
  const [open, setOpen] = useState(false);

  useEffect(() => {
    setOpen(modalState);
  }, [modalState]);

  const handleClose = () => {
    setOpen(false);
    onClose();
  };

  function ChildModal() {
    // Your ChildModal component logic here
    return (
      <div style={buttonContainerStyle}>
        <button style={buttonStyle}>Add to Favourites</button>
        <button style={buttonStyle}>Add to cart</button>
      </div>
    );
  }
  const buttonContainerStyle = {
    margin: "auto",
    width: "max-Content",
  };

  const buttonStyle = {
    width: "200px",
    display: "block",
    padding: "5px",
    margin: "15px",
    borderRadius: "10px",
    border: "none",
  };

  const customStyle = {
    width: "100%",
    maxWidth: "400px",
    backgroundColor: "white",
    top: "50%", // Center vertically
    left: "50%", // Center horizontally
    transform: "translate(-50%, -50%)", // Center the modal both vertically and horizontally
    position: "absolute",
  };

  const imageStyle = {
    width: "100%",
    height: "auto",
    marginBottom: "10px",
  };

  const nameStyle = {
    margin: "0 0 10px 15px",
    fontSize: "30px",
  };

  const priceStyle = {
    margin: "0 0 10px 15px",
    fontSize: "20px",
  };

  return (
    <Modal
      open={open}
      onClose={handleClose}
      aria-labelledby="parent-modal-title"
      aria-describedby="parent-modal-description"
    >
      <Box sx={{ ...customStyle }}>
        <ImageCouresel product={product} style={imageStyle} />
        <h2 id="parent-modal-title" style={nameStyle}>
          {product.name}
        </h2>
        <span id="parent-modal-description" style={priceStyle}>
          R {product.price}
        </span>
        <ChildModal />
      </Box>
    </Modal>
  );
}

export default ShopProductModal;
