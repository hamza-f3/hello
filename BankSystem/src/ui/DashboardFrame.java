package ui;

import service.Bank;
import model.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardFrame extends JFrame implements ActionListener {

    JButton balanceBtn, depositBtn, withdrawBtn, backBtn, exitBtn;
    Bank bank;
    Customer customer;

    public DashboardFrame(Bank bank, Customer customer) {
        this.bank = bank;
        this.customer = customer;

        setTitle("Bank Dashboard");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full Screen
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // --- 1. HEADER PANEL (Top) ---
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(44, 62, 80)); // Dark Blue
        headerPanel.setPreferredSize(new Dimension(100, 100));

        JLabel title = new JLabel("Welcome, " + customer.getName());
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        headerPanel.add(title);

        add(headerPanel, BorderLayout.NORTH);

        // --- 2. BUTTON PANEL (Center) ---
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3, 20, 20)); // 1 row, 3 cols
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        buttonPanel.setBackground(Color.WHITE);

        Font btnFont = new Font("Arial", Font.BOLD, 20);

        balanceBtn = createStyledButton("Check Balance", new Color(52, 152, 219), btnFont);
        depositBtn = createStyledButton("Deposit Money", new Color(46, 204, 113), btnFont);
        withdrawBtn = createStyledButton("Withdraw Money", new Color(241, 196, 15), btnFont);

        buttonPanel.add(balanceBtn);
        buttonPanel.add(depositBtn);
        buttonPanel.add(withdrawBtn);

        add(buttonPanel, BorderLayout.CENTER);

        // --- 3. FOOTER PANEL (Bottom - Navigation) ---
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(44, 62, 80));
        footerPanel.setPreferredSize(new Dimension(100, 80));

        backBtn = new JButton("Logout (Back)");
        backBtn.setFont(new Font("Arial", Font.BOLD, 16));
        backBtn.addActionListener(this);

        exitBtn = new JButton("Exit System");
        exitBtn.setBackground(new Color(231, 76, 60)); // Red
        exitBtn.setForeground(Color.WHITE);
        exitBtn.setFont(new Font("Arial", Font.BOLD, 16));
        exitBtn.addActionListener(this);

        footerPanel.add(backBtn);
        footerPanel.add(exitBtn);

        add(footerPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Helper method to make buttons look nice easily
    private JButton createStyledButton(String text, Color bg, Font font) {
        JButton btn = new JButton(text);
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFont(font);
        btn.setFocusPainted(false);
        btn.addActionListener(this);
        return btn;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            // NAVIGATION
            if (e.getSource() == exitBtn) {
                System.exit(0);
            }
            if (e.getSource() == backBtn) {
                new LoginFrame();
                dispose();
            }

            // BANKING ACTIONS
            if (e.getSource() == balanceBtn) {
                double bal = bank.getBalance(customer.getAccountNumber());
                // USE NEW STYLE
                Style.showSuccess(this, "Current Balance: $" + bal);
            }

            if (e.getSource() == depositBtn) {
                // USE NEW STYLE
                String amt = Style.showInput(this, "Enter amount to deposit:");

                if (amt != null && !amt.isEmpty()) {
                    bank.deposit(customer.getAccountNumber(), Double.parseDouble(amt));
                    Style.showSuccess(this, "Deposit Successful! ✅");
                }
            }

            if (e.getSource() == withdrawBtn) {
                // USE NEW STYLE
                String amt = Style.showInput(this, "Enter amount to withdraw:");

                if (amt != null && !amt.isEmpty()) {
                    boolean ok = bank.withdraw(customer.getAccountNumber(), Double.parseDouble(amt));
                    if (ok) {
                        Style.showSuccess(this, "Withdrawal Successful! 💸");
                    } else {
                        Style.showError(this, "Insufficient Funds! ❌");
                    }
                }
            }
        } catch (Exception ex) {
            Style.showError(this, "Invalid Input: " + ex.getMessage());
        }

    }

}