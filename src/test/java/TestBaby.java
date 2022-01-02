import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class TestBaby {
    /*Setters & Getters (a little redundant)
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
    } */

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

    /*Delete methods, quite redundant too
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
    }
    */

    //Load & save methods
    @Test
    public void testSaveBaby() throws FileNotFoundException {
        Baby baby = new Baby("baby1"); //instantiates an object of class Baby
        baby.addGlucoseConcentration(0.921,"8:30");
        baby.addGlucoseConcentration(0.922,"8:40");
        baby.addGlucoseConcentration(0.923,"8:50");
        baby.addSkinConcentration(0.1,0.01,"8:30");
        baby.addSkinConcentration(0.2,0.02,"8:40");
        baby.addSkinConcentration(0.3,0.03,"8:50");
        baby.addEvent("breakfast","8:30");
        baby.addEvent("lunch","13:00");
        baby.addEvent("dinner","19:00");
        baby.saveBaby(System.getProperty("user.home")+ "/Downloads/");
        File babyFile = new File(System.getProperty("user.home")+ "/Downloads/" + "\\baby1.txt"); //opens created file
        Scanner babyReader = new Scanner(babyFile);
        //Tests all lines of the file to make sure data has been saved and formatting is correct
        Assertions.assertEquals("id:baby1",babyReader.nextLine());
        Assertions.assertEquals("gc:8:30,0.921",babyReader.nextLine());
        Assertions.assertEquals("gc:8:40,0.922",babyReader.nextLine());
        Assertions.assertEquals("gc:8:50,0.923",babyReader.nextLine());
        Assertions.assertEquals("sa:8:30,0.1",babyReader.nextLine());
        Assertions.assertEquals("sa:8:40,0.2",babyReader.nextLine());
        Assertions.assertEquals("sa:8:50,0.3",babyReader.nextLine());
        Assertions.assertEquals("sc:8:30,0.01",babyReader.nextLine());
        Assertions.assertEquals("sc:8:40,0.02",babyReader.nextLine());
        Assertions.assertEquals("sc:8:50,0.03",babyReader.nextLine());
        Assertions.assertEquals("ev:8:30,breakfast",babyReader.nextLine());
        Assertions.assertEquals("ev:13:00,lunch",babyReader.nextLine());
        Assertions.assertEquals("ev:19:00,dinner",babyReader.nextLine());
    }

    @Test
    public void testLoadBaby(){
        Baby baby = new Baby("baby1"); //instantiates an object of class baby
        //Loads existing file
        baby.loadBaby(System.getProperty("user.home")+ "/Downloads/" + "\\baby1.txt");
        //Creates expected String & LinkedHashMaps for later comparison against output
        String id = "baby1";
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
        event.put("8:30","breakfast");
        event.put("13:00","lunch");
        event.put("19:00","dinner");
        //Compares the expected String & LinkedHashMaps with the ones created by loadBaby method
        Assertions.assertEquals(id,baby.getID());
        Assertions.assertEquals(glucoseConcentration,baby.getGlucoseConcentration());
        Assertions.assertEquals(skinCurrent,baby.getSkinCurrent());
        Assertions.assertEquals(skinConcentration,baby.getSkinConcentration());
        Assertions.assertEquals(event,baby.getEvent());
    }
}

