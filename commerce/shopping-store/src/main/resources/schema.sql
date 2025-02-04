CREATE TABLE IF NOT EXISTS products
(
    id UUID PRIMARY KEY NOT NULL,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(1000) NOT NULL,
    image_src VARCHAR(500),
    quantity_state VARCHAR(6) NOT NULL,
    category VARCHAR(8),
    price DECIMAL(10, 2) NOT NULL,
    product_state VARCHAR(10) NOT NULL
);