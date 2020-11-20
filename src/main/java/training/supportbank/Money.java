package training.supportbank;

import jdk.jfr.internal.LogLevel;
import jdk.jfr.internal.LogTag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Money {
    private static final Logger LOGGER = LogManager.getLogger();
    int amount;

    public Money(int amount){
        this.amount=amount;
    }
    public Money(double amount){
        this.amount= (int) Math.round(amount*100);
    }
    public Money(String samount) throws NumberFormatException{
                double amount = Double.parseDouble(samount);
                this.amount = (int) Math.round(amount * 100);


    }

    public String printMoney(){
        double dbl = amount/100;
        String printamount= Double.toString(dbl);
        return  "£ "+printamount;
    }
    public String printMoney(boolean sign){
        double dbl = amount/100;
        String printamount= Double.toString(dbl);
        if (sign) {
            return "£ " + printamount;
        }else{
            return printamount;
        }
    }

    public int getAmount(){
        return amount;
    }

    public void setAmount(int amount){
        this.amount= amount;
    }
    public void setAmount(double amount){
        this.amount= (int) Math.round(amount*100);
    }

    public int add(int amount){
        this.amount= this.amount+amount;
        return this.amount;
    }
    public int add(Money money){
        this.amount= this.amount+money.amount;
        return this.amount;
    }

    public int sub(int amount){
        this.amount= this.amount-amount;
        return this.amount;
    }
    public int sub(Money money){
        this.amount= this.amount-money.amount;
        return this.amount;
    }



}
