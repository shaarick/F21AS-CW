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
		this.quantity = quantity;
	}


	public void setItemPrice(double price) {
		this.price = price;
	}


	public void setItemPriceTotal(double priceTotal) {
		this.priceTotal = priceTotal;
	}
	
	


	public Object getMenuItemLine() {
		// TODO Auto-generated method stub
		return null;
	}


	public void addOne() {
		// TODO Auto-generated method stub
		
	}


	public void substractOne() {
		// TODO Auto-generated method stub
		
	}


	public Object getCurrentOrderItemLine() {
		// TODO Auto-generated method stub
		return null;
	}
}
