package main;

public class Item {
	//Fields
	private String itemID;
	private String name;
	private String description;
	private int quantity;
	private double price;
	private double priceTotal;
	
	
	//Constructor
	public Item(String itemID, String name, String description, int quantity, double price) {
		try {
			setItemID(itemID);
		}
		catch(StringLengthException exception) {
			System.out.println(exception.getMessage());
		}
		
		try {
			setName(name);
		}
		catch(StringLengthException exception) {
			System.out.println(exception.getMessage());
		}
		
		try {
			setDescription(description);
		}
		catch(StringLengthException exception) {
			System.out.println(exception.getMessage());
		}
		
		setQuantity(quantity);
		
		setPrice(price);
	}

	
	//Get methods
	//Get ID of item
	public String getItemID() {
		return itemID;
	}
	
	//Get name of item
	public String getName() {
		return name;
	}
	
	//Get description of item
	public String getDescription() {
		return description;
	}
	
	//Get quantity of item
	public int getQuantity() {
		return quantity;
	}
	
	//Get price of item
	public double getPrice() {
		return price;
	}
	
	//Get total price of item
	public double getPriceTotal() {
		return priceTotal;
	}
	
	
	//Set methods
	//Set ID of item with limit of 7 characters
	public void setItemID(String itemID) throws StringLengthException {
		int maxSize = 7;
		if(itemID.length() <= maxSize) {
			this.itemID = itemID;
		}
		else {
			throw new StringLengthException("ID",maxSize);
		}
	}
	
	//Set name of item with limit of 16 characters
	public void setName(String name) throws StringLengthException {
		int maxSize = 16;
		if(name.length() <= maxSize) {
			this.name = name;
		}
		else {
			throw new StringLengthException("Name",maxSize);
		}
	}
	
	//Set description of item with limit of 25 characters
	public void setDescription(String description) throws StringLengthException {
		int maxSize = 25;
		if(description.length() <= maxSize) {
			this.description = description;
		}
		else {
			throw new StringLengthException("Description",maxSize);
		}
	}
	
	//Set quantity of item as absolute value
	public void setQuantity(int quantity) {
		if(quantity>=0) {
			this.quantity = quantity;
		}
		else {
			this.quantity = -quantity;
		}
		setPriceTotal();
	}
	
	//Set price of item as absolute value
	public void setPrice(double price) {
		if(price>=0) {
			this.price = price;
		}
		else {
			this.price = -price;
		}
		setPriceTotal();
	}
	
	//Set price total of item as product of price and quantity of item
	private void setPriceTotal() {
		priceTotal = price*quantity;
	}
	
	
	//Other methods
	//Increment quantity and total price of item
	public void addOne() {
		quantity++;
		setPriceTotal();
	}
	
	//Decrement quantity and total price of item
	public void subtractOne() {
		quantity--;
		setPriceTotal();
	}
	
	//Compare one item to another using item IDs
	public int compareTo(Item item) {
		return this.itemID.compareTo(item.getItemID());
	}
	
	//Check if two items are equal using item IDs
	public boolean equals(Item item) {
		return this.itemID.equals(item.getItemID());
	}
	
	//Find hash code of item using item ID
	public int hashCode() {
		return this.itemID.hashCode();
	}
}
