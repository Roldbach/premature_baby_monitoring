import DataHandling.Baby;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class TestBaby {

    /*Commented methods are a bit useless to test (there's no logic to test)

    @Test
    public void testGetID(){
        Baby baby = new Baby("expected"); //instantiates an object of class Baby
        String id = baby.getID(); //calls getID method and stores output in string id
        Assertions.assertEquals("expected",id); //compares real output with expected output
    }
    @Test
    public void testGetAndAddGlucoseConcentration(){
        Baby baby = new Baby("expected"); //instantiates an object of class Baby
        LinkedHashMap<String, Double> expected = new LinkedHashMap<>(); //creates a LinkedHashMap object to store expected values
        LinkedHashMap<String, Double> glucoseConcentration; //creates a LinkedHashMap object to store real output values
        baby.addGlucoseConcentration(0.3,"10:30");
        expected.put("10:30",0.3); //stores expected values in expected to enable comparison below
        glucoseConcentration = baby.getGlucoseConcentration(); //stores real output from get method in test
        Assertions.assertEquals(expected,glucoseConcentration); //compares real output with expected output
    }
    @Test
    public void testGetAndAddSkinCurrentAndConcentration(){
        Baby baby = new Baby("expected"); //instantiates an object of class Baby
        LinkedHashMap<String, Double> expectedCurrent = new LinkedHashMap<>(); //creates a LinkedHashMap object to store expected values
        LinkedHashMap<String, Double> expectedConcentration = new LinkedHashMap<>();
        LinkedHashMap<String, Double> skinCurrent; //creates a LinkedHashMap object to store real output values
        LinkedHashMap<String, Double> skinConcentration;
        baby.addSkinConcentration(0.3,0.4,"10:30");
        expectedCurrent.put("10:30",0.3); //stores expected values in expected to enable comparison below
        expectedConcentration.put("10:30",0.4);
        skinCurrent = baby.getSkinCurrent(); //stores real output from get method in test
        skinConcentration = baby.getSkinConcentration();
        Assertions.assertEquals(expectedCurrent,skinCurrent); //compares real output with expected output
        Assertions.assertEquals(expectedConcentration,skinConcentration);
    }
    @Test
    public void testGetAndAddEvent(){
        Baby baby = new Baby("expected"); //instantiates an object of class Baby
        LinkedHashMap<String, String> expected = new LinkedHashMap<>(); //creates a LinkedHashMap object to store expected values
        LinkedHashMap<String, String> event; //creates a LinkedHashMap object to store real output values
        baby.addEvent("food intake","10:30");
        expected.put("10:30","food intake"); //stores expected values in expected to enable comparison below
        event = baby.getEvent(); //stores real output from get method in test
        Assertions.assertEquals(expected,event); //compares real output with expected output
    }

    //Change methods
    @Test
    public void testChangeGlucoseConcentration(){
        Baby baby = new Baby("baby1"); //instantiates an object of class Baby
        baby.addGlucoseConcentration(0.1,"10:30");
        baby.addGlucoseConcentration(0.4,"10:35");
        baby.addGlucoseConcentration(0.3,"10:40");
        baby.changeGlucoseConcentration("10:35",0.2);
        //Tests if value at key "10:35" is now 0.2
        Assertions.assertEquals(0.2,baby.getGlucoseConcentration().get("10:35")); //compares expected value with value stored at key
    }

    @Test
    public void testChangeEvent(){
        Baby baby = new Baby("baby1"); //instantiates an object of class Baby
        baby.addEvent("breakfast","8:30");
        baby.addEvent("lunch","13:00");
        baby.addEvent("dinner","19:00");
        baby.changeEvent("8:30","breakfast without milk");
        //Tests if value at key "8:30" is now "breakfast without milk"
        Assertions.assertEquals("breakfast without milk",baby.getEvent().get("8:30"));
    }

    //Delete methods
    @Test
    public void testDeleteGlucoseConcentration(){
        Baby baby = new Baby("baby1"); //instantiates an object of class Baby
        baby.addGlucoseConcentration(0.1,"10:30");
        baby.addGlucoseConcentration(0.4,"10:33");
        baby.addGlucoseConcentration(0.3,"10:40");
        baby.deleteGlucoseConcentration("10:33");
        //Creates expected LinkedHashMap for later comparison against output
        LinkedHashMap<String, Double> expectedOutput = new LinkedHashMap<>();
        expectedOutput.put("10:30",0.1);
        expectedOutput.put("10:40",0.3);
        //Tests expected LinkedHashMap against output LinkedHashMap
        Assertions.assertEquals(expectedOutput,baby.getGlucoseConcentration());
    }
    @Test
    public void testDeleteEvent(){
        Baby baby = new Baby("baby1"); //instantiates an object of class Baby
        baby.addEvent("breakfast","8:30");
        baby.addEvent("lunch","13:00");
        baby.addEvent("dinner","19:00");
        baby.deleteEvent("8:30");
        //Creates expected LinkedHashMap for later comparison against output
        LinkedHashMap<String, String> expectedOutput = new LinkedHashMap<>();
        expectedOutput.put("13:00","lunch");
        expectedOutput.put("19:00","dinner");
        //Tests expected LinkedHashMap against output LinkedHashMap
        Assertions.assertEquals(expectedOutput,baby.getEvent());
    } */


    //Rest of methods
    @Test
    public void testChangeGlucoseConcentrationTimestamp(){
        Baby baby = new Baby("baby1"); //instantiates an object of class Baby
        baby.addGlucoseConcentration(0.1,"10:30");
        baby.addGlucoseConcentration(0.4,"10:33");
        baby.addGlucoseConcentration(0.3,"10:40");
        baby.changeGlucoseConcentrationTimestamp("10:33","10:35");
        //Tests if value at key "10:35" is now 0.4
        Assertions.assertEquals(0.4,baby.getGlucoseConcentration().get("10:35"));
    }

    @Test
    public void testChangeEventTimestamp(){
        Baby baby = new Baby("baby1"); //instantiates an object of class Baby
        baby.addEvent("breakfast","8:30");
        baby.addEvent("lunch","13:00");
        baby.addEvent("dinner","19:00");
        baby.changeEventTimestamp("8:30","9:00");
        /*
        Tests if value at key "9:00" is the corresponding event. This implies that the
        previous key "8:30" which was linked to such event has changed to 9:00
        */
        Assertions.assertEquals("breakfast",baby.getEvent().get("9:00"));
    }

    @Test
    public void testSaveBaby() throws FileNotFoundException {
        Baby baby = new Baby("baby 2"); //instantiates an object of class Baby
        baby.addGlucoseConcentration(0.921,"8:30");
        baby.addGlucoseConcentration(0.922,"8:40");
        baby.addGlucoseConcentration(0.923,"8:50");
        baby.addSkinConcentration(0.1,0.01,"8:30");
        baby.addSkinConcentration(0.2,0.02,"8:40");
        baby.addSkinConcentration(0.3,0.03,"8:50");
        baby.addEvent("breakfast","10:00");
        baby.addEvent("lunch","14:00");
        baby.addEvent("dinner","19:00");
        baby.saveBaby(System.getProperty("user.dir")+ "/Testfiles/TestBaby");
        File babyFile = new File(System.getProperty("user.dir")+ "/Testfiles/TestBaby" + "/baby 2.txt"); //opens created file
        Scanner babyReader = new Scanner(babyFile);
        //Tests all lines of the file to make sure data has been saved and formatting is correct
        Assertions.assertEquals("id:baby 2",babyReader.nextLine());
        Assertions.assertEquals("gc:8:30,0.921",babyReader.nextLine());
        Assertions.assertEquals("gc:8:40,0.922",babyReader.nextLine());
        Assertions.assertEquals("gc:8:50,0.923",babyReader.nextLine());
        Assertions.assertEquals("sa:8:30,0.1",babyReader.nextLine());
        Assertions.assertEquals("sa:8:40,0.2",babyReader.nextLine());
        Assertions.assertEquals("sa:8:50,0.3",babyReader.nextLine());
        Assertions.assertEquals("sc:8:30,0.01",babyReader.nextLine());
        Assertions.assertEquals("sc:8:40,0.02",babyReader.nextLine());
        Assertions.assertEquals("sc:8:50,0.03",babyReader.nextLine());
        Assertions.assertEquals("ev:10:00,breakfast",babyReader.nextLine());
        Assertions.assertEquals("ev:14:00,lunch",babyReader.nextLine());
        Assertions.assertEquals("ev:19:00,dinner",babyReader.nextLine());
    }

    @Test
    public void testLoadBaby(){
        Baby baby = new Baby("baby 2"); //instantiates an object of class baby
        //Loads existing file
        baby.loadBaby(System.getProperty("user.dir")+ "/Testfiles/TestBaby" + "/baby 2.txt");
        //Creates expected String & LinkedHashMaps for later comparison against output
        String id = "baby 2";
        LinkedHashMap<String,Double> glucoseConcentration = new LinkedHashMap<>();
        LinkedHashMap<String,Double> skinCurrent = new LinkedHashMap<>();
        LinkedHashMap<String,Double> skinConcentration = new LinkedHashMap<>();
        LinkedHashMap<String,String> event = new LinkedHashMap<>();
        glucoseConcentration.put("8:30",0.921);
        glucoseConcentration.put("8:40",0.922);
        glucoseConcentration.put("8:50",0.923);
        skinCurrent.put("8:30",0.1);
        skinCurrent.put("8:40",0.2);
        skinCurrent.put("8:50",0.3);
        skinConcentration.put("8:30",0.01);
        skinConcentration.put("8:40",0.02);
        skinConcentration.put("8:50",0.03);
        event.put("10:00","breakfast");
        event.put("14:00","lunch");
        event.put("19:00","dinner");
        //Compares the expected String & LinkedHashMaps with the ones created by loadBaby method
        Assertions.assertEquals(id,baby.getID());
        Assertions.assertEquals(glucoseConcentration,baby.getGlucoseConcentration());
        Assertions.assertEquals(skinCurrent,baby.getSkinCurrent());
        Assertions.assertEquals(skinConcentration,baby.getSkinConcentration());
        Assertions.assertEquals(event,baby.getEvent());
    }

    @Test
    public void testSortTimestamp(){
        Baby baby = new Baby("baby 1");
        //Adding blood glucose, skin glucose and events with disordered timestamps
        baby.addGlucoseConcentration(0.921,"2022/01/04 16:41:00");
        baby.addGlucoseConcentration(0.922,"2022/01/04 16:39:00");
        baby.addGlucoseConcentration(0.923,"2022/01/04 16:40:00");
        baby.addSkinConcentration(0.1,0.01,"2022/01/04 16:41:00");
        baby.addSkinConcentration(0.2,0.02,"2022/01/04 16:39:00");
        baby.addSkinConcentration(0.3,0.03,"2022/01/04 16:40:00");
        baby.addEvent("breakfast","2022/01/04 16:41:00");
        baby.addEvent("lunch","2022/01/04 16:39:00");
        baby.addEvent("dinner","2022/01/04 16:40:00");
        //Ordering the timestamps
        baby.sortTimestamp();
        //Creating ordered linked hash maps for comparison
        LinkedHashMap<String,Double> glucoseConcentration = new LinkedHashMap<>();
        LinkedHashMap<String,Double> skinCurrent = new LinkedHashMap<>();
        LinkedHashMap<String,Double> skinConcentration = new LinkedHashMap<>();
        LinkedHashMap<String,String> event = new LinkedHashMap<>();
        glucoseConcentration.put("2022/01/04 16:39:00",0.922);
        glucoseConcentration.put("2022/01/04 16:40:00",0.923);
        glucoseConcentration.put("2022/01/04 16:41:00",0.921);
        skinCurrent.put("2022/01/04 16:39:00",0.2);
        skinCurrent.put("2022/01/04 16:40:00",0.3);
        skinCurrent.put("2022/01/04 16:41:00",0.1);
        skinConcentration.put("2022/01/04 16:39:00",0.02);
        skinConcentration.put("2022/01/04 16:40:00",0.03);
        skinConcentration.put("2022/01/04 16:41:00",0.01);
        event.put("2022/01/04 16:39:00","lunch");
        event.put("2022/01/04 16:40:00","dinner");
        event.put("2022/01/04 16:41:00","breakfast");
        //Checking if sort method ordered as expected
        Assertions.assertEquals(glucoseConcentration,baby.getGlucoseConcentration());
        Assertions.assertEquals(skinCurrent,baby.getSkinCurrent());
        Assertions.assertEquals(skinConcentration,baby.getSkinConcentration());
        Assertions.assertEquals(event,baby.getEvent());
    }

    @Test
    public void testResetSkinConcentration(){
        Baby baby = new Baby("baby 1");
        //Adding skin current & concentration values
        baby.addSkinConcentration(0.1,0.01,"2022/01/04 16:39:00");
        baby.addSkinConcentration(0.2,0.02,"2022/01/04 16:40:00");
        baby.addSkinConcentration(0.3,0.03,"2022/01/04 16:41:00");
        //Creating arrays for reset
        ArrayList<String> newTimestamps = new ArrayList<>(Arrays.asList("2022/01/04 16:50:00","2022/01/04 16:51:00","2022/01/04 16:52:00"));
        ArrayList<Double> newSkinCurrent = new ArrayList<>(Arrays.asList(0.4,0.5,0.6));
        ArrayList<Double> newSkinConcentration = new ArrayList<>(Arrays.asList(0.04,0.05,0.06));
        //Resetting skin values
        baby.resetSkinConcentration(newTimestamps,newSkinCurrent,newSkinConcentration);
        //Creating expected linked hash maps for comparison
        LinkedHashMap<String,Double> skinCurrent = new LinkedHashMap<>();
        LinkedHashMap<String,Double> skinConcentration = new LinkedHashMap<>();
        skinCurrent.put("2022/01/04 16:50:00",0.4);
        skinCurrent.put("2022/01/04 16:51:00",0.5);
        skinCurrent.put("2022/01/04 16:52:00",0.6);
        skinConcentration.put("2022/01/04 16:50:00",0.04);
        skinConcentration.put("2022/01/04 16:51:00",0.05);
        skinConcentration.put("2022/01/04 16:52:00",0.06);
        //Checking if reset method worked as expected
        Assertions.assertEquals(skinCurrent,baby.getSkinCurrent());
        Assertions.assertEquals(skinConcentration,baby.getSkinConcentration());
    }
}