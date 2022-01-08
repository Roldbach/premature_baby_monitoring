package UI;

import javax.swing.*;
import java.awt.*;

class ChangeValuePanel extends GeneralPanel
{
    protected ChangeValuePanel()
    {
        /*
            Initiate the change value panel given the UI design

            This pages requires following components:
            (1) label_1: JLabel, display the current user ID
            (2) label_2: JLabel, display the current baby ID
            (3) button_1: JButton, display "Log out"
            (4) button_2: JButton, display "Back"]
            (5) radiobutton_1: JRadioButton, display "Change Mode"
            (6) radioButton_2: JRadioButton, display "Delete Mode"
            (7) table_1: JTable, display glucose concentration with timestamp
            (8) table_2: JTable, display skin current, concentration with timestamp
            (9) table_3: JTable, display detailed event with timestamp
         */
        label_1=setLabel("User ID: ",false);
        label_2=setLabel("Baby ID: ",false);

        button_1=setButton("Log out",true);
        button_2=setButton("Back",true);

        ButtonGroup group=new ButtonGroup();
        radioButton_1=setRadioButton(group,"Change Mode",false);
        radioButton_2=setRadioButton(group,"Delete Mode",false);

        table_1=setTable();
        JScrollPane scrollPane_1=new JScrollPane();
        scrollPane_1.setViewportView(table_1);

        table_2=setTable();
        JScrollPane scrollPane_2=new JScrollPane();
        scrollPane_2.setViewportView(table_2);

        table_3=setTable();
        JScrollPane scrollPane_3=new JScrollPane();
        scrollPane_3.setViewportView(table_3);
        //Set the panel for labels and buttons
        JPanel userPanel=new JPanel(new FlowLayout(FlowLayout.TRAILING,44,0));
        userPanel.add(label_1);

        JPanel babyPanel=new JPanel(new FlowLayout(FlowLayout.TRAILING,44,0));
        babyPanel.add(label_2);

        JPanel buttonPanel_1=new JPanel(new FlowLayout(FlowLayout.TRAILING,44,0));
        buttonPanel_1.add(button_1);

        JPanel buttonPanel_2=new JPanel(new FlowLayout(FlowLayout.LEADING,44,0));
        buttonPanel_2.add(button_2);

        JPanel radioButtonPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,200,0));
        radioButtonPanel.add(radioButton_1);
        radioButtonPanel.add(radioButton_2);
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
        //Set the center panel as a box layout containing 3 tables horizontally
        JPanel middlePanel=new JPanel();
        BoxLayout middleLayout=new BoxLayout(middlePanel,BoxLayout.X_AXIS);
        middlePanel.setLayout(middleLayout);
        middlePanel.add(Box.createHorizontalStrut(1));
        middlePanel.add(scrollPane_1);
        middlePanel.add(scrollPane_2);
        middlePanel.add(scrollPane_3);
        middlePanel.add(Box.createHorizontalStrut(1));
        //Set the south panel to display the radio buttons
        JPanel southPanel=new JPanel(new GridLayout(3,1));
        southPanel.add(new JLabel(""));
        southPanel.add(radioButtonPanel);
        southPanel.add(new JLabel(""));
        //Add panels to the change value page
        add(northPanel,BorderLayout.NORTH);
        add(middlePanel,BorderLayout.CENTER);
        add(southPanel,BorderLayout.SOUTH);
        //Set the default selection to the radio button 1
        radioButton_1.setSelected(true);
    }
}
