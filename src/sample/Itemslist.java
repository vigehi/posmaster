package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static sample.webComm.sendData;

public class Itemslist implements Initializable {

    @FXML
    private TableView<ItemStore> item_list;

    @FXML
    private TableColumn<ItemStore, String> itemLabel;

    @FXML
    private TableColumn<ItemStore, String> itemCategory;

    @FXML
    private TableColumn<ItemStore, String> itemNombre;

    JSONArray jaItems = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        itemLabel.setCellValueFactory(new PropertyValueFactory<>("itemLbl"));
        itemCategory.setCellValueFactory(new PropertyValueFactory<>("itemCategory"));
        itemNombre.setCellValueFactory(new PropertyValueFactory<>("itemNome"));


        ObservableList<ItemStore> data = FXCollections.observableArrayList();

        String sResp = null;
        sResp = webComm.auth("https://demo.dewcis.com/hcm/pos_server","root", "baraza");
        JSONObject jResp = null;
        try {
            jResp = new JSONObject(sResp);
            int ResultCode = jResp.getInt("ResultCode");
            if(jResp.has("ResultCode") && (jResp.getInt("ResultCode") == 0)) {
                String auth = jResp.getString("access_token");

                String sItems = sendData("https://demo.dewcis.com/hcm/pos_server" + "?view=405:0", auth, "read", "{}");
                JSONObject jItems = new JSONObject(sItems);
                if(jItems.has("data")) {
                    jaItems = jItems.getJSONArray("data");
                    for(int j = 0; j < jaItems.length(); j++){
                        JSONObject jItem = jaItems.getJSONObject(j);
                        data.add(new ItemStore(jItem.getString("item_name"),jItem.getString("item_category_name"),jItem.getString("item_id")));
                    }
                    item_list.setItems(data);
                }
            }
            }
            catch (JSONException e) {
            e.printStackTrace();
        }


        item_list.setRowFactory( tv -> {

            TableRow<ItemStore> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    ItemStore rowData = row.getItem();
                    String Item = rowData.getItemName();
                    //System.out.println("Double click on: " + rowData.getItemNome());
                    System.out.println("Double click on: " + Item);
                }
            });
            return row ;
        });


    }


}




