package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * CoffeeShopIO Class.
 * @author Shariq Farooqui
 */
public class CoffeeShopIO {
    private MenuList ml;
    private OrderList ol;
    private CoffeeShopGUI gui;
    private String report;

    // Constructor
    public CoffeeShopIO(String filename) {
        readMenuFile(filename);
        readOrdersFile(filename);
        gui = new CoffeeShopGUI(ml, ol);
        //writeOrdersFile(filename);
        //createReport();
    }

    // Get and Set Methods
    public MenuList getMl() {
        return this.ml;
    }

    public void setMl(MenuList ml) {
        this.ml = ml;
    }

    public OrderList getOl() {
        return this.ol;
    }

    public void setOl(OrderList ol) {
        this.ol = ol;
    }

    public CoffeeShopGUI getGui() {
        return this.gui;
    }

    public void setGui(CoffeeShopGUI gui) {
        this.gui = gui;
    }

    public String getReport() {
        return this.report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    // Method to read raw menu file and create item objects.
    public void readMenuFile(String filename) {
        try {
            // Create a reader to read csv file. 
            BufferedReader reader = new BufferedReader(new FileReader("product.csv"));

            // Helps us skip the first row of the csv which contains column names.
            int lineToBeSkipped = 0;

            // Each line of the csv will be stored in this variable.
            String line = "";

            // Assign line from csv to variable and keep reading until EOF.
            while((line = reader.readLine()) != null) {

                // Skip column names.
                if(lineToBeSkipped != 0) {

                    // Split line into individual elements of the item constructor.
                    String[] lineSplit = line.split(",");
                    String itemID = lineSplit[1].substring(0,4) + lineSplit[0];
                    String name = lineSplit[2];
                    String description = lineSplit[3];
                    int quantity = 1;
                    String priceWithoutCurrencySymbol = lineSplit[4].replace("$", "");
                    double price = Integer.parseInt(priceWithoutCurrencySymbol);

                    // Create item from the line we read.
                    Item itemFromLine = new Item(itemID, name, description, quantity, price);

                    // Add item to menu list.
                    ml.addToList(itemFromLine);
                }
                // Counter to skip only the first row.
                lineToBeSkipped++;
            }
            // Close reader to prevent resource leak.
            reader.close();
        }
        // Finish try block with catch statement.
        catch (IOException error) {
            System.out.println("File not found.");
        }
    }

    public void readOrdersFile(String filename) {

    }

    public void writeOrdersFile(String filename) {

    }

    public void createReport() {
        try {
            // Create a new file. The parameter true allows for overwriting.
            FileWriter writer = new FileWriter("report.txt", true);
            String columns = "Menu Item, Number of times item ordered, Income";
            writer.write(columns);

            // Going to create rest of the report when I have other data.
        }
        // Finish try block with catch statement.
        catch (IOException error) {
            System.out.println("An error occured.");
            error.printStackTrace();
        }
    }
}