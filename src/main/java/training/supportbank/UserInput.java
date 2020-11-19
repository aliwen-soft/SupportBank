package training.supportbank;

import java.util.Scanner;

public class UserInput {
    public static String helpMessage = "help info";
    public static void run(Bank bank) {
        boolean keepRunning = true;
        while(keepRunning) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter command:");
            String command = scanner.next();
            String commandModifier;

                //System.out.println(command);
                //System.out.println(commandModifier);
                if (command.equals("list")) {
                    try {
                        commandModifier = scanner.nextLine().substring(1);
                        if (commandModifier.equals("all")) {
                            //Bank list all method
                            bank.ListAll();
                            System.out.println("Will list all");
                        } else {
                            //Band list account transactions
                            bank.ListAccount(commandModifier);
                            System.out.println("Will list transactions for " + commandModifier);
                        }
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Can call 'list all' or 'list [Person]'");
                    }
                }else if(command.equals("help")){
                    System.out.println(helpMessage);
                } else if(command.equals("exit")){
                    keepRunning = false;
                } else {
                    System.out.println("Invalid Command");
                }

        }
    }
}
