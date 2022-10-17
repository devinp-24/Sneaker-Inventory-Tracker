package ui;

import model.Shoe;
import model.ShoeInventory;

import java.util.Scanner;

public class MyShoeInventoryApp {

    private ShoeInventory myShoeCollection;
    private Scanner input;

    public MyShoeInventoryApp() {
        runMyShoeInventoryApp();
    }

    private void runMyShoeInventoryApp() {
        innit();

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
                keepGoing = false;
            }
        }
    }

    private void innit() {
        myShoeCollection = new ShoeInventory();
        input = new Scanner(System.in);
    }

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
        System.out.println("8. Quit");

    }

    private void getStats() {
        System.out.println("Your current collection of shoes is: ");
        for (Shoe shoe: myShoeCollection.getShoes()) {
            System.out.println(shoe.getName());
        }
        System.out.println("You currently have " + myShoeCollection.getNumShoes() + " shoes in your collection");
        System.out.println("You have sold " + myShoeCollection.getNumShoesSold() + " shoes");
        System.out.println("The total value of shoes in your collection is " + myShoeCollection.getTotalValue());

        boolean back = false;
        while (!back) {
            System.out.println("Enter back to go back to the menu");
            String userInput = input.next();
            if (userInput.equals("back")) {
                back = true;
            }
        }
    }

    private void addShoe() {
        Shoe shoe;
        shoe = createShoe();

        myShoeCollection.addShoes(shoe);
    }

    private void removeShoe() {
        System.out.println("Your current collection of shoes is: ");
        for (Shoe shoe: myShoeCollection.getShoes()) {
            System.out.println(shoe.getName());
        }
        System.out.println("Enter the name of the shoe you want to remove:");
        String name = input.nextLine();
        boolean found = false;
        for (Shoe shoe: myShoeCollection.getShoes()) {
            if (shoe.getName().equals(name)) {
                myShoeCollection.removeShoes(shoe);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Shoe not in your collection");
        }
    }

    private void sellShoe() {
        System.out.println("Your current collection of shoes is: ");
        for (Shoe shoe: myShoeCollection.getShoes()) {
            System.out.println(shoe.getName());
        }
        System.out.println("Enter the name of the shoe you want to sell:");
        String name = input.nextLine();
        boolean success = false;
        for (Shoe shoe: myShoeCollection.getShoes()) {
            if (shoe.getName().equals(name)) {
                success = myShoeCollection.sellShoes(shoe);
            }
        }

        if (success) {
            System.out.println("Shoe sold successfully");
        } else {
            System.out.println("The shoe is either part of your personal collection that you do not wish to sell "
                    + " or not found in your collection");
        }
    }

    private void viewWishList() {
        for (Shoe shoe: myShoeCollection.getWishlist()) {
            System.out.println(shoe.getName());
        }
    }

    private void addToWishList() {
        Shoe shoe;
        shoe = createShoe();

        boolean success;

        success = myShoeCollection.addToWishlist(shoe);
        if (success) {
            System.out.println("Shoe added to wishlist successfully");
        } else {
            System.out.println("You already have this shoe in your collection");
        }

    }

    private void getInfoAboutShoe() {
        System.out.println("Your current collection of shoes is: ");
        for (Shoe shoe: myShoeCollection.getShoes()) {
            System.out.println(shoe.getName());
        }
        System.out.println("Enter the name of the shoe you want to get information about:");
        String name = input.nextLine();

        for (Shoe shoe: myShoeCollection.getShoes()) {
            if (shoe.getName().equals(name)) {
                myShoeCollection.getInfo(shoe);
            }
        }
    }

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

}
