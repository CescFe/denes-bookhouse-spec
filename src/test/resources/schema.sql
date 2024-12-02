DROP TABLE IF EXISTS products;

CREATE TABLE products
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    amount      BIGINT       NOT NULL
);
