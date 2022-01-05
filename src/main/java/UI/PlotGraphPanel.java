package UI;

import javax.swing.*;
import java.awt.*;

class PlotGraphPanel extends GeneralPanel
{
    private JRadioButton driftButton_1;
    private JRadioButton driftButton_2;
    private JRadioButton filterButton_1;
    private JRadioButton filterButton_2;
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
            (5) driftButton_1: JRadioButton, display "Differentiation"
            (6) driftButton_2: JRadioButton, display "Linear Regression"
            (7) filterButton_1: JRadioButton, display "Moving Average"
            (8) filterButton_2: JRadioButton, display "Savitzky Golay"
            (9) plot_1: JLabel, display the plot of glucose concentration with respect to time
            (10) plot_2: JLabel, display the plot of skin concentration with respect to time
            (11) plot_3: JLabel, display the plot of glucose concentration with respect to skin concentration
            (12) plot_4: JLabel, display the Bland-Altman Plot

            Add action listeners to every radio button so each time a new option is selected, the plots could be
            reloaded and displayed
         */
        ButtonGroup driftGroup=new ButtonGroup();
        ButtonGroup filterGroup=new ButtonGroup();

        label_1=setLabel("User ID: ",880,50,80,14,false);
        label_2=setLabel("Baby ID: ",880,76,80,14,false);

        button_1=setButton("Log out",872,102,84,36,true);
        button_2=setButton("Back",44,44,69,26,true);

        plot_1=setLabel("",213,50,276,218,false);
        plot_2=setLabel("",511,50,276,218,false);
        plot_3=setLabel("",213,290,276,216,false);
        plot_4=setLabel("",511,290,276,218,false);

        //Stupid way to set radio buttons
        driftButton_1=new JRadioButton("Differentiation");
        driftButton_1.setBounds(388,672,100,14);
        driftButton_1.setFont(new Font("Arial", Font.PLAIN, 12));
        driftGroup.add(driftButton_1);
        add(driftButton_1);

        driftButton_2=new JRadioButton("Linear Regression");
        driftButton_2.setBounds(541,672,125,14);
        driftButton_2.setFont(new Font("Arial", Font.PLAIN, 12));
        driftGroup.add(driftButton_2);
        add(driftButton_2);

        filterButton_1=new JRadioButton("Moving Average");
        filterButton_1.setBounds(388,708,110,14);
        filterButton_1.setFont(new Font("Arial", Font.PLAIN, 12));
        filterGroup.add(filterButton_1);
        add(filterButton_1);

        filterButton_2=new JRadioButton("Savitzky Golay");
        filterButton_2.setBounds(541,708,135,14);
        filterButton_2.setFont(new Font("Arial", Font.PLAIN, 12));
        filterGroup.add(filterButton_2);
        add(filterButton_2);

        /*
        No idea why codes below don't work
        radioButton_1=setRadioButton(driftGroup,"Linear Regression", 500,500,93,14,false);
        driftButton_2=setRadioButton(driftGroup,"Linear Regression", 541,672,93,14,false);
        filterButton_1=setRadioButton(filterGroup,"Moving Average",388,708,87,14,false);
        filterButton_2=setRadioButton(filterGroup,"Savitzky Golay",541,708,100,14,false);
         */

        //By default, set "Linear Regression" and "Moving Average" as default options
        resetOption();
    }

    protected JRadioButton getDriftButton_1()
    {
        return driftButton_1;
    }

    protected JRadioButton getDriftButton_2()
    {
        return driftButton_2;
    }

    protected JRadioButton getFilterButton_1()
    {
        return filterButton_1;
    }

    protected JRadioButton getFilterButton_2()
    {
        return filterButton_2;
    }

    protected String getDriftOption()
    {
        /*
            Return the user's choice of the drift removal from the first button group

        return:
            result: String, either "differentiation" or "linear regression"
         */
        if (driftButton_1.isSelected()) {return "differentiation";}
        else {return "linear regression";}
    }

    protected String getFilterOption()
    {
        /*
            Return the user's choice of the noise removal from the second button group

        return:
            result: String, either "moving average" or "savitzky golay"
         */
        if (filterButton_1.isSelected()) {return "moving average";}
        else {return "savitzky golay";}
    }

    protected void resetOption()
    {
        /*
            Reset the default option before the user entering this page
         */
        driftButton_2.setSelected(true);
        filterButton_1.setSelected(true);
    }
    protected void loadImage(String directory)
    {
        /*
            Load plots generated by python scripts from the given directory and display them

            By default, the plots could be saved under: Base\DataBase\Plots

        input:
            directory: String, the directory where plots could be saved
         */
        ImageIcon plot=new ImageIcon(directory+"/DataBase/Plots/GlucoseTime.png");
        Image image=plot.getImage();
        Image newImage=image.getScaledInstance(276,218, Image.SCALE_SMOOTH);
        plot=new ImageIcon(newImage);
        plot_1.setIcon(plot);

        plot=new ImageIcon(directory+"/DataBase/Plots/SkinTime.png");
        image=plot.getImage();
        newImage=image.getScaledInstance(276,218, Image.SCALE_SMOOTH);
        plot=new ImageIcon(newImage);
        plot_2.setIcon(plot);

        plot=new ImageIcon(directory+"/DataBase/Plots/correlation.png");
        image=plot.getImage();
        newImage=image.getScaledInstance(276,218, Image.SCALE_SMOOTH);
        plot=new ImageIcon(newImage);
        plot_3.setIcon(plot);

        plot=new ImageIcon(directory+"/DataBase/Plots/BlandAltman.png");
        image=plot.getImage();
        newImage=image.getScaledInstance(276,218, Image.SCALE_SMOOTH);
        plot=new ImageIcon(newImage);
        plot_4.setIcon(plot);

        repaint();
        System.out.println("Done refresh");
    }
}
