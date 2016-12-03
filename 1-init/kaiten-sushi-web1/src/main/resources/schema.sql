DROP TABLE IF EXISTS Product;
DROP TABLE IF EXISTS OrderGroup;
DROP TABLE IF EXISTS OrderItem;

CREATE TABLE Product
(
  productId INT PRIMARY KEY
  ,productName VARCHAR(30)
  ,unitPrice INT
  ,stockQuantity INT
  ,version INT
);

CREATE TABLE OrderGroup
(
  orderGroupId INT PRIMARY KEY AUTO_INCREMENT
  ,tableNumber INT
  ,status INT
  ,version INT
);

CREATE TABLE OrderItem
(
  orderItemId INT PRIMARY KEY AUTO_INCREMENT
  ,orderGroupId INT
  ,productId INT
  ,quantity INT
  ,orderDateTime DATETIME
  ,status INT
  ,version INT
);
