DELETE
FROM products;
DELETE
FROM carts;
DELETE
FROM cart_products;

-- Insert Products
INSERT INTO products (description, amount)
VALUES ('description_test', 250);
INSERT INTO products (description, amount)
VALUES ('description_test', 250);

-- Insert Carts
INSERT INTO carts (updated_at)
VALUES (CURRENT_TIMESTAMP);
-- Associate Products with Carts
INSERT INTO cart_products (cart_id, product_id)
VALUES (1, 1);
INSERT INTO cart_products (cart_id, product_id)
VALUES (1, 2);