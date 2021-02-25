package test;

import main.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ItemJUnitTests {
	Item item1;
	
	@Before
	public void setup() {
		item1 = new Item("FOOD001","Chocolate","Delicious treat",2,20);
		
	}
	
	@Test
	public void tests() {
		assertTrue(item1.getItemID().equals("FOOD001"));
		assertTrue(item1.getItemName().equals("Chocolate"));
		assertTrue(item1.getItemDescription().equals("Delicious treat"));
		assertEquals(2,item1.getItemQuantity());
		assertTrue(item1.getItemPrice()==20);
		assertTrue(item1.getItemPriceTotal()==40);
		

		try {
			item1.setItemID("FOOD002");
			item1.setItemName("Pizza");
			item1.setItemDescription("Delicious meal");
			
			assertTrue(item1.getItemID().equals("FOOD002"));
			assertTrue(item1.getItemName().equals("Pizza"));
			assertTrue(item1.getItemDescription().equals("Delicious meal"));
		}
		catch(StringLengthException exception) {
			fail();
		}
		
		item1.setItemQuantity(10);
		item1.setItemPrice(30);
		assertEquals(10,item1.getItemQuantity());
		assertTrue(item1.getItemPrice()==30);
		assertTrue(item1.getItemPriceTotal()==300);
		
		item1.setItemQuantity(-20);
		item1.setItemPrice(-20);
		assertEquals(20,item1.getItemQuantity());
		assertTrue(item1.getItemPrice()==20);
		assertTrue(item1.getItemPriceTotal()==400);
		

		try {
			item1.setItemID("FOOD0010");
			fail();
		}
		catch(StringLengthException exception) {
			System.out.println(exception.getMessage());
		}

		try {
			item1.setItemName("Chocolate Ice Cream");
			fail();
		}
		catch(StringLengthException exception) {
			System.out.println(exception.getMessage());
		}
		
		try {
			item1.setItemDescription("Delicious ice cream with chocolate flavour");
			fail();
		}
		catch(StringLengthException exception) {
			System.out.println(exception.getMessage());
		}
	}
	
	
	
	@Test(expected = StringLengthException.class)
	public void IDException1() throws StringLengthException {
		item1.setItemID("FOOD0020");
	}
	
	
	@Test(expected = StringLengthException.class)
	public void nameException1() throws StringLengthException {
		item1.setItemName("Chocolate Ice Cream");
	}
	
	
	@Test(expected = StringLengthException.class)
	public void descriptionException1() throws StringLengthException {
		item1.setItemDescription("Delicious ice cream with chocolate flavour");
	}
}
