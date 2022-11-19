package ui;

import javax.swing.*;

public class MainGUI extends JFrame {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WelcomePage();
            }
        });
    }
}


