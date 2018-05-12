package View;

import Data.ReadAndWrite;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
public class ManagerView {

	public static void managerLoggedIn() throws IOException { //Level 1 Manager view

		int choice = -1;

		while (choice <= 0 || choice > 4) {
			try {
				Scanner input = new Scanner(System.in);

				System.out.println("------------------------------------------");
				System.out.println(" WELCOME TO CALSBERG ADMINISTRATOR VIEW! ");
				System.out.println("------------------------------------------");
				System.out.println("");
				System.out.println("What do you want to do? Press the respective number.");
				System.out.println("");
				System.out.println("-----------------------");
				System.out.println("|1| I would like to manage products");
				System.out.println("|2| I would like to view transactions");
				System.out.println("|3| Quit");
				System.out.println("-----------------------");

				choice = input.nextInt();

				switch (choice) {
				case 1:
					ManageProductView.ProductView(); //Go To Level 2 Manager view to view & Edit Products
					break;
				case 2:
					viewTransactions(); //Go To Level 2 Manager view to view finished transactions
					break;
				case 3:
					System.exit(0);
				default:
					System.out.print("Invalid input. Try again\n");
					continue;
				}
			} catch (InputMismatchException e) {
				System.out.print("Invalid input. \n");


			}

		}
	}



	public static void viewTransactions() throws IOException { //Level 2 Manager view to view transactions

		
		System.out.println("------------------------------------------");
		System.out.println("    WELCOME TO TRANSACTIONS VIEW!         ");
		System.out.println("------------------------------------------");
		System.out.println("");
		System.out.println("These are the transactions in our system");
		System.out.println("");
		// Printing out all information in alltransactions.txt
		Scanner input = ReadAndWrite.readDetails("alltransactions.txt");
		while (input.hasNextLine()) {
			System.out.println(input.nextLine());
		} 
		
		
		//Starting user interface after printing transactions
		int choice = -1;

		while (choice <= 0 || choice > 2) {
			try {
				input = new Scanner(System.in);

				System.out.println("");
				System.out.println("What would you like to do?");
				System.out.println("");
				System.out.println("-----------------------");
				System.out.println("|1| I would like to go back");
				System.out.println("|2| Quit");
				System.out.println("-----------------------");

				choice = input.nextInt();

				switch (choice) {
				case 1:
					managerLoggedIn(); //Go To Level 1 Manager view
					break;
				case 2:
					System.out.println("You are now logged off.");
					System.exit(0); //exit system
				default:
					System.out.print("Invalid input. Try again\n");
					continue;
				}
			} catch (InputMismatchException e) {
				System.out.print("Invalid input. \n");
				input.next();
			}

		}


	}

}
