import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.BatchUpdateException;
import java.util.ResourceBundle;

public class AutorizationController implements Initializable {

    //Controller controller;

    //private String text = controller.getText();

    //FXMLLoader loader = new FXMLLoader();


   // @FXML
   // Controller controller = loader.getController();

    @FXML
    private Label name;

    @FXML
    private Button sayYes;

    @FXML
    private Button sayNo;

    public void openStartWindow(){
        sayNo.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("start.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    public void openDataProcessingWindow(){
        sayYes.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("dataProcessing.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sayYes.setOnAction(event -> {
            openDataProcessingWindow();
        });
        sayNo.setOnAction(event -> {
            openStartWindow();
        });
        //name.setText(text);
    }
}
