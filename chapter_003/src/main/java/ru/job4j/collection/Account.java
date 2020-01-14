package ru.job4j.collection;

import java.util.Objects;

public class Account {
    private String passport;
    private String username;
    private String deposit;
    public Account(String passport, String username, String deposit) {
        this.passport = passport;
        this.username = username;
        this.deposit = deposit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(this.passport, account.passport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.passport);
    }

    @Override
    public String toString() {
        return "Account{" + "passport='" + this.passport + '\''
                + ", username='" + this.username + '\''
                + ", deposit='" + this.deposit + '\'' + '}';
    }
}