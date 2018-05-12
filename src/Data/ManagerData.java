package Data;

import model.Manager;

import java.util.ArrayList;
import java.util.Scanner;

public class ManagerData {

	// Get manager information from a string
	public static Manager getManager(String line) {
		Manager managerFromFile = new Manager();

		// Look for every ";" and turns the values into strings
		String[] values = line.split(";");

		// Change the String type into the correct format
		managerFromFile.setUsername(values[0]);
		managerFromFile.setPassword(values[1]);

		return managerFromFile;

	}
	// Get all manager details from a file
	public static ArrayList<Manager> getManagerLoginDetails(String file) {
		ArrayList<Manager> managerList = new ArrayList<Manager>();
		Scanner input = ReadAndWrite.readDetails(file); // reads input from
													// manager.txt file

		// checking each line
		while (input.hasNextLine()) {
			managerList.add(getManager(input.nextLine()));// passing each line
															// to the method
															// getManager which
															// returns a
															// customer
		} // then added to a ArrayList
		return managerList;
	}
}
