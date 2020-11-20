package training.supportbank;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public abstract class Writer {
    public static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public abstract void write(List<Transaction> transactions, String file) throws IOException;

}
