package model;

public class Beer extends Product {
    private String type;
    private double ABV;

    // Empty constructor
    public Beer() {
    }

    // Constructor with values and super from Products
    public Beer(int ID, String name, double price, String size,
                String type, double ABV) {
        super(ID, price, name, size);
        this.type = type;
        this.ABV = ABV;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getABV() {
        return ABV;
    }

    public void setABV(double ABV) {
        this.ABV = ABV;
    }

    // Beer to string
    public String BeerToString() {
        return "Product ID: " + getID() + ", Name: " + getName() + ", Price: " + getPrice() + ", Size: " + getSize()
                + ", Type: " + getType() + ", ABV: " + getABV();
    }
}