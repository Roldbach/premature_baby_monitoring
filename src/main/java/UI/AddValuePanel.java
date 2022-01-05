package UI;

import javax.swing.*;
import java.awt.*;

class AddValuePanel extends GeneralPanel
{
    protected AddValuePanel()
    {
        /*
            Initiate the add value panel given the UI design

            This pages requires following components:
            (1) label 1: JLabel, display the current user ID
            (2) label 2: JLabel, display the current baby ID
            (3) label 3: JLabel, display "Glucose Concentration: "
            (4) label 4: JLabel, display "Event: "
            (5) button_1: JButton, display "Log out"
            (6) button_2: JButton, display "Back"
            (7) button_3: JButton, display "Add"
            (8) radioButton_1: JRadioButton, display "Current"
            (9) radioButton_2: JRadioButton, display "5min Ago"
            (10) radioButton_3: JRadioButton, display "10min Ago"
            (11) textField 1: JTextField, enable user to input glucose concentration value
            (12) textField 2: JTextField, enable user to input event detail
         */
        label_1=setLabel("User ID: ",880,50,80,14,false);
        label_2=setLabel("Baby ID: ",880,76,80,14,false);
        label_3=setLabel("Glucose Concentration: ",314,248,126,14,false);
        label_4=setLabel("Event: ",314,328,35,14,false);

        button_1=setButton("Log out",872,102,84,36,true);
        button_2=setButton("Back",44,44,69,26,true);
        button_3=setButton("Add",456,466,84,36,true);

        textField_1=setTextField(314,270,372,36);
        textField_2=setTextField(314,350,372,36);

        ButtonGroup group=new ButtonGroup();
        radioButton_1=setRadioButton(group,"Current",382,408,50,14,false);
        radioButton_2=setRadioButton(group,"5min Ago",486,408,62,14,false);
        radioButton_3=setRadioButton(group,"10min Ago",602,408,69,14,false);
    }

    protected String getDelay()
    {
        /*
            Return the delay option the user chose to calibrate the timestamp

            By default, the user have 3 options: current, 5 minute ago, 10 minute ago

        return:
            delay: String, the approximate time difference between the measurement time and the input time
         */
        if (radioButton_1.isSelected()) {return "0";}
        else if (radioButton_2.isSelected()) {return "5";}
        else {return "10";}
    }
}
