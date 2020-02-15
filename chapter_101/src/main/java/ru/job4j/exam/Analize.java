package ru.job4j.exam;

import java.util.List;
import java.util.Objects;

public class Analize {

    public Info diff(List<User> previous, List<User> current) {
        int changed = 0;
        int deleted = 0;
        for (User user : previous) {
            int index = current.indexOf(user);
            if (index != -1) {
                if (!current.get(index).name.equals(user.name)) {
                    changed++;
                }
            } else {
                deleted++;
            }
        }
        int added = current.size() - (previous.size() - deleted);
        return new Info(added, changed, deleted);
    }

    public static class User {
        public User(int id, String name) {
            this.name = name;
            this.id = id;
        }

        int id;
        String name;

        @Override
        public int hashCode() {
            return Objects.hash(id);
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
            return this.id == user.id;
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;

        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }
    }

}