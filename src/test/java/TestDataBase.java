import DataHandling.Baby;
import DataHandling.DataBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;


public class TestDataBase {

    //Setters and getters are not useful to test, we would be testing the language rather than actual code

    @Test
    public void testLogIn(){
        DataBase db = new DataBase();
        db.addUser("Admin","Admin2","adminPassword",true,"10:30");
        db.addUser("Admin","notAdmin","password",false,"10:31");
        //Tests if logIn method returns true/false when credentials are/aren't already contained in user/admin hashtable or are erroneous
        Assertions.assertTrue(db.logIn("Admin2","adminPassword")[0]);
        Assertions.assertFalse(db.logIn("Admin2","password")[0]);
        Assertions.assertTrue(db.logIn("notAdmin","password")[0]);
        //Tests if logIn method returns true for admins and false for users
        Assertions.assertTrue(db.logIn("Admin2","adminPassword")[1]);
        Assertions.assertFalse(db.logIn("notAdmin","password")[1]);
    }

    @Test
    public void testGetBabyList(){
        DataBase db = new DataBase();
        db.addBaby("baby1");
        db.addBaby("baby2");
        db.addBaby("baby3");
        ArrayList<String> expectedBabyList = new ArrayList<>(Arrays.asList("baby3","baby2","baby1"));
        Assertions.assertEquals(expectedBabyList,db.getBabyList());
    }

    @Test
    public void testUpdateLogFile(){
        DataBase db = new DataBase();
        db.updateLogFile("10:30","Admin","None","Add User","notAdmin");
        db.updateLogFile("10:35","Admin","None","Add User","notAdmin2");
        //Tests if log file is updated appropriately by checking against expected string at each index
        Assertions.assertEquals("10:30,Admin,None,Add User,notAdmin",db.getLogFile().get(0));
        Assertions.assertEquals("10:35,Admin,None,Add User,notAdmin2",db.getLogFile().get(1));
    }

    @Test
    public void testAddUser(){
        DataBase db = new DataBase();
        db.addUser("Admin","notAdmin","password",false,"10:30");
        db.addUser("Admin","Admin2","adminPassword",true,"10:30");
        //Tests if users (admin and not admin) have been added correctly to respective hashtable
        Assertions.assertEquals("password",db.getUser().get("notAdmin"));
        Assertions.assertEquals("adminPassword",db.getAdministrator().get("Admin2"));
        //Tests if it still works when adding an already existing user (i.e. if it ignores the add)
        db.addUser("Admin","Admin2","adminPassword",true,"10:30");
        Hashtable<String,String> administrator = new Hashtable<>();
        administrator.put("Admin2","adminPassword");
        Assertions.assertEquals(administrator,db.getAdministrator());
    }

    @Test
    public void testCheckPermission(){
        DataBase db = new DataBase();
        //Tests if checkPermission method returns true for <5 minute differences
        Assertions.assertTrue(db.checkPermission("2021/12/30 10:30:00", "2021/12/30 10:34:59"));
        //Tests if checkPermission method returns false for >5 minute differences
        Assertions.assertFalse(db.checkPermission("2021/12/30 10:30:00", "2021/12/30 10:35:01"));
        //Tests if checkPermission method returns false for <5 minute differences but on different dates
        Assertions.assertFalse(db.checkPermission("2021/12/29 10:30:00", "2021/12/30 10:31:00"));
    }

    @Test
    public void testChangePassword(){
        DataBase db = new DataBase();
        db.addUser("Admin","notAdmin","password",false,"10:30");
        db.changePassword("Admin","notAdmin","newPassword","10:35");
        //Tests if value at key "notAdmin" has changed to "newPassword"
        Assertions.assertEquals("newPassword",db.getUser().get("notAdmin"));
    }

    @Test
    public void testChangeCalibrationParameters(){
        DataBase db = new DataBase();
        db.loadCalibrationParameter("0.4,0.4,0.4,0.4");
        ArrayList<Double> cParameters = new ArrayList<>(Arrays.asList(0.1,0.2,0.3,0.4));
        db.changeCalibrationParameter("Admin",cParameters,"10:30");
        //Tests output list of parameters against expected list to see if change has been implemented
        Assertions.assertEquals(cParameters,db.getCalibrationParameter());
    }

    @Test
    public void testChangeLagTime(){
        DataBase db = new DataBase();
        //Value of lagTime initially set to 10 minutes
        db.changeLagTime("Admin","12","10:30");
        //Tests if value of lagTime has changed to "12"
        Assertions.assertEquals("12",db.getLagTime());
    }

    @Test
    public void testChangePermissionTime(){
        DataBase db = new DataBase();
        //Value of permissionTime initially set to 5 minutes
        db.changePermissionTime("Admin","12","10:30");
        //Tests if value of permissionTime has changed to "10"
        Assertions.assertEquals("12",db.getPermissionTime());
    }

    @Test
    public void testLoadCalibrationParameter() {
        DataBase db = new DataBase();
        ArrayList<Double> cParameters = new ArrayList<>(Arrays.asList(0.1,0.2,0.3,0.4));
        //Tests if output is equal to an ArrayList of the input values
        Assertions.assertEquals(cParameters, db.loadCalibrationParameter("0.1,0.2,0.3,0.4"));
    }

