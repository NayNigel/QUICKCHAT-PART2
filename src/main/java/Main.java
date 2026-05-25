/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nigel
 */


// Main class - entry point for QuickChat application
// Updated for Part 2 - Added messaging features
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter first name: ");
        String firstName = input.nextLine();

        System.out.print("Enter last name: ");
        String lastName = input.nextLine();

        System.out.print("Create username: ");
        String username = input.nextLine();

        System.out.print("Create password: ");
        String password = input.nextLine();

        System.out.print("Enter cell phone number: ");
        String cellPhone = input.nextLine();

        Login login = new Login(firstName, lastName, username, password, cellPhone);
        System.out.println(login.registerUser());

        System.out.print("\nEnter username to login: ");
        String loginUsername = input.nextLine();

        System.out.print("Enter password to login: ");
        String loginPassword = input.nextLine();

        boolean loggedIn = login.loginUser(loginUsername, loginPassword);
        System.out.println(login.returnLoginStatus(loggedIn));

        if (!loggedIn) {
            System.out.println("You must login first.");
            return;
        }

        System.out.println("\nWelcome to QuickChat.");

        System.out.print("How many messages would you like to send? ");
        int maxMessages = input.nextInt();
        input.nextLine();

        int choice;

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Choose an option: ");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    for (int i = 0; i < maxMessages; i++) {
                        int messageNumber = i;

                        System.out.println("\nMessage " + (i + 1) + " of " + maxMessages);

                        System.out.print("Enter recipient number: ");
                        String recipient = input.nextLine();

                        System.out.print("Enter your message: ");
                        String messageText = input.nextLine();

                        Message msg = new Message(messageNumber, recipient, messageText);

                        String cellCheck = msg.checkRecipientCell();
                        String lengthCheck = msg.checkMessageLength();

                        System.out.println(cellCheck);
                        System.out.println(lengthCheck);

                        if (!cellCheck.equals("Cell phone number successfully captured.")
                                || !lengthCheck.equals("Message ready to send.")) {
                            continue;
                        }

                        System.out.println("\nChoose what to do:");
                        System.out.println("1) Send Message");
                        System.out.println("2) Disregard Message");
                        System.out.println("3) Store Message");
                        System.out.print("Option: ");
                        int option = input.nextInt();
                        input.nextLine();

                        String result = msg.sentMessage(option);
                        System.out.println(result);

                        if (option == 1) {
                            System.out.println("\n--- Message Details ---");
                            System.out.println(msg.printMessages());
                        }

                        System.out.println("Total messages sent: " + msg.returnTotalMessages());
                    }
                    break;

                case 2:
                    System.out.println("Coming Soon.");
                    break;

                case 3:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option.");
            }

        } while (choice != 3);

        input.close();
    }
}