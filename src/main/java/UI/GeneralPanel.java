package UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

class GeneralPanel extends JPanel
{

    protected JLabel label_1;
    protected JLabel label_2;
    protected JLabel label_3;
    protected JLabel label_4;
    protected JButton button_1;
    protected JButton button_2;
    protected JButton button_3;
    protected JButton button_4;
    protected JRadioButton radioButton_1;
    protected JRadioButton radioButton_2;
    protected JRadioButton radioButton_3;
    protected JRadioButton radioButton_4;
    protected JTextField textField_1;
    protected JTextField textField_2;
    protected JTable table_1;
    protected JTable table_2;
    protected JTable table_3;

    protected GeneralPanel()
    {
        /*
            Generally set the layout to border layout as the base layout manager
         */
        setLayout(new BorderLayout());
    }

    protected JLabel setLabel(String text, boolean bold)
    {
        /*
            Return a label with given text and add it to the panel

            By default, the font is set as "Arial" and the font size is set as 16

        input:
            text: String, the text displayed on the component
            bold: boolean, true if the font is BOLD, false otherwise

        return:
            label: JLabel, which satisfies all requirements
         */
        JLabel label=new JLabel(text);
        if (bold) {label.setFont(new Font("Arial", Font.BOLD,16));}
        else {label.setFont(new Font("Arial", Font.PLAIN, 16));}
        add(label);
        return label;
    }

    protected JButton setButton(String text, boolean bold)
    {
        /*
            Return a button with given text and add it to the panel

            By default, the font is set as "Arial" and the font size is set as 16

        input:
            text: String, the text displayed on the component
            bold: boolean, true if the font is BOLD, false otherwise

        return:
            button: JButton, which satisfies all requirements
         */
        JButton button=new JButton(text);
        if (bold) {button.setFont(new Font("Arial", Font.BOLD,16));}
        else {button.setFont(new Font("Arial", Font.PLAIN, 16));}
        add(button);
        return button;
    }

    protected JTextField setTextField()
    {
        /*
            Return a text field and add it to the panel

            By default, the font is set as "Arial" and the font size is set as 16

        return:
            textField: JTextField, which satisfies all requirements
         */
        JTextField textField=new JTextField();
        textField.setFont(new Font("Arial",Font.PLAIN,16));
        add(textField);
        return textField;
    }
    protected JTable setTable()
    {
        /*
            Return a table which is not editable and allow single cell selection

         */
        JTable table=new JTable(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setColumnSelectionAllowed(false);
        table.setRowSelectionAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return table;
    }

    protected JRadioButton setRadioButton(ButtonGroup group, String text,boolean bold)
    {
       /*
            Return a radio button and add it to the panel and group

            By default, the font is set as "Arial" and the font size is set as 16

       input:
            group: ButtonGroup, the group of button to be selected
            text: String, the text displayed on the component
        */
        JRadioButton radioButton=new JRadioButton(text);
        if (bold) {radioButton.setFont(new Font("Arial", Font.BOLD,16));}
        else {radioButton.setFont(new Font("Arial", Font.PLAIN, 16));}
        group.add(radioButton);
        add(radioButton);
        return radioButton;
    }

    protected void resetText(JTextField textField, boolean focus)
    {
        /*
            Reset the text field and set the default focus if required

        input:
            textField: JTextField, the target textField that requires resetting
            focus: boolean, true if set default target to the target
         */
        textField.setText("");
        if (focus) {textField.requestFocusInWindow();}
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
