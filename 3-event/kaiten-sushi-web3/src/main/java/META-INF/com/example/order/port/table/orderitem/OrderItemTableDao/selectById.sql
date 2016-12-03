SELECT
  orderItemId
  ,orderGroupId
  ,productId
  ,quantity
  ,orderDateTime
  ,status
FROM
  OrderItem
WHERE
  orderItemId = /* orderItemId */1
