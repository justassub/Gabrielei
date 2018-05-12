package View;

import Data.ProductDatabase;
import Data.ReadAndWrite;
import model.Product;
import model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class UserAddProducts {

    public static void addProducts(User user) throws IOException {
        Scanner scanner = new Scanner(System.in);
        ProductView.displayAvailableProducts();
        System.out.println("What product  do you want to buy ? Enter product ID");
        ArrayList<Product> products = ProductDatabase.getAllProducts("products.txt");
        int productId = scanner.nextInt();
        for (Product product : products) {
            System.out.println(product.getID()+product.getName());
            if (product.getID() == productId) {
                System.out.println("Product was added into basket");
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
        payForProducts(user);

    }

    public static void payForProducts(User user) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("All products in your cart :");
        for(Product product : user.getProducts()){
            System.out.println(product.getName()+" : "+product.getPrice());
        }
        System.out.println("How do you want to pay : 1-cash . anything else - creditCard");
        String choise = scanner.next();
        if(choise.equals("1")){
            payCash(user);
        }else {
            payWithCreditCard(user);
        }
    }
    public static void payCash(User user) throws IOException {
        double moneyYouneedToPay=0;
        for(Product product : user.getProducts()){
            moneyYouneedToPay=moneyYouneedToPay+product.getPrice();
        }
        System.out.println("You need to pay : "+moneyYouneedToPay+"$");
        CarlsbergShopProcess.startCarlsbergShopProcess();
        ReadAndWrite.WriteDetails("alltransactions.txt","New "+user.getCustomerID()+" bought products cash  worth of  : . "+moneyYouneedToPay+"$ "+new Date());
    }
    public static void payWithCreditCard(User user) throws IOException {
        double moneyYouneedToPay=0;
        for(Product product : user.getProducts()){
            moneyYouneedToPay=moneyYouneedToPay+product.getPrice();
        }
        System.out.println("Payment of "+ moneyYouneedToPay +" will be charged from your creditCard : "+ user.getCreditCard());
        ReadAndWrite.WriteDetails("alltransactions.txt","New "+user.getCustomerID()+" bought products with credit card worth of : . "+moneyYouneedToPay+"$ "+new Date());
        CarlsbergShopProcess.startCarlsbergShopProcess();

    }
}
