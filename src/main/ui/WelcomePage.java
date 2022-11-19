package ui;

import model.ShoeInventory;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;


public class WelcomePage extends JFrame {

    private JFrame startWindow;
    private Container container;
    private JButton create;
    private JButton load;
    private JButton exit;
    private static final String JSON_STORE = "./data/shoeInventory.json";
    private JsonReader jsonReader;
    protected static ShoeInventory shoeCollection;

    public WelcomePage() {
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        // creating the Welcome Page window JFrame and setting it up
        startWindow = new JFrame("My Shoe Collection");
        startWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startWindow.setSize(1200,840);

        // Display the window
        startWindow.setVisible(true);

        // Display formatting on the page
        formattingPage();

    }


    public void formattingPage() {
        startWindow.getContentPane().setBackground(new Color(0XFFFDD0));
        container = startWindow.getContentPane();
        container.setLayout(null);

        formatWelcomeLabel();
        addButtons();
    }

    public void formatWelcomeLabel() {
        JLabel shoesWelcomeLabel = new JLabel();
        shoesWelcomeLabel.setBounds(350,60,600,400);
        shoesWelcomeLabel.setText("My Shoe Collection");
        shoesWelcomeLabel.setIcon(new ImageIcon("./images/sneakerCollection.png"));
        shoesWelcomeLabel.setHorizontalTextPosition(JLabel.CENTER);
        shoesWelcomeLabel.setVerticalTextPosition(JLabel.BOTTOM);
        shoesWelcomeLabel.setIconTextGap(6);
        shoesWelcomeLabel.setFont(new Font("Gill Sans", Font.PLAIN, 60));
        container.add(shoesWelcomeLabel);
    }

    public void addButtons() {
        create = new JButton("Create A New Collection");
        load = new JButton("Load My Shoe Collection");
        exit = new JButton("Exit");
        formatButtons(create);
        formatButtons(load);
        formatButtons(exit);
        create.setBounds(440,450,300,75);
        load.setBounds(440,550,300,75);
        exit.setBounds(440,650,300,75);
        buttonHoverEffect(create);
        buttonHoverEffect(load);
        buttonHoverEffect(exit);
        createButtonFunction();
        loadButtonFunction();
        exitButtonFunction();
        container.add(create);
        container.add(load);
        container.add(exit);
    }

    public void formatButtons(JButton button) {
        button.setOpaque(true);
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public void buttonHoverEffect(JButton button) {
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setContentAreaFilled(true);
                button.setBackground(Color.BLACK);
                button.setForeground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setContentAreaFilled(false);
                button.setForeground(Color.BLACK);
                button.setBackground(UIManager.getColor("control"));
            }
        });

    }

    public void createButtonFunction() {
        create.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                startWindow.setVisible(false);
                startWindow.dispose();
                shoeCollection = new ShoeInventory();
                new MenuPage();
            }
        });
    }

    public void loadButtonFunction() {
        load.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(startWindow, "Loaded data successfully!");
                shoeCollection = new ShoeInventory();
                jsonReader = new JsonReader(JSON_STORE);
                try {
                    shoeCollection = jsonReader.read();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(startWindow, "Unable to load data!");
                }
                startWindow.setVisible(false);
                startWindow.dispose();
                new MenuPage();
            }
        });
    }

    public void exitButtonFunction() {
        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }




//    public static void main(String[] args) {
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new WelcomePage();
//            }
//        });
//    }

}
