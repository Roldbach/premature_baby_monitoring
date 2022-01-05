package UI;

import javax.swing.*;

class ManageLogFilePanel extends GeneralPanel
{
    private JTable logFileTable;

    protected ManageLogFilePanel()
    {
        /*
            Initiate the change value panel given the UI design

            This pages requires following components:
            (1) label 1: JLabel, display the current user ID
            (2) button_1: JButton, display "Log out"
            (3) button_2: JButton, display "Back"
            (4) button_3: JButton, display "Main"
            (5) table_1: JTable, display detailed modification to the database
         */
        label_1=setLabel("User: ",880,50,80,14,false);
        button_1=setButton("Log out",872,76,84,36,true);
        button_2=setButton("Back",44,44,69,26,true);
        button_3=setButton("Main",135,44,69,26,true);
    }

    protected JTable getLogFileTable()
    {
        /*
            Return the log file table to the controller

        return:
            logFileTable: JTable, the table which displays detailed modification to the database
         */
        return logFileTable;
    }

}
