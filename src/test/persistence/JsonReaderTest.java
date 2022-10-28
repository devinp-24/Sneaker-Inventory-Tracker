package persistence;

import model.ShoeInventory;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {

    // testing the reading of a non-existent JSON file
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/nonExistentFile.json");
        try {
            ShoeInventory shoeInventory = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    // testing the reading of a JSON file with an empty ShoeInventory
    @Test
    void testReaderEmptyShoeInventory() {
        JsonReader reader = new JsonReader("./data/testWriterEmptyShoeInventory.json");
        try {
            ShoeInventory shoeInventory = reader.read();
            assertEquals(0, shoeInventory.getShoes().size());
            assertEquals(0, shoeInventory.getNumShoes());
            assertEquals(0, shoeInventory.getWishlist().size());
            assertEquals(0, shoeInventory.getNumShoesSold());
            assertEquals(0, shoeInventory.getTotalValue());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    // testing the reading of a full example of a demo JSON file with shoes in ShoeInventory
    @Test
    void testReaderGeneralShoeInventory() {
        JsonReader reader = new JsonReader("./data/testWriterGeneralShoeInventory.json");
        try {
            ShoeInventory shoeInventory = reader.read();
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
