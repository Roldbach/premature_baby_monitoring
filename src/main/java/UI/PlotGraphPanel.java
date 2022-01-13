package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

class PlotGraphPanel extends GeneralPanel
{
    private JLabel plot_1;
    private JLabel plot_2;
    private JLabel plot_3;
    private JLabel plot_4;

    protected PlotGraphPanel()
    {
        /*
            Initiate the change value panel given the UI design

            This pages requires following components:
            (1) label 1: JLabel, display the current user ID
            (2) label 2: JLabel, display the current baby ID
            (3) button_1: JButton, display "Log out"
            (4) button_2: JButton, display "Back"
            (5) button_3: JButton, display "Previous"
            (6) button_4: JButton, display "Next"
            (5) radioButton_1: JRadioButton, display "Differentiation"
            (6) radioButton_2: JRadioButton, display "Linear Regression"
            (7) radioButton_3: JRadioButton, display "Moving Average"
            (8) radioButton_4: JRadioButton, display "Savitzky Golay"
            (9) plot_1: JLabel, display the plot of glucose concentration with respect to time
            (10) plot_2: JLabel, display the plot of skin concentration with respect to time
            (11) plot_3: JLabel, display the plot of glucose concentration with respect to skin concentration
            (12) plot_4: JLabel, display the Bland-Altman Plot

            Add action listeners to every radio button so each time a new option is selected, the plots could be
            reloaded and displayed
         */
        label_1=setLabel("User ID: ",false);
        label_2=setLabel("Baby ID: ",false);

        button_1=setButton("Log out",true);
        button_2=setButton("Back",true);
        button_3=setButton("Previous",true);
        button_4=setButton("Next",true);

        ButtonGroup driftGroup=new ButtonGroup();
        ButtonGroup filterGroup=new ButtonGroup();
        radioButton_1=setRadioButton(driftGroup,"Differentiation",false);
        radioButton_2=setRadioButton(driftGroup,"Linear Regression",false);
        radioButton_3=setRadioButton(filterGroup,"Moving Average",false);
        radioButton_4=setRadioButton(filterGroup,"Savitzky Golay",false);

        plot_1=new JLabel();
        plot_2=new JLabel();
        plot_3=new JLabel();
        plot_4=new JLabel();
        //Set the panel for labels and buttons
        JPanel userPanel=new JPanel(new FlowLayout(FlowLayout.TRAILING,44,0));
        userPanel.add(label_1);

        JPanel babyPanel=new JPanel(new FlowLayout(FlowLayout.TRAILING,44,0));
        babyPanel.add(label_2);

        JPanel buttonPanel_1=new JPanel(new FlowLayout(FlowLayout.TRAILING,44,0));
        buttonPanel_1.add(button_1);

        JPanel buttonPanel_2=new JPanel(new FlowLayout(FlowLayout.LEADING,44,0));
        buttonPanel_2.add(button_2);

        JPanel buttonPanel_3=new JPanel(new FlowLayout(FlowLayout.CENTER,200,0));
        buttonPanel_3.add(button_3);
        buttonPanel_3.add(button_4);

        //Set the north panel in the border layout
        JPanel northPanel=new JPanel(new GridLayout(7,1));
        northPanel.add(new JLabel(""));
        northPanel.add(buttonPanel_2);
        northPanel.add(userPanel);
        northPanel.add(babyPanel);
        northPanel.add(buttonPanel_1);
        northPanel.add(buttonPanel_3);
        northPanel.add(new JLabel(""));
        //Set the content panel using card layout manager to display one plot at a time
        JPanel contentPanel=new JPanel();
        CardLayout contentLayout=new CardLayout();
        contentPanel.setLayout(contentLayout);
        contentPanel.add(plot_1,"plot 1");
        contentPanel.add(plot_2,"plot 2");
        contentPanel.add(plot_3,"plot 3");
        contentPanel.add(plot_4,"plot 4");
        //Set the south panel to display radio buttons at the south area
        JPanel southPanel=new JPanel(new GridLayout(2,2));
        southPanel.add(radioButton_1);
        southPanel.add(radioButton_2);
        southPanel.add(radioButton_3);
        southPanel.add(radioButton_4);
        radioButton_1.setHorizontalAlignment(JLabel.CENTER);
        radioButton_2.setHorizontalAlignment(JLabel.CENTER);
        radioButton_3.setHorizontalAlignment(JLabel.CENTER);
        radioButton_4.setHorizontalAlignment(JLabel.CENTER);
        //Add panels to the plot graph page
        add(northPanel,BorderLayout.NORTH);
        add(Box.createHorizontalStrut(25),BorderLayout.WEST);
        add(contentPanel,BorderLayout.CENTER);
        add(Box.createHorizontalStrut(25),BorderLayout.EAST);
        add(southPanel,BorderLayout.SOUTH);
        //By default, set "Linear Regression" and "Moving Average" as default options
        resetOption();
        //Add action listener to the button 3 and button 4 so they could change the plot
        button_3.addActionListener(e->{
            contentLayout.previous(contentPanel);
        });
        button_4.addActionListener(e->{
            contentLayout.next(contentPanel);
        });
    }

    protected String getDriftOption()
    {
        /*
            Return the user's choice of the drift removal from the first button group

        return:
            result: String, either "differentiation" or "linear regression"
         */
        if (radioButton_1.isSelected()) {return "differentiation";}
        else {return "linear regression";}
    }

    protected String getFilterOption()
    {
        /*
            Return the user's choice of the noise removal from the second button group

        return:
            result: String, either "moving average" or "savitzky golay"
         */
        if (radioButton_3.isSelected()) {return "moving average";}
        else {return "savitzky golay";}
    }

    protected void resetOption()
    {
        /*
            Reset the default option before the user entering this page
         */
        radioButton_2.setSelected(true);
        radioButton_3.setSelected(true);
    }
    protected void loadImage(String directory)
    {
        /*
            Load plots generated by python scripts from the given directory and
        scale them according to the size of the label before displaying them

            By default, the plots could be saved under: Base\DataBase\Plots

        input:
            directory: String, the directory where plots could be saved
         */
        ImageIcon plot=new ImageIcon(directory+"/DataBase/Plots/GlucoseTime.png");
        Image image=plot.getImage();
        Image newImage=image.getScaledInstance(plot_1.getSize().width,plot_1.getSize().height,Image.SCALE_SMOOTH);
        plot=new ImageIcon(newImage);
        plot_1.setIcon(plot);

        plot=new ImageIcon(directory+"/DataBase/Plots/SkinTime.png");
        image=plot.getImage();
        newImage=image.getScaledInstance(plot_2.getSize().width,plot_2.getSize().height,Image.SCALE_SMOOTH);
        plot=new ImageIcon(newImage);
        plot_2.setIcon(plot);

        plot=new ImageIcon(directory+"/DataBase/Plots/correlation.png");
        image=plot.getImage();
        newImage=image.getScaledInstance(plot_3.getSize().width,plot_3.getSize().height,Image.SCALE_SMOOTH);
        plot=new ImageIcon(newImage);
        plot_3.setIcon(plot);

        plot=new ImageIcon(directory+"/DataBase/Plots/BlandAltman.png");
        image=plot.getImage();
        newImage=image.getScaledInstance(plot_4.getSize().width,plot_4.getSize().height,Image.SCALE_SMOOTH);
        plot=new ImageIcon(newImage);
        plot_4.setIcon(plot);
        repaint();
    }
}
