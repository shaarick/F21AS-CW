package test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import main.Item;
import main.CurrentOrderList;

/**
 * JUnit tests for the GUI related methods
 * @author Nicolas JEAN - nj2000 - H00359359
 */
class CurrentOrderListJUnitTests {

	@Test
	@DisplayName("Testing addToList method")
	void testAddToList() {
		CurrentOrderList currentOrderList = new CurrentOrderList();
		Item mi = new Item("FOOD445", "Croissant", "French Nature Pastry.", 1, 3.5);
		
		currentOrderList.addToList(mi);
		assertEquals("Croissant", String.format("%s", currentOrderList.getList().elementAt(1)).substring(0,9), "Adding an item to the list should work.");
		
		currentOrderList.addToList(mi);
		assertEquals("2", String.format("%s", currentOrderList.getList().elementAt(1)).substring(34,35), "Adding an item already present in the list should increase its quantity by 1.");
    }
	
	@Test
	@DisplayName("Testing substractOne method")
	void testSubstractOne() {
		CurrentOrderList currentOrderList = new CurrentOrderList();
		Item mi = new Item("FOOD445", "Croissant", "French Nature Pastry.", 1, 3.5);
		
		currentOrderList.addToList(mi);
		currentOrderList.addToList(mi);
		currentOrderList.substractOne(mi);
		assertEquals("1", String.format("%s", currentOrderList.getList().elementAt(1)).substring(34,35), "Substracting one quantity to an item having more than 1 quantity should reduce that quantity by 1.");
	    
		currentOrderList.substractOne(mi);
		int itemIndexAfter = currentOrderList.getList().indexOf("Croissant             03.50$      1      03.50$");
		assertEquals(-1, itemIndexAfter, "Substracting one quantity to an item having exactly 1 quantity should remove that itemm from the list.");
    }
	
	@Test
	@DisplayName("Testing removeItem method")
	void testRemoveItem() {
		CurrentOrderList currentOrderList = new CurrentOrderList();
		Item mi = new Item("FOOD445", "Croissant", "French Nature Pastry.", 1, 3.5);
		
		currentOrderList.addToList(mi);
		currentOrderList.removeItem(mi);
		int itemIndexAfter = currentOrderList.getList().indexOf("Croissant             03.50$      1      03.50$");
		assertEquals(-1, itemIndexAfter, "Removing an item from the list should work.");
    }
	
	@Test
	@DisplayName("Testing calculateTotal method")
	void testCalculateTotal() {
		CurrentOrderList currentOrderList = new CurrentOrderList();
		Item mi = new Item("FOOD445", "Croissant", "French Nature Pastry.", 1, 3.5);
		Item mi2 = new Item("BEVE446", "Diet Coke", "From one famous brand.", 1, 2);
		Item mi3 = new Item("MERC445", "T-shirt", "Why not go bold ?", 1, 14.99);

		currentOrderList.addToList(mi);
		currentOrderList.addToList(mi2);
		currentOrderList.addToList(mi3);
		currentOrderList.addToList(mi);
		assertEquals("23.99", String.format("%s", currentOrderList.calculateTotal()).substring(0,5), "Calculating the total amount of the list should work.");
    }

}