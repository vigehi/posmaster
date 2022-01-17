package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SaleT {
    private String saleItemId;
    private SimpleStringProperty id1;
    private SimpleStringProperty name1;
    private SimpleStringProperty category1;
    private SimpleStringProperty unitPrice1;
    private SimpleStringProperty time1;
    private SimpleStringProperty totalPrice1;


    public SaleT(String itemId, String itemName, String categoryName, String itemPrice) {
        this.id1 = new SimpleStringProperty(itemId);
        this.name1 = new SimpleStringProperty(itemName);
        this.category1 = new SimpleStringProperty(categoryName);
        this.unitPrice1 = new SimpleStringProperty(itemPrice);
        this.totalPrice1 = new SimpleStringProperty(itemPrice);


        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm ss");
        this.time1 = new SimpleStringProperty(dateFormat.format(new Date()));
    }

    public String getItemId() {
        return id1.get();
    }
    public void setItemId(String itemId) {
        this.id1.set(itemId);
    }

    public String getItemName() {
        return name1.get();
    }
    public void setItemName(String itemName) {
        this.name1.set(itemName);
    }

    public String getCategoryName() {
        return category1.get();
    }
    public void setCategoryName(String categoryName) {
        this.category1.set(categoryName);
    }

    public String getItemPrice() {
        return unitPrice1.get();
    }
    public void setItemPrice(String itemPrice) {
        this.unitPrice1.set(itemPrice);
    }

    public String getSaleTime() {
        return time1.get();
    }
    public void setSaleTime(String saleTime) {
        this.time1.set(saleTime);
    }
    public String getSale() {
        return totalPrice1.get();
    }
    public void setSale(String saleTime) {
        this.totalPrice1.set(saleTime);
    }
}
