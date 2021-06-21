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

    public static final String FILE_NOT_FOUND_MESSAGE = "Can't find file %s";
    public static final String FILE_READ_EXCEPTION_MESSAGE = "Can't read file %s";
    public static final String FILE_WRITE_EXCEPTION_MESSAGE = "Can't write to file %s";
    public static final String PLANE_FIELD_PATTERN = "%s.%s";
    private static final String MILITARY_PLANE_TYPE = "military";
    private static final String PASSENGER_PLANE_TYPE = "passenger";
    private static final String PLANE_TYPE_REGEX = "plane\\d+\\.type";
    private static final String MILITARY_PLANES_COMMENT = "#military planes";
    private static final String PASSENGER_PLANES_COMMENT = "#passenger planes";

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
            String propertyName = (String) object;

            if (propertyName.matches(PLANE_TYPE_REGEX)) {
                String planeName = propertyName.substring(0, propertyName.indexOf('.'));
                String planeType = (String) properties.get(String.format(PLANE_FIELD_PATTERN, planeName, PlanesFeatures.TYPE));
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

        try (FileWriter fileWriter = new FileWriter(getSourceIdentifier(), true)) {

            fileWriter.write(MILITARY_PLANES_COMMENT + '\n');
            for(MilitaryPlane plane : aircompany.getMilitaryPlanes()){
                addMilitaryPlaneToProperties(plane, properties);
                properties.store(fileWriter, "");
                fileWriter.write('\n');
            }

            fileWriter.write(PASSENGER_PLANES_COMMENT + '\n');
            for(PassengerPlane plane : aircompany.getPassengerPlanes()){
                addPassengerPlaneToProperties(plane, properties);
                properties.store(fileWriter, "");
                fileWriter.write('\n');
            }


        } catch (IOException e) {
            throw new UbableToWriteDataToSourceException(String.format(FILE_WRITE_EXCEPTION_MESSAGE, getSourceIdentifier(), e));
        }

    }

    private void addMilitaryPlaneToProperties(MilitaryPlane plane, Properties properties) {
        int id = planeId++;
        properties.setProperty(String.format("plane%d.%s",
                id,
                PlanesFeatures.MAX_FLIGHT_DISTANCE),
                String.valueOf(plane.getMaxFlightDistance()));

        properties.setProperty(String.format("plane%d.%s",
                id,
                PlanesFeatures.MILITARY_TYPE),
                plane.getMilitaryType());

        properties.setProperty(String.format("plane%d.%s",
                id,
                PlanesFeatures.MODEL),
                plane.getModel());

        properties.setProperty(String.format("plane%d.%s",
                id,
                PlanesFeatures.MAX_SPEED),
                String.valueOf(plane.getMaxSpeed()));

        properties.setProperty(String.format("plane%d.%s",
                id,
                PlanesFeatures.TYPE),
                PlaneTypes.MILITARY_PLANE.toString());
    }

    private void addPassengerPlaneToProperties(PassengerPlane plane, Properties properties) {
        int id = planeId++;
        properties.setProperty(String.format("plane%d.%s",
                id,
                PlanesFeatures.MAX_FLIGHT_DISTANCE),
                String.valueOf(plane.getMaxFlightDistance()));

        properties.setProperty(String.format("plane%d.%s",
                id,
                PlanesFeatures.MAX_PASSENGER_CAPACITY),
                String.valueOf(plane.getMaxPassengerCapacity()));

        properties.setProperty(String.format("plane%d.%s",
                id,
                PlanesFeatures.MODEL),
                plane.getModel());

        properties.setProperty(String.format("plane%d.%s",
                id,
                PlanesFeatures.MAX_SPEED),
                String.valueOf(plane.getMaxSpeed()));

        properties.setProperty(String.format("plane%d.%s",
                id,
                PlanesFeatures.TYPE),
                PlaneTypes.MILITARY_PLANE.toString());
    }
}
