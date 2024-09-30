CREATE TABLE order_product_tb (
	id BIGSERIAL NOT NULL,
	name VARCHAR(255) NOT NULL,
	description VARCHAR(255) NOT NULL,
	product_type VARCHAR(255) NOT NULL,
	price INT NOT NULL,
	range VARCHAR(255) NOT NULL,
	quantity INT NOT NULL,
	PRIMARY KEY (id)
)