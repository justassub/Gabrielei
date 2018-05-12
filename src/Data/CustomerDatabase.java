package Data;

import model.Manager;
import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// KA SITOJ KLASEJ DARYTI SU customerID ?

public class CustomerDatabase {
    private ArrayList<User> customersList;

    public CustomerDatabase() {
        customersList = new ArrayList<>();
        readCustomersFromFile("customer.txt");
    }

    public void addCustomer(User newCustomer) {

        customersList.add(newCustomer);

    }

    // nuo cia paimta is customerdata
    public static ArrayList<User> getAllCustomerDetails(String file) {
        ArrayList<User> customerList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] array = line.split(" ");
                for (String userInfo : array) {
                    customerList.add(getCustomer(userInfo));
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return customerList;
        // returns a

    }

    private static User getCustomer(String customerInfo) {
        System.out.println(customerInfo);
        User customer = new User();
        String[] userInformationAsArray = customerInfo.split(";");
        //jei sarase paskutinis narys yra Manager - useris paverciamas is userio i manageri.
        if (userInformationAsArray[9].equals("Manager")) {
            customer = new Manager();
        }
        customer.setCustomerID(Integer.parseInt(userInformationAsArray[0]));
        customer.setFirstName(userInformationAsArray[1]);
        customer.setLastName(userInformationAsArray[2]);
        customer.setUsername(userInformationAsArray[3]);
        customer.setPassword(userInformationAsArray[4]);
        customer.setAddress(userInformationAsArray[5]);
        customer.setcprNumber(userInformationAsArray[6]);
        customer.setTelephone(userInformationAsArray[7]);
        customer.setcprNumber(userInformationAsArray[6]);
        customer.setCreditCard(userInformationAsArray[8]);

        return customer;
    }


    public void readCustomersFromFile(String file) {
        Scanner input;
        File readFile = new File(file);
        try {
            input = new Scanner(readFile);

            while (input.hasNext()) {
                String[] values = input.nextLine().split(";");
                String firstName = values[0];
                String lastName = values[1];
                String address = values[2];
                String telephone = values[3];
                String cprNumber = values[4];
                String creditCard = values[5];

                addCustomer(new User(firstName, lastName, address, telephone, cprNumber, 0, cprNumber));

            }//while

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}