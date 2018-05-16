package View;

import Data.ProductDatabase;
import model.Beer;
import model.Product;
import model.Tshirt;

import java.util.ArrayList;

public class ProductView {

    // Method to display products to the manager and customer
    public static void displayAvailableProducts() {
        // Create an arraylist of product details
        // Get productdetails from products.txt
        ArrayList<Product> productDetails = ProductDatabase.getAllProducts("products.txt");
        System.out.println("\nThese are products available:\n\nBeer:");
        // Loop to print all beers, checked if the object is a beer and then prints if true
        //sukuria nauja kintamasis kuris yra 0. tada eina per kiek

        for (int i = 0; i < productDetails.size(); i++) {
            if (productDetails.get(i) instanceof Beer)
                System.out.println(((Beer) productDetails.get(i)).BeerToString());
        }
        System.out.println("\nTshirt:");
        // Same for Tshirt
        for (int i = 0; i < productDetails.size(); i++) {
            if (productDetails.get(i) instanceof Tshirt)
                System.out.println(((Tshirt) productDetails.get(i)).TshirtToString());
        }
    }
}
