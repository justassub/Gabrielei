package Services;

import Items.Item;

import java.util.List;

public interface CustomerInterface {
    void displayProducts();
    void payProducts(String paymentWay);
    boolean confirmationOfProducts();
    void seeReceipt();
    void makePayment();
    void addProductIntoCart();
    void removeProductFromCart();

}
