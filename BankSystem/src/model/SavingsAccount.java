package model;

public class SavingsAccount extends Account {

    public SavingsAccount(String acc, double bal) {
        super(acc, bal);
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}
