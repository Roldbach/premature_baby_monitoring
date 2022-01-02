import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        /*
        temporary test code for class baby
        Baby test=new Baby("0922");
        test.addGlucoseConcentration(0.98,"time 1");
        test.addSkinConcentration(0.922,0.968,"time 2");
        test.addEvent("Nice","time 3");
        test.changeGlucoseConcentration(0.98,0.97);
        test.changeEvent("Nice","Very Nice");
        test.changeGlucoseConcentrationTimestamp("time 1","Super time");
        test.changeEventTimestamp("time 3","Super time");
        test.deleteGlucoseConcentration(0.97);
        test.deleteEvent("Very Nice");
        System.out.println(test.getGlucoseConcentration());
        System.out.println(test.getSkinCurrent());
        System.out.println(test.getSkinConcentration());
        System.out.println(test.getEvent());
         */

        /*
        Test code for load baby
        Baby test=new Baby("Test1");
        test.loadBaby("Test.txt");
        System.out.println(test.getID());
        System.out.println(test.getGlucoseConcentration());
        System.out.println(test.getSkinCurrent());
        System.out.println(test.getSkinConcentration());
        System.out.println(test.getEvent());
        */


        /*
        //Test code for save baby
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
        +/

        /*
        Test code for class DataBase
        DataBase test=new DataBase();
        ArrayList<Double> parameter=new ArrayList<>();
        parameter.add(0.01);
        parameter.add(0.02);

        test.addUser("Roldbach","User 1","123",false,"Time 1");
        test.addUser("Roldbach","User 2","456",true,"Time 2");
        test.addUser("Roldbach","User 3","123",false,"Time 1");
        test.deleteUser("Roldbach","User 1","Time 3");
        test.changeCalibrationParameter("Roldbach",parameter,"Time 4");
        test.changeLagTime("Roldbach","9","Time 4");
        test.changePermissionTime("Roldbach","1","Time 5");
        test.changePassword("Roldbach","User 3","789","Time 6");

        test.addBaby("Nigel");
        test.addGlucoseConcentration("Roldbach","Nigel",0.922,"Time 7");
        test.addGlucoseConcentration("Roldbach","Nigel",0.922,"Time 8");
        test.changeGlucoseConcentration("Roldbach","Nigel","Time 7",0.0922,"Time 8");
        test.deleteGlucoseConcentration("Roldbach","Nigel","Time 7","Time 9");

        String time1="2021-12-29 23:00:00";
        String time2="2021-12-29 23:10:00";
        for (String line:test.getLogFile())
        {
            System.out.println(line);
        }
        test.saveDataBase(System.getProperty("user.dir")+"\\DataBase",System.getProperty("user.dir")+"\\DataBase\\Baby");
        */

        DataBase test=new DataBase();
        test.loadDataBase(System.getProperty("user.dir")+"\\DataBase",System.getProperty("user.dir")+"\\DataBase\\Baby");
        System.out.println(test.getLogFile());

    }
}
