package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ResourceBundle;

public class Items extends Controller implements Initializable {

    @FXML
    public TableView<store1> itemsView;

    @FXML
    public TableColumn<store1, String> itemId;

    @FXML
    public TableColumn<store1, String> categoryName;

    @FXML
    public TableColumn<store1, String> itemName;

    @FXML
    public TableColumn<store1, String> salesPrice;

    @FXML
    public TableColumn<store1, String> purchase;

    @FXML
    public TableColumn<store1, String> active;

    @FXML
    public TableColumn<store1, String> inventory;

    @FXML
    public TableColumn<store1, String> keyField;

    JSONArray jaItems = null;

    // constructor
    /*Items(String sItems) {

        this.setItems(sItems);
        System.out.println(sItems);
    }*/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        itemId.setCellValueFactory(new PropertyValueFactory<store1, String>("itemId"));
        categoryName.setCellValueFactory(new PropertyValueFactory<store1, String>("categoryName"));
        itemName.setCellValueFactory(new PropertyValueFactory<store1, String>("itemName"));
        salesPrice.setCellValueFactory(new PropertyValueFactory<>("salesPrice"));
        purchase.setCellValueFactory(new PropertyValueFactory<>("purchase"));
        active.setCellValueFactory(new PropertyValueFactory<>("active"));
        inventory.setCellValueFactory(new PropertyValueFactory<>("inventory"));
        keyField.setCellValueFactory(new PropertyValueFactory<>("item"));

        Controller con = new Controller();
        ObservableList<store1> data = FXCollections.observableArrayList();

        /*String sResp = null;
        try {
            sResp = webComm.auth("https://demo.dewcis.com/hcm/pos_server", "root", "baraza");
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jResp = null;
        try {
            jResp = new JSONObject(sResp);
            int ResultCode = jResp.getInt("ResultCode");

            if (jResp.has("ResultCode") && (jResp.getInt("ResultCode") == 0)) {
                String auth = jResp.getString("access_token");

                String sItems = sendData("https://demo.dewcis.com/hcm/pos_server" + "?view=405:0", auth, "read", "{}");
            */
                //System.out.println(sItems);
        try {
                JSONObject jItems = new JSONObject(sItems);
                if (jItems.has("data")) {
                    jaItems = jItems.getJSONArray("data");
                    for (int j = 0; j < jaItems.length(); j++) {
                        JSONObject jItem = jaItems.getJSONObject(j);
                        data.add(new store1(jItem.getString("is_active"), jItem.getString("item_category_name"), jItem.getString("item_id"), jItem.getString("keyfield"), jItem.getString("sales_price"), jItem.getString("purchase_price"), jItem.getString("item_name"), jItem.getString("inventory")));
                    }
                    itemsView.setItems(data);

                }

        }

        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
