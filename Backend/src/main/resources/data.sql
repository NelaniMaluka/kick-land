CREATE TABLE IF NOT EXISTS products (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    product_price DECIMAL(10,2) NOT NULL,
    product_category VARCHAR(255) NOT NULL,
    image1 VARCHAR(255) NOT NULL,
    image2 VARCHAR(255),
    image3 VARCHAR(255),
    image4 VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS product_stock (
    stock_ID INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT NOT NULL,
    size3 INT NOT NULL,
    size4 INT NOT NULL,
    size5 INT NOT NULL,
    size6 INT NOT NULL,
    size7 INT NOT NULL,
    size8 INT NOT NULL,
    size9 INT NOT NULL,
    size10 INT NOT NULL,
    size11 INT NOT NULL,
    size12 INT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES products(product_id) ON DELETE CASCADE
);

-- Dunk
INSERT INTO products (product_category, product_name, product_price, image1, image2, image3, image4)
VALUES
  ('Dunk', 'Nike Dunk Low', 2099.95, '/Images/ProductImages/Nike Dunk Low.png', '/Images/ProductImages/Nike Dunk Low2.png', '/Images/ProductImages/Nike Dunk Low3.png', '/Images/ProductImages/Nike Dunk Low4.png'),
  ('Dunk', 'Nike Dunk Low Retro', 2099.95, '/Images/ProductImages/Nike Dunk Low Retro.png', '/Images/ProductImages/Nike Dunk Low Retro2.png', '/Images/ProductImages/Nike Dunk Low Retro3.png', '/Images/ProductImages/Nike Dunk Low Retro4.png'),
  ('Dunk', 'Nike SB Dunk Low AA', 2099.95, '/Images/ProductImages/Nike SB Dunk Low AA.png', '/Images/ProductImages/Nike SB Dunk Low AA2.png', '/Images/ProductImages/Nike SB Dunk Low AA3.png', '/Images/ProductImages/Nike SB Dunk Low AA4.png'),
  ('Dunk', 'Nike Dunk High Retro', 2399.95, '/Images/ProductImages/Nike Dunk High Retro.png', '/Images/ProductImages/Nike Dunk High Retro2.png', '/Images/ProductImages/Nike Dunk High Retro3.png', '/Images/ProductImages/Nike Dunk High Retro4.png');

-- Jordan
INSERT INTO products (product_category, product_name, product_price, image1, image2, image3, image4)
VALUES
  ('Jordan', 'Air Jordan 1 Low', 2399.95, '/Images/ProductImages/Air Jordan 1 Low.png', '/Images/ProductImages/Air Jordan 1 Low2.png', '/Images/ProductImages/Air Jordan 1 Low3.png', '/Images/ProductImages/Air Jordan 1 Low4.png'),
  ('Jordan', 'Air Jordan 3 Retro', 3000.00, '/Images/ProductImages/Air Jordan 3 Retro.png', '/Images/ProductImages/Air Jordan 3 Retro2.png', '/Images/ProductImages/Air Jordan 3 Retro3.png', '/Images/ProductImages/Air Jordan 3 Retro4.png'),
  ('Jordan', 'Air Jordan 1 Mid SE', 2499.95, '/Images/ProductImages/Air Jordan 1 Mid SE.png', '/Images/ProductImages/Air Jordan 1 Mid SE2.png', '/Images/ProductImages/Air Jordan 1 Mid SE3.png', '/Images/ProductImages/Air Jordan 1 Mid SE4.png'),
  ('Jordan', 'Air Jordan Retro 13', 3499.95, '/Images/ProductImages/Air Jordan Retro 13.png', '/Images/ProductImages/Air Jordan Retro 132.png', '/Images/ProductImages/Air Jordan Retro 133.png', '/Images/ProductImages/Air Jordan Retro 134.png');
-- AirForce
INSERT INTO products (product_category, product_name, product_price, image1, image2, image3, image4)
VALUES
  ('AirForce', 'Nike Air Force 1 07', 1899.95, '/Images/ProductImages/Nike Air Force 1 07.png', '/Images/ProductImages/Nike Air Force 1 072.png', '/Images/ProductImages/Nike Air Force 1 073.png', '/Images/ProductImages/Nike Air Force 1 074.png'),
  ('AirForce', 'Nike Air Force 1 LV8', 1899.95, '/Images/ProductImages/Nike Air Force 1 LV8.png', '/Images/ProductImages/Nike Air Force 1 LV82.png', '/Images/ProductImages/Nike Air Force 1 LV83.png', '/Images/ProductImages/Nike Air Force 1 LV84.png'),
  ('AirForce', 'Nike Air Force 1 Low By You', 2599.95, '/Images/ProductImages/Nike Air Force 1 Low By You.png', '/Images/ProductImages/Nike Air Force 1 Low By You2.png', '/Images/ProductImages/Nike Air Force 1 Low By You3.png', '/Images/ProductImages/Nike Air Force 1 Low By You4.png'),
  ('AirForce', 'Nike Air Force 1 Luxe', 2899.95, '/Images/ProductImages/Nike Air Force 1 Luxe.png', '/Images/ProductImages/Nike Air Force 1 Luxe2.png', '/Images/ProductImages/Nike Air Force 1 Luxe3.png', '/Images/ProductImages/Nike Air Force 1 Luxe4.png');

-- AirMax
INSERT INTO products (product_category, product_name, product_price, image1, image2, image3, image4)
VALUES
  ('AirMax', 'Nike Air Max Plus', 3599.99, '/Images/ProductImages/Nike Air Max Plus.png', '/Images/ProductImages/Nike Air Max Plus2.png', '/Images/ProductImages/Nike Air Max Plus3.png', '/Images/ProductImages/Nike Air Max Plus4.png'),
  ('AirMax', 'Nike Air Max Pulse', 3599.95, '/Images/ProductImages/Nike Air Max Pulse.png', '/Images/ProductImages/Nike Air Max Pulse2.png', '/Images/ProductImages/Nike Air Max Pulse3.png', '/Images/ProductImages/Nike Air Max Pulse4.png'),
  ('AirMax', 'Nike Air Max 97', 2599.97, '/Images/ProductImages/Nike Air Max 97.png', '/Images/ProductImages/Nike Air Max 972.png', '/Images/ProductImages/Nike Air Max 973.png', '/Images/ProductImages/Nike Air Max 974.png'),
  ('AirMax', 'Nike Air Max 1', 2799.95, '/Images/ProductImages/Nike Air Max 1.png', '/Images/ProductImages/Nike Air Max 12.png', '/Images/ProductImages/Nike Air Max 13.png', '/Images/ProductImages/Nike Air Max 14.png');

  -- Dunk
INSERT INTO product_stock (product_id, size3, size4, size5, size6, size7, size8, size9, size10, size11, size12)
VALUES
  (1, 0, 1, 20, 5, 8, 12, 18, 22, 17, 9),
  (2, 14, 8, 11, 20, 4, 7, 19, 25, 16, 3),
  (3, 6, 13, 22, 7, 11, 19, 14, 18, 21, 5),
  (4, 9, 19, 5, 17, 12, 21, 10, 8, 23, 15);

-- Jordan
INSERT INTO product_stock (product_id, size3, size4, size5, size6, size7, size8, size9, size10, size11, size12)
VALUES
  (5, 17, 6, 12, 8, 22, 4, 9, 13, 18, 25),
  (6, 10, 14, 21, 19, 8, 5, 11, 23, 16, 7),
  (7, 18, 9, 15, 22, 13, 7, 11, 24, 19, 5),
  (8, 11, 23, 5, 17, 20, 9, 15, 8, 12, 25);

-- AirForce
INSERT INTO product_stock (product_id, size3, size4, size5, size6, size7, size8, size9, size10, size11, size12)
VALUES
  (9, 12, 7, 16, 19, 21, 8, 15, 11, 23, 6),
  (10, 13, 6, 8, 17, 20, 9, 14, 22, 5, 18),
  (11, 19, 14, 5, 10, 18, 11, 22, 7, 16, 25),
  (12, 10, 12, 21, 6, 9, 16, 8, 25, 17, 14);

-- AirMax
INSERT INTO product_stock (product_id, size3, size4, size5, size6, size7, size8, size9, size10, size11, size12)
VALUES
  (13, 8, 14, 17, 10, 22, 12, 6, 18, 9, 20),
  (14, 16, 9, 11, 23, 7, 13, 5, 19, 10, 21),
  (15, 22, 6, 14, 9, 16, 10, 18, 8, 12, 23),
  (16, 13, 8, 12, 7, 24, 11, 15, 19, 9, 21);
