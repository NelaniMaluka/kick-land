CREATE TABLE IF NOT EXISTS PRODUCTS (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(255) NOT NULL,
    PRICE DECIMAL(10,2) NOT NULL,
    CATEGORY VARCHAR(255) NOT NULL,
    IMAGE1 VARCHAR(255),
    IMAGE2 VARCHAR(255),
    IMAGE3 VARCHAR(255),
    IMAGE4 VARCHAR(255)
);

-- Dunk
INSERT INTO PRODUCTS (CATEGORY, NAME, PRICE, IMAGE1, IMAGE2, IMAGE3, IMAGE4)
VALUES
  ('Dunk', 'Nike Dunk Low', 2099.95, '/Images/ProductImages/Nike Dunk Low.png', '/Images/ProductImages/Nike Dunk Low2.png', '/Images/ProductImages/Nike Dunk Low3.png', '/Images/ProductImages/Nike Dunk Low4.png'),
  ('Dunk', 'Nike Dunk Low Retro', 2099.95, '/Images/ProductImages/Nike Dunk Low Retro.png', '/Images/ProductImages/Nike Dunk Low Retro2.png', '/Images/ProductImages/Nike Dunk Low Retro3.png', '/Images/ProductImages/Nike Dunk Low Retro4.png'),
  ('Dunk', 'Nike SB Dunk Low AA', 299.95, '/Images/ProductImages/Nike SB Dunk Low AA.png', '/Images/ProductImages/Nike SB Dunk Low AA2.png', '/Images/ProductImages/Nike SB Dunk Low AA3.png', '/Images/ProductImages/Nike SB Dunk Low AA4.png'),
  ('Dunk', 'Nike Dunk High Retro', 2399.95, '/Images/ProductImages/Nike Dunk High Retro.png', '/Images/ProductImages/Nike Dunk High Retro2.png', '/Images/ProductImages/Nike Dunk High Retro3.png', '/Images/ProductImages/Nike Dunk High Retro4.png');

-- Jordan
INSERT INTO PRODUCTS (CATEGORY, NAME, PRICE, IMAGE1, IMAGE2, IMAGE3, IMAGE4)
VALUES
  ('Jordan', 'Air Jordan 1 Low', 1000, '/Images/ProductImages/Air Jordan 1 Low.png', '/Images/ProductImages/Air Jordan 1 Low2.png', '/Images/ProductImages/Air Jordan 1 Low3.png', '/Images/ProductImages/Air Jordan 1 Low4.png'),
  ('Jordan', 'Air Jordan 3 Retro', 2000, '/Images/ProductImages/Air Jordan 3 Retro.png', '/Images/ProductImages/Air Jordan 3 Retro2.png', '/Images/ProductImages/Air Jordan 3 Retro3.png', '/Images/ProductImages/Air Jordan 3 Retro4.png'),
  ('Jordan', 'Air Jordan 1 Mid SE', 3000, '/Images/ProductImages/Air Jordan 1 Mid SE.png', '/Images/ProductImages/Air Jordan 1 Mid SE2.png', '/Images/ProductImages/Air Jordan 1 Mid SE3.png', '/Images/ProductImages/Air Jordan 1 Mid SE4.png'),
  ('Jordan', 'Air Jordan Retro 13', 4000, '/Images/ProductImages/Air Jordan Retro 13.png', '/Images/ProductImages/Air Jordan Retro 132.png', '/Images/ProductImages/Air Jordan Retro 133.png', '/Images/ProductImages/Air Jordan Retro 134.png');

-- AirForce
INSERT INTO PRODUCTS (CATEGORY, NAME, PRICE, IMAGE1, IMAGE2, IMAGE3, IMAGE4)
VALUES
  ('AirForce', 'Nike Air Force 1 07', 1899.95, '/Images/ProductImages/Nike Air Force 1 07.png', '/Images/ProductImages/Nike Air Force 1 072.png', '/Images/ProductImages/Nike Air Force 1 073.png', '/Images/ProductImages/Nike Air Force 1 074.png'),
  ('AirForce', 'Nike Air Force 1 LV8', 1899.95, '/Images/ProductImages/Nike Air Force 1 LV8.png', '/Images/ProductImages/Nike Air Force 1 LV82.png', '/Images/ProductImages/Nike Air Force 1 LV83.png', '/Images/ProductImages/Nike Air Force 1 LV84.png'),
  ('AirForce', 'Nike Air Force 1 Low By You', 2599.95, '/Images/ProductImages/Nike Air Force 1 Low By You.png', '/Images/ProductImages/Nike Air Force 1 Low By You2.png', '/Images/ProductImages/Nike Air Force 1 Low By You3.png', '/Images/ProductImages/Nike Air Force 1 Low By You4.png'),
  ('AirForce', 'Nike Air Force 1 Luxe', 4000, '/Images/ProductImages/Nike Air Force 1 Luxe.png', '/Images/ProductImages/Nike Air Force 1 Luxe2.png', '/Images/ProductImages/Nike Air Force 1 Luxe3.png', '/Images/ProductImages/Nike Air Force 1 Luxe4.png');

-- AirMax
INSERT INTO PRODUCTS (CATEGORY, NAME, PRICE, IMAGE1, IMAGE2, IMAGE3, IMAGE4)
VALUES
  ('AirMax', 'Nike Air Max Plus', 3599.99, '/Images/ProductImages/Nike Air Max Plus.png', '/Images/ProductImages/Nike Air Max Plus2.png', '/Images/ProductImages/Nike Air Max Plus3.png', '/Images/ProductImages/Nike Air Max Plus4.png'),
  ('AirMax', 'Nike Air Max Pulse', 3599.95, '/Images/ProductImages/Nike Air Max Pulse.png', '/Images/ProductImages/Nike Air Max Pulse2.png', '/Images/ProductImages/Nike Air Max Pulse3.png', '/Images/ProductImages/Nike Air Max Pulse4.png'),
  ('AirMax', 'Nike Air Max 97', 2599.97, '/Images/ProductImages/Nike Air Max 97.png', '/Images/ProductImages/Nike Air Max 972.png', '/Images/ProductImages/Nike Air Max 973.png', '/Images/ProductImages/Nike Air Max 974.png'),
  ('AirMax', 'Nike Air Max 1', 2799.95, '/Images/ProductImages/Nike Air Max 1.png', '/Images/ProductImages/Nike Air Max 12.png', '/Images/ProductImages/Nike Air Max 13.png', '/Images/ProductImages/Nike Air Max 14.png');
