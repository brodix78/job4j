package ru.job4j.exam;

import java.util.HashMap;

import java.util.List;
import java.util.stream.Collectors;

public class AnalizeMap {

    public Info diff(List<User> previous, List<User> current) {
        int changed = 0;
        int deleted = 0;
      //  HashMap<Integer, String> cur = new HashMap<>(
      //          current.stream().collect(Collectors.toMap(i -> i.id, i -> i.name)));
        HashMap<Integer, String> cur = new HashMap<>();
        for (User user : current) {
            cur.put(user.id, user.name);
        }
        for (User user : previous) {
            String rsl = cur.putIfAbsent(user.id, user.name);
            if (rsl == null) {
                deleted++;
            } else if (!rsl.equals(user.name)) {
                changed++;
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