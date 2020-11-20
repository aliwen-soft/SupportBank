package training.supportbank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CSVReader extends Reader {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Date convertDate(String date) throws ParseException {
        Date dateOut = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        return  dateOut;
    }

    public List<Transaction> readFile(Bank bank, File file) throws IOException,NumberFormatException {
        List<Transaction> transactions = new ArrayList<Transaction>();

        BufferedReader csvReader = new BufferedReader(new FileReader(file));
        String row;
        boolean first=true;
        int lineNumber = 0;
        while ((row = csvReader.readLine()) != null) {
            if(!first) {
                String[] data = row.split(",");
                lineNumber++;
                try {
                    addTransaction(bank,data[0],data[1],data[2],data[3],data[4]);
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
