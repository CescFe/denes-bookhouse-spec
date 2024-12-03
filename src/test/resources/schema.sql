-- Drop existing tables to avoid conflicts
DROP TABLE IF EXISTS cart_products;
DROP TABLE IF EXISTS carts;
DROP TABLE IF EXISTS products;

-- Create Products Table
CREATE TABLE products
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    amount      BIGINT       NOT NULL
);

-- Create Carts Table
CREATE TABLE carts
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    updated_at TIMESTAMP NOT NULL
);

-- Create Cart-Products Relationship Table
CREATE TABLE cart_products
(
    cart_id    BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    PRIMARY KEY (cart_id, product_id),
    FOREIGN KEY (cart_id) REFERENCES carts (id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE
);