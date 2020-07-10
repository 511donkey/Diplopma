import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.*;
import java.net.URL;
import java.sql.BatchUpdateException;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AutorizationController extends BasicController implements Initializable {

    @FXML
    private Label name;

    @FXML
    private Button sayYes;

    @FXML
    private Button sayNo;

    @FXML
    private Button onStart;

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

    public static void writeNameToExcel (String answer){
        try{
            FileInputStream myxls = new FileInputStream("src/main/resources/ответы на вопросы.xls");
            HSSFWorkbook wb = new HSSFWorkbook(myxls);
            HSSFSheet answers = wb.getSheetAt(0);
            Row row = answers.getRow(1);
            row.createCell(2).setCellValue(answer);
            myxls.close();
            FileOutputStream outputStream = new FileOutputStream(new File("src/main/resources/ответы на вопросы.xls"));
            wb.write(outputStream);
            outputStream.close();
            System.out.println("is successfully written");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sayYes.setOnAction(event -> {
            writeNameToExcel(((Controller) Controllers.get(Controller.class)).getText());
            openDataProcessingWindow();
        });
        sayNo.setOnAction(event -> {
            openStartWindow();
        });

        if(((Controller) Controllers.get(Controller.class)).getText() == null)
        {name.setText("сотрудник не найден");} else {name.setText(((Controller) Controllers.get(Controller.class)).getText() + ", это Вы ?");}

        onStart.setOnAction(event -> {
            openStartWindow();
        });
    }
}
