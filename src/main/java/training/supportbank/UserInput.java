package training.supportbank;

import java.util.Scanner;

public class UserInput {
    public static final String NEW_LINE = System.getProperty("line.separator");
    public static String helpMessage =
            "----- Support Bank v 1.0 -----"
            + NEW_LINE
            + "Available commands:"
            + NEW_LINE
            + "List All - Prints a list of all people and value of their account"
            + NEW_LINE
            + "List [name] - Prints all transactions associated with [name]"
            + NEW_LINE
            + "import [file] - imports transactions from [file]. File must be .JSON .CSV or .XML"
            + NEW_LINE
            + "export [file] - exports transactions to [file]. File must be .CSV"
            + NEW_LINE
            + "exit - exits program";

    public static void run(Bank bank) {
        boolean keepRunning = true;
        while(keepRunning) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter command:");
            String command = scanner.nextLine();
            String[] commands = command.split(" ");

                if (commands[0].equalsIgnoreCase("List")) {
                    if(bank.getTransactions().size() == 0){
                        System.out.println("No transactions imported");
                    } else {
                        try {
                            if (commands[1].equals("All")) {
                                System.out.println("listing all");
                                bank.listAll();
                            } else {
                                String name = "";
                                for (int i = 1; i < commands.length; i++) {
                                    name = name + " " + commands[i];
                                }
                                name = name.trim();
                                bank.listAccount(name);
                                System.out.println("listing transactions for" + name);
                            }
                        } catch (StringIndexOutOfBoundsException e) {
                            System.out.println("Can call 'list all' or 'list [Person]'");
                        }
                    }
                }else if (commands[0].equalsIgnoreCase("Import")){
                    System.out.println("Importing file");
                    bank.updateTransactionFromFile(commands[2]);
                }else if (commands[0].equalsIgnoreCase("Export")){
                    System.out.println("Export file");
                    bank.writeTransaction(commands[2]);
                }else if(command.equalsIgnoreCase("help")){
                    System.out.println(helpMessage);
                } else if(command.equalsIgnoreCase("exit")){
                    keepRunning = false;
                } else {
                    System.out.println("Invalid Command");
                }

        }
    }
}
