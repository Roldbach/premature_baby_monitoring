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
        setLayout(new BorderLayout());
        label_1=new JLabel("Premature Baby Monitoring App");
        label_1.setFont(new Font("Arial",Font.BOLD,22));
        label_1.setHorizontalAlignment(JLabel.CENTER);
        label_2=new JLabel("User ID: ");
        label_2.setFont(new Font("Arial",Font.PLAIN,16));
        label_3=new JLabel("Password: ");
        label_3.setFont(new Font("Arial",Font.PLAIN,16));

        button_1=new JButton("Log in");
        button_1.setFont(new Font("Arial",Font.BOLD,16));
        button_2=new JButton("Quit");
        button_2.setFont(new Font("Arial",Font.BOLD,16));

        textField_1=new JTextField();
        textField_1.setFont(new Font("Arial",Font.PLAIN,16));
        textField_2=new JTextField();
        textField_2.setFont(new Font("Arial",Font.PLAIN,16));

        //Set the north panel in the border layout
        JPanel northPanel=new JPanel(new GridLayout(9,1));
        for (int i=0;i<8;i++) {        northPanel.add(new JLabel(""));}
        northPanel.add(label_1);

        //Set the panel for labels and buttons
        JPanel userPanel=new JPanel(new GridLayout(1,2));
        userPanel.add(label_2);
        userPanel.add(new JLabel(""));

        JPanel babyPanel=new JPanel(new GridLayout(1,2));
        babyPanel.add(label_3);
        babyPanel.add(new JLabel(""));

        JPanel buttonPanel=new JPanel(new GridLayout(1,5));
        buttonPanel.add(new JLabel(""));
        buttonPanel.add(button_1);
        buttonPanel.add(new JLabel(""));
        buttonPanel.add(button_2);
        buttonPanel.add(new JLabel(""));
        //Set the content panel which display labels, buttons and text field at the central area
        JPanel middleContentPanel=new JPanel();
        BoxLayout middleContentLayout=new BoxLayout(middleContentPanel,BoxLayout.Y_AXIS);
        middleContentPanel.setLayout(middleContentLayout);
        //Add components into the content panel and set some fixed spaces between them
        middleContentPanel.add(Box.createVerticalStrut(50));
        middleContentPanel.add(userPanel);
        middleContentPanel.add(Box.createRigidArea(new Dimension(0,10)));
        middleContentPanel.add(textField_1);
        middleContentPanel.add(Box.createRigidArea(new Dimension(0,50)));
        middleContentPanel.add(babyPanel);
        middleContentPanel.add(Box.createRigidArea(new Dimension(0,10)));
        middleContentPanel.add(textField_2);
        middleContentPanel.add(Box.createRigidArea(new Dimension(0,25)));
        middleContentPanel.add(buttonPanel);
        middleContentPanel.add(Box.createVerticalStrut(250));
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
}
