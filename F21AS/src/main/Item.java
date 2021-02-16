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
}
