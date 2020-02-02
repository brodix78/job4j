package ru.job4j.map;

import java.util.*;

public class OverrideEqualsMap {

    static class UserEquals extends User {
        public UserEquals(String name, Calendar birthDate) {
            super(name, birthDate);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return this.getName().equals(user.getName())
                    && this.getBirthDate().equals(user.getBirthDate())
                    && this.getChildren() == user.getChildren();
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }
    }

    static User user1 = new UserEquals("Alexander", new GregorianCalendar(1990, Calendar.JANUARY, 11));
    static User user2 = new UserEquals("Alexander", new GregorianCalendar(1990, Calendar.JANUARY, 11));

    public static void main(String[] args) {
        Map<User, String> map = new HashMap<>();
        map.put(user1, "worker");
        map.put(user2, "teacher");
        System.out.println(user1.equals(user2));
        System.out.println(map);
    }

    /* Вывод map на экран говорит о наличии в ней двух объектов с различными hash кодами,
     * поскольку их генерация выполнена методом по умолчанию, однако сравнение
     * на интетичность выполняется переопределенным equals (вывод на экран System.out.println(user1.equals(user2))
     * дает true), по этой причине map воспринимает данные объекты не как идентичные.
     */

}