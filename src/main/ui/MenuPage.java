package ui;

import model.Event;
import model.EventLog;
import model.Shoe;
import model.ShoeInventory;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class MenuPage {

    private JFrame menuPage;
    private Container container;
    private JPanel menuPane;
    private JPanel functionPane;
    private JButton getStatsButton;
    private JButton addShoeButton;
    private JButton removeShoeButton;
    private JButton sellShoeButton;
    private JButton viewWishlistButton;
    private JButton addWishlistShoeButton;
    private JButton keepShoeButton;
    private JButton saveCollectionButton;
    private JButton exit;
    private JPanel personalCollectionPanel;
    private JPanel addShoePanel;
    private JPanel removeShoePanel;
    private JPanel sellShoePanel;
    private JPanel viewWishlistPanel;
    private JPanel addWishlistPanel;
    private JPanel keepShoePanel;
    private static final String JSON_STORE = "./data/shoeInventory.json";
    private ShoeInventory myShoeCollection;
    private JsonWriter jsonWriter;

    // EFFECTS: creates and runs the Menu Page of the Application
    public MenuPage() {
        createAndShowGUI();
    }

    // MODIFIES: this
    // EFFECTS: creates the menu JFrame
    private void createAndShowGUI() {
        init();
        // creating the Welcome Page window JFrame and setting it up
        menuPage = new JFrame("My Shoe Collection");
        menuPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuPage.setSize(1200,840);

        // Display the window
        menuPage.setVisible(true);

        // Display formatting on the page
        formattingPage();

    }

    // MODIFIES: myShoeCollection, jsonWriter
    // EFFECTS: sets myShoeCollection equal to Shoe Inventory in Welcome Page and sets jsonWriter
    private void init() {
        myShoeCollection = WelcomePage.shoeCollection;
        jsonWriter = new JsonWriter(JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: gets the content pane of the menu page jframe and adds the menu pane and function pane
    private void formattingPage() {
        container = menuPage.getContentPane();
        container.setLayout(null);

        addMenuPane();
        addFunctionPane();
    }

    // MODIFIES: this
    // EFFECTS: creates the menu pane
    private void addMenuPane() {
        menuPane = new JPanel();
        menuPane.setBounds(0,0,350, container.getHeight());
        menuPane.setVisible(true);
        menuPane.setBackground(new Color(0XECECEC));
        menuPane.setLayout(null);

        container.add(menuPane);
        addImage();
        addButtons();
    }

    // MODIFIES: this
    // EFFECTS: creates the function pane
    private void addFunctionPane() {
        functionPane = new JPanel();
        functionPane.setBounds(350,0,850,container.getHeight());
        functionPane.setVisible(true);
        functionPane.setBackground(new Color(0XFFFFFF));
        functionPane.setLayout(null);
        container.add(functionPane);
    }

    // MODIFIES: this
    // EFFECTS: adds the buttons to the menu pane
    private void addButtons() {
        getStatsButton = new JButton("     View Personal Collection");
        addShoeButton = new JButton("     Add Shoe");
        removeShoeButton = new JButton("     Remove Shoe");
        sellShoeButton = new JButton("     Sell Shoe");
        viewWishlistButton = new JButton("     View Wishlist");
        addWishlistShoeButton = new JButton("     Add Shoe To Wishlist");
        keepShoeButton = new JButton("     Shoes I Keep Collection");
        saveCollectionButton = new JButton("     Save Collection");
        exit = new JButton("     Exit");
        getStatsButton.setBounds(0,150,350,50);
        addShoeButton.setBounds(0,200,350,50);
        removeShoeButton.setBounds(0,250,350,50);
        sellShoeButton.setBounds(0,300,350,50);
        viewWishlistButton.setBounds(0,350,350,50);
        addWishlistShoeButton.setBounds(0,400,350,50);
        keepShoeButton.setBounds(0,450,350,50);
        saveCollectionButton.setBounds(0,500,350,50);
        exit.setBounds(0,550,350,50);

        buttonFormatter();
    }

    // MODIFIES: this
    // EFFECTS: calls the button formatting method and sets the button on-click functions
    private void buttonFormatter() {
        formatButtons(getStatsButton);
        formatButtons(addShoeButton);
        formatButtons(removeShoeButton);
        formatButtons(sellShoeButton);
        formatButtons(viewWishlistButton);
        formatButtons(addWishlistShoeButton);
        formatButtons(keepShoeButton);
        formatButtons(saveCollectionButton);
        formatButtons(exit);
        viewButtonFunction();
        addShoeButtonFunction();
        removeShoeButtonFunction();
        sellShoeButtonFunction();
        viewWishlistButtonFunction();
        addWishlistShoeButtonFunction();
        keepShoeButtonFunction();
        saveButtonFunction();
        exitButtonFunction();
    }

    // MODIFIES: this
    // EFFECTS: formats the buttons
    private void formatButtons(JButton button) {
        button.setOpaque(true);
        button.setContentAreaFilled(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setContentAreaFilled(true);
                button.setBackground(new Color(0X2083DC));
                button.setForeground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setContentAreaFilled(false);
                button.setForeground(Color.BLACK);
                button.setBackground(UIManager.getColor("control"));
            }
        });
        menuPane.add(button);

    }

    // MODIFIES: this
    // EFFECTS: adds image to the menu pane
    private void addImage() {
        JLabel shoeImage = new JLabel();
        shoeImage.setIcon(new ImageIcon("./images/MenuShoes.png"));
        shoeImage.setHorizontalAlignment(SwingConstants.CENTER);
        shoeImage.setVerticalAlignment(SwingConstants.CENTER);
        shoeImage.setVisible(true);
        shoeImage.setBounds(0,30,350,100);
        menuPane.add(shoeImage);
    }

    // MODIFIES: this
    // EFFECTS: creates the personal collection layout
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void personalCollectionLayout() {
        functionPane.removeAll();
        personalCollectionPanel = new JPanel();
        functionPane.add(personalCollectionPanel);
        personalCollectionPanel.setLayout(null);
        personalCollectionPanel.setBounds(0,0,functionPane.getWidth(), functionPane.getHeight());
        personalCollectionPanel.setBackground(Color.WHITE);
        JLabel introLabel = new JLabel("My current personal collection is: ");
        DefaultListModel<String> shoeNames = new DefaultListModel<>();
        for (Shoe shoe: myShoeCollection.getShoes()) {
            shoeNames.addElement(shoe.getName());
        }
        JList<String> shoesNames = new JList<>(shoeNames);
        JLabel numShoes = new JLabel("I currently have " + myShoeCollection.getNumShoes()
                + " shoes in your collection");
        JLabel numShoesSold = new JLabel("I have sold " + myShoeCollection.getNumShoesSold() + " shoes");
        JLabel totalValue = new JLabel("The total value of shoes in my collection is $"
                + myShoeCollection.getTotalValue());
        introLabel.setBounds(20,50,functionPane.getWidth(), introLabel.getPreferredSize().height);
        shoesNames.setBounds(20, 100, functionPane.getWidth(), shoesNames.getPreferredSize().height);
        numShoes.setBounds(20, 400, functionPane.getWidth(), numShoes.getPreferredSize().height);
        numShoesSold.setBounds(20, 430, functionPane.getWidth(), numShoesSold.getPreferredSize().height);
        totalValue.setBounds(20, 460, functionPane.getWidth(), totalValue.getPreferredSize().height);
        personalCollectionPanel.add(introLabel);
        personalCollectionPanel.add(shoesNames);
        personalCollectionPanel.add(numShoes);
        personalCollectionPanel.add(numShoesSold);
        personalCollectionPanel.add(totalValue);
    }

    // MODIFIES: this
    // EFFECTS: creates the add shoe layout
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void addShoeLayout() {
        functionPane.removeAll();
        addShoePanel = new JPanel();
        functionPane.add(addShoePanel);
        addShoePanel.setLayout(null);
        addShoePanel.setBounds(0,0,functionPane.getWidth(), functionPane.getHeight());
        addShoePanel.setBackground(Color.WHITE);
        JLabel shoeNameLabel = new JLabel("Model Name: ");
        JLabel brandNameLabel = new JLabel("Brand: ");
        JLabel shoeTypeLabel = new JLabel("Type (high/mid/low): ");
        JLabel retailPriceLabel = new JLabel("Retail Price: ");
        JLabel currentPriceLabel = new JLabel("Current Price: ");
        JLabel keepInCollectionLabel = new JLabel("Keep or Sell: ");
        JLabel noteLabel = new JLabel("Note: Enter true if you wish to keep the shoe and false to sell");
        JTextField shoeName = new JTextField();
        JTextField brandName = new JTextField();
        JTextField shoeType = new JTextField();
        JTextField retailPrice = new JTextField();
        JTextField currentPrice = new JTextField();
        JTextField keepInCollection = new JTextField();
        JButton submit = new JButton("Add");
        shoeNameLabel.setBounds(20,50,200,40);
        shoeName.setBounds(300,50,200,40);
        brandNameLabel.setBounds(20,100,200,40);
        brandName.setBounds(300,100,200,40);
        shoeTypeLabel.setBounds(20,150,200,40);
        shoeType.setBounds(300,150,200,40);
        retailPriceLabel.setBounds(20,200,200,40);
        retailPrice.setBounds(300,200,200,40);
        currentPriceLabel.setBounds(20,250,200,40);
        currentPrice.setBounds(300,250,200,40);
        keepInCollectionLabel.setBounds(20,300,200,40);
        keepInCollection.setBounds(300,300,200,40);
        noteLabel.setBounds(20, 350, 700,40);
        submit.setBounds(550,450,230,50);
        shoeNameLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        brandNameLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        shoeTypeLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        retailPriceLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        currentPriceLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        keepInCollectionLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        noteLabel.setFont((new Font("Dialog", Font.PLAIN,18)));
        shoeName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        brandName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        shoeType.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        retailPrice.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        currentPrice.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        keepInCollection.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        submit.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        submit.setOpaque(true);
        submit.setContentAreaFilled(false);
        submit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        submit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                submit.setContentAreaFilled(true);
                submit.setBackground(new Color(0X2083DC));
                submit.setForeground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                submit.setContentAreaFilled(false);
                submit.setForeground(Color.BLACK);
                submit.setBackground(UIManager.getColor("control"));
            }
        });
        submit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String name = shoeName.getText();
                String brand = brandName.getText();
                String type = shoeType.getText();
                Double retailValue = Double.parseDouble(retailPrice.getText());
                Double currentValue = Double.parseDouble(currentPrice.getText());
                Boolean keep = Boolean.parseBoolean(keepInCollection.getText());
                Shoe shoe;
                shoe = new Shoe(name);
                shoe.setBrand(brand);
                shoe.setType(type);
                shoe.setRetailPrice(retailValue);
                shoe.setValue(currentValue);
                shoe.setPersonalCollection(keep);
                myShoeCollection.addShoes(shoe);
                JOptionPane.showMessageDialog(menuPage, "Shoe Added!");
            }
        });
        addShoePanel.add(shoeNameLabel);
        addShoePanel.add(shoeName);
        addShoePanel.add(brandNameLabel);
        addShoePanel.add(brandName);
        addShoePanel.add(shoeTypeLabel);
        addShoePanel.add(shoeType);
        addShoePanel.add(retailPriceLabel);
        addShoePanel.add(retailPrice);
        addShoePanel.add(currentPriceLabel);
        addShoePanel.add(currentPrice);
        addShoePanel.add(keepInCollectionLabel);
        addShoePanel.add(keepInCollection);
        addShoePanel.add(noteLabel);
        addShoePanel.add(submit);
    }

    // MODIFIES: this
    // EFFECTS: creates the remove shoe layout
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void removeShoeLayout() {
        functionPane.removeAll();
        removeShoePanel = new JPanel();
        functionPane.add(removeShoePanel);
        removeShoePanel.setLayout(null);
        removeShoePanel.setBounds(0,0,functionPane.getWidth(), functionPane.getHeight());
        removeShoePanel.setBackground(Color.WHITE);
        JLabel introLabel = new JLabel("My current personal collection is: ");
        DefaultListModel<String> shoeNames = new DefaultListModel<>();
        for (Shoe shoe: myShoeCollection.getShoes()) {
            shoeNames.addElement(shoe.getName());
        }
        JList<String> shoesNames = new JList<>(shoeNames);
        JLabel removeShoeLabel = new JLabel("Enter name of shoe to remove: ");
        JTextField shoeName = new JTextField();
        JButton submit = new JButton("Remove");
        introLabel.setBounds(20,50,functionPane.getWidth(), introLabel.getPreferredSize().height);
        shoesNames.setBounds(20, 100, functionPane.getWidth(), shoesNames.getPreferredSize().height);
        removeShoeLabel.setBounds(20, 400, 200, 40);
        shoeName.setBounds(300,400,200,40);
        submit.setBounds(550,450,230,50);
        shoeName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        submit.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        submit.setOpaque(true);
        submit.setContentAreaFilled(false);
        submit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        submit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                submit.setContentAreaFilled(true);
                submit.setBackground(new Color(0X2083DC));
                submit.setForeground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                submit.setContentAreaFilled(false);
                submit.setForeground(Color.BLACK);
                submit.setBackground(UIManager.getColor("control"));
            }
        });
        submit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                for (Shoe shoe: myShoeCollection.getShoes()) {
                    if (shoe.getName().equals(shoeName.getText())) {
                        myShoeCollection.removeShoes(shoe);
                    }
                }
                JOptionPane.showMessageDialog(menuPage, "Shoe Removed!");
            }
        });
        removeShoePanel.add(introLabel);
        removeShoePanel.add(shoesNames);
        removeShoePanel.add(removeShoeLabel);
        removeShoePanel.add(shoeName);
        removeShoePanel.add(submit);
    }

    // MODIFIES: this
    // EFFECTS: creates the sell shoe layout
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void sellShoeLayout() {
        functionPane.removeAll();
        sellShoePanel = new JPanel();
        functionPane.add(sellShoePanel);
        sellShoePanel.setLayout(null);
        sellShoePanel.setBounds(0,0,functionPane.getWidth(), functionPane.getHeight());
        sellShoePanel.setBackground(Color.WHITE);
        JLabel introLabel = new JLabel("My current personal collection is: ");
        DefaultListModel<String> shoeNames = new DefaultListModel<>();
        for (Shoe shoe: myShoeCollection.getShoes()) {
            shoeNames.addElement(shoe.getName());
        }
        JList<String> shoesNames = new JList<>(shoeNames);
        JLabel removeShoeLabel = new JLabel("Enter name of shoe to sell: ");
        JTextField shoeName = new JTextField();
        JButton submit = new JButton("Sell");
        introLabel.setBounds(20,50,functionPane.getWidth(), introLabel.getPreferredSize().height);
        shoesNames.setBounds(20, 100, functionPane.getWidth(), shoesNames.getPreferredSize().height);
        removeShoeLabel.setBounds(20, 400, 200, 40);
        shoeName.setBounds(300,400,200,40);
        submit.setBounds(550,450,230,50);
        shoeName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        submit.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        submit.setOpaque(true);
        submit.setContentAreaFilled(false);
        submit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        submit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                submit.setContentAreaFilled(true);
                submit.setBackground(new Color(0X2083DC));
                submit.setForeground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                submit.setContentAreaFilled(false);
                submit.setForeground(Color.BLACK);
                submit.setBackground(UIManager.getColor("control"));
            }
        });
        submit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                for (Shoe shoe: myShoeCollection.getShoes()) {
                    if (shoe.getName().equals(shoeName.getText())) {
                        myShoeCollection.sellShoes(shoe);
                    }
                }
                JOptionPane.showMessageDialog(menuPage, "Shoe Sold!");
            }
        });
        sellShoePanel.add(introLabel);
        sellShoePanel.add(shoesNames);
        sellShoePanel.add(removeShoeLabel);
        sellShoePanel.add(shoeName);
        sellShoePanel.add(submit);
    }

    // MODIFIES: this
    // EFFECTS: creates the view wishlist layout
    private void viewWishlistLayout() {
        functionPane.removeAll();
        viewWishlistPanel = new JPanel();
        functionPane.add(viewWishlistPanel);
        viewWishlistPanel.setLayout(null);
        viewWishlistPanel.setBounds(0,0,functionPane.getWidth(), functionPane.getHeight());
        viewWishlistPanel.setBackground(Color.WHITE);
        JLabel introLabel = new JLabel("My wishlist looks like: ");
        DefaultListModel<String> shoeNames = new DefaultListModel<>();
        for (Shoe shoe: myShoeCollection.getWishlist()) {
            shoeNames.addElement(shoe.getName());
        }
        JList<String> shoesNames = new JList<>(shoeNames);
        introLabel.setBounds(20,50,functionPane.getWidth(), introLabel.getPreferredSize().height);
        shoesNames.setBounds(20, 100, functionPane.getWidth(), shoesNames.getPreferredSize().height);
        viewWishlistPanel.add(introLabel);
        viewWishlistPanel.add(shoesNames);

    }

    // MODIFIES: this
    // EFFECTS: creates the add shoe to wishlist layout
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void addWishlistLayout() {
        functionPane.removeAll();
        addWishlistPanel = new JPanel();
        functionPane.add(addWishlistPanel);
        addWishlistPanel.setLayout(null);
        addWishlistPanel.setBounds(0,0,functionPane.getWidth(), functionPane.getHeight());
        addWishlistPanel.setBackground(Color.WHITE);
        JLabel shoeNameLabel = new JLabel("Model Name: ");
        JLabel brandNameLabel = new JLabel("Brand: ");
        JLabel shoeTypeLabel = new JLabel("Type (high/mid/low): ");
        JLabel retailPriceLabel = new JLabel("Retail Price: ");
        JLabel currentPriceLabel = new JLabel("Current Price: ");
        JLabel keepInCollectionLabel = new JLabel("Keep or Sell: ");
        JLabel noteLabel = new JLabel("Note: Enter true if you wish to keep the shoe and false to sell");
        JTextField shoeName = new JTextField();
        JTextField brandName = new JTextField();
        JTextField shoeType = new JTextField();
        JTextField retailPrice = new JTextField();
        JTextField currentPrice = new JTextField();
        JTextField keepInCollection = new JTextField();
        JButton submit = new JButton("Add");
        shoeNameLabel.setBounds(20,50,200,40);
        shoeName.setBounds(300,50,200,40);
        brandNameLabel.setBounds(20,100,200,40);
        brandName.setBounds(300,100,200,40);
        shoeTypeLabel.setBounds(20,150,200,40);
        shoeType.setBounds(300,150,200,40);
        retailPriceLabel.setBounds(20,200,200,40);
        retailPrice.setBounds(300,200,200,40);
        currentPriceLabel.setBounds(20,250,200,40);
        currentPrice.setBounds(300,250,200,40);
        keepInCollectionLabel.setBounds(20,300,200,40);
        keepInCollection.setBounds(300,300,200,40);
        noteLabel.setBounds(20, 350, 700,40);
        submit.setBounds(550,450,230,50);
        shoeNameLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        brandNameLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        shoeTypeLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        retailPriceLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        currentPriceLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        keepInCollectionLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        noteLabel.setFont((new Font("Dialog", Font.PLAIN,18)));
        shoeName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        brandName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        shoeType.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        retailPrice.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        currentPrice.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        keepInCollection.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        submit.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        submit.setOpaque(true);
        submit.setContentAreaFilled(false);
        submit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        submit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                submit.setContentAreaFilled(true);
                submit.setBackground(new Color(0X2083DC));
                submit.setForeground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                submit.setContentAreaFilled(false);
                submit.setForeground(Color.BLACK);
                submit.setBackground(UIManager.getColor("control"));
            }
        });
        submit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String name = shoeName.getText();
                String brand = brandName.getText();
                String type = shoeType.getText();
                Double retailValue = Double.parseDouble(retailPrice.getText());
                Double currentValue = Double.parseDouble(currentPrice.getText());
                Boolean keep = Boolean.parseBoolean(keepInCollection.getText());
                Shoe shoe;
                shoe = new Shoe(name);
                shoe.setBrand(brand);
                shoe.setType(type);
                shoe.setRetailPrice(retailValue);
                shoe.setValue(currentValue);
                shoe.setPersonalCollection(keep);
                myShoeCollection.addToWishlist(shoe);
                JOptionPane.showMessageDialog(menuPage, "Shoe Added!");
            }
        });
        addWishlistPanel.add(shoeNameLabel);
        addWishlistPanel.add(shoeName);
        addWishlistPanel.add(brandNameLabel);
        addWishlistPanel.add(brandName);
        addWishlistPanel.add(shoeTypeLabel);
        addWishlistPanel.add(shoeType);
        addWishlistPanel.add(retailPriceLabel);
        addWishlistPanel.add(retailPrice);
        addWishlistPanel.add(currentPriceLabel);
        addWishlistPanel.add(currentPrice);
        addWishlistPanel.add(keepInCollectionLabel);
        addWishlistPanel.add(keepInCollection);
        addWishlistPanel.add(noteLabel);
        addWishlistPanel.add(submit);
    }


    // MODIFIES: this
    // EFFECTS: creates the keep shoe layout
    private void keepShoeLayout() {
        functionPane.removeAll();
        keepShoePanel = new JPanel();
        functionPane.add(keepShoePanel);
        keepShoePanel.setLayout(null);
        keepShoePanel.setBounds(0,0,functionPane.getWidth(), functionPane.getHeight());
        keepShoePanel.setBackground(Color.WHITE);
        JLabel introLabel = new JLabel("Shoes I am keeping in collection and not willing to sell: ");
        DefaultListModel<String> shoeNames = new DefaultListModel<>();
        for (Shoe shoe: myShoeCollection.getShoes()) {
            if (shoe.getPersonalCollection()) {
                shoeNames.addElement(shoe.getName());
            }
        }
        JList<String> shoesNames = new JList<>(shoeNames);
        introLabel.setBounds(20,50,functionPane.getWidth(), introLabel.getPreferredSize().height);
        shoesNames.setBounds(20, 100, functionPane.getWidth(), shoesNames.getPreferredSize().height);
        keepShoePanel.add(introLabel);
        keepShoePanel.add(shoesNames);
    }


    // MODIFIES: viewButton
    // EFFECTS: sets the viewButton function
    public void viewButtonFunction() {
        getStatsButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                personalCollectionLayout();
            }
        });
    }

    // MODIFIES: addShoeButton
    // EFFECTS: sets the addShoeButton function
    public void addShoeButtonFunction() {
        addShoeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addShoeLayout();
            }
        });
    }

    // MODIFIES: removeShoeButton
    // EFFECTS: sets the removeShoeButton function
    public void removeShoeButtonFunction() {
        removeShoeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                removeShoeLayout();
            }
        });
    }

    // MODIFIES: sellShoeButton
    // EFFECTS: sets the sellShoeButton function
    public void sellShoeButtonFunction() {
        sellShoeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                sellShoeLayout();
            }
        });
    }

    // MODIFIES: viewWishlistButton
    // EFFECTS: sets the viewWishlistButton function
    public void viewWishlistButtonFunction() {
        viewWishlistButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                viewWishlistLayout();
            }
        });
    }

    // MODIFIES: addWishlistShoeButton
    // EFFECTS: sets the addWishlistShoeButton function
    public void addWishlistShoeButtonFunction() {
        addWishlistShoeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addWishlistLayout();
            }
        });
    }

    // MODIFIES: keepShoeButton
    // EFFECTS: sets the keepShoeButton function
    public void keepShoeButtonFunction() {
        keepShoeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                keepShoeLayout();
            }
        });
    }

    // MODIFIES: saveButton
    // EFFECTS: sets the saveButton function
    public void saveButtonFunction() {
        saveCollectionButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                jsonWriter = new JsonWriter(JSON_STORE);
                try {
                    jsonWriter.open();
                    jsonWriter.write(myShoeCollection);
                    jsonWriter.close();
                    JOptionPane.showMessageDialog(menuPage, "Collection Saved!");

                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(menuPage, "Error Saving Collection!");
                }

            }
        });
    }

    // MODIFIES: exitButton
    // EFFECTS: sets the exitButton function
    public void exitButtonFunction() {
        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                printLog(EventLog.getInstance());
                System.exit(0);
            }
        });
    }

    public void printLog(EventLog eventLog) {
        for (Event event: eventLog) {
            System.out.println(event.toString());
        }
    }

}
