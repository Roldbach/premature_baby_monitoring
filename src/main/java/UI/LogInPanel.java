package UI;

import javax.swing.*;
import java.awt.*;

class LogInPanel extends GeneralPanel
{
    protected LogInPanel()
    {
        /*
            Initiate the login panel given the UI design

            This pages requires following components:
            (1) label 1: JLabel, display the name of the App
            (2) label 2: JLabel, display "User ID: "
            (3) label 3: JLabel, display "Password: "
            (4) button_1: JButton, display "Log in"
            (5) button_2: JButton, display "Quit"
            (6) textField 1: JTextField, enable user to input user ID
            (7) textField 2: JTextField, enable user to input password
         */
        label_1=setLabel("Premature Baby App",391,178,218,26, true);
        label_1.setFont(new Font("Arial",Font.BOLD,22));

        label_2=setLabel("User ID: ",314,248,45,14,false);
        label_3=setLabel("Password: ", 314,328,57,14,false);
        button_1=setButton("Log in",394,430,84,36,true);
        button_2=setButton("Quit",522,430,84,36,true);

        textField_1=setTextField(314,270,372,36);
        textField_2=setTextField(314,350,372,36);
    }
}
