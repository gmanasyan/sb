DELETE FROM users;
DELETE FROM items;
DELETE FROM purchases;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, lastname, age, email, password) VALUES
('Иван', 'Иванов', 32, 'ivan@gmail.com', '{noop}password'),
('Алексей', 'Алексеев', 21, 'alex@gmail.com', '{noop}password'),
('Константин', 'Константинов', 45, 'konst@gmail.com', '{noop}password');

INSERT INTO items (name, cost) VALUES
('Телевизор',  1565020),
('Смартфон',  870000),
('Соковыжималка',  325010),
('Наушники',  239999),
('Клавиатура',  120010);

INSERT INTO purchases (user_id, item_id, count, date) VALUES
(100000, 100003, 1, '2019-12-1'),
(100000, 100004, 1, '2019-12-2'),
(100001, 100005, 1, '2019-12-2'),
(100002, 100006, 2, '2019-12-3'),
(100001, 100007, 3, '2019-12-3'),
(100002, 100005, 1, '2019-12-4');
