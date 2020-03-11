Нужно написать SQL скрипты:

Создать структур данных в базе.
Таблицы.
   Кузов. Двигатель, Коробка передач.
Создать структуру Машина. Машина не может существовать без данных из п.1.
Заполнить таблицы через insert.
Создать SQL запросы:

1. Вывести список всех машин и все привязанные к ним детали.

2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.


CREATE TABLE bodies (
    id SERIAL PRIMARY KEY,
    model VARCHAR(30),
    type VARCHAR(30)
);
CREATE TABLE engines (
    id SERIAL PRIMARY KEY,
    model VARCHAR(30),
    type VARCHAR(30)
);
CREATE TABLE transmissions (
    id SERIAL PRIMARY KEY,
    model VARCHAR(30),
    type VARCHAR(30)
);
CREATE TABLE cars (
    id SERIAL PRIMARY KEY,
    model VARCHAR(30),
    body_id INT REFERENCES bodies(id),
    engine_id INT REFERENCES engines(id),
    transmission_id INT REFERENCES transmissions(id),
);

INSERT INTO bodies(model, type) VALUES ("c52", "sedan");
INSERT INTO bodies(model, type) VALUES ("c54", "coupe");
INSERT INTO bodies(model, type) VALUES ("c60", "suv");
INSERT INTO bodies(model, type) VALUES ("a72", "pickup");

INSERT INTO engines(model, type) VALUES ("S16I4", "petrol");
INSERT INTO engines(model, type) VALUES ("S24I4", "petrol");
INSERT INTO engines(model, type) VALUES ("S30V6", "petrol");
INSERT INTO engines(model, type) VALUES ("D40V6", "diesel");

INSERT INTO transmissions(model, type) VALUES ("MT6V2", "manual");
INSERT INTO transmissions(model, type) VALUES ("MT7SV3", "manual");
INSERT INTO transmissions(model, type) VALUES ("AT6V1", "automatic");
INSERT INTO transmissions(model, type) VALUES ("AT6FV2", "automatic");

INSERT INTO cars(model, body_id, engine_id, transmission_id) VALUES ("Simple Sedan", 1, 1, 1);
INSERT INTO cars(model, body_id, engine_id, transmission_id) VALUES ("Simple Coupe", 2, 2, 2);
INSERT INTO cars(model, body_id, engine_id, transmission_id) VALUES ("Simple Sedan", 3, 3, 3);

SELECT c.id, c.model, b.model, e.model, t.model FROM cars AS c
LEFT OUTER JOIN bodies AS b ON c.body_id = b.id,
                engines AS e ON c.engine_id = e.id,
                transmissions AS t ON t.transmissions = t.id;

SELECT b.model FROM bodies AS b
LEFT OUTER JOIN cars AS c ON b.id = c.body_id
WHERE c.id IS NULL;

SELECT e.model FROM engines AS e
LEFT OUTER JOIN cars AS c ON e.id = c.engine_id
WHERE c.id IS NULL;

SELECT t.model FROM transmissions AS t
LEFT OUTER JOIN cars AS c ON t.id = c.transmission_id
WHERE c.id IS NULL;