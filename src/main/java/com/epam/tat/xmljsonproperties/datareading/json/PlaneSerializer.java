package com.epam.tat.xmljsonproperties.datareading.json;

import com.epam.tat.xmljsonproperties.model.planes.AbstractPlane;
import com.epam.tat.xmljsonproperties.model.planes.MilitaryPlane;
import com.epam.tat.xmljsonproperties.model.planes.PassengerPlane;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import static com.epam.tat.xmljsonproperties.constants.PlaneConstants.*;

public class PlaneSerializer implements JsonSerializer<AbstractPlane> {
    @Override
    public JsonElement serialize(AbstractPlane plane, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();

        if(plane instanceof MilitaryPlane){
            jsonObject.addProperty(PROPERTY_TYPE, MILITARY_PLANE_TYPE);
            jsonObject.addProperty(FIELD_MILITARY_TYPE, ((MilitaryPlane) plane).getMilitaryType());
            jsonObject.addProperty(FIELD_MAX_PASSENGER_CAPACITY, "null");
        }

        if(plane instanceof PassengerPlane){
            jsonObject.addProperty(PROPERTY_TYPE, PASSENGER_PLANE_TYPE);
            jsonObject.addProperty(FIELD_MAX_PASSENGER_CAPACITY, ((PassengerPlane) plane).getMaxPassengerCapacity());
            jsonObject.addProperty(FIELD_MILITARY_TYPE, "null");
        }

        jsonObject.addProperty(FIELD_MODEL, plane.getModel());
        jsonObject.addProperty(FIELD_MAX_SPEED, plane.getMaxSpeed());
        jsonObject.addProperty(FIELD_MAX_FLIGHT_DISTANCE, plane.getMaxFlightDistance());

        return jsonObject;
    }
}
