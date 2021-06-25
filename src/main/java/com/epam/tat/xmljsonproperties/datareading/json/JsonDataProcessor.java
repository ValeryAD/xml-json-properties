package com.epam.tat.xmljsonproperties.datareading.json;

import com.epam.tat.xmljsonproperties.datareading.AbstractDataProcessor;
import com.epam.tat.xmljsonproperties.exceptions.UbableToWriteDataToSourceException;
import com.epam.tat.xmljsonproperties.model.AirCompany;
import com.epam.tat.xmljsonproperties.model.planes.AbstractPlane;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.Arrays;

import static com.epam.tat.xmljsonproperties.constants.ExceptionMessages.*;

public class JsonDataProcessor extends AbstractDataProcessor {

    private static final int BUFFER_SIZE = 128;

    public JsonDataProcessor(String sourceIdentifier) {
        super(sourceIdentifier);
    }

    @Override
    public AirCompany readDataFromSource() {
        AirCompany airCompany = new AirCompany();
        File file = new File(getSourceIdentifier());

        if (file.isDirectory() || file.length() == 0) {
            return airCompany;
        }

        try (FileReader fileReader = new FileReader(file)) {
            char[] buffer = new char[BUFFER_SIZE];
            int charAmount = 0;
            StringBuilder json = new StringBuilder();

            while ((charAmount = fileReader.read(buffer)) > 0) {
                if (charAmount < buffer.length) {
                    buffer = Arrays.copyOf(buffer, charAmount);
                }
                json.append(buffer);
            }

            Gson gson = new GsonBuilder().
                    registerTypeAdapter(AbstractPlane.class, new PlaneDeserializer()).
                    registerTypeAdapter(AirCompany.class, new AirCompanyDeserializer()).
                    create();
            airCompany = gson.fromJson(json.toString(), AirCompany.class);
            System.out.println(airCompany);

        } catch (FileNotFoundException e) {
            throw new UbableToWriteDataToSourceException(String.format(FILE_NOT_FOUND_MESSAGE, file.getName()), e);
        } catch (IOException e) {
            System.err.println(FILE_WRITE_EXCEPTION_MESSAGE);
        }
        return airCompany;
    }

    @Override
    public void writeDataToSource(AirCompany aircompany) {
        if (aircompany == null) {
            throw new UbableToWriteDataToSourceException(AIRCOMPANY_NOT_EXISTS_MESSAGE);
        }

        try (FileWriter fileWriter = new FileWriter(getSourceIdentifier())) {

            Gson gson = new GsonBuilder().
                    setPrettyPrinting().
                    registerTypeHierarchyAdapter(AbstractPlane.class, new PlaneSerializer()).
                    registerTypeAdapter(AirCompany.class, new AirCompanySerializer()).
                    create();
            String jsonString = gson.toJson(aircompany);
            fileWriter.write(jsonString);
            fileWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


