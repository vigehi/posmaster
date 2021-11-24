package sample;

//import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class DashboardController {
    @FXML
    private Button btn_purchases;

    @FXML
    private AnchorPane paneRight;

    private AnchorPane newRightPane = null;
    private Button temp = null;
    private Button recover = null;

    private HashMap<String, String> FXML_URL = new HashMap<>();

    private void autoResizePane() {
        newRightPane.setPrefWidth(paneRight.getWidth());
        newRightPane.setPrefHeight(paneRight.getHeight());
    }

    /**
     * This method will help to set appropriate right pane contents
     * respective to the left pane selection and will make it responsive if
     * any window resize occurs
     * @param URL: main.resources.view file path; scene
     * @throws IOException
     */

    @FXML
    private void ctrlRightPane(String URL) throws IOException {
        try {
            paneRight.getChildren().clear(); //Removing previous nodes
            newRightPane = FXMLLoader.load(getClass().getResource(URL));

            newRightPane.setPrefHeight(paneRight.getHeight());
            newRightPane.setPrefWidth(paneRight.getWidth());

            paneRight.getChildren().add(newRightPane);

            //Listener to monitor any window size change
            paneRight.layoutBoundsProperty().addListener((obs, oldVal, newVal) -> {
                // Some components of the scene will be resized automatically
                autoResizePane();
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The method here is universal method for all the navigators from left
     * pane which will identify the selection by user and
     * set the respective right pane FXML
     * @param event
     */

    @FXML
    void btnNavigators(ActionEvent event) {
        borderSelector(event); //Marking selected navigator button

        Button btn = (Button)event.getSource();

        // Getting navigation button label
        String btnText = btn.getText();

        // Checking which button is clicked from the map
        // and navigating to respective menu
        try {
            ctrlRightPane(FXML_URL.get(btnText));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFXMLMap() {
        // Adding URLS in the FXML_URL ArrayList field
        // to avoid code redundancy in ctrlRightPane() method
        FXML_URL.put("Dashboard", "/main/resources/view/login.fxml");
        FXML_URL.put("Items", "/main/resources/view/inventory.fxml");
        FXML_URL.put("Customers", "/main/resources/view/customer.fxml");
        FXML_URL.put("Dashboard", "/main/resources/view/dashboard.fxml");
        FXML_URL.put("Sells", "/main/resources/view/sells.fxml");
        FXML_URL.put("Rentals", "/main/resources/view/rentals.fxml");
        FXML_URL.put("Accounts", "/main/resources/view/accounts.fxml");
        FXML_URL.put("Administrative", "/main/resources/view/administrator.fxml");
        FXML_URL.put("Update Due", "/main/resources/view/dueupdate.fxml");
    }

    /**
     * This method will mark selected navigator
     * from left navigation pane and will remove it if another
     * navition button is clicked.
     * @param event
     */
    private void borderSelector(ActionEvent event) {
        Button btn = (Button)event.getSource();

        if(temp == null) {
            temp = btn; //Saving current button properties
        } else {
            temp.setStyle(""); //Resetting previous selected button properties
            temp = btn; //Saving current button properties
        }

        btn.setStyle("-fx-background-color: #455A64");
    }


}
