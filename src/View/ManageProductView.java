
package View;

import Data.ProductDatabase;
import Data.ReadAndWrite;
import model.Beer;
import model.Product;
import model.Tshirt;

import java.io.IOException;
import java.util.*;

public class ManageProductView {
    public static String details, writeDetails;

    // Level 2 manager view to manipulate Product database and display all products
    public static void ProductView() throws IOException {
        int choice = -1;

        while (choice <= 0 || choice > 4) {
            try {
                // Create scanner to collect input from user
                Scanner input = new Scanner(System.in);
                // Print out interface text
                System.out.println("------------------------------------------");
                System.out.println(" YOU ARE NOW IN PRODUCT MANAGEMENT VIEW ");
                System.out.println("------------------------------------------");
                System.out.println("");
                System.out.println("What do you want to do? Press the respective number.");
                System.out.println("");
                System.out.println("-----------------------");
                System.out.println("|1| I would like to view current products");
                System.out.println("|2| I would like to add new products");
                System.out.println("|3| Go Back");
                System.out.println("|4| Log off");
                System.out.println("-----------------------");

                // User enters input to select next process
                choice = input.nextInt();

                switch (choice) {
                    case 1:
                        // Level 3 Manager view to view edit products
                        editProduct();
                        break;
                    case 2:
                        // Level 3 Manager view to add products
                        addProduct();
                        break;
                    case 3:
                        // Going back to Level 1 Manager view
                        ManagerView.managerLoggedIn();
                        break;
                    case 4:
                        System.out.println("You are now logged off");
                        System.exit(0);
                    default:
                        System.out.print("Invalid input. Try again\n");
                        continue;
                }
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. \n");
            }

        }
    }

    // Edit products view to view all products in database
    public static void editProduct() throws IOException {
        // Create an empty arraylist
        ArrayList<Product> productDetails;
        // Initiate a method to add all products(libe-by-line) to the arraylist
        productDetails = ProductDatabase.getAllProducts("products.txt");
        System.out.println("------------------------------------------");
        System.out.println(" YOU ARE NOW IN PRODUCT MANAGEMENT VIEW ");
        System.out.println("------------------------------------------");
        // Printing amount of products that are in the database based on lines in
        // database
        System.out.println("There are " + productDetails.size() + " Products in our database:");
        System.out.println("");
        // Initiate a method to display all available products
        ProductView.displayAvailableProducts();
        int choice = -1;

        while (choice <= 0 || choice > 4) {
            try {
                Scanner input = new Scanner(System.in);
                // Print out interface text
                System.out.println("------------------------------------------");
                System.out.println("");
                System.out.println("What do you want to do? Press the respective number.");
                System.out.println("");
                System.out.println("-----------------------");
                System.out.println("|1| I would like to add products to database");
                System.out.println("|2| I would like to remove products from database");
                System.out.println("|3| Go Back");
                System.out.println("|4| Log off");
                System.out.println("-----------------------");
                // Input from the user
                choice = input.nextInt();

                switch (choice) {
                    case 1:
                        // Go to the add products view (Level 3 Manager view)
                        addProduct();
                        break;
                    case 2:
                        // Go to the view to remove products
                        deleteProduct();
                        break;
                    case 3:
                        // Go to previous view
                        ProductView();
                        break;
                    case 4:
                        System.out.println("You are now logged off");
                        System.exit(0);

                    default:
                        System.out.print("Invalid input. Try again\n");
                        continue;
                }
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. \n");
            }

        }
    }

    // Level 3 Manager view to add products to Database
    public static void addProduct() throws IOException {
        int choice = -1;

        while (choice <= 0 || choice > 4) {
            try {
                // Create new scanner to collect input from the user
                Scanner input = new Scanner(System.in);
                // Print out interface text
                System.out.println("------------------------------------------");
                System.out.println("       YOU CAN NOW ADD NEW PRODUCTS          ");
                System.out.println("------------------------------------------");
                System.out.println("");
                System.out.println("Which product type would you like to add? Press the respective number.");
                System.out.println("");
                System.out.println("-----------------------");
                System.out.println("|1| I would like to add Beer to our database");
                System.out.println("|2| I would like to add Tshirt to our database");
                System.out.println("|3| I would like to Go Back");
                System.out.println("|4| I would like to Log off");
                System.out.println("-----------------------");

                // Get user input
                choice = input.nextInt();

                switch (choice) {
                    case 1:
                        // Level 4 manager view to add Beer
                        addBeer();
                        break;
                    case 2:
                        // Level 4 manager view to add Tshirt
                        addTshirt();
                        break;
                    case 3:
                        // Going back to Level 2 Manager view
                        ProductView();
                        break;
                    case 4:
                        // Exit system
                        System.out.println("You are now logged off");
                        System.exit(0);
                    default:
                        System.out.print("Invalid input. Try again\n");
                        continue;
                }
            } catch (InputMismatchException e) {
                // If the user enters a string(or double) instead of int,
                // The system throws an error and starts again from the
                // beginning(due to loop)
                System.out.print("Invalid input. \n");
            }
        }
    }

