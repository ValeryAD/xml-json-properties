import com.epam.tat.xmljsonproperties.datareading.property.PropertyDataProcessor;

public class Test {
    public static void main(String[] args) {
        PropertyDataProcessor pdp = new PropertyDataProcessor("d:\\temp\\planes.properties");
        pdp.readDataFromSource();
    }
}
