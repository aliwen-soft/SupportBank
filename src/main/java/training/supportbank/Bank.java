package training.supportbank;
import java.util.ArrayList;

public class Bank {

    private ArrayList<Person> people = new ArrayList<Person>();
    private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    private ArrayList<Transaction> completedTransactions = new ArrayList<Transaction>();

    //edits the balance of the sender and receiver of a given transaction
    private void PerformTransaction(Transaction trans){
        trans.getTransactionFrom().EditBalance(-trans.getTransactionAmount());
        trans.getTransactionTo().EditBalance(trans.getTransactionAmount());
    }

    //adds a new person to the list
    public void AddPerson(Person newPerson){
        people.add(newPerson);
    }

    //adds a new transaction to the list
    public void AddTransaction(Transaction newTransaction){
        transactions.add(newTransaction);
    }

    //performs all transactions in the list, then adds them to the completedTransactions list
    public void PerformAllTransactions(){
        while(transactions.size() > 0){
            PerformTransaction(transactions.get(0));
            completedTransactions.add(transactions.get(0));
            transactions.remove(0);
        }
    }

    //lists each person, and their current balance
    public void ListAll(){
        people.forEach(person -> {
            System.out.println(person.getName());
            System.out.println("Balance: " + person.getBalance());
        });
    }

    //lists each transaction involving a given person
    public void ListAccount(Person person){
        transactions.forEach(transaction -> {
            if(QueryTransaction(transaction, person)) ListTransaction(transaction);
        });

        completedTransactions.forEach(transaction -> {
            if(QueryTransaction(transaction, person)) ListTransaction(transaction);
        });
    }

    //returns true if the person is part of the transaction supplied
    private boolean QueryTransaction(Transaction transaction, Person person){
        return false;
    }

    private void ListTransaction(Transaction transaction){
        System.out.println("Date: " + transaction.getTransactionDate() +
                            ", From: " + transaction.getTransactionFrom().getName() +
                            ", To: " + transaction.getTransactionTo().getName() +
                            ", Narrative: " + transaction.getTransactionNarrative() +
                            ", Amount: " + transaction.getTransactionAmount());
    }
}
