import Swal from "sweetalert2";

const showSuccessMessage = (title, message) => {
  Swal.fire({
    icon: "success",
    title: title,
    text: message,
  });
};

export default showSuccessMessage;
