package View;

import Data.ProductDatabase;

import Data.ReadAndWrite;
import model.Product;
import model.User;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class UserAddProducts {
    static Scanner scanner = new Scanner(System.in);

    public static void whatDoYouWantToDo(User user) throws IOException {
        System.out.println("Hello , " + user.getLastName() + " what would you like to do?");
        System.out.println("1. Add Products into Cart 2. See your cart 3. Remove product from your cart  4.Pay for products");
        String choise = scanner.next();
        if (choise.equals("1")) {
            addProducts(user);
        } else if (choise.equals("2")) {
            for (Product product : user.getProducts()) {
                System.out.println(product.getName() + " " + product.getPrice() + "$");
            }
            whatDoYouWantToDo(user);
        } else if (choise.equals("3")) {
            removeProduct(user);
        } else if (choise.equals("4")) {
            payForProducts(user);
        } else {
            System.out.println("Bad choise");
            whatDoYouWantToDo(user);
        }

    }

    public static void removeProduct(User user) throws IOException {
        Product productToRemove = null;
        for (Product product : user.getProducts()) {
            System.out.println(product.getID() + " " + product.getName() + " " + product.getPrice() + "$");
        }
        System.out.println("enter  product ID you do not want to buy");
        String productId = scanner.next();
        try {
            //We need to make sure good input
            Integer.parseInt(productId);
        } catch (NumberFormatException e) {
            System.out.println("Bad product ID input ");
            whatDoYouWantToDo(user);
        }
        for (Product product : user.getProducts()) {
            if (product.getID()==Integer.parseInt(productId)) {
                productToRemove = product;
                break;
            }
        }

        if (productToRemove == null) {
            System.out.println("You do not have this product in your cart list");
            whatDoYouWantToDo(user);
        } else {
            user.removeProduct(productToRemove);
            System.out.println(productToRemove.getName() + " product removed from your cart ");
            whatDoYouWantToDo(user);
        }

    }

    public static void addProducts(User user) throws IOException {
        ProductView.displayAvailableProducts();
        System.out.println("What product  do you want to buy? Enter product ID");
        ArrayList<Product> products = ProductDatabase.getAllProducts("products.txt");
        int productId = scanner.nextInt();
        for (Product product : products) {
            if (product.getID() == productId) {
                System.out.println(product.getName()+" was added into basket.Price -"+product.getPrice());
                user.addProduct(product);
            }
        }
        if (user.getProducts().isEmpty()) {
            System.out.println("Your cart is empty , please Add product");
            addProducts(user);
        }
        System.out.println("Do you want to add more Products into your Basket? 1-yes ,default -no");
        String choise = scanner.next();
        if ((choise.equals("1"))) {
            addProducts(user);
            return;
        }
        whatDoYouWantToDo(user);

    }

    public static void payForProducts(User user) throws IOException {
        System.out.println("All products in your cart :");
        for (Product product : user.getProducts()) {
            System.out.println(product.getName() + " : " + product.getPrice());
        }
        System.out.println("How do you want to pay : 1-cash . anything else - creditCard");
        String choise = scanner.next();
        if (choise.equals("1")) {
            payCash(user);
        } else {
            payWithCreditCard(user);
        }
    }

    public static void payCash(User user) throws IOException {
        double moneyYouneedToPay = 0;
        for (Product product : user.getProducts()) {
            moneyYouneedToPay = moneyYouneedToPay + product.getPrice();
        }
        System.out.println("You need to pay : " + moneyYouneedToPay + "DKK " + ". Thank you for your purchase!");
        CarlsbergShopProcess.startCarlsbergShopProcess();
        ReadAndWrite.WriteDetails("alltransactions.txt", "New " + user.getCustomerID() + " bought products cash  worth of  : . " + moneyYouneedToPay + "DKK " + new Date());
    }

    public static void payWithCreditCard(User user) throws IOException {
        double moneyYouneedToPay = 0;
        for (Product product : user.getProducts()) {
            moneyYouneedToPay = moneyYouneedToPay + product.getPrice();
        }
        System.out.println("Payment of " + moneyYouneedToPay + " will be charged from your creditCard : " + user.getCreditCard()
                + ". Thank you for your purchase!");
        ReadAndWrite.WriteDetails("alltransactions.txt", "New " + user.getCustomerID() + " bought products with credit card worth of : . " + moneyYouneedToPay + "DKK " + new Date());
        CarlsbergShopProcess.startCarlsbergShopProcess();

    }
}
