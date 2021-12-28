import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedHashMap;

public class DataBase
{
    private Hashtable<String, String> user;
    private Hashtable<String, String> administrator;
    private Hashtable<String, Baby> babyList;
    private ArrayList<String> logFile;
    private ArrayList<Double> calibrationParameter;
    private String lagTime;
    private String permissionTime;

    public DataBase()
    {
        /*
            By initiating the default database, the lag time is set to be 10min and
        the permission time is set to be 5min
         */
        user=new Hashtable<>();
        administrator=new Hashtable<>();
        babyList=new Hashtable<>();
        logFile=new ArrayList<>();
        calibrationParameter=new ArrayList<>();
        lagTime="10";
        permissionTime="5";
    }

    public DataBase(String directory)
    {
        /*
            Initiate the class DataBase with given file path. The file is automatically loaded

        input:
            directory: String, the file path which contains the information about the database

        throws:
            FileNotFoundException: file is not successfully loaded due to the incorrect path
         */
    }

    public Boolean[] logIn(String userID, String password)
    {
        /*
            Verify whether the ID and the password are matched and set the status and priority
        according to this result and the account

        input:
            userID: String, the unique ID for each user
            password: String, the password set by the user

        return:
            result: Boolean[], item 0: whether the user could log in successfully
                               item 1: whether the user is an administrator or not
          */
        Boolean[] result={false, false};
        if (password.equals(user.get(userID))) {result[0]=true;}
        if (password.equals(administrator.get(userID)))
        {
            result[0]=true;
            result[1]=true;
        }
        return result;
    }

    public Hashtable<String, String> getUser()
    {
        /*
            Return the whole list of user id and matching password (Might not be used until version 2)

        return:
            user: Hashtable<String, String>, key=user id, value=password
         */
        return user;
    }

    public Hashtable<String, String> getAdministrator()
    {
        /*
            Return the whole list of administrator id and matching password (Might not be used until version 2)

        return:
            administrator: Hashtable<String, String>, key=administrator id, value=password
         */
        return administrator;
    }

    public ArrayList<String> getBabyList()
    {
        /*
            Return the whole list of baby id (Might not be used until version 2)

        return:
            result: ArrayList<String>, the list of ID of all babies saved in the database
         */
        ArrayList<String> result=new ArrayList<>();
        Enumeration<String> keys=babyList.keys();
        while(keys.hasMoreElements())
        {
            result.add(keys.nextElement());
        }
        return result;
    }

    public ArrayList<String> getLogFile()
    {
        /*
            Return the log file which records every detailed modification to the database

         return:
            logFile: ArrayList<String>, the log file which records every detailed modification to the database
         */
        return logFile;
    }

    public ArrayList<Double> getCalibrationParameter()
    {
        /*
            Return the calibration parameter which could be used for calibration of current reading

            The calibration curve only supports polynomials with float coefficient/order for version 1+
            The index of an element represents the order and the element represents the coefficient
            For example, the function f(x)=1-2x+3x^2 is represented by {1.0,-2.0,3.0}

        return:
            calibrationParameter: ArrayList<Double>, the calibration curve which could be used to
        predict the concentration data from the current reading
         */
        return calibrationParameter;
    }

    public String getLagTime()
    {
        /*
            Return the lag time in minute which could be used to calibrate the timestamp
         of the skin concentration data

         return:
            lagTime: String, the time in minute which specifies the time difference between the input time
        and the sampled time
         */
        return lagTime;
    }

    public String getPermissionTime()
    {
        /*
            Return the permission time in minute which limit the modification to the database for protection

         return:
            permissionTime: String, the time in minute which specifies the time interval the user could
        modify the input
         */
        return permissionTime;

    }

    public LinkedHashMap<String, Double> getGlucoseConcentration(String babyID)
    {
        /*
            Return the glucose concentration data with timestamp of the specific baby with the matched hospital number

        input:
            babyID: String, the unique ID (hospital number) of the baby

        return:
            glucoseConcentration: LinkedHashMap<String,Double>, key=timestamp, value=concentration with put-in order
         */
        return babyList.get(babyID).getGlucoseConcentration();
    }

    public LinkedHashMap<String, Double> getSkinCurrent(String babyID)
    {
        /*
            Return the skin current data with timestamp of the specific baby with the matched hospital number
            (Might not be used until version 2)

        input:
            babyID: String, the unique ID (hospital number) of the baby

        return:
            skinCurrent: LinkedHashMap<String,Double>, key=timestamp, value=current with put-in order
         */
        return babyList.get(babyID).getSkinCurrent();
    }

    public LinkedHashMap<String, Double> getSkinConcentration(String babyID)
    {
        /*
            Return the skin concentration data with timestamp of the specific baby with the matched hospital number

        input:
            babyID: String, the unique ID (hospital number) of the baby

        return:
            skinConcentration: LinkedHashMap<String,Double>, key=timestamp, value=concentration with put-in order
         */
        return babyList.get(babyID).getSkinConcentration();
    }

