import React from "react";
import { Formik, ErrorMessage } from "formik";
import * as Yup from "yup";
import ErrorMessageAlert from "../../../Components/Alerts/ErrorMessageAlert.jsx";
import showSuccessMessage from "../../../Components/Alerts/SuccessMessageAlert.jsx";

import "./UserProfile.css";
import { updateUserData } from "../../../State/Authentication/Action.js";
import { useDispatch, useSelector } from "react-redux";

export default function UserProfile() {
  const auth = useSelector((state) => state.auth);
  const dispatch = useDispatch();
  const user = auth.user;
  const jwt = localStorage.getItem("jwt");

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
      const updateData = {
        firstname: values.firstname,
        lastname: values.lastname,
        email: values.email,
        phoneNumber: values.phoneNumber,
      };

      const result = await dispatch(updateUserData(updateData, jwt));

      if (result && result.status >= 200 && result.status < 300) {
        // Successful update
        showSuccessMessage("Success!", "Successfully updated your profile");
      } else if (result?.status === 400) {
        // Bad request: Input validation error
        ErrorMessageAlert({
          message: "Please fill out all the fields in the correct format.",
        });
      } else if (result?.status === 403) {
        // Forbidden: Unauthorized action
        ErrorMessageAlert({
          message:
            "You do not have permission to perform this update. Please log in and try again.",
        });
      } else if (result?.status === 404) {
        // Resource not found
        ErrorMessageAlert({
          message: "The requested user profile was not found.",
        });
      } else if (result?.status >= 500) {
        // Internal server error
        ErrorMessageAlert({
          message:
            "An unexpected server error occurred. Please try again later.",
        });
      } else {
        // Fallback for unexpected responses
        ErrorMessageAlert({
          message: "An unexpected error occurred. Please try again.",
        });
      }
    } catch (error) {
      if (error.response) {
        // Handle HTTP response errors based on status
        const { status } = error.response;

        if (status === 400) {
          ErrorMessageAlert({
            message: "Please fill out all the fields in the correct format.",
          });
        } else if (status === 403) {
          ErrorMessageAlert({
            message:
              "You do not have permission to perform this update. Please log in and try again.",
          });
        } else if (status === 404) {
          ErrorMessageAlert({
            message: "The requested user profile was not found.",
          });
        } else if (status >= 500) {
          ErrorMessageAlert({
            message:
              "An unexpected server error occurred. Please try again later.",
          });
        } else {
          ErrorMessageAlert({
            message: `Unexpected error: ${status}. Please try again.`,
          });
        }
      } else if (error.request) {
        // Handle network issues or no server response
        ErrorMessageAlert({
          message: "Network error. Please check your connection and try again.",
        });
      } else {
        // Handle unexpected errors in code logic
        ErrorMessageAlert({
          message: "Unexpected error, please contact support.",
        });
      }
    }
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
