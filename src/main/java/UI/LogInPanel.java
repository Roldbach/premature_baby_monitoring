package UI;

import javax.swing.*;

class LogInPanel extends GeneralPanel
{
    protected LogInPanel()
    {
        /*
            Initiate the login panel given the UI design

            This pages requires following components:
            (1) label 1: JLabel, display the name of the App
            (2) label 2: JLabel, display "User ID: " in front of the text field
            (3) label 3: JLabel, display "Password" in front of the text field
            (4) button_1: JButton, display "Log in"
            (5) button_2: JButton, display "Quit"
            (6) textField 1: JTextField, enable user to input ID
            (7) textField 2: JTextField, enable user to input password
         */
        label_1=setLabel("Premature Baby App",500,500,100,100);
        label_2=setLabel("User ID: ",400,400,50,50);
        label_3=setLabel("Password: ", 450,450,50,50);
        button_1=setButton("Log in",600,600,100,50);
        button_2=setButton("Quit",650,650,100,50);
        textField_1=setTextField(50,50,50,50);
        textField_2=setTextField(150,150,50,50);

        //Action listener for quit button
        button_2.addActionListener(e-> System.exit(0));
    }

}
