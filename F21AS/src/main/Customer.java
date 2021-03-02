package main1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
/**
 * 
 * @author rashid
 * @see main.F21AS.src.main.
 *
 */
public class Customer {

	private String ts;
	private String Name;

	public Customer(String name) {
		setTimeStamp();
		try {
			generatenewcustomer(name);
		} catch (IOException e) {
			this.Name = "ErrorName001";
			System.out.println("CANT FIND ORDERLIST FILE, name will be set to ERRORNAME001");
			e.printStackTrace();
		}
	}

	public Customer() {
		setTimeStamp();
		try {
			generatenewcustomer();
		} catch (IOException e) {
			this.Name = "ErrorName001";
			System.out.println("CANT FIND ORDERLIST FILE, name will be set to ERRORNAME001");
			e.printStackTrace();
		}
	}

	/**
	 * Generates a new ID for a customer depending on the last entry
	 * @throws IOException
	 */
	public void generatenewcustomer() throws IOException {

		setTimeStamp();

		int value = getIDFromFile();

		setUndefinedName(value);

	}
	/**
	 * 
	 * @return value - the last ID of the Last entry
	 * @throws IOException
	 */
	private int getIDFromFile() throws IOException {
		Path p = Paths.get("OrderList.txt").toAbsolutePath();
		p.toFile();
		Scanner myReader = new Scanner(p);
		String data;
		ArrayList<String> dataArray = new ArrayList<String>();
		data = myReader.nextLine();
		dataArray.add(data);

		while (myReader.hasNext()) { //loop through the txt file
			data = myReader.nextLine();
			dataArray.add(data);
			//System.out.println(data);
		}
		myReader.close();

		String lastEntry = dataArray.get(dataArray.size()-2); // get the last person in the list

		int pointer =0;

		for (pointer = 0; pointer < lastEntry.length(); pointer++) { //loop through and find the number
			if(lastEntry.charAt(pointer) == ',') break;
		}

		String NameNumber = lastEntry.substring(0,pointer);
		int value = Integer.parseInt(NameNumber.replaceAll("[^0-9]", "")); //get the number
		return value++;
	}

	/**
	 * If given a name, it will generate a new name and ID for the new customer
	 * @param name
	 * @throws IOException
	 */
	private void generatenewcustomer(String name) throws IOException {

		setTimeStamp();
		int value = getIDFromFile();
		SetName(name,value);
	}

	/**
	 * Sets the name of the customer when given the name string and the ID number
	 * @param name2
	 * @param value
	 */
	public void SetName(String name2, int value) {
		if(value>=100) {
			this.Name = name2+value;
		}

		else if (value<100) {
			this.Name = name2+value;
		}
		else if(value<10) {
			this.Name = name2+value;
		}
	}

	/**
	 * If no name is provided the system will generate a new name
	 * @param value - the ID of the last entry in the orderlist
	 */
	private void setUndefinedName(int value) {
		if(value>=100) {
			this.Name = "undefinedName"+value;
		}

		else if (value<100) {
			this.Name = "undefinedName0"+value;
		}
		else if(value<10) {
			this.Name = "undefinedName00"+value;
		}
	}
	/**
	 * Sets the timestamp for the customer initialization
	 * @see com.java.sql.Timestamp;
	 */
	private void setTimeStamp(){
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime()); 
		this.ts = ts.toString();
	}

	public String getTimeStamp() {
		return this.ts;
	}

	public String GetName() {
		return Name;
	}
}
