package UI;

import javax.swing.*;
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

    protected JLabel setLabel(String text, int x, int y, int width, int height, boolean bold)
    {
        /*
            Return a label with given text, coordinate and size after adding it to the panel

            The input value is pixel value and requires transform into point value by /0.75

            By default, the font is set as "Arial" and the font size is set as 12

        input:
            text: String, the text displayed on the component
            x: int, the pixel value of x coordinate relative to the top left corner of the window
            y: int, the pixel value of y coordinate relative to the top left corner of the window
            width: int, the pixel value of the width of the component
            height: int, the pixel value of the height of the component
            bold: boolean, true if the font is BOLD, false otherwise

        return:
            label: JLabel, which satisfies all requirements
         */
        JLabel label=new JLabel(text);
        label.setBounds((int) (x/0.75), (int) (y/0.75),(int) (width/0.75),(int) (height/0.75));
        if (bold) {label.setFont(new Font("Arial", Font.BOLD,12));}
        else {label.setFont(new Font("Arial", Font.PLAIN, 12));}
        add(label);
        return label;
    }

    protected JButton setButton(String text, int x, int y, int width, int height, boolean bold)
    {
        /*
            Return a button with given text, coordinate and size after adding it to the panel

            The input value is pixel value and requires transform into point value by /0.75

            By default, the font is set as "Arial" and the font size is set as 12

        input:
            text: String, the text displayed on the component
            x: int, the pixel value of x coordinate relative to the top left corner of the window
            y: int, the pixel value of y coordinate relative to the top left corner of the window
            width: int, the pixel value of the width of the component
            height: int, the pixel value of the height of the component
            bold: boolean, true if the font is BOLD, false otherwise

        return:
            button: JButton, which satisfies all requirements
         */
        JButton button=new JButton(text);
        button.setBounds((int) (x/0.75), (int) (y/0.75),(int) (width/0.75),(int) (height/0.75));
        if (bold) {button.setFont(new Font("Arial", Font.BOLD,12));}
        else {button.setFont(new Font("Arial", Font.PLAIN, 12));}
        add(button);
        return button;
    }

    protected JTextField setTextField(int x, int y, int width, int height)
    {
        /*
            Return a text field with given coordinate and size after adding it to the panel

            The input value is pixel value and requires transform into point value by /0.75

        input:
            x: int, the pixel value of x coordinate relative to the top left corner of the window
            y: int, the pixel value of y coordinate relative to the top left corner of the window
            width: int, the pixel value of the width of the component
            height: int, the pixel value of the height of the component

        return:
            textField: JTextField, which satisfies all requirements
         */
        JTextField textField=new JTextField();
        textField.setBounds((int) (x/0.75), (int) (y/0.75),(int) (width/0.75),(int) (height/0.75));
        add(textField);
        return textField;
    }

    protected JRadioButton setRadioButton(ButtonGroup group, String text, int x, int y, int width, int height, boolean bold)
    {
       /*
            Return a radio button with given coordinate and size after adding it to the panel and group

            The input value is pixel value and requires transform into point value by /0.75

            By default, the font is set as "Arial" and the font size is set as 12

       input:
            group: ButtonGroup, the group of button to be selected
            text: String, the text displayed on the component
            x: int, the pixel value of x coordinate relative to the top left corner of the window
            y: int, the pixel value of y coordinate relative to the top left corner of the window
            width: int, the pixel value of the width of the component
            height: int, the pixel value of the height of the component
        */
        JRadioButton radioButton=new JRadioButton(text);
        radioButton.setBounds((int) (x/0.75), (int) (y/0.75),(int) (width/0.75),(int) (height/0.75));
        if (bold) {radioButton.setFont(new Font("Arial", Font.BOLD,12));}
        else {radioButton.setFont(new Font("Arial", Font.PLAIN, 12));}
        group.add(radioButton);
        add(radioButton);
        return radioButton;
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
        JTable table=new JTable(data,columnName);
        table.setBounds((int) (x/0.75), (int) (y/0.75),(int) (width/0.75),(int) (height/0.75));
        table.setCellSelectionEnabled(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(table));
        System.out.println(data[0][0]);
        return table;
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
}
