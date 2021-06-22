package com.epam.tat.xmljsonproperties.datareading.property;

import com.epam.tat.xmljsonproperties.datareading.AbstractDataProcessor;
import com.epam.tat.xmljsonproperties.exceptions.DataSourceFileNotExistsException;
import com.epam.tat.xmljsonproperties.exceptions.UbableToWriteDataToSourceException;
import com.epam.tat.xmljsonproperties.model.AirCompany;
import com.epam.tat.xmljsonproperties.model.planes.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PropertyDataProcessor extends AbstractDataProcessor {

    private static final String FILE_NOT_FOUND_MESSAGE = "Can't find file %s";
    private static final String FILE_READ_EXCEPTION_MESSAGE = "Can't read file %s";
    private static final String FILE_WRITE_EXCEPTION_MESSAGE = "Can't write to file %s";
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
                String planeType = (String) properties.get(String.format(PLANE_FIELD_PATTERN, planeName, PlanesFeatures.TYPE));
                if (planeType.equals(PlaneTypes.MILITARY_PLANE.getShortName())) {
                    MilitaryPlane plane = new MilitaryPlane();
                    initMilitaryPlane(planeName, properties, plane);
                    planes.add(plane);
                }

                if (planeType.equals(PlaneTypes.PASSENGER_PLANE.getShortName())) {
                    PassengerPlane plane = new PassengerPlane();
                    initPassengerPlane(planeName, properties, plane);
                    planes.add(plane);
                }
            }
        }
    }

    private void initMilitaryPlane(String name, Properties properties, MilitaryPlane plane) {
        plane.setModel(properties.getProperty(String.format(PLANE_FIELD_PATTERN, name, PlanesFeatures.MODEL)));
        plane.setMaxSpeed(Integer.parseInt(properties.getProperty(String.format(PLANE_FIELD_PATTERN, name, PlanesFeatures.MAX_SPEED))));
        plane.setMaxFlightDistance(Integer.parseInt(properties.getProperty(String.format(PLANE_FIELD_PATTERN, name, PlanesFeatures.MAX_FLIGHT_DISTANCE))));
        plane.setMilitaryType(properties.getProperty(String.format(PLANE_FIELD_PATTERN, name, PlanesFeatures.MILITARY_TYPE)));
    }

    private void initPassengerPlane(String name, Properties properties, PassengerPlane plane) {
        plane.setModel(properties.getProperty(String.format(PLANE_FIELD_PATTERN, name, PlanesFeatures.MODEL)));
        plane.setMaxSpeed(Integer.parseInt(properties.getProperty(String.format(PLANE_FIELD_PATTERN, name, PlanesFeatures.MAX_SPEED))));
        plane.setMaxFlightDistance(Integer.parseInt(properties.getProperty(String.format(PLANE_FIELD_PATTERN, name, PlanesFeatures.MAX_FLIGHT_DISTANCE))));
        plane.setMaxPassengerCapacity(Integer.parseInt(properties.getProperty(String.format(PLANE_FIELD_PATTERN, name, PlanesFeatures.MAX_PASSENGER_CAPACITY))));
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
            throw new UbableToWriteDataToSourceException(String.format(FILE_WRITE_EXCEPTION_MESSAGE, getSourceIdentifier(), e));
        }

    }

    private void addMilitaryPlaneToProperties(MilitaryPlane plane, Properties properties) {
        int id = planeId++;
        properties.setProperty(String.format(PROPERTY_KEY_PATTERN,
                id,
                PlanesFeatures.MAX_FLIGHT_DISTANCE),
                String.valueOf(plane.getMaxFlightDistance()));

        properties.setProperty(String.format(PROPERTY_KEY_PATTERN,
                id,
                PlanesFeatures.MILITARY_TYPE),
                plane.getMilitaryType());

        properties.setProperty(String.format(PROPERTY_KEY_PATTERN,
                id,
                PlanesFeatures.MODEL),
                plane.getModel());

        properties.setProperty(String.format(PROPERTY_KEY_PATTERN,
                id,
                PlanesFeatures.MAX_SPEED),
                String.valueOf(plane.getMaxSpeed()));

        properties.setProperty(String.format(PROPERTY_KEY_PATTERN,
                id,
                PlanesFeatures.TYPE),
                PlaneTypes.MILITARY_PLANE.getShortName());
    }

    private void addPassengerPlaneToProperties(PassengerPlane plane, Properties properties) {
        int id = planeId++;
        properties.setProperty(String.format(PROPERTY_KEY_PATTERN,
                id,
                PlanesFeatures.MAX_FLIGHT_DISTANCE),
                String.valueOf(plane.getMaxFlightDistance()));

        properties.setProperty(String.format(PROPERTY_KEY_PATTERN,
                id,
                PlanesFeatures.MAX_PASSENGER_CAPACITY),
                String.valueOf(plane.getMaxPassengerCapacity()));

        properties.setProperty(String.format(PROPERTY_KEY_PATTERN,
                id,
                PlanesFeatures.MODEL),
                plane.getModel());

        properties.setProperty(String.format(PROPERTY_KEY_PATTERN,
                id,
                PlanesFeatures.MAX_SPEED),
                String.valueOf(plane.getMaxSpeed()));

        properties.setProperty(String.format(PROPERTY_KEY_PATTERN,
                id,
                PlanesFeatures.TYPE),
                PlaneTypes.PASSENGER_PLANE.getShortName());
    }
}
