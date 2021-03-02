package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * CoffeeShopIO Class.
 * <p>
 * In this class, we will read menu items from a file and initialize them as
 * Item objects. These items are then added to a MenuList object which is passed
 * to our GUI. It also receives an OrderList object from the Main/Manager class,
 * which contains details about orders placed prior to opening the GUI, i.e.
 * they they are existing orders. This OrderList object will also be passed to
 * the GUI, so that any new orders can be appended to it. On exit, this class
 * will use the OrderList file to create the final report.
 * <p>
 * 
 * @author Shariq Farooqui
 */
public class CoffeeShopIO {
    private MenuList ml;
    private OrderList orderList;
    private CoffeeShopGUI gui;

    /**
     * Constructor for CoffeeShopIO Class.
     * 
     * @param filename Name of file from which menu items will be extracted.
     * @param orders   Object containing existing orders.
     * 
     */
    public CoffeeShopIO(String filename, OrderList orders) {
        readMenuFile(filename);
        orderList = orders;
        // readOrderList();
        gui = new CoffeeShopGUI(ml, orderList);
    }

    // Get and Set Methods for the instance variables.

    public MenuList getMl() {
        return this.ml;
    }

    public void setMl(MenuList ml) {
        this.ml = ml;
    }

    public OrderList getOrderList() {
        return this.orderList;
    }

    public void setOrderList(OrderList orderList) {
        this.orderList = orderList;
    }

    public CoffeeShopGUI getGui() {
        return this.gui;
    }

    public void setGui(CoffeeShopGUI gui) {
        this.gui = gui;
    }

    /**
     * Method used to read menu file and create Item objects.
     * 
     * @param filename Name of the menu file.
     */
    public void readMenuFile(String filename) {
        try {
            // Create a reader to read csv file.
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            // Counter to skip the first row of csv that contains column names.
            int lineToBeSkipped = 0;

            // Each line of the csv will be stored in this variable.
            String line = "";

            // Assign line from csv to variable and keep reading until EOF.
            while ((line = reader.readLine()) != null) {

                // Read file if it is not the first row
                if (lineToBeSkipped != 0) {
                    // Split line into individual elements of the item constructor.
                    String[] lineSplit = line.split(",");

                    String itemID = lineSplit[0];
                    String name = lineSplit[2];
                    String description = lineSplit[3];

                    // Each line can represent only one item, hence quantity is always 1.
                    int quantity = 1;

                    /*
                     * File contains price with dollar symbol. We remove it and then parse the
                     * string to a Double
                     */
                    String priceWithoutCurrencySymbol = lineSplit[4].replace("$", "");
                    double price = Integer.parseInt(priceWithoutCurrencySymbol);

                    // Create Item object using the individual elements in the line we read.
                    Item itemFromLine = new Item(itemID, name, description, quantity, price);

                    // Add item to menu list.
                    try {
                    	ml.addToList(itemFromLine);
                    } catch (IncorrectItemForMenuList error) {
                    	System.out.println("Incorrect item; not added to MenuList");
                    }
                // Counter to skip only the first row.
                lineToBeSkipped++;
            }
            // Close reader to prevent resource leak.
            reader.close();
        }
        }
        // Finish try block with catch statement.
        catch (IOException error) {
            System.out.println("File not found.");
        }
    }

    /**
     * Static method used to create final report when GUI is closed.
     * @param menu All the items available for purchase in the shop.
     * @param orders All the orders placed within this shop.
     */
    public static void createReport(MenuList menu, OrderList orders) {
        try {
            // Create a new file. The parameter true allows for appending.
            FileWriter writer = new FileWriter("report.txt", true);

            // Write first row containing column names to the report.
            String columnName = "Menu Item \t Number of times item ordered \t Income from Item";
            writer.write(columnName);

            // Loop over each item
            for (Item item : menu) {
                // Counter to store number of times an item appeared in the orders list.
                int itemCount = 0;

                // Loop over each element of the orders ArrayList
                for (String line : orders.getData()) {
                    /*
                     * Last line always begins with final, but that line has no Item Objects so we
                     * skip it
                     */
                    if (line.substring(0, 6) != "Final") {
                        /*
                         * Get index of item name, which appears after the second occurance of ","
                         * character
                         */
                        int indexOfItem = line.indexOf(",", (line.indexOf(",") + 1));
                        // Get item as a string from Order file
                        String stringItem = line.substring(indexOfItem + 1).trim();
                        // If the item in the orders object matches the menu item, increase count:
                        if (item.getName() == stringItem) {
                            itemCount++;
                        }
                    }
                }

                /*
                 * At this point, we took an item from the menu and matched it against all items
                 * in the orders ArrayList. We now have the number of times each item was
                 * ordered. To write a line in the file, we need item name, quantity and revenue
                 */
                String itemName = item.getName();
                Double itemRevenue = itemCount * item.getPrice();

                // This string will contain relevant all item details
                String itemLine = itemName + "\t" + itemCount + "\t" + itemRevenue + "\n";
                writer.write(itemLine);
            }
            // Write the final line of report
            String finalLine = "Total Revenue (inc. discounts): " + orders.getFinalCost();
            writer.write(finalLine);

            // Close writer to prevent resource leak.
            writer.close();
        } catch (IOException e) {
            System.out.println("Report already exists");
            System.out.println(e.getStackTrace());
        }
    }

}