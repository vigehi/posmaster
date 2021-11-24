package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static sample.webComm.sendData;

public class Items implements Initializable {
    @FXML
    private AnchorPane paneTop;

    @FXML
    private ImageView imgNew;

    @FXML
    private Label lblStatus;

    @FXML
    private TableView<ItemStore> tableview2;

    @FXML
    private TableColumn<ItemStore, String> item1;

    @FXML
    private TableColumn<ItemStore, String> category1;

    @FXML
    private TableColumn<ItemStore, String> name1;

    @FXML
    private TableColumn<ItemStore, String> sale1;

    @FXML
    private TableColumn<ItemStore, String> purchase;

    @FXML
    private TableColumn<ItemStore, String> active1;

    @FXML
    private TableColumn<ItemStore, String> invent;

    @FXML
    private TableColumn<ItemStore, String> fill;
    JSONArray jaItems = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        item1.setCellValueFactory(new PropertyValueFactory<ItemStore, String>("salesPrice"));
        category1.setCellValueFactory(new PropertyValueFactory<ItemStore, String>("categoryName"));
        name1.setCellValueFactory(new PropertyValueFactory<ItemStore, String>("itemName"));
        sale1.setCellValueFactory(new PropertyValueFactory<>("purchase"));
        purchase.setCellValueFactory(new PropertyValueFactory<>("active"));
        active1.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        invent.setCellValueFactory(new PropertyValueFactory<>("inventory"));
        fill.setCellValueFactory(new PropertyValueFactory<>("item"));

        ObservableList<ItemStore> data = FXCollections.observableArrayList();

        String sResp = null;
        try {
            sResp = webComm.auth("https://demo.dewcis.com/hcm/pos_server","root", "baraza");
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jResp = null;
        try {
            jResp = new JSONObject(sResp);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            int ResultCode = jResp.getInt("ResultCode");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            if(jResp.has("ResultCode") && (jResp.getInt("ResultCode") == 0)) {
                String auth = jResp.getString("access_token");

                String sItems = sendData("https://demo.dewcis.com/hcm/pos_server" + "?view=405:0", auth, "read", "{}");
                JSONObject jItems = new JSONObject(sItems);
                if(jItems.has("data")) {
                    jaItems = jItems.getJSONArray("data");
                    for(int j = 0; j < jaItems.length(); j++){
                        JSONObject jItem = jaItems.getJSONObject(j);
                        data.add(new ItemStore(jItem.getString("is_active"),jItem.getString("item_category_name"),jItem.getString("item_id"),jItem.getString("keyfield"),jItem.getString("sales_price"),jItem.getString("purchase_price"),jItem.getString("item_name"),jItem.getString("inventory")));
                    }
                    tableview2.setItems(data);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