    @Test
    public void testDeleteUser(){
        DataBase db = new DataBase();
        db.addUser("Admin", "notAdmin", "password", false, "10:30");
        db.deleteUser("Admin","notAdmin","10:34");
        Hashtable<String,String> empty = new Hashtable<>();
        //Tests if "notAdmin" was deleted and hence User Hashtable is empty
        Assertions.assertEquals(empty,db.getUser());
        //Tests if deleteUser method returns false when user ID does not exist
        //Assertions.assertFalse(db.deleteUser("Admin","notAdmin2","10:34"));
    }

    @Test
    public void testSaveDataBase() throws FileNotFoundException {
        DataBase db = new DataBase();
        //Adds info to class to test it has been correctly saved posteriorly
        db.addUser("Admin", "Admin2", "adminPassword", true, "10:30");
        db.addUser("Admin", "notAdmin", "password", false, "10:31");
        db.changeCalibrationParameter("Admin",db.loadCalibrationParameter("0.1,0.2,0.3,0.4"),"10:31");
        //db.addBaby("baby2");
        db.addGlucoseConcentration("notAdmin", "baby2", 0.1, "10:32");
        db.addSkinConcentration("notAdmin", "baby2", 0.01, 0.1, "10:32");
        db.addEvent("notAdmin", "baby2", "breakfast", "10:32");
        //Saves the dataBase
        db.saveDataBase(System.getProperty("user.home") + "/Desktop/Database/", System.getProperty("user.home") + "/Desktop/Database/Babies/");
        //Loads the saved files
        File dBAccountFile = new File(System.getProperty("user.home") + "/Desktop/Database/" + "\\account.txt"); //opens created user&admin dataBase file
        File dBSettingsFile = new File(System.getProperty("user.home") + "/Desktop/Database/" + "\\setting.txt"); //opens created user&admin dataBase file
        File babyFile = new File(System.getProperty("user.home") + "/Desktop/Database/Babies/" + "\\baby2.txt"); //opens created baby file
        File logFile = new File(System.getProperty("user.home") + "/Desktop/Database/" + "\\log file.txt"); //opens created baby file
        Scanner accountReader = new Scanner(dBAccountFile);
        Scanner settingsReader = new Scanner(dBSettingsFile);
        Scanner babyReader = new Scanner(babyFile);
        Scanner logReader = new Scanner(logFile);
        //Tests expected lines in each file to make sure data has been saved and formatting is correct
        Assertions.assertEquals("us:notAdmin,password",accountReader.nextLine());
        Assertions.assertEquals("ad:Admin2,adminPassword",accountReader.nextLine());
        Assertions.assertEquals("cp:0.1,0.2,0.3,0.4",settingsReader.nextLine());
        Assertions.assertEquals("lt:10",settingsReader.nextLine());
        Assertions.assertEquals("pt:5",settingsReader.nextLine());
        Assertions.assertEquals("id:baby2",babyReader.nextLine());
        Assertions.assertEquals("gc:10:32,0.1",babyReader.nextLine());
        Assertions.assertEquals("sa:10:32,0.01",babyReader.nextLine());
        Assertions.assertEquals("sc:10:32,0.1",babyReader.nextLine());
        Assertions.assertEquals("ev:10:32,breakfast",babyReader.nextLine());
        Assertions.assertEquals("10:30,Admin,None,Add Administrator,Admin2",logReader.nextLine());
        Assertions.assertEquals("10:31,Admin,None,Add User,notAdmin",logReader.nextLine());
        Assertions.assertEquals("10:31,Admin,None,Change Calibration Parameter,None",logReader.nextLine());
        Assertions.assertEquals("10:32,notAdmin,baby2,Add Glucose Concentration,0.1",logReader.nextLine());
        Assertions.assertEquals("10:32,notAdmin,baby2,Add Skin Current/Concentration,0.01/0.1",logReader.nextLine());
        Assertions.assertEquals("10:32,notAdmin,baby2,Add Event,breakfast",logReader.nextLine());
    }

