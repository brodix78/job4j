package ru.job4j.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class NoOverrideMap {

        static User user1 = new User("Alexander", new GregorianCalendar(1990, Calendar.JANUARY, 11));
        static User user2 = new User("Alexander", new GregorianCalendar(1990, Calendar.JANUARY, 11));

        public static void main(String[] args) {
            Map<User, String> map = new HashMap<>();
            map.put(user1, "worker");
            map.put(user2, "teacher");
            System.out.println(map);
        }

        /* Вывод map на экран говорит о наличии в ней двух объектов с различными hash кодами,
         * поскольку их генерация выполняется стандартным методом, как и сравнение на интетичность equals
         */
    }
