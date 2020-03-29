package ru.sbt.mipt.oop.applications.homebuilder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sbt.mipt.oop.smarthome.components.SmartHome;
import ru.sbt.mipt.oop.smarthome.serialization.SmartHomeSerializer;

public class HomeBuilder {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(HomeBuilderConfiguration.class);
        SmartHome smartHome = context.getBean(SmartHome.class);
        SmartHomeSerializer serializer = context.getBean(SmartHomeSerializer.class);
        serializer.serialize(smartHome);
    }

}
