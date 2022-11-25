DROP DATABASE truYum;
CREATE DATABASE truYum;

USE truYum;

CREATE TABLE categories(
    id INT PRIMARY KEY auto_increment,
    name VARCHAR(20) UNIQUE,
    description VARCHAR(150)
);

CREATE TABLE menu_items(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL UNIQUE,
    price DECIMAL(5, 2) NOT NULL,
    active BOOLEAN DEFAULT FALSE,
    date_of_launch DATE,
    category_id INT,
    free_delivery BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE table users(
    id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    gender CHAR(1),
    birth_day DATE,
    email VARCHAR(30) UNIQUE,
    phone_no VARCHAR(15) NOT NULL UNIQUE
);

CREATE TABLE carts(
    item_id INT NOT NULL,
    user_id INT NOT NULL,
    PRIMARY KEY (item_id, user_id),
    FOREIGN KEY (item_id) REFERENCES menu_items(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);


CREATE table owners(
    id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    birth_day DATE,
    email VARCHAR(20) NOT NULL UNIQUE,
    phone_no INT(15) NOT NULL UNIQUE
);


DESCRIBE menu_items;
DESCRIBE users;
DESCRIBE carts;
DESCRIBE owners;