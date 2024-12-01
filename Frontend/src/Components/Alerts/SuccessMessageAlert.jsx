import Swal from "sweetalert2";

export default function showSuccessMessage(title, message) {
  Swal.fire({
    icon: "success",
    title: title,
    text: message,
  });
}
