CREATE TABLE inventory_product_tb (
	id BIGSERIAL NOT NULL,
	name VARCHAR NOT NULL,
	description VARCHAR NOT NULL,
	vehicle_type VARCHAR NOT NULL,
	price INT NOT NULL,
	range VARCHAR NOT NULL,
	PRIMARY KEY (id)
)