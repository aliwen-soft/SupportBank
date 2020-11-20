package training.supportbank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public abstract class Reader {

    private static final Logger LOGGER = LogManager.getLogger();

    public abstract Date convertDate(String date) throws ParseException;

    public abstract List<Transaction> readFile(Bank bank, File file) throws IOException, FileNotFoundException;

    public void addTransaction(Bank bank, String dateString, String toString, String fromString, String narrative, String moneyString) {
        List<Transaction> transactions = bank.getTransactions();
        List<Person> people = bank.getPeople();
        try {
            Date date = convertDate(dateString);
            Person to = checkPerson(toString,people);
            Person from = checkPerson(fromString,people);
            narrative = narrative;
            Money amount = new Money(moneyString);

            Transaction transaction = new Transaction(date, amount, from, to, narrative);
            transactions.add(transaction);
        } catch (ParseException e) {
            LOGGER.info("Invalid date: " + dateString);
        } catch (NumberFormatException e) {
            LOGGER.info("Non-parsable string given for amount: '" + moneyString + "'" + "or Invalid date" + dateString);
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
