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
  console.log(user);

  // Validation schema
  const validationSchema = Yup.object().shape({
    firstname: Yup.string().required("First name is required"),
    lastname: Yup.string().required("Last name is required"),
    email: Yup.string().email("Invalid email").required("Email is required"),
    phoneNumber: Yup.string().matches(
      /^(?:\+?27|0)[ -]?(\d{2})[ -]?(\d{3})[ -]?(\d{4})$/,
      "Invalid phone number"
    ),
  });

  // Handle form submission
  async function handleSubmit(values) {
    try {
      const result = await AuthContext.updateUserDetails(
        user.id,
        values.firstname,
        values.lastname,
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
      Alert({ message: "Unexpected error, please contact support." });
    }
  }

  // Success message function
  function showSuccessMessage(result) {
    Swal.fire({
      icon: "success",
      title: "Success!",
      text: result.data,
    });
  }

  return (
    <div>
      <Formik
        initialValues={{
          firstname: user.firstname || "",
          lastname: user.lastname || "",
          phoneNumber: user.phoneNumber || "",
          email: user.email || "",
          address: user.address || "",
        }}
        onSubmit={handleSubmit}
        validationSchema={validationSchema}
      >
        {({ values, handleChange, handleSubmit, errors, touched }) => (
          <form className="profile" onSubmit={handleSubmit}>
            <div>
              <h2>Personal Information:</h2>
            </div>
            <div className="field-1">
              <input
                className="messageField"
                placeholder="First name*"
                type="text"
                name="firstname"
                value={values.firstname}
                autoComplete="given-name"
                onChange={handleChange}
              />
              <ErrorMessage
                name="firstname"
                component="div"
                className="error-message"
              />
              <input
                className="messageField"
                placeholder="Last name*"
                type="text"
                name="lastname"
                value={values.lastname}
                autoComplete="family-name"
                onChange={handleChange}
              />
              <ErrorMessage
                name="lastname"
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
              <ErrorMessage
                name="phoneNumber"
                component="div"
                className="error-message"
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
              <h2>Address Information:</h2>
            </div>
            <div className="field-1">
              <LocationSearchInput
                initialAddress={values.address}
                onAddressSelect={(address) => setSelectedAddress(address)}
              />
            </div>

            <div>
              <button type="submit" name="saveChanges">
                Save Changes
              </button>
            </div>
          </form>
        )}
      </Formik>
    </div>
  );
}

export default UserProfile;
