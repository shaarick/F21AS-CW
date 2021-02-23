package test;

import main.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ItemJUnitTests {
	Item item;
	
	@Before
	public void setup() {
		item = new Item("1","Chocolate Ice Cream","Ice cream made of chocolate",2,20);
	}
	
	@Test
	public void tests() {
		assertTrue(item.getItemID().equals("1"));
	}
}
