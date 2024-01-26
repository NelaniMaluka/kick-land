import React, { useState, useEffect } from "react";

function LogText() {
  const [isLogged, setIsLogged] = useState(false);

  const userLogged = () => {
    if (isLogged) {
      return <a href="../home">Log Out</a>;
    } else {
      return <a href="../home">Log In</a>;
    }
  };

  const [loged, setLoged] = useState(userLogged);

  useEffect(() => {
    setLoged(userLogged());
  }, [isLogged]);

  const handleToggle = (e) => {
    e.preventDefault(); // Prevent the default behavior of the anchor tag
    setIsLogged((prevIsLogged) => !prevIsLogged);
  };

  return (
    <a href="../home" onClick={handleToggle}>
      {loged}
    </a>
  );
}

export default LogText;
