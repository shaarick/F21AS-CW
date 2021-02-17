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
	public void addToList(Item item) {
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
        return (String.format("%-16s", menuItem.getItemName()) + "  " + String.format("%-25s", menuItem.getItemDescription()) + "  " + String.format("%.2f$", menuItem.getItemPrice()));
    }
}
