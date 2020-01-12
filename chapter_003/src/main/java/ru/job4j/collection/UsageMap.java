package ru.job4j.collection;

import java.util.HashMap;
import java.util.HashSet;

public class UsageMap {
    public static void main(String[] args) {
        HashMap<String , String> map = new HashMap<>();
        map.put("ily78@inbox.ru", "Ilya Brodnikov");
        for (String key : map.keySet()) {
            System.out.println(map.get(key));
        }
    }
}
