package com.epam.atm.preselect.datasource.model.planes;

import java.util.Objects;

public class MilitaryPlane extends AbstractPlane {

    private String militaryType;

    public MilitaryPlane(String model, int maxSpeed, int maxFlightDistance, String militaryType) {
        super(model, maxSpeed, maxFlightDistance);
        this.militaryType = militaryType;
    }

    public MilitaryPlane() {
        super();
    }

    public String getMilitaryType() {
        return militaryType;
    }

    public void setMilitaryType(String militaryType) {
        this.militaryType = militaryType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        MilitaryPlane that = (MilitaryPlane) o;
        return Objects.equals(militaryType, that.militaryType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), militaryType);
    }

    @Override
    public String toString() {
        return "MilitaryPlane{" +
                "militaryType='" + militaryType + '\'' +
                "} " + super.toString();
    }

}
