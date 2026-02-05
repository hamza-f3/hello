package model;



public abstract class Account {
    protected String accountNumber;
    protected double balance;

    public Account(String acc, double bal) {
        accountNumber = acc;
        balance = bal;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public abstract boolean withdraw(double amount);
}
