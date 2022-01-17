package sample;

import javafx.beans.property.SimpleStringProperty;

public class ItemStore {
    private SimpleStringProperty itemName;
    private SimpleStringProperty itemCategory;
    private SimpleStringProperty itemID;


    public ItemStore(String itemName, String itemCategory, String itemID ) {
        this.itemName = new SimpleStringProperty(itemName);
        this.itemCategory = new SimpleStringProperty(itemCategory);
        this.itemID = new SimpleStringProperty(itemID);
    }

    public String getItemName(){
        return itemName.get();
    }

    public void setItemName(String itemName){
        this.itemName = new SimpleStringProperty(itemName);
    }

    public String getItemCategory() {
        return itemCategory.get();
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = new SimpleStringProperty(itemCategory);
    }

    public String getItemID() {
        return itemID.get();
    }

    public void setItemID(String itemID) {
        this.itemID = new SimpleStringProperty(itemID);
    }
}
