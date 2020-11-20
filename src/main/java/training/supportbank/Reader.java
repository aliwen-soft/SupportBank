package training.supportbank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Date.*;
import java.util.List;
import java.lang.Object;


public abstract class Reader {

    private static final Logger LOGGER = LogManager.getLogger();

    public List<Person> people;

    public abstract Date convertDate(String date) throws ParseException;

    public abstract List<Transaction> readFile(String filename) throws IOException;

    public void addTransaction(List<Transaction> transactions, String sdate, String sto, String sfrom, String snarrative, String smoney) {
        try {
            Date date = convertDate(sdate);
            /**if (sdate.contains("/")) {
                date = new SimpleDateFormat("dd/MM/yyyy").parse(sdate);}
            else if (sdate.contains("-")) {
                    date = new SimpleDateFormat("yyyy-MM-dd").parse(sdate);
            } else {
                date = convertExcelTime(sdate);
            }**/
            Person to = checkPerson(sto);
            Person from = checkPerson(sfrom);
            String narrative = snarrative;
            Money amount = new Money(smoney);

            Transaction transaction = new Transaction(date, amount, from, to, narrative);
            transactions.add(transaction);
        } catch (ParseException e) {
            LOGGER.info("Invalid date: " + sdate);
        } catch (NumberFormatException e) {
            LOGGER.info("Non-parsable string given for amount: '" + smoney + "'" + "or Invlaid date" + sdate);
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


}
