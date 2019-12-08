DELETE FROM users;
DELETE FROM items;
DELETE FROM purchases;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, lastname, age) VALUES
('Иван', 'Иванов', 32),
('Алексей', 'Алексеев', 21),
('Константин', 'Константинов', 45);

INSERT INTO items (name) VALUES
('Tv'),
('Smartphone'),
('Juicer'),
('Headphone'),
('Keyboard');

INSERT INTO purchases (user_id, item_id, count, amount, date) VALUES
(100000, 100003, 1, 1160000, '2019-12-1'),
(100000, 100004, 1, 2340000, '2019-12-2'),
(100001, 100005, 1, 410000, '2019-12-2'),
(100002, 100006, 2, 780000, '2019-12-3'),
(100001, 100007, 3, 120000, '2019-12-3'),
(100002, 100005, 1, 560000, '2019-12-4');
