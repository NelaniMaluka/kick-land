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
  ('Dunk', '/Images/ProductImages/Nike Dunk Low Retro Sneakers.png', 'Nike Dunk Low Retro Sneakers', 1999),
  ('Dunk', '/Images/ProductImages/Nike Dunk Low SE 85 Double Swoosh Sail Orange.png', 'Nike Dunk Low SE 85 Double Swoosh Sail Orange', 5400),
  ('Dunk', '/Images/ProductImages/Nike Travis Scott X SB Dunk Low PRM QS ''Cactus Jack''.png', 'Nike Travis Scott X SB Dunk Low PRM QS ''Cactus Jack''', 2500),
  ('Dunk', '/Images/ProductImages/Nike Dunk Low Clear Aura.png', 'Nike Dunk Low Clear Aura', 4000),

  -- Jordan
  ('Jordan', '/Images/ProductImages/Nike Paris Saint-Germain X Air Jordan 4 Retro ''Bordeaux''.png', 'Nike Paris Saint-Germain X Air Jordan 4 Retro ''Bordeaux''', 1000),
  ('Jordan', '/Images/ProductImages/Nike Air Jordan X Zion Willamson Low OG ''Voodoo'' Sneakers.png', 'Nike Air Jordan X Zion Willamson Low OG ''Voodoo'' Sneakers', 2000),
  ('Jordan', '/Images/ProductImages/Nike Air Jordan 4 SB ''Pine Green'' Sneakers.png', 'Nike Air Jordan 4 SB ''Pine Green'' Sneakers', 3000),
  ('Jordan', '/Images/ProductImages/Nike Air Jordan 1 ''Travis Scott'' Low.png', 'Nike Air Jordan 1 ''Travis Scott'' Low', 4000),

  -- AirForce
  ('AirForce', '/Images/ProductImages/Nike Air Force ''07 Low-Top'' Sneakers.png', 'Nike Air Force ''07 Low-Top'' Sneakers', 1899),
  ('AirForce', '/Images/ProductImages/Nike Air Force 1 Low ''Panda'' Sneakers.png', 'Nike Air Force 1 Low ''Panda'' Sneakers', 2299),
  ('AirForce', '/Images/ProductImages/Nike Air Force 1 ''07 Low VD Sneakers.png', 'Nike Air Force 1 ''07 Low VD Sneakers', 4989),
  ('AirForce', '/Images/ProductImages/Nike Air Force 1 Low ''ST PATRICK''S DAY'' Sneakers.png', 'Nike Air Force 1 Low ''ST PATRICK''S DAY'' Sneakers', 4000),

  -- AirMax
  ('AirMax', '/Images/ProductImages/Nike Air Max Plus.png', 'Nike Air Max Plus', 3599),
  ('AirMax', '/Images/ProductImages/Nike Air Max Pulse.png', 'Nike Air Max Pulse', 3199),
  ('AirMax', '/Images/ProductImages/Nike Air Max 97.png', 'Nike Air Max 97', 3599),
  ('AirMax', '/Images/ProductImages/Nike Air Max 1.png', 'Nike Air Max 1', 4000);
