import Swal from "sweetalert2";
import "../Styling/Form.css";

const ErrorMessageAlert = (message) => {
  return Swal.fire({
    icon: "error",
    title: "Oops...",
    text: message.message,
    customClass: {
      container: "custom-popup-class",
    },
    didOpen: () => {
      const popupElement = document.querySelector(".swal2-popup");
      if (popupElement) {
        popupElement.style.zIndex = "9999"; // Adjust the z-index as needed
      }
    },
  });
};

export default ErrorMessageAlert;
