import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

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
        baby.changeGlucoseConcentration(0.4,0.2);
        LinkedHashMap<String, Double> glucoseConcentration;
        glucoseConcentration = baby.getGlucoseConcentration();
        Assertions.assertEquals(0.2,glucoseConcentration.get("10:35")); //compares expected value with value stored at key
    }

    @Test
    public void testChangeEvent(){
        Baby baby = new Baby("baby1"); //instantiates an object of class Baby
        baby.addEvent("breakfast","8:30");
        baby.addEvent("lunch","13:00");
        baby.addEvent("dinner","19:00");
        baby.changeEvent("breakfast","breakfast without milk");
        LinkedHashMap<String, String> event;
        event = baby.getEvent();
        Assertions.assertEquals("breakfast without milk",event.get("8:30")); //comparison used to test if key 8:30 exists and is linked to corresponding event
    }

    @Test
    public void testChangeGlucoseConcentrationTimestamp(){
        Baby baby = new Baby("baby1"); //instantiates an object of class Baby
        baby.addGlucoseConcentration(0.1,"10:30");
        baby.addGlucoseConcentration(0.4,"10:33");
        baby.addGlucoseConcentration(0.3,"10:40");
        baby.changeGlucoseConcentrationTimestamp("10:33","10:35");
        LinkedHashMap<String, Double> glucoseConcentration;
        glucoseConcentration = baby.getGlucoseConcentration();
        Assertions.assertEquals(0.4,glucoseConcentration.get("10:35"));
    }

    @Test
    public void testChangeEventTimestamp(){
        Baby baby = new Baby("baby1"); //instantiates an object of class Baby
        baby.addEvent("breakfast","8:30");
        baby.addEvent("lunch","13:00");
        baby.addEvent("dinner","19:00");
        baby.changeEventTimestamp("8:30","9:00");
        LinkedHashMap<String, String> event;
        event = baby.getEvent();
        Assertions.assertEquals("breakfast",event.get("9:00"));
    }

    //Delete methods
    @Test
    public void testDeleteGlucoseConcentration(){
        Baby baby = new Baby("baby1"); //instantiates an object of class Baby
        baby.addGlucoseConcentration(0.1,"10:30");
        baby.addGlucoseConcentration(0.4,"10:33");
        baby.addGlucoseConcentration(0.3,"10:40");
        baby.deleteGlucoseConcentration(0.4);
        LinkedHashMap<String, Double> glucoseConcentration;
        glucoseConcentration = baby.getGlucoseConcentration();
        LinkedHashMap<String, Double> expectedOutput = new LinkedHashMap<>();
        expectedOutput.put("10:30",0.1);
        expectedOutput.put("10:40",0.3);
        Assertions.assertEquals(expectedOutput,glucoseConcentration);
    }

    @Test
    public void testDeleteEvent(){
        Baby baby = new Baby("baby1"); //instantiates an object of class Baby
        baby.addEvent("breakfast","8:30");
        baby.addEvent("lunch","13:00");
        baby.addEvent("dinner","19:00");
        baby.deleteEvent("breakfast");
        LinkedHashMap<String, String> event;
        event = baby.getEvent();
        LinkedHashMap<String, String> expectedOutput = new LinkedHashMap<>();
        expectedOutput.put("13:00","lunch");
        expectedOutput.put("19:00","dinner");
        Assertions.assertEquals(expectedOutput,event);
    }

    //
    }
