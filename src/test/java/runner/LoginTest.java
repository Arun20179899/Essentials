package runner;

import workbook.DataDriven;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LoginTest {
    public static void main(String[] args) throws IOException {
//        Scanner sc =  new Scanner(System.in);
//        System.out.println("Enter the Test Case Name");
//        String testCaseName = sc.next();
        DataDriven data = new DataDriven();
        ArrayList<String> value = data.getData("TestData", "RestAssured","AddBook");
        System.out.println(value.get(2));
        System.out.println(value);
    }
}
