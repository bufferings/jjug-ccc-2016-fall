SELECT
  orderId
  ,orderGroupId
  ,tableNumber
  ,productId
  ,productName
  ,quantity
  ,orderDateTime
FROM
  OrderStaffView
ORDER BY
  orderDateTime ASC
