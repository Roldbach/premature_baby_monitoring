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
            (1) label_1: JLabel, display the current user ID
            (2) label_2: JLabel, display the current baby ID
            (3) label_3: JLabel, display "Glucose Concentration: "
            (4) label_4: JLabel, display "Event: "
            (5) button_1: JButton, display "Log out"
            (6) button_2: JButton, display "Back"
            (7) button_3: JButton, display "Add"
            (8) radioButton_1: JRadioButton, display "Current"
            (9) radioButton_2: JRadioButton, display "5min Ago"
            (10) radioButton_3: JRadioButton, display "10min Ago"
            (11) textField_1: JTextField, enable user to input glucose concentration value
            (12) textField_2: JTextField, enable user to input event detail
         */
        label_1=setLabel("User ID: ",false);
        label_2=setLabel("Baby ID: ",false);
        label_3=setLabel("Glucose Concentration: ",false);
        label_4=setLabel("Event: ",false);

        button_1=setButton("Log out",true);
        button_2=setButton("Back",true);
        button_3=setButton("Add",true);

        ButtonGroup group=new ButtonGroup();
        radioButton_1=setRadioButton(group,"Current",false);
        radioButton_2=setRadioButton(group,"5 min ago",false);
        radioButton_3=setRadioButton(group,"10 min ago",false);

        textField_1=setTextField();
        textField_2=setTextField();
        //Set the panel for labels and buttons
        JPanel userPanel=new JPanel(new FlowLayout(FlowLayout.TRAILING,44,0));
        userPanel.add(label_1);

        JPanel babyPanel=new JPanel(new FlowLayout(FlowLayout.TRAILING,44,0));
        babyPanel.add(label_2);

        JPanel labelPanel_1=new JPanel(new GridLayout(1,2));
        labelPanel_1.add(label_3);
        labelPanel_1.add(new JLabel(""));

        JPanel labelPanel_2=new JPanel(new GridLayout(1,2));
        labelPanel_2.add(label_4);
        labelPanel_2.add(new JLabel(""));

        JPanel buttonPanel_1=new JPanel(new FlowLayout(FlowLayout.TRAILING,44,0));
        buttonPanel_1.add(button_1);

        JPanel buttonPanel_2=new JPanel(new FlowLayout(FlowLayout.LEADING,44,0));
        buttonPanel_2.add(button_2);

        JPanel buttonPanel_3=new JPanel(new GridLayout(1,5));
        buttonPanel_3.add(new JLabel(""));
        buttonPanel_3.add(new JLabel(""));
        buttonPanel_3.add(button_3);
        buttonPanel_3.add(new JLabel(""));
        buttonPanel_3.add(new JLabel(""));

        JPanel radioButtonPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,25,0));
        radioButtonPanel.add(radioButton_1);
        radioButtonPanel.add(radioButton_2);
        radioButtonPanel.add(radioButton_3);
        //Set the north panel in the border layout
        JPanel northPanel=new JPanel(new GridLayout(9,1));
        northPanel.add(new JLabel(""));
        northPanel.add(buttonPanel_2);
        northPanel.add(userPanel);
        northPanel.add(babyPanel);
        northPanel.add(buttonPanel_1);
        northPanel.add(new JLabel(""));
        northPanel.add(new JLabel(""));
        northPanel.add(new JLabel(""));
        northPanel.add(new JLabel(""));
        //Set the content panel which display labels, buttons, radio buttons and text field at the central area
        JPanel middleContentPanel=new JPanel();
        BoxLayout middleContentLayout=new BoxLayout(middleContentPanel,BoxLayout.Y_AXIS);
        middleContentPanel.setLayout(middleContentLayout);
        //Add components into the content panel and set some fixed spaces between them
        middleContentPanel.add(Box.createVerticalStrut(50));
        middleContentPanel.add(labelPanel_1);
        middleContentPanel.add(Box.createRigidArea(new Dimension(0,10)));
        middleContentPanel.add(textField_1);
        middleContentPanel.add(Box.createRigidArea(new Dimension(0,50)));
        middleContentPanel.add(labelPanel_2);
        middleContentPanel.add(Box.createRigidArea(new Dimension(0,10)));
        middleContentPanel.add(textField_2);
        middleContentPanel.add(Box.createRigidArea(new Dimension(0,15)));
        middleContentPanel.add(radioButtonPanel);
        middleContentPanel.add(Box.createRigidArea(new Dimension(0,15)));
        middleContentPanel.add(buttonPanel_3);
        middleContentPanel.add(Box.createVerticalStrut(180));
        //Set the center panel as a box layout containing 3 parts horizontally
        JPanel middlePanel=new JPanel();
        BoxLayout middleLayout=new BoxLayout(middlePanel,BoxLayout.X_AXIS);
        middlePanel.setLayout(middleLayout);
        middlePanel.add(Box.createRigidArea(new Dimension(275,0)));
        middlePanel.add(middleContentPanel);
        middlePanel.add(Box.createRigidArea(new Dimension(275,0)));
        //Add panels to the login page
        add(northPanel,BorderLayout.NORTH);
        add(middlePanel,BorderLayout.CENTER);
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
