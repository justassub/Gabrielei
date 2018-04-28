package Services.impl;

import Items.Item;
import Login.Reader;
import Services.EmployeeService;
import Users.Roles;
import com.sun.deploy.util.StringUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class EmployeeServiceImpl implements EmployeeService {

    Roles roles = Roles.EMPLOYEE;
    Scanner scanner = new Scanner(System.in);
    Reader reader = new Reader();

    public void whatEmloyeeCanDo() {
        System.out.println(roles + " What do you want to do : 1.See all Products. 2. Add new Products 3.Remove Products By name q-shut down application.");

    boolean choiseStatus = false;
        do {
        switch (scanner.nextLine()) {
            case "1":
                readProducts();
                break;
            case "2":
                addProduct();
                break;
            case "3":
                removeProduct();
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
    public void addProduct() {
        System.out.println("Enter product Name: OR enter 'b' if you want go back.");
        String productName=scanner.nextLine();
        if(productName.equals("b")){
            whatEmloyeeCanDo();
        }
        System.out.println("Enter product price : ");
        String productPrice=scanner.nextLine();
        try{
            double price = Double.parseDouble(productPrice);
            //We want to avoid  any new lines , so do not let users create  names with spaces.
            Item item = new Item(productName.replace(" ",""),price);
            reader.addItem(item);
        }catch (Exception e){
            System.out.println("Bad price format . Please enter valid price! Try again.");
            addProduct();
        }
        addProduct();

    }
    @Override
    public void removeProduct() {
        //Simple prove That We KNOW about java 8 - labmdas.
        reader.getItems().forEach((item -> System.out.println(item.getItemName())));
        System.out.println("Enter product Name you want to delete : OR enter 'b' if you want go back.");
        String productNameToRemove=scanner.nextLine();
        if(productNameToRemove.equals("b")){
            whatEmloyeeCanDo();
        }
        for(Item item:reader.getItems()){
            if(item.getItemName().equals(productNameToRemove)){
                reader.removeItem(item);
                System.out.println("Item was removed "+item.getItemName());
                whatEmloyeeCanDo();
            }
        }
        System.out.println("Bad item name . Try again");
        removeProduct();
    }

    @Override
    public void readProducts() {
        for(Item item : reader.getItems()){
            System.out.println("Item name: "+  item.getItemName()+" Price: "+item.getPrice());
        }
        whatEmloyeeCanDo();
    }
}
