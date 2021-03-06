package training.supportbank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.security.InvalidParameterException;


public class Main {
    private static final Logger LOGGER = LogManager.getLogger();
    public static void main(String args[]) {
        Bank bank = new Bank();

        try {
            UserInput.run(bank);
        } catch (InvalidParameterException e){
            LOGGER.info("Invalid File Type: Must be .csv, .xml or .json");
        }

    }
}
