import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.commons.math3.util.OpenIntToDoubleHashMap;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import static org.apache.poi.ss.usermodel.CellType.*;

public class Controller {
    @FXML
    private Button next;

    @FXML
    private Text output;
    private String text;
    private long num = 0;

    private boolean start = true;
    private String delete = "";
    private String ok = "";

    private Model model = new Model();

    public Text getOutput() {
        return output;
    }

    public String getText() {
        return text;
    }

    @FXML
    private void num(ActionEvent event){
        if(start){
            output.setText("");
            start = false;
        }
        String value = ((Button)event.getSource()).getText();
        output.setText(output.getText() + value);
    }


    @FXML
    private void processOk(ActionEvent event){
        String value = output.getText();
        System.out.println(output.getText());
        closeWindow();
        readName();
        start = true;
    }

    @FXML
    private void processDelete(ActionEvent event){
        String value = ((Button)event.getSource()).getText();
        if("delete".equals(value)){
            output.setText("");
            start = true;
        }
    }

    @FXML
    private void initialize(){

    }

    public void readName(){

       try {
           Workbook wb = new HSSFWorkbook(new FileInputStream("src/main/resources/сотрудники.xls"));
            for (int i = 1; i < 3 ; i++) {
               int result =(int) wb.getSheetAt(0).getRow(i).getCell(0).getNumericCellValue();
               if(String.valueOf(result).equals(output.getText())){
                    text = wb.getSheetAt(0).getRow(i).getCell(1).getStringCellValue();
                    System.out.println(text);
                }  //else System.out.println("сотрудник не найден");
                wb.close();}
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public void readName(){

        try {
            FileInputStream input = new FileInputStream("src/main/resources/сотрудники.xls");
            Workbook wb = new HSSFWorkbook(input);
            for (int i = 1; i < 3; i++) {
                String number = getCellText(wb.getSheetAt(0).getRow(i).getCell(0));
                if(number.equals(output.getText())){
                    text = getCellText(wb.getSheetAt(0).getRow(i).getCell(1));
                    System.out.println(text);
                }
            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public void closeWindow(){
        next.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("autorization.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
        // Controller controller = loader.getController();
    }

    public static String getCellText(Cell cell){
        String cellText = null;
        switch (cell.getCellType()) {
            case STRING:
                cellText = cell.getRichStringCellValue().getString();
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    cellText = cell.getDateCellValue().toString();
                } else {
                    cellText = String.valueOf(cell.getNumericCellValue());
                }
                break;
            case BOOLEAN:
                cellText = String.valueOf(cell.getBooleanCellValue());
                break;
            case FORMULA:
                cellText = String.valueOf(cell.getCellFormula());
                break;
            case BLANK:
                cellText = "";
                break;
            default:
                cellText = "";
        }
        return cellText;
    }
}
