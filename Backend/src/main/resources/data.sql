CREATE TABLE IF NOT EXISTS PRODUCTS (
    PRODUCT_ID INT AUTO_INCREMENT PRIMARY KEY,
    PRODUCT_NAME VARCHAR(255) NOT NULL,
    PRODUCT_PRICE DECIMAL(10,2) NOT NULL,
    PRODUCT_CATEGORY VARCHAR(255) NOT NULL,
    IMAGE1 VARCHAR(255) NOT NULL,
    IMAGE2 VARCHAR(255),
    IMAGE3 VARCHAR(255),
    IMAGE4 VARCHAR(255),
    PRICE_URL VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS PRODUCT_STOCK (
    STOCK_ID INT AUTO_INCREMENT PRIMARY KEY,
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
    FOREIGN KEY (product_id) REFERENCES PRODUCTS(PRODUCT_ID) ON DELETE CASCADE
);

-- Dunk
INSERT INTO PRODUCTS (PRODUCT_CATEGORY, PRODUCT_NAME, PRODUCT_PRICE, IMAGE1, IMAGE2, IMAGE3, IMAGE4, PRICE_URL)
VALUES
  ('Dunk', 'Nike Dunk Low', 2099.95, '/Images/ProductImages/Nike Dunk Low.png', '/Images/ProductImages/Nike Dunk Low2.png', '/Images/ProductImages/Nike Dunk Low3.png', '/Images/ProductImages/Nike Dunk Low4.png','price_1OxtDsEU0z74hURXuKdTkfkD'),
  ('Dunk', 'Nike Dunk Low Retro', 2099.95, '/Images/ProductImages/Nike Dunk Low Retro.png', '/Images/ProductImages/Nike Dunk Low Retro2.png', '/Images/ProductImages/Nike Dunk Low Retro3.png', '/Images/ProductImages/Nike Dunk Low Retro4.png','price_1OxtFFEU0z74hURXcxuSRbs4'),
  ('Dunk', 'Nike SB Dunk Low AA', 2099.95, '/Images/ProductImages/Nike SB Dunk Low AA.png', '/Images/ProductImages/Nike SB Dunk Low AA2.png', '/Images/ProductImages/Nike SB Dunk Low AA3.png', '/Images/ProductImages/Nike SB Dunk Low AA4.png','price_1OxtFnEU0z74hURXTj3v6dQu'),
  ('Dunk', 'Nike Dunk High Retro', 2399.95, '/Images/ProductImages/Nike Dunk High Retro.png', '/Images/ProductImages/Nike Dunk High Retro2.png', '/Images/ProductImages/Nike Dunk High Retro3.png', '/Images/ProductImages/Nike Dunk High Retro4.png','price_1OxtGVEU0z74hURXFV5GVhhu');

-- Jordan
INSERT INTO PRODUCTS (PRODUCT_CATEGORY, PRODUCT_NAME, PRODUCT_PRICE, IMAGE1, IMAGE2, IMAGE3, IMAGE4, PRICE_URL)
VALUES
  ('Jordan', 'Air Jordan 1 Low', 2399.95, '/Images/ProductImages/Air Jordan 1 Low.png', '/Images/ProductImages/Air Jordan 1 Low2.png', '/Images/ProductImages/Air Jordan 1 Low3.png', '/Images/ProductImages/Air Jordan 1 Low4.png','price_1OxtHvEU0z74hURXR12K7y5a'),
  ('Jordan', 'Air Jordan 3 Retro', 3000.00, '/Images/ProductImages/Air Jordan 3 Retro.png', '/Images/ProductImages/Air Jordan 3 Retro2.png', '/Images/ProductImages/Air Jordan 3 Retro3.png', '/Images/ProductImages/Air Jordan 3 Retro4.png','price_1OxtKwEU0z74hURX2cIaQKfF'),
  ('Jordan', 'Air Jordan 1 Mid SE', 2499.95, '/Images/ProductImages/Air Jordan 1 Mid SE.png', '/Images/ProductImages/Air Jordan 1 Mid SE2.png', '/Images/ProductImages/Air Jordan 1 Mid SE3.png', '/Images/ProductImages/Air Jordan 1 Mid SE4.png','price_1OxtMWEU0z74hURXD4zM526Z'),
  ('Jordan', 'Air Jordan Retro 13', 3499.95, '/Images/ProductImages/Air Jordan Retro 13.png', '/Images/ProductImages/Air Jordan Retro 132.png', '/Images/ProductImages/Air Jordan Retro 133.png', '/Images/ProductImages/Air Jordan Retro 134.png','price_1OxtOxEU0z74hURXoWnakVhK');
-- AirForce
INSERT INTO PRODUCTS (PRODUCT_CATEGORY, PRODUCT_NAME, PRODUCT_PRICE, IMAGE1, IMAGE2, IMAGE3, IMAGE4, PRICE_URL)
VALUES
  ('AirForce', 'Nike Air Force 1 07', 1899.95, '/Images/ProductImages/Nike Air Force 1 07.png', '/Images/ProductImages/Nike Air Force 1 072.png', '/Images/ProductImages/Nike Air Force 1 073.png', '/Images/ProductImages/Nike Air Force 1 074.png','price_1OxtPwEU0z74hURXu4euvVNP'),
  ('AirForce', 'Nike Air Force 1 LV8', 1899.95, '/Images/ProductImages/Nike Air Force 1 LV8.png', '/Images/ProductImages/Nike Air Force 1 LV82.png', '/Images/ProductImages/Nike Air Force 1 LV83.png', '/Images/ProductImages/Nike Air Force 1 LV84.png','price_1OxtQdEU0z74hURXpX06ccX6'),
  ('AirForce', 'Nike Air Force 1 Low By You', 2599.95, '/Images/ProductImages/Nike Air Force 1 Low By You.png', '/Images/ProductImages/Nike Air Force 1 Low By You2.png', '/Images/ProductImages/Nike Air Force 1 Low By You3.png', '/Images/ProductImages/Nike Air Force 1 Low By You4.png','price_1OxtRUEU0z74hURX23SXZy1M'),
  ('AirForce', 'Nike Air Force 1 Luxe', 2899.95, '/Images/ProductImages/Nike Air Force 1 Luxe.png', '/Images/ProductImages/Nike Air Force 1 Luxe2.png', '/Images/ProductImages/Nike Air Force 1 Luxe3.png', '/Images/ProductImages/Nike Air Force 1 Luxe4.png','price_1OxtSkEU0z74hURX8mWd3o3j');

-- AirMax
INSERT INTO PRODUCTS (PRODUCT_CATEGORY, PRODUCT_NAME, PRODUCT_PRICE, IMAGE1, IMAGE2, IMAGE3, IMAGE4, PRICE_URL)
VALUES
  ('AirMax', 'Nike Air Max Plus', 3599.99, '/Images/ProductImages/Nike Air Max Plus.png', '/Images/ProductImages/Nike Air Max Plus2.png', '/Images/ProductImages/Nike Air Max Plus3.png', '/Images/ProductImages/Nike Air Max Plus4.png','price_1OxtTOEU0z74hURX42PwaUOX'),
  ('AirMax', 'Nike Air Max Pulse', 3599.95, '/Images/ProductImages/Nike Air Max Pulse.png', '/Images/ProductImages/Nike Air Max Pulse2.png', '/Images/ProductImages/Nike Air Max Pulse3.png', '/Images/ProductImages/Nike Air Max Pulse4.png','price_1OxtUCEU0z74hURX3bWAu2et'),
  ('AirMax', 'Nike Air Max 97', 2599.97, '/Images/ProductImages/Nike Air Max 97.png', '/Images/ProductImages/Nike Air Max 972.png', '/Images/ProductImages/Nike Air Max 973.png', '/Images/ProductImages/Nike Air Max 974.png','price_1OxtUkEU0z74hURXKNPnAj4U'),
  ('AirMax', 'Nike Air Max 1', 2799.95, '/Images/ProductImages/Nike Air Max 1.png', '/Images/ProductImages/Nike Air Max 12.png', '/Images/ProductImages/Nike Air Max 13.png', '/Images/ProductImages/Nike Air Max 14.png','price_1OxtVHEU0z74hURX84QFjsyc');

  -- Dunk
INSERT INTO PRODUCT_STOCK (product_id, size3, size4, size5, size6, size7, size8, size9, size10, size11, size12)
VALUES
  (1, 0, 1, 20, 5, 8, 12, 18, 22, 17, 9),
  (2, 14, 8, 11, 20, 4, 7, 19, 25, 16, 3),
  (3, 6, 13, 22, 7, 11, 19, 14, 18, 21, 5),
  (4, 9, 19, 5, 17, 12, 21, 10, 8, 23, 15);

-- Jordan
INSERT INTO PRODUCT_STOCK (product_id, size3, size4, size5, size6, size7, size8, size9, size10, size11, size12)
VALUES
  (5, 17, 6, 12, 8, 22, 4, 9, 13, 18, 25),
  (6, 10, 14, 21, 19, 8, 5, 11, 23, 16, 7),
  (7, 18, 9, 15, 22, 13, 7, 11, 24, 19, 5),
  (8, 11, 23, 5, 17, 20, 9, 15, 8, 12, 25);

-- AirForce
INSERT INTO PRODUCT_STOCK (product_id, size3, size4, size5, size6, size7, size8, size9, size10, size11, size12)
VALUES
  (9, 12, 7, 16, 19, 21, 8, 15, 11, 23, 6),
  (10, 13, 6, 8, 17, 20, 9, 14, 22, 5, 18),
  (11, 19, 14, 5, 10, 18, 11, 22, 7, 16, 25),
  (12, 10, 12, 21, 6, 9, 16, 8, 25, 17, 14);

-- AirMax
INSERT INTO PRODUCT_STOCK (product_id, size3, size4, size5, size6, size7, size8, size9, size10, size11, size12)
VALUES
  (13, 8, 14, 17, 10, 22, 12, 6, 18, 9, 20),
  (14, 16, 9, 11, 23, 7, 13, 5, 19, 10, 21),
  (15, 22, 6, 14, 9, 16, 10, 18, 8, 12, 23),
  (16, 13, 8, 12, 7, 24, 11, 15, 19, 9, 21);
  