    // Adding beer to Database
    public static void addBeer() throws IOException {
        // Print out interface text
        System.out.println("______________________");
        System.out.println("We are now adding a  Beer to our database, please enter the product details below:");
        System.out.println("");
        // Create scanner to collect input from user
        Scanner input = new Scanner(System.in);

        // ProductString is to collect string inputs from user
        String productString;
        // productInt is to collect Int inputs from user
        Random random = new Random();
        int id = random.nextInt(10000);

        // productDouble is to collect double inputs from user
        double productDouble;

        // Create new object Beer
        Beer beer = new Beer();

        beer.setID(id);
        // ID of the product about to be added is always one higher than the last
        // product ID
        // This is to ensure that the product ID's are always unique
        System.out.println("Product ID: " + id);
        System.out.println("Product Name: ");
        productString = input.nextLine();
        beer.setName(productString);
        System.out.println("Size of the Beer: ");
        productString = input.nextLine();
        beer.setSize(productString);
        System.out.println("Type of the Beer: ");
        productString = input.nextLine();
        beer.setType(productString + "L");
        System.out.println("Alcohol by Volume of the Beer: ");
        productDouble = input.nextDouble();
        beer.setABV(productDouble);
        System.out.println("Price of the product: ");
        productDouble = input.nextDouble();
        beer.setPrice(productDouble);
        // NOTE: empty input.nextLine() is to avoid system errors when switching
        // between String, int and double inputs

        int choice = -1;

        while (choice <= 0 || choice > 4) {
            try {

                // Print out interface text
                System.out.println("---------------------------------------------");
                System.out.println("You have added a beer with following details");
                System.out.println("---------------------------------------------");
                System.out.println("");
                System.out.println("------------------------------------------------------------------------------");
                // Printing out the product details which were just added for
                // confirmation
                System.out.println(beer.BeerToString());
                System.out.println("------------------------------------------------------------------------------");
                System.out.println("Is this correct? \nPress respective number");
                System.out.println("");
                System.out.println("-----------------------");
                System.out.println("|1| Yes, add product to database");
                System.out.println("|2| No, I'd like to enter the details again");
                System.out.println("|3| No, I'd like to change the product type");
                System.out.println("|4| I'd like to go back without saving");
                System.out.println("-----------------------");

                choice = input.nextInt();

                switch (choice) {
                    case 1:
                        // Writing the product details to products.txt file

                        ProductDatabase.writeProducttoFile("products.txt", beer);
                        ReadAndWrite.WriteDetails("alltransactions.txt", "New " + beer.getID() + " Beer was registered. " + new Date());
                        // Printing confirmation message
                        System.out
                                .println("------------------------------------------------------------------------------");
                        System.out.println("The product has been added to the database");
                        System.out
                                .println("------------------------------------------------------------------------------");
                        System.out.println("Going back to product management view");
                        // Going back to Level 3 Manager view
                        ProductView();
                        break;
                    case 2:
                        // in case of a mistake in details, Manager can add details
                        // again without saving the process
                        addBeer();
                        break;
                    case 3:
                        // If the manager made a mistake, and wants to add
                        // beer instead, they can cancel current flow
                        // and will go back to select the product type they'd like to
                        // add
                        addProduct();
                        break;
                    case 4:
                        // Does not save current process and moves back one level
                        ProductView();
                    default:
                        System.out.print("Invalid input. Try again\n");
                        continue;
                }
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. \n");
                input.next();
                continue;
            }
        }

    }

