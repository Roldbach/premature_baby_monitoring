package UI;

import DataHandling.DataBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UIController
{
    private DataBase dataBase;
    private String currentUser;
    private String currentBaby;
    private Boolean priority;

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private LogInPanel logInPanel;
    private MainMenuPanel mainMenuPanel;


    public UIController(String directory, String babyDirectory)
    {
        /*
            Initiate the UIController and the database using given directory

            By default, the database data could be found under: Base\DataBase
                        the baby data could be found under: Base\Database\Baby

            Initiate pages according to the ui design and add action listeners if needed
            1. logInPanel: the default page when the user first use the App, the user could
                           log in or quit the app here
               This page contains those action listeners:
               (1) button 1: get the user input when click the button and
                  change the status of the controller depending on the result

               If the user successfully log in, jump to the main menu after hiding the login page
               and pass the current user ID and current baby ID to the main menu page

               If the user fails the login, show an alert and reset the text field

               (2) button 2: quit the window when click the button

           2. mainMenuPanel: the main menu of the App and user could choose various function here
                             including: adding value/event, checking or changing value/event, plotting,
                             changing password and managing as an administrator
              This page contains those action listeners:
              (1) button 1: jump to the change baby page
              (2) button 2: jump to the login page and the reset the current user ID and current baby ID
              (3) addValueButton: jump to the add value page
              (4) changeValueButton: jump to the change value page, acquire array-format data from the
                                     database and refresh tables to display data
              (5) plotGraphButton: jump to the plot graph page, call python script to run data processing
                                   and plotting automatically and load the image results to image view
              (6) changePasswordButton: jump to the change password page
              (7) entryButton: jump to the administrator entry page

           3. changeBabyPanel: the page where the user could enter a new baby ID to monitor and this
                               ID must be previously saved in the database
              This page contains those action listeners:
              (1) button 1: jump to the login page and then reset the current user ID and current baby ID
              (2) button 2: jump to the main menu page without any change
              (3) button 3: verify the input ID and set it as new baby ID if it is contained in the database
                            jump to the main menu page

           4. addValuePanel: the page where the user could add glucose concentration and events
                             the timestamp is automatically generated according to the delay selected by the user
              This page contains those action listeners:
              (1) button 1: jump to the login page and then reset the current user ID and current baby ID
              (2) button 2: jump to the main menu page without any change
              (3) button 3: generate the timestamp according to the delay selected by the user and only add
                            valid input to the dataset (white spaces and null are not valid) followed by resetting
                            the text field and focus (stay at this page)

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
        dataBase=new DataBase(directory,babyDirectory);
        currentUser=null;
        currentBaby=null;
        priority=false;

        cardLayout=new CardLayout();
        mainPanel=new JPanel(cardLayout);
        logInPanel=new LogInPanel();
        mainMenuPanel=new MainMenuPanel();

        mainPanel.add(logInPanel,"log in");
        mainPanel.add(mainMenuPanel,"main menu");

        //Action listener for log in button
        logInPanel.button_1.addActionListener(e -> {
            //Get input and verify the log in result
            String userID= logInPanel.textField_1.getText();
            String password= logInPanel.textField_2.getText();
            Boolean[] status=dataBase.logIn(userID,password);
            //If log in successfully, modify the status of controller and move to the main menu page
            if (status[0])
            {
                currentUser=userID;
                //By default, the first baby in the database is set for monitoring
                currentBaby=dataBase.getBabyList().get(0);
                if (status [1]) {priority=true;}
                cardLayout.show(mainPanel,"main menu");
            }
            //If fail the login, show an alert to the user and reset the text field
            else
            {
                showAlert("Warning","Invalid ID or password. Please try again.");
                logInPanel.resetText(logInPanel.textField_1, true);
                logInPanel.resetText(logInPanel.textField_2, false);
            }
        });
    }

    public JPanel getMainPanel()
    {
        /*
            Return the main panel of the controller which could be
        added to the frame

        return:
            mainPanel: JPanel
         */
        return mainPanel;
    }

    public void showAlert(String title, String message)
    {
        JOptionPane.showMessageDialog(mainPanel,message,title,JOptionPane.WARNING_MESSAGE);
    }

    public String formatTime(String minute)
    {
        /*
            Return the modified time in the format "yyyy/MM/dd HH:mm:ss"
            Return the current local time if given "0"

        input:
            minute: String, the time difference between the target time and the current time

        return:
            time: String, the target time in the format "yyyy/MM/dd HH:mm:ss"
         */
        LocalDateTime time=LocalDateTime.now().minusMinutes(Long.parseLong(minute));
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return formatter.format(time);
    }
}
