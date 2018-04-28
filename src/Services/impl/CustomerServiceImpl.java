package Services.impl;

import Items.Item;
import Login.Reader;
import Services.CustomerInterface;
import Users.Customer;
import Users.Roles;
import Users.User;
import javafx.application.Application;

import java.lang.reflect.Array;
import java.util.*;

public class CustomerServiceImpl  implements CustomerInterface{
    Roles roles = Roles.CUSTOMER;
    Scanner scanner = new Scanner(System.in);
    Reader reader = new Reader();
    Customer customer;

    public CustomerServiceImpl(User customer) {
        this.customer = (Customer) customer;
    }

    public void whatCustomerCanDo() {

        System.out.println(roles + " What do you want to do : 1.See all Products. 2. Add new Products To your List 3.Remove Products from your list" +
                "4-Pay for products "+
                "  By name q-shut down application."       );

        boolean choiseStatus = false;
        do {
            switch (scanner.nextLine()) {
                case "1":
                    displayProducts();
                    whatCustomerCanDo();
                    break;
                case "2":
                    addProductIntoCart();
                    break;
                case "3":
                    removeProductFromCart();
                    break;
                case "4":
                    makePayment();
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
    public void displayProducts() {
        //Simple prove That We KNOW about java 8 - labmdas.
        reader.getItems().forEach(item -> System.out.println(item.getItemName()+" Price : "+item.getPrice()));

    }

    @Override
    public void payProducts(String paymentWay) {
        if(paymentWay.equals("bank")){
            customer.setBalance(customer.getBalance()-cartListSumCalculator());
            System.out.println("Done.Balance in your bank account :"+customer.getBalance()+"$" );
        }else{
            System.out.println(" You need to pay  : "+cartListSumCalculator()+" $ for  :  " +customer.getItemList().size()+" items ");
        }
        System.out.println("would you like to see your receipt? 1-yes, anything else -No , thank you. I am in a hurry");

        String choise= scanner.nextLine();
        if(choise.equals("1")){
            seeReceipt();
        }
        else {
            whatCustomerCanDo();
        }

    }

    @Override
    //returns True if customer has enough money in his bank account to pay for items.
    public boolean confirmationOfProducts() {

        return customer.getBalance() > cartListSumCalculator();
    }

    @Override
    public void seeReceipt() {
        for (Item item :customer.getItemList()){
            System.out.println(item.getItemName()+" : "+item.getPrice());

        }
        System.out.println("you paid : "+cartListSumCalculator()+"$ for :"+customer.getItemList().size()+" items "+new Date());
        reader.logWriter(new Date()+" customer : "+customer.getUsername()+" paid : "+cartListSumCalculator()+"$ for : "+customer.getItemList().size()+" items");
        //remove customer items;
        List<Item> items =new ArrayList<>();
        customer.setItemList(items);
        whatCustomerCanDo();
    }

    @Override
    public void makePayment() {
        System.out.println("How would you like to pay for your payments? 1-cash 2-from your bank balanse. You have : "+customer.getBalance()+"$");
        String choise = scanner.nextLine();
        switch (choise){
            case "1":
                payProducts("cash");
                break;
            case "2":
                if(!confirmationOfProducts()){
                    System.out.println("you do not have enough money in your bank account. Pay cash or remove items from your cart.");
                    whatCustomerCanDo();
                    break;
                }else{
                    System.out.println("you have enough money. Proceed ..");
                    payProducts("bank");
                    break;
                }
             default:
                 System.out.println("Bad key. Try again");
                 makePayment();
        }

    }

    @Override
    public void addProductIntoCart() {
        System.out.println("Insert name of item name OR insert 'q' to go back");
        displayProducts();
        String itemName= scanner.nextLine();
        if(itemName.equals("q")){
            whatCustomerCanDo();
        }
        for(Item item:reader.getItems()){
            if (item.getItemName().equals(itemName)){
                System.out.println(item.getItemName()+" was added.");
                customer.addItem(item);
                addProductIntoCart();
            }
        }
        System.out.println("Item with this name doesnt exist");
        addProductIntoCart();
    }

    @Override
    public void removeProductFromCart() {
        System.out.println("your items in your cartList:");
        customer.getItemList().forEach((item -> System.out.println(item.getItemName()+" Price : "+item.getPrice())));
        System.out.println("Insert name of product you do not  want to buy: or insert 'q' to go back");
        String productToRemove= scanner.nextLine();
        if(productToRemove.equals("q")){
            whatCustomerCanDo();
        }
        for(Item item : customer.getItemList()){
            if(item.getItemName().equals(productToRemove)){
                System.out.println(item.getItemName()+" from your cart was removed");
                customer.removeItem(item);
            }
        }
        System.out.println("You do not have this product in your cart : "+ productToRemove);
        removeProductFromCart();
    }

    private double cartListSumCalculator(){

        System.out.println(customer.getItemList());
        double sum = 0;
        for(Item item : customer.getItemList()){
            sum+=item.getPrice();
        }
        return sum;
    }
}
