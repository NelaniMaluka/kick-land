import { useState } from "react";
import ErrorMessageAlert from "../../../Components/Alerts/ErrorMessageAlert";
import { isValidPhoneNumber } from "../../../Utils/FormValidations";
import { isValidEmail } from "../../../Utils/FormValidations";

import "../../../Components/Styling/Form.css";
import { useDispatch } from "react-redux";
import { addOrder } from "../../../State/Order/Action";

export default function Order() {
  const [firstname, setFirstname] = useState("");
  const [lastname, setLastname] = useState("");
  const [email, setEmail] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [province, setProvince] = useState("");
  const [ZIPCode, setZIPCode] = useState("");
  const [address, setAddress] = useState("");
  const [emailError, setEmailError] = useState("");
  const [phoneNumberError, setPhoneNumberError] = useState("");
  const [provinceError, setProvinceError] = useState("");
  const [ZIPCodeError, setZIPCodeError] = useState("");
  const [addressError, setAddressError] = useState("");

  const dispatch = useDispatch();
  const jwt = localStorage.getItem("jwt");

  const orderData = {
    firstname,
    lastname,
    phoneNumber,
    email,
    province,
    ZIPCode,
    address,
  };

  const handleFirstNameChange = (event) => setFirstname(event.target.value);

  const handleLastNameChange = (event) => setLastname(event.target.value);

  const handleEmailChange = (event) => {
    setEmail(event.target.value);
    // Reset email error message
    setEmailError("");
  };

  const handleProvinceChange = (event) => {
    setProvince(event.target.value);
    // Reset email error message
    setProvinceError("");
  };

  const handleZIPCodeChange = (event) => {
    setZIPCode(event.target.value);
    // Reset email error message
    setZIPCodeError("");
  };

  const handleAddressChange = (event) => {
    setAddress(event.target.value);
    // Reset email error message
    setAddressError("");
  };

  const handlePhoneNumberChange = (event) => {
    setPhoneNumber(event.target.value);
    // Reset phone number error message
    setPhoneNumberError("");
  };

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

      // Province validation
      if (!province) {
        setProvinceError("Province is required.");
        return;
      }

      // ZIP Code validation
      if (!ZIPCode) {
        setZIPCodeError("ZIP Code is required.");
        return;
      }

      // address validation
      if (!address) {
        setAddressError("Province is required.");
        return;
      }

      const result = await dispatch(addOrder(orderData, jwt));

      if (result?.status >= 200 && result?.status < 300) {
        // Successfully placed the order
        setFirstname("");
        setLastname("");
        setEmail("");
        setPhoneNumber("");
        setProvinceError("");
        setZIPCodeError("");
        setAddressError("");
        window.location.href = result.data.payment_url; // Redirect to payment page
      } else if (result?.status === 400) {
        // Bad request: Input validation error
        ErrorMessageAlert({
          message: "Invalid input. Please check your details and try again.",
        });
      } else if (result?.status === 401) {
        // Unauthorized: Authentication required
        ErrorMessageAlert({
          message: "Authentication failed. Please log in and try again.",
        });
      } else if (result?.status === 403) {
        // Forbidden: Action not allowed
        ErrorMessageAlert({
          message: "You are not authorized to perform this action.",
        });
      } else if (result?.status === 404) {
        // Resource not found
        ErrorMessageAlert({
          message: "Order service not available. Please try again later.",
        });
      } else if (result?.status >= 500) {
        // Server error
        ErrorMessageAlert({
          message: "A server error occurred. Please try again later.",
        });
      } else {
        // Fallback for unexpected response
        ErrorMessageAlert({
          message: "An unexpected error occurred. Please try again.",
        });
      }
    } catch (error) {
      if (error.response) {
        const { status } = error.response;

        if (status === 400) {
          ErrorMessageAlert({
            message: "Invalid input. Please review your order details.",
          });
        } else if (status === 401) {
          ErrorMessageAlert({
            message: "Authentication error. Please log in and try again.",
          });
        } else if (status >= 500) {
          ErrorMessageAlert({
            message: "A server error occurred. Please try again later.",
          });
        } else {
          ErrorMessageAlert({
            message: `Unexpected error: ${status}. Please try again.`,
          });
        }
      } else if (error.request) {
        // Network or no response error
        ErrorMessageAlert({
          message: "Network error. Please check your connection and try again.",
        });
      } else {
        // Fallback for other unexpected errors
        ErrorMessageAlert({
          message: "An unexpected error occurred. Please try again.",
        });
      }
    }
  }

  return (
    <form className="form container2">
      <div className="form-container message">
        <div>
          <h2> Order Details</h2>
        </div>
        <div className="field">
          <input
            className="messageField"
            placeholder="First Name"
            type="text"
            name="firstName"
            value={firstname}
            autoComplete="given-name"
            onChange={handleFirstNameChange}
          />

          <input
            className="messageField"
            placeholder="Last Name"
            type="text"
            name="lastName"
            value={lastname}
            autoComplete="lastName"
            onChange={handleLastNameChange}
          />
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
        <div className="field">
          <input
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
            placeholder="Address"
            type="text"
            name="address"
            value={address}
            autoComplete="street-address"
            onChange={handleAddressChange}
          />
          {addressError && <div className="error-message">{addressError}</div>}
        </div>
        <div className="field">
          <input
            className="messageField"
            placeholder="Province"
            type="text"
            name="province"
            value={province}
            autoComplete="address-level1"
            onChange={handleProvinceChange}
          />
          {provinceError && (
            <div className="error-message">{provinceError}</div>
          )}
          <input
            className="messageField"
            placeholder="ZIP Code"
            type="text"
            name="ZIPCode"
            value={ZIPCode}
            autoComplete="postal-code"
            onChange={handleZIPCodeChange}
          />
          {ZIPCodeError && <div className="error-message">{ZIPCodeError}</div>}
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
