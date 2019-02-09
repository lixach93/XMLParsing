package by.training.lihodievski.xmlparsing.parser;

import by.training.lihodievski.xmlparsing.bean.*;
import by.training.lihodievski.xmlparsing.util.DateConvert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;

public class StAXFlowerParser extends AbstractFlowerParser {

    private static final Logger LOGGER = LogManager.getLogger (StAXFlowerParser.class);
    private XMLInputFactory inputFactory;
    public StAXFlowerParser() {
        super();
        inputFactory = XMLInputFactory.newInstance();
    }

    @Override
    public void buildSetFlowers(InputStream dataStream) {
        String name;
        try (InputStream stream = dataStream){
            XMLStreamReader reader = inputFactory.createXMLStreamReader (stream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name.equals (FlowerEnum.MONOCOTS_FLOWER.getField ())){
                        Flower monocotsFlower = buildFlowers (reader, new MonocotsFlower ());
                        flowers.add (monocotsFlower);
                    }
                    if (name.equals (FlowerEnum.DICOTYLEDONS_FLOWER.getField ())){
                        Flower dicotyledonsFlower = buildFlowers (reader, new DicotyledonsFlower ());
                        flowers.add (dicotyledonsFlower);
                    }
                }
            }
        } catch (XMLStreamException |IOException e) {
            LOGGER.error ("StAX parsing error! ",e);
        }
    }
    private Flower buildFlowers(XMLStreamReader reader,Flower flower) throws XMLStreamException {
        flower.setId (reader.getAttributeValue (null, FlowerEnum.ID.getField ()));
        if((reader.getAttributeValue (null, FlowerEnum.SOIL.getField ()))!= null){
            flower.setSoil (Soil.fromValue (reader.getAttributeValue (null, FlowerEnum.SOIL.getField ())));
        }else{
            flower.setSoil (Soil.DIRT);
        }
        String name;
        while (reader.hasNext ()) {
            int type = reader.next ();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName ();
                    switch (FlowerEnum.valueOf (name.replace ("-", "_").toUpperCase ())) {
                        case NAME:
                            flower.setName (getXMLText (reader));
                            break;
                        case ORIGIN:
                            flower.setOrigin (getXMLText (reader));
                            break;
                        case FIRSTMENTION:
                            flower.setFirstMention (DateConvert.convertDate (getXMLText (reader)));
                            break;
                        case MULTIPLYING:
                            flower.setMultiplying (Multiplying.fromValue (getXMLText (reader)));
                            break;
                        case VISUAL:
                            flower.setVisual (getXMLVisual (reader));
                            break;
                        case GROWING_TIPS:
                            flower.setGrowingTip (getXMLGrowing (reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName ().replace ("-","_").toUpperCase ();
                    FlowerEnum flowerEnum = FlowerEnum.valueOf (name);
                    if (flowerEnum == FlowerEnum.MONOCOTS_FLOWER || flowerEnum == FlowerEnum.DICOTYLEDONS_FLOWER) {
                        return flower;
                    }
                    break;
            }
        }
        throw  new XMLStreamException ("Unknown element");
    }

    private GrowingTip getXMLGrowing(XMLStreamReader reader) throws XMLStreamException {
        GrowingTip growing = new GrowingTip ();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (FlowerEnum.valueOf(name.replace ("-","_").toUpperCase ())) {
                        case TEMPERATURE:
                            growing.setTemperature (Integer.parseInt (getXMLText (reader)));
                            break;
                        case LIGHTING:
                            growing.setLighting (Boolean.parseBoolean (getXMLText (reader)));
                            break;
                        case WATERING:
                            growing.setWatering (Integer.parseInt (getXMLText (reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (FlowerEnum.valueOf(name.replace ("-","_").toUpperCase ()) == FlowerEnum.GROWING_TIPS){
                        return growing;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag growing-tips");
    }

    private Visual getXMLVisual(XMLStreamReader reader) throws XMLStreamException {
        Visual visual = new Visual ();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();

                    switch (FlowerEnum.valueOf(name.replace ("-","_").toUpperCase ())) {
                        case LEAF_COLOR:
                            visual.setLeafColor (getXMLText (reader));
                            break;
                        case STEM_COLOR:
                            visual.setStemColor (getXMLText (reader));
                            break;
                        case LENGTH:
                            visual.setLength (Integer.parseInt (getXMLText (reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (FlowerEnum.valueOf(name.replace ("-","_").toUpperCase ()) == FlowerEnum.VISUAL){
                        return visual;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag visual");
    }
    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
