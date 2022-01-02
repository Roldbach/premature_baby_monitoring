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
            (2) button_1: JButton, display "Log out"
            (3) button_2: JButton, display "Back"
            (4) button_3: JButton, display "Add"
            (5) button_4: JButton, display "Delete"
            (6) mainButton: JButton, display "Main"
         */

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
