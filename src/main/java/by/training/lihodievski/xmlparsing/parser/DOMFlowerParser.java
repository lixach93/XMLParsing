package by.training.lihodievski.xmlparsing.parser;

import by.training.lihodievski.xmlparsing.bean.*;
import by.training.lihodievski.xmlparsing.exception.ParserException;
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
    public DOMFlowerParser() throws ParserException {
        super();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOGGER.error ("DOM Constructor exception",e);
            throw  new ParserException (e);
        }
    }
    @Override
    public void buildSetFlowers(InputStream dataStream) {
        try (InputStream stream = dataStream){
            Document document = documentBuilder.parse(stream);
            Element root = document.getDocumentElement();
            NodeList monocotsFlowers = root.getElementsByTagName(FlowerEnum.MONOCOTS_FLOWER.getField ());
            NodeList dicotyledonsFlowers = root.getElementsByTagName(FlowerEnum.DICOTYLEDONS_FLOWER.getField ());
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
        if(!flowerElement.getAttribute (FlowerEnum.SOIL.getField ()).isEmpty ()) {
            flower.setSoil (Soil.fromValue (flowerElement.getAttribute (FlowerEnum.SOIL.getField ())));
        }else {
            flower.setSoil (Soil.DIRT);
        }
        LocalDate date = DateConvert.convertDate (getElementTextContent (flowerElement,FlowerEnum.DATE_LANDING.getField ()));
        flower.setDateLanding (date);
        flower.setId (flowerElement.getAttribute (FlowerEnum.ID.getField ()));
        flower.setName(getElementTextContent(flowerElement, FlowerEnum.NAME.getField ()));
        flower.setOrigin (getElementTextContent(flowerElement, FlowerEnum.ORIGIN.getField ()));
        flower.setVisual (fillVisual (flowerElement));
        flower.setGrowingTip (fillGrowingTip (flowerElement));
        flower.setMultiplying (Multiplying.fromValue (getElementTextContent(flowerElement, FlowerEnum.MULTIPLYING.getField ())));
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
        Element visualElement = (Element) flowerElement.getElementsByTagName(FlowerEnum.VISUAL.getField ()).item(0);
        visual.setLeafColor (getElementTextContent(visualElement, FlowerEnum.LEAF_COLOR.getField ()));
        visual.setStemColor (getElementTextContent(visualElement, FlowerEnum.STEM_COLOR.getField ()));
        visual.setLength (Integer.parseInt (getElementTextContent(visualElement, FlowerEnum.LENGTH.getField ())));
        return visual;
    }
    private GrowingTip fillGrowingTip(Element flowerElement){
        GrowingTip growing = new GrowingTip();
        Element growingElement = (Element)flowerElement.getElementsByTagName (FlowerEnum.GROWING_TIPS.getField ()).item (0);
        growing.setTemperature (Integer.parseInt (getElementTextContent (growingElement,FlowerEnum.TEMPERATURE.getField ())));
        if(getElementTextContent (growingElement,FlowerEnum.LIGHTING.getField ()).equals ("true")){
            growing.setLighting (true);
        }else{
            growing.setLighting (false);
        }
        growing.setWatering (Integer.parseInt (getElementTextContent (growingElement,FlowerEnum.WATERING.getField ())));
        return growing;
    }
}
