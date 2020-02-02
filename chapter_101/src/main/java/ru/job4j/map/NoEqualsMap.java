package ru.job4j.map;

import java.util.GregorianCalendar;
import java.util.Map;

public class NoEqualsMap {
    static User user1 = new User("Alexander", new GregorianCalendar(1990, 0, 11));
    static User user2 = new User("Alexander", new GregorianCalendar(1990, 0, 11));

    public static void main(String[] args) {
        Map<User, String> map = Map.of(user1, "worker",
                user2, "worker");
        System.out.println(map);
    }

    /* Вывод map на экран говорит о наличии в ней двух объектов с различными hash кодами,
     * поскольку их генерация выполняется стандартным методом, как и сравнение на интетичность equals
     */
}
