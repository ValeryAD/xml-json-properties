package com.epam.tat.xmljsonproperties.model.planes;

public enum PlaneTypes {
    MILITARY_PLANE("military", "MilitaryPlane"), PASSENGER_PLANE("passenger", "PassengerPlane");
    private String shortName;
    private String name;

    PlaneTypes(String shortName, String name) {
        this.shortName = shortName;
    }

    public String getShortName(){
        return shortName;
    }

    public String getName(){
        return name;
    }

}
