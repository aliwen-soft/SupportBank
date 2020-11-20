package training.supportbank;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class JSONReader extends Reader {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Date convertDate(String date) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(date);
    }

    public List<Transaction> readFile(Bank bank,String filename) throws IOException,NumberFormatException {
        List<Transaction> transactions = new ArrayList();
        Gson gson = new Gson();
        Map[] allTransactions = gson.fromJson(new FileReader(filename), Map[].class);

        for (Map<String,Object> transaction : allTransactions) {
            String date = (String) transaction.get("date");
            String from = (String) transaction.get("fromAccount");
            String to = (String)transaction.get("toAccount");
            String narrative = (String)transaction.get("narrative");
            String amount =Double.toString((Double) transaction.get("amount"));
            addTransaction(bank,date,to,from,narrative,amount);
        }
        return transactions;
    }
}

