
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Student
 */
  import java.util.Scanner;

public class Main {
    
  
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to QuickChat.");

        boolean running = true;

        while (running) {
            System.out.println("\nOption 1) Send Messages");
            System.out.println("Option 2) Show recently sent messages");
            System.out.println("Option 3) Quit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    handleSendingMessages(scanner);
                    break;
                case 2:
                    System.out.println("Coming Soon.");
                    break;
                case 3:
                    System.out.println("Exiting QuickChat.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
        scanner.close();
    }

    private static void handleSendingMessages(Scanner scanner) {
        System.out.print("How many messages do you wish to enter? ");
        int numMessages = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numMessages; i++) {
            System.out.println("\n--- Message " + (i + 1) + " ---");
            
            String recipient = "";
            Message tempMsg = new Message();
            boolean validRecipient = false;

            while (!validRecipient) {
                System.out.print("Enter recipient number (+international code): ");
                recipient = scanner.nextLine();
                tempMsg = new Message(recipient, "");
                String result = tempMsg.checkRecipientCell();
                System.out.println(result);
                if (result.contains("successfully")) validRecipient = true;
            }

            String text = "";
            boolean validLength = false;
            Message finalMsg = null;

            while (!validLength) {
                System.out.print("Enter message (max 250 chars): ");
                text = scanner.nextLine();
                finalMsg = new Message(recipient, text);
                String result = finalMsg.validateMessageLength();
                System.out.println(result);
                if (result.contains("ready")) validLength = true;
            }

            finalMsg.createMessageHash();
            System.out.println("Message ID generated: " + finalMsg.getMessageID());

            boolean actionTaken = false;
            while (!actionTaken) {
                System.out.println("\n1) Send Message\n2) Disregard Message\n3) Store Message");
                System.out.print("Choice: ");
                int action = scanner.nextInt();
                scanner.nextLine();

                String actionResult = finalMsg.SentMessage(action);
                System.out.println(actionResult);

                if (action == 1) {
                    System.out.println("\n" + finalMsg.printMessages());
                    actionTaken = true;
                } else if (action == 2) {
                    actionTaken = true;
                } else if (action == 3) {
                    finalMsg.storeMessage();
                    actionTaken = true;
                }
            }
        }
        System.out.println("\nTotal messages accumulated: " + Message.returnTotalMessages());
    }
}