/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nigel
 */
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Message {

    private String messageID;
    private int messageNumber;
    private String recipient;
    private String message;

    private static int totalMessagesSent = 0;

    public Message(int messageNumber, String recipient, String message) {
        this.messageID = generateMessageID();
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.message = message;
    }

    public Message(String messageID, int messageNumber, String recipient, String message) {
        this.messageID = messageID;
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.message = message;
    }

    private String generateMessageID() {
        Random random = new Random();
        long number = 1000000000L + (long) (random.nextDouble() * 9000000000L);
        return String.valueOf(number);
    }

    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    public String checkRecipientCell() {
        if (recipient.startsWith("+27") && recipient.length() <= 12) {
            return "Cell phone number successfully captured.";
        }

        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }

    public String checkMessageLength() {
        if (message.length() <= 250) {
            return "Message ready to send.";
        }

        int extraCharacters = message.length() - 250;
        return "Message exceeds 250 characters by " + extraCharacters + "; please reduce the size.";
    }

    public String createMessageHash() {
        String[] words = message.trim().split("\\s+");

        String firstWord = words[0].replaceAll("[^A-Za-z0-9]", "");
        String lastWord = words[words.length - 1].replaceAll("[^A-Za-z0-9]", "");

        return (messageID.substring(0, 2) + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();
    }

    public String sentMessage(int option) {
        switch (option) {
            case 1:
                totalMessagesSent++;
                return "Message successfully sent.";

            case 2:
                return "Press 0 to delete the message.";

            case 3:
                storeMessage();
                return "Message successfully stored.";

            default:
                return "Invalid option.";
        }
    }

    public String printMessages() {
        return "Message ID: " + messageID
                + "\nMessage Hash: " + createMessageHash()
                + "\nRecipient: " + recipient
                + "\nMessage: " + message;
    }

    public int returnTotalMessages() {
        return totalMessagesSent;
    }

    public void storeMessage() {
        try {
            FileWriter writer = new FileWriter("storedMessages.json", true);

            writer.write("{\n");
            writer.write("\"Message ID\": \"" + messageID + "\",\n");
            writer.write("\"Message Hash\": \"" + createMessageHash() + "\",\n");
            writer.write("\"Recipient\": \"" + recipient + "\",\n");
            writer.write("\"Message\": \"" + message + "\"\n");
            writer.write("}\n");

            writer.close();

        } catch (IOException e) {
            System.out.println("Error storing message.");
        }
    }

    public String getMessageID() {
        return messageID;
    }
}