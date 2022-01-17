package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

import static sample.webComm.sendData;

public class Sales implements Initializable {
    @FXML
    public Label newLbl;
    @FXML
    private Label newLbl1;
    @FXML
    private TableView<SalesStore> itemsData1;
    @FXML
    private TableColumn<SalesStore, String> category1;
    @FXML
    private TableColumn<SalesStore, String> id1;
    @FXML
    private TableColumn<SalesStore, String> name1;
    @FXML
    private TableColumn<SalesStore, String> quantity1;
    @FXML
    private TableColumn<SalesStore, String> unitPrice1;
    @FXML
    private TableColumn<SalesStore, String> time1;
    @FXML
    private TableColumn<SalesStore, String> totalPrice1;
    @FXML
    private Button btnNewSale;
    @FXML
    public TableView<ItemStore> itemList;
    @FXML
    private TableColumn<ItemStore, String> itemID;
    @FXML
    private TableColumn<ItemStore, String> itemCategory;
    @FXML
    private TableColumn<ItemStore, String> itemName;
    @FXML
    private TextField filterField1;
    @FXML
    private TextField filterField;
    @FXML
    private AnchorPane main;
    @FXML
    private Button btnRefresh;
    @FXML
    private TableView<SalesStore> itemsData;
    @FXML
    private TableColumn<SalesStore, String> category;
    @FXML
    private TableColumn<SalesStore, String> id;

    @FXML
    private TableColumn<SalesStore, String> name;
    @FXML
    private TableColumn<SalesStore, String> quantity;
    @FXML
    private TableColumn<SalesStore, String> unitPrice;
    @FXML
    private TableColumn<SalesStore, String> time;
    @FXML
    private TableColumn<SalesStore, String> totalPrice;
    @FXML
    private Button pay_50;
    @FXML
    private Button pay_200;
    @FXML
    private Button pay_100;
    @FXML
    private Button pay_500;
    @FXML
    private Button pay_1000;
    @FXML
    private Button amount;
    @FXML
    private Button cash;
    @FXML
    private Button mpesa;
    @FXML
    private Button total;
    @FXML
    private TextField total_text;
    @FXML
    private TextField total_text1;
    @FXML
    private Button change;
    @FXML
    private Button total1;
    @FXML
    private TextField change_text;

    @FXML
    private Button item_list1;
      @FXML
    private Button btnNewSale1;
    @FXML
    void btn_new(ActionEvent event) {
        itemsData.getItems().clear();
    }
    @FXML
    private void Change(KeyEvent event) throws IOException, JSONException {
        JSONArray   jaItems = getItems();
        for (int i = 0; i < jaItems.length(); i++) {
            JSONObject jItem = jaItems.getJSONObject(i);
            String itemId = jItem.getString("item_id");
            String itemName = jItem.getString("item_name");

            mItems.put(itemId, jItem);

            int nCol = i % 5;

            int nRow = i / 5;

            Button b = new Button(itemName);
            b.setId(itemId);
            b.setMinWidth(50);
            b.setOnAction(itemEvent);
            buttons.add(b);
            ScrollBar s = new ScrollBar();
            StackPane root = new StackPane();
            root.getChildren().add(s);
            stack.getChildren().add(s);
            stack.getChildren().add(nCol,b);
        }
    }
    @FXML
    private GridPane grid;
    @FXML
    private Button add;
    @FXML
    private TextField button;
    @FXML
    private ListView<Button> visibleList;

    private ObservableList<Button> buttons =
            FXCollections.observableArrayList();
    @FXML
    void addName() {
        Button b = new Button(button.getText());
        buttons.add(b);
        b.addEventHandler(MouseEvent.MOUSE_CLICKED,
                (event -> b.setText((b.getText()))));
        button.getId();
    }
    @FXML
    private HBox store;
    @FXML
    private TextField such;

    @FXML
    private VBox stack;

    private ObservableList<SaleT> tableData;

