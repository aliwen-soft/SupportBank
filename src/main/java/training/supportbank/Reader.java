package training.supportbank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public abstract class Reader {

    private static final Logger LOGGER = LogManager.getLogger();

    public abstract Date convertDate(String date) throws ParseException;

    public abstract List<Transaction> readFile(Bank bank, String filename) throws IOException;

    public void addTransaction(Bank bank, String sdate, String sto, String sfrom, String snarrative, String smoney) {
        List<Transaction> transactions = bank.getTransactions();
        List<Person> people = bank.getPeople();
        try {
            Date date = convertDate(sdate);
            Person to = checkPerson(sto,people);
            Person from = checkPerson(sfrom,people);
            String narrative = snarrative;
            Money amount = new Money(smoney);

            Transaction transaction = new Transaction(date, amount, from, to, narrative);
            transactions.add(transaction);
        } catch (ParseException e) {
            LOGGER.info("Invalid date: " + sdate);
        } catch (NumberFormatException e) {
            LOGGER.info("Non-parsable string given for amount: '" + smoney + "'" + "or Invalid date" + sdate);
        }

    }

    public Person checkPerson(String name,List<Person> people) {

        Person newPerson = new Person(name);

        for (Person p : people) {
            if (p.equals(newPerson)) {
                return p;
            }
        }
        people.add(newPerson);

        return newPerson;
    }


}
