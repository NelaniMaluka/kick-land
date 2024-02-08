CREATE TABLE IF NOT EXISTS PRODUCTS (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(255) NOT NULL,
    PRICE DECIMAL(10,2) NOT NULL,
    CATEGORY VARCHAR(255) NOT NULL,
    IMAGE_URL VARCHAR(255) NOT NULL
);

INSERT INTO PRODUCTS (CATEGORY, IMAGE_URL, NAME, PRICE)
VALUES
  -- Dunk
  ('Dunk', '/Images/ProductImages/Nike Dunk Low.png', 'Nike Dunk Low', 2099.95),
  ('Dunk', '/Images/ProductImages/Nike Dunk Low Retro.png', 'Nike Dunk Low Retro', 2099.95),
  ('Dunk', '/Images/ProductImages/Nike SB Dunk Low AA.png', 'Nike SB Dunk Low AA', 299.95),
  ('Dunk', '/Images/ProductImages/Nike Dunk High Retro.png', 'Nike Dunk High Retro', 2399.95),

  -- Jordan
  ('Jordan', '/Images/ProductImages/Air Jordan 1 Low.png', 'Air Jordan 1 Low', 1000),
  ('Jordan', '/Images/ProductImages/Air Jordan 3 Retro.png', 'Air Jordan 3 Retro', 2000),
  ('Jordan', '/Images/ProductImages/Air Jordan 1 Mid SE.png', 'Air Jordan 1 Mid SE', 3000),
  ('Jordan', '/Images/ProductImages/Air Jordan Retro 13.png', 'Air Jordan Retro 13', 4000),

  -- AirForce
  ('AirForce', '/Images/ProductImages/Nike Air Force 1 07.png', 'Nike Air Force 1 07', 1899.95),
  ('AirForce', '/Images/ProductImages/Nike Air Force 1 LV8.png', 'Nike Air Force 1 LV8', 1899.95),
  ('AirForce', '/Images/ProductImages/Nike Air Max 1 07 LX.png', 'Nike Air Max 1 07 LX', 2599.95),
  ('AirForce', '/Images/ProductImages/Nike Air Max 1 07 WB.png', 'Nike Air Max 1 07 WB', 4000),

  -- AirMax
  ('AirMax', '/Images/ProductImages/Nike Air Max Plus.png', 'Nike Air Max Plus', 3599.99),
  ('AirMax', '/Images/ProductImages/Nike Air Max Pulse.png', 'Nike Air Max Pulse', 3599.95),
  ('AirMax', '/Images/ProductImages/Nike Air Max 97.png', 'Nike Air Max 97', 2599.97),
  ('AirMax', '/Images/ProductImages/Nike Air Max 1.png', 'Nike Air Max 1', 2799.95);