    @Test
    public void testLoadDataBase(){
        DataBase db = new DataBase();
        //Loads dataBase from existing files
        db.loadDataBase(System.getProperty("user.home") + "/Desktop/Database/", System.getProperty("user.home") + "/Desktop/Database/Babies/");
        //Creates expected Hashtables, Arraylists & strings for later comparison
        String lagTime = "10";
        String permissionTime = "5";
        Hashtable<String, String> user = new Hashtable<>();
        user.put("notAdmin","password");
        Hashtable<String, String> administrator = new Hashtable<>();
        administrator.put("Admin2","adminPassword");
        ArrayList<String> babyList = new ArrayList<>();
        babyList.add("baby2");
        ArrayList<String> logFile = new ArrayList<>();
        logFile.add("10:30,Admin,None,Add Administrator,Admin2");
        logFile.add("10:31,Admin,None,Add User,notAdmin");
        logFile.add("10:31,Admin,None,Change Calibration Parameter,None");
        logFile.add("10:32,notAdmin,baby2,Add Glucose Concentration,0.1");
        logFile.add("10:32,notAdmin,baby2,Add Skin Current/Concentration,0.01/0.1");
        logFile.add("10:32,notAdmin,baby2,Add Event,breakfast");
        ArrayList<Double> calibrationParameter = new ArrayList<>();
        calibrationParameter.add(0.1);
        calibrationParameter.add(0.2);
        calibrationParameter.add(0.3);
        calibrationParameter.add(0.4);
        //Compares the content of the expected structures with the ones created by loadDataBase method
        Assertions.assertEquals(lagTime,db.getLagTime());
        Assertions.assertEquals(permissionTime,db.getPermissionTime());
        Assertions.assertEquals(user,db.getUser());
        Assertions.assertEquals(administrator,db.getAdministrator());
        Assertions.assertEquals(babyList,db.getBabyList());
        Assertions.assertEquals(logFile,db.getLogFile());
        Assertions.assertEquals(calibrationParameter,db.getCalibrationParameter());
    }

    @Test
    public void testFormatGlucoseConcentration(){
        DataBase db = new DataBase();
        db.addBaby("baby 1");
        //Adding blood glucose values
        db.addGlucoseConcentration("Admin","baby 1",0.922,"2022/01/04 16:40:00");
        db.addGlucoseConcentration("Admin","baby 1",0.921,"2022/01/04 16:39:00");
        //Formatting and checking if array is as expected
        String[][] output = db.formatGlucoseConcentration("baby 1");
        Assertions.assertEquals("2022/01/04 16:39:00",output[0][0]);
        Assertions.assertEquals("0.921",output[0][1]);
        Assertions.assertEquals("2022/01/04 16:40:00",output[1][0]);
        Assertions.assertEquals("0.922",output[1][1]);
    }

    @Test
    public void testFormatSkinConcentration(){
        DataBase db = new DataBase();
        db.addBaby("baby 1");
        //Adding skin glucose values
        db.addSkinConcentration("Admin","baby 1",0.2,0.02,"2022/01/04 16:40:00");
        db.addSkinConcentration("Admin","baby 1",0.1,0.01,"2022/01/04 16:39:00");
        //Formatting and checking if array is as expected
        String[][] output = db.formatSkinConcentration("baby 1");
        Assertions.assertEquals("2022/01/04 16:39:00",output[0][0]);
        Assertions.assertEquals("0.01",output[0][1]);
        Assertions.assertEquals("0.1",output[0][2]);
        Assertions.assertEquals("2022/01/04 16:40:00",output[1][0]);
        Assertions.assertEquals("0.02",output[1][1]);
        Assertions.assertEquals("0.2",output[1][2]);
    }

    @Test
    public void testFormatEvent(){
        DataBase db = new DataBase();
        db.addBaby("baby 1");
        //Adding events
        db.addEvent("Admin","baby 1","lunch","2022/01/04 14:00:00");
        db.addEvent("Admin","baby 1","breakfast","2022/01/04 09:30:00");
        //Formatting and checking if array is as expected
        String[][] output = db.formatEvent("baby 1");
        Assertions.assertEquals("2022/01/04 09:30:00",output[0][0]);
        Assertions.assertEquals("breakfast",output[0][1]);
        Assertions.assertEquals("2022/01/04 14:00:00",output[1][0]);
        Assertions.assertEquals("lunch",output[1][1]);
    }

    @Test
    public void testFormatLogFile(){
        DataBase db = new DataBase();
        //Adding to log file
        db.updateLogFile("10:30","Admin","None","Add User","notAdmin");
        db.updateLogFile("10:35","Admin","None","Add User","notAdmin2");
        //Formatting and checking if array is as expected
        String[][] output = db.formatLogFile();
        Assertions.assertEquals("10:30",output[0][0]);
        Assertions.assertEquals("Admin",output[0][1]);
        Assertions.assertEquals(" ",output[0][2]);
        Assertions.assertEquals("Add User",output[0][3]);
        Assertions.assertEquals("notAdmin",output[0][4]);
        Assertions.assertEquals("10:35",output[1][0]);
        Assertions.assertEquals("Admin",output[1][1]);
        Assertions.assertEquals(" ",output[1][2]);
        Assertions.assertEquals("Add User",output[1][3]);
        Assertions.assertEquals("notAdmin2",output[1][4]);
    }

    @Test
    public void testFormatSetting(){
        DataBase db = new DataBase();
        //Adding calibration parameters
        db.changeCalibrationParameter("Admin",db.loadCalibrationParameter("0.1,0.2,0.3,0.4"),"10:31");
        //Formatting and checking if array is as expected
        String[][] output = db.formatSetting();
        Assertions.assertEquals("10",output[0][0]); //default lag time
        Assertions.assertEquals("5",output[0][1]); //default permission time
        Assertions.assertEquals("0.1,0.2,0.3,0.4",output[0][2]);
    }

    //@Test
    //public void testSortTimestamp()

    //@Test
    //public void testLoadSkinCurrent()


}