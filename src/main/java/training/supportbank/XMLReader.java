package training.supportbank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XMLReader extends Reader {

    private static final Logger LOGGER = LogManager.getLogger();

    public XMLReader(List<Person> people) {
        this.people = people;
    }

    public Date convertExcelTime(String excelDate) throws NumberFormatException  {
        double time = Double.parseDouble(excelDate);
        LocalDate localdate =LocalDate.of( 1899 , Month.DECEMBER , 30 ).plusDays((long)time);
        Date date = Date.from(localdate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        return date;
    }

    @Override
    public Date convertDate(String date) throws ParseException {
        Date dateOut = convertExcelTime(date);
        return  dateOut;
    }

    public List<Transaction> readFile(String filename) throws IOException {
        List<Transaction> transactions = new ArrayList<Transaction>();
        try {
            File xmlFile = new File(filename);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            Element docEle = doc.getDocumentElement();
            NodeList nl = docEle.getChildNodes();

            int length = nl.getLength();
            for (int i = 0; i < length; i++) {
                if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element el = (Element) nl.item(i);
                    if (el.getNodeName().contains("SupportTransaction")) {
                        String date = el.getAttribute("Date");
                        String description = el.getElementsByTagName("Description").item(0).getTextContent();
                        String value = el.getElementsByTagName("Value").item(0).getTextContent();
                        Element parties = (Element) el.getElementsByTagName("Parties").item(0);
                        String to= parties.getElementsByTagName("To").item(0).getTextContent();
                        String from= parties.getElementsByTagName("From").item(0).getTextContent();

                        addTransaction(transactions,date,to,from,description,value);
                    }
                }
            }

        } catch (ParserConfigurationException | SAXException e) {
            LOGGER.info("failed to parse file: " + filename);
            e.printStackTrace();
        }
        for(Transaction t:transactions){
          //  System.out.println(t.getTransactionNarrative());
        }
        return transactions;
    }


}
