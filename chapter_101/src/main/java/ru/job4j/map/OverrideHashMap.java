package ru.job4j.map;

import java.util.*;

public class OverrideHashMap {

    static class UserHash extends User {
        public UserHash(String name, Calendar birthDate) {
            super(name, birthDate);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.getName() != null ? this.getName() : 0,
                    this.getBirthDate() != null ? this.getName() : 0,
                    this.getChildren());
        }
    }

    static User user1 = new UserHash("Alexander", new GregorianCalendar(1990, Calendar.JANUARY, 11));
    static User user2 = new UserHash("Alexander", new GregorianCalendar(1990, Calendar.JANUARY, 11));

    public static void main(String[] args) {
        Map<User, String> map = new HashMap<>();
        map.put(user1, "worker");
        map.put(user2, "teacher");
        System.out.println(map);
    }

    /* Вывод map на экран говорит о наличии в ней двух объектов с одинаковыми hash кодами,
     * поскольку их генерация выполнена переопределенным методом, однако сравнение
     * на интетичность выполняется дефолтным equals, по этой причине map воспринимает данные объекты
     * не как идентичные
     */

}
