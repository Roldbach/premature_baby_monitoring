import java.util.LinkedHashMap;

public class Baby {
    private String ID;
    private LinkedHashMap<String, Double> glucoseConcentration;
    private LinkedHashMap<String, Double> skinCurrent;
    private LinkedHashMap<String, Double> skinConcentration;
    private LinkedHashMap<String, String> event;

    public Baby(String hospitalNumber)
    {
        /*
            Initiate the class Baby with given hospital number

        input:
            hospitalNumber: String, the unique hospital number for each baby
         */
        ID=hospitalNumber;
        glucoseConcentration=new LinkedHashMap<>();
        skinCurrent=new LinkedHashMap<>();
        skinConcentration=new LinkedHashMap<>();
        event=new LinkedHashMap<>();
    }

    public Baby(String path, boolean action)
    {
        /*
            Initiate the class Baby with given file path. The file is automatically loaded

        input:
            path: String, the file path which contains the information about the baby
            action: boolean, true if the String parameter represents a file path
         */
    }

    public String getID()
    {
        /*
            Return the hospital number of the baby

        return:
            ID: String, the unique hospital number for each baby
         */
        return ID;
    }

    public LinkedHashMap<String,Double> getGlucoseConcentration()
    {
        /*
            Return the glucose concentration data with timestamp of the baby for data processing and plotting

        return:
            glucoseConcentration: LinkedHashMap<String,Double>, key=timestamp, value=concentration with put-in order
         */
        return glucoseConcentration;
    }

    public LinkedHashMap<String,Double> getSkinCurrent()
    {
        /*
            Return the skin current data with timestamp of the baby for data processing and plotting

        return:
            skinCurrent: LinkedHashMap<String,Double>, key=timestamp, value=current with put-in order
         */
        return skinCurrent;
    }

    public LinkedHashMap<String,Double> getSkinConcentration()
    {
        /*
            Return the skin concentration data with timestamp of the baby for data processing and plotting

        return:
            skinConcentration: LinkedHashMap<String,Double>, key=timestamp, value=concentration with put-in order
         */
        return skinConcentration;
    }

    public LinkedHashMap<String,String> getEvent()
    {
        /*
            Return the event data with timestamp of the baby for data processing and plotting

        return:
            event: LinkedHashMap<String,String>, key=timestamp, value=event with put-in order
         */
        return event;
    }

    public void addGlucoseConcentration(double value, String time)
    {
        /*
            Add a glucose concentration reading for the baby with timestamp

         input:
            value: double, the glucose concentration reading
            time: String, the measurement time for this reading
         */
    }

    public void addSkinConcentration(double current, double concentration, String time)
    {
        /*
            Add both skin current/concentration reading for the baby with timestamp simultaneously
        The concentration data is obtained after skin current calibration

         input:
            current: double, the skin current reading from the sensor directly
            concentration: double, the skin concentration reading after skin current calibration
            time: String, the measurement time for this reading after lag time calibration
         */
    }

    public void addEvent(String event, String time)
    {
        /*
            Add an event for the baby with timestamp

         input:
            event: String, information about the event which might influence the concentration measurement
            time: String, the time for this event
         */
    }

    public void changeGlucoseConcentration(double oldValue, double newValue)
    {
        /*
            Change the glucose concentration reading which might be a typo or miss click

        input:
            oldValue: double, the old glucose concentration which requires modification
            newValue: double, the right glucose concentration that should be saved
        */
    }

    public void changeEvent(String oldEvent,String newEvent)
    {
        /*
            Change the event information which might be a typo or miss click

         input:
            oldEvent: String, the old event information which requires modification
            newEvent: String, the right event that should be saved
         */
    }

    public void changeGlucoseConcentrationTimestamp(String oldTime, String newTime)
    {
        /*
            Change the glucose concentration timestamp due to the difference between
        measurement time and input time

        input:
            oldTime: String, the old timestamp which requires modification
            newTime: String, the right timestamp that should be saved
        */
    }

    public void changeEventTimestamp(String oldTime, String newTime)
    {
        /*
            Change the event timestamp due to the difference between measurement
        time and input time

        input:
            oldTime: String, the old timestamp which requires modification
            newTime: String, the right timestamp that should be saved
        */
    }

    public void deleteGlucoseConcentration(double value)
    {
        /*
           Delete the glucose concentration with timestamp which might be a typo or miss click

         input:
            value: double, the target value which requires deletion
         */
    }

    public void deleteEvent(String event)
    {
        /*
           Delete the event with timestamp which might be a typo or miss click

         input:
            event: String, the target event which requires deletion
         */
    }

    public void loadBaby(String path)
    {
        /*
            Load the file which contains data for a specific baby

         input:
            path: String, the file path which contains data for a specific baby
         */
    }

    public void saveBaby(String path)
    {
        /*
            Save the data for a specific baby to the target file location

         input:
            path: String, the file path where the data could be saved
         */
    }
}
