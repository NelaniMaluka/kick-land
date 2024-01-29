import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../../Security/AuthContext";

function LoginForm() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [ShowErrormessage, setShowErrormessage] = useState(false);

  const navigate = useNavigate();
  const authContext = useAuth();

  function handleUsernameChange(event) {
    setUsername(event.target.value);
  }

  function handlePasswordChange(event) {
    setPassword(event.target.value);
  }

  function handleSubmit() {
    if (authContext.Login(username, password)) {
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
    <form>
      {ShowErrormessage && (
        <div>LogIn failed. Please check Your credentials</div>
      )}
      <div>
        <label>Username:</label>
        <input
          type="text"
          name="username"
          value={username}
          onChange={handleUsernameChange}
        />
      </div>
      <div>
        <label>Password:</label>
        <input
          type="text"
          name="password"
          value={password}
          onChange={handlePasswordChange}
        />
      </div>
      <div>
        <button type="button" name="login" onClick={handleSubmit}>
          LogIn
        </button>
      </div>
    </form>
  );
}

export default LoginForm;
