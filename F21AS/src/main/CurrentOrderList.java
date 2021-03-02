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
	
	private boolean discounts[] = {false, false, false};
	
	public CurrentOrderList() { currentOrderList = new HashSet<Item>(); };

	public Set<Item> getCurrentOrderList() {
		return currentOrderList;
	}

	public boolean[] getDiscounts() { return discounts; }

	public void setDiscounts(boolean discA, boolean discB, boolean discC) {
		discounts[0] = discA;
		discounts[1] = discB;
		discounts[2] = discC;
	}

	//add an element to the set
	public void addToList(Item mi) {
		for (Item coi : currentOrderList) {
        	if (mi.hashCode() == coi.hashCode()) {
        		coi.addOne();
        		System.out.println(coi.getName() + " already present in the order: +1 added to quantity.");
        		return;
        	}
        }
        currentOrderList.add(mi);
        System.out.println(mi.getName() + " added to the order.");
        return;
	}
	
	//add one to the quantity of the item selected in the order list
	public void addOne(Item coiIn) {
		for (Item coi : currentOrderList) {
        	if (coiIn.hashCode() == coi.hashCode()) {
        		coi.addOne();
        		System.out.println(coi.getName() + ": +1 added to quantity.");
        		return;
        	}
        }
        return;
	}
	
	//remove one from the quantity of the item selected in the order list
	public void substractOne(Item coiIn) {
		for (Item coi : currentOrderList) {
        	if (coiIn.hashCode() == coi.hashCode()) {
        		if (coi.getQuantity() == 1) {
        			currentOrderList.remove(coi);
        			System.out.println(coi.getName() + " deleted from the order.");
        		}
        		else {
        			coi.subtractOne();
        			System.out.println(coi.getName() + ": -1 removed from quantity");
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
        		coi.setQuantity(1);
        		System.out.println(coi.getName() + " deleted from the order.");
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
			total += coi.getPriceTotal();
		}
		total = calculateDiscounts(total);
		return total;
	}
	
	//determine the discounts applicable on the current order and apply them to the order's total
	public double calculateDiscounts(double total) {
		double beverageDiscount = 0;
		double merchDiscount = 0;
		int nbMerchArticles = 0;
		int nbTotalArticles = 0;

		for (Item coi : currentOrderList) {
			if (coi.getItemID().substring(0, 4).equals("BEVE") && coi.getQuantity() > 2) {
				beverageDiscount += (coi.getQuantity() / 3) * coi.getPrice();
			}
			if (coi.getItemID().substring(0, 4).equals("MERC")) {
				nbMerchArticles += coi.getQuantity();
				merchDiscount = coi.getPrice();
			}
			nbTotalArticles += coi.getQuantity();
		}

		//only if the number of merchandise items exceeds 3, we look for the cheapest
		//in order to reduce computation time if there is less than 3 merchandise items
		if (nbMerchArticles >= 3) {
			for (Item coi : currentOrderList) {
				if (coi.getItemID().substring(0, 4).equals("MERC") && coi.getPrice() < merchDiscount)
					merchDiscount = coi.getPrice();
			}
			
		}
		else
			merchDiscount = 0;

		//identical beverages discount: for 3 identical beverages bought, the third one is free
		if (beverageDiscount > 0) {
			discounts[0] = true;
			total -= beverageDiscount;
		}
		else
			discounts[0] = false;

		//merchandise items discount: for more than 3 merchandise items bought included, the cheapest one is free
		if (merchDiscount > 0) {
			discounts[1] = true;
			total -= merchDiscount;
		}
		else
			discounts[1] = false;

		//total number of articles discount: for more than 10 articles bought included, you get 10% off your order
		if (nbTotalArticles >= 10) {
			discounts[2] = true;
			total *= 0.9;
		}
		else
			discounts[2] = false;

		//all discounts can be combined
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
        	if (String.format("%-16s", co.getName()).equals(currentOrderItem.substring(0, 16)))
        		coi = co;
        }
		return (coi);
	}
	
	//return a String corresponding to the item ordered, used as part of the table presented in the GUI
    public String getCurrentOrderItemLine(Item currentOrderItem) {
        return (String.format("%-16s", currentOrderItem.getName()) + "      " + new DecimalFormat("00.00").format(currentOrderItem.getPrice()) + "$      " + String.format("%d", currentOrderItem.getQuantity()) + "      " + new DecimalFormat("00.00").format(currentOrderItem.getPriceTotal()) + "$");
    }
    
    //return a String corresponding to the discounts being currently applied on the order
    public String getDiscountsLine() {
    	return((discounts[0] ? "3 Identical Beverages Discount\n\n\n" : "") + (discounts[1] ? "3 Merchandise Items and More Discount\n\n\n" : "") + (discounts[2] ? "10 Items And More Discount" : ""));
    }
}
