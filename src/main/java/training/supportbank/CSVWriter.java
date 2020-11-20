package training.supportbank;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVWriter extends Writer {
    @Override
    public void Write(List<Transaction> transactions, String file) throws IOException {
        FileWriter writer = new FileWriter(file);

        writer.write("Date,From,To,Narrative,Amount");

       for (Transaction t: transactions){
           String line = dateFormat.format(t.getTransactionDate()) + "," + t.getTransactionFrom() +"," + t.getTransactionTo()+","+t.getTransactionNarrative()+","+t.getTransactionAmount().printMoney();
           writer.write(line);
           System.out.println(line);
       }

        writer.close();
    }
}
