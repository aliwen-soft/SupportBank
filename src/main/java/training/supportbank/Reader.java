package training.supportbank;

import java.io.IOException;
import java.util.List;

public abstract class Reader {

    public List<Person> people;
    public abstract  List<Transaction> readFile(String filename) throws IOException;

    public Person checkPerson(String name){

        Person newperson = new Person(name);

        for(Person p:people){
            if(p.equals(newperson)) {
                //     System.out.println("added a same person: " + name);
                return p;
            }
        }
        //  System.out.println("added a new person: " + name);
        people.add(newperson);

        return  newperson;
    }
}
