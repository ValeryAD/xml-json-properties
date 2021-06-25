package com.epam.tat.xmljsonproperties.datareading.property;

import com.epam.tat.xmljsonproperties.datareading.AbstractDataProcessor;
import com.epam.tat.xmljsonproperties.exceptions.DataSourceFileNotExistsException;
import com.epam.tat.xmljsonproperties.exceptions.UbableToWriteDataToSourceException;
import com.epam.tat.xmljsonproperties.model.AirCompany;
import com.epam.tat.xmljsonproperties.model.planes.AbstractPlane;
import com.epam.tat.xmljsonproperties.model.planes.MilitaryPlane;
import com.epam.tat.xmljsonproperties.model.planes.PassengerPlane;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.epam.tat.xmljsonproperties.constants.ExceptionMessages.*;
import static com.epam.tat.xmljsonproperties.constants.PlaneConstants.*;

public class PropertyDataProcessor extends AbstractDataProcessor {

    private static final String PLANE_FIELD_PATTERN = "%s.%s";
    private static final String PLANE_TYPE_REGEX = "plane\\d+\\.type";
    private static final String MILITARY_PLANES_COMMENT = "military planes";
    private static final String PASSENGER_PLANES_COMMENT = "passenger planes";
    private static final String PROPERTY_KEY_PATTERN = "plane%d.%s";

    private static int planeId = 0;

    public PropertyDataProcessor(String sourceIdentifier) {
        super(sourceIdentifier);
    }

    @Override
    public AirCompany readDataFromSource() {
        List<AbstractPlane> planes = new ArrayList<>();
        Properties properties = new Properties();

        try (FileReader fileReader = new FileReader(getSourceIdentifier())) {

            properties.load(fileReader);
            createPlanes(planes, properties);

        } catch (FileNotFoundException e) {
            throw new DataSourceFileNotExistsException(String.format(FILE_NOT_FOUND_MESSAGE, getSourceIdentifier(), e));
        } catch (IOException e) {
            throw new DataSourceFileNotExistsException(String.format(FILE_READ_EXCEPTION_MESSAGE, getSourceIdentifier(), e));
        }
        return new AirCompany(planes);
    }

    private void createPlanes(List<AbstractPlane> planes, Properties properties) {

        for (Object object : properties.keySet()) {
            String key = (String) object;

            if (key.matches(PLANE_TYPE_REGEX)) {
                String planeName = key.substring(0, key.indexOf('.'));
                String planeType = (String) properties.get(String.format(PLANE_FIELD_PATTERN, planeName, PROPERTY_TYPE.toLowerCase()));
                if (planeType.equals(MILITARY_PLANE_TYPE)) {
                    MilitaryPlane plane = new MilitaryPlane();
                    initMilitaryPlane(planeName, properties, plane);
                    planes.add(plane);
                }

                if (planeType.equals(PASSENGER_PLANE_TYPE)) {
                    PassengerPlane plane = new PassengerPlane();
                    initPassengerPlane(planeName, properties, plane);
                    planes.add(plane);
                }
            }
        }
    }

    private void initMilitaryPlane(String name, Properties properties, MilitaryPlane plane) {
        plane.setModel(properties.getProperty(String.format(PLANE_FIELD_PATTERN, name, FIELD_MODEL.toLowerCase())));
        plane.setMaxSpeed(Integer.parseInt(properties.getProperty(String.format(PLANE_FIELD_PATTERN, name, FIELD_MAX_SPEED.toLowerCase()))));
        plane.setMaxFlightDistance(Integer.parseInt(properties.getProperty(String.format(PLANE_FIELD_PATTERN, name, FIELD_MAX_FLIGHT_DISTANCE.toLowerCase()))));
        plane.setMilitaryType(properties.getProperty(String.format(PLANE_FIELD_PATTERN, name,
                FIELD_MILITARY_TYPE.replace(FIELD_MILITARY_TYPE.substring(0, 1), FIELD_MILITARY_TYPE.substring(0, 1).toLowerCase()))));
    }

