package main;

import java.io.IOException;

public class mains {
		
	public static void main(String[] args) {
		OrderList OL;
		try {
			OL = new OrderList();
			CoffeeShopIO CoffeeShop = new CoffeeShopIO("F21AS/src/main/product.csv", OL);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
