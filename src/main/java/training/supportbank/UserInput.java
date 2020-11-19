package training.supportbank;

import java.util.Scanner;

public class UserInput {
    public static String helpMessage = "help info";
    public static void run() {
        boolean keepRunning = true;
        while(keepRunning) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter command:");
            String command = scanner.next();
            String commandModifier;
            try {
                commandModifier = scanner.nextLine().substring(1);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Can call 'list all' or 'list [Person]'");
                break;
            }
            commandModifier = scanner.nextLine().substring(1);
            //System.out.println(command);
            //System.out.println(commandModifier);
            if (command.equals("list")) {

                if (commandModifier.equals("all")) {
                    //Bank list all method
                    System.out.println("Will list all");
                } else {
                    //Band list account transactions
                    System.out.println("Will list transactions for " + commandModifier);
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
