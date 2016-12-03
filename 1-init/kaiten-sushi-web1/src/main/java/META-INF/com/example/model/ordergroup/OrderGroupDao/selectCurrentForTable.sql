SELECT
  orderGroupId
  ,tableNumber
  ,status
  ,version
FROM
  OrderGroup
WHERE
  tableNumber = /* tableNumber */1
  AND status <> 3
