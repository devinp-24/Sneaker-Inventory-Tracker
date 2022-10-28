package ui;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new MyShoeInventoryApp();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found! Application cannot run!");
        }
    }
}
