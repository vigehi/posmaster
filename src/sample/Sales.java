package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import static sample.webComm.sendData;

public class Sales implements Initializable {
    private ObservableList<saleitem> tableData;
    private Map<String, JSONObject> mItems;
    JSONArray jaItems = null;
    private Sales primary;

    @FXML
    private AnchorPane paneTop;

    @FXML
    private ImageView imgNew;

    @FXML
    private Label lblStatus;

    @FXML
    private TableView<SalesStore> table1;

    @FXML
    private TableColumn<SalesStore, String> id1;

    @FXML
    private TableColumn<SalesStore, String> cat1;

    @FXML
    private TableColumn<SalesStore, String> nam1;

    @FXML
    private TableView<SalesStore> table2;

    @FXML
    private TableColumn<?, ?> id2;

    @FXML
    private TableColumn<?, ?> cat2;

    @FXML
    private TableColumn<?, ?> nam2;

    @FXML
    private TableColumn<?, ?> p1;

    @FXML
    private TableColumn<SalesStore, String> Q1;

    @FXML
    private TableColumn<?, ?> t1;

    @FXML
    private TableColumn<SalesStore, String> time;

    @FXML
    private Button newsale;

    @FXML
    private TextField text2;

    @FXML
    private TextField text3;
    @FXML
    private Button btn;
    private  int x;

    @FXML
    private TextField text1;
    @FXML
    void btn1(ActionEvent event) {
        int x;
            Object node = event.getSource();
            Button btn = (Button)node;
            System.out.println(btn.getText());//prints out Click Me
           // text2.setText(0);
             text2.setText(btn.getText());
    }

    @FXML
    void btns(ActionEvent event) {
        table2.getItems().clear();
    }

    Button button = new Button("Click Me");



    EventHandler controlEvent = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent event) {
            Button aBtn = (Button) event.getSource();
            addSale(aBtn.getText());
        }
    };


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id1.setCellValueFactory(new PropertyValueFactory<SalesStore, String>("salesPrice"));
        cat1.setCellValueFactory(new PropertyValueFactory<SalesStore, String>("categoryName"));
        nam1.setCellValueFactory(new PropertyValueFactory<SalesStore, String>("itemName"));
        id2.setCellValueFactory(new PropertyValueFactory<>("SalesPrice"));
        cat2.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        nam2.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        p1.setCellValueFactory(new PropertyValueFactory<>("active"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));


editableCol();


        ObservableList<SalesStore> data = FXCollections.observableArrayList();
        table2.setEditable(true);

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

                String sItems = sendData("https://demo.dewcis.com/hcm/pos_server" + "?view=405:1", auth, "read", "{}");
                JSONObject jItems = new JSONObject(sItems);
                if(jItems.has("data")) {
                    jaItems = jItems.getJSONArray("data");
                    for(int j = 0; j < jaItems.length(); j++){
                        JSONObject jItem = jaItems.getJSONObject(j);
                        data.add(new SalesStore(jItem.getString("item_category_name"),jItem.getString("item_id"),jItem.getString("keyfield"),jItem.getString("sales_price"),jItem.getString("purchase_price"),jItem.getString("item_name"),jItem.getString("inventory")));
                    }
                    table1.setItems(data);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        table1.setRowFactory( tv -> {
            TableRow<SalesStore> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (! row.isEmpty()) ) {
                    SalesStore rowData = row.getItem();

                    table2.getItems().addAll(rowData);

                }else if (event.getClickCount() == 1){
                  // Q1.setCellValueFactory();
                }
            });
            return row ;
        });
        FilteredList<SalesStore> filteredData = new FilteredList<>(data, b -> true);
        text1.textProperty().addListener((observable, oldValue, newValue)->{
            filteredData.setPredicate(SalesStore->{
                if(newValue.isEmpty() || newValue.isBlank() || newValue == null){
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();
                if (SalesStore.getItemName().toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                }
                else if (SalesStore.getCategoryName().toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                }
                else if (SalesStore.getSalesPrice().toString().indexOf(searchKeyword) > -1) {
                    return true;
                }
                else
                    return false;
            });
        });
        SortedList<SalesStore> sortedData = new SortedList<>(filteredData);
        //bind the data with tableview
        sortedData.comparatorProperty().bind(table1.comparatorProperty());
        //apply filtered data
        table1.setItems(sortedData);

        table2.getSelectionModel().setCellSelectionEnabled(true);
        Q1.setEditable(true);

        Q1.setOnEditCommit(e -> {

            SalesStore selectedTask = table2.getSelectionModel().getSelectedItem();
            table2.getSelectionModel();
            TableView<SalesStore> table2 = new TableView<>();

            table2.setEditable(true);

        });


    }



    public void addSale(String actionName) {
        if("New Sale".equals(actionName)) {
            tableData.clear();
        }
    }
    private void editableCol(){
        Q1.setCellFactory(TextFieldTableCell.forTableColumn());
        Q1.setOnEditCommit(e ->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setInventory(e.getNewValue());
        });
        table2.setEditable(true);
    }


}








