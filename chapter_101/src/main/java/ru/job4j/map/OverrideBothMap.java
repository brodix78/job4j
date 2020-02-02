package ru.job4j.map;

import java.util.*;

public class OverrideBothMap {

    static class UserOver extends User {
        public UserOver(String name, Calendar birthDate) {
            super(name, birthDate);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.getName() != null ? this.getName() : 0,
                    this.getBirthDate() != null ? this.getName() : 0,
                    this.getChildren());
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || this.getClass() != obj.getClass()) {
                return false;
            }
            UserOver user = (UserOver) obj;
            return this.getName().equals(user.getName())
                    && this.getBirthDate().equals(user.getBirthDate())
                    && this.getChildren() == user.getChildren()
                    && this.hashCode() == user.hashCode();
        }
    }

    static User user1 = new UserOver("Alexander", new GregorianCalendar(1990, Calendar.JANUARY, 11));
    static User user2 = new UserOver("Alexander", new GregorianCalendar(1990, Calendar.JANUARY, 11));

    public static void main(String[] args) {
        Map<User, String> map = new HashMap<>();
        map.put(user1, "worker");
        map.put(user2, "teacher");
        System.out.println(map);
    }

    /* Вывод map на экран говорит о наличии в ней одного объекта user2, что говорит о восприятии map
     * объектов user1 и user2 как интентичных, что в свою очередь привело к замене user1 на user2.
     * Это достигнуто за счет преопределения обих методов hashCode и equals (в данном случае для класса
     * UserOver, наследующего User).
     */
}
