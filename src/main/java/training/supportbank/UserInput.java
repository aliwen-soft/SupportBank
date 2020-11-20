package training.supportbank;

import java.util.Scanner;

public class UserInput {
    public static String helpMessage = "help info";
    public static void run(Bank bank) {
        boolean keepRunning = true;
        while(keepRunning) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter command:");
            String command = scanner.nextLine();
            String[] commands = command.split(" ");
            String commandModifier;

                if (commands[0].equals("List")) {
                    try {
                        if (commands[1].equals("All")) {
                            System.out.println("listing all");
                            bank.ListAll();
                        } else {
                            String name="";
                            for(int i=1;i<commands.length;i++){
                                name=name+" "+commands[i];
                            }
                            name = name.trim();
                            bank.ListAccount(name);
                            System.out.println("listing transactions for" + name);
                        }
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Can call 'list all' or 'list [Person]'");
                    }
                }else if (commands[0].equals("Import")){
                    System.out.println("Importing file");
                    bank.updateTransactionFromFile(commands[2]);
                }else if (commands[0].equals("Export")){
                    System.out.println("Export file");
                    bank.WriteTransaction(commands[2]);
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
