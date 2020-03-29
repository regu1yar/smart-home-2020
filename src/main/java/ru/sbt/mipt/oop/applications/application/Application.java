package ru.sbt.mipt.oop.applications.application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sbt.mipt.oop.smarthome.events.EventProcessor;


public class Application {

    public static void main(String... args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        EventProcessor eventProcessor = context.getBean(EventProcessor.class);
        eventProcessor.startProcessing();
    }

}
