import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import NavBar from "./Components/NavBar/NavBar";
import Footer from "./Components/Footer/Footer";
import HomePage from "./Components/HomePage/HomePage";
import Shop from "./Components/Shop/Shop";
import Info from "./Components/Info/Info";

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <NavBar />
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/Shop/:item" element={<Shop />} />
          <Route path="/Info/:page" element={<Info />} />
        </Routes>
        <Footer />
      </div>
    </BrowserRouter>
  );
}

export default App;
