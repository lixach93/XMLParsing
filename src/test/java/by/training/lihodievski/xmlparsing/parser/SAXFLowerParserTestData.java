package by.training.lihodievski.xmlparsing.parser;

import by.training.lihodievski.xmlparsing.bean.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class SAXFLowerParserTestData {

    private static final Logger LOGGER = LogManager.getLogger (SAXFLowerParserTestData.class);
    @DataProvider(name = "buildSetFlowersData")
    public static Object[] buildSetFlowersData(){

        Flower flowerOne =  new MonocotsFlower ();
        flowerOne.setId ("a6");
        flowerOne.setDateLanding (LocalDate.of (2000,10,20));
        flowerOne.setSoil (Soil.SOD_PODZOLIC);
        flowerOne.setName ("Vallota");
        flowerOne.setOrigin ("South Africa");
        flowerOne.getVisual ().setLeafColor ("Red");
        flowerOne.getVisual ().setStemColor ("Green");
        flowerOne.getVisual ().setLength (60);
        flowerOne.getGrowingTip ().setLighting (false);
        flowerOne.getGrowingTip ().setTemperature (25);
        flowerOne.getGrowingTip ().setWatering (90);
        flowerOne.setMultiplying (Multiplying.SEED);

        Flower flowerTwo = new MonocotsFlower ();
        flowerTwo.setId ("a7");
        flowerTwo.setDateLanding (LocalDate.of (2000,10,29));
        flowerTwo.setSoil (Soil.DIRT);
        flowerTwo.setName ("Hedychium");
        flowerTwo.setOrigin ("South Asia");
        flowerTwo.getVisual ().setLeafColor ("Yellow");
        flowerTwo.getVisual ().setStemColor ("Green");
        flowerTwo.getVisual ().setLength (45);
        flowerTwo.getGrowingTip ().setLighting (false);
        flowerTwo.getGrowingTip ().setTemperature (27);
        flowerTwo.getGrowingTip ().setWatering (105);
        flowerTwo.setMultiplying (Multiplying.LEAF);

        Flower flowerThree = new DicotyledonsFlower ();
        flowerThree.setId ("b1");
        flowerThree.setSoil (Soil.DIRT);
        flowerThree.setName ("Iris");
        flowerThree.setOrigin ("Holland");
        flowerThree.getVisual ().setLeafColor ("White");
        flowerThree.getVisual ().setStemColor ("Green");
        flowerThree.getVisual ().setLength (40);
        flowerThree.getGrowingTip ().setWatering (145);
        flowerThree.getGrowingTip ().setLighting (true);
        flowerThree.getGrowingTip ().setTemperature (40);
        flowerThree.setMultiplying (Multiplying.SEED);
        flowerThree.setDateLanding (LocalDate.of (1970,12,22));

        Flower flowerFour = new DicotyledonsFlower ();
        flowerFour.setId ("b2");
        flowerFour.setSoil (Soil.PODZOLIC);
        flowerFour.setName ("Gerbera");
        flowerFour.setOrigin ("Holland");
        flowerFour.getVisual ().setLeafColor ("Red");
        flowerFour.getVisual ().setStemColor ("Green");
        flowerFour.getVisual ().setLength (60);
        flowerFour.getGrowingTip ().setWatering (185);
        flowerFour.getGrowingTip ().setLighting (true);
        flowerFour.getGrowingTip ().setTemperature (15);
        flowerFour.setMultiplying (Multiplying.LEAF);
        flowerFour.setDateLanding (LocalDate.of (1900,10,20));

        Set<Flower> dataOne = new HashSet<> ();
        Set<Flower> dataTwo = new HashSet<> ();

        dataOne.add (flowerOne);
        dataOne.add (flowerTwo);

        dataTwo.add (flowerThree);
        dataTwo.add (flowerFour);

        InputStream streamOne = null;
        InputStream streamTwo = null;
        try {
            Path path = Paths.get("src/test/resources/testDataOne.xml");
            Path path2 = Paths.get("src/test/resources/testDataThree.xml");
            streamOne = Files.newInputStream (path);
            streamTwo = Files.newInputStream (path2);
        } catch (IOException e) {
            LOGGER.error ("error",e);
        }

        return  new Object[][]{
                {
                        dataOne, streamOne
                },
                {
                        dataTwo, streamTwo
                }
        };
    }
}

