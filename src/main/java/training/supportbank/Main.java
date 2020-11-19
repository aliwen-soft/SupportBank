package training.supportbank;

import java.io.IOException;

public class Main {
    public static void main(String args[]) {
        try {
            CSVReader.readFile("Transactions2014.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Test!");
    }
}
