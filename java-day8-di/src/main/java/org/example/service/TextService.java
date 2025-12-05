package org.example.service;

public class TextService implements MessageService{
    @Override
    public void sendMessage(String from, String to, String message) {
        System.out.println("Sending SMS From: " + from + " To: " + to + " Message: " + message);
    }
}
