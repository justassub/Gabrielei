package Data;

import model.Beer;
import model.Product;
import model.Tshirt;

import java.util.ArrayList;
import java.util.Scanner;

public class ProductDatabase {
    // Get products from a line
    public static Product getProduct(String line) {
        Product productFromFile = new Product();//priskiria null
        String[] values = line.split(";");
        // Check if type is Beer or Tshirt, assign correct values in each situation
        if (values[0].equals("Beer")) {
            productFromFile = new Beer();
            productFromFile.setID(Integer.parseInt(values[1]));
            productFromFile.setName(values[2]);
            productFromFile.setPrice(Double.parseDouble(values[3]));
            productFromFile.setSize(values[4]);
            ((Beer) productFromFile).setType(values[5]);
            ((Beer) productFromFile).setABV(Double.parseDouble(values[6]));

        } else if (values[0].equals("Tshirt")) {
            productFromFile = new Tshirt();
            productFromFile.setID(Integer.parseInt(values[1]));
            productFromFile.setName(values[2]);
            productFromFile.setPrice(Double.parseDouble(values[3]));
            productFromFile.setSize(values[4]);
            //sex and extra color should be asked for here!!
            ((Tshirt) productFromFile).setSex(values[5]);
            ((Tshirt) productFromFile).setColor(values[6]);
        }
        return productFromFile;
    }

    // Get all products from a file
    public static ArrayList<Product> getAllProducts(String file) {
        ArrayList<Product> productList = new ArrayList<>();
        Scanner input = ReadAndWrite.readDetails(file);
        // checking each line
        while (input.hasNextLine()) {
            productList.add(getProduct(input.nextLine()));// passing each line to
            // the method getProduct
            // which returns a bike
            // object
        } // which is then added to a ArrayList
        return productList;
    }

    // Write a product to a file
    public static void writeProducttoFile(String file, Product product) {
        String details = "";
        // If the product is beer, details must equal beer details, if it is Tshirt, it must be Tshirt details
        if (product instanceof Beer) {
            details = "Beer;" + product.getID() + ";" + product.getName() + ";" + product.getPrice() + ";" + product.getSize() + ";"
                    + ((Beer) product).getType() + ";" + ((Beer) product).getABV() + ";";
        }
        if (product instanceof Tshirt) {
            details = "Tshirt;" + product.getID() + ";" + product.getName() + ";" + product.getPrice() + ";" + product.getSize() + ";"
                    + ";" + ((Tshirt) product).getSex() + ";" + ((Tshirt) product).getColor() + ";";
        }
        // Write the details to the file
        ReadAndWrite.WriteDetails(file, details);
    }

}