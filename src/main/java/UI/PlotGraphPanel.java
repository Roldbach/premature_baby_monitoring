package UI;

import javax.swing.*;
import java.awt.*;

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
        setLayout(new BorderLayout());
        label_1=new JLabel("User ID: ");
        label_1.setFont(new Font("Arial",Font.PLAIN,16));
        label_2=new JLabel("Baby ID: ");
        label_2.setFont(new Font("Arial",Font.PLAIN,16));

        button_1=new JButton("Log out");
        button_1.setFont(new Font("Arial",Font.BOLD,16));
        button_2=new JButton("Back");
        button_2.setFont(new Font("Arial",Font.BOLD,16));

        radioButton_1=new JRadioButton("Differentiation");
        radioButton_1.setFont(new Font("Arial",Font.PLAIN,16));
        radioButton_2=new JRadioButton("Linear Regression");
        radioButton_2.setFont(new Font("Arial",Font.PLAIN,16));
        radioButton_3=new JRadioButton("Moving Average");
        radioButton_3.setFont(new Font("Arial",Font.PLAIN,16));
        radioButton_4=new JRadioButton("Savitzky Golay");
        radioButton_4.setFont(new Font("Arial",Font.PLAIN,16));
        ButtonGroup driftGroup=new ButtonGroup();
        driftGroup.add(radioButton_1);
        driftGroup.add(radioButton_2);
        ButtonGroup filterGroup=new ButtonGroup();
        filterGroup.add(radioButton_3);
        filterGroup.add(radioButton_4);

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

        JPanel radioButtonPanel_1=new JPanel(new GridLayout(1,5));
        radioButtonPanel_1.add(new Label(""));
        radioButtonPanel_1.add(radioButton_1);
        radioButtonPanel_1.add(new Label(""));
        radioButtonPanel_1.add(radioButton_2);
        radioButtonPanel_1.add(new Label(""));

        JPanel radioButtonPanel_2=new JPanel(new GridLayout(1,5));
        radioButtonPanel_2.add(new Label(""));
        radioButtonPanel_2.add(radioButton_3);
        radioButtonPanel_2.add(new Label(""));
        radioButtonPanel_2.add(radioButton_4);
        radioButtonPanel_2.add(new Label(""));
        //Set the north panel in the border layout
        JPanel northPanel=new JPanel(new GridLayout(6,1));
        northPanel.add(new JLabel(""));
        northPanel.add(buttonPanel_2);
        northPanel.add(userPanel);
        northPanel.add(babyPanel);
        northPanel.add(buttonPanel_1);
        northPanel.add(new JLabel(""));
        //Set the central panel to display content at the central area
        JPanel centralPanel=new JPanel(new GridLayout(2,2,25,25));
        centralPanel.add(plot_1);
        centralPanel.add(plot_2);
        centralPanel.add(plot_3);
        centralPanel.add(plot_4);
        //Set the south panel to display radio buttons at the south area
        JPanel southPanel=new JPanel(new GridLayout(2,1));
        southPanel.add(radioButtonPanel_1);
        southPanel.add(radioButtonPanel_2);
        //Add panels to the login page
        add(northPanel,BorderLayout.NORTH);
        add(Box.createHorizontalStrut(25),BorderLayout.WEST);
        add(centralPanel,BorderLayout.CENTER);
        add(Box.createHorizontalStrut(25),BorderLayout.EAST);
        add(southPanel,BorderLayout.SOUTH);
        //By default, set "Linear Regression" and "Moving Average" as default options
        resetOption();

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
