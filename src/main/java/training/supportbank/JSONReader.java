package training.supportbank;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

public class JSONReader extends Reader {
    private static final Logger LOGGER = LogManager.getLogger();


    public JSONReader(List<Person> people){
        this.people=people;
    }

    public List<Transaction> readFile(String filename) throws IOException,NumberFormatException {
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
            //add trans func
            System.out.println(snarrative);
        }
        return transactions;
    }
}

