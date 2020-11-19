package training.supportbank;

import java.util.Date;

public class Transaction {
    private Date transactionDate;
    private int transactionAmount;
    private String transactionFrom;
    private String transactionTo;
    private String transactionNarrative;

    public Transaction(Date date, int amount, String from, String to, String narrative){
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

    public String getTransactionFrom() {
        return transactionFrom;
    }

    public String getTransactionTo() {
        return transactionTo;
    }

    public String getTransactionNarrative() {
        return transactionNarrative;
    }
}
