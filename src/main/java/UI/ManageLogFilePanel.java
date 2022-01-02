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
            (5) logFileTable: JTable, display detailed modification to the database
         */
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
