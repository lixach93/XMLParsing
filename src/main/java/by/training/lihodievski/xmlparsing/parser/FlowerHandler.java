package by.training.lihodievski.xmlparsing.parser;


import by.training.lihodievski.xmlparsing.bean.*;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class FlowerHandler extends DefaultHandler  {

    private Set<Flower> flowers;
    private Flower current = null;
    private FlowerEnum currentEnum = null;
    private EnumSet<FlowerEnum> withText;

    FlowerHandler() {
        flowers = new HashSet<>();
        withText = EnumSet.range (FlowerEnum.NAME, FlowerEnum.WATERING);
    }

    public Set<Flower> getFlowers() {
        return flowers;
    }

    public void startElement(String uri, String localName, String qName, Attributes attrs) {

        if ("monocots-flower".equals (localName)) {
            current = new MonocotsFlower ();
            current.setId (attrs.getValue (0));
            if (attrs.getValue (1) !=null) {
                current.setSoil (Soil.fromValue (attrs.getValue (1)));
            }else {
                current.setSoil (Soil.DIRT);
            }
        }
        else if("dicotyledons-flower".equals (localName)){
            current = new DicotyledonsFlower ();
            current.setId (attrs.getValue (0));
            if (attrs.getValue (1) !=null) {
                current.setSoil (Soil.fromValue (attrs.getValue (1)));
            }else {
                current.setSoil (Soil.DIRT);
            }
        }
        else {
            FlowerEnum temp = FlowerEnum.valueOf (localName.replace ("-","_").toUpperCase ());
            if (withText.contains (temp)) {
                currentEnum = temp;
            }
        }
    }

    public void endElement(String uri, String localName, String qName) {
        if ("dicotyledons-flower".equals (localName)) {
            flowers.add (current);
        }
        if ("monocots-flower".equals (localName)) {
            flowers.add (current);
        }
    }

    public void characters(char[] ch, int start, int length) {
        String s = new String (ch, start, length).trim ();
        if (currentEnum != null) {
            switch (currentEnum) {
                case NAME:
                    current.setName (s);
                    break;
                case ORIGIN:
                    current.setOrigin (s);
                    break;
                case LEAF_COLOR:
                    current.getVisual ().setLeafColor (s);
                    break;
                case STEM_COLOR:
                    current.getVisual ().setStemColor (s);
                    break;
                case LENGTH:
                    current.getVisual ().setLength (Integer.parseInt (s));
                    break;
                case TEMPERATURE:
                    current.getGrowingTip ().setTemperature (Integer.parseInt (s));
                    break;
                case LIGHTING:
                    current.getGrowingTip().setLighting (Boolean.parseBoolean (s));
                    break;
                case WATERING:
                    current.getGrowingTip ().setWatering (Integer.parseInt (s));
                    break;
                case MULTIPLYING:
                    current.setMultiplying (Multiplying.fromValue (s));
                    break;
                default:
                    throw new EnumConstantNotPresentException (
                            currentEnum.getDeclaringClass (), currentEnum.name ());
            }
        }
        currentEnum = null;
    }
}


