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
        setLayout(new BorderLayout());
        label_1=new JLabel("User ID: ");
        label_1.setFont(new Font("Arial",Font.PLAIN,16));
        label_2=new JLabel("Baby ID: ");
        label_2.setFont(new Font("Arial",Font.PLAIN,16));
        label_3=new JLabel("Glucose Concentration: ");
        label_3.setFont(new Font("Arial",Font.PLAIN,16));
        label_4=new JLabel("Event: ");
        label_4.setFont(new Font("Arial",Font.PLAIN,16));

        button_1=new JButton("Log out");
        button_1.setFont(new Font("Arial",Font.BOLD,16));
        button_2=new JButton("Back");
        button_2.setFont(new Font("Arial",Font.BOLD,16));
        button_3=new JButton("Add");
        button_3.setFont(new Font("Arial",Font.BOLD,16));

        radioButton_1=new JRadioButton("Current");
        radioButton_1.setFont(new Font("Arial",Font.PLAIN,16));
        radioButton_2=new JRadioButton("5 minute ago");
        radioButton_2.setFont(new Font("Arial",Font.PLAIN,16));
        radioButton_3=new JRadioButton("10 minute ago");
        radioButton_3.setFont(new Font("Arial",Font.PLAIN,16));
        ButtonGroup group=new ButtonGroup();
        group.add(radioButton_1);
        group.add(radioButton_2);
        group.add(radioButton_3);

        textField_1=new JTextField();
        textField_1.setFont(new Font("Arial",Font.PLAIN,16));
        textField_2=new JTextField();
        textField_2.setFont(new Font("Arial",Font.PLAIN,16));
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
