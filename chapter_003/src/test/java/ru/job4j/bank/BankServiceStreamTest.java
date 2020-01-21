package ru.job4j.bank;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BankServiceStreamTest {

    @Test
    public void addUser() {
        User user = new User("3434", "Petr Arsentev");
        User user1 = new User("3434", "P. Antonov");
        BankServiceStream bank = new BankServiceStream();
        bank.addUser(user);
        bank.addUser(user1);
        assertThat(bank.findByPassport("3434"), is(user));
    }

    @Test
    public void addAccount() {
        User user = new User("3434", "Petr Arsentev");
        BankServiceStream bank = new BankServiceStream();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("5546", 150));
        assertThat(bank.findByRequisite("3434", "5546").getBalance(), is((double) 150));
    }

    @Test
    public void transferMoney() {
        User user = new User("3434", "Petr Arsentev");
        BankServiceStream bank = new BankServiceStream();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("5546", 150));
        bank.addAccount(user.getPassport(), new Account("113", 50));
        bank.transferMoney(user.getPassport(), "5546", user.getPassport(), "113", 150);
        assertThat(bank.findByRequisite("3434", "113").getBalance(), is((double) 200));
    }
}