package ui;

import javax.swing.*;
import java.awt.*;

public class Style {

    // 1. SUCCESS MESSAGE (Green Title)
    public static void showSuccess(Component parent, String message) {
        // We use HTML to style the text inside the box easily
        String htmlMessage = "<html><body style='width: 250px; text-align: center; color: white;'>"
                + "<h2 style='color: #2ecc71;'>Success! \u2714</h2>" // Green Checkmark
                + "<p>" + message + "</p>"
                + "</body></html>";

        JOptionPane.showMessageDialog(parent, htmlMessage, "Success", JOptionPane.PLAIN_MESSAGE);
    }

    // 2. ERROR MESSAGE (Red Title)
    public static void showError(Component parent, String message) {
        String htmlMessage = "<html><body style='width: 250px; text-align: center; color: white;'>"
                + "<h2 style='color: #e74c3c;'>Error \u274C</h2>" // Red X
                + "<p>" + message + "</p>"
                + "</body></html>";

        JOptionPane.showMessageDialog(parent, htmlMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // 3. INPUT DIALOG (Clean Look)
    public static String showInput(Component parent, String message) {
        // Customizing the input text field color is tricky in standard JOptionPane,
        // so we create a small custom panel.
        JPanel panel = new JPanel();
        panel.setOpaque(false); // Use the global dark blue background

        JLabel label = new JLabel(message);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JTextField field = new JTextField(10);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        panel.add(label);
        panel.add(field);

        int result = JOptionPane.showConfirmDialog(parent, panel, "Input Required",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            return field.getText();
        }
        return null;
    }
}