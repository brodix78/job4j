package ru.job4j.bank;

import java.util.*;

public class BankService {
    private Map<User, List<Account>> users = new HashMap<>();

    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<Account>());
    }

    public void addAccount(String passport, Account account) {
        User user = this.findByPassport(passport);
        if (user != null) {
            users.get(user).add(account);
        }
    }

    public User findByPassport(String passport) {
        User user = null;
        for (User person : users.keySet()) {
            if (person.getPassport().equals(passport)) {
                user = person;
                break;
            }
        }
        return user;
    }

    public Account findByRequisite(String passport, String requisite) {
        Account account = null;
        User user = this.findByPassport(passport);
        if (user != null){
            for (Account accountInBank : users.get(user)) {
                if (accountInBank.getRequisite().equals(requisite)) {
                    account = accountInBank;
                    break;
                }
            }
        }
        return account;
    }

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean conf = false;
        User sender = findByPassport(srcPassport);
        User recipient = findByPassport(destPassport);
        if (sender != null && recipient != null) {
            int srcAccount = users.get(sender).indexOf(findByRequisite(srcPassport,srcRequisite));
            int destAccount = users.get(recipient).indexOf(findByRequisite(destPassport, destRequisite));
            if (srcAccount != -1 && destAccount != -1) {
                if (users.get(sender).get(srcAccount).getBalance() >= amount) {
                    double Balance = users.get(sender).get(srcAccount).getBalance() - amount;
                    users.get(sender).get(srcAccount).setBalance(Balance);
                    Balance = users.get(recipient).get(destAccount).getBalance() + amount;
                    users.get(recipient).get(destAccount).setBalance(Balance);
                    conf = true;
                }
            }
        }
        return conf;
    }
}
