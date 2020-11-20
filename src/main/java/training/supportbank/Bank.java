package training.supportbank;
import com.sun.media.sound.InvalidFormatException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Bank {
    private static final Logger LOGGER = LogManager.getLogger();

    private static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private ArrayList<Person> people = new ArrayList<Person>();
    private ArrayList<Transaction> transactions = new ArrayList<Transaction>();

    //edits the balance of the sender and receiver of a given transaction
    private void PerformTransaction(Transaction trans){
        trans.getTransactionFrom().decreaseBalance(trans.getTransactionAmount());
        trans.getTransactionTo().increaseBalance(trans.getTransactionAmount());
    }

    public void WriteTransaction(String filename){
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
    public void AddPerson(Person newPerson){
        people.add(newPerson);
    }

    //adds a new transaction to the list
    private void AddTransaction(Transaction newTransaction){
        transactions.add(newTransaction);
    }

    //performs all transactions in the list, then adds them to the completedTransactions list
    private void PerformAllTransactions(){
        transactions.forEach(trans -> PerformTransaction(trans));
    }

    //lists each person, and their current balance
    public void ListAll(){
        people.forEach(person -> {
            System.out.println(person.getName());
            System.out.println("Balance: " + person.getBalance().printMoney());
        });
    }

    public synchronized void updateTransactionFromFile(String file) throws NumberFormatException{


        Reader reader;
        String[] fileData = file.split("\\.");

        if (fileData[1].equals("csv")) reader = new CSVReader(people);
        else if (fileData[1].equals("xml"))
            reader = new XMLReader(people);
        else if (fileData[1].equals("json"))
            reader = new JSONReader(people);
        else throw new InvalidParameterException("File type must be .XML, .CSV or .JSON");


        try {
            List<Transaction> transactions = reader.readFile(file);
            for(Transaction t:transactions){
                AddTransaction(t);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        PerformAllTransactions();

    }

    //lists each transaction involving a given person
    public void ListAccount(String name){

        List<Person> match = people.stream().filter(i->i.getName().equals(name)).collect(Collectors.toList());
        if(match.size()==1) {
            Person person = match.get(0);
            transactions.forEach(transaction -> {
                if (transaction.InvolvesPeron(person)) ListTransaction(transaction);
            });
        }else{
            System.out.println("error");
        }
    }
    //lists details of the current transaction
    private void ListTransaction(Transaction trans){
        System.out.println("Date: " + dateFormat.format(trans.getTransactionDate()) +
                            ", From: " + trans.getTransactionFrom().getName() +
                            ", To: " + trans.getTransactionTo().getName() +
                            ", Narrative: " + trans.getTransactionNarrative() +
                            ", Amount: " + trans.getTransactionAmount().printMoney());
    }


}
