package training.supportbank;

public class Person {

    private String name;
    private Money balance;

    //Constructor for setting up a person
    public Person(String name){
        this.name = name;
        balance.setAmount(0);
    }

    //returns the current balance in the account
    public float getBalance(){
        return balance.getAmount();
    }

    //edits balance by a given amount
    public void EditBalance(int amount){
        balance.add(amount);
    }
}
