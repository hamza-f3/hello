import ui.LoginFrame;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        // --- 1. DEFINE YOUR PALETTE ---
        Color darkBlue = new Color(44, 62, 80);
        Color brightGreen = new Color(46, 204, 113);
        Color white = Color.WHITE;
        Font mainFont = new Font("Segoe UI", Font.BOLD, 16);

        // --- 2. APPLY TO EVERYTHING ---
        // Change the background of the dialogs
        UIManager.put("OptionPane.background", new ColorUIResource(darkBlue));
        UIManager.put("Panel.background", new ColorUIResource(darkBlue));

        // Change the text color
        UIManager.put("OptionPane.messageForeground", white);
        UIManager.put("Label.foreground", white);
        UIManager.put("OptionPane.messageFont", mainFont);

        // Change the buttons in the dialogs
        UIManager.put("Button.background", brightGreen);
        UIManager.put("Button.foreground", white);
        UIManager.put("Button.font", mainFont);
        UIManager.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0))); // Remove focus line

        // Add a nice border around the popup
        UIManager.put("OptionPane.border", new LineBorder(brightGreen, 2));

        // Start the App
        new LoginFrame();
    }
}