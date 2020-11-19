package training.supportbank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger();
    public static void main(String args[]) {

        //Initialise Bank object
        Bank bank = new Bank();
        bank.updateTransactionFromFile("Transactions2012.xml");
        //Read input files and generate transaction list & person list
        //Feed transaction lists to bank
        //bank will have listAll and list(personAccount)
        UserInput.run(bank);


    }
}
