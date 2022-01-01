package UI;

import javax.swing.*;
import java.awt.*;

class AddValuePanel extends GeneralPanel
{
    private JRadioButton optionButton_1;
    private JRadioButton optionButton_2;
    private JRadioButton optionButton_3;

    protected AddValuePanel()
    {
        /*
            Initiate the add value panel given the UI design

            This pages requires following components:
            (1) label 1: JLabel, display the current user ID
            (2) label 2: JLabel, display the current baby ID
            (3) label 3: JLabel, display "Glucose Concentration" in front of the text field 1
            (4) label 4: JLabel, display "Event" in front of the text field 2
            (5) button_1: JButton, display "Log out"
            (6) button_2: JButton, display "Back"
            (7) button_3: JButton, display "Add"
            (8) textField 1: JTextField, enable user to input glucose concentration value
            (9) textField 2: JTextField, enable user to input event detail
         */
        ButtonGroup group=new ButtonGroup();
    }

    protected String getDelay()
    {
        /*
            Return the delay option the user chose to calibrate the timestamp

            By default, the user have 3 options: current, 5 minute ago, 10 minute ago

        return:
            delay: String, the approximate time difference between the measurement time and the input time
         */
        if (optionButton_1.isSelected()) {return "0";}
        else if (optionButton_2.isSelected()) {return "5";}
        else {return "10";}
    }
}
