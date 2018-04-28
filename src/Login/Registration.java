package Login;

import Users.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Registration {
    private static Scanner scanner = new Scanner(System.in);
    private static Login login = new Login();
    private static Reader reader = new Reader();


    public static void register(Roles roles) {
        switch (roles) {
            case CUSTOMER:
                registerCustomer(roles);
            case EMPLOYEE:
                registerCustomer(roles);
            case ADMINISTRATOR:
                registerCustomer(roles);
            default:
        }
    }

    public static void registerCustomer(Roles roles) {

        System.out.println(" Your familyName ");
        String userName = scanner.nextLine();
        //We want to avoid  any new lines , so do not let users create  names with spaces.
        userName = userName.replace(" ", "");
        userName = userName.replace(",", "");
        if (checkNameFromNumbers(userName)) {
            if (roles == Roles.CUSTOMER) {
                Customer newCustomer = new Customer();
                newCustomer.setUsername(userName);
                newCustomer.setPassword(userName);
                newCustomer.setAuthorities(Arrays.asList(roles));
                //give 1000 dollars for new employees
                newCustomer.setBalance(1000);
                //Register client
                reader.registerClient(newCustomer);
                login.customersChoice(roles);
            } else if (roles == Roles.ADMINISTRATOR) {
                Administrator administrator = new Administrator();
                administrator.setUsername(userName);
                administrator.setPassword(userName);
                administrator.setAuthorities(Arrays.asList(roles));
                reader.registerClient(administrator);
                login.customersChoice(roles);

            } else if (roles == Roles.EMPLOYEE) {
                Employee employee = new Employee();
                employee.setUsername(userName);
                employee.setPassword(userName);
                employee.setAuthorities(Arrays.asList(roles));
                reader.registerClient(employee);
                login.customersChoice(roles);
            }
        } else {
            System.out.println(" your username is wrong please try again.");
            registerCustomer(roles);
        }
    }


    //checks if name contains numbers- THERE ARE NO NAMES WITH NUMBERS.
    private static boolean checkNameFromNumbers(String familyName) {
        for (char c : familyName.toCharArray()) {
            if (Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
