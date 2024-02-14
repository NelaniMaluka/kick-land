import { Modal, Box } from "@mui/material";
import { useState, useEffect } from "react";
import ImageCouresel from "./ImageCouresel";
import { useAuth } from "../../Security/AuthContext";
import Api from "../../Api/Api";
import LogInAlert from "../../LoginLogout/LogInAlert";
import TransitionsSnackbar from "./TransitionSnackBar";

function ShopProductModal({ modalState, product, onClose }) {
  const [open, setOpen] = useState(false);
  const [showSnackbar, setShowSnackbar] = useState(false); // New state to control Snackbar visibility
  const useContext = useAuth();
  const api = Api();

  useEffect(() => {
    setOpen(modalState);
  }, [modalState]);

  const handleClose = () => {
    setOpen(false);
    onClose();
  };

  function Cart() {
    if (useContext.isAuthenticated) {
      const userId = useContext.isUser.id;
      const productWithUserId = { ...product, userId };
      api.addToCart(productWithUserId);
      setShowSnackbar(true); // Set state to show Snackbar
    } else {
      handleClose();
      LogInAlert();
    }
  }

  function ChildModal() {
    return (
      <div style={buttonContainerStyle}>
        <button onClick={Cart} style={buttonStyle}>
          Add to cart
        </button>
        {showSnackbar && <TransitionsSnackbar />}{" "}
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
    zIndex: 10000,
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
        <ImageCouresel product={product} />
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
