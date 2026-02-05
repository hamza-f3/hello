package ui;

import service.Bank;
import model.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame implements ActionListener {

    JTextField accField;
    JPasswordField pinField;
    JButton loginBtn, exitBtn;
    Bank bank = new Bank();

    public LoginFrame() {
        setTitle("Bank Management System - Login");

        // 1. MAKE FULL SCREEN
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // 2. SET BACKGROUND COLOR
        getContentPane().setBackground(new Color(44, 62, 80)); // Dark Blue
        setLayout(new GridBagLayout()); // Centers the content

        // Create a specific panel for the form so it looks like a card
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4, 2, 10, 10)); // 4 rows, 2 cols, gap of 10
        formPanel.setBackground(new Color(52, 73, 94)); // Slightly lighter blue
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Styling Fonts
        Font font = new Font("Arial", Font.BOLD, 18);

        JLabel userLabel = new JLabel("Account Number:");
        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(font);

        JLabel pinLabel = new JLabel("PIN:");
        pinLabel.setForeground(Color.WHITE);
        pinLabel.setFont(font);

        accField = new JTextField(15);
        accField.setFont(font);

        pinField = new JPasswordField(15);
        pinField.setFont(font);

        loginBtn = new JButton("Login");
        loginBtn.setBackground(new Color(46, 204, 113)); // Green
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFont(font);
        loginBtn.addActionListener(this);

        exitBtn = new JButton("Exit");
        exitBtn.setBackground(new Color(231, 76, 60)); // Red
        exitBtn.setForeground(Color.WHITE);
        exitBtn.setFont(font);
        exitBtn.addActionListener(this);

        // Add components to the panel
        formPanel.add(userLabel);
        formPanel.add(accField);
        formPanel.add(pinLabel);
        formPanel.add(pinField);
        formPanel.add(new JLabel("")); // Empty placeholder
        formPanel.add(new JLabel("")); // Empty placeholder
        formPanel.add(loginBtn);
        formPanel.add(exitBtn);

        add(formPanel); // Add the form panel to the center of the screen
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitBtn) {
            System.exit(0);
        }

        if (e.getSource() == loginBtn) {
            try {
                Customer c = bank.login(
                        accField.getText(),
                        new String(pinField.getPassword())
                );

                if (c != null) {
                    new DashboardFrame(bank, c);
                    dispose(); // Close login window
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Login");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}