package com.epam.tat.xmljsonproperties.datareading.json;

import com.epam.tat.xmljsonproperties.model.AirCompany;
import com.epam.tat.xmljsonproperties.model.planes.AbstractPlane;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class AirCompanySerializer implements JsonSerializer<AirCompany> {
    @Override
    public JsonElement serialize(AirCompany airCompany, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonArray jsonArray = new JsonArray();
        for (AbstractPlane plane : airCompany.getPlanes()) {
            jsonArray.add(jsonSerializationContext.serialize(plane));
        }

        return jsonArray;
    }
}
