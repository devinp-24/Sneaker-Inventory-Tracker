package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents the user's shoe collection with a list of all their shoes
public class ShoeInventory implements Writable {

    //attributes
    private List<Shoe> shoes;
    private int numShoes;
    private double totalValue;
    private int numShoesSold;
    private List<Shoe> wishlist;


    // EFFECTS: Constructs an empty shoe collection with an empty list of shoes, and sets the number of shoes and value
    // of collection to 0.
    public ShoeInventory() {
        this.shoes = new ArrayList<Shoe>();
        this.numShoes = 0;
        this.totalValue = 0;
        this.numShoesSold = 0;
        this.wishlist = new ArrayList<Shoe>();
    }

    // Methods

    // MODIFIES: this
    // EFFECTS: adds a shoe to the shoe collection list
    public void addShoes(Shoe shoe) {
        shoes.add(shoe);
        this.setNumShoes(this.numShoes + 1);
        this.setTotalValue(this.totalValue + shoe.getValue());
    }

    // MODIFIES: this
    // EFFECTS: removes a shoe from the shoe collection list
    public void removeShoes(Shoe shoe) {
        shoes.remove(shoe);
        this.setNumShoes(this.numShoes - 1);
        this.setTotalValue(totalValue - shoe.getValue());
    }

    // REQUIRES: shoe should not be labelled as part of personal collection to keep and shoe is in inventory
    // MODIFIES: this
    // EFFECTS: removes shoe from the shoe collection list and increments the number of shoes sold by 1
    public boolean sellShoes(Shoe shoe) {
        if ((!shoe.getPersonalCollection()) && this.getShoes().contains(shoe)) {
            this.removeShoes(shoe);
            this.setNumShoesSold(numShoesSold + 1);
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES: shoe should not already be part of collection or wishlist
    // MODIFIES: this
    // EFFECTS: adds the shoe to the wishlist
    public boolean addToWishlist(Shoe shoe) {
        if (!this.getShoes().contains(shoe) && !this.getWishlist().contains(shoe)) {
            this.wishlist.add(shoe);
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES: shoe to be part of the shoe collection
    // EFFECTS: returns some information about the shoe
    public String getInfo(Shoe shoe) {
        if (this.shoes.contains(shoe)) {
            return shoe.getInfo();
        } else {
            return "Shoes not found!";
        }
    }


    // EFFECTS: Returns the list of shoes in the user's shoe collection
    public List<Shoe> getShoes() {
        return shoes;
    }

    // EFFECTS: Returns the number of shoes in the user's collection
    public int getNumShoes() {
        return numShoes;
    }

    // MODIFIES: this
    // EFFECTS: Sets the number of shoes in the user's collection to the new value numShoes
    public void setNumShoes(int numShoes) {
        this.numShoes = numShoes;
    }

    // EFFECTS: Returns the total value of the user's shoe collection
    public double getTotalValue() {
        return totalValue;
    }

    // MODIFIES: this
    // EFFECTS: Sets the value of the user's shoe collection to the new value totalValue
    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    // EFFECTS: return the number of shoes previously sold by the user
    public int getNumShoesSold() {
        return numShoesSold;
    }

    // MODIFIES: this
    // EFFECTS: sets a new value for the number of shoes sold by the user
    public void setNumShoesSold(int numShoesSold) {
        this.numShoesSold = numShoesSold;
    }

    // EFFECTS: returns the list of shoes in the user's wishlist
    public List<Shoe> getWishlist() {
        return wishlist;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", "My Shoe Collection");
        json.put("shoes", shoesToJson());
        json.put("numShoes", numShoes);
        json.put("totalValue", totalValue);
        json.put("numShoesSold", numShoesSold);
        json.put("wishlist", wishlistToJson());
        return json;
    }

    // EFFECTS: returns shoes in this shoe collection as a JSON array
    private JSONArray shoesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Shoe shoe: shoes) {
            jsonArray.put(shoe.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: returns shoes in the wishlist in this shoe collection as a JSON array
    private JSONArray wishlistToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Shoe shoe: wishlist) {
            jsonArray.put(shoe.toJson());
        }
        return jsonArray;
    }
}
