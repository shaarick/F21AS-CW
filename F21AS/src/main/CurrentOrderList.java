package main;
import java.text.DecimalFormat;
import java.util.*;
import javax.swing.DefaultListModel; 

/**
 * CurrentOrderList class
 * This class allows the creation of the set for the current order,
 * its management, its display, and search through it.
 * @author Nicolas JEAN - nj2000 - H00359359
 */

public class CurrentOrderList {
	private static Set<Item> currentOrderList;
	
	public CurrentOrderList() { currentOrderList = new HashSet<Item>();};
	
	//add an element to the set
	public void addToList(Item mi) {
		for (Item coi : currentOrderList) {
        	if (mi.hashCode() == coi.hashCode()) {
        		coi.addOne();
        		System.out.println(coi.getItemName() + " already present in the order: +1 added to quantity.");
        		return;
        	}
        }
        currentOrderList.add(mi);
        System.out.println(mi.getItemName() + " added to the order.");
        return;
	}
	
	//add one to the quantity of the item selected in the order list
	public void addOne(Item coiIn) {
		for (Item coi : currentOrderList) {
        	if (coiIn.hashCode() == coi.hashCode()) {
        		coi.addOne();
        		System.out.println(coi.getItemName() + ": +1 added to quantity.");
        		return;
        	}
        }
        return;
	}
	
	//remove one from the quantity of the item selected in the order list
	public void substractOne(Item coiIn) {
		for (Item coi : currentOrderList) {
        	if (coiIn.hashCode() == coi.hashCode()) {
        		if (coi.getItemQuantity() == 1) {
        			currentOrderList.remove(coi);
        			System.out.println(coi.getItemName() + " deleted from the order.");
        		}
        		else {
        			coi.substractOne();
        			System.out.println(coi.getItemName() + ": -1 removed from quantity");
        		}
        		
        		return;
        	}
        }
        return;
	}
	
	//remove an item from the set
	public void removeItem(Item coiIn) {
		for (Item coi : currentOrderList) {
        	if (coiIn.hashCode() == coi.hashCode()) {
        		currentOrderList.remove(coi);
        		coi.setItemQuantity(1);
        		System.out.println(coi.getItemName() + " deleted from the order.");
        		return;
        	}
        }
        return;
	}
	
	//remove all items from the set
	public void removeAllItems() {
		currentOrderList.clear();
	}
	
	//evaluate the total of the order
	public double calculateTotal() {
		double total = 0;
		for (Item coi : currentOrderList) {
			total+=coi.getItemPriceTotal();
		}
		return total;
	}
	
	//return a List<String> of all the items in the order, each one represented by a String
	public DefaultListModel getList() {
		DefaultListModel listModel = new DefaultListModel();
		listModel.addElement("Item                  Price    Quantity  Total");
        for (Item c : currentOrderList) {
        	listModel.addElement(getCurrentOrderItemLine(c));
        }
        return listModel;
	}
	
	//search and return the CurrentOrderItem corresponding to the String of that item
	public Item getCurrentOrderItem(String currentOrderItem) {
		Item coi = new Item("", "", "", 0, 0);
		for (Item co : currentOrderList) {
        	if (String.format("%-16s", co.getItemName()).equals(currentOrderItem.substring(0, 16)))
        		coi = co;
        }
		return (coi);
	}
	
	//return a String corresponding to the item ordered, used as part of the table presented in the GUI
    public String getCurrentOrderItemLine(Item currentOrderItem) {
        return (String.format("%-16s", currentOrderItem.getItemName()) + "      " + new DecimalFormat("00.00").format(currentOrderItem.getItemPrice()) + "$      " + String.format("%d", currentOrderItem.getItemQuantity()) + "      " + new DecimalFormat("00.00").format(currentOrderItem.getItemPriceTotal()) + "$");
    }
}
