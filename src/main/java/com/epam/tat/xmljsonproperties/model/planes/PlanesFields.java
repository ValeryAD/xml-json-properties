package com.epam.tat.xmljsonproperties.model.planes;

import java.util.EnumSet;

public enum PlanesFields {
    MAX_PASSENGER_CAPACITY, MODEL, MAX_SPEED, MAX_FLIGHT_DISTANCE, MILITARY_TYPE;

    public static EnumSet<PlanesFields> getMilitaryPlaneFields() {
        return EnumSet.range(PlanesFields.MAX_PASSENGER_CAPACITY, PlanesFields.MAX_FLIGHT_DISTANCE);
    }

    public static EnumSet<PlanesFields> getPassengerPlaneFields() {
        return EnumSet.range(PlanesFields.MODEL, PlanesFields.MILITARY_TYPE);
    }


    @Override
    public String toString() {
        String name = null;
        switch (this) {
            case MODEL:
                name = "model";
                break;
            case MAX_SPEED:
                name = "maxSpeed";
                break;
            case MAX_FLIGHT_DISTANCE:
                name = "maxFlightDistance";
                break;
            case MILITARY_TYPE:
                name = "militaryType";
                break;
            case MAX_PASSENGER_CAPACITY:
                name = "maxPassengerCapacity";
                break;
            default:
                name = "No such field";
        }
        return name;
    }
}
