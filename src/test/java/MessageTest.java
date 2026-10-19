/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Student
 */
public class MessageTest {
    
 public static void main(String[] args) {
        System.out.println("--- Running Custom Unit Tests ---\n");
        
        testMessageLength();
        testRecipientNumberFormatting();
        testCreateMessageHash();
        testSentMessageOptions();
    }

    public static void testMessageLength() {
        Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
         if (msg.checkMessageID()) {
            System.out.println("PASS: Message length validation (Success Case)");
        } else {
            System.out.println("FAIL: Message length validation (Success Case)");
        }

        // Create a string over 250 chars
        StringBuilder longMsg = new StringBuilder();
        for (int i = 0; i < 255; i++) longMsg.append("a");
        
        Message failMsg = new Message("+27718693002", longMsg.toString());
        if (!failMsg.checkMessageID()) {
            System.out.println("PASS: Message length validation (Failure Case)");
        } else {
            System.out.println("FAIL: Message length validation (Failure Case)");
        }
        System.out.println();
    }

    public static void testRecipientNumberFormatting() {
        Message validMsg = new Message("+27718693002", "Test");
        if(validMsg.checkRecipientCell()){
            System.out.println("PASS: Recipient formatting (Success Case)");
        } else {
            System.out.println("FAIL: Recipient formatting (Success Case)");
        }

        Message invalidMsg = new Message("08575975889", "Test");
        if(invalidMsg.checkRecipientCell()){
            System.out.println("PASS: Recipient formatting (Failure Case)");
        } else {
            System.out.println("FAIL: Recipient formatting (Failure Case)");
        }
        System.out.println();
    }

    public static void testCreateMessageHash() {
        Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
        msg.setMessageID("0012345678");
        msg.setMessageNumber(1); 
        
        String expectedHash = "00:1:HITONIGHT";
        String actualHash = msg.createMessageHash();

        if (expectedHash.equals(actualHash)) {
            System.out.println("PASS: Message Hash creation");
        } else {
            System.out.println("FAIL: Message Hash creation. Expected " + expectedHash + " but got " + actualHash);
        }
        System.out.println();
    }

    public static void testSentMessageOptions() {
        Message msg = new Message("+27718693002", "Test");
        
        if (msg.SentMessage(1).equals("Message successfully sent.")) {
            System.out.println("PASS: Send Message option");
        }
        
        if (msg.SentMessage(2).equals("Press 0 to delete the message.")) {
            System.out.println("PASS: Discard Message option");
        }
        
        if (msg.SentMessage(3).equals("Message successfully stored.")) {
            System.out.println("PASS: Store Message option");
        }
        System.out.println();
    }
    }