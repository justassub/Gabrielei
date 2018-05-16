package View;

import model.User;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class CustomerView {

    // What happens if a customer has completed the registration process and
    // thus has a user
    // Takes a User object as a parameter and throws IOException due to
    // selectAndConfirmProducts
    public static void postReg(User customer) throws IOException {
        // Prompt the customer to login
        LogInView.customerLogin(customer);
        // Display available products
        // Select which products to buy and confirm
        UserAddProducts.whatDoYouWantToDo(customer);
        // Confirm personal details
//        confirmCustomerDetails(customer);
//        // Print receipt of purchased items
//        viewReceipt();
    }

    /**
     * This class works well.
     * .
     */
    // What happens if a customer does not have a registered user already. Takes
    // a User object as a parameter
    public static User customerRegistration(User user) {
        // Create scanner to read input from user
        Scanner input = new Scanner(System.in);

        // User input for first name
        System.out.println("");
        System.out.println("YOU ARE NOW REGISTERING AS A NEW " + user.getClass());
        System.out.println("");
        System.out.println("Your first name must be at least 1 character (to use for username):"); //SITA GALIM ISIMT
        // Set the first name to input, first name must be a word with at least
        // 1 character, checked from validRegex
        user.setFirstName(validRegex("\\w{1,}", "Enter your first name"));
        // User input for last name
        System.out.println("Your last name must be at least 3 characters (to use for username/password): "); //SITA GALIM ISIMT
        // Set the last name to input, last name must be a word with at least 3
        // characters, checked from validRegex
        user.setLastName(validRegex("\\w{3,}", "Enter your last name"));
        // User input for CPR
        System.out.println("Your CPR-number must have the format 123456-1234");
        // Set CPR number through the method that checks if CPR number is valid,
        // not using validRegex here as
        // we check for valid birthdays including leap year in validCPR
        user.setcprNumber(validCPR());
        // User input for phone number
        System.out.println("Your phone number must have the format 12345678");
        // Set the phone number to input, phone number must be a digit of length
        // 8, checked from validRegex
        user.setTelephone(validRegex("\\d{8}", "Enter your phone number"));
        // User input for address
        System.out.print("Enter your address: ");
        // Set address to input
        user.setAddress(input.nextLine());
        // User input for credit card number
        System.out.println("Your credit card number must have the format 1234-1234-1234-1234");
        // Set the credit card number through the method that checks if
        // the credit card number is valid
        user.setCreditCard(validCreditCard());
        // Set user name and password
        /**
         * Justas S: set username and password : take
         */
        user.createUserName();
        user.createPassword();
        System.out.println("You are now registered.");
        System.out.println();
        System.out.println("Your username is " + user.getUsername() + " and your password is " + user.getPassword());
        Random random = new Random();

        //nustatom random id vartotojams . Negarantuoja kad nesidubliuos , bet lengviausias budas.

        String id = String.format("%04d", random.nextInt(10000));
        user.setCustomerID(Integer.parseInt(id));


        System.out.println();
        // Write the customer information to the customer file customer.txt
        user.writeCustomerToFile("users.txt");
        // Return the customer object
        return user;
    }

    // AR BENDRAM PROGRAMOS VEIKIMUI REIKIA SITO APACIOJE?

    /**
     * Taip. Cia tikrina Ar veikia gerai validacija
     *
     * @param regNeeded
     * @param inputNeeded
     * @return
     */

    // Checking if a user input has the correct regular expression regNeeded
    // Also takes a parameter inputNeeded to print to the user what must be
    // written
    public static String validRegex(String regNeeded, String inputNeeded) {
        // Empty string regular expression
        String reg = "";
        // While reg does not match the regular expression needed, run the loop
        while (!reg.matches(regNeeded)) {
            // Create a scanner to read from the user
            Scanner input = new Scanner(System.in);
            // Prompt user input. For instance, if inputNeeded is equal to
            // "Enter your phone
            // number", then the user will be prompted to enter his phone number
            System.out.printf("%s: ", inputNeeded);
            // reg is set to user input
            reg = input.nextLine();
            // If user input matches the regular expression needed, continue the
            // program outside of the loop
            if (reg.matches(regNeeded))
                continue;
            else
                System.out.println("Invalid input");
        }
        return reg;
    }

    // Check CPR-number

    private static String validCPR() {
        // Use regular expressions to see if CPR is in valid format, could use
        // Character.isDigit
        // as well but would be a lot uglier and more complicated
        // here "\\d{x}" says we must have x digits, so "\\d{6}" means we must
        // have 6 following digits
        // and then a "-" and then 4 following digits
        String regexCPR = "\\d{6}-\\d{4}";
        String cpr = "";
        // Check if CPR-number is in correct format: 123456-1234
        // ie. run this loop as long as cpr does not have the same format as
        // regularEx
        while (!cpr.matches(regexCPR)) {

            Scanner input = new Scanner(System.in);
            // Prompt the user to enter CPR-number
            System.out.print("Enter your CPR-number: ");
            // cpr is the next line written by the user
            cpr = input.nextLine();
            // if the cpr matches the regular expression with digits and "-"
            // then continue,
            // else print invalid input
            if (cpr.matches(regexCPR)) {
                continue;
            } else
                System.out.println("Invalid input, format must be 123456-1234");
        }

        return cpr;

    }

    private static String validCreditCard() {

        String creditCard = "";
        String regexCred = "\\d{4}-\\d{4}-\\d{4}-\\d{4}";

        while (!creditCard.matches(regexCred)) {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter credit card details: ");
            creditCard = input.nextLine();
            if (creditCard.matches(regexCred))
                continue;
            else
                System.out.println("Invalid input, format must be 1234-5678-9012-3456");
        }

        return creditCard;
    }


}