    public static void addTshirt() throws IOException { // adding Tshirt
        Random random = new Random();
        // Print out interface text
        System.out.println("______________________");
        System.out.println("We are now adding an Tshirt to our database, please enter the product details below:");
        System.out.println("");
        // create scanner to collect input from user
        Scanner input = new Scanner(System.in);

        // productString is to collect string inputs from user
        String productString;
        // productInt is to collect Int inputs from user
        int productInt = random.nextInt(10000);
        ; // this is created as 1 to be used in creating ProductID
        // productDouble is to collect double inputs from user
        double productDouble;

        Tshirt Tshirt = new Tshirt();
        // ID of the product about to be added is always one higher than the last
        // product ID
        // This is to ensure that the product ID's are always unique
        productInt += Product.getLastProductID();
        Tshirt.setID(productInt);
        System.out.println("Product Name: ");
        productString = input.nextLine();
        Tshirt.setName(productString);
        System.out.println("Product Size: ");
        productString = input.nextLine();
        Tshirt.setSize(productString);
        System.out.println("Sex: ");
        productString = input.nextLine();
        Tshirt.setSex(productString);
        System.out.println("Colour of the Tshirt: ");
        productString = input.nextLine();
        Tshirt.setColor(productString);
        System.out.println("Price of the product: ");
        productDouble = input.nextDouble();
        Tshirt.setPrice(productDouble);

        int choice = -1;

        while (choice <= 0 || choice > 4) {
            try {

                System.out.println("---------------------------------------------");
                System.out.println("You have added a Tshirt with following details");
                System.out.println("---------------------------------------------");
                System.out.println("");
                System.out.println("------------------------------------------------------------------------------");
                System.out.println(Tshirt.TshirtToString());
                System.out.println("------------------------------------------------------------------------------");
                System.out.println("Is this correct? \nPress respective number");
                System.out.println("");
                System.out.println("-----------------------");
                System.out.println("|1| Yes, add product to database");
                System.out.println("|2| No, I'd like to enter the details again");
                System.out.println("|3| No, I'd like to change the product type");
                System.out.println("|4| I'd like to go back without saving");
                System.out.println("-----------------------");

                choice = input.nextInt();

                switch (choice) {
                    case 1:
                        ReadAndWrite.WriteDetails("alltransactions.txt", "New " + Tshirt.getID() + " was registered. " + new Date());
                        ProductDatabase.writeProducttoFile("products.txt", Tshirt);
                        System.out
                                .println("------------------------------------------------------------------------------");
                        System.out.println("The product has been added to the database");
                        System.out
                                .println("------------------------------------------------------------------------------");
                        System.out.println("Going back to product management view");
                        ProductView();
                        break;
                    case 2:
                        // in case of a mistake in details, Manager can add details
                        // again without saving the process
                        addTshirt();
                        break;
                    case 3:
                        // If the manager made a mistake, and wants to add beer
                        // instead, they can cancel current flow
                        // and will go back to select the product type they'd like to
                        // add
                        addProduct();
                        break;
                    // Does not save current process and moves back one level
                    case 4:
                        ProductView();
                    default:
                        System.out.print("Invalid input. Try again\n");
                        continue;
                }
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. \n");
                input.nextLine();
            }
        } // end of while-loop
    }

    // reading all products and deleting the one that was selected based on ID
    public static void deleteProduct() throws IOException {
        try {

            System.out.println("------------------------------------------");
            System.out.println("       YOU ARE NOW REMOVING PRODUCTS          ");
            System.out.println("------------------------------------------");
            System.out.println("");
            System.out.println("Press the ID of the product you'd like to delete");
            Scanner input = new Scanner(System.in);
            int deleteProduct = input.nextInt();

            ArrayList<Product> productDetails = new ArrayList<Product>();
            productDetails = ProductDatabase.getAllProducts("products.txt");
            // initiate f to always be 0 when the program starts -
            // where f == current line in products.txt
            int f = 0;
            String productTodelete = null;
            Scanner input2 = ReadAndWrite.readDetails("products.txt");
            // to read the first line no matter if while statement is true or
            // false

            productTodelete = input2.nextLine();
            while (deleteProduct != productDetails.get(f).getID()) {
                // every time the program goes through the loop, it changes
                // producttoDelete to current
                // line it reads until the ID of the product in current line
                // is the same as the ID that was selected to be deleted
                productTodelete = input2.nextLine();
                // increases current line to be read every time the program goes
                // through the loop
                f++;
            }

            System.out.println("------------------------------------------");
            System.out.println("You are about to delete the following product:");
            // Checks if current line of object in position f is an instance of
            // Beer...
            if (productDetails.get(f) instanceof Beer) {
                System.out.println(((Beer) productDetails.get(f)).BeerToString());
            }
            // ... Or Tshirt
            if (productDetails.get(f) instanceof Tshirt) {
                System.out.println(((Tshirt) productDetails.get(f)).TshirtToString());
            }
            System.out.println("------------------------------------------");
            System.out.println("Are you sure you'd like to do that?");
            System.out.println("|1| Yes, remove product from databse");
            System.out.println("|2| No, I'd want to remove another product");
            System.out.println("|3| I'd like to go back without saving");
            System.out.println("|4| I'd like to log off");
            int choice = -1;
            while (choice <= 0 || choice > 4) {
                try {
                    choice = input.nextInt();
                    switch (choice) {
                        case 1:
                            // Removes the product from the database, where
                            // productTodelete = line with matching ID of the product
                            // that was requested to be deleted
                            ReadAndWrite.removeLine("products.txt", "producttemp.txt", productTodelete);
                            // Confirmation messages below
                            System.out.println("The products has been removed from the database");
                            System.out.println("Going back to view products");
                            editProduct();
                            break;
                        case 2:
                            // Jumping to the start without saving to select another
                            // product to be deleted
                            deleteProduct();
                            break;
                        case 3:
                            // Going back one level to select another option
                            editProduct();
                            break;
                        case 4:
                            System.out.println("You are now logged off");
                            System.exit(0);
                        default:
                            System.out.print("Invalid input. Try again\n");
                            continue;
                    }
                } catch (InputMismatchException e) {

                    System.out.print("Invalid input. \n");
                    deleteProduct();

                }

            }

        } catch (NoSuchElementException e) {

            // IF the program finds "No element" exception, it displays an error
            // message stating that there is no such product in the
            // database that Matches with selected ID
            System.out.println("-----ERROR-----");
            System.out.print("No such product exists in the database \n");
            deleteProduct();

        }

    }
}