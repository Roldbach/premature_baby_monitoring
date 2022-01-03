package UI;

import javax.swing.*;

class ChangeValuePanel extends GeneralPanel
{
    private JTable glucoseTable;
    private JTable skinTable;
    private  JTable eventTable;

    protected ChangeValuePanel()
    {
        /*
            Initiate the change value panel given the UI design

            This pages requires following components:
            (1) label 1: JLabel, display the current user ID
            (2) label 2: JLabel, display the current baby ID
            (3) button_1: JButton, display "Log out"
            (4) button_2: JButton, display "Back"
            (5) glucoseTable: JTable, display glucose concentration with timestamp
            (6) skinTable: JTable, display skin current, concentration with timestamp
            (7) eventTable: JTable, display detailed event with timestamp
         */
        label_1=setLabel("User ID: ",880,50,80,14,false);
        label_2=setLabel("Baby ID: ",880,76,80,14,false);

        button_1=setButton("Log out",872,102,84,36,true);
        button_2=setButton("Back",44,44,69,26,true);

    }

    protected JTable getGlucoseTable()
    {
        /*
            Return the glucose table to the controller

        return:
            glucoseTable: JTable, the table which displays glucose concentration with timestamp
         */
        return glucoseTable;
    }

    protected JTable getSkinTable()
    {
        /*
            Return the skin table to the controller

        return:
            skinTable: JTable, the table which displays skin current/concentration with timestamp
         */
        return skinTable;
    }

    protected JTable eventTable()
    {
        /*
            Return the event table to the controller

        return:
            eventTable: JTable, the table which displays detailed event with timestamp
         */
        return eventTable;
    }
}
