package com.epam.atm.preselect.datasource.model;

import com.epam.atm.preselect.datasource.model.planes.AbstractPlane;
import com.epam.atm.preselect.datasource.model.planes.MilitaryPlane;
import com.epam.atm.preselect.datasource.model.planes.PassengerPlane;

import java.util.ArrayList;
import java.util.List;

public class AirCompany {

    private List<AbstractPlane> planes;

    public AirCompany() {
        planes = new ArrayList<>();
    }

    public AirCompany(List<AbstractPlane> planes) {
        this.planes = planes;
    }

    public List<PassengerPlane> getPassengerPlanes() {
        List<PassengerPlane> passengerPlanes = new ArrayList<>();
        for (AbstractPlane plane : planes) {
            if (plane instanceof PassengerPlane) {
                passengerPlanes.add((PassengerPlane) plane);
            }
        }
        return passengerPlanes;
    }

    public List<MilitaryPlane> getMilitaryPlanes() {
        List<MilitaryPlane> passengerPlanes = new ArrayList<>();
        for (AbstractPlane plane : planes) {
            if (plane instanceof MilitaryPlane) {
                passengerPlanes.add((MilitaryPlane) plane);
            }
        }
        return passengerPlanes;
    }

    public int getTotalNumberOfPlanes() {
        return planes.size();
    }

    public List<AbstractPlane> getPlanes() {
        return planes;
    }

}
