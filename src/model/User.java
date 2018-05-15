package model;

import Data.ReadAndWrite;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {
    private String firstName, lastName, address, username, password, telephone, creditCard, cprNumber;
    private int customerID;
    private static int count;
    private List<Product> products = new ArrayList<>();

    public User() {
        count++;
        customerID = count;

    }

    public User(String firstName, String lastName, String address, String telephone, String creditCard, int postcode,
                String cprNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.telephone = telephone;
        this.creditCard = creditCard;
        this.cprNumber = cprNumber;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void createPassword() {
        this.password = lastName.substring(0, 3) + getcprNumber().substring(getcprNumber().length() - 4); //get rid of the CPR and random generate the password
    }

    // Create userName from first name and last name
    public void createUserName() {
        this.username = firstName.charAt(0) + lastName.substring(0, 3);

    }

    // Set first and last name
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Return first and last name
    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Return first and last name
    public String getLastName() {
        return lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setcprNumber(String cprNumber) {
        this.cprNumber = cprNumber;
    }

    public String getcprNumber() {
        return cprNumber;
    }


    // Write a customer to a file
    public void writeCustomerToFile(String file) {
        //Cia padarysim transactiona logus
        ReadAndWrite.WriteDetails("alltransactions.txt", "New " + getCustomerID() + " was registered. " + new Date());


        String details = getCustomerID() + ";" + getLastName() + ";" + getFirstName() + ";" + getUsername() + ";" + getPassword() + ";" + getAddress() + ";"
                + getcprNumber() + ";" + getTelephone() + ";" + getCreditCard() + ";" + this.getClass().getName().substring(6);
        ReadAndWrite.WriteDetails(file, details);

    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        User.count = count;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        //Removes all user products with same ID
        products.removeIf(product1 -> product.getID()==product1.getID());
    }
}
