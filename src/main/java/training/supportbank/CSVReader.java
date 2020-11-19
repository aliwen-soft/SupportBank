package training.supportbank;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader implements Reader {
    public static void readFile(String csvfile) throws IOException {

        BufferedReader csvReader = new BufferedReader(new FileReader(csvfile));
        String row;
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            for(String d:data){
                System.out.println(d);
            }
        }
        csvReader.close();

    }
}
