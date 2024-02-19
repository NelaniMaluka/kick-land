import React, { useState, useEffect } from "react";
import { useAuth } from "../../Security/AuthContext.js";
import Swal from "sweetalert2";
import { Formik } from "formik";
import LocationInput from "./LocationInput.tsx";
import "./Profile.css";

function UserProfile() {
  const [errormessage, setErrormessage] = useState("");
  const [ShowErrormessage, setShowErrormessage] = useState(false);

  const AuthContext = useAuth();
  const user = AuthContext.isUser;
  console.log(user);

  useEffect(() => {
    // No need to set initial form values using useEffect
  }, [user]);

  function handleSubmit(values) {
    AuthContext.updateUserDetails(
      user.id,
      values.name,
      values.surname,
      values.email,
      values.phoneNumber
    )
      .then(function (result) {
        if (result.success) {
          showSuccessMessage(result);
          setShowErrormessage(false);
        } else {
          setShowErrormessage(true);
          console.error("ContactUs failed:", result.error);
          setErrormessage(
            "Please fill out all the fields in the correct format"
          );
        }
      })
      .catch(function (error) {
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
    <Formik
      initialValues={{
        name: user.username || "",
        surname: user.surname || "",
        phoneNumber: user.phoneNumber || "",
        email: user.email || "",
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
              placeholder="Name"
              type="name"
              name="name"
              value={values.name}
              onChange={handleChange}
            />
            <input
              className="messageField"
              placeholder="Surname"
              type="Surname"
              name="surname"
              value={values.surname}
              onChange={handleChange}
            />
          </div>
          <div className="field-1">
            <input
              className="messageField"
              placeholder="Phone Number"
              type="phone"
              name="phoneNumber"
              value={values.phoneNumber}
              onChange={handleChange}
            />
            <input
              className="messageField"
              placeholder="Email"
              type="email"
              name="email"
              value={values.email}
              onChange={handleChange}
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
      )}
    </Formik>
  );
}

export default UserProfile;
