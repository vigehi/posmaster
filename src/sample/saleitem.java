package sample;

import javafx.beans.property.SimpleStringProperty;

public class saleitem {
    private String saleItemId;
    private SimpleStringProperty itemId;
    private SimpleStringProperty itemName;
    private SimpleStringProperty categoryName;
    private SimpleStringProperty itemPrice;

    public saleitem(String itemId, String itemName, String categoryName, String itemPrice) {
        this.itemId = new SimpleStringProperty(itemId);
        this.itemName = new SimpleStringProperty(itemName);
        this.categoryName = new SimpleStringProperty(categoryName);
        this.itemPrice = new SimpleStringProperty(itemPrice);
    }

    public String getItemId() {
        return itemId.get();
    }
    public void setItemId(String itemId) {
        this.itemId.set(itemId);
    }

    public String getItemName() {
        return itemName.get();
    }
    public void setItemName(String itemName) {
        this.itemName.set(itemName);
    }

    public String getCategoryName() {
        return categoryName.get();
    }
    public void setCategoryName(String categoryName) {
        this.categoryName.set(categoryName);
    }

    public String getItemPrice() {
        return itemPrice.get();
    }
    public void setItemPrice(String itemPrice) {
        this.itemPrice.set(itemPrice);
    }


}
