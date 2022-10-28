package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class ShoeInventoryTest {

    ShoeInventory shoeInventory1;
    Shoe shoe1;
    Shoe shoe2;
    Shoe shoe3;
    Shoe shoe4;
    Shoe shoe5;
    Shoe shoe6;
    Shoe shoe7;
    Shoe shoe8;
    Shoe shoe9;
    Shoe shoe10;

    // Setup to create a new shoe inventory and add shoes to the collection as well as set dummy values
    @BeforeEach
    public void setup() {
        shoeInventory1 = new ShoeInventory();

        shoe1 = new Shoe("Jordan 1 Travis Scott");
        shoe2 = new Shoe("SB Dunk Strange Love");
        shoe3 = new Shoe("Human Race Holi");
        shoe4 = new Shoe("Off White Air Force 97");
        shoe5 = new Shoe("Balenciaga Speed 2.0");
        shoe6 = new Shoe("Amiri Bone Runner");
        shoe7 = new Shoe("Jordan 5 Off White");
        shoe8 = new Shoe("SB Dunk Sean Cliver");
        shoe9 = new Shoe("Balenciaga Triple S");
        shoe10 = new Shoe("Jordan 1 Low Travis Fragment");

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

        shoe4.setBrand("Nike");
        shoe4.setType("Low");
        shoe4.setRetailPrice(120.00);
        shoe4.setValue(1100.00);
        shoe4.setPersonalCollection(true);

        shoe5.setBrand("Balenciaga");
        shoe5.setType("Mid");
        shoe5.setRetailPrice(800.00);
        shoe5.setValue(750.00);
        shoe5.setPersonalCollection(true);

        shoe6.setBrand("Amiri");
        shoe6.setType("Low");
        shoe6.setRetailPrice(600.00);
        shoe6.setValue(400.00);
        shoe6.setPersonalCollection(false);

        shoe7.setBrand("Jordan");
        shoe7.setType("High");
        shoe7.setRetailPrice(210.00);
        shoe7.setValue(700.00);
        shoe7.setPersonalCollection(false);

        shoe8.setBrand("Nike");
        shoe8.setType("Low");
        shoe8.setRetailPrice(100.00);
        shoe8.setValue(1300.00);
        shoe8.setPersonalCollection(true);

        shoe9.setBrand("Balenciaga");
        shoe9.setType("Mid");
        shoe9.setRetailPrice(900.00);
        shoe9.setValue(1300.00);
        shoe9.setPersonalCollection(false);

        shoe10.setBrand("Jordan");
        shoe10.setType("Low");
        shoe10.setRetailPrice(120.00);
        shoe10.setValue(2100.00);
        shoe10.setPersonalCollection(true);

        shoeInventory1.getShoes().add(shoe1);
        shoeInventory1.getShoes().add(shoe2);
        shoeInventory1.getShoes().add(shoe3);
        shoeInventory1.getShoes().add(shoe4);
        shoeInventory1.getShoes().add(shoe7);
        shoeInventory1.getShoes().add(shoe8);
        shoeInventory1.getShoes().add(shoe10);
        shoeInventory1.setNumShoes(shoeInventory1.getShoes().size());
        double totalValue1 = 0;
        for (Shoe s: shoeInventory1.getShoes()) {
            totalValue1 += s.getValue();
        }
        shoeInventory1.setTotalValue(totalValue1);
        shoeInventory1.setNumShoesSold(3);
        shoeInventory1.getWishlist().add(shoe9);
        shoeInventory1.getWishlist().add(shoe6);

    }

    // to test the constructor of the ShoeInventory Class
    @Test
    public void TestConstructor() {
        ShoeInventory shoeInventory2 = new ShoeInventory();
        assertEquals(shoeInventory2.getShoes().size(), 0);
        assertEquals(shoeInventory2.getNumShoes(), 0);
        assertEquals(shoeInventory2.getNumShoesSold(), 0);
        assertEquals(shoeInventory2.getWishlist().size(), 0);
        assertEquals(shoeInventory2.getTotalValue(), 0);
    }

    // to test the addShoes method
    @Test
    public void testAddShoes() {
        shoeInventory1.addShoes(shoe5);
        assertTrue(shoeInventory1.getShoes().contains(shoe5));
        assertEquals(shoeInventory1.getNumShoes(),8);
        assertEquals(shoeInventory1.getTotalValue(), 9650);
    }

    // to test the removeShoes method
    @Test
    public void testRemoveShoes() {
        shoeInventory1.removeShoes(shoe2);
        assertFalse(shoeInventory1.getShoes().contains(shoe2));
        assertEquals(shoeInventory1.getNumShoes(), 6);
        assertEquals(shoeInventory1.getTotalValue(), 7700);
    }

    // to test the sellShoes method
    @Test
    public void testSellShoes() {
        assertFalse(shoeInventory1.sellShoes(shoe1));
        assertFalse(shoeInventory1.sellShoes(shoe6));
        assertTrue(shoeInventory1.sellShoes(shoe3));
        shoeInventory1.sellShoes(shoe7);
        assertFalse(shoeInventory1.getShoes().contains(shoe7));
        assertEquals(shoeInventory1.getNumShoes(), 5);
        assertEquals(shoeInventory1.getTotalValue(), 7600);
        assertEquals(shoeInventory1.getNumShoesSold(), 5);
    }

    // to test the addToWishlist method
    @Test
    public void testAddToWishlist() {
        assertFalse(shoeInventory1.addToWishlist(shoe1));
        assertFalse(shoeInventory1.addToWishlist(shoe9));
        assertTrue(shoeInventory1.getWishlist().contains(shoe9));
        assertTrue(shoeInventory1.addToWishlist(shoe5));
        assertTrue(shoeInventory1.getWishlist().contains(shoe5));
    }

    // to test the getInfo method
    @Test
    public void testGetInfo() {
        assertEquals("Shoes not found!", shoeInventory1.getInfo(shoe5));
        String shoe1Check = "Model Name: Jordan 1 Travis Scott\nBrand: Jordan\nTop: High\nRetail Price: 150.0\n" +
                "Current Value: 1900.0";
        assertEquals(shoeInventory1.getInfo(shoe1), shoe1Check);
    }


}