CREATE TABLE IF NOT EXISTS shopping_cart
(
    id UUID PRIMARY KEY NOT NULL,
    username VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS shopping_cart_product
(
    id UUID PRIMARY KEY,
    product_id UUID NOT NULL,
    quantity BIGINT NOT NULL,
    shopping_cart_id UUID NOT NULL,
    state VARCHAR(8) NOT NULL,
    FOREIGN KEY (shopping_cart_id) REFERENCES shopping_cart(id) ON DELETE CASCADE
);