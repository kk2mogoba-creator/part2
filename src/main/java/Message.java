
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Student
 */

import java.util.Random;
import java.util.Scanner;

public class Message{
    

    private static int totalMessagesSent = 0;

    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;

    public Message(String recipient, String messageText) {
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageID = generateMessageID();
        totalMessagesSent++;
        this.messageNumber = totalMessagesSent;
    }

   
    public Message() {}

    private String generateMessageID(){
        Random rand = new Random();
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            id.append(rand.nextInt(10));
        }
        return id.toString();
    }

  
    public boolean checkMessageID() {
        return this.messageID != null && this.messageID.length() <= 10;
    }

  
    public String checkRecipientCell(){
        if (this.recipient != null && this.recipient.matches("^\\+[0-9]{1,10}$")){
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }


    public String createMessageHash() {
        if (messageText == null || messageText.trim().isEmpty()) {
            return "";
        }
        
        String idPrefix = messageID.substring(0, 2);
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        this.messageHash = (idPrefix + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();
        return this.messageHash;
    }

    
    public String SentMessage(int choice) {
        switch (choice) {
            case 1: return "Message successfully sent.";
            case 2: return "Press 0 to delete the message.";
            case 3: return "Message successfully stored.";
            default: return "Invalid option.";
        }
    }


    public String printMessages() {
        return "Message ID: " + messageID + "\n" +
               "Message Hash: " + messageHash + "\n" +
               "Recipient: " + recipient + "\n" +
               "Message: " + messageText;
    }

  
    public static int returnTotalMessages() {
        return totalMessagesSent;
    }


    public void storeMessage() {
      
        System.out.println("{\"id\": \"" + messageID + "\", \"recipient\": \"" + recipient + "\", \"message\": \"" + messageText + "\"}");
    }


    public String validateMessageLength(){
        if (this.messageText.length() > 250) {
            int excess = this.messageText.length() - 250;
            return "Message exceeds 250 characters by " + excess + "; please reduce the size.";
        }
        return "Message ready to send.";
    }

 
    public void setMessageID(String messageID){ 
        this.messageID = messageID; 
    }
    public void setMessageNumber(int messageNumber){
        this.messageNumber = messageNumber; 
    }
    public String getMessageID(){ 
        return messageID; 
    }
}
    
    

