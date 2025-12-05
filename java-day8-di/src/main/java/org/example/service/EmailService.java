package org.example.service;

public class EmailService implements MessageService{
    @Override
    public void sendMessage(String from, String to, String message) {
        System.out.println("Sending email From: " + from + " To: " + to + " Message: " + message);
    }
}
