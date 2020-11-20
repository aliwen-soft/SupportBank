package training.supportbank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Scanner;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger();
    public static void main(String args[]) {


        //Initialise Bank object
        Bank bank = new Bank();

        try {
            bank.updateTransactionFromFile("DodgyTransactions2015.csv");
            UserInput.run(bank);
        } catch (InvalidParameterException e){
            LOGGER.info("Invalid File Type: Must be .CSV, .XML or .JSON");
        }

        //Read input files and generate transaction list & person list
        //Feed transaction lists to bank
        //bank will have listAll and list(personAccount)
    }
}
