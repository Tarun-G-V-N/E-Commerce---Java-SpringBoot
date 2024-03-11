CREATE TABLE category
(
    id            BINARY(16) NOT NULL,
    category_name VARCHAR(255) NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE ecom_order
(
    id BINARY(16) NOT NULL,
    CONSTRAINT pk_ecom_order PRIMARY KEY (id)
);

CREATE TABLE ecom_order_products
(
    ecom_order_id BINARY(16) NOT NULL,
    products_id   BINARY(16) NOT NULL
);

CREATE TABLE price
(
    id       BINARY(16) NOT NULL,
    currency VARCHAR(255) NULL,
    amount DOUBLE NOT NULL,
    discount DOUBLE NOT NULL,
    CONSTRAINT pk_price PRIMARY KEY (id)
);

CREATE TABLE product
(
    id            BINARY(16) NOT NULL,
    title         VARCHAR(255) NULL,
    price_id      BINARY(16) NULL,
    `description` VARCHAR(255) NULL,
    category_id   BINARY(16) NULL,
    image         VARCHAR(255) NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_PRICE FOREIGN KEY (price_id) REFERENCES price (id);

ALTER TABLE ecom_order_products
    ADD CONSTRAINT fk_ecoordpro_on_order FOREIGN KEY (ecom_order_id) REFERENCES ecom_order (id);

ALTER TABLE ecom_order_products
    ADD CONSTRAINT fk_ecoordpro_on_product FOREIGN KEY (products_id) REFERENCES product (id);