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

    public JSONReader(List<Person> people){
        this.people=people;
    }

    @Override
    public Date convertDate(String date) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(date);
    }

    public List<Transaction> readFile(String filename) throws IOException,NumberFormatException {
        List<Transaction> transactions = new ArrayList();
        Gson gson = new Gson();
        Map[] allTransactions = gson.fromJson(new FileReader(filename), Map[].class);
        System.out.println(allTransactions[0].get("fromAccount"));

        for (Map<String,String> transaction : allTransactions) {
            String sdate = transaction.get("date");
            String sfrom = transaction.get("fromAccount");
            String sto = transaction.get("toAccount");
            String snarrative = transaction.get("narrative");
            String samount = transaction.get("narrative");
            addTransaction(transactions,sdate,sto,sfrom,snarrative,samount);
        }
        return transactions;
    }
}

