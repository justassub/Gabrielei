package View;

import Data.CustomerDatabase;
import model.Manager;
import model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LogInView {

    public static void customerLogin(User user) throws IOException {
        ArrayList<User> customerDetails = CustomerDatabase.getAllCustomerDetails("users.txt");
        String username;
        String password;
        boolean loggedIn = false;
        int counter = 0;

        while (!loggedIn && counter < 3) {
            Scanner input = new Scanner(System.in);
            ++counter;
            System.out.println("-------------------------------------");
            System.out.println("YOU CAN NOW LOG IN TO THE SHOP");
            System.out.println("-------------------------------------");
            System.out.println("Your username was given to you during registration\nEnter username:");
            username = input.nextLine();
            user.setUsername(username);
            System.out.println("Your password was given to you during registration\nEnter password: ");
            password = input.nextLine();
            user.setPassword(password);
            for (int i = 0; i < customerDetails.size(); ++i) {
                if (!loggedIn && user.getUsername().equals((customerDetails.get(i)).getUsername()) && user.getPassword().equals((customerDetails.get(i)).getPassword()) && user.getClass() == (customerDetails.get(i)).getClass()) {
                    user.setFirstName((customerDetails.get(i)).getFirstName());
                    user.setLastName((customerDetails.get(i)).getLastName());
                    user.setcprNumber((customerDetails.get(i)).getcprNumber());
                    user.setTelephone((customerDetails.get(i)).getTelephone());
                    user.setAddress((customerDetails.get(i)).getAddress());
                    user.setCreditCard((customerDetails.get(i)).getCreditCard());
                    System.out.println("");
                    System.out.println("YOU ARE LOGGED IN");
                    System.out.println("");
                    if (user instanceof Manager) {
                        ManagerView.managerLoggedIn();
                    }
                    loggedIn = true;
                }
            }
            if (!loggedIn) {
                System.out.printf("Username or password is not correct, you have used %d of 3 tries.\n", counter);
            }

            if (counter == 3 && !loggedIn) {
                System.out.print("Try again in a few hours.");
                System.exit(0);
            }
        }

    }


}
