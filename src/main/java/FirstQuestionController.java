import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.awt.event.MouseEvent;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class FirstQuestionController {

    @FXML
    private Button nextWindow;

    @FXML
    private Text yearOutput;

    //private String text;
    //private long num = 0;

    private boolean start = true;
    //private String delete = "";
    //private String ok = "";

    @FXML
    private void number(ActionEvent event) {
        if (start) {
            yearOutput.setText("");
            start = false;
        }
        String value = ((Button) event.getSource()).getText();
        yearOutput.setText(yearOutput.getText() + value);
    }


    @FXML
    private void processSend(ActionEvent event) {
        System.out.println(yearOutput.getText());
        writeFirstAnswer(yearOutput.getText());
        openSecondQuestion();
        start = true;
    }

    @FXML
    private void processDeleteYear(ActionEvent event) {
        String value = ((Button) event.getSource()).getText();
        if ("delete".equals(value)) {
            yearOutput.setText("");
            start = true;
        }
    }

   /* @FXML
    void inizialize() {
        yearOutput.setText("234");
    }
*/
    public void openSecondQuestion(){
        nextWindow.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("secondQuestion.fxml"));

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


    public static void writeFirstAnswer(String answer){
        try{
            FileInputStream myxls = new FileInputStream("src/main/resources/ответы на вопросы.xls");
            HSSFWorkbook wb = new HSSFWorkbook(myxls);
            HSSFSheet answers = wb.getSheetAt(0);
            Row row = answers.getRow(2);
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

   /* @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }*/

}


