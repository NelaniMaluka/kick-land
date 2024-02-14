import { useState, useEffect } from "react";
import Dropdown from "react-bootstrap/Dropdown";

import "./ProductBanner.css";

function ProductBanner({ products, setProducts }) {
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

  function sortProducts(value) {
    let sortedProducts;

    switch (value) {
      case "ascending":
        sortedProducts = products.sort((a, b) => a.name.localeCompare(b.name));
        break;
      case "descending":
        sortedProducts = products.sort((a, b) => b.name.localeCompare(a.name));
        break;
      case "high-price":
        sortedProducts = products.sort((a, b) => b.price - a.price);
        break;
      case "low-price":
        sortedProducts = products.sort((a, b) => a.price - b.price);
        break;
      default:
        // Handle other cases or set a default sorting logic
        sortedProducts = products;
    }

    setProducts([...sortedProducts]); // Update state with the new sorted list
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
                <Dropdown.Item
                  href="#/action-1"
                  onClick={() => sortProducts("high-price")}
                >
                  Price: High-Low
                </Dropdown.Item>
                <Dropdown.Item
                  href="#/action-2"
                  onClick={() => sortProducts("low-price")}
                >
                  Price: Low-High
                </Dropdown.Item>
                <Dropdown.Item
                  href="#/action-2"
                  onClick={() => sortProducts("ascending")}
                >
                  Alphabetically: A-Z
                </Dropdown.Item>
                <Dropdown.Item
                  href="#/action-2"
                  onClick={() => sortProducts("descending")}
                >
                  Alphabetically: Z-A
                </Dropdown.Item>
              </Dropdown.Menu>
            </Dropdown>
          </div>
        </nav>
      </div>
    </>
  );
}

export default ProductBanner;
