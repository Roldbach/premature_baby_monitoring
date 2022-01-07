package UI;

import javax.swing.*;
import java.awt.*;

class ChangeBabyPanel extends GeneralPanel
{
    protected ChangeBabyPanel()
    {
        /*
            Initiate the change baby panel given the UI design

            This pages requires following components:
            (1) label 1: JLabel, display the current user ID
            (2) label 2: JLabel, display the current baby ID
            (3) label 3: JLabel, display "New ID: "
            (4) button_1: JButton, display "Log out"
            (5) button_2: JButton, display "Back"
            (6) button_3: JButton, display "Confirm"
            (7) textField 1: JTextField, enable user to input new baby ID
         */
        setLayout(new BorderLayout());
        label_1=new JLabel("User ID: ");
        label_1.setFont(new Font("Arial",Font.PLAIN,16));
        label_2=new JLabel("Baby ID: ");
        label_2.setFont(new Font("Arial",Font.PLAIN,16));
        label_3=new JLabel("New Baby ID: ");
        label_3.setFont(new Font("Arial",Font.PLAIN,16));

        button_1=new JButton("Log out");
        button_1.setFont(new Font("Arial",Font.BOLD,16));
        button_2=new JButton("Back");
        button_2.setFont(new Font("Arial",Font.BOLD,16));
        button_3=new JButton("Confirm");
        button_3.setFont(new Font("Arial",Font.BOLD,16));

        textField_1=new JTextField();
        textField_1.setFont(new Font("Arial",Font.PLAIN,16));
        //Set the panel for labels and buttons
        JPanel userPanel=new JPanel(new FlowLayout(FlowLayout.TRAILING,44,0));
        userPanel.add(label_1);

        JPanel babyPanel=new JPanel(new FlowLayout(FlowLayout.TRAILING,44,0));
        babyPanel.add(label_2);

        JPanel babyPanel_2=new JPanel(new GridLayout(1,2));
        babyPanel_2.add(label_3);
        babyPanel_2.add(new JLabel(""));

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
        northPanel.add(babyPanel);
        northPanel.add(buttonPanel_1);
        northPanel.add(new JLabel(""));
        northPanel.add(new JLabel(""));
        northPanel.add(new JLabel(""));
        northPanel.add(new JLabel(""));
        //Set the content panel which display label 3, text field 1 and button 3
        JPanel middleContentPanel=new JPanel();
        BoxLayout middleContentLayout=new BoxLayout(middleContentPanel,BoxLayout.Y_AXIS);
        middleContentPanel.setLayout(middleContentLayout);
        //Add components into the content panel and set some fixed spaces between them
        middleContentPanel.add(Box.createVerticalStrut(100));
        middleContentPanel.add(babyPanel_2);
        middleContentPanel.add(Box.createRigidArea(new Dimension(0,10)));
        middleContentPanel.add(textField_1);
        middleContentPanel.add(Box.createRigidArea(new Dimension(0,25)));
        middleContentPanel.add(buttonPanel_3);
        middleContentPanel.add(Box.createVerticalStrut(275));
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
