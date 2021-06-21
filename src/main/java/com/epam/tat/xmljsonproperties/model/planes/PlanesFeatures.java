package com.epam.tat.xmljsonproperties.model.planes;

import java.util.EnumSet;

public enum PlanesFeatures {
    MAX_PASSENGER_CAPACITY, MODEL, MAX_SPEED, MAX_FLIGHT_DISTANCE, MILITARY_TYPE, TYPE;

    @Override
    public String toString() {
        String name = null;
        switch (this) {
            case MODEL:
                name = "model";
                break;
            case MAX_SPEED:
                name = "speed";
                break;
            case MAX_FLIGHT_DISTANCE:
                name = "distance";
                break;
            case MILITARY_TYPE:
                name = "militaryType";
                break;
            case MAX_PASSENGER_CAPACITY:
                name = "capacity";
                break;
            case TYPE:
                name = "type";
                break;
            default:
                name = "No such field";
        }
        return name;
    }
}
