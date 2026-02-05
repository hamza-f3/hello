package service;

import db.DBConnection;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Bank {

    // LOGIN METHOD
    // LOGIN METHOD
    public Customer login(String acc, String pin) {

        // ERROR FIX: Changed 'username' to 'account_number' and 'password' to 'pin'
        String sql = "SELECT * FROM customers WHERE account_number = ? AND pin = ?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, acc);
            ps.setString(2, pin);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Customer(
                        rs.getString("name"),
                        rs.getString("customer_id"),
                        rs.getString("pin"),           // Fix: Fetch the actual PIN column
                        rs.getString("account_number") // Fix: Fetch the actual account_number
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // GET BALANCE
    public double getBalance(String acc) {

        String sql = """
            SELECT balance
            FROM public.accounts
            WHERE account_number = ?
        """;

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, acc);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble("balance");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    // DEPOSIT
    public void deposit(String acc, double amount) {

        String updateSql = """
            UPDATE accounts
            SET balance = balance + ?
            WHERE account_number = ?
        """;

        String insertSql = """
            INSERT INTO transactions
            (account_number, transaction_type, amount)
            VALUES (?, 'DEPOSIT', ?)
        """;

        try (Connection con = DBConnection.getConnection()) {

            PreparedStatement ps1 = con.prepareStatement(updateSql);
            ps1.setDouble(1, amount);
            ps1.setString(2, acc);
            ps1.executeUpdate();

            PreparedStatement ps2 = con.prepareStatement(insertSql);
            ps2.setString(1, acc);
            ps2.setDouble(2, amount);
            ps2.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // WITHDRAW
    public boolean withdraw(String acc, double amount) {

        double balance = getBalance(acc);
        if (amount > balance) return false;

        String updateSql = """
            UPDATE accounts
            SET balance = balance - ?
            WHERE account_number = ?
        """;

        String insertSql = """
            INSERT INTO transactions
            (account_number, transaction_type, amount)
            VALUES (?, 'WITHDRAW', ?)
        """;

        try (Connection con = DBConnection.getConnection()) {

            PreparedStatement ps1 = con.prepareStatement(updateSql);
            ps1.setDouble(1, amount);
            ps1.setString(2, acc);
            ps1.executeUpdate();

            PreparedStatement ps2 = con.prepareStatement(insertSql);
            ps2.setString(1, acc);
            ps2.setDouble(2, amount);
            ps2.executeUpdate();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
