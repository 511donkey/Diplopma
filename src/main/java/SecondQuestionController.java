import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class SecondQuestionController implements Initializable {

    @FXML
    private Button one;
    @FXML
    private Button two;
    @FXML
    private Button three;
    @FXML
    private Button four;
    @FXML
    private Button five;
    @FXML
    private Button six;
    @FXML
    private Button seven;
    @FXML
    private Button eight;
    @FXML
    private Button nine;
    @FXML
    private Button ten;
    @FXML
    private Button eleven;
    @FXML
    private Button twelve;



   /* @FXML
    public void monthsNumber (ActionEvent event) {

        String value = ((Button) event.getSource()).getText();

        writeSecondAnswer(value);

    }*/

    public static void writeSecondAnswer(String answer){
        try{
            FileInputStream myxls = new FileInputStream("src/main/resources/ответы на вопросы.xls");
            HSSFWorkbook wb = new HSSFWorkbook(myxls);
            HSSFSheet answers = wb.getSheetAt(0);
            Row row = answers.getRow(3);
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

    public void openThirdQuestion(Button button){
        button.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("thirdQuestion.fxml"));

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
        one.setOnAction(event -> {
        writeSecondAnswer(one.getText());
        openThirdQuestion(one);
        });

        two.setOnAction(event -> {
            writeSecondAnswer(two.getText());
            openThirdQuestion(two);
        });

        three.setOnAction(event -> {
            writeSecondAnswer(three.getText());
            openThirdQuestion(three);
        });

        four.setOnAction(event -> {
            writeSecondAnswer(four.getText());
            openThirdQuestion(four);
        });

        five.setOnAction(event -> {
            writeSecondAnswer(five.getText());
            openThirdQuestion(five);
        });

        six.setOnAction(event -> {
            writeSecondAnswer(six.getText());
            openThirdQuestion(six);
        });

        seven.setOnAction(event -> {
            writeSecondAnswer(seven.getText());
            openThirdQuestion(seven);
        });

        eight.setOnAction(event -> {
            writeSecondAnswer(eight.getText());
            openThirdQuestion(eight);
        });

        nine.setOnAction(event -> {
            writeSecondAnswer(nine.getText());
            openThirdQuestion(nine);
        });

        ten.setOnAction(event -> {
            writeSecondAnswer(ten.getText());
            openThirdQuestion(ten);
        });

        eleven.setOnAction(event -> {
            writeSecondAnswer(eleven.getText());
            openThirdQuestion(eleven);
        });

        twelve.setOnAction(event -> {
            writeSecondAnswer(twelve.getText());
            openThirdQuestion(twelve);
        });
    }
}
