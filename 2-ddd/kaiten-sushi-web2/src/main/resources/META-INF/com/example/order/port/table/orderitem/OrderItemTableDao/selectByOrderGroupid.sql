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
  orderGroupId = /* orderGroupId */1
ORDER BY orderDateTime ASC