    private HashMap<String, JSONObject> mItems;
    JSONArray   jaItems = null;
    String      saleId = null;
    String      sURL = "https://demo.dewcis.com/hcm/pos_server";
    String      token = null;
    public void token() throws IOException {
        String userName = "root";
        String password = "baraza";
        token = webComm.auth(sURL, userName, password);
    }
    public void controlEvent(ActionEvent event) throws JSONException {
        Button aBtn = (Button) event.getSource();
        addSale(aBtn.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        itemID.setCellValueFactory(new PropertyValueFactory<>("itemID"));
        itemCategory.setCellValueFactory(new PropertyValueFactory<>("itemCategory"));
        itemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        unitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        totalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        category1.setCellValueFactory(new PropertyValueFactory<>("category"));
        id1.setCellValueFactory(new PropertyValueFactory<>("id"));
        name1.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantity1.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        unitPrice1.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        time1.setCellValueFactory(new PropertyValueFactory<>("time"));
        totalPrice1.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        ObservableList<ItemStore> myItems = FXCollections.observableArrayList();
        ObservableList<SalesStore> data = FXCollections.observableArrayList();
        ObservableList<SaleT> sItem = FXCollections.observableArrayList();
        mItems = new HashMap<String, JSONObject>();
        itemsData.setEditable(true);
        editableCol();
        Button btnNewSale = new Button("New Sale");
        Button btnRefresh = new Button("Refresh");
       // btnNewSale.setOnAction(controlEvent);
        //btnRefresh.setOnAction(controlEvent);

        try {
            token();
            String sItems = sendData(sURL + "?view=405:1", token, "read", "{}");
            JSONObject jItems = new JSONObject(sItems);
            if (jItems.has("data")) {
                jaItems = jItems.getJSONArray("data");
                for (int j = 0; j < jaItems.length(); j++) {
                    JSONObject jItem = jaItems.getJSONObject(j);
                    myItems.add(new ItemStore(jItem.getString("item_name"), jItem.getString("item_category_name"), jItem.getString("item_id")));
                    String itemId = jItem.getString("item_id");
                    mItems.put(itemId, jItem);
                }
                itemList.setItems(myItems);
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        itemList.setRowFactory( tv -> {

            TableRow<ItemStore> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (! row.isEmpty()) ) {
                    ItemStore rowData = row.getItem();
                    String itemId = rowData.getItemID();
                    //System.out.println("Double click on: " + rowData.getItemNome());

                    JSONObject jItem = mItems.get(itemId);
                    System.out.println(jItem);

                    try {
                        int Price = Integer.parseInt(jItem.getString("sales_price").replaceAll("[^a-zA-Z0-9]", ""));
                        int quantity = 1;
                        int total = Price * quantity;

                        data.add(new SalesStore(jItem.getString("item_category_name"),itemId,jItem.getString("sales_price"), jItem.getString("item_name"), "1", "" , total ));
                       itemsData.setItems(data);
                       // itemsData1.setItems(data);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            });
            return row ;
        });
        //itemsData1.setItems(tableData);

        FilteredList<ItemStore> filteredData = new FilteredList<>(myItems, b -> true);
        filterField1.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(ItemStore -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();
                if (ItemStore.getItemName().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (ItemStore.getItemCategory().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (ItemStore.getItemID().toString().indexOf(searchKeyword) > -1) {
                    return true;
                } else
                    return false;
            });
        });
        SortedList<ItemStore> sortedData = new SortedList<>(filteredData);
        //bind the data with tableview
        sortedData.comparatorProperty().bind(itemList.comparatorProperty());
        //apply filtered data
        itemList.setItems(sortedData);
        FilteredList<ItemStore> filter = new FilteredList<>(myItems, b -> true);
        such.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate(ItemStore -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();
                if (ItemStore.getItemName().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (ItemStore.getItemCategory().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (ItemStore.getItemID().toString().indexOf(searchKeyword) > -1) {
                    return true;
                } else
                    return false;
            });
        });
        SortedList<ItemStore> sort = new SortedList<>(filteredData);
         }

    public void addItem(String itemId) throws JSONException {
        System.out.println("Add Item " + itemId);

        if(saleId != null) {
            JSONObject jItem = mItems.get(itemId);

            JSONObject jSale = new JSONObject();
            jSale.put("item_id", itemId);
            jSale.put("quantity", "1");
            jSale.put("item_price", jItem.getString("sales_price"));

            String aUrl = sURL + "?fnct=insert&view=200:0:0:0&linkdata=" + saleId;
            String sData = sendData(aUrl, token, "grid_update", jSale.toString());
            JSONObject jData = new JSONObject(sData);

            SaleT sItem = new SaleT(itemId, jItem.getString("item_name"), jItem.getString("item_category_name"), jItem.getString("sales_price"));
            tableData.add(sItem);
        }
    }

    EventHandler itemEvent = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent event) {
            Button abtn = (Button) event.getSource();

            try {
                addItem(abtn.getId());
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    };


    public void addSale(String actionName) throws JSONException {
        if ("New Sale".equals(actionName)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String currDate = dateFormat.format(new Date());
            JSONObject jParams = new JSONObject();
            jParams.put("transaction_date", currDate);

            String sItems = sendData(sURL + "?view=405:0", token, "read", /*"{}",*/ jParams.toString());
            JSONObject jItems = new JSONObject(sItems);
            if (jItems.has("data")) {
                JSONObject jSale = jItems.getJSONObject("data");
                saleId = jSale.getString("keyfield");
            }
            //data.clear();
        }
    }



    @FXML
    public void total(ActionEvent actiontotal) {
        TableView<SalesStore> table = itemsData;
        int sum = 0 ;
        for (SalesStore item: table.getItems()) {
            sum = sum + item.getTotalPrice();
            total_text1.setText(String.valueOf(sum));
        }


    }

    public void calculate(ActionEvent actionEvent) {

        }
    private void editableCol(){
        quantity.setCellFactory(TextFieldTableCell.forTableColumn());
        quantity.setOnEditCommit(e ->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setQuantity(e.getNewValue());

        });
        itemsData.setEditable(true);
        itemsData1.setEditable(true);
    }
    public JSONArray getItems() throws JSONException {
        String sData = webComm.sendData(sURL + "?view=405:1", token, "read", "{}");
        JSONObject jData = new JSONObject(sData);

        JSONArray jaItems = new JSONArray();
        if(jData.has("data")) jaItems = jData.getJSONArray("data");

        return jaItems;
    }


    }








