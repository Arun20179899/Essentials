package runner;

import workbook.DataDriven;

import java.io.IOException;
import java.util.ArrayList;

public class LoginTest {
    public static void main(String[] args) throws IOException {
        DataDriven data = new DataDriven();
        ArrayList<String> value = data.getData("Orders");
//        System.out.println(value.get(2));
        System.out.println(value);
    }
}
