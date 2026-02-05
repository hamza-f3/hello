package model;

public class Customer extends Person {
    private String pin;
    private String accountNumber;

    public Customer(String name, String id, String pin, String acc) {
        super(name, id);
        this.pin = pin;
        this.accountNumber = acc;
    }

    public boolean checkPin(String p) {
        return pin.equals(p);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }
}
