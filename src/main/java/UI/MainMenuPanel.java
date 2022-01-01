package UI;

import javax.swing.*;

class MainMenuPanel extends GeneralPanel
{
    private JButton addButton;
    private JButton changeValueButton;
    private JButton plotButton;
    private JButton changePasswordButton;
    private JButton entryButton;

    protected MainMenuPanel()
    {
        /*
            Initiate the main menu panel given the UI design

            This pages requires following components:
            (1) label 1: JLabel, display the current user ID
            (2) label 2: JLabel, display the current baby ID
            (3) button_1: JButton, display "Change Baby"
            (4) button_2: JButton, display "Log out"
            (5) addButton: JButton, display "Add Value"
            (6) changeValueButton: JButton, display "Change Value"
            (7) plotButton: JButton, display "Plot Graph"
            (8) changePasswordButton: JButton, display "Change Password"
            (9) entryButton: JButton, display "Administrator Entry"
         */

    }

    protected JButton getAddButton()
    {
        /*
            Return the add button to the controller

        return:
            addButton: JButton, requires action listener to change page
         */
        return addButton;
    }

    protected JButton getChangeValueButton()
    {
        /*
            Return the change value button to the controller

        return:
            changeValueButton: JButton, requires action listener to change page
         */
        return changeValueButton;
    }

    protected JButton getPlotButton()
    {
        /*
            Return the plot button to the controller

        return:
            plotButton: JButton， requires action listener to change page
         */
        return plotButton;
    }

    protected JButton getChangePasswordButton()
    {
        /*
            Return the change password button to the controller

        return:
            changePasswordButton: JButton, requires action listener to change page
         */
        return changePasswordButton;

    }

    protected JButton getEntryButton()
    {
        /*
            Return the entry button to the controller

        return:
            entryButton: JButton, requires action listener to change page
         */
        return entryButton;
    }
}
