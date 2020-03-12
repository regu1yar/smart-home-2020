package ru.sbt.mipt.oop.serialization;

import com.google.gson.*;
import ru.sbt.mipt.oop.components.ActionableComponent;

import java.lang.reflect.Type;

public class ActionableComponentInterfaceAdaptor implements JsonSerializer<ActionableComponent>, JsonDeserializer<ActionableComponent> {
    private static final String CLASSNAME = "CLASSNAME";
    private static final String DATA = "DATA";

    public ActionableComponent deserialize(JsonElement jsonElement, Type type,
                                           JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonPrimitive prim = (JsonPrimitive) jsonObject.get(CLASSNAME);
        String className = prim.getAsString();
        Class clazz = getObjectClass(className);
        return jsonDeserializationContext.deserialize(jsonObject.get(DATA), clazz);
    }
    public JsonElement serialize(ActionableComponent jsonElement, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(CLASSNAME, jsonElement.getClass().getName());
        jsonObject.add(DATA, jsonSerializationContext.serialize(jsonElement));
        return jsonObject;
    }

    public Class getObjectClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new JsonParseException(e.getMessage());
        }
    }
}