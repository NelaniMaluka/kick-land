import { useState } from "react";
import ErrorMessageAlert from "../../Components/Alerts/ErrorMessageAlert";
import isValidPhoneNumber from "../../Utils/PhonenumberValidation";
import isValidEmail from "../../Utils/EmailValidation";
import showSuccessMessage from "../../Components/Alerts/SuccessMessageAlert";

import "../../Components/Styling/Form.css";
import { contactUs } from "../../Context/Api";

function ContactUs() {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [message, setMessage] = useState("");
  const [emailError, setEmailError] = useState("");
  const [phoneNumberError, setPhoneNumberError] = useState("");
  const [messageError, setMessageError] = useState("");

  function handleNameChange(event) {
    setName(event.target.value);
  }

  function handleEmailChange(event) {
    setEmail(event.target.value);
    // Reset email error message
    setEmailError("");
  }

  function handlePhoneNumberChange(event) {
    setPhoneNumber(event.target.value);
    // Reset phone number error message
    setPhoneNumberError("");
  }

  function handleMessageChange(event) {
    setMessage(event.target.value);
    // Reset message error message
    setMessageError("");
  }

  async function handleSubmit() {
    try {
      // Email validation
      if (!email) {
        setEmailError("Email is required.");
      } else if (!isValidEmail(email)) {
        setPhoneNumberError("Invalid email format.");
        return;
      }

      // Phone number validation
      if (!phoneNumber) {
        setPhoneNumberError("Phone number is required.");
        return;
      } else if (!isValidPhoneNumber(phoneNumber)) {
        setPhoneNumberError("Invalid phone number format.");
        return;
      }

      // Message validation
      if (!message) {
        setMessageError("Message is required.");
        return;
      }

      const result = await contactUs(name, email, phoneNumber, message);
      console.log(result);
      if (result.status === 200) {
        // API call was successful, handle the success
        showSuccessMessage("Sent", "we recieved your message");
        setName("");
        setEmail("");
        setPhoneNumber("");
        setMessage("");
      } else {
        // API call failed, handle the error
        ErrorMessageAlert({ message: "Invalid Credentials" });
      }
    } catch (error) {
      // Handle other unexpected errors
      ErrorMessageAlert({ message: "Unexpected error please ContactUs" });
    }
  }

  return (
    <form className="form container2">
      <div className="form-container message">
        <div>
          <h2> Contact Us</h2>
        </div>
        <div className="field">
          <input
            className="messageField"
            placeholder="Name"
            type="text"
            name="name"
            value={name}
            autoComplete="given-name"
            onChange={handleNameChange}
          />

          <input
            className="messageField"
            placeholder="Email"
            type="email"
            name="email"
            value={email}
            autoComplete="email"
            onChange={handleEmailChange}
          />
          {emailError && <div className="error-message">{emailError}</div>}
        </div>
        <div className="field">
          <input
            placeholder="Phone Number"
            type="tel"
            name="phoneNumber"
            value={phoneNumber}
            onChange={handlePhoneNumberChange}
          />
          {phoneNumberError && (
            <div className="error-message">{phoneNumberError}</div>
          )}
        </div>
        <div className="field messagebox">
          <textarea
            className="message"
            placeholder="Message"
            type="text"
            name="message"
            value={message}
            onChange={handleMessageChange}
          ></textarea>
          {messageError && <div className="error-message">{messageError}</div>}
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

export default ContactUs;
