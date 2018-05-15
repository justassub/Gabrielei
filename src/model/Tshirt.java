//v1.2
package model;

public class Tshirt extends Product {
	private String sex;
	private String color;
	
	// Empty constructor
	public Tshirt() {
	}
	// Constructor with values including super type from Product
	public Tshirt(int ID, String name, double price, String size,
			String sex, String color) {
		super(ID, price, name, size);
		this.sex = sex;
		this.color = color;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	// Get Tshirt to string
	public String TshirtToString() {
		return "Product ID: " + getID() + ", Name: " + getName() + ", Price: " + getPrice() + " DKK" + ", Size: " + getSize()
				+ ", Sex: " + getSex() + ", Color: " + getColor();
	}
}
