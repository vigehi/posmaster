package sample;

import javafx.beans.property.SimpleStringProperty;

public class store1 {
    private SimpleStringProperty active;
    private SimpleStringProperty categoryName;
    private SimpleStringProperty itemId;
    private SimpleStringProperty item;
    private SimpleStringProperty salesPrice;
    private SimpleStringProperty purchase;
    private SimpleStringProperty itemName;
    private SimpleStringProperty inventory;


    public store1(String active, String categoryName, String itemId, String item, String salesPrice, String purchase, String itemName, String inventory){
        this.active = new SimpleStringProperty(active);
        this.categoryName = new SimpleStringProperty(categoryName);
        this.itemId = new SimpleStringProperty(itemId);
        this.item = new SimpleStringProperty(item);
        this.salesPrice = new SimpleStringProperty(salesPrice);
        this.purchase = new SimpleStringProperty(purchase);
        this.itemName = new SimpleStringProperty(itemName);
        this.inventory = new SimpleStringProperty(inventory);

    }


    public String getActive(){
        return active.get();
    }

    public void setActive(String active){
        this.active = new SimpleStringProperty(active);
    }

    public String getCategoryName() {
        return categoryName.get();
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = new SimpleStringProperty(categoryName);
    }

    public String getItemId() {
        return itemId.get();
    }

    public void setItemId(String itemId) {
        this.itemId = new SimpleStringProperty(itemId);
    }

    public String getItem() {
        return item.get();
    }

    public void setItem(String item) {
        this.item = new SimpleStringProperty(item);
    }

    public String getSalesPrice() {
        return salesPrice.get();
    }

    public void setSalesPrice(String salesPrice) {
        this.salesPrice = new SimpleStringProperty(salesPrice);
    }

    public String getPurchase() {
        return purchase.get();
    }

    public void setPurchase(String purchase) {
        this.purchase = new SimpleStringProperty(purchase);
    }

    public String getItemName() {
        return itemName.get();
    }

    public void setItemName(String itemName) {
        this.itemName = new SimpleStringProperty(itemName);
    }

    public String getInventory() {
        return inventory.get();
    }

    public void setInventory(String inventory) {
        this.inventory = new SimpleStringProperty(inventory);
    }

}