package UI;

import DataHandling.DataBase;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Hashtable;

public class UIController {
    private DataBase dataBase;
    private String currentUser;
    private String currentBaby;
    private Boolean priority;

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private LogInPanel logInPanel;
    private MainMenuPanel mainMenuPanel;
    private ChangeBabyPanel changeBabyPanel;
    private AddValuePanel addValuePanel;
    private ChangeValuePanel changeValuePanel;


    private ChangePasswordPanel changePasswordPanel;
    private AdministratorEntryPanel administratorEntryPanel;
    private ManageAccountPanel manageAccountPanel;
    private ManageLogFilePanel manageLogFilePanel;

    public UIController(String directory, String babyDirectory) {
        /*
            Initiate the UIController and the database using given directory

            By default, the database data could be found under: Base\DataBase
                        the baby data could be found under: Base\Database\Baby

           5. changeValuePanel: the page where the user could check glucose concentration, skin current/concentration
                                and detailed event information with timestamp
                                the value could be modified directly by editing the table
              This page contains those action listeners:
              (1) button 1: jump to the login page and then reset the current user ID and current baby ID
              (2) button 2: jump to the main menu page and refresh the hashmap-format data

        input:
            directory: String, the directory path where files except baby data could be loaded
            babyDirectory: String, the directory path where all baby data could be loaded
         */
        //Initiate components and pages and add those into the main panel
        dataBase=new DataBase(directory, babyDirectory);
        currentUser=null;
        currentBaby=null;
        priority=false;
        //Initiate all pages
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



        changePasswordPanel=new ChangePasswordPanel();
        setChangePasswordPanel();
        administratorEntryPanel=new AdministratorEntryPanel();
        setAdministratorEntryPanel();
        manageAccountPanel=new ManageAccountPanel();
        setManageAccountPanel();
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
          (3) addValueButton: jump to the add value page, set the default focus for text field 1 and
                              set option button 1 as default selected radio button
          (4) changeValueButton: jump to the change value page, acquire array-format data from the
                                 database and refresh tables to display data
          (5) plotGraphButton: jump to the plot graph page, get options from user, call python script
                               to run data processing and plotting automatically and load the image
                               results to each label
          (6) changePasswordButton: jump to the change password page
          (7) entryButton: jump to the administrator entry page
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
        //Action listener to jump to add value page for add value button
        mainMenuPanel.getAddButton().addActionListener(e->{
            setUserID(addValuePanel.label_1, currentUser);
            setBabyID(addValuePanel.label_2, currentBaby);
            cardLayout.show(mainPanel,"add value");
            addValuePanel.resetText(addValuePanel.textField_1, true);
            addValuePanel.resetText(addValuePanel.textField_2, false);
            addValuePanel.getOptionButton().setSelected(true);
        });
        //Action listener to jump to change value page for change value button
        mainMenuPanel.getChangeValueButton().addActionListener(e->{
            //Update the current user ID and current baby ID
            setUserID(changeValuePanel.label_1, currentUser);
            setBabyID(changeValuePanel.label_2,currentBaby);
            //Sort the data before displaying to the user
            dataBase.sortTimestamp();
            //Initiates 3 tables
            String[][] glucoseConcentration=dataBase.formatGlucoseConcentration(currentBaby);
            String[] columnName={"Time","Glucose Concentration"};
            changeValuePanel.setTable(glucoseConcentration,columnName,150,210,204,380);
            cardLayout.show(mainPanel,"change value");
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
            addValuePanel.getOptionButton().setSelected(true);
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

           This page contains those mouse listeners:
           (1) glucose table: show confirm message box when double-click the table, if the permission is given
                              then edit the data and refresh the table when clicking yes option
           (2) skin table: the same as glucose table
           (3) event table: the same as glucose table
        */
        //Action listener to log out for button 1
        jumpBack(changeValuePanel.button_1,"log in");
        //Action listener to jump to the main menu for button 2
        jumpBack(changeValuePanel.button_2, "main menu");
        mainPanel.add(changeValuePanel,"change value");

        //Add mouse click listener for the glucose concentration table
        JTable glucoseTable=changeValuePanel.getGlucoseTable();
        //System.out.println(glucoseTable.getValueAt(0,0));
        /*
        glucoseTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                //Only response when double-click the table
                if (e.getClickCount()==2&&!e.isConsumed())
                {
                    int row=glucoseTable.getSelectedRow();
                    int column=glucoseTable.getSelectedColumn();
                    //Show different message when clicking concentration and timestamp according to the column number
                    if (column==1)
                    {
                        String inputConcentration=JOptionPane.showInputDialog( "Glucose Concentration: "+glucoseTable.getValueAt(row,column), JOptionPane.PLAIN_MESSAGE);
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

         */
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
           (4) button 4: jump to the manage log file page
        */
        //Action listener to log out for button 1
        jumpBack(administratorEntryPanel.button_1,"log in");
        //Action listener to jump to the main menu for button 2
        jumpBack(administratorEntryPanel.button_2, "main menu");
        //Action listener to jump to the manage account page
        administratorEntryPanel.button_3.addActionListener(e->{
            //Update the current user ID, reset both text fields and set the default focus to the text field 1
            setUserID(manageAccountPanel.label_1,currentUser);
            cardLayout.show(mainPanel,"manage account");
            administratorEntryPanel.resetText(manageAccountPanel.textField_1,true);
            administratorEntryPanel.resetText(manageAccountPanel.textField_2, false);
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

    private void showMessage(String title, String message, String type)
    {
        /*
            Show message dialog given title, message and type

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
        main menu page or the login page, a helper function for jumping to those pages

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
}