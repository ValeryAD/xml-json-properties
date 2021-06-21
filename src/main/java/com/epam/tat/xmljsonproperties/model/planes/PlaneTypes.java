package com.epam.tat.xmljsonproperties.model.planes;

public enum PlaneTypes {
    MILITARY_PLANE("military"), PASSENGER_PLANE("passenger");
    private String shortName;

    PlaneTypes(String shortName) {
        this.shortName = shortName;
    }

    public AbstractPlane createPlane() {
        AbstractPlane plane;

        switch (this) {
            case MILITARY_PLANE:
                plane = new MilitaryPlane();
                break;
            case PASSENGER_PLANE:
                plane = new PassengerPlane();
                break;
            default:
                plane = null;
        }

        return plane;
    }

    @Override
    public String toString() {
        return shortName;
    }
}
