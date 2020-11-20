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

                if (command.equals("list")) {
                    try {
                        commandModifier = scanner.nextLine().substring(1);
                        if (commandModifier.equals("all")) {
                            //Bank list all method
                            bank.ListAll();
                            System.out.println("listing all");
                        } else {
                            //Band list account transactions
                            bank.ListAccount(commandModifier);
                            System.out.println("listing transactions for " + commandModifier);
                        }
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Can call 'list all' or 'list [Person]'");
                    }
                }else if (command.equals("Import")){
                    System.out.println("import");
                    String[] filename = scanner.nextLine().split(" ");
                    bank.updateTransactionFromFile(filename[2]);
                }else if (command.equals("Export")){
                    System.out.println("export");
                    String[] filename = scanner.nextLine().split(" ");
                    bank.WriteTransaction(filename[2]);

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
