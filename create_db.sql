# DROP DATABASE IF EXISTS `spring_shop`;
# CREATE SCHEMA `spring_shop` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;
#
# USE `spring_shop`;

DROP TABLE IF EXISTS `spring_shop`.`purchases`;
CREATE TABLE `spring_shop`.`purchases`
(
    `id`             INT            NOT NULL AUTO_INCREMENT,
    `customer`       INT            NOT NULL,
    `product`        INT            NOT NULL,
    `purchase_price` DECIMAL(19, 2) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    UNIQUE INDEX `customer_id_idx` (`customer` ASC) VISIBLE,
    UNIQUE INDEX `product_id_idx` (`product` ASC) VISIBLE,
    FOREIGN KEY (`customer`)
        REFERENCES `spring_shop`.`customers` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    FOREIGN KEY (`product`)
        REFERENCES `spring_shop`.`products` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);

DROP TABLE IF EXISTS `spring_shop`.`customers`;
CREATE TABLE `spring_shop`.`customers`
(
    `id`   INT         NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NULL,
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE
);

DROP TABLE IF EXISTS `spring_shop`.`products`;
CREATE TABLE `spring_shop`.`products`
(
    `id`    INT            NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(45)    NULL,
    `price`  DECIMAL(19, 2) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    UNIQUE INDEX `title_UNIQUE` (`title` ASC) VISIBLE
);

INSERT INTO `spring_shop`.`customers` (`name`)
VALUES ('Арнольд'),
       ('Иван'),
       ('Мария'),
       ('Сергей'),
       ('Татьяна'),
       ('Ольга'),
       ('Тарас'),
       ('Гиви');

INSERT INTO `spring_shop`.`products` (`title`, `price`)
VALUES ('Чемодан', '1000'),
       ('Сундук', '2000'),
       ('Портфель', '3000'),
       ('Кошёлка', '7500'),
       ('Кровать', '1540'),
       ('Стул', '7841'),
       ('Табурет', '4545'),
       ('Тахта', '1500'),
       ('Тумбочка', '6500'),
       ('Фонарь', '100'),
       ('Лампа', '9000'),
       ('Трюмо', '1000'),
       ('Раскладушка', '1000'),
       ('Контейнер', '2000'),
       ('Кастрюля', '3000'),
       ('Сковорода', '1000'),
       ('Ковшик', '1000'),
       ('Крышка', '2000'),
       ('Полотенце', '3000'),
       ('Наушники', '1000'),
       ('Плеер', '1000'),
       ('Патефон', '2000'),
       ('Магнитофон', '3000'),
       ('Радиола', '1000'),
       ('Проигрыватель', '2300'),
       ('Радиоприемник', '6500'),
       ('Видеомагнитофон', '4200'),
       ('Телевизор', '7500'),
       ('Монитор', '3254'),
       ('Компьютер', '1547'),
       ('Ноутбук', '5800'),
       ('Колонки', '600'),
       ('Клавиатура', '1000'),
       ('Мышь', '1200'),
       ('Коврик', '50'),
       ('Микрофон', '600');

INSERT INTO `spring_shop`.`purchases` (`customer`, `product`, `purchase_price`)
VALUES ('1', '1', '1000'),
       ('1', '2', '900'),
       ('2', '2', '2000');

DROP TABLE IF EXISTS `spring_shop`.`users`;
CREATE TABLE `spring_shop`.`users`
(
    `id`         INT         NOT NULL AUTO_INCREMENT,
    `username`   varchar(50) NOT NULL,
    `password`   varchar(80) NOT NULL,
    `first_name` VARCHAR(50) NOT NULL,
    `last_name`  VARCHAR(50) NOT NULL,
    `email`      VARCHAR(50) NOT NULL,
    `phone`      VARCHAR(15) NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS `spring_shop`.`roles`;
CREATE TABLE `spring_shop`.`roles`
(
    `id`   INT         NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS users_roles;
CREATE TABLE users_roles
(
    `user_id` INT NOT NULL,
    `role_id` INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id)
        REFERENCES users (id),
    FOREIGN KEY (role_id)
        REFERENCES roles (id)
);

INSERT INTO roles (name)
VALUES ('ROLE_USER'),
       ('ROLE_MANAGER'),
       ('ROLE_ADMIN');

INSERT INTO users (username, password, first_name, last_name, email, phone)
VALUES ('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'Admin', 'Admin', 'admin@gmail.com',
        '+79881111111');

INSERT INTO users_roles (user_id, role_id)
VALUES (1, 1),
       (1, 2),
       (1, 3);