package training.supportbank;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

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
            String sdate = (String) transaction.get("date");
            String sfrom = (String) transaction.get("fromAccount");
            String sto = (String)transaction.get("toAccount");
            String snarrative = (String)transaction.get("narrative");
            String samount =Double.toString((Double) transaction.get("amount"));
            addTransaction(bank,sdate,sto,sfrom,snarrative,samount);
        }
        return transactions;
    }
}

