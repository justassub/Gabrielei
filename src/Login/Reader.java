package Login;

import Items.Item;
import Users.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * This class reads txt files and writes into files
 */
public class Reader {
    public static List<User> users = new ArrayList<>();
    public static List<Item> items = new ArrayList<>();


    public void readUsers() {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Bendras\\Desktop\\Gabriele\\customers.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] array = line.split(" ");
                for (String singleUserInfo : array) {
                    createUsersFromFile(singleUserInfo);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readItems() {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Bendras\\Desktop\\Gabriele\\items.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] array = line.split(" ");
                for (String itemInfo : array) {
                    createItems(itemInfo);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createItems(String itemsInfo) {
        String[] priceInformationAsArray = itemsInfo.split(",");
        Item newItem= new Item();
        newItem.setItemName(priceInformationAsArray[0]);
        newItem.setPrice(Double.parseDouble(priceInformationAsArray[1]));
        items.add(newItem);

    }

    public void readLogs() {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Bendras\\Desktop\\Gabriele\\logs.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Something wrong with Logs.txt file+ " + e.getLocalizedMessage());
        }
    }

    public void createUsersFromFile(String userInfo) {
        String[] userInformationAsArray = userInfo.split(",");
        if (userInformationAsArray[2].contains(Roles.CUSTOMER.toString())) {
            Customer customer = new Customer();
            customer.setUsername(userInformationAsArray[0]);
            customer.setPassword(userInformationAsArray[1]);
            customer.setAuthorities(Arrays.asList(Roles.CUSTOMER));
            customer.setBalance(Double.parseDouble(userInformationAsArray[3]));
            users.add(customer);
        }
        if (userInformationAsArray[2].contains(Roles.ADMINISTRATOR.toString())) {
            User administrator = new Administrator();
            administrator.setUsername(userInformationAsArray[0]);
            administrator.setPassword(userInformationAsArray[1]);
            administrator.setAuthorities(Arrays.asList(Roles.ADMINISTRATOR));
            users.add(administrator);
        }
        if (userInformationAsArray[2].contains(Roles.EMPLOYEE.toString())) {
            User employee = new Employee();
            employee.setUsername(userInformationAsArray[0]);
            employee.setPassword(userInformationAsArray[1]);
            employee.setAuthorities(Arrays.asList(Roles.EMPLOYEE));
            users.add(employee);
        }
    }
    public void registerClient(User user) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Bendras\\Desktop\\Gabriele\\customers.txt", true))) {
            if (user instanceof Customer) {
                bw.write(user.getUsername() + "," + user.getPassword() + "," + user.getAuthorities() + "," + ((Customer) user).getBalance() + "\n");
            } else {
                bw.write(user.getUsername() + "," + user.getPassword() + "," + user.getAuthorities() + "\n");
            }
            System.out.println(user.getAuthorities() + " Was saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
        addUser(user);
    }
    private void itemWriter(List<Item> items) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Bendras\\Desktop\\Gabriele\\items.txt", false))) {
            for(Item item:items){
                bw.write(item.getItemName() +","+item.getPrice()+ "\n");
            }
        } catch (IOException e) {
            System.out.println("something wrong with items.txt file . Imposible to write. "+e);
        }
    }

    public void logWriter(String message) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Bendras\\Desktop\\Gabriele\\logs.txt", true))) {
            bw.write(message + "\n");
        } catch (IOException e) {
            System.out.println("something wrong with logs.txt file . Imposible to write. "+e);
        }
    }

    public void addUser(User user) {
        users.add(user);
        logWriter(new Date() + " : " + user.getUsername() + " : was added");
        System.out.println(user.getUsername()+ "were registered. Your password is same as username");
    }

    public void addItem(Item item){
        items.add(item);
        itemWriter(items);
        logWriter(new Date()+" :"+item.getItemName() +": was added");
    }
    public void removeItem(Item item){
        items.remove(item);
        itemWriter(items);
        logWriter(new Date()+" :"+item.getItemName() +": was removed");
    }

    public List<User> getUsers() {
        return users;
    }

    public  List<Item> getItems() {
        return items;
    }
}
