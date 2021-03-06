package training.supportbank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.processing.Filer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Bank {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final DateFormat DATA_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    private List<Person> people = new ArrayList<Person>();
    private List<Transaction> transactions = new ArrayList<Transaction>();

    public List<Person> getPeople() {
        return people;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    //edits the balance of the sender and receiver of a given transaction
    private void PerformTransaction(Transaction trans){
        trans.getTransactionFrom().decreaseBalance(trans.getTransactionAmount());
        trans.getTransactionTo().increaseBalance(trans.getTransactionAmount());
    }

    public void writeTransaction(String filename){
        Writer writer=new CSVWriter();;
        String type =filename.split("\\.")[1];
        if(type.equals("csv")){
            writer =new CSVWriter();
        }

        try {
            writer.Write(transactions,filename);
        } catch (IOException e) {
            LOGGER.info("error writing to file");
            e.printStackTrace();
        }

    }

    //adds a new person to the list
    public void addPerson(Person newPerson){
        people.add(newPerson);
    }

    //adds a new transaction to the list
    private void addTransaction(Transaction newTransaction){
        transactions.add(newTransaction);
    }

    //performs all transactions in the list, then adds them to the completedTransactions list
    private void performAllTransactions(){
        transactions.forEach(trans -> PerformTransaction(trans));
    }

    //lists each person, and their current balance
    public void listAll(){
        people.forEach(person -> {
            System.out.println(person.getName());
            System.out.println("Balance: " + person.getBalance().printMoney(true));
        });
    }

    public void updateTransactionFromFile(String fileName) throws NumberFormatException, FileNotFoundException {
        Reader reader;
        String[] fileData = fileName.split("\\.");
        File file = new File(fileName);
        FileReader test = new FileReader(file);

        if (fileData[1].equalsIgnoreCase("csv")) reader = new CSVReader();
        else if (fileData[1].equalsIgnoreCase("xml"))
            reader = new XMLReader();
        else if (fileData[1].equalsIgnoreCase("json"))
            reader = new JSONReader();
        else throw new InvalidParameterException("File type must be .xml, .csv or .json");


        try {
            List<Transaction> transactions = reader.readFile(this,file);
            for(Transaction t:transactions){
                addTransaction(t);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        performAllTransactions();

    }

    //lists each transaction involving a given person
    public void listAccount(String name){

        List<Person> match = people.stream().filter(i->i.getName().equals(name)).collect(Collectors.toList());
        if(match.size()==1) {
            Person person = match.get(0);
            transactions.forEach(transaction -> {
                if (transaction.InvolvesPeron(person)) listTransaction(transaction);
            });
        }else{
            System.out.println("error");
        }
    }
    //lists details of the current transaction
    private void listTransaction(Transaction trans){
        System.out.println("Date: " + DATA_FORMAT.format(trans.getTransactionDate()) +
                            ", From: " + trans.getTransactionFrom().getName() +
                            ", To: " + trans.getTransactionTo().getName() +
                            ", Narrative: " + trans.getTransactionNarrative() +
                            ", Amount: " + trans.getTransactionAmount().printMoney(true));
    }


}
