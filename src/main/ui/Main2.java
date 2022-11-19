package ui;

import javax.swing.*;

public class Main2 extends JFrame {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WelcomePage();
            }
        });
    }
}


