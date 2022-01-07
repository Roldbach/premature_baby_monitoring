package UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

class ChangeValuePanel extends GeneralPanel
{
    protected ChangeValuePanel()
    {
        /*
            Initiate the change value panel given the UI design

            This pages requires following components:
            (1) label 1: JLabel, display the current user ID
            (2) label 2: JLabel, display the current baby ID
            (3) button_1: JButton, display "Log out"
            (4) button_2: JButton, display "Back"
            (5) table_1: JTable, display glucose concentration with timestamp
            (6) table_2: JTable, display skin current, concentration with timestamp
            (7) table_3: JTable, display detailed event with timestamp

            #Class diagram requires modification
         */
        setLayout(new BorderLayout());
        label_1=new JLabel("User ID: ");
        label_1.setFont(new Font("Arial",Font.PLAIN,16));
        label_2=new JLabel("Baby ID: ");
        label_2.setFont(new Font("Arial",Font.PLAIN,16));

        button_1=new JButton("Log out");
        button_1.setFont(new Font("Arial",Font.BOLD,16));
        button_2=new JButton("Back");
        button_2.setFont(new Font("Arial",Font.BOLD,16));

        table_1=new JTable(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table_1.setColumnSelectionAllowed(false);
        table_1.setRowSelectionAllowed(false);
        table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane_1=new JScrollPane();
        scrollPane_1.setViewportView(table_1);

        table_2=new JTable(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table_2.setColumnSelectionAllowed(false);
        table_2.setRowSelectionAllowed(false);
        table_2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane_2=new JScrollPane();
        scrollPane_2.setViewportView(table_2);

        table_3=new JTable(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table_3.setColumnSelectionAllowed(false);
        table_3.setRowSelectionAllowed(false);
        table_3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
        //Set the center panel as a box layout containing 3 parts horizontally
        JPanel middlePanel=new JPanel();
        BoxLayout middleLayout=new BoxLayout(middlePanel,BoxLayout.X_AXIS);
        middlePanel.setLayout(middleLayout);
        middlePanel.add(Box.createHorizontalStrut(1));
        middlePanel.add(scrollPane_1);
        //middlePanel.add(Box.createRigidArea(new Dimension(1,0)));
        middlePanel.add(scrollPane_2);
        //middlePanel.add(Box.createRigidArea(new Dimension(1,0)));
        middlePanel.add(scrollPane_3);
        middlePanel.add(Box.createHorizontalStrut(1));
        //Add panels to the login page
        add(northPanel,BorderLayout.NORTH);
        add(middlePanel,BorderLayout.CENTER);
        add(Box.createVerticalStrut(150),BorderLayout.SOUTH);
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