    private void initPassengerPlane(String name, Properties properties, PassengerPlane plane) {
        plane.setModel(properties.getProperty(String.format(PLANE_FIELD_PATTERN, name, FIELD_MODEL.toLowerCase())));
        plane.setMaxSpeed(Integer.parseInt(properties.getProperty(String.format(PLANE_FIELD_PATTERN, name, FIELD_MAX_SPEED.toLowerCase()))));
        plane.setMaxFlightDistance(Integer.parseInt(properties.getProperty(String.format(PLANE_FIELD_PATTERN, name, FIELD_MAX_FLIGHT_DISTANCE.toLowerCase()))));
        plane.setMaxPassengerCapacity(Integer.parseInt(properties.getProperty(String.format(PLANE_FIELD_PATTERN, name, FIELD_MAX_PASSENGER_CAPACITY.toLowerCase()))));
    }

    @Override
    public void writeDataToSource(AirCompany aircompany) {
        Properties properties = new Properties();

        try (FileWriter fileWriter = new FileWriter(getSourceIdentifier())) {

            for (MilitaryPlane plane : aircompany.getMilitaryPlanes()) {
                addMilitaryPlaneToProperties(plane, properties);
            }
            properties.store(fileWriter, MILITARY_PLANES_COMMENT);
            properties.clear();
            fileWriter.write('\n');

            for (PassengerPlane plane : aircompany.getPassengerPlanes()) {
                addPassengerPlaneToProperties(plane, properties);
            }
            properties.store(fileWriter, PASSENGER_PLANES_COMMENT);
            properties.clear();
            fileWriter.write('\n');

        } catch (IOException e) {
            throw new UbableToWriteDataToSourceException(String.format(FILE_WRITE_TO_FILE_EXCEPTION_MESSAGE, getSourceIdentifier(), e));
        }

    }

    private void addMilitaryPlaneToProperties(MilitaryPlane plane, Properties properties) {
        int id = planeId++;
        properties.setProperty(String.format(PROPERTY_KEY_PATTERN,
                id,
                FIELD_MAX_FLIGHT_DISTANCE.toLowerCase()),
                String.valueOf(plane.getMaxFlightDistance()));

        properties.setProperty(String.format(PROPERTY_KEY_PATTERN,
                id,
                FIELD_MILITARY_TYPE.replace(FIELD_MILITARY_TYPE.substring(0, 1), FIELD_MILITARY_TYPE.substring(0, 1).toLowerCase())),
                plane.getMilitaryType());

        properties.setProperty(String.format(PROPERTY_KEY_PATTERN,
                id,
                FIELD_MODEL.toLowerCase()),
                plane.getModel());

        properties.setProperty(String.format(PROPERTY_KEY_PATTERN,
                id,
                FIELD_MAX_SPEED.toLowerCase()),
                String.valueOf(plane.getMaxSpeed()));

        properties.setProperty(String.format(PROPERTY_KEY_PATTERN,
                id,
                PROPERTY_TYPE.toLowerCase()),
                MILITARY_PLANE_TYPE);
    }

    private void addPassengerPlaneToProperties(PassengerPlane plane, Properties properties) {
        int id = planeId++;
        properties.setProperty(String.format(PROPERTY_KEY_PATTERN,
                id,
                FIELD_MAX_FLIGHT_DISTANCE.toLowerCase()),
                String.valueOf(plane.getMaxFlightDistance()));

        properties.setProperty(String.format(PROPERTY_KEY_PATTERN,
                id,
                FIELD_MAX_PASSENGER_CAPACITY.toLowerCase()),
                String.valueOf(plane.getMaxPassengerCapacity()));

        properties.setProperty(String.format(PROPERTY_KEY_PATTERN,
                id,
                FIELD_MODEL.toLowerCase()),
                plane.getModel());

        properties.setProperty(String.format(PROPERTY_KEY_PATTERN,
                id,
                FIELD_MAX_SPEED.toLowerCase()),
                String.valueOf(plane.getMaxSpeed()));

        properties.setProperty(String.format(PROPERTY_KEY_PATTERN,
                id,
                PROPERTY_TYPE.toLowerCase()),
                PASSENGER_PLANE_TYPE);
    }
}
