package ui;

import model.Shoe;
import model.ShoeInventory;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// ShoeInventory application
public class MyShoeInventoryApp {

    private static final String JSON_STORE = "./data/shoeInventory.json";
    private ShoeInventory myShoeCollection;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the ShoeInventory application
    public MyShoeInventoryApp() throws FileNotFoundException {
        runMyShoeInventoryApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void runMyShoeInventoryApp() {
        init();
        boolean keepGoing = true;
        while (keepGoing) {
            displayMenu();
            int userInput = input.nextInt();
            if (userInput == 1) {
                getStats();
            } else if (userInput == 2) {
                addShoe();
            } else if (userInput == 3) {
                removeShoe();
            } else if (userInput == 4) {
                sellShoe();
            } else if (userInput == 5) {
                viewWishList();
            } else if (userInput == 6) {
                addToWishList();
            } else if (userInput == 7) {
                getInfoAboutShoe();
            } else if (userInput == 8) {
                saveShoeCollection();
            } else if (userInput == 9) {
                loadShoeCollection();
            } else if (userInput == 10) {
                keepGoing = false;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes accounts
    private void init() {
        myShoeCollection = new ShoeInventory();
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: displays the menu of options to the user
    private void displayMenu() {
        System.out.println("Welcome to your Shoe Collection!");
        System.out.println("What would you like to do? (enter the number key)");
        System.out.println("1. Get personal collection statistics");
        System.out.println("2. Add a shoe to collection");
        System.out.println("3. Remove a shoe from your collection");
        System.out.println("4. Sell a shoe from your collection");
        System.out.println("5. View your wishlist");
        System.out.println("6. Add a shoe to your wishlist");
        System.out.println("7. Get information about a shoe from your collection");
        System.out.println("8. Save your shoe collection");
        System.out.println("9. Load your shoe collection");
        System.out.println("10. Quit");

    }

    // EFFECTS: Displays the current statistics of the user's personal collection
    private void getStats() {
        System.out.println("Your current collection of shoes is: ");
        for (Shoe shoe: myShoeCollection.getShoes()) {
            System.out.println(shoe.getName());
        }
        System.out.println("You currently have " + myShoeCollection.getNumShoes() + " shoes in your collection");
        System.out.println("You have sold " + myShoeCollection.getNumShoesSold() + " shoes");
        System.out.println("The total value of shoes in your collection is " + myShoeCollection.getTotalValue());
        backToMenu();
    }

    // MODIFIES: this
    // EFFECTS: adds a shoe to the user's personal collection
    private void addShoe() {
        Shoe shoe;
        shoe = createShoe();

        myShoeCollection.addShoes(shoe);
        backToMenu();
    }

    // REQUIRES: the shoe to be removed exists in the collection
    // MODIFIES: this
    // EFFECTS: removes a shoe from the user's collection
    private void removeShoe() {
        displayCurrentShoes();
        System.out.println("Enter the name of the shoe you want to remove:");
        String name = input.nextLine();
        boolean found = false;
        Shoe remove = null;
        for (Shoe shoe: myShoeCollection.getShoes()) {
            if (shoe.getName().equals(name)) {
                remove = shoe;
                found = true;
            }
        }
        if (found) {
            myShoeCollection.removeShoes(remove);
        } else {
            System.out.println("Shoe not in your collection");
        }
        backToMenu();
    }

    // REQUIRES: the shoe to be removed exists in the collection and shoe should not be tagged as 'to keep'
    // MODIFIES: this
    // EFFECTS: removes a shoe from the user's collection
    private void sellShoe() {
        displayCurrentShoes();
        System.out.println("Enter the name of the shoe you want to sell:");
        String name = input.nextLine();
        boolean found = false;
        Shoe remove = null;
        for (Shoe shoe: myShoeCollection.getShoes()) {
            if (shoe.getName().equals(name)) {
                found = true;
                remove = shoe;
            }
        }
        if (found) {
            if (myShoeCollection.sellShoes(remove)) {
                System.out.println("Shoe sold successfully");
            } else {
                System.out.println("You cannot sell this shoe since you wish to keep this in your collection");
            }
        } else {
            System.out.println("Shoe not found in your collection");
        }
        backToMenu();
    }

    // EFFECTS: displays the user's wishlist
    private void viewWishList() {
        System.out.println("Your wishlist: ");
        for (Shoe shoe: myShoeCollection.getWishlist()) {
            System.out.println(shoe.getName());
        }
        backToMenu();
    }

    // REQUIRES: the shoe to be added should not already be part of collection or wishlist
    // MODIFIES: this
    // EFFECTS: adds a shoe to the wishlist
    private void addToWishList() {
        Shoe shoe;
        shoe = createShoe();

        boolean found = false;

        for (Shoe s: myShoeCollection.getShoes()) {
            if (s.getName().equals(shoe.getName())) {
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Shoe added to wishlist successfully");
            myShoeCollection.addToWishlist(shoe);
        } else {
            System.out.println("You already have this shoe in your collection/wishlist");
        }
        backToMenu();
    }

    // REQUIRES: the shoe should be in the collection
    // EFFECTS: displays information about the shoe
    private void getInfoAboutShoe() {
        displayCurrentShoes();
        System.out.println("Enter the name of the shoe you want to get information about:");
        String name = input.nextLine();

        boolean found = false;
        for (Shoe shoe: myShoeCollection.getShoes()) {
            if (shoe.getName().equals(name)) {
                System.out.println(myShoeCollection.getInfo(shoe));
                found = true;
            }
        }
        if (!found) {
            System.out.println("Shoe not in your collection");
        }
        backToMenu();
    }

    // EFFECTS: saves the shoeInventory to a file
    private void saveShoeCollection() {
        try {
            jsonWriter.open();
            jsonWriter.write(myShoeCollection);
            jsonWriter.close();
            System.out.println("Saved My Shoe Collection to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        } finally {
            backToMenu();
        }
    }

    // EFFECTS: loads shoeInventory from file
    private void loadShoeCollection() {
        try {
            myShoeCollection = jsonReader.read();
            System.out.println("Loaded My Shoe Collection from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        } finally {
            backToMenu();
        }
    }

    // EFFECTS: helper method to create a new shoe
    private Shoe createShoe() {
        input.nextLine();
        System.out.println("Enter the name of the shoe: ");
        String name = input.nextLine();
        System.out.println("Enter the shoe's brand: ");
        String brand = input.nextLine();
        System.out.println("Enter the shoe's type(high/mid/low): ");
        String type = input.nextLine();
        System.out.println("Enter the shoe's retail price in USD: ");
        double retailPrice = input.nextDouble();
        System.out.println("Enter the shoe's current value in USD: ");
        double currentValue = input.nextDouble();
        System.out.println("Enter true if you would keep this shoe and false if you wish to sell it: ");
        boolean personalCollection = input.nextBoolean();

        Shoe shoe = new Shoe(name);
        shoe.setBrand(brand);
        shoe.setType(type);
        shoe.setRetailPrice(retailPrice);
        shoe.setValue(currentValue);
        shoe.setPersonalCollection(personalCollection);

        return shoe;
    }

    // EFFECTS: helper method to go back to the menu
    private void backToMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("Enter back to go back to the menu");
            String userInput = input.next();
            if (userInput.equals("back")) {
                back = true;
            }
        }
    }

    // EFFECTS: helper method to display current shoes in the collection
    private void displayCurrentShoes() {
        input.nextLine();
        System.out.println("Your current collection of shoes is: ");
        for (Shoe shoe: myShoeCollection.getShoes()) {
            System.out.println(shoe.getName());
        }
    }

}
