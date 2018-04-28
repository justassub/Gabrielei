package Services.impl;

import Login.Reader;
import Services.AdminService;
import Users.Roles;

import java.util.Scanner;

public class DatabaseAdminServiceImpl implements AdminService {
    Roles roles = Roles.ADMINISTRATOR;
    Scanner scanner = new Scanner(System.in);
    Reader reader = new Reader();

    public void whatAdminCanDo() {
        System.out.println(roles + " What do you want to do : 1.See logs.2- See statistics q-shut down application.");

        boolean choiseStatus = false;
        do {
            switch (scanner.nextLine()) {
                case "1":
                    readLogs();
                    break;
                case "2":
                    seeStatistics();
                    break;
                case "q":
                    System.exit(1);
                default:
                    System.out.println("Bad key. Please try again!!");
                    break;
            }
        } while (!choiseStatus);

    }

    @Override
    public void readLogs() {
        reader.readLogs();
        whatAdminCanDo();
    }

    public void seeStatistics() {
        System.out.println("We have " + reader.getUsers().size() + " registered users");
        System.out.println("Newest " + reader.getUsers().get(reader.getUsers().size() - 1).getAuthorities() + reader.getUsers().get(reader.getUsers().size() - 1).getUsername());
        whatAdminCanDo();
    }
}
