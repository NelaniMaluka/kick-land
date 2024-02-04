import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../../Security/AuthContext";
import { Link } from "react-router-dom";
import Swal from "sweetalert2";

import "./Form.css";

function CreateAccount() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [email, setEmail] = useState("");
  const [surname, setSurname] = useState("");
  const [ShowErrormessage, setShowErrormessage] = useState(false);
  const [message, setMessage] = useState("");

  const navigate = useNavigate();
  const authContext = useAuth();

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

  function handleSubmit() {
    authContext
      .CreateAccount(username, surname, email, password)
      .then(function (result) {
        showSuccessMessage(username);
        navigate("/");
      })
      .catch(function (error) {
        setMessage(error.response.data.message);
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
    <form className="form container2">
      <div className="form-container">
        <div>
          <h2> Create Account </h2>
        </div>
        {ShowErrormessage && (
          <div className="error">
            <p>{message}</p>
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
          <button type="button" name="login" onClick={handleSubmit}>
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
