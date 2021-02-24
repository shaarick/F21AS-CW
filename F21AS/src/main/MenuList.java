package main;
import java.util.*;
import javax.swing.DefaultListModel; 

/**
 * MenuList class
 * This class allows the creation of the global menu,
 * the filtering and display of the 3 types of item menu,
 * and the search for an item in the global menu.
 * @author Nicolas JEAN - nj2000 - H00359359
 */

public class MenuList {
	private static Set<Item> menuList;
	
	public MenuList() { menuList = new HashSet<Item>(); };
	
	//add an element to the set
	public void addToList(Item item) throws IncorrectItemForMenuList {
		//checking the length of the item's ID
    	if (item.getItemID().trim().length() < 5) {
        	throw new IncorrectItemForMenuList("Item ID is too short.");
        }
    	
    	//checking the format of the item's ID: starting with FOOD, BEVE or MERC
        if (!item.getItemID().substring(0, 4).equals("FOOD") && !item.getItemID().substring(0, 4).equals("BEVE") && !item.getItemID().substring(0, 4).equals("MERC")) {
        	throw new IncorrectItemForMenuList("Item ID category identifier incorrect.");
        }
        
        //checking the format of the item's ID: having at least a number after category identifier
        try {
        	int i = Integer.parseInt(item.getItemID().substring(4, 5)); 
        }
        catch (NumberFormatException e) {
        	throw new IncorrectItemForMenuList("Item ID doesn't have a number identifier.");
        }
        
        //checking the length of the item's name
        if (item.getItemName().trim().length() == 0) {
        	throw new IncorrectItemForMenuList("Item name blank.");
        }
        
        //checking the value of item's quantity
        if (item.getItemQuantity() < 1) {
        	throw new IncorrectItemForMenuList("Item quantity negative or null.");
        }
        
        //checking the value of the item's price
        if (item.getItemPrice() < 0) {
        	throw new IncorrectItemForMenuList("Item price negative.");
        }
        
		for (Item mi : menuList) {
	        if (item.hashCode() == mi.hashCode()) {
	        	System.out.println(mi.getItemName() + " already present in the menu.");
	        	return;
	        }
	    }
	    menuList.add(item);
	    System.out.println(item.getItemName() + " added to the menu.");
	    return;
	}
	
	//return a List<String> of all the Food items, each one represented by a String
	public DefaultListModel getListFood() {
		DefaultListModel listModel = new DefaultListModel();
		listModel.addElement("Item              Description                Price");
        for (Item mi : menuList) {
        	if (mi.getItemID().substring(0, 4).equals("FOOD"))
        		listModel.addElement(getMenuItemLine(mi));
        }
        return listModel;
	}
	
	//return a List<String> of all the Beverage items, each one represented by a String
	public DefaultListModel getListBeverage() {
		DefaultListModel listModel = new DefaultListModel();
		listModel.addElement("Item              Description                Price");
        for (Item mi : menuList) {
        	if (mi.getItemID().substring(0, 4).equals("BEVE"))
        		listModel.addElement(getMenuItemLine(mi));
        }
        return listModel;
	}
	
	//return a List<String> of all the Merchandise items, each one represented by a String
	public DefaultListModel getListMerchandise() {
		DefaultListModel listModel = new DefaultListModel();
		listModel.addElement("Item              Description                Price");
        for (Item mi : menuList) {
        	if (mi.getItemID().substring(0, 4).equals("MERC"))
        		listModel.addElement(getMenuItemLine(mi));
        }
        return listModel;
	}
	
	//search and return the MenuItem corresponding to the String of that item
	public Item getMenuItem(String menuItem) {
		Item mir = new Item("", "", "", 0, 0);
		for (Item mi : menuList) {
        	if (String.format("%-16s", mi.getItemName()).equals(menuItem.substring(0, 16)))
        		mir = mi;
        }
		return (mir);
	}
	
	//return a String corresponding to the menu item, used as part of the table presented in the GUI
	public String getMenuItemLine(Item menuItem) {
		if (menuItem.getItemDescription().length() <= 25)
			return (String.format("%-16s", menuItem.getItemName()) + "  " + String.format("%-25s", menuItem.getItemDescription()) + "  " + String.format("%.2f$", menuItem.getItemPrice()));
		else
			return (String.format("%-16s", menuItem.getItemName()) + "  " + menuItem.getItemDescription().substring(0,22) + "...  " + String.format("%.2f$", menuItem.getItemPrice()));
    }
}
