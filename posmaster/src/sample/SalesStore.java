package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SalesStore {
    private SimpleStringProperty item1;
    private SimpleStringProperty category1;
    private SimpleStringProperty name1;
    private SimpleStringProperty sale1;
    private SimpleStringProperty purchase;
    private SimpleStringProperty active1;
    private SimpleStringProperty Q1;
    private SimpleStringProperty fill;
    private SimpleStringProperty time;

    private ObservableList<saleitem> tableData;


    public SalesStore( String categoryName, String itemId, String item, String salesPrice, String purchase, String itemName, String inventory) {
        this.item1 = new SimpleStringProperty("true");
        this.category1 = new SimpleStringProperty(categoryName);
        this.name1 = new SimpleStringProperty(itemId);
        this.sale1 = new SimpleStringProperty(item);
        this.purchase = new SimpleStringProperty(salesPrice);
        this.active1 = new SimpleStringProperty(purchase);
        this.fill = new SimpleStringProperty(itemName);
        this.Q1 = new SimpleStringProperty(inventory);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm ss");
        this.time = new SimpleStringProperty(dateFormat.format(new Date()));



    }

    public String getActive() {
        return active1.get();
    }

    public void setActive(String active) {
        this.active1 = new SimpleStringProperty(active);
    }

    public String getCategoryName() {
        return category1.get();
    }

    public void setCategoryName(String categoryName) {
        this.category1 = new SimpleStringProperty(categoryName);
    }

    public String getItemId() {
        return item1.get();
    }

    public void setItemId(String itemId) {
        this.item1 = new SimpleStringProperty(itemId);
    }

    public String getItem() {
        return name1.get();
    }

    public void setItem(String item) {
        this.name1 = new SimpleStringProperty(item);
    }

    public String getSalesPrice() {
        return sale1.get();
    }

    public void setSalesPrice(String salesPrice) {
        this.sale1 = new SimpleStringProperty(salesPrice);
    }

    public String getPurchase() {
        return purchase.get();
    }

    public void setPurchase(String purchase) {
        this.purchase = new SimpleStringProperty(purchase);
    }

    public String getItemName() {
        return fill.get();
    }

    public void setItemName(String itemName) {
        this.fill = new SimpleStringProperty(itemName);
    }

    public String getInventory() {
        return Q1.get();
    }

    public void setInventory(String inventory) {
        this.Q1 = new SimpleStringProperty(inventory);
    }


    public String getTime() {
        return time.get();
    }

    public SimpleStringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }
}