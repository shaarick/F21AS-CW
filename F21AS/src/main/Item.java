package main;

public class Item {
	private String itemID;
	private String name;
	private String description;
	private int quantity;
	private double price;
	private double priceTotal;
	
	
	public Item(String itemID, String name, String description, int quantity, double price) {
		try {
			setItemID(itemID);
			setItemName(name);
			setItemDescription(description);
			setItemQuantity(quantity);
			setItemPrice(price);
		}
		catch(StringLengthException exception) {
			System.out.println(exception.getMessage());
		}
	}


	public String getItemID() {
		return itemID;
	}


	public String getItemName() {
		return name;
	}
	
	
	public String getItemDescription() {
		return description;
	}


	public int getItemQuantity() {
		return quantity;
	}


	public double getItemPrice() {
		return price;
	}


	public double getItemPriceTotal() {
		return priceTotal;
	}
	
	
	
	public void setItemID(String itemID) throws StringLengthException {
		int maxSize = 7;
		if(itemID.length() <= maxSize) {
			this.itemID = itemID;
		}
		else {
			throw new StringLengthException(maxSize);
		}
	}


	public void setItemName(String name) throws StringLengthException {
		int maxSize = 16;
		if(name.length() <= maxSize) {
			this.name = name;
		}
		else {
			throw new StringLengthException(maxSize);
		}
	}
	
	
	public void setItemDescription(String description) throws StringLengthException {
		int maxSize = 25;
		if(description.length() <= maxSize) {
			this.description = description;
		}
		else {
			throw new StringLengthException(maxSize);
		}
	}


	public void setItemQuantity(int quantity) {
		if(quantity>=0) {
			this.quantity = quantity;
		}
		else {
			this.quantity = -quantity;
		}
		setItemPriceTotal();
	}


	public void setItemPrice(double price) {
		if(price>=0) {
			this.price = price;
		}
		else {
			this.price = -price;
		}
		setItemPriceTotal();
	}


	public void setItemPriceTotal() {
		priceTotal = price*quantity;
	}
	
	
	

	public void addOne() {
		quantity++;
		priceTotal+=price;
	}


	public void substractOne() {
		quantity--;
		priceTotal-=price;
	}
	
	
	public int compareTo(Item item) {
		return this.itemID.compareTo(item.getItemID());
	}
	
	
	public boolean equals(Item item) {
		return this.itemID.equals(item.getItemID());
	}
	
	
	public int hashCode() {
		return this.itemID.hashCode();
	}
}
