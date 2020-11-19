package training.supportbank;

import java.util.Date;

public class Transaction {
    private Date transactionDate;
    private int transactionAmount;
    private Person transactionFrom;
    private Person transactionTo;
    private String transactionNarrative;

    public Transaction(Date date, int amount, Person from, Person to, String narrative){
        transactionDate = date;
        transactionAmount = amount;
        transactionFrom = from;
        transactionTo = to;
        transactionNarrative =narrative;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public int getTransactionAmount() {
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
}
