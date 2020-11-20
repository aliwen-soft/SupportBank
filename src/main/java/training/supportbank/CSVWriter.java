package training.supportbank;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVWriter extends Writer {
    @Override
    public void Write(List<Transaction> transactions, String file) throws IOException {
        FileWriter writer = new FileWriter(file);

        writer.write("Date,From,To,Narrative,Amount\n");

       for (Transaction t: transactions){
           String line = dateFormat.format(t.getTransactionDate()) + "," + t.getTransactionFrom().getName() +"," + t.getTransactionTo().getName()+","+t.getTransactionNarrative()+","+t.getTransactionAmount().printMoney(false);
           writer.write(line);
           writer.write("\n");
       }

        writer.close();
    }
}
