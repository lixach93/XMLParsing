package by.training.lihodievski.xmlparsing.parser;

import by.training.lihodievski.xmlparsing.bean.*;
import by.training.lihodievski.xmlparsing.util.DateConvert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;


public class DOMFlowerParser extends AbstractFlowerParser {

    private static final Logger LOGGER = LogManager.getLogger (DOMFlowerParser.class);
    private DocumentBuilder documentBuilder;
    public DOMFlowerParser() throws ParserConfigurationException {
        super();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        documentBuilder = factory.newDocumentBuilder();
    }
    @Override
    public void buildSetFlowers(InputStream dataStream) {
        try (InputStream stream = dataStream){
            Document document = documentBuilder.parse(stream);
            Element root = document.getDocumentElement();
            NodeList monocotsFlowers = root.getElementsByTagName("monocots-flower");
            NodeList dicotyledonsFlowers = root.getElementsByTagName("dicotyledons-flower");
            fillFlowers (monocotsFlowers,"monocots");
            fillFlowers (dicotyledonsFlowers,"dicotyledons");
        } catch (SAXException | IOException e) {
           LOGGER.error ("DOM parsing error!",e);
        }
    }
    private Flower buildFlower(Element flowerElement, String typeFlower) {
        Flower flower;
        if(typeFlower.equals ("monocots")){
            flower = new MonocotsFlower ();
        }else {
            flower = new DicotyledonsFlower ();
        }
        if(!flowerElement.getAttribute ("soil").isEmpty ()) {
            flower.setSoil (Soil.fromValue (flowerElement.getAttribute ("soil")));
        }else {
            flower.setSoil (Soil.DIRT);
        }
        LocalDate date = DateConvert.convertDate (getElementTextContent (flowerElement,"firstMention"));
        flower.setFirstMention (date);
        flower.setId (flowerElement.getAttribute ("id"));
        flower.setName(getElementTextContent(flowerElement, "name"));
        flower.setOrigin (getElementTextContent(flowerElement, "origin"));
        flower.setVisual (fillVisual (flowerElement));
        flower.setGrowingTip (fillGrowingTip (flowerElement));
        flower.setMultiplying (Multiplying.fromValue (getElementTextContent(flowerElement, "multiplying")));
        return flower;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }

    private void fillFlowers(NodeList flowersList, String typeFlower){
        for (int i = 0; i < flowersList.getLength(); i++) {
            Element flowerElement = (Element) flowersList.item(i);
            Flower flower = buildFlower(flowerElement,typeFlower);
            flowers.add(flower);
        }
    }

    private Visual fillVisual(Element flowerElement){
        Visual visual = new Visual ();
        Element visualElement = (Element) flowerElement.getElementsByTagName("visual").item(0);
        visual.setLeafColor (getElementTextContent(visualElement, "leaf-color"));
        visual.setStemColor (getElementTextContent(visualElement, "stem-color"));
        visual.setLength (Integer.parseInt (getElementTextContent(visualElement, "length")));
        return visual;
    }
    private GrowingTip fillGrowingTip(Element flowerElement){
        GrowingTip growing = new GrowingTip();
        Element growingElement = (Element)flowerElement.getElementsByTagName ("growing-tips").item (0);
        growing.setTemperature (Integer.parseInt (getElementTextContent (growingElement,"temperature")));
        if(getElementTextContent (growingElement,"lighting").equals ("true")){
            growing.setLighting (true);
        }else{
            growing.setLighting (false);
        }
        growing.setWatering (Integer.parseInt (getElementTextContent (growingElement,"watering")));
        return growing;
    }
}
