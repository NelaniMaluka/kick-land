import Swal from "sweetalert2";

export default function showSuccessMessage(title) {
  Swal.fire({
    position: "top-end",
    icon: "success",
    title: title,
    showConfirmButton: false,
    timer: 1500,
  });
}
