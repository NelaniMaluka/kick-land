import { useState } from "react";
import { useNavigate } from "react-router-dom";
import Api from "../Api/Api";
import { Link } from "react-router-dom";
import Swal from "sweetalert2";

import "./Form.css";

function CreateAccount() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [email, setEmail] = useState("");
  const [surname, setSurname] = useState("");
  const [ShowErrormessage, setShowErrormessage] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");

  const navigate = useNavigate();
  const api = Api();

  function handleUsernameChange(event) {
    setUsername(event.target.value);
  }

  function handlePasswordChange(event) {
    setPassword(event.target.value);
  }
  function handleEmailChange(event) {
    setEmail(event.target.value);
  }

  function handleSurnameChange(event) {
    setSurname(event.target.value);
  }

  function handleSubmit(event) {
    event.preventDefault();
    api
      .CreateAccount(username, surname, email, password)
      .then(function (result) {
        if (result.success) {
          showSuccessMessage(username);
          navigate("/");
        } else {
          setErrorMessage("Please enter all the fields");
          setShowErrormessage(true);
        }
      })
      .catch(function (error) {
        setErrorMessage(
          "We are encountering some problems Sorry for the inconvinience"
        );
        setShowErrormessage(true);
      });
  }

  function showSuccessMessage(username) {
    Swal.fire({
      position: "top-end",
      icon: "success",
      title: "Welcome: " + username,
      showConfirmButton: false,
      timer: 1500,
    });
  }

  return (
    <form className="form container2" onSubmit={handleSubmit}>
      <div className="form-container">
        <div>
          <h2> Create Account </h2>
        </div>
        {ShowErrormessage && (
          <div className="error">
            <p>{errorMessage}</p>
          </div>
        )}
        <div className="field">
          <input
            placeholder="Name"
            type="text"
            name="username"
            value={username}
            onChange={handleUsernameChange}
          />
        </div>
        <div className="field">
          <input
            placeholder="Surname"
            type="text"
            name="surname"
            value={surname}
            onChange={handleSurnameChange}
          />
        </div>
        <div className="field">
          <input
            placeholder="Email"
            type="text"
            name="email"
            value={email}
            onChange={handleEmailChange}
          />
        </div>
        <div className="field">
          <input
            placeholder="Password"
            type="password"
            name="password"
            value={password}
            onChange={handlePasswordChange}
          />
        </div>
        <div>
          <button type="submit" name="login">
            Create
          </button>
        </div>
        <ul>
          <li>
            <Link to="/Login">Login</Link>
          </li>
        </ul>
      </div>
    </form>
  );
}

export default CreateAccount;
