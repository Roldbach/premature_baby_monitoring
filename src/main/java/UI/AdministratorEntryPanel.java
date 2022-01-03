package UI;

class AdministratorEntryPanel extends GeneralPanel
{
    protected AdministratorEntryPanel()
    {
        /*
            Initiate the change value panel given the UI design

            This pages requires following components:
            (1) label 1: JLabel, display the current user ID
            (2) button_1: JButton, display "Log out"
            (3) button_2: JButton, display "Back"
            (4) button_3: JButton, display "Manage Account"
            (5) button_4: JButton, display "Manage Log File"
         */
        label_1=setLabel("User ID: ",880,50,80,14,false);

        button_1=setButton("Log out",872,76,84,36,true);
        button_2=setButton("Back",44,44,69,26,true);
        button_3=setButton("Manage Account",228,310,250,180,true);
        button_4=setButton("Manage Log File",522,310,250,180,true);
    }
}
