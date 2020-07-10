import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class FourthQuestionController implements Initializable {

    @FXML
    private Button noSmoking;

    @FXML
    private Button onePack;

    @FXML
    private Button twoPacks;

    @FXML
    private Button fivePacks;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        noSmoking.setOnAction(event -> {
            writeFourthAnswer(noSmoking.getText());

            openFifthQuestion(noSmoking);

        });

        onePack.setOnAction(event -> {
            writeFourthAnswer(onePack.getText());

            openFifthQuestion(onePack);

        });

        twoPacks.setOnAction(event -> {
            writeFourthAnswer(twoPacks.getText());

            openFifthQuestion(twoPacks);

        });

        fivePacks.setOnAction(event -> {
            writeFourthAnswer(fivePacks.getText());

            openFifthQuestion(fivePacks);

        });
    }

    public static void writeFourthAnswer(String answer){
        try{
            FileInputStream myxls = new FileInputStream("src/main/resources/ответы на вопросы.xls");
            HSSFWorkbook wb = new HSSFWorkbook(myxls);
            HSSFSheet answers = wb.getSheetAt(0);
            Row row = answers.getRow(5);
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

    public void openFifthQuestion(Button button){
        button.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fifthQuestion.fxml"));

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
}
