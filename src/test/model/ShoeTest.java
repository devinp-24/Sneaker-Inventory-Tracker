package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShoeTest {

    Shoe shoe1;
    Shoe shoe2;
    Shoe shoe3;

    // setup to create new shoes for tests
    @BeforeEach
    public void setup() {
        shoe1 = new Shoe("Jordan 1 Travis Scott");
        shoe2 = new Shoe("SB Dunk Strange Love");
        shoe3 = new Shoe("Human Race Holi");

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
    }

    // to test the constructor of the Shoe Class
    @Test
    public void testConstructor() {
        Shoe shoe4 = new Shoe("Kobe 5");
        assertEquals("Kobe 5", shoe4.getName());
        assertEquals("Custom", shoe4.getBrand());
        assertEquals("Low", shoe4.getType());
        assertEquals(0, shoe4.getRetailPrice());
        assertEquals(0, shoe4.getValue());
        assertTrue(shoe4.getPersonalCollection());
    }

    // to test the getInfo method
    @Test
    public void getInfoTest() {
        String shoe1Check = "Model Name: Jordan 1 Travis Scott\nBrand: Jordan\nTop: High\nRetail Price: 150.0\n" +
                "Current Value: 1900.0";
        assertEquals(shoe1Check, shoe1.getInfo());

        String shoe2Check = "Model Name: SB Dunk Strange Love\nBrand: Nike\nTop: Low\nRetail Price: 100.0\n" +
                "Current Value: 1200.0";
        assertEquals(shoe2Check, shoe2.getInfo());

        String shoe3Check = "Model Name: SB Dunk Strange Love\nBrand: Nike\nTop: Low\nRetail Price: 100.0\n" +
                "Current Value: 1200.0";
        assertFalse(shoe3Check == shoe3.getInfo());
    }

    // to test the getPersonalCollection getter method
    @Test
    public void personalCollectionTest() {
        assertTrue(shoe1.getPersonalCollection() == true);
        assertEquals(shoe2.getPersonalCollection(), true);
        assertFalse(shoe3.getPersonalCollection() == true);
    }



}
