import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FirstQuestionController {
    @FXML
    private Button nextWindow;

    @FXML
    private Text yearOutput;
    private String text;
    private long num = 0;

    private boolean start = true;
    private String delete = "";
    private String ok = "";

    private Model model = new Model();

    public Text getOutput() {
        return yearOutput;
    }

    public String getText() {
        return text;
    }

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
        String value = yearOutput.getText();
        System.out.println(yearOutput.getText());
        HSSFWorkbook wb = readWorkbook("src/main/resources/ответы на вопросы.xls");
        String firstAnswer = yearOutput.getText();
        wb.getSheetAt(0).getRow(3).getCell(2).setCellValue(firstAnswer);
       // Cell cell = wb.createSheet("Лист1").createRow(0).createCell(0);
       // cell.setCellValue(yearOutput.getText());
        writeWorkbook(wb,"src/main/resources/ответы на вопросы.xls");
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

    @FXML
    void inizialize(){

    }

    public static HSSFWorkbook readWorkbook(String filename) {
        try {
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(filename));
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            return wb;
        }
        catch (Exception e) {
            return null;
        }
    }

    public static void writeWorkbook(HSSFWorkbook wb, String fileName) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            wb.write(fileOut);
            fileOut.close();
        }
        catch (Exception e) {
            //Обработка ошибки
        }
    }
    /*public void writeToExcel() {
        try {
            FileInputStream input = new FileInputStream("src/main/resources/ответы на вопросы.xls");
            FileOutputStream fos = new FileOutputStream("src/main/resources/ответы на вопросы.xls");
            Workbook wb = new HSSFWorkbook(input);
            String firstAnswer = yearOutput.getText();
            wb.getSheetAt(0).getRow(3).getCell(2).setCellValue(firstAnswer);
            wb.write(fos);
            input.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
