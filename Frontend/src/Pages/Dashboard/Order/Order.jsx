import { useState } from "react";
import ErrorMessageAlert from "../../../Components/Alerts/ErrorMessageAlert";
import isValidPhoneNumber from "../../../Utils/PhonenumberValidation";
import isValidEmail from "../../../Utils/EmailValidation";

import "../../../Components/Styling/Form.css";
import { useDispatch } from "react-redux";
import { addOrder } from "../../../State/Order/Action";

function Order() {
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

  function handleFirstNameChange(event) {
    setFirstname(event.target.value);
  }

  function handleLastNameChange(event) {
    setLastname(event.target.value);
  }

  function handleEmailChange(event) {
    setEmail(event.target.value);
    // Reset email error message
    setEmailError("");
  }

  function handleProvinceChange(event) {
    setProvince(event.target.value);
    // Reset email error message
    setProvinceError("");
  }

  function handleZIPCodeChange(event) {
    setZIPCode(event.target.value);
    // Reset email error message
    setZIPCodeError("");
  }

  function handleAddressChange(event) {
    setAddress(event.target.value);
    // Reset email error message
    setAddressError("");
  }

  function handlePhoneNumberChange(event) {
    setPhoneNumber(event.target.value);
    // Reset phone number error message
    setPhoneNumberError("");
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
      if (result.result) {
        setFirstname("");
        setLastname("");
        setEmail("");
        setPhoneNumber("");
        setProvinceError("");
        setZIPCodeError("");
        setAddressError("");
        window.location.href = result.response.payment_url;
      } else {
        // API call failed, handle the error
        ErrorMessageAlert({ message: "Invalid Credentials" });
      }
      //ErrorMessageAlert({ message: "Invalid Credentials" });
    } catch (error) {
      ErrorMessageAlert({ message: "Unexpected error please ContactUs" });
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

export default Order;
