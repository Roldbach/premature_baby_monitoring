package UI;

import javax.swing.*;

class ChangeBabyPanel extends GeneralPanel
{
    protected ChangeBabyPanel()
    {
        /*
            Initiate the change baby panel given the UI design

            This pages requires following components:
            (1) label 1: JLabel, display the current user ID
            (2) label 2: JLabel, display the current baby ID
            (3) label 3: JLabel, display "New ID: "
            (4) button_1: JButton, display "Log out"
            (5) button_2: JButton, display "Back"
            (6) button_3: JButton, display "Confirm"
            (7) textField 1: JTextField, enable user to input new baby ID

            ### No "Main" Button needed in this page ###
         */
        label_1=setLabel("User ID: ",880,50,80,14,false);
        label_2=setLabel("Baby ID: ",880,76,80,14,false);
        label_3=setLabel("New ID: ",314,328,42,14,false);
        button_1=setButton("Log out",872,102,84,36,true);
        button_2=setButton("Back",44,44,69,26,true);
        button_3=setButton("Confirm",458,430,84,36,true);
        textField_1=setTextField(314,350,372,36);
    }
}
