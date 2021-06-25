package com.epam.tat.xmljsonproperties.datareading.xml;

import com.epam.tat.xmljsonproperties.datareading.AbstractDataProcessor;
import com.epam.tat.xmljsonproperties.exceptions.DataSourceFileNotExistsException;
import com.epam.tat.xmljsonproperties.exceptions.UbableToParseDataSourceException;
import com.epam.tat.xmljsonproperties.exceptions.UbableToWriteDataToSourceException;
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
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.tat.xmljsonproperties.constants.ExceptionMessages.*;
import static com.epam.tat.xmljsonproperties.constants.PlaneConstants.*;

public class XmlDataProcessor extends AbstractDataProcessor {

    public static final String ROOT_ELEMENT_NAME = "planes";

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
            throw new UbableToParseDataSourceException(String.format(PARSE_DATA_EXCEPTION_MESSAGE, getSourceIdentifier()), e);
        } catch (SAXException e) {
            System.err.println(TRANSFORMING_DATA_EXCEPTION_MESSAGE);
        } catch (IOException e) {
            System.err.println(String.format(FILE_READ_EXCEPTION_MESSAGE, getSourceIdentifier()));
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

        if (aircompany == null) {
            throw new UbableToWriteDataToSourceException(AIRCOMPANY_NOT_EXISTS_MESSAGE);
        }

        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element root = document.createElement(ROOT_ELEMENT_NAME);
            document.appendChild(root);
            addPlane(document, aircompany);
            writeDocument(document);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    private void addPlane(Document document, AirCompany aircompany) {
        List<AbstractPlane> planes = aircompany.getPlanes();
        Element root = document.getDocumentElement();

        Element planeNode = null;

        for (AbstractPlane plane : planes) {

            if (plane instanceof MilitaryPlane) {
                planeNode = fillMilitaryPlaneNode(document, plane);
            }

            if (plane instanceof PassengerPlane) {
                planeNode = fillPassengerPlaneNode(document, plane);
            }
            root.appendChild(planeNode);
        }
    }

    private Element fillPassengerPlaneNode(Document document, AbstractPlane plane) {
        PassengerPlane passengerPlane = (PassengerPlane) plane;

        Element planeNode = document.createElement(PASSENGER_PLANE_TAG);
        planeNode.setAttribute(FIELD_MODEL.toLowerCase(), plane.getModel());

        Element passengerCapacity = document.createElement(FIELD_MAX_PASSENGER_CAPACITY);
        passengerCapacity.setTextContent(String.valueOf(passengerPlane.getMaxPassengerCapacity()));

        Element speed = document.createElement(FIELD_MAX_SPEED);
        speed.setTextContent(String.valueOf(plane.getMaxSpeed()));

        Element distance = document.createElement(FIELD_MAX_FLIGHT_DISTANCE);
        distance.setTextContent(String.valueOf(plane.getMaxFlightDistance()));

        planeNode.appendChild(passengerCapacity);
        planeNode.appendChild(speed);
        planeNode.appendChild(distance);
        return planeNode;
    }

    private Element fillMilitaryPlaneNode(Document document, AbstractPlane plane) {
        MilitaryPlane militaryPlane = (MilitaryPlane) plane;

        Element planeNode = document.createElement(MILITARY_PLANE_TAG);
        planeNode.setAttribute(FIELD_MODEL.toLowerCase(), plane.getModel());

        Element militaryType = document.createElement(FIELD_MILITARY_TYPE);
        militaryType.setTextContent(militaryPlane.getMilitaryType());

        Element speed = document.createElement(FIELD_MAX_SPEED);
        speed.setTextContent(String.valueOf(plane.getMaxSpeed()));

        Element distance = document.createElement(FIELD_MAX_FLIGHT_DISTANCE);
        distance.setTextContent(String.valueOf(plane.getMaxFlightDistance()));

        planeNode.appendChild(militaryType);
        planeNode.appendChild(speed);
        planeNode.appendChild(distance);
        return planeNode;
    }

    private void writeDocument(Document document) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(getSourceIdentifier())) {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(document);
            StreamResult streamResult = new StreamResult(fileOutputStream);
            transformer.transform(source, streamResult);

        } catch (TransformerConfigurationException e) {
            System.err.println(TRANSFORMING_DATA_EXCEPTION_MESSAGE);
        } catch (FileNotFoundException e) {
            throw new DataSourceFileNotExistsException(String.format(FILE_NOT_FOUND_MESSAGE, getSourceIdentifier()), e);
        } catch (IOException e) {
            throw new UbableToWriteDataToSourceException(String.format(FILE_WRITE_TO_FILE_EXCEPTION_MESSAGE, getSourceIdentifier()), e);
        } catch (TransformerException e) {
            System.err.println(TRANSFORMING_DATA_EXCEPTION_MESSAGE);
        }
    }
}
