package model;

import Data.ProductDatabase;

import java.util.ArrayList;

public class Product {

	private int ID;
	private double price;
	private String name;
	private String size;
	// Empty constructor
	public Product() {
	}
	// Constructor with information
	public Product(int ID, double price, String name, String size) {
		this.ID = ID;
		this.name = name;
		this.price = price;
		this.size = size;
	}
	public int getID() {
		return ID;
	}
	public void setID(int ID) {
		this.ID = ID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public static int getLastProductID(){ //used in manageProductView to automatically create product ID's based on the value of last product ID in the list(by adding 1)
		ArrayList<Product> getProductID = new ArrayList<Product>();
		getProductID = ProductDatabase.getAllProducts("Products.txt"); //reads products.txt and puts them in arrays to find out how many products there are in the file
		return getProductID.get(getProductID.size()-1).getID(); //returns the value of last product ID in the file
	}
}