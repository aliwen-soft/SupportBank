package training.supportbank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Date.*;
import java.util.List;
import java.lang.Object;

public abstract class Reader {

    private static final Logger LOGGER = LogManager.getLogger();

    public List<Person> people;

    public abstract List<Transaction> readFile(String filename) throws IOException;

    public void addTransaction(List<Transaction> transactions, String sdate, String sto, String sfrom, String snarrative, String smoney) {
        try {
            Date date;
            if (sdate.contains("/")) {
                date = new SimpleDateFormat("dd/MM/yyyy").parse(sdate);
            } else {
                date = convertTime(sdate);
            }

            Person to = checkPerson(sto);
            Person from = checkPerson(sfrom);
            String narrative = snarrative;
            Money amount = new Money(smoney);

            Transaction transaction = new Transaction(date, amount, from, to, narrative);
            transactions.add(transaction);
        } catch (ParseException e) {
            LOGGER.info("Invalid date: " + sdate);
        } catch (NumberFormatException e) {
            LOGGER.info("Non-parsable string given for amount: '" + smoney + "'");
        }

    }

    public Person checkPerson(String name) {

        Person newperson = new Person(name);

        for (Person p : people) {
            if (p.equals(newperson)) {
                //     System.out.println("added a same person: " + name);
                return p;
            }
        }
        //  System.out.println("added a new person: " + name);
        people.add(newperson);

        return newperson;
    }

    public Date convertTime(String excelDate) {
        double time = Double.parseDouble(excelDate);
        int days = (int) time;  //number of days
        int seconds = (int) ((time - days) * 86400);  //number of seconds in .6 days

        //create calendar set to 01-Jan-1900
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 1900);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        //Add days and seconds to get required date/time
        cal.add(Calendar.DATE, days - 1);
        cal.add(Calendar.SECOND, seconds);

        return cal.getTime();
    }
}
