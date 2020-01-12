package ru.job4j.bank;

import java.util.*;

public class BankService {
    private Map<User, List<Account>> users = new HashMap<>();

    public void addUser(User user) {
        if (!users.containsKey(user)) {
            List<Account> accounts= new ArrayList<>();
            users.put(user, accounts);
        }
    }

    public void addAccount(String passport, Account account) {
        User user = this.findByPassport(passport);
        if (user != null) {
            List<Account> accounts = users.get(user);
            accounts.add(account);
            users.put(user, accounts);
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
            Account srcAccount = findByRequisite(srcPassport, srcRequisite);
            Account destAccount = findByRequisite(destPassport, destRequisite);
            if (srcAccount != null && destAccount != null) {
                if (srcAccount.getBalance() >= amount) {
                    this.changeBalance(sender, srcRequisite, -amount);
                    this.changeBalance(recipient, destRequisite, amount);
                    conf = true;
                }
            }
        }
        return conf;
    }

    private void changeBalance(User user, String requisite, double amount) {
        List<Account> accounts = users.get(user);
        int acc = accounts.indexOf(findByRequisite(user.getPassport(), requisite));
        double oldBalance = accounts.get(acc).getBalance();
        accounts.set(acc, new Account(requisite, oldBalance + amount));
        users.put(user, accounts);
    }
}
