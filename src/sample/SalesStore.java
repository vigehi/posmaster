package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SalesStore {
    private SimpleStringProperty category;
    private SimpleStringProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty quantity;
    private SimpleStringProperty unitPrice;
    private SimpleStringProperty time;
    private SimpleIntegerProperty totalPrice;

    public SalesStore(String category, String id, String unitPrice, String name, String quantity, String time, int totalPrice){
        this.category = new SimpleStringProperty(category);
        this.id = new SimpleStringProperty(id);
        this.unitPrice = new SimpleStringProperty(unitPrice);
        this.name = new SimpleStringProperty(name);
        this.quantity = new SimpleStringProperty(quantity);
        this.time     = new SimpleStringProperty(time);
        this.totalPrice= new SimpleIntegerProperty(totalPrice);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm ss");
        this.time = new SimpleStringProperty(dateFormat.format(new Date()));

    }



    public String getCategory() {
        return category.get();
    }

    public void setCategory(String category) {
        this.category = new SimpleStringProperty(category);
    }

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id = new SimpleStringProperty(id);
    }

    public String getUnitPrice() {
        return unitPrice.get();
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = new SimpleStringProperty(unitPrice);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public String getQuantity(){return quantity.get();}

    public void setQuantity(String quantity){this.quantity = new SimpleStringProperty(quantity);}


    public String getTime() {
        return time.get();
    }

    public SimpleStringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public int getTotalPrice(){return totalPrice.get();}

    public void setTotalPrice(int totalPrice){this.totalPrice = new SimpleIntegerProperty(totalPrice);}
}