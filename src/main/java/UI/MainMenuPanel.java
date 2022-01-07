package UI;

import javax.swing.*;
import java.awt.*;

class MainMenuPanel extends GeneralPanel
{
    private JButton addButton;
    private JButton changeValueButton;
    private JButton plotButton;
    private JButton changePasswordButton;
    private JButton entryButton;

    protected MainMenuPanel()
    {
        /*
            Initiate the main menu panel given the UI design

            This pages requires following components:
            (1) label 1: JLabel, display the current user ID
            (2) label 2: JLabel, display the current baby ID
            (3) button_1: JButton, display "Change Baby"
            (4) button_2: JButton, display "Log out"
            (5) addButton: JButton, display "Add Value"
            (6) changeValueButton: JButton, display "Change Value"
            (7) plotButton: JButton, display "Plot Graph"
            (8) changePasswordButton: JButton, display "Change Password"
            (9) entryButton: JButton, display "Administrator Entry"
         */
        setLayout(new BorderLayout());
        label_1=new JLabel("User ID: ");
        label_1.setFont(new Font("Arial",Font.PLAIN,16));
        label_2=new JLabel("Baby ID: ");
        label_2.setFont(new Font("Arial",Font.PLAIN,16));

        button_1=new JButton("Change Baby");
        button_1.setFont(new Font("Arial",Font.BOLD,16));
        button_2=new JButton("Log out");
        button_2.setFont(new Font("Arial",Font.BOLD,16));

        addButton=new JButton("Add Value");
        addButton.setFont(new Font("Arial",Font.BOLD,12));
        changeValueButton=new JButton("Change Value");
        changeValueButton.setFont(new Font("Arial",Font.BOLD,12));
        plotButton=new JButton("Plot Graphs");
        plotButton.setFont(new Font("Arial",Font.BOLD,12));
        changePasswordButton=new JButton("Change Password");
        changePasswordButton.setFont(new Font("Arial",Font.BOLD,12));
        entryButton=new JButton("Administrator Entry");
        entryButton.setFont(new Font("Arial",Font.BOLD,12));

        //Set the panel for labels and buttons
        JPanel userPanel=new JPanel(new FlowLayout(FlowLayout.TRAILING,44,0));
        userPanel.add(label_1);

        JPanel babyPanel=new JPanel(new FlowLayout(FlowLayout.TRAILING,44,0));
        babyPanel.add(label_2);

        JPanel buttonPanel_1=new JPanel(new FlowLayout(FlowLayout.TRAILING,44,0));
        buttonPanel_1.add(button_1);

        JPanel buttonPanel_2=new JPanel(new FlowLayout(FlowLayout.TRAILING,44,0));
        buttonPanel_2.add(button_2);
        //Set the north panel in the border layout
        JPanel northPanel=new JPanel(new GridLayout(9,1));
        northPanel.add(new JLabel(""));
        northPanel.add(new JLabel(""));
        northPanel.add(userPanel);
        northPanel.add(babyPanel);
        northPanel.add(buttonPanel_1);
        northPanel.add(buttonPanel_2);
        northPanel.add(new JLabel(""));
        northPanel.add(new JLabel(""));
        northPanel.add(new JLabel(""));
        //Set the content panel which display buttons at a preferred shape and layout
        JPanel contentPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,25,0));
        addButton.setPreferredSize(new Dimension(150,150));
        changeValueButton.setPreferredSize(new Dimension(150,150));
        plotButton.setPreferredSize(new Dimension(150,150));
        changePasswordButton.setPreferredSize(new Dimension(150,150));
        entryButton.setPreferredSize(new Dimension(150,150));
        contentPanel.add(addButton);
        contentPanel.add(changeValueButton);
        contentPanel.add(plotButton);
        contentPanel.add(changePasswordButton);
        contentPanel.add(entryButton);
        //Set the central panel to display content at the central area
        JPanel centralPanel=new JPanel(new GridLayout(3,1));
        centralPanel.add(new JLabel(""));
        centralPanel.add(contentPanel);
        centralPanel.add(new JLabel(""));
        //Add panels to the login page
        add(northPanel,BorderLayout.NORTH);
        add(centralPanel,BorderLayout.CENTER);
    }

    protected JButton getAddButton()
    {
        /*
            Return the add button to the controller

        return:
            addButton: JButton, requires action listener to change page
         */
        return addButton;
    }

    protected JButton getChangeValueButton()
    {
        /*
            Return the change value button to the controller

        return:
            changeValueButton: JButton, requires action listener to change page
         */
        return changeValueButton;
    }

    protected JButton getPlotButton()
    {
        /*
            Return the plot button to the controller

        return:
            plotButton: JButton， requires action listener to change page
         */
        return plotButton;
    }

    protected JButton getChangePasswordButton()
    {
        /*
            Return the change password button to the controller

        return:
            changePasswordButton: JButton, requires action listener to change page
         */
        return changePasswordButton;

    }

    protected JButton getEntryButton()
    {
        /*
            Return the entry button to the controller

        return:
            entryButton: JButton, requires action listener to change page
         */
        return entryButton;
    }
}
