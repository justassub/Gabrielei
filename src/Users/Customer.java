package Users;


import Items.Item;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
        getItemList().add(item);
    }
    public void removeItem(String item) {
        getItemList().removeIf((Item itemToRemove) -> itemToRemove.getItemName() ==item);
    }

}
