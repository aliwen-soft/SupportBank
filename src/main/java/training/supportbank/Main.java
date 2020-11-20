package training.supportbank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Scanner;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger();
    public static void main(String args[]) {
        Bank bank = new Bank();

        try {
            UserInput.run(bank);
        } catch (InvalidParameterException e){
            LOGGER.info("Invalid File Type: Must be .CSV, .XML or .JSON");
        }

    }
}
