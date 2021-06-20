package com.epam.tat.xmljsonproperties.datareading.property;

import com.epam.tat.xmljsonproperties.datareading.AbstractDataProcessor;
import com.epam.tat.xmljsonproperties.model.AirCompany;
import com.epam.tat.xmljsonproperties.model.planes.AbstractPlane;
import com.epam.tat.xmljsonproperties.model.planes.MilitaryPlane;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PropertyDataProcessor extends AbstractDataProcessor {

    private static final String MILITARY_PLANE_HEADING = "#military planes";
    private static final String PASSENGER_PLANE_HEADING = "#passenger planes";

    public PropertyDataProcessor(String sourceIdentifier) {
        super(sourceIdentifier);
    }

    @Override
    public AirCompany readDataFromSource() {
        List<AbstractPlane> planes = new ArrayList<>();
        Properties properties = new Properties();

        try (FileReader fileReader = new FileReader(getSourceIdentifier());
             BufferedReader reader = new BufferedReader(fileReader)) {
            String line;

            createMilitaryPlanesFromProperties(reader);


            /*do {
                line = reader.readLine();
                if (line.isEmpty()) {
                    continue;
                }
                if (line.matches(COMMENT_REGEX)) {
                    break;
                }
            } while (true);

            while (!(line = reader.readLine()).matches(COMMENT_REGEX)) {

            }*/
            properties.load(reader);

            properties.forEach((k, v) -> System.out.println(k + " : " + v));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return new AirCompany(planes);
    }

    private List<MilitaryPlane> createMilitaryPlanesFromProperties(BufferedReader reader) throws IOException {
        StringBuilder militaryPlaneLines = new StringBuilder();
        StringBuilder passengerPlaneLines = new StringBuilder();
        String line = null;

        while ((line = reader.readLine()) != null) {
            String s = line;
            if (line.contains(MILITARY_PLANE_HEADING)) {
                while ((line = reader.readLine()) != null) {
                    if (line.contains(PASSENGER_PLANE_HEADING)) {
                        break;
                    }
                    militaryPlaneLines.append(line);
                    militaryPlaneLines.append('\n');
                }
            }
            if (line.contains(PASSENGER_PLANE_HEADING)) {
                while ((line = reader.readLine()) != null) {
                    if (line.contains(PASSENGER_PLANE_HEADING)) {
                        break;
                    }
                    passengerPlaneLines.append(line);
                    passengerPlaneLines.append('\n');

                }
            }
        }

        System.out.println(militaryPlaneLines);
        System.out.println(passengerPlaneLines);


        /*Properties properties = new Properties();

        do{
            line = reader.readLine();
            if(line.isEmpty()){
                continue;
            }
            if(line == null){
                return new ArrayList<MilitaryPlane>();
            }
            if(line.contains(MILITARY_PLANE_HEADING)){
                while((line = reader.readLine()) != null){

                }
            }
        }while(line != null);*/

        return new ArrayList<MilitaryPlane>();
    }

    @Override
    public void writeDataToSource(AirCompany aircompany) {
        throw new UnsupportedOperationException("You need to implement this method");
    }
}
