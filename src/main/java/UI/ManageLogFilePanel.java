package UI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

class ManageLogFilePanel extends GeneralPanel
{

    protected ManageLogFilePanel()
    {
        /*
            Initiate the change value panel given the UI design

            This pages requires following components:
            (1) label 1: JLabel, display the current user ID
            (2) button_1: JButton, display "Log out"
            (3) button_2: JButton, display "Back"
            (4) button_3: JButton, display "Main"
            (5) table_1: JTable, display detailed modification to the database
         */
        setLayout(new BorderLayout());
        label_1=new JLabel("User ID: ");
        label_1.setFont(new Font("Arial",Font.PLAIN,16));

        button_1=new JButton("Log out");
        button_1.setFont(new Font("Arial",Font.BOLD,16));
        button_2=new JButton("Back");
        button_2.setFont(new Font("Arial",Font.BOLD,16));
        button_3=new JButton("Main");
        button_3.setFont(new Font("Arial",Font.BOLD,16));

        table_1=new JTable(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table_1.setColumnSelectionAllowed(false);
        table_1.setRowSelectionAllowed(false);
        table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane=new JScrollPane();
        scrollPane.setViewportView(table_1);
        //Set the panel for labels and buttons
        JPanel userPanel=new JPanel(new FlowLayout(FlowLayout.TRAILING,44,0));
        userPanel.add(label_1);

        JPanel buttonPanel_1=new JPanel(new FlowLayout(FlowLayout.TRAILING,44,0));
        buttonPanel_1.add(button_1);

        JPanel buttonPanel_2=new JPanel(new FlowLayout(FlowLayout.LEADING,44,0));
        buttonPanel_2.add(button_2);
        buttonPanel_2.add(button_3);
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
        //Set the content panel which display the table
        JPanel middleContentPanel=new JPanel();
        BoxLayout middleContentLayout=new BoxLayout(middleContentPanel,BoxLayout.Y_AXIS);
        middleContentPanel.setLayout(middleContentLayout);
        //Add components into the content panel and set some fixed spaces between them
        middleContentPanel.add(Box.createVerticalStrut(50));
        middleContentPanel.add(scrollPane);
        middleContentPanel.add(Box.createVerticalStrut(200));
        //Set the center panel as a box layout containing 3 parts horizontally
        JPanel middlePanel=new JPanel();
        BoxLayout middleLayout=new BoxLayout(middlePanel,BoxLayout.X_AXIS);
        middlePanel.setLayout(middleLayout);
        middlePanel.add(Box.createRigidArea(new Dimension(120,0)));
        middlePanel.add(middleContentPanel);
        middlePanel.add(Box.createRigidArea(new Dimension(120,0)));
        //Add panels to the login page
        add(northPanel,BorderLayout.NORTH);
        add(middlePanel,BorderLayout.CENTER);
    }

    protected void refreshTable(JTable table, String[][] data, String[] columnName)
    {
        /*
            Create a new default table model using given data and column name and set it
        for the table

       input:
            table: JTable, the table to present the new table model
            data: String[][], 2-d String Array containing timestamp and value
            columnName: String[], containing names for each column
         */
        DefaultTableModel tableModel=new DefaultTableModel(data,columnName);
        table.setModel(tableModel);
    }
}
