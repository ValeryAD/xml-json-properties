package com.epam.tat.xmljsonproperties.datareading.xml;

import com.epam.tat.xmljsonproperties.constants.PlaneConstants;
import com.epam.tat.xmljsonproperties.datareading.AbstractDataProcessor;
import com.epam.tat.xmljsonproperties.model.AirCompany;
import com.epam.tat.xmljsonproperties.model.planes.AbstractPlane;
import com.epam.tat.xmljsonproperties.model.planes.MilitaryPlane;
import com.epam.tat.xmljsonproperties.model.planes.PassengerPlane;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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

        if (nodePlane.getNodeName().equals(MILITARY_PLANE_TAG)) {
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

        if (nodePlane.getNodeName().equals(PASSENGER_PLANE_TAG)) {
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
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(getSourceIdentifier());
            addPlane(document, aircompany);
            writeDocument(document);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    private void writeDocument(Document document)  {
        try(FileOutputStream fileOutputStream = new FileOutputStream("d:\\temp\\other.xml")) {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult streamResult = new StreamResult(fileOutputStream);
            transformer.transform(source,streamResult);

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private void addPlane(Document document, AirCompany aircompany) {
        Element root = document.getDocumentElement();

        MilitaryPlane plane = aircompany.getMilitaryPlanes().get(0);

        Element newPlane = newPlane = document.createElement(MILITARY_PLANE_TAG);
        newPlane.setAttribute(FIELD_MODEL, plane.getModel());

        Element militaryType = document.createElement(FIELD_MILITARY_TYPE);
        militaryType.setTextContent(plane.getMilitaryType());

        Element speed = document.createElement(FIELD_MAX_SPEED);
        speed.setTextContent(String.valueOf(plane.getMaxSpeed()));

        Element distance = document.createElement(FIELD_MAX_FLIGHT_DISTANCE);
        distance.setTextContent(String.valueOf(plane.getMaxFlightDistance()));

        newPlane.appendChild(militaryType);
        newPlane.appendChild(speed);
        newPlane.appendChild(distance);

        root.appendChild(newPlane);
        /*for(AbstractPlane plane : aircompany.getPlanes()){

        }*/
    }
}
