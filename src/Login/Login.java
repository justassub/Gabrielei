package Login;

import Services.impl.CustomerServiceImpl;
import Services.impl.DatabaseAdminServiceImpl;
import Services.impl.EmployeeServiceImpl;
import Users.Roles;
import Users.User;

import java.util.Scanner;

public class Login {
    Registration registration;
    public final static Scanner scanner = new Scanner(System.in);
    Reader reader = new Reader();

    public void connectAs() {
        boolean choiseStatus = false;
        do {
            System.out.println(" You want to connect as : c-customer ,e-employee, a-admin, q-quit");
            switch (scanner.nextLine()) {
                case "c":
                    customersChoice(Roles.CUSTOMER);
                    choiseStatus = true;
                    break;
                case "e":
                    customersChoice(Roles.EMPLOYEE);
                    choiseStatus = true;
                    break;
                case "a":
                    customersChoice(Roles.ADMINISTRATOR);
                default:
                    System.out.println("Bad key. Please try again!!");
                    break;
            }
        } while (!choiseStatus);
    }

    public void customersChoice(Roles roles) {
        boolean choiseStatus = false;
        do {
            System.out.println(roles + "  What would you like to do  : 1-login ,2-register, 3-switch role, q-quit");
            switch (scanner.nextLine()) {
                case "1":
                    login(roles);
                    choiseStatus = true;
                    break;
                case "2":
                    choiseStatus = true;
                    if (roles == Roles.CUSTOMER) {
                        registration.register(Roles.CUSTOMER);
                    } else if (roles == Roles.ADMINISTRATOR) {
                        registration.register(Roles.ADMINISTRATOR);
                    } else if (roles == Roles.EMPLOYEE) {
                        registration.register(Roles.EMPLOYEE);
                    }
                    break;
                case "3":
                    connectAs();
                    choiseStatus = true;
                    break;
                case "q":
                    System.exit(0);
                default:
                    System.out.println("Bad key. Please try again");
            }
        } while (!choiseStatus);
    }

    public void login(Roles roles) {
        System.out.println("Enter login name :");
        String login = scanner.nextLine();
        System.out.println("Enter password  :");
        String password = scanner.nextLine();
        if (login.equals("q") || password.equals("q")) {
            connectAs();
        }
        for (User user : reader.getUsers()) {

            if (user.getUsername().equals(login) && user.getPassword().equals(password) && user.getAuthorities().contains(roles)) {
                System.out.println("Connected");
                if (roles.equals(Roles.ADMINISTRATOR)) {
                    new DatabaseAdminServiceImpl().whatAdminCanDo();
                    break;
                }
                if (roles.equals(Roles.EMPLOYEE)) {
                    new EmployeeServiceImpl().whatEmloyeeCanDo();
                    break;
                }
                if (roles.equals(Roles.CUSTOMER)) {
                    new CustomerServiceImpl(user).whatCustomerCanDo();
                    break;
                }

            } else if (user.getUsername().equals(login) && user.getPassword().equals(password)) {
                System.out.println("You selected wrong role. Please select another  Role . Your role : " + user.getAuthorities());
                connectAs();
                break;
            }
        }
        System.out.println("Sorry , Wrong credencials. Please try again- . Or insert Q-to go back");
        login(roles);
    }


}
