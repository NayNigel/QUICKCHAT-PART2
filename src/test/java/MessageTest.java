/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    public void testMessageLengthSuccess() {
        Message msg = new Message(0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");

        assertEquals("Message ready to send.", msg.checkMessageLength());
    }

    @Test
    public void testMessageLengthFailure() {
        String longMessage = "a".repeat(260);

        Message msg = new Message(0, "+27718693002", longMessage);

        assertEquals("Message exceeds 250 characters by 10; please reduce the size.",
                msg.checkMessageLength());
    }

    @Test
    public void testRecipientCorrect() {
        Message msg = new Message(0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");

        assertEquals("Cell phone number successfully captured.",
                msg.checkRecipientCell());
    }

    @Test
    public void testRecipientIncorrect() {
        Message msg = new Message(0, "08575975889",
                "Hi Keegan, did you receive the payment?");

        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
                msg.checkRecipientCell());
    }

    @Test
    public void testMessageHashCreated() {
        Message msg = new Message("0012345678", 0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");

        assertEquals("00:0:HITONIGHT", msg.createMessageHash());
    }

    @Test
    public void testMessageIDCreated() {
        Message msg = new Message(0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");

        assertTrue(msg.checkMessageID());
        assertEquals(10, msg.getMessageID().length());
    }

    @Test
    public void testSendMessage() {
        Message msg = new Message(0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");

        assertEquals("Message successfully sent.", msg.sentMessage(1));
    }

    @Test
    public void testDisregardMessage() {
        Message msg = new Message(0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");

        assertEquals("Press 0 to delete the message.", msg.sentMessage(2));
    }

    @Test
    public void testStoreMessage() {
        Message msg = new Message(0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");

        assertEquals("Message successfully stored.", msg.sentMessage(3));
    }
}