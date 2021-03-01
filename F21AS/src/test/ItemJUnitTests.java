package test;

import main.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ItemJUnitTests {
	Item item1, item2;
	
	
	@Before
	public void setup() {
		item1 = new Item("FOOD001","Chocolate","Delicious treat",2,20);
		item2 = new Item("FOOD002","Pizza","Delicious meal",10,30);
	}
	
	
	@Test
	public void items() {
		//Check fields of item 1
		assertTrue(item1.getItemID().equals("FOOD001"));
		assertTrue(item1.getItemName().equals("Chocolate"));
		assertTrue(item1.getItemDescription().equals("Delicious treat"));
		assertEquals(2,item1.getItemQuantity());
		assertTrue(item1.getItemPrice()==20);
		assertTrue(item1.getItemPriceTotal()==40);
		item1.addOne();
		assertEquals(3,item1.getItemQuantity());
		assertTrue(item1.getItemPriceTotal()==60);
		item1.subtractOne();
		assertEquals(2,item1.getItemQuantity());
		assertTrue(item1.getItemPriceTotal()==40);
		
		
		//Check fields of item 2
		assertTrue(item2.getItemID().equals("FOOD002"));
		assertTrue(item2.getItemName().equals("Pizza"));
		assertTrue(item2.getItemDescription().equals("Delicious meal"));
		assertEquals(10,item2.getItemQuantity());
		assertTrue(item2.getItemPrice()==30);
		assertTrue(item2.getItemPriceTotal()==300);
		
		//Compare items 1 and 2
		assertTrue(item1.compareTo(item2)<0);
		assertTrue(item2.compareTo(item1)>0);
		assertFalse(item1.equals(item2));
		assertFalse(item2.equals(item1));
		assertNotEquals(item1.hashCode(),item2.hashCode());
	}
	
	
	@Test
	public void changeFields() {
		//Changes fields of item 1
		try {
			item1.setItemID("FOOD002");
			item1.setItemName("Pizza");
			item1.setItemDescription("Delicious meal");
		}
		catch(StringLengthException exception) {
			fail();
		}
		item1.setItemQuantity(10);
		item1.setItemPrice(30);
		
		//Check fields of item 1
		assertTrue(item1.getItemID().equals("FOOD002"));
		assertTrue(item1.getItemName().equals("Pizza"));
		assertTrue(item1.getItemDescription().equals("Delicious meal"));
		assertEquals(10,item1.getItemQuantity());
		assertTrue(item1.getItemPrice()==30);
		assertTrue(item1.getItemPriceTotal()==300);

		//Compare items 1 and 2
		assertEquals(0,item1.compareTo(item2));
		assertEquals(0,item2.compareTo(item1));
		assertTrue(item1.equals(item2));
		assertTrue(item2.equals(item1));
		assertEquals(item1.hashCode(),item2.hashCode());
	}
	
	
	//Test exceptions for String fields and negative numbers for numeric fields
	@Test
	public void exceptionsNegativeNumbers() {
		try {
			item1.setItemID("FOOD0010");
			fail();
		}
		catch(StringLengthException exception) {
			assertTrue(exception.getMessage().equals("ID cannot be greater than 7 characters."));
		}

		try {
			item1.setItemName("Chocolate Ice Cream");
			fail();
		}
		catch(StringLengthException exception) {
			assertTrue(exception.getMessage().equals("Name cannot be greater than 16 characters."));
		}
		
		try {
			item1.setItemDescription("Delicious ice cream with chocolate flavour");
			fail();
		}
		catch(StringLengthException exception) {
			assertTrue(exception.getMessage().equals("Description cannot be greater than 25 characters."));
		}
		
		item1.setItemQuantity(-2);
		assertEquals(2,item1.getItemQuantity());
		assertTrue(item1.getItemPriceTotal()==40);
		
		item1.setItemPrice(-20);
		assertTrue(item1.getItemPrice()==20);
		assertTrue(item1.getItemPriceTotal()==40);
	}
	
	
	//Test exception for ItemID field
	@Test(expected = StringLengthException.class)
	public void IDException() throws StringLengthException {
		item1.setItemID("FOOD0010");
	}
	
	//Test exception for ItemName field
	@Test(expected = StringLengthException.class)
	public void nameException() throws StringLengthException {
		item1.setItemName("Chocolate Ice Cream");
	}
	
	//Test exception for ItemDescription field
	@Test(expected = StringLengthException.class)
	public void descriptionException() throws StringLengthException {
		item1.setItemDescription("Delicious ice cream with chocolate flavour");
	}
}