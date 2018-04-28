package Users;


import Items.Item;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {

    private List<Item> itemList = new ArrayList<>();
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public void addItem(Item item) {
        List<Item> items = getItemList();
        items.add(item);
        setItemList(items);
    }

    public void removeItem(Item item) {
        List<Item> items = getItemList();
        items.remove(item);
        setItemList(items);
    }

}
