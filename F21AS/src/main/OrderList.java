package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
/**
 * Initialise in MAIN
 * OrderList to append to txt file with current orders
 * Change orderlist to read from file and store data once initialised
 * then have a seperate append to orderlist
 * 
 * TODO:
 * summary, orderlist, create Junit, and write about testing
 * 
 * @author rashid
 *
 */
public class OrderList {

	private final Path p = Paths.get("OrderList.txt").toAbsolutePath(); //stores the path (works on any system)
	private ArrayList<String> dataArray; //stores all of the orderlist.txt
	private float cost; //stores the final updated cost

	public OrderList() throws IOException {
		dataArray = new ArrayList<String>();
		dataArray = ReadFile();
	}
	
	
	
	private ArrayList<String> ReadFile() throws IOException {
		
		Scanner myReader;
			myReader = new Scanner(p);
			String data;
			data = myReader.nextLine();
			dataArray.add(data);

			while (myReader.hasNext()) { //loop through the txt file
				data = myReader.nextLine();
				dataArray.add(data);
			}
			myReader.close();

			//get the final cost:
			String finalCost = dataArray.get(dataArray.size()-1);
			System.out.println(finalCost);
			this.cost = Float.parseFloat(finalCost.substring(12,finalCost.length()));
		return dataArray;
	}



	public void addCurrentOrderToList(Customer c, CurrentOrderList col) {
			IntermediateWrite(c,col);
	}
	
	/**
	 * * 1. Puts all file into arraylist string
	 * 2. Removes the cost, updates it
	 * 3. adds all the new orders to the array list
	 * 4. adds the final cost to the arraylist
	 * 5. writes the arraylist to the orderlist.txt file
	 * @param c
	 * @param col
	 */
	private void IntermediateWrite(Customer c, CurrentOrderList col) {
		
		
			this.cost += col.calculateTotal(); //UPDATE FINAL COST

			//Remove the final cost from the list and add it again at the end after adding all the NEW ORDERS
			dataArray.remove(dataArray.size()-1);
			
			//add all the new items to the dataArray 
			for (Item items : col.getCurrentOrderList()) {

				appendToOrderList(c.GetName(),c.getTimeStamp(),items);
				
			}
			
			
			//add the final cost back into the array
			
			dataArray.add("Final cost:"+" "+this.cost);
			
			System.out.println("FinalUpdatedCost = "+ this.cost);
			
			try(FileWriter fw = new FileWriter(p.toString(), false); //FALSE MEANS RE WRITE
					BufferedWriter bw = new BufferedWriter(fw);
					PrintWriter out = new PrintWriter(bw))
			{
				for (int i = 0; i < dataArray.size()-1; i++) {
					out.println(dataArray.get(i));	
				}
				out.println("Final cost: "+ this.cost);
			} catch (IOException e) {
				System.err.println("CANT WRITE TO FILE, DOESNT EXIST");
				e.printStackTrace();
			}
		
		}
	
	/**
	 * Creates a formatted string to place into the file using the current order items
	 * @param getName
	 * @param timeStamp
	 * @param items1
	 * @return
	 */
	private String appendToOrderList(String getName, String timeStamp, Item items1){
		
		String ORDER = null;
		
		for (int i = 0; i < items1.getQuantity(); i++) {
			ORDER = getName + "," +" "+ timeStamp +"," +" "+ items1.getName();
			dataArray.add(ORDER);
		} 
		
		return ORDER;
	}
	
	public float getFinalCost() {
		return this.cost;
	}
	
	public ArrayList<String> getData(){
		return this.dataArray;
	}

}