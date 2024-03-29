DROP DATABASE IF EXISTS `spring_shop`;
CREATE SCHEMA `spring_shop` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;

USE `spring_shop`;

CREATE TABLE `spring_shop`.`customers`
(
    `id`   INT(8)      NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NULL,
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE
);

CREATE TABLE `spring_shop`.`products`
(
    `id`    INT(8)      NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(45) NULL,
    `cost`  INT         NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    UNIQUE INDEX `title_UNIQUE` (`title` ASC) VISIBLE
);

CREATE TABLE `spring_shop`.`purchases`
(
    `id`             INT(8)  NOT NULL AUTO_INCREMENT,
    `customer`       INT(8)  NOT NULL,
    `product`        INT(8)  NOT NULL,
    `purchase_price` DECIMAL NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE
);

ALTER TABLE `spring_shop`.`purchases`
    ADD INDEX `customer_id_idx` (`customer` ASC) VISIBLE,
    ADD INDEX `product_id_idx` (`product` ASC) VISIBLE;
;
ALTER TABLE `spring_shop`.`purchases`
    ADD CONSTRAINT `customer_id`
        FOREIGN KEY (`customer`)
            REFERENCES `spring_shop`.`customers` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    ADD CONSTRAINT `product_id`
        FOREIGN KEY (`product`)
            REFERENCES `spring_shop`.`products` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

INSERT INTO `spring_shop`.`customers` (`name`)
VALUES ('Арнольд'),
       ('Иван'),
       ('Мария'),
       ('Сергей'),
       ('Татьяна'),
       ('Ольга'),
       ('Тарас'),
       ('Гиви');

INSERT INTO `spring_shop`.`products` (`title`, `cost`)
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
