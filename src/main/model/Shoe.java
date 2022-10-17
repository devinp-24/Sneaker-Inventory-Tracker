package model;

// Represents an individual shoe created by the user
public class Shoe {

    // attributes
    private String name;
    private String brand;
    // high-top, mid-top, low-top
    private String type;
    private double retailPrice;
    private double value;
    private boolean personalCollection;

    //Constructor
    // EFFECTS: Constructs a new shoe with default attributes
    public Shoe(String name) {
        this.name = name;
        this.brand = "Custom";
        this.type = "Low";
        this.retailPrice = 0.00;
        this.value = 0.00;
        this.personalCollection = true;
    }

    // Methods
    public String getInfo() {
        return "Model Name: " + this.getName() + "\n"
                + "Brand: " + this.getBrand() + "\n"
                + "Top: " + this.getType() + "\n"
                + "Retail Price: " + this.getRetailPrice() + "\n"
                + "Current Value: " + this.getValue();
    }

    // Setters and Getters
    // EFFECTS: returns the name of the model of the shoe
    public String getName() {
        return name;
    }

    // EFFECTS: returns the brand name of the shoe
    public String getBrand() {
        return brand;
    }

    // MODIFIES: this
    // EFFECTS: Sets the brand name of the shoe
    public void setBrand(String brand) {
        this.brand = brand;
    }

    // EFFECTS: Returns the type of the shoe
    public String getType() {
        return type;
    }

    // MODIFIES: this
    // EFFECTS: Sets the type of the shoe
    public void setType(String type) {
        this.type = type;
    }

    // EFFECTS: Returns the retail price of the shoe
    public double getRetailPrice() {
        return retailPrice;
    }

    // MODIFIES: this
    // EFFECTS: Sets the retail price of the shoe
    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    // EFFECTS: Returns the current resell value of the shoe
    public double getValue() {
        return value;
    }

    // MODIFIES: this
    // EFFECTS: Sets the current resell value of the shoe
    public void setValue(double value) {
        this.value = value;
    }

    // EFFECTS: returns the boolean whether the user wants to keep the shoe or sell it
    public boolean getPersonalCollection() {
        return personalCollection;
    }

    // MODIFIES: this
    // EFFECTS: sets whether the user wants to keep the shoe or sell it
    public void setPersonalCollection(boolean personalCollection) {
        this.personalCollection = personalCollection;
    }
}
