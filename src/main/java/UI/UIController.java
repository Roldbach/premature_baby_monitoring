package UI;

import DataHandling.DataBase;
import com.sun.tools.javac.util.ArrayUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Hashtable;

public class UIController {
    private DataBase dataBase;
    private String currentUser;
    private String currentBaby;
    private Boolean priority;

    private final CardLayout cardLayout;
    private final JPanel mainPanel;
    private final LogInPanel logInPanel;
    private final MainMenuPanel mainMenuPanel;
    private final ChangeBabyPanel changeBabyPanel;
    private final AddValuePanel addValuePanel;
    private final ChangeValuePanel changeValuePanel;
    private final PlotGraphPanel plotGraphPanel;
    private final ChangePasswordPanel changePasswordPanel;
    private final AdministratorEntryPanel administratorEntryPanel;
    private final ManageAccountPanel manageAccountPanel;
    private final ManageLogFilePanel manageLogFilePanel;

    public UIController(String directory, String babyDirectory) {
        /*
            Initiate the UIController and the database using given directory

            By default, the database data could be found under: Base\DataBase
                        the baby data could be found under: Base\Database\Baby

        input:
            directory: String, the directory path where files except baby data could be loaded
            babyDirectory: String, the directory path where all baby data could be loaded
         */
        //Initiate components and pages and add those into the main panel
        dataBase=new DataBase(directory, babyDirectory);
        currentUser=null;
        currentBaby=null;
        priority=false;
        //Initiate all pages and the card layout
        cardLayout=new CardLayout();
        mainPanel=new JPanel(cardLayout);
        logInPanel=new LogInPanel();
        setLogInPanel(directory,babyDirectory);
        mainMenuPanel=new MainMenuPanel();
        setMainMenuPanel(directory,babyDirectory);
        changeBabyPanel=new ChangeBabyPanel();
        setChangeBabyPanel();
        addValuePanel=new AddValuePanel();
        setAddValuePanel();
        changeValuePanel=new ChangeValuePanel();
        setChangeValuePanel();
        plotGraphPanel=new PlotGraphPanel();
        setPlotGraphPanel(directory);
        changePasswordPanel=new ChangePasswordPanel();
        setChangePasswordPanel();
        administratorEntryPanel=new AdministratorEntryPanel();
        setAdministratorEntryPanel();
        manageAccountPanel=new ManageAccountPanel();
        setManageAccountPanel();
        manageLogFilePanel=new ManageLogFilePanel();
        setManageLogFilePanel();
    }

    public JPanel getMainPanel() {
        /*
            Return the main panel of the controller which could be
        added to the frame

        return:
            mainPanel: JPanel
         */
        return mainPanel;
    }

    public void saveDataBase(String directory, String babyDirectory)
    {
        /*
        Save the database before closing the frame

        input:
            directory: String, the directory path where files except baby data could be loaded
            babyDirectory: String, the directory path where all baby data could be loaded
         */
        dataBase.saveDataBase(directory,babyDirectory);
    }

    private void setLogInPanel(String directory, String babyDirectory)
    {
        /*
           Set the log in panel, the page where the user could log in to the system using
       valid ID and password

           This page contains those action listeners:
           (1) button 1: get the user input when click the button and
                         change the status of the controller depending on the result

           If the user successfully log in, jump to the main menu and set current user ID
           and current baby ID to the matched labels

           If the user fails the login, show an alert and reset the text field

           (2) button 2: save the database to the local and quit the App when click the button
                         By default, the database is saved under: Base\DataBase
                                     the baby data is saved under: Base\DataBase\Baby

         input:
            directory: String, the directory path where files except baby data could be loaded
            babyDirectory: String, the directory path where all baby data could be loaded
         */
        //Action listener to log in for button 1
        logInPanel.button_1.addActionListener(e -> {
            //Get input and verify the log in result
            String userID = logInPanel.textField_1.getText();
            String password = logInPanel.textField_2.getText();
            Boolean[] status = dataBase.logIn(userID, password);
            //If log in successfully, modify the status of controller and move to the main menu page
            if (status[0]) {
                currentUser = userID;
                //By default, the first baby in the database is set for monitoring
                currentBaby = dataBase.getBabyList().get(0);
                if (status[1]) {
                    priority = true;
                }
                //Set current user ID and baby ID to the matched labels in the main page
                setUserID(mainMenuPanel.label_1,currentUser);
                setBabyID(mainMenuPanel.label_2,currentBaby);
                cardLayout.show(mainPanel, "main menu");
            }
            //If fail the login, show an alert to the user and reset the text field
            else {
                showMessage("Error", "Invalid ID or password. Please try again.","error");
                logInPanel.resetText(logInPanel.textField_1, true);
                logInPanel.resetText(logInPanel.textField_2, false);
            }
        });
        //Action listener to save and quit for button 2
        logInPanel.button_2.addActionListener(e->{
            saveDataBase(directory,babyDirectory);
            System.exit(0);
        });
        mainPanel.add(logInPanel, "log in");
    }

