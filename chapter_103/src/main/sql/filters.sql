В системе заданы таблицы
product(id, name, type_id, expired_date, price)
type(id, name)
Задание.

1. Написать запрос получение всех продуктов с типом "СЫР"

SELECT p.name FROM product AS p
INNER JOIN type AS t ON p.type_id = t.id
where t.name = 'СЫР';

2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"

SELECT name FROM product
WHERE name LIKE '%мороженое%';

3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.

SELECT name, expired_date FROM product
WHERE MONTH(expired_date) = MONTH(CURRENT_DATE()) + 1;

4. Написать запрос, который выводит самый дорогой продукт.
SELECT p.name, p.price FROM product
WHERE price = (SELECT MAX(price) FROM product);

5. Написать запрос, который выводит количество всех продуктов определенного типа.

SELECT COUNT(*) AS 'numbers of products type СЫР' FROM p.products AS p
INNER JOIN type AS t ON p.type_id = t.id
WHERE t.name = 'СЫР';

6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"

SELECT p.name FROM products AS p
INNER JOIN type AS t ON p.type_id = t.id
WHERE t.name IN ('СЫР', 'МОЛОКО');

7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.

SELECT t.name FROM type AS t, product AS p
WHERE (SELECT COUNT(*) FROM p WHERE p.type_id) = t.id < 10;

8. Вывести все продукты и их тип.

SELECT p.product, t.name FROM products AS p
INNER JOIN type AS t on p.type_id = t.id;
