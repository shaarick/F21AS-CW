package test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import main.MenuList;

/**
 * JUnit tests for the GUI related methods
 * @author Nicolas JEAN - nj2000 - H00359359
 */
class MenuListJUnitTests {

	@Test
	@DisplayName("Testing getMenuItem method")
	void testgetMenuItem() {
	    MenuList menuList = new MenuList();
		String menuItemToLookForTopList = "Croissant";
		String menuItemToLookForMiddleList = "Water";
		String menuItemToLookForBottomList = "Sunglasses";

		assertEquals(menuItemToLookForTopList, menuList.getMenuItem(String.format("%-16s",menuItemToLookForTopList)).getItemName(), "Looking for an item at the menu's top should work.");
		assertEquals(menuItemToLookForMiddleList, menuList.getMenuItem(String.format("%-16s",menuItemToLookForMiddleList)).getItemName(), "Looking for an item at the menu's middle should work.");
		assertEquals(menuItemToLookForBottomList, menuList.getMenuItem(String.format("%-16s",menuItemToLookForBottomList)).getItemName(), "Looking for an item at the menu's bottom should work.");
    }

}