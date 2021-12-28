import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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
        Test code for save baby
        Baby test=new Baby("Test");
        test.addGlucoseConcentration(0.921,"Time 1");
        test.addGlucoseConcentration(0.922,"Time 2");
        test.addGlucoseConcentration(0.923,"Time 3");
        test.addSkinConcentration(0.1,0.01,"Time 1");
        test.addSkinConcentration(0.2,0.02,"Time 2");
        test.addSkinConcentration(0.3,0.03,"Time 3");
        test.addEvent("good","Time 1");
        test.addEvent("better","Time 2");
        test.addEvent("best","Time 3");
        test.saveBaby(System.getProperty("user.dir"));
        */

        /*
        Test code for time difference
        LocalDateTime now=LocalDateTime.now();
        System.out.println(now);
        String test="2021-12-28 15:00:00";
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss");
        LocalDateTime newTime= (LocalDateTime) formatter.parse(test);
         */
    }
}
