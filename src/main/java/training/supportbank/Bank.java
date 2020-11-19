package training.supportbank;
import java.util.ArrayList;

public class Bank {

    private ArrayList<Person> people = new ArrayList<Person>();
    private ArrayList<Transaction> transactions = new ArrayList<Transaction>();

    //edits the balance of the sender and receiver of a given transaction
    private void PerformTransaction(Transaction trans){
        trans.getTransactionFrom().decreaseBalance(trans.getTransactionAmount());
        trans.getTransactionTo().increaseBalance(trans.getTransactionAmount());
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
        transactions.forEach(trans -> PerformTransaction(trans));
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
            if(transaction.InvolvesPeron(person)) ListTransaction(transaction);
        });
    }

    //lists details of the current transaction
    private void ListTransaction(Transaction trans){
        System.out.println("Date: " + trans.getTransactionDate() +
                            ", From: " + trans.getTransactionFrom().getName() +
                            ", To: " + trans.getTransactionTo().getName() +
                            ", Narrative: " + trans.getTransactionNarrative() +
                            ", Amount: " + trans.getTransactionAmount());
    }
}
