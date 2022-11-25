-- 1.a. Frame insert scripts to add data into menu_item table.
-- First of all, let's insert the necessary rows data into categories table
INSERT INTO categories(name, description)
VALUES
    ('Main Course', 'This category should be associated to only main courses menu items'),
    ('Starters', 'This category should be associated to only starters menu items'),
    ('Dessert', 'This category should be associated to only dessert menu items');

INSERT INTO menu_items(name, price, active, date_of_launch, category_id, free_delivery)
VALUES
    ('Sandwich', 99.00, true, '2017-03-15', 1, true),
    ('Burger', 129.00, true, '2017-12-23', 1, false),
    ('Pizza', 149.00, true, '2017-08-21', 1, false),
    ('French Fries', 57.00, false, '2017-07-02', 2, true),
    ('Chocolate Brownie', 32.00, true, '2022-11-02', 3, true);

-- 1.b. Frame SQL query to get all menu items
-- Bellow I'm using LEFT JOIN to make sure all menu items are retrieved... Even the ones with null category_id
SELECT mi.name,
       mi.price,
       mi.active,
       mi.date_of_launch,
       c.name as category,
       mi.free_delivery
FROM menu_items mi
LEFT JOIN categories c on mi.category_id = c.id;

-- 2.a. Frame SQL query to get all menu items which after launch date and is active.
SELECT mi.name,
       mi.price,
       mi.active,
       mi.date_of_launch,
       c.name as category,
       mi.free_delivery
FROM menu_items mi
LEFT JOIN categories c on mi.category_id = c.id
WHERE mi.active = 1 AND current_date > mi.date_of_launch;

-- 3.a. Frame SQL query to get a menu items based on Menu Item Id
-- Example of selecting item with id 1
SELECT mi.name,
       mi.price,
       mi.active,
       mi.date_of_launch,
       c.name as category,
       mi.free_delivery
FROM menu_items mi
 LEFT JOIN categories c on mi.category_id = c.id
WHERE mi.id = 1;

-- 3.b. Frame update SQL menu_items table to update all the columns values based on Menu Item Id
-- Example of updating item with id 1
UPDATE menu_items
SET name = 'foo',
    price = 99.99,
    active = 0,
    date_of_launch = '2022-11-11',
    category_id = 2,
    free_delivery = false
WHERE id = 1;

-- 4.a. Frame insert scripts for adding data into user and cart tables.In user table create two users. Once user will not have any entries in cart, while the other will have at least 3 items in the cart.
-- Insert into users table
INSERT INTO users(first_name, last_name, gender, birth_day, email, phone_no)
VALUES
    ('Jon', 'Doe', 'M', '1990-11-15', 'jondoe@gmail.com', '7234561890'),
    ('Jessica','Smith', 'F', '1995-07-30', 'jessicasmith@outlook.com', '9034561872');


INSERT INTO carts(item_id, user_id)
VALUES
    (2, 2),
    (3, 2),
    (4, 2);

-- 5.a. Frame SQL query to get all menu items in a particular user’s cart
-- All menu items in Jessica Smith's cart (id=2)

SELECT mi.name,
       mi.price,
       mi.active,
       mi.date_of_launch,
       c1.name as category,
       mi.free_delivery
FROM menu_items mi
LEFT JOIN categories c1 on mi.category_id = c1.id
INNER JOIN carts c2 on mi.id = c2.item_id
INNER JOIN users u on c2.user_id = u.id
WHERE u.id = 2;

-- 5.b. Frame SQL query to get the total price of all menu items in a particular user’s cart
-- Total price of all menu items in Jessica Smith's cart (id=2)
SELECT SUM(i.price) AS TOTAL_PRICE
FROM carts c
INNER JOIN menu_items i on c.item_id = i.id
WHERE user_id = 2;

-- 6.a. Frame SQL query to remove a menu items from Cart based on User Id and Menu Item Id
-- Delete French Fries from Jessica Smith's cart (id=2)
DELETE FROM carts
WHERE user_id = 2 AND item_id = 4;