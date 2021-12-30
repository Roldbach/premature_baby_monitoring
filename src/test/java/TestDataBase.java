import com.sun.org.apache.xerces.internal.xs.StringList;
import org.graalvm.compiler.hotspot.replacements.AssertionSnippets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;


public class TestDataBase {
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
        //Assertions.assertFalse(db.checkPermission("2021/12/29 10:30:00", "2021/12/30 10:31:00"));
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
        db.changePermissionTime("Admin","10","10:30");
        //Tests if value of permissionTime has changed to "10"
        Assertions.assertEquals("10",db.getPermissionTime());
    }

    @Test
    public void testLoadCalibrationParameter() {
        DataBase db = new DataBase();
        ArrayList<Double> cParameters = new ArrayList<>(Arrays.asList(0.1, 0.2, 0.3, 0.4));
        //Tests if output is equal to an ArrayList of the input values
        Assertions.assertEquals(cParameters, db.loadCalibrationParameter("0.1,0.2,0.3,0.4"));
    }

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
    public void testGetBabyFile(){
        DataBase db = new DataBase();
        db.addBaby("baby1");
        db.addBaby("baby2");
        db.addBaby("baby3");
        ArrayList<String> expectedBabyList = new ArrayList<>(Arrays.asList("baby3","baby2","baby1"));
        Assertions.assertEquals(expectedBabyList,db.getBabyList());
    }
}
