package main;

import java.io.IOException;

public class mains {
		
	public static void main(String[] args) {
		OrderList OL;
		try {
			OL = new OrderList();
			CoffeeShopIO CoffeeShop = new CoffeeShopIO("src/main/product.csv", OL);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
	
//		Path p = Paths.get("OrderList.txt").toAbsolutePath();
//		p.toFile();
//		Scanner myReader = new Scanner(p);
//		String data;
//		ArrayList<String> dataArray = new ArrayList<String>();
//		data = myReader.nextLine();
//		dataArray.add(data);
//		
//		while (myReader.hasNext()) {
//			data = myReader.nextLine();
//			dataArray.add(data);
//			System.out.println(data);
//		}
//		myReader.close();
//		
//		String lastEntry = dataArray.get(dataArray.size()-2); // get the last person in the list
//		
//		int pointer =0;
//		for (pointer = 0; pointer < lastEntry.length(); pointer++) {
//			if(lastEntry.charAt(pointer) == ',') break;
//		}
//		String NameNumber = lastEntry.substring(0,pointer);
//		int value = Integer.parseInt(NameNumber.replaceAll("[^0-9]", ""));
//		System.out.println(value);
//	
//	}
