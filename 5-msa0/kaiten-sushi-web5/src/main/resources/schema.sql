DROP TABLE IF EXISTS Product;
DROP TABLE IF EXISTS OrderGroup;
DROP TABLE IF EXISTS OrderItem;
DROP TABLE IF EXISTS OrderSeatView;
DROP TABLE IF EXISTS OrderStaffView;

CREATE TABLE Product
(
  productId VARCHAR(36) PRIMARY KEY
  ,productName VARCHAR(30)
  ,stockQuantity INT
  ,version INT
);

CREATE TABLE OrderGroup
(
  orderGroupId VARCHAR(36) PRIMARY KEY
  ,tableNumber INT
  ,status INT
  ,version INT
);

CREATE TABLE OrderItem
(
  orderItemId VARCHAR(36) PRIMARY KEY
  ,orderGroupId VARCHAR(36)
  ,productId VARCHAR(36)
  ,quantity INT
  ,orderDateTime DATETIME
  ,status INT
);

CREATE TABLE OrderSeatView
(
  orderId VARCHAR(36) PRIMARY KEY
  ,orderGroupId VARCHAR(36)
  ,productId VARCHAR(36)
  ,productName VARCHAR(30)
  ,tableNumber INT
  ,quantity INT
  ,orderDateTime DATETIME
  ,delivered INT
);

CREATE TABLE OrderStaffView
(
  orderId VARCHAR(36) PRIMARY KEY
  ,orderGroupId VARCHAR(36)
  ,productId VARCHAR(36)
  ,productName VARCHAR(30)
  ,tableNumber INT
  ,quantity INT
  ,orderDateTime DATETIME
);
