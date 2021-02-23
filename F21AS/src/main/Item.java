package main;

public class Item {
	private String itemID;
	private String name;
	private String description;
	private int quantity;
	private double price;
	private double priceTotal;
	
	
	public Item(String itemID, String name, String description, int quantity, double price) {
		this.itemID = itemID;
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
		this.priceTotal = price*quantity;
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
	
	
	
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}


	public void setItemName(String name) {
		this.name = name;
	}
	
	
	public void setItemDescription(String description) {
		this.description = description;
	}


	public void setItemQuantity(int quantity) {
		if(quantity>0)
			this.quantity = quantity;
	}


	public void setItemPrice(double price) {
		this.price = price;
	}


	public void setItemPriceTotal(double priceTotal) {
		this.priceTotal = priceTotal;
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
