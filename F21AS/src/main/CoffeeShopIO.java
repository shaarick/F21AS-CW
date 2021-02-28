package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * CoffeeShopIO Class.
 * 
 * @author Shariq Farooqui
 */
public class CoffeeShopIO {
    private MenuList ml;
    private OrderList ol;
    private CoffeeShopGUI gui;
    private String report;
    private double totalIncome;

    // Constructor
    public CoffeeShopIO(String filename) {
        readMenuFile(filename);
        readOrdersFile(filename);
        gui = new CoffeeShopGUI(ml, ol);
        // createReport();
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

    public double getTotalIncome() {
        return this.totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    // Method to read raw menu file and create item objects.
    public void readMenuFile(String filename) {
        try {
            // Create a reader to read csv file.
            BufferedReader reader = new BufferedReader(new FileReader("product.csv"));

            // Counter which elps us skip the first row of the csv that contains column
            // names.
            int lineToBeSkipped = 0;

            // Each line of the csv will be stored in this variable.
            String line = "";

            // Assign line from csv to variable and keep reading until EOF.
            while ((line = reader.readLine()) != null) {

                // Skip column names.
                if (lineToBeSkipped != 0) {

                    // Split line into individual elements of the item constructor.
                    String[] lineSplit = line.split(",");
                    String itemID = lineSplit[0];
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
        try {
            // Create a reader to read txt file.
            BufferedReader reader2 = new BufferedReader(new FileReader("OrderList.txt"));

            // Each line of the csv will be stored in this variable.
            String line = "";

            while ((line = reader2.readLine()) != null) {
                // Split line into order details
                String[] lineSplit = line.split(",");

                if (lineSplit.length > 1) {
                    String name = lineSplit[0];
                    String timeStamp = lineSplit[1].trim();
                    String item = lineSplit[2].trim();
                    // ol.appendToOrderList(name, timeStamp, item);
                } else {
                    // Convert last line to a String:
                    String lastLine = Arrays.toString(lineSplit);
                    // Extract the number:
                    String stringNumber = lastLine.substring(12, lastLine.length() - 1);
                    // Convert to double and assign to income variable.
                    totalIncome = Double.parseDouble(stringNumber);

                }
            }
            // Close reader to prevent resource leak.
            reader2.close();
        } catch (IOException error) {
            System.out.println("File not found.");
        }
    }

    public void createReport() {
        try {
            // Create a new file. The parameter true allows for appending.
            FileWriter writer = new FileWriter("report.txt", true);
            String columns = "Menu Item \t Number of times item ordered \t Income";
            writer.write(columns);

            // Loop over each item
            for (Item i : ml) {
                // Get item name and set default ordered quantity to zero
                String itemLine = "";
                String itemName = i.getItemName();
                int itemCount = 0;
                String line = "";

                try {
                    // Using the reader, loop over every line and count how many items an item appears in orders file
                    BufferedReader reader3 = new BufferedReader(new FileReader("OrderList.txt"));
                    while ((line = reader3.readLine()) != null) {
                        String[] lineSplit = line.split(",");
                        if (lineSplit.length > 1) {
                            String fileItem = lineSplit[2].trim();
                            // If menulist item (i) matches 
                            if (fileItem == itemName) {
                                // Then increase count by one
                                itemCount++;
                            }
                        }
                    }
                    // Calculate income earned from just this item, price times quantity
                    Double income = itemCount * i.getItemPrice();
                    // Add line to report
                    itemLine += itemName + "\t" + itemCount + "\t" + income + "\n";
                    writer.write(itemLine);
                } catch (IOException error) {
                    System.out.println("File not found.");
                }
            }
            // Only works if Rashid's method updates income properly. Otherwise I can keep track of it manually as well.
            String inc = "Total income for all orders: " + totalIncome;
            writer.write(inc);
        }
        // Finish try block with catch statement.
        catch (IOException error) {
            System.out.println("An error occured.");
            error.printStackTrace();
        }
    }
}