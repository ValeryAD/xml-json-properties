package com.epam.tat.xmljsonproperties.datareading.xml;

import com.epam.tat.xmljsonproperties.datareading.AbstractDataProcessor;
import com.epam.tat.xmljsonproperties.model.AirCompany;
import com.epam.tat.xmljsonproperties.model.planes.AbstractPlane;
import com.epam.tat.xmljsonproperties.model.planes.MilitaryPlane;
import com.epam.tat.xmljsonproperties.model.planes.PassengerPlane;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.tat.xmljsonproperties.constants.PlaneConstants.*;

public class XmlDataProcessor extends AbstractDataProcessor {

    public XmlDataProcessor(String sourceIdentifier) {
        super(sourceIdentifier);
    }

    @Override
    public AirCompany readDataFromSource() {
        List<AbstractPlane> planes = new ArrayList<>();
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(getSourceIdentifier());

            NodeList nodeListPlanes = document.getDocumentElement().getChildNodes();
            for (int i = 0; i < nodeListPlanes.getLength(); i++) {
                Node nodePlane = nodeListPlanes.item(i);
                if (nodePlane.getNodeType() != Node.TEXT_NODE) {
                    AbstractPlane plane = createPlane(nodePlane);
                    if (plane != null) {
                        planes.add(plane);
                    }
                }

            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new AirCompany(planes);
    }

    private AbstractPlane createPlane(Node nodePlane) {

        if (nodePlane.getNodeName().equals(MILITARY_PLANE_NODE_NAME)) {
            MilitaryPlane militaryPlane = new MilitaryPlane();
            militaryPlane.setModel(nodePlane.getAttributes().getNamedItem(FIELD_MODEL.toLowerCase()).getNodeValue());

            NodeList nodeFields = nodePlane.getChildNodes();
            for (int i = 0; i < nodeFields.getLength(); i++) {
                Node nodeField = nodeFields.item(i);

                if (nodeField.getNodeType() != Node.TEXT_NODE) {
                    switch (nodeField.getNodeName()) {
                        case FIELD_MILITARY_TYPE:
                            militaryPlane.setMilitaryType(nodeField.getChildNodes().item(0).getTextContent());
                            break;
                        case FIELD_MAX_FLIGHT_DISTANCE:
                            militaryPlane.setMaxFlightDistance(Integer.parseInt(nodeField.getChildNodes().item(0).getTextContent()));
                            break;
                        case FIELD_MAX_SPEED:
                            militaryPlane.setMaxSpeed(Integer.parseInt(nodeField.getChildNodes().item(0).getTextContent()));
                            break;
                    }
                }
            }
            return militaryPlane;
        }

        if (nodePlane.getNodeName().equals(PASSENGER_PLANE_NODE_NAME)) {
            PassengerPlane passengerPlane = new PassengerPlane();
            passengerPlane.setModel(nodePlane.getAttributes().getNamedItem(FIELD_MODEL.toLowerCase()).getNodeValue());
            NodeList nodeFields = nodePlane.getChildNodes();
            for (int i = 0; i < nodeFields.getLength(); i++) {
                Node nodeField = nodeFields.item(i);
                switch (nodeField.getNodeName()) {
                    case FIELD_MAX_PASSENGER_CAPACITY:
                        passengerPlane.setMaxPassengerCapacity(Integer.parseInt(nodeField.getChildNodes().item(0).getTextContent()));
                        break;
                    case FIELD_MAX_FLIGHT_DISTANCE:
                        passengerPlane.setMaxFlightDistance(Integer.parseInt(nodeField.getChildNodes().item(0).getTextContent()));
                        break;
                    case FIELD_MAX_SPEED:
                        passengerPlane.setMaxSpeed(Integer.parseInt(nodeField.getChildNodes().item(0).getTextContent()));
                        break;
                }
            }
            return passengerPlane;
        }
        return null;
    }

    @Override
    public void writeDataToSource(AirCompany aircompany) {
        throw new UnsupportedOperationException("You need to implement this method");
    }
}
