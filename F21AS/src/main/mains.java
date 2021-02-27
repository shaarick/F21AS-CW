package main1;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class mains {
		
	public static void main(String[] args) throws IOException {
		Path p = Paths.get("OrderList.txt").toAbsolutePath();
		p.toFile();
		Scanner myReader = new Scanner(p);
		String data;
		ArrayList<String> dataArray = new ArrayList<String>();
		data = myReader.nextLine();
		dataArray.add(data);
		
		while (myReader.hasNext()) {
			data = myReader.nextLine();
			dataArray.add(data);
			System.out.println(data);
		}
		myReader.close();
		
		String lastEntry = dataArray.get(dataArray.size()-2); // get the last person in the list
		
		int pointer =0;
		for (pointer = 0; pointer < lastEntry.length(); pointer++) {
			if(lastEntry.charAt(pointer) == ',') break;
		}
		String NameNumber = lastEntry.substring(0,pointer);
		int value = Integer.parseInt(NameNumber.replaceAll("[^0-9]", ""));
		System.out.println(value);
	
	}
}