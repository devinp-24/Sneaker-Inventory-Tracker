package persistence;

import model.Shoe;
import model.ShoeInventory;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {

    // testing writing to an invalid file name format
    @Test
    void testWriterInvalidFile() {
        try {
            ShoeInventory shoeInventory = new ShoeInventory();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    // testing writing an empty shoeInventory to a JSON file
    @Test
    void testWriterEmptyWorkroom() {
        try {
            ShoeInventory shoeInventory = new ShoeInventory();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyShoeInventory.json");
            writer.open();
            writer.write(shoeInventory);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyShoeInventory.json");
            shoeInventory = reader.read();
            assertEquals(0, shoeInventory.getShoes().size());
            assertEquals(0, shoeInventory.getNumShoes());
            assertEquals(0, shoeInventory.getWishlist().size());
            assertEquals(0, shoeInventory.getNumShoesSold());
            assertEquals(0, shoeInventory.getTotalValue());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    // testing writing a full example of a demo shoeInventory to a JSON file
    @Test
    void testReaderGeneralShoeInventory() {
        try {
            ShoeInventory shoeInventory = new ShoeInventory();
            Shoe shoe1 = new Shoe("Jordan 1 Travis Scott");
            Shoe shoe2 = new Shoe("SB Dunk Strange Love");
            Shoe shoe3 = new Shoe("Human Race Holi");

            shoe1.setBrand("Jordan");
            shoe1.setType("High");
            shoe1.setRetailPrice(150.00);
            shoe1.setValue(1900.00);
            shoe1.setPersonalCollection(true);

            shoe2.setBrand("Nike");
            shoe2.setType("Low");
            shoe2.setRetailPrice(100.00);
            shoe2.setValue(1200.00);
            shoe2.setPersonalCollection(true);

            shoe3.setBrand("Adidas");
            shoe3.setType("Low");
            shoe3.setRetailPrice(170.00);
            shoe3.setValue(600.00);
            shoe3.setPersonalCollection(false);

            shoeInventory.addShoes(shoe1);
            shoeInventory.addShoes(shoe2);
            shoeInventory.addToWishlist(shoe3);
            shoeInventory.setNumShoesSold(2);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralShoeInventory.json");
            writer.open();
            writer.write(shoeInventory);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralShoeInventory.json");
            shoeInventory = reader.read();
            assertEquals(2, shoeInventory.getShoes().size());
            assertEquals(2, shoeInventory.getNumShoes());
            assertEquals(1, shoeInventory.getWishlist().size());
            assertEquals(2, shoeInventory.getNumShoesSold());
            assertEquals(3100, shoeInventory.getTotalValue());
        } catch (IOException e){
            fail("Exception should not have been thrown");
        }
    }

}
