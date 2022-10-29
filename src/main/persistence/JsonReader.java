package persistence;

import model.Shoe;
import model.ShoeInventory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// I have modelled my code on the sample application provided. I am citing this source in the following comment:
// https://github.students.cs.ubc.ca/CPSC210

// Represents a reader that reads shoeInventory from JSON data stored in a file
public class JsonReader {

    private String source;

    // EFFECTS: constructs reader to read from the source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads shoeInventory from the source file and returns it and
    // throws an IOException Error if there is a problem reading the file
    public ShoeInventory read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseShoeInventory(jsonObject);
    }

    // EFFECTS: reads the source file as String and returns it and throws an
    // IOException error if there is an issue reading the source file
    public String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses shoeInventory from jsonObject and returns it
    private ShoeInventory parseShoeInventory(JSONObject jsonObject) {
        ShoeInventory shoeInventory = new ShoeInventory();
        // shoeInventory.setNumShoes(jsonObject.getInt("numShoes"));
        // shoeInventory.setTotalValue(jsonObject.getDouble("totalValue"));
        shoeInventory.setNumShoesSold(jsonObject.getInt("numShoesSold"));
        addShoes(shoeInventory, jsonObject);
        return shoeInventory;
    }

    // MODIFIES: shoeInventory
    // EFFECTS: parses shoes from JSON object and adds them to shoeInventory
    private void addShoes(ShoeInventory shoeInventory, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("shoes");
        for (Object json: jsonArray) {
            JSONObject nextShoe = (JSONObject) json;
            addShoe(shoeInventory, nextShoe);
        }
        JSONArray jsonArrayWishList = jsonObject.getJSONArray("wishlist");
        for (Object json: jsonArrayWishList) {
            JSONObject nextShoe = (JSONObject) json;
            addShoeToWishList(shoeInventory, nextShoe);
        }
    }

    // MODIFIES: shoeInventory
    // EFFECTS: parses shoe from JSON object and adds them to shoeInventory
    private void addShoe(ShoeInventory shoeInventory, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Shoe shoe = new Shoe(name);
        shoe.setBrand(jsonObject.getString("brand"));
        shoe.setType(jsonObject.getString("type"));
        shoe.setRetailPrice(jsonObject.getDouble("retailPrice"));
        shoe.setValue(jsonObject.getDouble("value"));
        shoe.setPersonalCollection(jsonObject.getBoolean("personalCollection"));
        shoeInventory.addShoes(shoe);
    }

    // MODIFIES: shoeInventory
    // EFFECTS: parses shoe from JSON object and adds them to wishlist in shoeInventory
    private void addShoeToWishList(ShoeInventory shoeInventory, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Shoe shoe = new Shoe(name);
        shoe.setBrand(jsonObject.getString("brand"));
        shoe.setType(jsonObject.getString("type"));
        shoe.setRetailPrice(jsonObject.getDouble("retailPrice"));
        shoe.setValue(jsonObject.getDouble("value"));
        shoe.setPersonalCollection(jsonObject.getBoolean("personalCollection"));
        shoeInventory.addToWishlist(shoe);
    }
}
