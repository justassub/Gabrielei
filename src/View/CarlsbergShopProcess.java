package View;

import model.Manager;
import model.User;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

// CarlsbergShopProcess contains the processes of the Carlsberg webshop, including the beginning
// of the program and the process of the manager and customer
public class CarlsbergShopProcess {
    // startCarlsbergMain runs the beginning of the program to see if the user is a customer or manager
    // It throws IOException due to removeLine used inside of the methods called

    public static void startCarlsbergShopProcess() throws IOException {
        // Initialize the menu choice of the user to -1
        int choice = -1;
        // Run a while loop when the menu choice of the user differs from 0,1,2
        while (choice < 0 || choice > 2) {
            // Try the following
            try {
                // Take input from the user
                Scanner input = new Scanner(System.in);
                // Menu choices
                System.out.println("-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-");
                System.out.println("                   WELCOME                 ");
                System.out.println("");
                System.out.println("             TO CARLSBERG WEBSHOP!         ");
                System.out.println("-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-");
                System.out.println("");
                System.out.println("What do you want to do? Press one of the following:");
                System.out.println("");
                System.out.println("Press 0 If you are a customer");
                System.out.println("Press 1 If you are a manager");
                System.out.println("Press 2 If you want to exit");
                System.out.println("");
                // Choice is the next integer from the user
                choice = input.nextInt();
                // Switch statement for the choices
                switch (choice) {
                    // For input 0, run the customer process
                    case 0:
                        customerProcess(new User());
                        // Break loop if this case is chosen
                        break;
                    // For input 1, run the manager process
                    case 1:
                        managerProcess(new Manager());
                        break;
                    // For input 2, exit the shop
                    case 2:
                        System.out.println("You have exited the shop.");
                        break;
                    default:
                        // If input is invalid, print statement
                        System.out.print("Invalid input. ");
                }
                // If the input is not a digit, there is an InputMismatchException
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. ");
            }
        }
    }

    // The process of the customer in the Carlsberg webshop, throws IOException due to removeLine method called through methods called
    public static void customerProcess(User user) throws IOException {
        // Create a User object
        User newCustomer = user;
        // Initialize choice as -1 so we can run the while loop to start with
        int choice = -1;
        // While loop runs when choice is not either 0, 1 or 2
        while (choice < 0 || choice > 2) {
            // Try the following
            try {
                // Create a new Scanner to read input from the user
                Scanner input = new Scanner(System.in);
                // Prompt user input
                System.out.println("-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-");
                System.out.println("          WELCOME TO THE CUSTOMER PAGE! ");
                System.out.println("-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-");
                System.out.println("");
                System.out.println("What do you want to do? Press one of the following:");
                System.out.println("");
                System.out.println("Press 0 If you want to register");
                System.out.println("Press 1 If you want to login");
                System.out.println("Press 2 If you want to exit");
                System.out.println("");
                // choice is the next integer written by the user
                choice = input.nextInt();
                // Switch statement for choice
                switch (choice) {
                    case 0:
                        // Run the registration process of the newCustomer
                        if (newCustomer instanceof Manager) {
                            System.out.println("Please enter secret Key for managers");
                            System.out.println("If you fail - program will shut down");

                            String secretKey = input.next();
                            //Secret key for managers .
                            if (!secretKey.equals("111")) {
                                System.exit(1);
                            }

                        }
                        CustomerView.customerRegistration(newCustomer);
                        // When the customer has a registered user, run the post-registration method

                        CustomerView.postReg(newCustomer);
                        break;
                    case 1:
                        // If the user is already registered, run the post-registration method
                        CustomerView.postReg(newCustomer);
                        break;
                    case 2:
                        // If the user wants to exit the shop
                        System.out.println("You have exited the shop.");
                        break;
                    default:
                        System.out.print("Invalid input. ");
                }
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. ");
            }
        }
    }

    // The process of the manager
    public static void managerProcess(Manager manager) throws IOException {
        customerProcess(manager);
        // The manager already has a user, so he first has to login

        // Run the process needed after the manager has logged in
        ManagerView.managerLoggedIn();
    }


}
