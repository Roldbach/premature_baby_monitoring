package UI;

import javax.swing.*;

class ManageAccountPanel extends GeneralPanel
{
    private JButton mainButton;

    protected ManageAccountPanel()
    {
        /*
            Initiate the change value panel given the UI design

            This pages requires following components:
            (1) label 1: JLabel, display the current user ID
            (2) label_2: JLabel, display "User ID: "
            (3) label_3: JLabel, display "Password: "
            (4) button_1: JButton, display "Log out"
            (5) button_2: JButton, display "Back"
            (6) button_3: JButton, display "Add"
            (7) button_4: JButton, display "Delete"
            (8) mainButton: JButton, display "Main"
         */
        label_1=setLabel("User ID: ",880,50,80,14,false);
        label_2=setLabel("User ID: ",314,248,45,14,false);
        label_3=setLabel("Password: ",314,328,57,14,false);

        button_1=setButton("Log out",872,76,84,36,true);
        button_2=setButton("Back",44,44,69,26,true);
        button_3=setButton("Add",394,430,84,36,true);
        button_4=setButton("Delete",522,430,84,36,true);
        mainButton=setButton("Main",135,44,69,26,true);

        textField_1=setTextField(314,270,372,36);
        textField_2=setTextField(314,350,372,36);
    }

    protected JButton getMainButton()
    {
        /*
            Return the main button to the controller

        return:
            mainButton: JButton, requires action listener to change page
         */
        return mainButton;
    }

}
