package by.training.lihodievski.xmlparsing.parser;

import by.training.lihodievski.xmlparsing.bean.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class StAXFlowerParserTestData {

    private static final Logger LOGGER = LogManager.getLogger (StAXFlowerParserTestData.class);
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

        Flower flowerThree =  new MonocotsFlower ();
        flowerThree.setId ("a1");
        flowerThree.setDateLanding (LocalDate.of (2000,10,20));
        flowerThree.setSoil (Soil.DIRT);
        flowerThree.setName ("Chlorophytum");
        flowerThree.setOrigin ("South Africa");
        flowerThree.getVisual ().setLeafColor ("Green");
        flowerThree.getVisual ().setStemColor ("Green");
        flowerThree.getVisual ().setLength (50);
        flowerThree.getGrowingTip ().setLighting (true);
        flowerThree.getGrowingTip ().setTemperature (26);
        flowerThree.getGrowingTip ().setWatering (250);
        flowerThree.setMultiplying (Multiplying.SEED);

        Flower flowerFour =  new MonocotsFlower ();
        flowerFour.setId ("a2");
        flowerFour.setDateLanding (LocalDate.of (2000,10,20));
        flowerFour.setSoil (Soil.DIRT);
        flowerFour.setName ("Snowdrop");
        flowerFour.setOrigin ("Europe");
        flowerFour.getVisual ().setLeafColor ("White");
        flowerFour.getVisual ().setStemColor ("Dark green");
        flowerFour.getVisual ().setLength (15);
        flowerFour.getGrowingTip ().setLighting (false);
        flowerFour.getGrowingTip ().setTemperature (-8);
        flowerFour.getGrowingTip ().setWatering (100);
        flowerFour.setMultiplying (Multiplying.LEAF);

        Flower flowerFive = new DicotyledonsFlower ();
        flowerFive.setId ("b1");
        flowerFive.setSoil (Soil.DIRT);
        flowerFive.setName ("Iris");
        flowerFive.setOrigin ("Holland");
        flowerFive.getVisual ().setLeafColor ("White");
        flowerFive.getVisual ().setStemColor ("Green");
        flowerFive.getVisual ().setLength (40);
        flowerFive.getGrowingTip ().setWatering (145);
        flowerFive.getGrowingTip ().setLighting (true);
        flowerFive.getGrowingTip ().setTemperature (40);
        flowerFive.setMultiplying (Multiplying.SEED);
        flowerFive.setDateLanding (LocalDate.of (1970,12,22));

        Flower flowerSix = new DicotyledonsFlower ();
        flowerSix.setId ("b2");
        flowerSix.setSoil (Soil.PODZOLIC);
        flowerSix.setName ("Gerbera");
        flowerSix.setOrigin ("Holland");
        flowerSix.getVisual ().setLeafColor ("Red");
        flowerSix.getVisual ().setStemColor ("Green");
        flowerSix.getVisual ().setLength (60);
        flowerSix.getGrowingTip ().setWatering (185);
        flowerSix.getGrowingTip ().setLighting (true);
        flowerSix.getGrowingTip ().setTemperature (15);
        flowerSix.setMultiplying (Multiplying.LEAF);
        flowerSix.setDateLanding (LocalDate.of (1900,10,20));

        Set<Flower> dataOne = new HashSet<> ();
        Set<Flower> dataTwo = new HashSet<> ();
        Set<Flower> dataThree = new HashSet<> ();

        dataOne.add (flowerOne);
        dataOne.add (flowerTwo);

        dataTwo.add (flowerThree);
        dataTwo.add (flowerFour);

        dataThree.add (flowerFive);
        dataThree.add (flowerSix);

        InputStream streamOne = null;
        InputStream streamTwo = null;
        InputStream streamThree = null;
        try {
            Path path = Paths.get("src/test/resources/testDataOne.xml");
            Path path2 = Paths.get("src/test/resources/testDataTwo.xml");
            Path path3 = Paths.get("src/test/resources/testDataThree.xml");
            streamOne = Files.newInputStream (path);
            streamTwo = Files.newInputStream (path2);
            streamThree = Files.newInputStream (path3);
        } catch (FileNotFoundException e) {
            System.out.println (e.getMessage ());
        } catch (IOException e) {
            LOGGER.error ("error",e);
        }

        return  new Object[][]{
                {
                    dataOne, streamOne
                },
                {
                    dataTwo, streamTwo
                },
                {
                    dataThree, streamThree
                }
        };
    }
}

