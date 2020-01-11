DROP TABLE IF EXISTS authorities;
CREATE TABLE authorities (id bigint auto_increment not null, username varchar_ignorecase(50) not null, authority varchar_ignorecase(50) not null, constraint fk_authorities_users foreign key(username) references users(username));

INSERT INTO users (id, username, password,enabled) VALUES (1, 'root', 'root', true), (2, 'user', 'user', true);
INSERT INTO authorities (id, username, authority) VALUES (1, 'root', 'ROLE_user'), (2, 'root', 'ROLE_admin'), (3, 'user', 'ROLE_user');

DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS item;
DROP TABLE IF EXISTS customerorder;
DROP TABLE IF EXISTS orderitem;

CREATE TABLE customer(id bigint auto_increment not null, name varchar_ignorecase(50));
CREATE TABLE item(id bigint auto_increment not null, name varchar_ignorecase(50), price decimal(18, 2));
CREATE TABLE customerorder(id bigint auto_increment not null, order_No varchar_ignorecase(50), customer_Id bigint, pmethod varchar_ignorecase(50), gtotal decimal(18, 2), constraint fk_customer foreign key(customer_Id) references customer(id));

CREATE TABLE orderItem(id bigint auto_increment not null, orderId bigint, itemId int, quantity int, constraint fk_order FOREIGN KEY(orderId) references customerorder(id), constraint fk_item FOREIGN KEY(itemId) references item(id));


INSERT INTO Customer(id, name) VALUES (1, N'Olivia Kathleen');
INSERT INTO Customer(id, name) VALUES (2, N'Liam Patrick');
INSERT INTO Customer(id, name) VALUES  (3, N'Charlotte Rose');
INSERT INTO Customer(id, name) VALUES  (4, N'Elijah Burke ');
INSERT INTO Customer(id, name) VALUES  (5, N'Ayesha Ameer');
INSERT INTO Customer(id, name) VALUES  (6, N'Eva Louis');

-- INSERT INTO Item (id, name, price) VALUES (1, N'Chicken Tenders', CAST(3.50 AS Decimal(18, 2)));
-- INSERT INTO Item (id, name, price) VALUES  (2, N'Chicken Tenders w/ Fries', CAST(4.99 AS Decimal(18, 2)));
-- INSERT INTO Item (id, name, price) VALUES  (3, N'Chicken Tenders w/ Onion', CAST(5.99 AS Decimal(18, 2)));
-- INSERT INTO Item (id, name, price) VALUES  (4, N'Grilled Cheese Sandwich', CAST(2.50 AS Decimal(18, 2)));
-- INSERT INTO Item (id, name, price) VALUES  (5, N'Grilled Cheese Sandwich w/ Fries', CAST(3.99 AS Decimal(18, 2)));
-- INSERT INTO Item (id, name, price) VALUES  (6, N'Grilled Cheese Sandwich w/ Onion', CAST(4.99 AS Decimal(18, 2)));
-- INSERT INTO Item (id, name, price) VALUES  (7, N'Lettuce and Tomato Burger', CAST(1.99 AS Decimal(18, 2)));
-- INSERT INTO Item (id, name, price) VALUES  (8, N'Soup', CAST(2.50 AS Decimal(18, 2)));
-- INSERT INTO Item (id, name, price) VALUES  (9, N'Onion Rings', CAST(2.99 AS Decimal(18, 2)));
-- INSERT INTO Item (id, name, price) VALUES (10, N'Fries', CAST(1.99 AS Decimal(18, 2)));
-- INSERT INTO Item (id, name, price) VALUES  (11, N'Sweet Potato Fries', CAST(2.49 AS Decimal(18, 2)));
-- INSERT INTO Item (id, name, price) VALUES  (12, N'Sweet Tea', CAST(1.79 AS Decimal(18, 2)));
-- INSERT INTO Item (id, name, price) VALUES  (13, N'Botttle Water', CAST(1.00 AS Decimal(18, 2)));
-- INSERT INTO Item (id, name, price) VALUES  (14, N'Canned Drinks', CAST(1.00 AS Decimal(18, 2)));

INSERT INTO Item (id, name, price) VALUES (1, N'Chicken Tenders', 3.50);
INSERT INTO Item (id, name, price) VALUES  (2, N'Chicken Tenders w/ Fries', 4.99);
INSERT INTO Item (id, name, price) VALUES  (3, N'Chicken Tenders w/ Onion', 5.99);
INSERT INTO Item (id, name, price) VALUES  (4, N'Grilled Cheese Sandwich', 2.50);
INSERT INTO Item (id, name, price) VALUES  (5, N'Grilled Cheese Sandwich w/ Fries', 3.99);
INSERT INTO Item (id, name, price) VALUES  (6, N'Grilled Cheese Sandwich w/ Onion', 4.99);
INSERT INTO Item (id, name, price) VALUES  (7, N'Lettuce and Tomato Burger', 1.99);
INSERT INTO Item (id, name, price) VALUES  (8, N'Soup', 2.50);
INSERT INTO Item (id, name, price) VALUES  (9, N'Onion Rings', 2.99);
INSERT INTO Item (id, name, price) VALUES (10, N'Fries', 1.99);
INSERT INTO Item (id, name, price) VALUES  (11, N'Sweet Potato Fries', 2.49);
INSERT INTO Item (id, name, price) VALUES  (12, N'Sweet Tea', 1.79);
INSERT INTO Item (id, name, price) VALUES  (13, N'Botttle Water', 1.00);
INSERT INTO Item (id, name, price) VALUES  (14, N'Canned Drinks', 1.00);


