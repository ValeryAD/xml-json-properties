package com.epam.tat.xmljsonproperties.datareading.json;

import com.epam.tat.xmljsonproperties.model.AirCompany;
import com.epam.tat.xmljsonproperties.model.planes.AbstractPlane;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

public class AirCompanyDeserializer implements JsonDeserializer<AirCompany> {
    @Override
    public AirCompany deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        List<AbstractPlane> planes = new LinkedList<>();

        if (jsonElement.isJsonArray()) {
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            for (JsonElement element : jsonArray) {
                planes.add(context.deserialize(element.getAsJsonObject(), AbstractPlane.class));
            }
        }

        return new AirCompany(planes);
    }
}
