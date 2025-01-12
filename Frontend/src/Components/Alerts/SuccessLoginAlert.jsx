import Swal from "sweetalert2";

const showSuccessMessage = (title) => {
  Swal.fire({
    position: "top-end",
    icon: "success",
    title: title,
    showConfirmButton: false,
    timer: 1500,
  });
};

export default showSuccessMessage;
