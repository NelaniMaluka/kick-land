import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../../Security/AuthContext";
import { Link } from "react-router-dom";

import "./LogInForm.css";

function CreateAccount() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [email, setEmail] = useState("");
  const [surname, setSurname] = useState("");
  const [ShowErrormessage, setShowErrormessage] = useState(false);

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
    if (authContext.CreateAccount(username, password, email, surname)) {
      console.log(username);
      console.log(password);
      navigate("/");
    } else {
      console.log(username);
      console.log(password);
      setShowErrormessage(true);
    }
  }

  return (
    <form className="login-form container2">
      <div className="form-container">
        <div>
          <h2> Create Account </h2>
        </div>
        {ShowErrormessage && (
          <div className="error">
            <p>Email and Password Can't be Blank</p>
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
            type="text"
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
