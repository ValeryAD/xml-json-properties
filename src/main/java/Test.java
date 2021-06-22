import com.epam.tat.xmljsonproperties.datareading.property.PropertyDataProcessor;
import com.epam.tat.xmljsonproperties.model.AirCompany;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Test {
    public static void main(String[] args) throws IOException {
        final String path = "d:\\temp\\planes.properties";
        final String outPutPath = "d:\\temp\\planesOutput.properties";



        PropertyDataProcessor pdp = new PropertyDataProcessor(path);
        AirCompany ac = pdp.readDataFromSource();
        //ac.getPlanes().stream().forEach(p -> System.out.println(p));

        pdp.writeDataToSource(ac);
    }
}
