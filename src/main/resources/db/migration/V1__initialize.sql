/*DROP DATABASE IF EXISTS `spring_shop`;*/
CREATE SCHEMA IF NOT EXISTS spring_shop DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;

USE spring_shop;

DROP TABLE IF EXISTS customers;
CREATE TABLE customers
(
    id   serial,
    name VARCHAR(45) NULL,
    UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE,
    PRIMARY KEY (id),
    UNIQUE INDEX name_UNIQUE (name ASC) VISIBLE
);

DROP TABLE IF EXISTS products;
CREATE TABLE products
(
    id    SERIAL,
    title VARCHAR(45)   not null,
    cost  DECIMAL(8, 2) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE,
    UNIQUE INDEX title_UNIQUE (`title` ASC) VISIBLE
);

INSERT INTO customers (name)
VALUES ('Арнольд'),
       ('Иван'),
       ('Мария'),
       ('Сергей'),
       ('Татьяна'),
       ('Ольга'),
       ('Тарас'),
       ('Гиви');

INSERT INTO products (title, cost)
VALUES ('Чемодан', '1000'),
       ('Сундук', '2000'),
       ('Портфель', '3000'),
       ('Кошёлка', '7500'),
       ('Кровать', '1540'),
       ('Стул', '7841'),
       ('Табурет', '4545'),
       ('Тахта', '1500'),
       ('Тумбочка', '6500'),
       ('Фонарь', '100');

DROP TABLE IF EXISTS users;
CREATE TABLE users
(
    id         serial,
    username   varchar(50) NOT NULL,
    password   varchar(80) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name  VARCHAR(50) NOT NULL,
    email      VARCHAR(50) NOT NULL,
    phone      VARCHAR(15) NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS roles;
CREATE TABLE roles
(
    id   serial,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS users_roles;
CREATE TABLE users_roles
(
    user_id bigint NOT NULL,
    role_id bigint NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

INSERT INTO roles (name)
VALUES ('ROLE_USER'),
       ('ROLE_MANAGER'),
       ('ROLE_ADMIN');

INSERT INTO users (username, password, first_name, last_name, email, phone)
VALUES ('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'Admin', 'Admin', 'admin@gmail.com',
        '+79881111111'); /*pass=100*/

INSERT INTO users_roles (user_id, role_id)
VALUES (1, 1),
       (1, 2),
       (1, 3);

drop table if exists orders;
create table orders
(
    id      serial,
    user_id bigint,
    PRIMARY KEY (id),
    foreign key (user_id) references users (id)
);

drop table if exists order_items;
create table order_items
(
    id         bigint NOT NULL AUTO_INCREMENT,
    order_id   bigint,
    product_id bigint,
    quantity   int,
    price      numeric(8, 2),
    PRIMARY KEY (id),
    foreign key (order_id) references orders (id),
    foreign key (product_id) references products (id)
);