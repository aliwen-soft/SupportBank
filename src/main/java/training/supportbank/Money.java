package training.supportbank;

public class Money {
    int amount;

    public Money(int amount){
        this.amount=amount;
    }
    public Money(double amount){
        this.amount= (int) Math.round(amount*100);
    }
    public Money(String samount){
        double amount = Double.parseDouble(samount);
        this.amount= (int) Math.round(amount*100);
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
