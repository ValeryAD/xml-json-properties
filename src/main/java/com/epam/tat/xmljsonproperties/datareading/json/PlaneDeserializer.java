package com.epam.tat.xmljsonproperties.datareading.json;

import com.epam.tat.xmljsonproperties.model.planes.AbstractPlane;
import com.epam.tat.xmljsonproperties.model.planes.MilitaryPlane;
import com.epam.tat.xmljsonproperties.model.planes.PassengerPlane;
import com.google.gson.*;

import java.lang.reflect.Type;

import static com.epam.tat.xmljsonproperties.constants.PlaneConstants.*;


public class PlaneDeserializer implements JsonDeserializer<AbstractPlane> {
    @Override
    public AbstractPlane deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        AbstractPlane plane = null;

        if (jsonElement.isJsonObject()) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            if (jsonObject.get(PROPERTY_TYPE).getAsString().equals(MILITARY_PLANE_TYPE)) {
                MilitaryPlane militaryPlane = new MilitaryPlane();
                militaryPlane.setMilitaryType(jsonObject.get(FIELD_MILITARY_TYPE).getAsString());
                plane = militaryPlane;
            }

            if (jsonObject.get(PROPERTY_TYPE).getAsString().equals(PASSENGER_PLANE_TYPE)) {
                PassengerPlane passengerPlane = new PassengerPlane();
                passengerPlane.setMaxPassengerCapacity(jsonObject.get(FIELD_MAX_PASSENGER_CAPACITY).getAsInt());
                plane = passengerPlane;
            }

            if (plane != null) {
                plane.setMaxSpeed(jsonObject.get(FIELD_MAX_SPEED).getAsInt());
                plane.setMaxFlightDistance(jsonObject.get(FIELD_MAX_FLIGHT_DISTANCE).getAsInt());
                plane.setModel(jsonObject.get(FIELD_MODEL).getAsString());
            }
        }

        return plane;
    }
}
