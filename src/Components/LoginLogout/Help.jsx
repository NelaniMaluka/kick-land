import { useState } from "react";
import { useAuth } from "../Security/AuthContext";
import Swal from "sweetalert2";

import "./Form.css";

function Help() {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [message, setMessage] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const [ShowErrormessage, setShowErrormessage] = useState(false);

  const authContext = useAuth();

  function handleNameChange(event) {
    setName(event.target.value);
  }

  function handleEmailChange(event) {
    setEmail(event.target.value);
  }

  function handlePhoneNumberChange(event) {
    setPhoneNumber(event.target.value);
  }

  function handleMessageChange(event) {
    setMessage(event.target.value);
  }

  function handleSubmit() {
    authContext
      .contactUs(name, email, phoneNumber, message)
      .then(function (result) {
        if (result.success) {
          // API call was successful, handle the success
          showSuccessMessage(true);
          setShowErrormessage(false);
          setName("");
          setEmail("");
          setPhoneNumber("");
          setMessage("");
          console.log(result);
        } else {
          // API call failed, handle the error
          setShowErrormessage(true);
          setErrorMessage("Invalid Credentials");
        }
      })
      .catch(function (error) {
        // Handle other unexpected errors
        setShowErrormessage(true);
        setErrorMessage("Unexpected error please ContactUs");
      });
  }

  function showSuccessMessage(result) {
    Swal.fire({
      icon: "success",
      title: "Sent",
      text: result.data,
    });
  }

  return (
    <form className="form container2">
      <div className="form-container message">
        <div>
          <h2> Contact Us</h2>
        </div>
        {ShowErrormessage && <div className="error"> {errorMessage}</div>}
        <div className="field">
          <input
            className="messageField"
            placeholder="Name"
            type="name"
            name="name"
            value={name}
            onChange={handleNameChange}
          />
          <input
            className="messageField"
            placeholder="Email"
            type="email"
            name="email"
            value={email}
            onChange={handleEmailChange}
          />
        </div>
        <div className="field">
          <input
            placeholder="Phone Number"
            type="phone"
            name="phoneNumber"
            value={phoneNumber}
            onChange={handlePhoneNumberChange}
          />
        </div>
        <div className="field messagebox">
          <textarea
            className="message"
            placeholder="Message"
            type="message"
            name="message"
            value={message}
            onChange={handleMessageChange}
          ></textarea>
        </div>
        <div>
          <button type="button" name="login" onClick={handleSubmit}>
            Send
          </button>
        </div>
      </div>
    </form>
  );
}
export default Help;
