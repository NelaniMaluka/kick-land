import { useState } from "react";
import { useAuth } from "../../Security/AuthContext.js";
import Swal from "sweetalert2";
import LocationInput from "./LocationInput.tsx";

import "./Profile.css";

function UserProfile() {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [message, setMessage] = useState("");
  const [errormessage, setErrormessage] = useState("");
  const [ShowErrormessage, setShowErrormessage] = useState(false);

  const AuthContext = useAuth();

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
    AuthContext.ContactUs(name, email, phoneNumber, message)
      .then(function (result) {
        if (result.success) {
          // API call was successful, handle the success
          showSuccessMessage(true);
          setShowErrormessage(false);
          setName("");
          setEmail("");
          setPhoneNumber("");
          setMessage("");
        } else {
          // API call failed, handle the error
          setShowErrormessage(true);
          console.error("ContactUs failed:", result.error);
          setErrormessage(
            "Please fill out all the fields in the correct format"
          );
        }
      })
      .catch(function (error) {
        // Handle other unexpected errors
        setShowErrormessage(true);
        console.error("Unexpected error in ContactUs:", error);
        setErrormessage("Unexpected error please ContactUs");
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
    <form className=" profile">
      <div>
        <h2> Personal Information:</h2>
      </div>
      {ShowErrormessage && <div className="error">{errormessage}</div>}
      <div className="field-1">
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
      <div className="field-1">
        <input
          className="messageField"
          placeholder="Phone Number"
          type="phone"
          name="phoneNumber"
          value={phoneNumber}
          onChange={handlePhoneNumberChange}
        />
        <input
          className="messageField"
          placeholder="Phone Number"
          type="phone"
          name="phoneNumber"
          value={phoneNumber}
          onChange={handlePhoneNumberChange}
        />
      </div>
      <div>
        <h2> Address Information:</h2>
      </div>
      <div>
        <LocationInput />
      </div>
      <div>
        <button type="button" name="login" onClick={handleSubmit}>
          Save Changes
        </button>
      </div>
    </form>
  );
}
export default UserProfile;
