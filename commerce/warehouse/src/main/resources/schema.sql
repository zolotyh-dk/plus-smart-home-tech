CREATE TABLE IF NOT EXISTS products
(
    id UUID PRIMARY KEY,
    fragile BOOLEAN,
    width DECIMAL(10, 2) NOT NULL,
    height DECIMAL(10, 2) NOT NULL,
    depth DECIMAL(10, 2) NOT NULL,
    weight DECIMAL(10, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS addresses
(
    id UUID PRIMARY KEY,
    country VARCHAR(100),
    city VARCHAR(100),
    street VARCHAR(100),
    house VARCHAR(10),
    flat VARCHAR(10)
);

CREATE TABLE IF NOT EXISTS warehouses
(
    id UUID PRIMARY KEY,
    address_id UUID NOT NULL,
    FOREIGN KEY (address_id) REFERENCES addresses(id)
);

CREATE TABLE IF NOT EXISTS warehouse_product
(
    product_id UUID NOT NULL,
    warehouse_id UUID NOT NULL,
    quantity BIGINT NOT NULL,
    PRIMARY KEY (product_id, warehouse_id),
    FOREIGN KEY (warehouse_id) REFERENCES warehouses(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);