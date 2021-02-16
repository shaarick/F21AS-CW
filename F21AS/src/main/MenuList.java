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
	
	public MenuList() { menuList = new HashSet<Item>(); populate(); };
	
	//used to simulate MenuItems in a future collection
	public void populate() {
		Item mi1 = new Item("FOOD445", "Croissant", "French Nature Pastry.", 1, 3.5);
        menuList.add(mi1);
        Item mi2 = new Item("FOOD446", "Pain au chocolat", "Chocolate Viennoiserie.", 1, 4);
        menuList.add(mi2);
        Item mi3 = new Item("FOOD447", "Choux a la creme", "Pastry with custard.", 1, 5.5);
        menuList.add(mi3);
        Item mi4 = new Item("FOOD448", "Eclair", "Pastry with chocolate.", 1, 6);
        menuList.add(mi4);
        Item mi5 = new Item("BEVE445", "Water", "The original.", 1, 1.5);
        menuList.add(mi5);
        Item mi6 = new Item("BEVE446", "Diet Coke", "From one famous brand.", 1, 2);
        menuList.add(mi6);
        Item mi7 = new Item("BEVE447", "Beer", "No selection.", 1, 2);
        menuList.add(mi7);
        Item mi8 = new Item("BEVE448", "White Wine", "From the Sunny Valley.", 1, 3.5);
        menuList.add(mi8);
        Item mi9 = new Item("MERC445", "T-shirt", "Why not go bold ?", 1, 14.99);
        menuList.add(mi9);
        Item mi10 = new Item("MERC446", "Cap", "Protect your head.", 1, 7.5);
        menuList.add(mi10);
        Item mi11 = new Item("MERC447", "Coffe Cup", "Keep energized.", 1, 12.99);
        menuList.add(mi11);
        Item mi12 = new Item("MERC448", "Sunglasses", "Sun can harm.", 1, 17.5);
        menuList.add(mi12);
        Item mi13 = new Item("MERC449", "Hat", "", 1, 18);
        menuList.add(mi13);
        
    }
	
	//return a List<String> of all the Food items, each one represented by a String
	public DefaultListModel getListFood() {
		DefaultListModel listModel = new DefaultListModel();
		listModel.addElement("Item              Description                Price");
        for (Item mi : menuList) {
        	if (mi.getItemID().substring(0, 4).equals("FOOD"))
        		listModel.addElement(mi.getMenuItemLine());
        }
        return listModel;
	}
	
	//return a List<String> of all the Beverage items, each one represented by a String
	public DefaultListModel getListBeverage() {
		DefaultListModel listModel = new DefaultListModel();
		listModel.addElement("Item              Description                Price");
        for (Item mi : menuList) {
        	if (mi.getItemID().substring(0, 4).equals("BEVE"))
        		listModel.addElement(mi.getMenuItemLine());
        }
        return listModel;
	}
	
	//return a List<String> of all the Merchandise items, each one represented by a String
	public DefaultListModel getListMerchandise() {
		DefaultListModel listModel = new DefaultListModel();
		listModel.addElement("Item              Description                Price");
        for (Item mi : menuList) {
        	if (mi.getItemID().substring(0, 4).equals("MERC"))
        		listModel.addElement(mi.getMenuItemLine());
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
}