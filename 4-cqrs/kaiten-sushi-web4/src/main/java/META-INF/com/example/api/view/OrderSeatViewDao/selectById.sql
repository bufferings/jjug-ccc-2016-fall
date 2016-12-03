SELECT
  orderId
  ,orderGroupId
  ,tableNumber
  ,productId
  ,productName
  ,quantity
  ,orderDateTime
  ,delivered
FROM
  OrderSeatView
WHERE
  orderId = /* orderId */1
