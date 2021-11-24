package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class FxmlLoader {
    private Pane view;

    public Pane getPage(String fileName){
        try{
            URL fileUrl = Controller.class.getResource("/sample/" + fileName + ".fxml");
            if(fileUrl==null){
                throw new java.io.FileNotFoundException("FXML file can't be found");
            }

            view = new FXMLLoader().load(fileUrl);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return view;
    }
}
