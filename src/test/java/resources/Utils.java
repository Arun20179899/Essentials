package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Utils {
    public FileInputStream getExcelFilePath() throws FileNotFoundException {
        FileInputStream fis = new FileInputStream("C:\\Users\\Arun\\Documents\\GetData.xlsx");
        return fis;
    }
}
