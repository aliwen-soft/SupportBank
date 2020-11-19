package training.supportbank;

public class Person {

    private String name;
    private Money balance;

    //Constructor for setting up a person
    public Person(String name){
        this.name = name;
        balance = new Money(0);
    }

    //returns the name of the person
    public String getName(){
        return name;
    }

    //returns the current balance in the account
    public Money getBalance(){
        return balance;
    }

    //edits balance by a given amount
    public void increaseBalance(Money amount){
        balance.add(amount);
    }

    //edits balance by a given amount
    public void decreaseBalance(Money amount){
        balance.sub(amount);
    }

    public boolean equals(Object o){
        if(! (o instanceof Person)) return false;

        Person p = (Person) o;
        return name == null && p.name == null || name.equals(p.name);
    }
}
