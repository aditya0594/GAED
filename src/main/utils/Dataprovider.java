package utils;

import baseClass.TestBase;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class Dataprovider extends TestBase {


    @org.testng.annotations.DataProvider(name = "ValidLoginEmail")
    public static Object[][] LoginData() throws IOException {

        FileInputStream fis = new FileInputStream("src/resources/ExcelFile.xlsx");
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet loginsheet = workbook.getSheet("ValidLoginsheet");

        int numberOfRows = loginsheet.getPhysicalNumberOfRows();
        Object[][] testData = new Object[numberOfRows - 1][2]; // Adjust for header row if present

        for (int i = 1; i < numberOfRows; i++) { // Start from 1 to skip header row
            Row row = loginsheet.getRow(i);

            if (row != null) {
                Cell usernameEmail = row.getCell(0);
                Cell password = row.getCell(1);

                if (usernameEmail != null) {
                    testData[i - 1][0] = getCellValue(usernameEmail);
                } else {
                    testData[i - 1][0] = ""; // Handle null cell
                }

                if (password != null) {
                    testData[i - 1][1] = getCellValue(password);
                } else {
                    testData[i - 1][1] = ""; // Handle null cell
                }

                System.out.println("Username fetched value: " + usernameEmail);
                System.out.println("Password fetched value: " + password);
            }
        }

        workbook.close();
        fis.close();

        return testData;
    }
    @org.testng.annotations.DataProvider(name = "InvalidLoginEmail")
    public static Object[][] InvalidLoginEmail() throws IOException {

        FileInputStream fis = new FileInputStream("src/resources/ExcelFile.xlsx");
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet loginsheet = workbook.getSheet("InvalidLoginEmail");

        int numberOfRows = loginsheet.getPhysicalNumberOfRows();
        Object[][] testData = new Object[numberOfRows - 1][2]; // Adjust for header row if present

        for (int i = 1; i < numberOfRows; i++) { // Start from 1 to skip header row
            Row row = loginsheet.getRow(i);

            if (row != null) {
                Cell usernameEmail = row.getCell(0);
                Cell password = row.getCell(1);

                if (usernameEmail != null) {
                    testData[i - 1][0] = getCellValue(usernameEmail);
                } else {
                    testData[i - 1][0] = ""; // Handle null cell
                }

                if (password != null) {
                    testData[i - 1][1] = getCellValue(password);
                } else {
                    testData[i - 1][1] = ""; // Handle null cell
                }

                System.out.println("Username fetched value: " + usernameEmail);
                System.out.println("Password fetched value: " + password);
            }
        }

        workbook.close();
        fis.close();

        return testData;
    }
    private static String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            default:
                return "";
        }
    }


}