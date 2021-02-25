package test;

import main.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ItemJUnitTests {
	Item item1, item2;
	
	@Before
	public void setup() {
		item1 = new Item("FOOD001","Chocolate","Delicious food",2,20);
		item2 = new Item("FOOD002","Chocolate Ice Cream","Delicious Ice Cream made of chocolate",2,20);
	}
	
	@Test
	public void tests() {
		System.out.println(item2.getItemPriceTotal());
		
		
		assertNotNull(item1);
		assertTrue(item1.getItemID().equals("FOOD001"));
		assertTrue(item1.getItemName().equals("Chocolate"));
		assertTrue(item1.getItemDescription().equals("Delicious food"));
		assertEquals(2,item1.getItemQuantity());
		assertTrue(item1.getItemPrice()==20);
		assertTrue(item1.getItemPriceTotal()==40);
		
		item1.setItemQuantity(10);
		item1.setItemPrice(30);
		assertEquals(10,item1.getItemQuantity());
		assertTrue(item1.getItemPrice()==30);
		assertTrue(item1.getItemPriceTotal()==300);
		
		item1.setItemQuantity(-10);
		item1.setItemPrice(-30);
		assertEquals(10,item1.getItemQuantity());
		assertTrue(item1.getItemPrice()==30);
		assertTrue(item1.getItemPriceTotal()==300);
	}
}
