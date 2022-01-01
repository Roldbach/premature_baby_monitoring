package UI;

import javax.swing.*;

class GeneralPanel extends JPanel
{

    protected JLabel label_1;
    protected JLabel label_2;
    protected JLabel label_3;
    protected JLabel label_4;
    protected JButton button_1;
    protected JButton button_2;
    protected JButton button_3;
    protected JTextField textField_1;
    protected JTextField textField_2;

    protected GeneralPanel()
    {
        /*
            Generally set the layout to null to enable absolute position with coordinates
        and enable children pages to set components conveniently with less repeated codes
         */
        setLayout(null);
    }

    protected JLabel setLabel(String text, int x, int y, int width, int height)
    {
        /*
            Return a label with given text, coordinate and size after adding it to the panel

        input:
            text: String, the text displayed on the component
            x: int, the value of x coordinate relative to the top left corner of the window
            y: int, the value of y coordinate relative to the top left corner of the window
            width: int, the value of the width of the component
            height: int, the value of the height of the component

        return:
            label: JLabel, which satisfies all requirements
         */
        JLabel label=new JLabel(text);
        label.setBounds(x,y,width,height);
        add(label);
        return label;
    }

    protected JButton setButton(String text, int x, int y, int width, int height)
    {
        /*
            Return a button with given text, coordinate and size after adding it to the panel

        input:
            text: String, the text displayed on the component
            x: int, the value of x coordinate relative to the top left corner of the window
            y: int, the value of y coordinate relative to the top left corner of the window
            width: int, the value of the width of the component
            height: int, the value of the height of the component

        return:
            button: JButton, which satisfies all requirements
         */
        JButton button=new JButton(text);
        button.setBounds(x,y,width,height);
        add(button);
        return button;
    }

    protected JTextField setTextField(int x, int y, int width, int height)
    {
        /*
            Return a text field with given coordinate and size after adding it to the panel

        input:
            x: int, the value of x coordinate relative to the top left corner of the window
            y: int, the value of y coordinate relative to the top left corner of the window
            width: int, the value of the width of the component
            height: int, the value of the height of the component

        return:
            textField: JTextField, which satisfies all requirements
         */
        JTextField textField=new JTextField();
        textField.setBounds(x,y,width,height);
        add(textField);
        return textField;
    }

    protected JRadioButton setRadioButton(ButtonGroup group, String text, int x, int y, int width, int height)
    {
       /*
            Return a radio button with given coordinate and size after adding it to the panel and group

       input:
            group: ButtonGroup, the group of button to be selected
            text: String, the text displayed on the component
            x: int, the value of x coordinate relative to the top left corner of the window
            y: int, the value of y coordinate relative to the top left corner of the window
            width: int, the value of the width of the component
            height: int, the value of the height of the component
        */
        JRadioButton radioButton=new JRadioButton(text);
        radioButton.setBounds(x,y,width,height);
        group.add(radioButton);
        add(radioButton);
        return radioButton;
    }

    protected JTable setTable(String[][] data, String[] columnName, int x, int y, int width, int height)
    {
        /*
            Return a table with given data, columnName, coordinates and size after adding it to a scroll pane
        and page panel

            The table allows single cell selection

       input:
            data: String[][], 2-d String Array containing timestamp and value
            columnName: String[], containing names for each column
            x: int, the value of x coordinate relative to the top left corner of the window
            y: int, the value of y coordinate relative to the top left corner of the window
            width: int, the value of the width of the component
            height: int, the value of the height of the component
         */
        JTable table=new JTable(data,columnName);
        table.setBounds(x,y,width,height);
        table.setCellSelectionEnabled(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane=new JScrollPane();
        scrollPane.add(table);
        add(scrollPane);
        return table;
    }

    protected void resetText(JTextField textField, boolean focus)
    {
        /*
            Reset the text field and set the default focus if required

        input:
            group: ButtonGroup, the group of button to be selected
            text: String, the text displayed on the component
            x: int, the value of x coordinate relative to the top left corner of the window
            y: int, the value of y coordinate relative to the top left corner of the window
            width: int, the value of the width of the component
            height: int, the value of the height of the component
         */
        textField.setText("");
        if (focus) {textField.requestFocusInWindow();}
    }
}
