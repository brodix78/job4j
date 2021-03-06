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
        return users.keySet().stream().filter(u -> u.getPassport().equals(passport)).
                reduce(null, (s, n) -> n);
    }

    public Account findByRequisite(String passport, String requisite) {
        Account account = null;
        User user = this.findByPassport(passport);
        if (user != null) {
            account = users.get(user).stream().filter(req -> req.getRequisite().equals(requisite)).
                    reduce(null, (s, n) -> n);
        }
        return account;
    }

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean conf = false;
        User sender = findByPassport(srcPassport);
        User recipient = findByPassport(destPassport);
        if (sender != null && recipient != null) {
            int srcAccount = users.get(sender).indexOf(findByRequisite(srcPassport, srcRequisite));
            int destAccount = users.get(recipient).indexOf(findByRequisite(destPassport, destRequisite));
            if (srcAccount != -1 && destAccount != -1) {
                if (users.get(sender).get(srcAccount).getBalance() >= amount) {
                    double balance = users.get(sender).get(srcAccount).getBalance() - amount;
                    users.get(sender).get(srcAccount).setBalance(balance);
                    balance = users.get(recipient).get(destAccount).getBalance() + amount;
                    users.get(recipient).get(destAccount).setBalance(balance);
                    conf = true;
                }
            }
        }
        return conf;
    }
}
