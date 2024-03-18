import React, { useState, useEffect } from "react";
import { useAuth } from "../../Security/AuthContext.js";
import Swal from "sweetalert2";
import { Formik } from "formik";
import LocationSearchInput from "./LocationSearchInput.jsx";
import "./Profile.css";

function UserProfile() {
  const [errormessage, setErrormessage] = useState("");
  const [ShowErrormessage, setShowErrormessage] = useState(false);
  const [selectedAddress, setSelectedAddress] = useState("");

  const AuthContext = useAuth();
  const user = AuthContext.isUser;

  async function handleSubmit(values) {
    try {
      const result = await AuthContext.updateUserDetails(
        user.id,
        values.name,
        values.surname,
        values.email,
        values.phoneNumber,
        selectedAddress // Use the selected address
      );
      if (result.success) {
        showSuccessMessage(result);
        setShowErrormessage(false);
      } else {
        setShowErrormessage(true);
        setErrormessage("Please fill out all the fields in the correct format");
      }
    } catch (error) {
      setShowErrormessage(true);
      setErrormessage("Unexpected error please ContactUs");
    }
  }

  function showSuccessMessage(result) {
    Swal.fire({
      icon: "success",
      title: "Sent",
      text: result.data,
    });
  }

  return (
    <Formik
      initialValues={{
        name: user.username || "",
        surname: user.surname || "",
        phoneNumber: user.phoneNumber || "",
        email: user.email || "",
        address: user.address || "",
      }}
      onSubmit={handleSubmit}
    >
      {({ values, handleChange, handleSubmit }) => (
        <form className="profile">
          <div>
            <h2> Personal Information:</h2>
          </div>
          {ShowErrormessage && <div className="error">{errormessage}</div>}
          <div className="field-1">
            <input
              className="messageField"
              placeholder="Name*"
              type="text"
              name="name"
              value={values.name}
              onChange={handleChange}
            />
            <input
              className="messageField"
              placeholder="Surname*"
              type="text"
              name="surname"
              value={values.surname}
              onChange={handleChange}
            />
          </div>
          <div className="field-1">
            <input
              className="messageField"
              placeholder="Phone Number"
              type="tel"
              name="phoneNumber"
              value={values.phoneNumber}
              onChange={handleChange}
            />
            <input
              className="messageField"
              placeholder="Email*"
              type="email"
              name="email"
              value={values.email}
              onChange={handleChange}
            />
          </div>
          <div>
            <h2> Address Information:</h2>
          </div>
          <div className="field-1">
            <LocationSearchInput
              initialAddress={values.address}
              onAddressSelect={(address) => setSelectedAddress(address)}
            />
          </div>
          <div>
            <button type="button" name="login" onClick={handleSubmit}>
              Save Changes
            </button>
          </div>
        </form>
      )}
    </Formik>
  );
}

export default UserProfile;
