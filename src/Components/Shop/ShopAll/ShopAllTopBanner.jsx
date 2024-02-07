import { useState, useEffect } from "react";
import Dropdown from "react-bootstrap/Dropdown";
import DropdownButton from "react-bootstrap/DropdownButton";

// ... (previous imports)

import "./ShopAllTopBanner.css";

function ShopAllTopBanner({ products }) {
  const [shoeCount, setShoeCount] = useState(null);

  useEffect(() => {
    getShoeCount();
  }, []);

  function getShoeCount() {
    let count = 0;
    for (let i = 0; i < products.length; i++) {
      count++;
    }
    setShoeCount("(" + count + ")");
  }

  return (
    <>
      <div className="container2 shopAll">
        <nav>
          <div>
            <p>All Trainers & Shoes {shoeCount}</p>
          </div>
          <div>
            <Dropdown>
              <Dropdown.Toggle variant="basic" id="dropdown-basic">
                Sort By
              </Dropdown.Toggle>

              <Dropdown.Menu>
                <Dropdown.Item href="#/action-1">Price: High-Low</Dropdown.Item>
                <Dropdown.Item href="#/action-2">Price: Low-High</Dropdown.Item>
                <Dropdown.Item href="#/action-3">Something else</Dropdown.Item>
              </Dropdown.Menu>
            </Dropdown>
          </div>
        </nav>
      </div>
    </>
  );
}

export default ShopAllTopBanner;
