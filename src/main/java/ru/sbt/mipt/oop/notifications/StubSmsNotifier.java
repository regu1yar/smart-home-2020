package ru.sbt.mipt.oop.notifications;

public class StubSmsNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("Sending sms");
    }
}
