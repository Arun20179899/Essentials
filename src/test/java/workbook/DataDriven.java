package workbook;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import resources.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class DataDriven extends Utils {
    public ArrayList<String> getData(String testcaseName) throws IOException {
        ArrayList<String> list = new ArrayList<String>();
//        Get hold of Excel File
        XSSFWorkbook workbook = new XSSFWorkbook(getExcelFilePath());

//        Get count of number of sheets
        int sheets = workbook.getNumberOfSheets();
        System.out.println(sheets);
        for (int i = 0; i < sheets; i++) {
            if (workbook.getSheetName(i).equalsIgnoreCase("TestData")) {
                XSSFSheet sheet = workbook.getSheetAt(i);
//                Identify TestCases column by scanning the entire 1st row
                Iterator<Row> rows = sheet.rowIterator(); // sheet is collection of row
                Row firstrow = rows.next();
                Iterator<Cell> cells = firstrow.cellIterator(); // row is collection of cells
                System.out.println(cells.next().getStringCellValue());
//                Once column is identified than scan entire testcase column to identify Dashboard testcase row
                int k = 0;
                int column = 0;
                while (cells.hasNext()) {
                    Cell value = cells.next();
                    if (value.getStringCellValue().equalsIgnoreCase("TestCase")) {

                        column = k; // get the index of the column name
                    }
                    k++;

                }
                System.out.println(column);

//                once column is identified than scan entire testcase column to identify the "Orders" testcase row

                while (rows.hasNext()) {
                    Row r = rows.next();
                    if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testcaseName)) {
//                        after you grab the "Orders" testcase row than pull all the data of that row and feed into test
                        Iterator<Cell> cv = r.cellIterator();
                        while (cv.hasNext()) {
                            Cell c = cv.next();
                            if (c.getCellType() == CellType.STRING) {
                                list.add(c.getStringCellValue());
                            } else {
                                list.add(NumberToTextConverter.toText(c.getNumericCellValue()));
                            }
                        }
                    }
                }
            }
        }
        return list;
    }

}
