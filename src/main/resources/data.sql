INSERT INTO categories VALUES (nextval('categories_id_seq'), 'Salad', 'Салат');
INSERT INTO categories VALUES (nextval('categories_id_seq'), 'Pasta', 'Паста');
INSERT INTO categories VALUES (nextval('categories_id_seq'), 'Grill', 'Гриль');
INSERT INTO categories VALUES (nextval('categories_id_seq'), 'Sweets', 'Солодощі');

INSERT INTO dishes VALUES (nextval('dishes_id_seq'), 'Pasta', 'Паста', 12.99, current_timestamp, 2);
INSERT INTO dishes VALUES (nextval('dishes_id_seq'), 'Salad Mix', 'Салат Мікс', 9.99, current_timestamp, 1);
INSERT INTO dishes VALUES (nextval('dishes_id_seq'), 'Meat', 'Мясо', 16.99, current_timestamp, 3);
INSERT INTO dishes VALUES (nextval('dishes_id_seq'), 'Ribs', 'Реберця', 12.99, current_timestamp, 3);
INSERT INTO dishes VALUES (nextval('dishes_id_seq'), 'Cake', 'Торт', 12.99, current_timestamp, 4);
INSERT INTO dishes VALUES (nextval('dishes_id_seq'), 'Cookies', 'Печиво', 12.99, current_timestamp, 4);
INSERT INTO dishes VALUES (nextval('dishes_id_seq'), 'Cupcake', 'Маффін', 12.99, current_timestamp, 4);
INSERT INTO dishes VALUES (nextval('dishes_id_seq'), 'Salad Cesar', 'Салат Цезар', 12.99, current_timestamp, 1);
INSERT INTO dishes VALUES (nextval('dishes_id_seq'), 'Ravioli', 'Равіолі', 12.99, current_timestamp, 2);
INSERT INTO dishes VALUES (nextval('dishes_id_seq'), 'Steak', 'Стейк', 12.99, current_timestamp, 3);
INSERT INTO dishes VALUES (nextval('dishes_id_seq'), 'Salad Fresh', 'Салат Свіжий', 12.99, current_timestamp, 1);
INSERT INTO dishes VALUES (nextval('dishes_id_seq'), 'Hot dog', 'Хот дог', 12.99, current_timestamp, 2);
