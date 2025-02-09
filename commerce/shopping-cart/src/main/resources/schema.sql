CREATE TABLE IF NOT EXISTS shopping_carts
(
    id UUID PRIMARY KEY NOT NULL,
    username VARCHAR(100) NOT NULL UNIQUE,
    state VARCHAR(8) NOT NULL
);

CREATE TABLE IF NOT EXISTS shopping_cart_products
(
    id UUID PRIMARY KEY,
    product_id UUID NOT NULL,
    quantity BIGINT NOT NULL,
    shopping_cart_id UUID NOT NULL,
    FOREIGN KEY (shopping_cart_id) REFERENCES shopping_carts(id) ON DELETE CASCADE
);