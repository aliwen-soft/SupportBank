package training.supportbank;

import java.io.IOException;
import java.util.List;

public interface Reader {

    public List<Transaction> readFile(String filename) throws IOException;
}
