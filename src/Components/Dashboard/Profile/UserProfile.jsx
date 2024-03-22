import React, { useState } from "react";
import { useAuth } from "../../Security/AuthContext.js";
import Swal from "sweetalert2";
import { Formik, ErrorMessage } from "formik";
import * as Yup from "yup";
import LocationSearchInput from "./LocationSearchInput.jsx";
import Alert from "../../LoginLogout/Alert.jsx";

import "./Profile.css";

function UserProfile() {
  const [selectedAddress, setSelectedAddress] = useState("");

  const AuthContext = useAuth();
  const user = AuthContext.isUser;

  const validationSchema = Yup.object().shape({
    name: Yup.string().required("Name is required"),
    email: Yup.string().email("Invalid email").required("Email is required"),
    phoneNumber: Yup.string().matches(
      /^(?:\+?27|0)[ -]?(\d{2})[ -]?(\d{3})[ -]?(\d{4})$/,
      "Invalid phone number"
    ),
  });

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
      } else {
        Alert({
          message: "Please fill out all the fields in the correct format",
        });
      }
    } catch (error) {
      Alert({ message: "Unexpected error please ContactUs" });
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
      validationSchema={validationSchema}
    >
      {({ values, handleChange, handleSubmit, errors, touched }) => (
        <form className="profile">
          <div>
            <h2> Personal Information:</h2>
          </div>
          <div className="field-1">
            <input
              className="messageField"
              placeholder="Name*"
              type="text"
              name="name"
              value={values.name}
              onChange={handleChange}
            />
            <ErrorMessage
              name="name"
              component="div"
              className="error-message"
            />
            <input
              className="messageField"
              placeholder="Surname*"
              type="text"
              name="surname"
              value={values.surname}
              onChange={handleChange}
            />
            <ErrorMessage
              name="surname"
              component="div"
              className="error-message"
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
            <ErrorMessage
              name="email"
              component="div"
              className="error-message"
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
