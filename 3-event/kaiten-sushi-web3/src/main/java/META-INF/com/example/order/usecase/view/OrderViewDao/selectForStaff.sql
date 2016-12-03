SELECT
  OrderItem.orderItemId AS orderId
  ,OrderItem.orderGroupId
  ,OrderGroup.tableNumber
  ,OrderItem.productId
  ,Product.productName
  ,OrderItem.quantity
  ,OrderItem.orderDateTime
  ,OrderItem.status AS orderItemStatus
FROM
  OrderItem
  JOIN OrderGroup
    ON OrderItem.orderGroupId = OrderGroup.orderGroupId
  JOIN Product
    ON OrderItem.productId = Product.productId
WHERE
  OrderGroup.status <> 3
  AND OrderItem.status = 1
ORDER BY
  OrderItem.orderDateTime ASC
