CREATE TABLE IF NOT EXISTS products
(
    id UUID PRIMARY KEY,
    fragile BOOLEAN,
    width DECIMAL(10, 2) NOT NULL,
    height DECIMAL(10, 2) NOT NULL,
    depth DECIMAL(10, 2) NOT NULL,
    weight DECIMAL(10, 2) NOT NULL,
    quantity BIGINT NOT NULL
);