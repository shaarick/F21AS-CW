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

		assertEquals("0", String.format("%s", currentOrderList.calculateTotal()).substring(0,1), "Calculating the total amount of the list empty should work.");
		
		currentOrderList.addToList(mi);
		currentOrderList.addToList(mi2);
		currentOrderList.addToList(mi3);
		currentOrderList.addToList(mi);
		assertEquals("23.99", String.format("%s", currentOrderList.calculateTotal()).substring(0,5), "Calculating the total amount of the list containing items should work.");
    }
	
	@Test
	@DisplayName("Testing the beverage discount of the calculateDiscounts method")
	void testCalculateDiscountsBeverage() {
		CurrentOrderList currentOrderList = new CurrentOrderList();
		Item mi = new Item("FOOD445", "Croissant", "French Nature Pastry.", 1, 3.5);
		Item mi2 = new Item("BEVE446", "Diet Coke", "From one famous brand.", 1, 2);
		Item mi3 = new Item("BEVE445", "Water", "The original.", 1, 1.5);

		currentOrderList.addToList(mi);
		currentOrderList.addToList(mi2);
		currentOrderList.addToList(mi2);
		currentOrderList.addToList(mi3);
		assertEquals(
				String.format("%s", (mi.getPriceTotal() + mi2.getPriceTotal() + mi3.getPriceTotal())).substring(0,3),
				String.format("%s", currentOrderList.calculateTotal()).substring(0,3),
				"Calculating the discount with 2 identical beverages shouldn't change the total amount.");
		
		currentOrderList.addToList(mi2);
		assertEquals(
				String.format("%s", (mi.getPriceTotal() + mi2.getPriceTotal() - mi2.getPrice() + mi3.getPriceTotal())).substring(0,3),
				String.format("%s", currentOrderList.calculateTotal()).substring(0,3),
				"Calculating the discount with 3 identical beverages should substract the price of one to the total.");
		
		currentOrderList.addToList(mi2);
		currentOrderList.addToList(mi2);
		currentOrderList.addToList(mi2);
		currentOrderList.addToList(mi2);
		assertEquals(
				String.format("%s", (mi.getPriceTotal() + mi2.getPriceTotal() - 2 * mi2.getPrice()  + mi3.getPriceTotal())).substring(0,3),
				String.format("%s", currentOrderList.calculateTotal()).substring(0,3),
				"Calculating the discount with 7 identical beverages should substract the price of two to the total.");
		}

	@Test
	@DisplayName("Testing the merchandise discount of the calculateDiscounts method")
	void testCalculateDiscountsMerchandise() {
		CurrentOrderList currentOrderList = new CurrentOrderList();
		Item mi = new Item("FOOD445", "Croissant", "French Nature Pastry.", 1, 3.5);
		Item mi2 = new Item("BEVE445", "Water", "The original.", 1, 1.5);
		Item mi3 = new Item("MERC445", "T-shirt", "Why not go bold ?", 1, 14.99);
		Item mi4 = new Item("MERC447", "Coffee Cup", "Keep energized.", 1, 12.99);
		Item mi5 = new Item("MERC446", "Cap", "Protect your head.", 1, 7.5);

		currentOrderList.addToList(mi);
		currentOrderList.addToList(mi2);
		currentOrderList.addToList(mi3);
		currentOrderList.addToList(mi4);
		assertEquals(
				String.format("%s", (mi.getPriceTotal() + mi2.getPriceTotal() + mi3.getPriceTotal() + mi4.getPriceTotal())).substring(0,5),
				String.format("%s", currentOrderList.calculateTotal()).substring(0,5),
				"Calculating the discount with 2 merchandise items shouldn't change the total amount.");
		
		currentOrderList.addToList(mi5);
		assertEquals(
				String.format("%s", (mi.getPriceTotal() + mi2.getPriceTotal() + mi3.getPriceTotal() + mi4.getPriceTotal() + mi5.getPriceTotal() - mi5.getPrice())).substring(0,5),
				String.format("%s", currentOrderList.calculateTotal()).substring(0,5),
				"Calculating the discount with 3 merchandise items should substract the price of the cheapest of them to the total.");
		
		currentOrderList.addToList(mi3);
		assertEquals(String.format("%s", (mi.getPriceTotal() + mi2.getPriceTotal() + mi3.getPriceTotal() + mi4.getPriceTotal() + mi5.getPriceTotal() - mi5.getPrice())).substring(0,5),
				String.format("%s", currentOrderList.calculateTotal()).substring(0,5),
				"Calculating the discount with 4 merchandise items should still substract the price of the cheapest of them to the total.");
		
		currentOrderList.addToList(mi5);
		currentOrderList.addToList(mi5);
		assertEquals(
				String.format("%s", (mi.getPriceTotal() + mi2.getPriceTotal() + mi3.getPriceTotal() + mi4.getPriceTotal() + mi5.getPriceTotal() - mi5.getPrice())).substring(0,5),
				String.format("%s", currentOrderList.calculateTotal()).substring(0,5),
				"Calculating the discount with 5 merchandise items, of which 3 are identical and the cheapest, should still substract the price of one cheapest to the total.");
	}

	@Test
	@DisplayName("Testing the total number of items discount of the calculateDiscounts method")
	void testCalculateDiscountsTotalNbItems() {
		CurrentOrderList currentOrderList = new CurrentOrderList();
		Item mi = new Item("FOOD445", "Croissant", "French Nature Pastry.", 1, 3.5);
		Item mi2 = new Item("BEVE445", "Water", "The original.", 1, 1.5);
		Item mi3 = new Item("MERC447", "Coffee Cup", "Keep energized.", 1, 12.99);

		currentOrderList.addToList(mi);
		currentOrderList.addToList(mi);
		currentOrderList.addToList(mi);
		currentOrderList.addToList(mi2);
		currentOrderList.addToList(mi3);
		assertEquals(
				String.format("%s", (mi.getPriceTotal() + mi2.getPriceTotal() + mi3.getPriceTotal())).substring(0,5),
				String.format("%s", currentOrderList.calculateTotal()).substring(0,5),
				"Calculating the discount with 5 items in total shouldn't change the total amount.");
		
		currentOrderList.addToList(mi);
		currentOrderList.addToList(mi);
		currentOrderList.addToList(mi);
		currentOrderList.addToList(mi);
		currentOrderList.addToList(mi);
		assertEquals(
				String.format("%s", (mi.getPriceTotal() + mi2.getPriceTotal() + mi3.getPriceTotal()) * 0.9).substring(0,5),
				String.format("%s", currentOrderList.calculateTotal()).substring(0,5),
				"Calculating the discount with 10 items in total should reduce the total amount by 10%.");
		
		currentOrderList.addToList(mi2);
		currentOrderList.addToList(mi3);
		assertEquals(
				String.format("%s", (mi.getPriceTotal() + mi2.getPriceTotal() + mi3.getPriceTotal()) * 0.9).substring(0,5),
				String.format("%s", currentOrderList.calculateTotal()).substring(0,5),
				"Calculating the discount with 12 items in total should still reduce the total amount by 10%.");
	}

	@Test
	@DisplayName("Testing all the discounts of the calculateDiscounts method")
	void testCalculateDiscountsAll() {
		CurrentOrderList currentOrderList = new CurrentOrderList();
		Item mi = new Item("FOOD445", "Croissant", "French Nature Pastry.", 8, 3.5);
		Item mi2 = new Item("BEVE446", "Diet Coke", "From one famous brand.", 7, 2);
		Item mi3 = new Item("BEVE445", "Water", "The original.", 2, 1.5);
		Item mi4 = new Item("MERC445", "T-shirt", "Why not go bold ?", 2, 14.99);
		Item mi5 = new Item("MERC447", "Coffee Cup", "Keep energized.", 2, 12.99);
		Item mi6 = new Item("MERC446", "Cap", "Protect your head.", 3, 7.5);

		currentOrderList.addToList(mi);
		currentOrderList.addToList(mi2);
		currentOrderList.addToList(mi3);
		currentOrderList.addToList(mi4);
		currentOrderList.addToList(mi5);
		currentOrderList.addToList(mi6);

		assertEquals("100.76", String.format("%s", currentOrderList.calculateTotal()).substring(0,6), "Calculating the discount should substract the price of two identical beverages, one cheapest merchandise item, and reduce the total by 10%.");
	}

}
