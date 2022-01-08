package UI;

import javax.swing.*;
import java.awt.*;

class ChangePasswordPanel extends GeneralPanel
{
    protected ChangePasswordPanel()
    {
        /*
            Initiate the change value panel given the UI design

            This pages requires following components:
            (1) label 1: JLabel, display the current user ID
            (2) label 2: JLabel, display "User ID: "
            (3) label 3: JLabel, display "New Password: "
            (4) button_1: JButton, display "Log out"
            (5) button_2: JButton, display "Back"
            (6) button_3: JButton, display "Confirm"
            (7) textField 1: JTextField, enable user to input user ID
            (8) textField 2: JTextField, enable user to input new password
         */
        label_1=setLabel("User ID: ",false);
        label_2=setLabel("User ID: ",false);
        label_3=setLabel("New Passwrord",false);

        button_1=setButton("Log out",true);
        button_2=setButton("Back",true);
        button_3=setButton("Confirm",true);

        textField_1=setTextField();
        textField_2=setTextField();
        //Set the panel for labels and buttons
        JPanel userPanel=new JPanel(new FlowLayout(FlowLayout.TRAILING,44,0));
        userPanel.add(label_1);

        JPanel labelPanel_1=new JPanel(new GridLayout(1,2));
        labelPanel_1.add(label_2);
        labelPanel_1.add(new JLabel(""));

        JPanel labelPanel_2=new JPanel(new GridLayout(1,2));
        labelPanel_2.add(label_3);
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
        //Set the north panel in the border layout
        JPanel northPanel=new JPanel(new GridLayout(9,1));
        northPanel.add(new JLabel(""));
        northPanel.add(buttonPanel_2);
        northPanel.add(userPanel);
        northPanel.add(buttonPanel_1);
        northPanel.add(new JLabel(""));
        northPanel.add(new JLabel(""));
        northPanel.add(new JLabel(""));
        northPanel.add(new JLabel(""));
        northPanel.add(new JLabel(""));
        //Set the content panel which display labels, buttons and text field at the central area
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
        middleContentPanel.add(Box.createRigidArea(new Dimension(0,30)));
        middleContentPanel.add(buttonPanel_3);
        middleContentPanel.add(Box.createVerticalStrut(210));
        //Set the center panel as a box layout containing 3 parts horizontally
        JPanel middlePanel=new JPanel();
        BoxLayout middleLayout=new BoxLayout(middlePanel,BoxLayout.X_AXIS);
        middlePanel.setLayout(middleLayout);
        middlePanel.add(Box.createRigidArea(new Dimension(275,0)));
        middlePanel.add(middleContentPanel);
        middlePanel.add(Box.createRigidArea(new Dimension(275,0)));
        //Add panels to the change password page
        add(northPanel,BorderLayout.NORTH);
        add(middlePanel,BorderLayout.CENTER);
    }

}