    public LinkedHashMap<String, String> getEvent(String babyID)
    {
        /*
            Return the event data with timestamp of the specific baby with the matched hospital number

        input:
            babyID: String, the unique ID (hospital number) of the baby

        return:
            event: LinkedHashMap<String,String>, key=timestamp, value=event with put-in order
         */
        return babyList.get(babyID).getEvent();
    }

    public void updateLogFile(String time, String userID, String babyID, String action, String result)
    {
        /*
            Record the detailed modification to the database for management
            The sentence is in the format: time,userID,babyID,action,result if related to baby data modification
            The sentence is in the format: time,userID,None,action,result if related to user account management

        input:
            time: String, the time at which the user performed the action
            userID: String, the unique ID of the user who performed the action
            babyID: String, the unique ID of the baby who is monitored
            action: String, description of the action done
            result: String, the object of the action
         */
        String sentence=time+","+userID+","+babyID+","+action+","+result;
        logFile.add(sentence);
    }

    public boolean addUser(String userID, String newID, String password, boolean givePriority, String time)
    {
        /*
            Add a new user to the system and update the log file only if the given newID is not already in the database
            The user would become an administrator if given priority

        input:
            userID: String, the ID of the user who performed this action
            newID: String, the new ID that could be used in the future
            password: String, the matched password of the new ID
            givePriority: boolean, true if the new ID has the administrator priority, false otherwise
            time: String, the time at which the user performed the action

        return:
            result: boolean, true if added successfully, false otherwise
         */

        //Check whether the new ID is already been used within the database
        boolean status;
        if (givePriority) status = administrator.containsKey(newID);
        else status = user.containsKey(newID);

        //Add the new ID and the matched password into the database according to the result and the givenPriority
        if (!status&&givePriority)
        {
            administrator.put(newID, password);
            updateLogFile(time,userID,"None","Add Administrator",newID);
            return true;
        }
        else if(!status)
        {
            user.put(newID, password);
            updateLogFile(time,userID,"None","Add User",newID);
            return true;
        }
        else
        {
            return false;
        }
    }

    public void addGlucoseConcentration(String userID, String babyID, double value, String time)
    {
        /*
            Add a glucose concentration reading with timestamp for the baby
        with the matched hospital number and update the log file

         input:
            userID: String, the unique ID of the user who performed the action
            babyID: String, the unique ID of the baby who is monitored
            value: double, the glucose concentration reading
            time: String, the time at which the user performed the action
         */
        babyList.get(babyID).addGlucoseConcentration(value,time);
        updateLogFile(time, userID, babyID, "Add Glucose Concentration",Double.toString(value));
    }

    public void addSkinConcentration(String userID, String babyID, double current, double concentration, String time)
    {
        /*
            Add both skin current/concentration reading with timestamp for the baby
        with the matched hospital number and update the log file

         input:
            userID: String, the unique ID of the user who performed the action
            babyID: String, the unique ID of the baby who is monitored
            current: double, the skin current reading from the sensor directly
            concentration: double, the skin concentration reading after skin current calibration
            time: String, the time which has taken lag time into account
         */
        babyList.get(babyID).addSkinConcentration(current, concentration, time);
        updateLogFile(time, userID, babyID, "Add Skin Current/Concentration", current +"/"+ concentration);
    }

    public void addEvent(String userID, String babyID, String detail, String time)
    {
        /*
            Add an event with timestamp for the baby with the matched hospital number and update the log file

         input:
            userID: String, the unique ID of the user who performed the action
            babyID: String, the unique ID of the baby who is monitored
            detail: String, information about the event which might influence the concentration measurement
            time: String, the time at which the user performed the action
         */
        babyList.get(babyID).addEvent(detail,time);
        updateLogFile(time, userID, babyID, "Add Glucose Concentration",detail);
    }

    public boolean checkGlucoseConcentrationPermission(String babyID, double value)
    {
        /*
            Verify whether the user could modify this specific glucose concentration value
        by calculating the time difference between the current time and the input time

        input:
            value: double, the target value that the user is asking permission for

        return:
            result: true if the user could modify this value, false otherwise
         */
        return false;
    }


    public String formatTime(String minute)
    {
        /*
            Return the modified time in the format "yyyy/MM/dd HH:mm:ss"
            Return the current local time if given "0"

        input:
            minute: String, the time difference between the target time and the current time

        return:
            time: String, the target time in the format "yyyy/MM/dd HH:mm:ss"
         */
        LocalDateTime time=LocalDateTime.now().minusMinutes(Long.parseLong(minute));
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return formatter.format(time);
    }




}
