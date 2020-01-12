package ru.job4j.collection;

import java.util.HashSet;
import java.util.List;

public class NotifyAccount {
    public static HashSet<Account> sent(List<Account> accounts) {
        HashSet<Account> sendList = new HashSet<>();
        for (Account account : accounts) {
            sendList.add(account);
        }
        return sendList;
    }
}