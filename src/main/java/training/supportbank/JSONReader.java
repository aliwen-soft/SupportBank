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
        Date dateOut = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        return  dateOut;
    }

    public List<Transaction> readFile(Bank bank,String filename) throws IOException,NumberFormatException {
        List<Transaction> transactions = new ArrayList<Transaction>();


        Gson gson = new Gson();
        ArrayList<ArrayList> allData = gson.fromJson(new FileReader(filename), ArrayList.class);
        for (Object item:allData
             ) {
            //System.out.println(item);
            String stringData = item.toString();
            String[] dataRaw = stringData.substring(1,stringData.length()-2).split(",");
            Stream<Object> dataParsed = Arrays.stream(dataRaw).map(i -> i.split("=")[1]);
            Object[] data = dataParsed.toArray();
            String sdate = data[0].toString();
            String sfrom = data[1].toString();
            String sto = data[2].toString();
            String snarrative = data[3].toString();
            String samount = data[4].toString();
            addTransaction(bank,sdate,sto,sfrom,snarrative,samount);
            //System.out.println(snarrative);
        }
        return transactions;
    }
}

