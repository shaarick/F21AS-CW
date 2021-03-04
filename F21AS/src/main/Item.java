package main;

/**
 * This class defines an item that a customer can order.
 * @author Andrew Manson - wam4 - H00267387
 *
 */
public class Item {
	/**
	 * Fields
	 */
	private String itemID;
	private String name;
	private String description;
	private int quantity;
	private double price;
	private double priceTotal;
	
	
	
	/**
	 * Constructor
	 * @param itemID
	 * @param name
	 * @param description
	 * @param quantity
	 * @param price
	 */
	public Item(String itemID, String name, String description, int quantity, double price) {
		try {
			setItemID(itemID);
		}
		catch(StringLengthException exception) {
			System.out.println(exception.getMessage());
			System.out.println("Item ID trimmed to 7 characters.");
			try {
				setItemID(itemID.substring(0,7));
			} catch (StringLengthException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			setName(name);
		}
		catch(StringLengthException exception) {
			System.out.println(exception.getMessage());
			System.out.println("Name trimmed to 16 characters.");
			try {
				setName(name.substring(0,16));
			} catch (StringLengthException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			setDescription(description);
		}
		catch(StringLengthException exception) {
			System.out.println(exception.getMessage());
			System.out.println("Description trimmed to 25 characters.");
			try {
				setDescription(description.substring(0,25));
			} catch (StringLengthException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		setQuantity(quantity);
		
		setPrice(price);
	}

	
	
	/**
	 * Get methods
	 */
	
	/**
	 * Get item ID
	 * @return itemID
	 */
	public String getItemID() {
		return itemID;
	}
	
	
	/**
	 * Get name of item
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	
	/**
	 * Get description of item
	 * @return description
	 */
	public String getDescription() {
		return description;
	}
	
	
	/**
	 * Get quantity of item
	 * @return quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	
	
	/**
	 * Get price of item
	 * @return price
	 */
	public double getPrice() {
		return price;
	}
	
	
	/**
	 * Get price total of item
	 * @return priceTotal
	 */
	public double getPriceTotal() {
		return priceTotal;
	}
	
	
	
	/**
	 * Set methods
	 */
	
	/**
	 * Set item ID with limit of 7 characters
	 * @param itemID
	 * @throws StringLengthException
	 */
	public void setItemID(String itemID) throws StringLengthException {
		int maxSize = 7;
		if(itemID.length() <= maxSize) {
			this.itemID = itemID;
		}
		else {
			throw new StringLengthException("ID",maxSize);
		}
	}
	
	
	/**
	 * Set name of item with limit of 16 characters
	 * @param name
	 * @throws StringLengthException
	 */
	public void setName(String name) throws StringLengthException {
		int maxSize = 16;
		if(name.length() <= maxSize) {
			this.name = name;
		}
		else {
			throw new StringLengthException("Name",maxSize);
		}
	}
	
	
	/**
	 * Set description of item with limit of 25 characters
	 * @param description
	 * @throws StringLengthException
	 */
	public void setDescription(String description) throws StringLengthException {
		int maxSize = 25;
		if(description.length() <= maxSize) {
			this.description = description;
		}
		else {
			throw new StringLengthException("Description",maxSize);
		}
	}
	
	
	/**
	 * Set quantity of item as absolute value
	 * @param quantity
	 */
	public void setQuantity(int quantity) {
		if(quantity>=0) {
			this.quantity = quantity;
		}
		else {
			this.quantity = -quantity;
		}
		setPriceTotal();
	}
	
	
	/**
	 * Set price of item as absolute value
	 * @param price
	 */
	public void setPrice(double price) {
		if(price>=0) {
			this.price = price;
		}
		else {
			this.price = -price;
		}
		setPriceTotal();
	}
	
	
	/**
	 * Set price total of item as product of price and quantity of item
	 */
	private void setPriceTotal() {
		priceTotal = price*quantity;
	}
	
	
	
	/**
	 * Other methods
	 */
	
	/**
	 * Increment quantity and total price of item
	 */
	public void addOne() {
		quantity++;
		setPriceTotal();
	}
	
	
	/**
	 * Decrement quantity and total price of item
	 */
	public void subtractOne() {
		quantity--;
		setPriceTotal();
	}
	
	
	/**
	 * Compare one item to another using item IDs
	 * @param item
	 * @return comparison of items
	 */
	public int compareTo(Item item) {
		return this.itemID.compareTo(item.getItemID());
	}
	
	
	/**
	 * Check if two items are equal using item IDs
	 * @param item
	 * @return boolean variable that is true if items are equal and false if they are not
	 */
	public boolean equals(Item item) {
		return this.itemID.equals(item.getItemID());
	}
	
	
	/**
	 * Find hash code of item using item ID
	 * @return hash code of item ID
	 */
	public int hashCode() {
		return this.itemID.hashCode();
	}
}
