package UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class ChangeValuePanel extends GeneralPanel
{
    private JTable glucoseTable;
    private JTable skinTable;
    private  JTable eventTable;

    protected ChangeValuePanel()
    {
        /*
            Initiate the change value panel given the UI design

            This pages requires following components:
            (1) label 1: JLabel, display the current user ID
            (2) label 2: JLabel, display the current baby ID
            (3) button_1: JButton, display "Log out"
            (4) button_2: JButton, display "Back"
            (5) glucoseTable: JTable, display glucose concentration with timestamp
            (6) skinTable: JTable, display skin current, concentration with timestamp
            (7) eventTable: JTable, display detailed event with timestamp
         */
        label_1=setLabel("User ID: ",880,50,80,14,false);
        label_2=setLabel("Baby ID: ",880,76,80,14,false);

        button_1=setButton("Log out",872,102,84,36,true);
        button_2=setButton("Back",44,44,69,26,true);

        glucoseTable=new JTable();


    }

    protected JTable getGlucoseTable()
    {
        /*
            Return the glucose table to the controller

        return:
            glucoseTable: JTable, the table which displays glucose concentration with timestamp
         */
        return glucoseTable;
    }

    protected JTable getSkinTable()
    {
        /*
            Return the skin table to the controller

        return:
            skinTable: JTable, the table which displays skin current/concentration with timestamp
         */
        return skinTable;
    }

    protected JTable eventTable()
    {
        /*
            Return the event table to the controller

        return:
            eventTable: JTable, the table which displays detailed event with timestamp
         */
        return eventTable;
    }

    protected JTable setTable(String[][] data, String[] columnName, int x, int y, int width, int height)
    {
        /*
            Return a table with given data, columnName, coordinates and size after adding it to a scroll pane
        and page panel

            The input value is pixel value and requires transform into point value by /0.75

            The table allows single cell selection

       input:
            data: String[][], 2-d String Array containing timestamp and value
            columnName: String[], containing names for each column
            x: int, the pixel value of x coordinate relative to the top left corner of the window
            y: int, the pixel value of y coordinate relative to the top left corner of the window
            width: int, the pixel value of the width of the component
            height: int, the pixel value of the height of the component
         */
        //JScrollPane scrollPane=new JScrollPane();
        //scrollPane.setBounds((int) (x/0.75), (int) (y/0.75),(int) (width/0.75),(int) (height/0.75));

        JTable table=new JTable(data,columnName){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        //table.getPreferredSize();
        table.setBounds((int) (x/0.75), (int) (y/0.75),table.getPreferredSize().width,table.getPreferredSize().height);
        table.setColumnSelectionAllowed(false);
        table.setRowSelectionAllowed(false);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //scrollPane.add(table);
        add(table);
        return table;
    }
}
