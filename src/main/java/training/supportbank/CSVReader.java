package training.supportbank;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class CSVReader implements Reader {

    public List<Person> people;

    public CSVReader(List<Person> people){
        this.people=people;
    }

    public static List<Transaction> readFile(String filename) throws IOException {
        List<Transaction> transactions =Collections.emptyList();

        BufferedReader csvReader = new BufferedReader(new FileReader(filename));
        String row;
        boolean first=true;
        while ((row = csvReader.readLine()) != null) {
            if(!first) {
                String[] data = row.split(",");
                try {
                    Date date = new SimpleDateFormat("dd/MM/yyyy").parse(data[0]);
                    String to = data[1];
                    String from = data[2];
                    String narrative = data[3];
                    Money amount = new Money(data[4]);

                    Transaction transaction= new Transaction(date,amount,from,to,narrative);
                    transactions.add(transaction);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            first=false;
        }
        csvReader.close();
        return transactions;

    }
}
