import Swal from "sweetalert2";
import "./Form.css";

const LogInAlert = () => {
  const customClass = "custom-popup-class";

  Swal.fire({
    icon: "error",
    title: "Oops...",
    text: "LogIn first",
    customClass: {
      container: customClass,
    },
    onBeforeOpen: () => {
      const popupElement = document.querySelector(`.${customClass}`);
      if (popupElement) {
        popupElement.style.zIndex = "9999"; // Adjust the z-index as needed
      }
    },
  });
};

export default LogInAlert;