    private void setMainMenuPanel(String directory, String babyDirectory)
    {
       /*
           Set the main menu panel, the page where the user could choose various function here
       including: changing baby, logging out, adding value/event, checking+changing value/event,
       plotting, changing password and managing as an administrator

          This page contains those action listeners:
          (1) button 1: jump to the change baby page and set current user ID and current baby ID to the
                        matched labels and then set the default focus to the text field in the new page
          (2) button 2: jump to the login page and the reset the current user ID and current baby ID
          (3) button 3: load skin current data from the given directory and ask permission to overwrite
                        current data
          (4) addValueButton: jump to the add value page, set the default focus for text field 1 and
                              set radio button 1 as default selected radio button
          (5) changeValueButton: jump to the change value page, acquire array-format data from the
                                 database and refresh tables to display data
          (6) plotGraphButton: jump to the plot graph page, get options from user, call python script
                               to run data processing and plotting automatically and load the image
                               results to each label
          (7) changePasswordButton: jump to the change password page
          (8) entryButton: jump to the administrator entry page

        input:
            directory: String, the directory path where files except baby data could be loaded
            babyDirectory: String, the directory path where all baby data could be loaded

        */
        //Action listener to jump to change baby page for button 1
        mainMenuPanel.button_1.addActionListener(e -> {
            //Update the labels and set the default focus to the text field 1
            setUserID(changeBabyPanel.label_1,currentUser);
            setBabyID(changeBabyPanel.label_2,currentBaby);
            cardLayout.show(mainPanel,"change baby");
            changeBabyPanel.resetText(changeBabyPanel.textField_1, true);
        });
        //Action listener to jump to log in page for button 2
        jumpBack(mainMenuPanel.button_2, "log in");
        //Action listener to load skin current data for button 3
        mainMenuPanel.button_3.addActionListener(e->{
            //Open the current file and find all skin current file
            File currentFile=new File(directory+"/Current");
            String[] currentFileList=currentFile.list();
            if (currentFileList.length!=0)
            {
                //For every file in this directory, check whether the baby is in the database
                for (String fileName:currentFileList)
                {
                    String babyID=fileName.substring(0, fileName.length()-4);
                    //If the baby is not in the database, load data for this baby
                    if (!dataBase.getBabyList().contains(babyID))
                    {
                        boolean result= dataBase.loadSkinCurrent(directory+"/Current/"+fileName,babyID);
                        //According to the result, show different message
                        if (result) {showMessage("Message","Load data for "+babyID+" successfully!","message");}
                        else {showMessage("Error","Can not load data for "+babyID+". Please try again.","error");}
                    }
                    else
                    {
                        //if the baby is in the database, check permission to overwrite the current data first
                        int option=JOptionPane.showConfirmDialog(mainPanel,
                                "Would you like to overwrite data for "+babyID+"?",
                                "Confirmation",
                                JOptionPane.YES_NO_OPTION);
                        if (option==JOptionPane.YES_OPTION)
                        {
                            boolean result= dataBase.loadSkinCurrent(directory+"/Current/"+fileName,babyID);
                            //According to the result, show different message
                            if (result) {showMessage("Message","Load data for "+babyID+" successfully!","message");}
                            else {showMessage("Error","Can not load data for "+babyID+". Please try again.","error");}
                        }
                    }
                }
            }
            else
            {
               //If no current data under this directory, show error message to the user
                showMessage("Error","No skin current data found. Please try again.","error");
            }


        });
        //Action listener to jump to add value page for add value button
        mainMenuPanel.getAddButton().addActionListener(e->{
            setUserID(addValuePanel.label_1, currentUser);
            setBabyID(addValuePanel.label_2, currentBaby);
            cardLayout.show(mainPanel,"add value");
            addValuePanel.resetText(addValuePanel.textField_1, true);
            addValuePanel.resetText(addValuePanel.textField_2, false);
            addValuePanel.radioButton_1.setSelected(true);
        });
        //Action listener to jump to change value page for change value button
        mainMenuPanel.getChangeValueButton().addActionListener(e->{
            //Update the current user ID and current baby ID
            setUserID(changeValuePanel.label_1, currentUser);
            setBabyID(changeValuePanel.label_2,currentBaby);
            //Sort the data before displaying to the user
            dataBase.sortTimestamp();
            //Initiates glucose concentration table
            String[][] glucoseConcentration=dataBase.formatGlucoseConcentration(currentBaby);
            String[] glucoseColumnName={"Time","Glucose Concentration"};
            changeValuePanel.refreshTable(changeValuePanel.table_1,glucoseConcentration,glucoseColumnName);
            //Initiate skin glucose concentration table
            String[][] skinConcentration= dataBase.formatSkinConcentration(currentBaby);
            String[] skinColumnName= {"Time","Skin Concentration","Skin Current"};
            changeValuePanel.refreshTable(changeValuePanel.table_2,skinConcentration,skinColumnName);
            //Initiate event table
            String[][] event = dataBase.formatEvent(currentBaby);
            String[] eventColumnName= {"Time","Event"};
            changeValuePanel.refreshTable(changeValuePanel.table_3, event,eventColumnName);
            //Set the default selection to the radio button 1 for change model
            changeValuePanel.radioButton_1.setSelected(true);
            //Add mouse listener for the glucose concentration table
            changeValuePanel.table_1.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    int row=changeValuePanel.table_1.getSelectedRow();
                    int column=changeValuePanel.table_1.getSelectedColumn();
                    //Only response when the user double-click the table under change value
                    if (e.getClickCount()==2&&!e.isConsumed()&&changeValuePanel.radioButton_1.isSelected())
                    {
                        //According to the column show different message box to ask for input
                        if (column==1)
                        {
                            String input=JOptionPane.showInputDialog("Glucose Concentration: " + changeValuePanel.table_1.getValueAt(row, column),
                                    changeValuePanel.table_1.getValueAt(row, column));
                            if (input!=null)
                            {
                                //If the user want to change the value, check permission and whether the input is valid before changing
                                try
                                {
                                    Double newValue=Double.parseDouble(input.trim());
                                    String targetTime=(String) changeValuePanel.table_1.getValueAt(row,0);
                                    if (dataBase.checkPermission(targetTime,formatTime("0"))||priority)
                                    {
                                        //No need to sort the timestamp as only the concentration is modified
                                        changeValuePanel.table_1.setValueAt(input,row,column);
                                        dataBase.changeGlucoseConcentration(currentUser,currentBaby,targetTime,newValue,formatTime("0"));
                                        showMessage("Message","Change glucose concentration successfully!","message");
                                    }
                                    else {showMessage("Error","Permission denied. You haven't given priority.","error");}
                                }
                                catch (NumberFormatException exception)
                                {
                                    showMessage("Error","Invalid concentration value. Please try again.","error");
                                }
                            }
                        }
                        else
                        {
                            String targetTimestamp= (String) changeValuePanel.table_1.getValueAt(row, column);
                            String input=JOptionPane.showInputDialog("Timestamp: "+targetTimestamp,
                                    targetTimestamp);
                            if (input!=null)
                            {
                                //Check whether the input timestamp is valid and permission
                                try
                                {
                                    DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                                    formatter.parse(input.trim());
                                    if (dataBase.checkPermission(targetTimestamp,formatTime("0"))||priority)
                                    {
                                        dataBase.changeGlucoseConcentrationTimestamp(currentUser,currentBaby,
                                                targetTimestamp,input,formatTime("0"));
                                        //Sort the data in the database and update the table
                                        String[][] glucoseConcentration=dataBase.formatGlucoseConcentration(currentBaby);
                                        changeValuePanel.refreshTable(changeValuePanel.table_1,glucoseConcentration,glucoseColumnName);
                                        showMessage("Message","Change timestamp successfully!","message");
                                    }
                                    else {showMessage("Error","Permission denied. You haven't given priority.","error");}
                                }
                                catch (StringIndexOutOfBoundsException|DateTimeParseException exception)
                                {
                                    showMessage("Error","Invalid timestamp. Please try again.","error");
                                }
                            }
                        }
                    }
                    //Only response when double-click the table under delete model
                    else if (e.getClickCount()==2&&!e.isConsumed()&&changeValuePanel.radioButton_2.isSelected())
                    {
                        //Show different messages according to the column
                        if (column==1)
                        {
                            int result=JOptionPane.showConfirmDialog(mainPanel,
                                    "Glucose Concentration: "+changeValuePanel.table_1.getValueAt(row,column)+"\n" +"Would you like to delete this concentration?",
                                    "Confirmation",
                                    JOptionPane.YES_NO_OPTION);
                            if (result==JOptionPane.YES_OPTION)
                            {
                                //Check permission if the user choose the yes option
                                if (dataBase.checkPermission((String) changeValuePanel.table_1.getValueAt(row,0),formatTime("0"))|priority)
                                {
                                    //Delete the data and update if given permission or priority
                                    dataBase.deleteGlucoseConcentration(currentUser,currentBaby,(String) changeValuePanel.table_1.getValueAt(row,0),formatTime("0"));
                                    String[][] glucoseConcentration=dataBase.formatGlucoseConcentration(currentBaby);
                                    String[] glucoseColumnName= {"Time","Glucose Concentration"};
                                    changeValuePanel.refreshTable(changeValuePanel.table_1,glucoseConcentration,glucoseColumnName);
                                    showMessage("Message","Delete concentration successfully","message");
                                }
                                else {showMessage("Error","Permission denied. You haven't given priority.","error");}
                            }
                        }
                        else
                        {
                            int result=JOptionPane.showConfirmDialog(mainPanel,
                                    "Timestamp: "+changeValuePanel.table_1.getValueAt(row,column)+"\n" +"Would you like to delete this timestamp?",
                                    "Confirmation",
                                    JOptionPane.YES_NO_OPTION);
                            if (result==JOptionPane.YES_OPTION)
                            {
                                //Check permission if the user choose the yes option
                                if (dataBase.checkPermission((String) changeValuePanel.table_1.getValueAt(row,0),formatTime("0"))|priority)
                                {
                                    //Delete the timestamp and update if given permission or priority
                                    dataBase.deleteGlucoseConcentration(currentUser,currentBaby,(String) changeValuePanel.table_1.getValueAt(row,0),formatTime("0"));
                                    String[][] glucoseConcentration=dataBase.formatGlucoseConcentration(currentBaby);
                                    String[] glucoseColumnName= {"Time","Glucose Concentration"};
                                    changeValuePanel.refreshTable(changeValuePanel.table_1,glucoseConcentration,glucoseColumnName);
                                    showMessage("Message","Delete timestamp successfully","message");
                                }
                                else {showMessage("Error","Permission denied. You haven't given priority.","error");}
                            }
                        }
                    }
                    e.consume();
                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
            //Add mouse listener for the skin table
            changeValuePanel.table_2.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    int row=changeValuePanel.table_2.getSelectedRow();
                    int column=changeValuePanel.table_2.getSelectedColumn();
                    //Only response when double-clicking the table
                    if (e.getClickCount()==2&&!e.isConsumed())
                    {
                        //Show different message according to the user's choice
                        if (column==1)
                        {
                            showMessage("Skin Concentration","Time: "+changeValuePanel.table_2.getValueAt(row,0)+"\n"
                            +"Skin Concentration: "+changeValuePanel.table_2.getValueAt(row,column),"message");
                        }
                        else if (column==2)
                        {
                            showMessage("Skin Current","Time: "+changeValuePanel.table_2.getValueAt(row,0)+"\n"
                                    +"Skin Current: "+changeValuePanel.table_2.getValueAt(row,column),"message");
                        }
                        else {showMessage("Time","Time: "+changeValuePanel.table_2.getValueAt(row,column),"message");}
                    }
                }
                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
            //Add mouse listener for the event table
            changeValuePanel.table_3.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    int row=changeValuePanel.table_3.getSelectedRow();
                    int column=changeValuePanel.table_3.getSelectedColumn();
                    //Only response when double-clicking the table under the change mode
                    if (e.getClickCount()==2&&!e.isConsumed()&&changeValuePanel.radioButton_1.isSelected())
                    {

                        //According to the column show different message box to ask for input
                        if (column==1)
                        {
                            String input=JOptionPane.showInputDialog("Event: " + changeValuePanel.table_3.getValueAt(row, column),
                                    changeValuePanel.table_3.getValueAt(row, column));
                            if (input!=null)
                            {
                                //If the user want to change the event, check permission and assume the input is valid (Can not find a way to check validity for event)
                                try
                                {
                                    String targetTime=(String) changeValuePanel.table_3.getValueAt(row,0);
                                    if (dataBase.checkPermission(targetTime,formatTime("0"))||priority)
                                    {
                                        //No need to sort the timestamp as only the event is modified
                                        changeValuePanel.table_3.setValueAt(input,row,column);
                                        dataBase.changeEvent(currentUser,currentBaby,targetTime,input.trim(),formatTime("0"));
                                        showMessage("Message","Change event successfully!","message");
                                    }
                                    else {showMessage("Error","Permission denied. You haven't given priority.","error");}
                                }
                                catch (NumberFormatException exception)
                                {
                                    showMessage("Error","Invalid event information. Please try again.","error");
                                }
                            }
                        }
                        else
                        {
                            String targetTimestamp=(String) changeValuePanel.table_3.getValueAt(row,column);
                            String input=JOptionPane.showInputDialog("Timestamp: "+targetTimestamp,
                                    targetTimestamp);
                            if (input!=null)
                            {
                                //Check whether the input timestamp is valid and permission
                                try
                                {
                                    DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                                    formatter.parse(input.trim());
                                    if (dataBase.checkPermission(targetTimestamp,formatTime("0"))||priority)
                                    {
                                        dataBase.changeEventTimestamp(currentUser,currentBaby,
                                                targetTimestamp,input,formatTime("0"));
                                        //Sort the data and update the table
                                        String[][] event=dataBase.formatEvent(currentBaby);
                                        changeValuePanel.refreshTable(changeValuePanel.table_3,event,eventColumnName);
                                        showMessage("Message","Change timestamp successfully!","message");
                                    }
                                    else {showMessage("Error","Permission denied. You haven't given priority.","error");}
                                }
                                catch (StringIndexOutOfBoundsException|DateTimeParseException exception)
                                {
                                    showMessage("Error","Invalid timestamp. Please try again.","error");
                                }
                            }
                        }
                    }
                    //Only response when double-click the table under delete model
                    else if (e.getClickCount()==2&&!e.isConsumed()&&changeValuePanel.radioButton_2.isSelected())
                    {
                        //Show different messages according to the column
                        if (column==1)
                        {
                            int result=JOptionPane.showConfirmDialog(mainPanel,
                                    "Event: "+changeValuePanel.table_3.getValueAt(row,column)+"\n" +"Would you like to delete this event?",
                                    "Confirmation",
                                    JOptionPane.YES_NO_OPTION);
                            if (result==JOptionPane.YES_OPTION)
                            {
                                //Check permission if the user choose the yes option
                                if (dataBase.checkPermission((String) changeValuePanel.table_3.getValueAt(row,0),formatTime("0"))|priority)
                                {
                                    //Delete the data and update if given permission or priority
                                    dataBase.deleteEvent(currentUser,currentBaby,(String) changeValuePanel.table_3.getValueAt(row,0),formatTime("0"));
                                    String[][] event=dataBase.formatEvent(currentBaby);
                                    String[] eventColumnName= {"Time","Event"};
                                    changeValuePanel.refreshTable(changeValuePanel.table_3,event,eventColumnName);
                                    showMessage("Message","Delete event successfully","message");
                                }
                                else {showMessage("Error","Permission denied. You haven't given priority.","error");}
                            }
                        }
                        else
                        {
                            int result=JOptionPane.showConfirmDialog(mainPanel,
                                    "Timestamp: "+changeValuePanel.table_3.getValueAt(row,column)+"\n" +"Would you like to delete this timestamp?",
                                    "Confirmation",
                                    JOptionPane.YES_NO_OPTION);
                            if (result==JOptionPane.YES_OPTION)
                            {
                                //Check permission if the user choose the yes option
                                if (dataBase.checkPermission((String) changeValuePanel.table_3.getValueAt(row,0),formatTime("0"))|priority)
                                {
                                    //Delete the timestamp and update if given permission or priority
                                    dataBase.deleteEvent(currentUser,currentBaby,(String) changeValuePanel.table_3.getValueAt(row,0),formatTime("0"));
                                    String[][] event=dataBase.formatEvent(currentBaby);
                                    String[] eventColumnName= {"Time","Event"};
                                    changeValuePanel.refreshTable(changeValuePanel.table_3,event,eventColumnName);
                                    showMessage("Message","Delete timestamp successfully","message");
                                }
                                else {showMessage("Error","Permission denied. You haven't given priority.","error");}
                            }
                        }
                    }
                    e.consume();
                }
                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
            cardLayout.show(mainPanel,"change value");
        });
        //Action listener to jump to plot graph page for plot button
        mainMenuPanel.getPlotButton().addActionListener(e -> {
            //Update the current user ID and current baby ID
            setUserID(plotGraphPanel.label_1,currentUser);
            setBabyID(plotGraphPanel.label_2, currentBaby);
            //Save the database first before loading the images
            saveDataBase(directory,babyDirectory);
            //Reset the option
            plotGraphPanel.resetOption();
            //According to the user's choice, write the instruction file
            setInstruction(directory, plotGraphPanel.getDriftOption(), plotGraphPanel.getFilterOption());
            //Generate plots by calling python script from java
            String[] command= {"python",System.getProperty("user.dir")+"/PythonScript/main.py",System.getProperty("user.dir")};
            try {
                Process process=Runtime.getRuntime().exec(command);
                if (process.waitFor()!=0) {showMessage("Error","Something really bad happened. Please call the emergency number.","Error");}
                //Show error message if something unexpected happen
                else
                {
                    //Load plots and jump to the page
                    plotGraphPanel.loadImage(System.getProperty("user.dir"));
                    cardLayout.show(mainPanel,"plot graph");
                }
            } catch (IOException | InterruptedException exception) {
                showMessage("Error","Something really bad happened. Please call the emergency number.","Error");
            }
        });

        //Action listener to jump to change password page for change password button
        mainMenuPanel.getChangePasswordButton().addActionListener(e->{
            //Update the current user ID, reset both text fields and set the default focus to the text field 1
            setUserID(changePasswordPanel.label_1, currentUser);
            cardLayout.show(mainPanel,"change password");
            changeBabyPanel.resetText(changePasswordPanel.textField_1, true);
            changeBabyPanel.resetText(changePasswordPanel.textField_2, false);
        });
        mainPanel.add(mainMenuPanel, "main menu");
        //Action listener to jump to administrator entry page for entry button
        mainMenuPanel.getEntryButton().addActionListener(e -> {
            if (priority)
            {
                //Update the current user ID
                setUserID(administratorEntryPanel.label_1,currentUser);
                cardLayout.show(mainPanel,"administrator entry");
            }
            //If no priority, show message
            else {showMessage("Error","Permission denied. You haven't given priority.","error");}
        });
    }

    private void setChangeBabyPanel()
    {
        /*
            Set the change baby panel, the page where the user could enter a new baby ID
        to monitor and this ID must be previously saved in the database

            This page contains those action listeners:
            (1) button 1: jump to the login page and then reset the current user ID and current baby ID
            (2) button 2: jump to the main menu page without any change
            (3) button 3: verify the input ID and set it as new baby ID if it is contained in the database
                          jump to the main menu page
         */
        //Action listener to log out for button 1
        jumpBack(changeBabyPanel.button_1,"log in");
        //Action listener to jump to the main menu for button 2
        jumpBack(changeBabyPanel.button_2, "main menu");
        //Action listener to change baby for button 3
        changeBabyPanel.button_3.addActionListener(e->{
            String input=changeBabyPanel.textField_1.getText();
            //If valid, update the current baby ID, show message and go back to the main menu
            if (dataBase.getBabyList().contains(input))
            {
                currentBaby=input;
                setUserID(changeBabyPanel.label_1, currentUser);
                setBabyID(changeBabyPanel.label_2, currentBaby);
                changeBabyPanel.resetText(changeBabyPanel.textField_1, true);
                showMessage("Message","Baby ID is successfully changed!","message");
                setUserID(mainMenuPanel.label_1, currentUser);
                setBabyID(mainMenuPanel.label_2, currentBaby);
                cardLayout.show(mainPanel,"main menu");
            }
            //If not valid, show error and reset the text field for a next try
            else
            {
                showMessage("Error","Invalid ID. Please try again.","error");
                changeBabyPanel.resetText(changeBabyPanel.textField_1, true);
            }
        });
        mainPanel.add(changeBabyPanel, "change baby");
    }

    private void setAddValuePanel()
    {
       /*
           Set the add value panel, the page where the user could add glucose concentration and events
       the timestamp is automatically generated according to the delay selected by the user

           This page contains those action listeners:
           (1) button 1: jump to the login page and then reset the current user ID and current baby ID
           (2) button 2: jump to the main menu page without any change
           (3) button 3: generate the timestamp according to the delay selected by the user and only add
                         valid input to the dataset (white spaces and null are not valid) followed by resetting
                         the text field and focus (stay at this page)
        */
        //Action listener to log out for button 1
        jumpBack(addValuePanel.button_1,"log in");
        //Action listener to jump to the main menu for button 2
        jumpBack(addValuePanel.button_2, "main menu");
        //Action listener to add input for button 3
        addValuePanel.button_3.addActionListener(e->{
            //Detect whether the user add glucose or event or both and show different messages for each situation
            boolean glucose=false;
            boolean event=false;
            //Get the glucose concentration and check it is valid (the concentration has to be >0)
            String glucoseInput=addValuePanel.textField_1.getText().trim();
            if (!glucoseInput.equals(""))
            {
                double glucoseConcentration=Double.parseDouble(glucoseInput);
                //If it is a valid input, add into database
                if (glucoseConcentration>0)
                {
                    dataBase.addGlucoseConcentration(currentUser,currentBaby,glucoseConcentration,formatTime(addValuePanel.getDelay()));
                    glucose=true;
                }
            }
            String eventInput=addValuePanel.textField_2.getText().trim();
            if (!eventInput.equals(""))
            {
                dataBase.addEvent(currentUser,currentBaby,eventInput,formatTime(addValuePanel.getDelay()));
                event=true;
            }
            //Reset both text fields and set the default focus to the text field 1 and default select option button 1
            addValuePanel.resetText(addValuePanel.textField_1, true);
            addValuePanel.resetText(addValuePanel.textField_2, false);
            addValuePanel.radioButton_1.setSelected(true);
            //Show messages for each situation
            if (!glucose&&!event) {showMessage("Error","Invalid concentration or event. Please try again","error");}
            else if (glucose&&event) {showMessage("Message","Add glucose and event successfully!","message");}
            else if(glucose) {showMessage("Message","Add glucose concentration successfully!","message");}
            else if (event) {showMessage("Message","Add event successfully!","message");}
        });

        mainPanel.add(addValuePanel,"add value");
    }

    private void setChangeValuePanel()
    {
      /*
           Set the change value panel, the page where the user could check glucose concentration, skin
       current/concentration and event with timestamp. By interacting with the table, the user could directly
       modify the value if permission given (only within 5min for user)

           This page contains those action listeners:
           (1) button 1: jump to the login page and then reset the current user ID and current baby ID
           (2) button 2: jump to the main menu page without any change
        */
        //Action listener to log out for button 1
        jumpBack(changeValuePanel.button_1,"log in");
        //Action listener to jump to the main menu for button 2
        jumpBack(changeValuePanel.button_2, "main menu");
        mainPanel.add(changeValuePanel,"change value");
    }

    private void setPlotGraphPanel(String directory)
    {
      /*
           Set the plot graph panel, the page where the user could choose the drift
       and noise removal option and all plots are automatically generated and refreshed
       when a new option is chosen

           This page contains those action listeners:
           (1) button 1: jump to the login page and then reset the current user ID and current baby ID
           (2) button 2: jump to the main menu page without any change
           (3) radio button 1: Obtain user's choice for drift/noise removal and refresh the label
           (4) radio button 2: the same as radio button 1
           (5) radio button 3: the same as radio button 1
           (6) radio button 4: the same as radio button 1

        input:
            directory: String, the directory path where files except baby data could be loaded
        */
        //Action listener to log out for button 1
        jumpBack(plotGraphPanel.button_1,"log in");
        //Action listener to jump to the main menu for button 2
        jumpBack(plotGraphPanel.button_2, "main menu");
        //Action listener to refresh images for every radio button
        refreshImages(plotGraphPanel.radioButton_1, directory);
        refreshImages(plotGraphPanel.radioButton_2, directory);
        refreshImages(plotGraphPanel.radioButton_3, directory);
        refreshImages(plotGraphPanel.radioButton_4, directory);
        mainPanel.add(plotGraphPanel,"plot graph");
    }
    private void setChangePasswordPanel()
    {
       /*
           Set the change password panel, the page where the user could change its own password and the administrator
       could change all user's password

           This page contains those action listeners:
           (1) button 1: jump to the login page and then reset the current user ID and current baby ID
           (2) button 2: jump to the main menu page without any change
           (3) button 3: Verify whether it is a valid ID first, allow user to change its own password and
                         administrator to change all user's password
        */
        //Action listener to log out for button 1
        jumpBack(changePasswordPanel.button_1,"log in");
        //Action listener to jump to the main menu for button 2
        jumpBack(changePasswordPanel.button_2, "main menu");
        //Action listener to change password for button 3
        changePasswordPanel.button_3.addActionListener(e->{
            //Check whether the user entered both ID and password
            String ID=changePasswordPanel.textField_1.getText().trim();
            String password=changePasswordPanel.textField_2.getText().trim();
            //Reset both text fields and set the default focus to the text field 1
            changePasswordPanel.resetText(changePasswordPanel.textField_1, true);
            changePasswordPanel.resetText(changePasswordPanel.textField_2, false);
            //If entered both ID and password, check whether the password could be changed according to priority
            if (!ID.equals("")&&!password.equals(""))
            {
                Hashtable<String, String> userList=dataBase.getUser();
                //If no priority, check whether the input ID is matched with the current user ID
                if (!priority)
                {
                    //If true, change the password and show message to the user
                    if (ID.equals(currentUser)&&userList.containsKey(ID))
                    {
                        dataBase.changePassword(currentUser,ID,password,formatTime("0"));
                        showMessage("Message","Change password successfully!","message");
                        //Return to the main menu if successfully change the password
                        setUserID(mainMenuPanel.label_1,currentUser);
                        setBabyID(mainMenuPanel.label_2,currentBaby);
                        cardLayout.show(mainPanel,"main menu");
                    }
                    //If the input ID is not in the database, show message
                    else if (!userList.containsKey(ID)) {showMessage("Error","Invalid ID. Please try again.","error");}
                    //If not matched, show message
                    else if (userList.containsKey(ID)&&!currentUser.equals(ID)) {showMessage("Error","Permission denied. You haven't given priority.","error");}
                }
                //If given priority, check whether the input ID is in the database
                else
                {
                    //If true, change the password and show message
                    if (userList.containsKey(ID))
                    {
                        dataBase.changePassword(currentUser,ID,password,formatTime("0"));
                        showMessage("Message","Change password successfully!","message");
                        //Return to the main menu if successfully change the password
                        setUserID(mainMenuPanel.label_1,currentUser);
                        setBabyID(mainMenuPanel.label_2,currentBaby);
                        cardLayout.show(mainPanel,"main menu");
                    }
                    else {showMessage("Error","Invalid ID. Please try again.","error");}
                }
            }
            //If missed any information, show different message for each situation
            else if (ID.equals("")&&password.equals("")){showMessage("Error","Please enter a valid ID and password.","error");}
            else if (ID.equals("")) {showMessage("Error","Please enter a valid ID.","error");}
            else {showMessage("Error","Please enter a valid password.","error");}
        });
        mainPanel.add(changePasswordPanel,"change password");
    }

    private void setAdministratorEntryPanel()
    {
       /*
           Set the administrator panel, the page where the administrator could jump to the manage account page
       or the manage log file page


           This page contains those action listeners:
           (1) button 1: jump to the login page and then reset the current user ID and current baby ID
           (2) button 2: jump to the main menu page without any change
           (3) button 3: jump to the manage account page
           (4) button 4: jump to the manage log file page and initiate the table
        */
        //Action listener to log out for button 1
        jumpBack(administratorEntryPanel.button_1,"log in");
        //Action listener to jump to the main menu for button 2
        jumpBack(administratorEntryPanel.button_2, "main menu");
        //Action listener to jump to the manage account page for button 3
        administratorEntryPanel.button_3.addActionListener(e->{
            //Update the current user ID, reset both text fields and set the default focus to the text field 1
            setUserID(manageAccountPanel.label_1,currentUser);
            cardLayout.show(mainPanel,"manage account");
            administratorEntryPanel.resetText(manageAccountPanel.textField_1,true);
            administratorEntryPanel.resetText(manageAccountPanel.textField_2, false);
        });
        //Action listener to jump to the manage log file page for button 4
        administratorEntryPanel.button_4.addActionListener(e -> {
            //Update the current user ID
            setUserID(manageLogFilePanel.label_1, currentUser);
            //Initiate and display the setting for table 2
            String[][] setting=dataBase.formatSetting();
            String[] settingName={"Lag Time","Permission Time","Calibration Parameter"};
            manageLogFilePanel.refreshTable(manageLogFilePanel.table_2, setting,settingName);
            //Add mouse listener for the table to allow administrator to modify the setting
            manageLogFilePanel.table_2.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    //Only response when the user double-click the table
                    if (e.getClickCount()==2&&!e.isConsumed())
                    {
                        int row=manageLogFilePanel.table_2.getSelectedRow();
                        int column=manageLogFilePanel.table_2.getSelectedColumn();
                        //According to the column number show different message to the user
                        if (column==0)
                        {
                            String input=JOptionPane.showInputDialog("Lag Time: " + manageLogFilePanel.table_2.getValueAt(row, column),
                                    manageLogFilePanel.table_2.getValueAt(row, column));
                            if (input!=null)
                            {
                                try
                                {
                                    Integer newValue=Integer.parseInt(input.trim());
                                    manageLogFilePanel.table_2.setValueAt(input,row,column);
                                    dataBase.changeLagTime(currentUser,input,formatTime("0"));
                                    //Update the log file table at the same time
                                    String[][] logFile=dataBase.formatLogFile();
                                    String[] columnName={"Time", "User ID", "Baby ID", "Action", "Result"};
                                    manageLogFilePanel.refreshTable(manageLogFilePanel.table_1, logFile,columnName);
                                    showMessage("Message","Change lag time successfully!","message");
                                }
                                catch (NumberFormatException exception)
                                {
                                    //The lag time only allows an integer number as an input
                                    showMessage("Error","Invalid lag time value. Please try again.","error");
                                }
                            }
                        }
                        else if (column==1)
                        {
                            String input=JOptionPane.showInputDialog("Permission Time: " + manageLogFilePanel.table_2.getValueAt(row, column),
                                    manageLogFilePanel.table_2.getValueAt(row, column));
                            if (input!=null)
                            {
                                try
                                {
                                    Integer newValue=Integer.parseInt(input.trim());
                                    manageLogFilePanel.table_2.setValueAt(input,row,column);
                                    dataBase.changePermissionTime(currentUser,input,formatTime("0"));
                                    //Update the log file table at the same time
                                    String[][] logFile=dataBase.formatLogFile();
                                    String[] columnName={"Time", "User ID", "Baby ID", "Action", "Result"};
                                    manageLogFilePanel.refreshTable(manageLogFilePanel.table_1, logFile,columnName);
                                    showMessage("Message","Change permission time successfully!","message");
                                }
                                catch (NumberFormatException exception)
                                {
                                    //The permission time only allows an integer number as an input
                                    showMessage("Error","Invalid permission time value. Please try again.","error");
                                }
                            }
                        }
                        else
                        {
                            String input=JOptionPane.showInputDialog("Calibration Parameter: " + manageLogFilePanel.table_2.getValueAt(row, column)+"\n"
                                    +"Please enter the new parameter in the format: value 1,value 2,value 3...",
                                    manageLogFilePanel.table_2.getValueAt(row, column));
                            if (input!=null)
                            {
                                try
                                {
                                    String[] parameterList=input.trim().split(",");
                                    ArrayList<Double> parameter=new ArrayList<>();
                                    //Transform each value into double type
                                    for (String value:parameterList)
                                    {
                                        parameter.add(Double.parseDouble(value));
                                    }
                                    manageLogFilePanel.table_2.setValueAt(input,row,column);
                                    dataBase.changeCalibrationParameter(currentUser,parameter,formatTime("0"));
                                    //Update the log file table at the same time
                                    String[][] logFile=dataBase.formatLogFile();
                                    String[] columnName={"Time", "User ID", "Baby ID", "Action", "Result"};
                                    manageLogFilePanel.refreshTable(manageLogFilePanel.table_1, logFile,columnName);
                                    showMessage("Message","Change calibration parameter successfully!","message");
                                }
                                catch (NumberFormatException exception)
                                {
                                    //The permission time only allows an integer number as an input
                                    showMessage("Error","Invalid calibration parameter. Please try again.","error");
                                }
                            }
                        }
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
            //Initiate and display the log file for table 1
            String[][] logFile=dataBase.formatLogFile();
            String[] columnName={"Time", "User ID", "Baby ID", "Action", "Result"};
            manageLogFilePanel.refreshTable(manageLogFilePanel.table_1,logFile,columnName);
            //Add mouse listener for the table to show detailed information when double-clicking the table
            manageLogFilePanel.table_1.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    //Only response when double-click the table
                    if (e.getClickCount()==2&&!e.isConsumed())
                    {
                        int row=manageLogFilePanel.table_1.getSelectedRow();
                        int column=manageLogFilePanel.table_1.getSelectedColumn();
                        String content= (String) manageLogFilePanel.table_1.getValueAt(row, column);
                        //Show different messages when selecting different columns
                        switch (column)
                        {
                            case 0:
                                showMessage("Time","Time: "+content,"message");
                                break;
                            case 1:
                                showMessage("User ID","User ID: "+content,"message");
                                break;
                            case 2:
                                showMessage("Baby ID","Baby ID: "+content,"message");
                                break;
                            case 3:
                                showMessage("Action","Action: "+content,"message");
                                break;
                            case 4:
                                showMessage("Result","Result: "+content,"message");
                                break;
                            default:
                                showMessage("Error","System crashed. Please call the emergency number.","error");
                                break;
                        }
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
            cardLayout.show(mainPanel,"manage log file");
        });
        mainPanel.add(administratorEntryPanel,"administrator entry");
    }

    private void setManageAccountPanel()
    {
       /*
           Set the manage account panel, the page where the administrator could add new user or delete a current user
       account if it is in the database already

           It might be possible to add and administrator in version 2

           This page contains those action listeners:
           (1) button 1: jump to the login page and then reset the current user ID and current baby ID
           (2) button 2: jump to the main menu page without any change
           (3) button 3: add new ID and password into the database
           (4) button 4: delete a current user account
        */
        //Action listener to log out for button 1
        jumpBack(manageAccountPanel.button_1,"log in");
        //Action listener to jump to the administrator entry for button 2
        manageAccountPanel.button_2.addActionListener(e->{
            setUserID(administratorEntryPanel.label_1, currentUser);
            cardLayout.show(mainPanel,"administrator entry");
        });
        //Action listener to jump to the main meu for main button
        jumpBack(manageAccountPanel.getMainButton(),"main menu");
        //Action listener to add account for button 3
        manageAccountPanel.button_3.addActionListener(e->{
            //Check whether the user entered both ID and password
            String ID=manageAccountPanel.textField_1.getText().trim();
            String password=manageAccountPanel.textField_2.getText().trim();
            //Reset both text fields and set the default focus to the text field 1
            manageAccountPanel.resetText(manageAccountPanel.textField_1, true);
            manageAccountPanel.resetText(manageAccountPanel.textField_2, false);
            //If entered both ID and password, check whether the input ID is in the database
            if (!ID.equals("")&&!password.equals(""))
            {
                Hashtable<String,String> userList=dataBase.getUser();
                if (userList.containsKey(ID))
                {
                    //Show a message to ask whether need to change password for the input ID
                    int result=JOptionPane.showConfirmDialog(mainPanel,"ID is already added. Would you like to change password?","Confirmation",JOptionPane.YES_NO_OPTION);
                    if (result==JOptionPane.YES_OPTION)
                    {
                        dataBase.changePassword(currentUser,ID,password,formatTime("0"));
                        showMessage("Message","Change password successfully!","message");
                    }
                }
                else
                {
                    dataBase.addUser(currentUser,ID,password,false,formatTime("0"));
                    showMessage("Message","Add user (ID: "+ID+") successfully!","message");
                }
            }
            else if (ID.equals("")&&password.equals("")) {showMessage("Error","Please enter a valid ID and password.","error");}
            else if (password.equals("")) {showMessage("Error","Please enter a valid password.","error");}
            else {showMessage("Error","Please enter a valid ID.","error");}
        });
        manageAccountPanel.button_4.addActionListener(e->{
            //Check whether the user entered ID
            String ID=manageAccountPanel.textField_1.getText().trim();
            //Reset both text fields and set the default focus to the text field 1
            manageAccountPanel.resetText(manageAccountPanel.textField_1, true);
            manageAccountPanel.resetText(manageAccountPanel.textField_2, false);
            //If entered ID, check whether it is valid
            if (!ID.equals(""))
            {
                Hashtable<String, String> userList=dataBase.getUser();
                //If it is in the database, double check to delete it
                if (userList.containsKey(ID))
                {
                    int result=JOptionPane.showConfirmDialog(mainPanel,"Would you like to delete user (ID: "+ID+")?","Confirmation",JOptionPane.YES_NO_OPTION);
                    if (result==JOptionPane.YES_OPTION)
                    {
                        dataBase.deleteUser(currentUser,ID,formatTime("0"));
                        showMessage("Message","Delete user successfully!","message");
                    }
                }
                else {showMessage("Error","ID not found in the database. Please try again.","error");}
            }
            else {showMessage("Error","Please enter a valid ID.","error");}
        });
        //Action listener to jump to main menu for main button
        jumpBack(manageAccountPanel.getMainButton(),"main menu");
        mainPanel.add(manageAccountPanel,"manage account");
    }

    private void setManageLogFilePanel()
    {
       /*
           Set the manage log file panel, the page where the administrator could check detailed records in the log file
       and change general settings containing lag time, permission time and calibration parameter

           The lag time and permission time for now only supports integer input

           This page contains those action listeners:
           (1) button 1: jump to the login page and then reset the current user ID and current baby ID
           (2) button 2: jump to the administrator entry page
           (3) button 3: jump to the main menu page without any change
        */
        //Action listener to log out for button 1
        jumpBack(manageLogFilePanel.button_1,"log in");
        //Action listener to jump to the administrator entry page for button 2
        manageLogFilePanel.button_2.addActionListener(e -> {
            setUserID(administratorEntryPanel.label_1, currentUser);
            cardLayout.show(mainPanel,"administrator entry");
        });
        //Action listener to jump to the main menu for button 3
        jumpBack(manageLogFilePanel.button_3, "main menu");
        mainPanel.add(manageLogFilePanel,"manage log file");
    }

    private void showMessage(String title, String message, String type)
    {
        /*
            Show different message dialog given title, message and type
            if "error", show error dialog
            if "input", show input dialog
            else show information dialog

        input:
            title: String, the title of the message box
            message: String, the main content of the message box
            type: String, the type of message box displayed
         */
        if (type.equals("error")) {JOptionPane.showMessageDialog(mainPanel, message, title, JOptionPane.ERROR_MESSAGE);}
        else if (type.equals("input")) {JOptionPane.showInputDialog(mainPanel, message, title);}
        else {JOptionPane.showMessageDialog(mainPanel,message,title,JOptionPane.INFORMATION_MESSAGE);}
    }

    private String formatTime(String minute) {
        /*
            Return the modified time in the format "yyyy/MM/dd HH:mm:ss"
            Return the current local time if given "0"

        input:
            minute: String, the time difference between the target time and the current time

        return:
            time: String, the target time in the format "yyyy/MM/dd HH:mm:ss"
         */
        LocalDateTime time = LocalDateTime.now().minusMinutes(Long.parseLong(minute));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return formatter.format(time);
    }

    private void setUserID(JLabel label, String userID)
    {
        /*
            Set the current user ID to the matched label with the same format

        input:
            label: JLabel, the target label which shows the current user ID in the page
            text: String, the user ID
         */
        label.setText("User ID: " + userID);
    }

    private void setBabyID(JLabel label, String babyID)
    {
        /*
            Set the current user ID to the matched label with the same format

        input:
            label: JLabel, the target label which shows the current user ID in the page
            text: String, the user ID
         */
        label.setText("Baby ID: " + babyID);
    }

    private void jumpBack(JButton button, String page)
    {
        /*
            Add an action listener to the given button that enable it to jump to the
        main menu page or the login page, a helper function for jumping to those pages only

            If it jumps to the main menu page, update the current user ID and baby ID
            If it jumps to the login page, reset the current user ID and baby ID of the controller,
            reset both text fields and set the default focus to the ID input

        input:
            button: JButton, the target button that needs action listener
            page: String, the name of the target page
         */
        if (page.equals("main menu"))
        {
            button.addActionListener(e -> {
                setUserID(mainMenuPanel.label_1, currentUser);
                setBabyID(mainMenuPanel.label_2, currentBaby);
                cardLayout.show(mainPanel,"main menu");
            });
        }
        else
        {
            button.addActionListener(e->{
                currentUser=null;
                currentBaby=null;
                cardLayout.show(mainPanel,"log in");
                logInPanel.resetText(logInPanel.textField_1,true);
                logInPanel.resetText(logInPanel.textField_2, false);
            });
        }
    }

    private void setInstruction(String directory, String driftOption, String filterOption)
    {
        /*
            Generate the instruction file which could be used to generate plots
        according to the user's choice

            By default, the instruction file could be found under: Base\DataBase

        input:
            directory: String, the directory path where files except baby data could be loaded
            driftOption: String, the user's choice of drift removal
            filterOption: String, the user's choice of noise removal

         throws:
            IOException: there is something wrong with the input/output operations
         */
        try
        {
            //Create a new file that is named by the baby's hospital number
            FileWriter instructionWriter=new FileWriter(directory+"/instruction.txt",false);
            //Save the current baby ID to the file
            instructionWriter.write("id:"+currentBaby+"\n");
            //Save the drift removal and noise removal to the file
            instructionWriter.write("drift:"+driftOption+"\n");
            instructionWriter.write("noise:"+filterOption+"\n");
            instructionWriter.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    protected void refreshImages(JRadioButton radioButton, String directory)
    {
        /*
            Add action listener to the given JRadio button so that every time the
        user choose a new option in the plot graph page, the plots could be generated
        according to the new instruction file and refreshed

        input:
            radioButton: JRadioButton, allows user to choose different option
            directory: String, the directory path where files except baby data could be loaded
         */
        radioButton.addActionListener(e->{
            //Generate new instruction file
            setInstruction(directory, plotGraphPanel.getDriftOption(), plotGraphPanel.getFilterOption());
            //Generate plots by calling python script from java
            String[] command= {"python",System.getProperty("user.dir")+"/PythonScript/main.py",System.getProperty("user.dir")};
            try {
                Process process=Runtime.getRuntime().exec(command);
                if (process.waitFor()!=0)
                {
                    //Show error message and force quit current page if something wrong happened
                    showMessage("Error","Something really bad happened. Please call the emergency number.","Error");
                    setUserID(mainMenuPanel.label_1, currentUser);
                    setUserID(mainMenuPanel.label_2, currentBaby);
                    cardLayout.show(mainPanel,"main menu");
                }
                //Show error message if something unexpected happen
                else
                {
                    //Load plots and refresh
                    plotGraphPanel.loadImage(System.getProperty("user.dir"));
                }
            } catch (IOException | InterruptedException exception) {
                showMessage("Error","Something really bad happened. Please call the emergency number.","Error");
                setUserID(mainMenuPanel.label_1, currentUser);
                setUserID(mainMenuPanel.label_2, currentBaby);
                cardLayout.show(mainPanel,"main menu");
            }
        });
    }
}