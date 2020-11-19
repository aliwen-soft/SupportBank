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
    public float getBalance(){
        return balance.getAmount();
    }

    //edits balance by a given amount
    public void EditBalance(int amount){
        balance.add(amount);
    }

    public boolean equals(Object o){
        if(! (o instanceof Person)) return false;

        Person p = (Person) o;
        return name == null && p.name == null || name.equals(p.name);
    }
}
