package training.supportbank;

import java.util.Date;

public class Transaction {
    private Date transactionDate;

    private Money transactionAmount;
    private Person transactionFrom;
    private Person transactionTo;
    private String transactionNarrative;

    public Transaction(Date date, Money amount, Person from, Person to, String narrative){
        transactionDate = date;
        transactionAmount = amount;
        transactionFrom = from;
        transactionTo = to;
        transactionNarrative =narrative;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public Money getTransactionAmount() {
        return transactionAmount;
    }

    public Person getTransactionFrom() {
        return transactionFrom;
    }

    public Person getTransactionTo() {
        return transactionTo;
    }

    public String getTransactionNarrative() {
        return transactionNarrative;
    }

    //checks if the transaction involves the given person
    public boolean InvolvesPeron(Person person){
        boolean output = false;
        if (transactionFrom.equals(person)) output = true;
        if (transactionTo.equals(person)) output = true;
        return output;
    }
}
