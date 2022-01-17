package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import okhttp3.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Base64;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import static sample.webComm.sendData;

public class Controller implements Initializable {

    @FXML
    private Circle circle;

    @FXML
    private TextField txt_email;

    @FXML
    private PasswordField txt_password;

    @FXML
    private Button btn_login;

    @FXML
    private ComboBox<String> comboItems;

    @FXML
    private BorderPane mainPane;


    @FXML
    void btn_click(ActionEvent event) throws IOException, JSONException {
        auth();
    }

    // initializing public variables
    JSONArray jaItems = null;
    String sItems;
    String url = "https://demo.dewcis.com/hcm/pos_server";
    String token = null;

    public void auth() throws IOException, JSONException {
        String userName = txt_email.getText();
        String password = txt_password.getText();

        String token = webComm.auth(url, userName, password);
        /*JSONObject jResp = new JSONObject(token);
        int ResultCode = jResp.getInt("ResultCode");
        if (jResp.has("ResultCode") && (jResp.getInt("ResultCode") == 0)) {
            token = jResp.getString("access_token");
            System.out.println("Authenticated : " + token);*/
        if (token != null) {
            Stage logIn = (Stage) btn_login.getScene().getWindow(); //Getting current window
            Stage primaryStage = new Stage();
            Parent root = null;

            try {
                root = FXMLLoader.load(getClass().getResource("DesktopUI.fxml"));
                Scene s = new Scene(root);
                logIn.setScene(s);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    public JSONArray getItem() throws JSONException {
        sItems = sendData(url + "?view=405:0", token, "read", "{}");
        System.out.println( "Items : " + sItems);
        JSONObject jItems = new JSONObject(sItems);
        if(jItems.has("data")) {
            jaItems = jItems.getJSONArray("data");
            for(int j = 0; j < jaItems.length(); j++){
                JSONObject jItem = jaItems.getJSONObject(j);
                System.out.println(jItem.getString("item_id"));
                System.out.println(jItem.getString("item_name"));
            }
        }
        return jaItems;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image img = new Image("resources/useravatar.jpg");
        circle.setFill(new ImagePattern(img));
    }
}
