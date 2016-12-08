SELECT
  orderItemId
  ,orderGroupId
  ,productId
  ,quantity
  ,orderDateTime
  ,status
  ,version
FROM
  OrderItem
WHERE
  orderItemId = /* orderItemId */1
