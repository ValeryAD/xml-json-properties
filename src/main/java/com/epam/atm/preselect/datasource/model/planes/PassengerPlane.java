package com.epam.atm.preselect.datasource.model.planes;

import java.util.Objects;

public class PassengerPlane extends AbstractPlane {

    private int maxPassengerCapacity;

    public PassengerPlane(String model, int maxSpeed, int maxPassengerCapacity, int maxFlightDistance) {
        super(model, maxSpeed, maxFlightDistance);
        this.maxPassengerCapacity = maxPassengerCapacity;
    }

    public PassengerPlane() {
        super();
    }

    public int getMaxPassengerCapacity() {
        return maxPassengerCapacity;
    }

    public void setMaxPassengerCapacity(int maxPassengerCapacity) {
        this.maxPassengerCapacity = maxPassengerCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PassengerPlane that = (PassengerPlane) o;
        return maxPassengerCapacity == that.maxPassengerCapacity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), maxPassengerCapacity);
    }

    @Override
    public String toString() {
        return "PassengerPlane{" +
                "maxPassengerCapacity=" + maxPassengerCapacity +
                "} " + super.toString();
    }
}
