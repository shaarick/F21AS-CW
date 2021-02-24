package test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import main.IncorrectItemForMenuList;
import main.Item;
import main.MenuList;

/**
 * JUnit tests for the GUI related methods
 * @author Nicolas JEAN - nj2000 - H00359359
 */
class MenuListJUnitTests {

	@Test
	@DisplayName("Testing the item's ID verification of the addToList method")
	void testAddToListItemID() throws IncorrectItemForMenuList {
		MenuList ml = new MenuList();
		Item mi = new Item("FOOD", "Croissant", "French Nature Pastry.", 1, 3.5);
		
		Throwable exception = assertThrows(IncorrectItemForMenuList.class, () -> ml.addToList(mi));
		assertEquals("Item ID is too short.", exception.getMessage(), "Setting an ItemID too short should not work.");
		
		Item mi2 = new Item("FOAD1", "Croissant", "French Nature Pastry.", 1, 3.5);
		Throwable exceptionB = assertThrows(IncorrectItemForMenuList.class, () -> ml.addToList(mi2));
		assertEquals("Item ID category identifier incorrect.", exceptionB.getMessage(), "Setting a wrong ItemID category identifier should not work.");
		
		Item mi3 = new Item("FOODA", "Croissant", "French Nature Pastry.", 1, 3.5);
		Throwable exceptionC = assertThrows(IncorrectItemForMenuList.class, () -> ml.addToList(mi3));
		assertEquals("Item ID doesn't have a number identifier.", exceptionC.getMessage(), "Setting no ItemID number identifier should not work.");
		
		Item mi4 = new Item("FOOD365", "Croissant", "French Nature Pastry.", 1, 3.5);
		ml.addToList(mi4);
		assertEquals("FOOD365", (ml.getMenuItem(String.format("%-16s", "Croissant"))).getItemID(), "Setting a correctly formated Item ID should work.");
    }

	@Test
	@DisplayName("Testing the item's Name verification of the addToList method")
	void testAddToListItemName() throws IncorrectItemForMenuList {
		MenuList ml = new MenuList();
		Item mi = new Item("FOOD365", "", "French Nature Pastry.", 1, 3.5);
		
		Throwable exception = assertThrows(IncorrectItemForMenuList.class, () -> ml.addToList(mi));
		assertEquals("Item name blank.", exception.getMessage(), "Setting no Item name should not work.");
		
		Item mi2 = new Item("FOOD365", "Croissant", "French Nature Pastry.", 1, 3.5);
		ml.addToList(mi2);
		assertEquals("Croissant", (ml.getMenuItem(String.format("%-16s", "Croissant"))).getItemName(), "Setting a non null Item name should work.");
    }

	@Test
	@DisplayName("Testing the item's Price verification of the addToList method")
	void testAddToListItemPrice() throws IncorrectItemForMenuList {
		MenuList ml = new MenuList();
		Item mi = new Item("FOOD445", "Croissant", "French Nature Pastry.", 1, -2.5);
		
		Throwable exception = assertThrows(IncorrectItemForMenuList.class, () -> ml.addToList(mi));
		assertEquals("Item price negative.", exception.getMessage(), "Setting a negative price should not work.");
		
		Item mi2 = new Item("FOOD365", "Croissant", "French Nature Pastry.", 1, 3.5);
		ml.addToList(mi2);
		assertEquals(3.5, (ml.getMenuItem(String.format("%-16s", "Croissant"))).getItemPrice(), "Setting a correctly formated price should work.");
    }

	@Test
	@DisplayName("Testing getMenuItem method")
	void testgetMenuItem() throws IncorrectItemForMenuList {
	    MenuList menuList = new MenuList();
	    Item mi = new Item("FOOD445", "Croissant", "French Nature Pastry.", 1, 3.5);
	    Item mi2 = new Item("BEVE445", "Water", "The original.", 1, 1.5);
	    Item mi3 = new Item("MERC448", "Sunglasses", "Sun can harm.", 1, 17.5);

	    String menuItemToLookForTopList = "Croissant";
		String menuItemToLookForMiddleList = "Water";
		String menuItemToLookForBottomList = "Sunglasses";
		String menuItemToLookForDoesntExist = "Candy";

        menuList.addToList(mi);
        menuList.addToList(mi2);
        menuList.addToList(mi3);

		assertEquals(menuItemToLookForTopList, menuList.getMenuItem(String.format("%-16s", menuItemToLookForTopList)).getItemName(), "Looking for an item at the menu's top should work.");
		assertEquals(menuItemToLookForMiddleList, menuList.getMenuItem(String.format("%-16s", menuItemToLookForMiddleList)).getItemName(), "Looking for an item at the menu's middle should work.");
		assertEquals(menuItemToLookForBottomList, menuList.getMenuItem(String.format("%-16s", menuItemToLookForBottomList)).getItemName(), "Looking for an item at the menu's bottom should work.");
		assertEquals("", menuList.getMenuItem(String.format("%-16s", menuItemToLookForDoesntExist)).getItemName(), "Looking for an item that doesn't exist should work.");
    }

}
