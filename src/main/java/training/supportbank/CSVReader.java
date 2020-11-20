package training.supportbank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CSVReader extends Reader {
    private static final Logger LOGGER = LogManager.getLogger();
   

    public CSVReader(List<Person> people){
        this.people=people;
    }

    @Override
    public Date convertDate(String date) throws ParseException {
        Date dateOut = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        return  dateOut;
    }

    public List<Transaction> readFile(String filename) throws IOException,NumberFormatException {
        List<Transaction> transactions = new ArrayList<Transaction>();

        BufferedReader csvReader = new BufferedReader(new FileReader(filename));
        String row;
        boolean first=true;
        int lineNumber = 0;
        while ((row = csvReader.readLine()) != null) {
            if(!first) {
                String[] data = row.split(",");
                lineNumber++;
                try {
                    Date date = new SimpleDateFormat("dd/MM/yyyy").parse(data[0]);
                    Person to = checkPerson(data[1]);
                    Person from = checkPerson(data[2]);
                    String narrative = data[3];
                    Money amount = new Money(data[4]);

                    Transaction transaction= new Transaction(date,amount,from,to,narrative);
                    transactions.add(transaction);
                } catch (ParseException e) {
                    LOGGER.info("Invalid date at line " + lineNumber + ": " + data[0]);
                } catch(NumberFormatException e){
                LOGGER.info("Non-parsable string given for amount at line " + lineNumber + ": '" + data[4] + "'");
            }
            }
            first=false;
        }
        csvReader.close();
        return transactions;

    }


}
