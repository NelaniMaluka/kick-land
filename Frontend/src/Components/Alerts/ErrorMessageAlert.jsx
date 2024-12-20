import Swal from "sweetalert2";
import "../Styling/Form.css";

const ErrorMessageAlert = ({ message }) => {
  const customClass = "custom-popup-class";

  Swal.fire({
    icon: "error",
    title: "Oops...",
    text: message,
    customClass: {
      container: customClass,
    },
    didOpen: () => {
      const popupElement = document.querySelector(
        `.${customClass} .swal2-popup`
      );
      if (popupElement) {
        popupElement.style.zIndex = "9999"; // Adjust the z-index as needed
      }
    },
  });
};

export default ErrorMessageAlert;
