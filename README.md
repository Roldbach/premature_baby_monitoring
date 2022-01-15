# PrematureBabyMonitoringApp
Programming Group Project

PREMATURE BABY APP READ ME FILE

*ASSUMPTIONS*

	- The app has access to a local database: setting files, a folder with baby data, and another folder with skin current data files

*PRE-BUILT DATA*

There are four built-in users:

	- UserID: 123, Password: 123 (regular user account)
	- UserID: 321, Password: 321 (regular user account)
	- UserID: 456, Password: 456 (regular user account)
	- UserID: 789, Password: 789 (administrator account)

There are also three baby files in the database, each containing different data:

	- Baby 1
	- Baby 2
	- Baby 3

And one file for skin current data:

	- Baby 3

*HOW TO USE*

The purpose and functionality of the different pages that make up the app, and their respective elements, are listed below:

[1] Log In Page.    

The user is presented with a “User ID” and “Password” field to enter their credentials. They can log in or quit the app. User priority will be given at this stage depending on whether the entered account is a regular user or an administrator. 

[2] Main Menu. 

On the top right corner, information about the current user and current baby is displayed. The current baby is the first baby in the database by default, which can be changed with the “Change Baby” button below. This takes the user to a separate page where a different baby ID can be entered, as long as it is contained within the database. All functionalities listed below will be applied to this current baby.

Just to the left, there is a “Load Skin Concentration Data” button. This will load all locally available skin glucose concentration data. If a file is named after an already existing baby, the system will prompt an overwriting warning message before preceding with placing that data into the corresponding baby file. If a file has a name that does not match any existing baby, a new baby will be created in the database containing such skin data. 

The user is also presented with five main options in the middle of the screen: 

	- The “Add Value” button takes the user to a separate page that enables the addition 
	of new blood glucose concentration values for the current baby (more detail on section [3]).

	- The “Change Value” button takes the user to a separate page where added concentration 
	values, events, and their respective times can be edited (more detail on section [4]).

	- The “Plot Graph” button takes the user to a separate page where different graphs relating 
	blood and skin glucose concentrations can be visualised (more detail on section [5]).

	- The “Change Password” button takes the user to a separate page where their current 
	password can be modified (more detail on section [6]).

	- The “Administrator Entry” button takes the user, if an administrator, to a separate page where 
	he can manage user accounts, access a log file with all recorded activity, and edit the app’s 
	settings (more detail on section [7]).

All daughter pages branching from the Main Menu will contain a “back” button on the top left corner so as to return to the Main Menu. For deeper pages, both a “back” button and a “main” button will show on this top left corner, to go to the previous page or directly to the Main Menu, respectively. 

[3] Add Value.

The user is presented with two fields:

	- “Glucose Concentration” for adding a blood glucose concentration value.
 
	- “Event” for adding an event. 

These two work independently, that is, the user can add a value (and no event) or an event (and no value). If the user adds both a value and an event, they will be separately stored, but will share the same timestamp. 

The default timestamp associated to the input is the current input time, but the user has the ability to quickly change this time to 5 minutes ago or 10 minutes ago with the provided selectors. 

[4] Change Value. 

The user is presented with three tables:

	- The left-most table shows blood glucose concentration values and their respective timestamps.
 
	- The middle table shows skin glucose concentration values and their respective timestamps.

	- The right-most table shows events and their respective timestamps.

Values, events and timestamps can be changed or deleted (depending on the selected option below the tables). In order to change/delete, the user must double click on top of the desired row. For a regular user, there is a 5 minute time limit for making any edits from the input of such value, whilst an administrator can make changes at any given time.

[5] Plot Graph. 

The user is presented with four different plot options (use “next” and “previous” buttons to navigate through them): 

	- Blood Glucose Concentration vs Time plot.

	- Skin Glucose Concentration vs Time plot.

	- Blood Glucose Concentration vs Skin Glucose Concentration (Pearson Correlation).

	- Bland Altman plot.

There are also four options below, from which the user can select any combination comprising:

	- “Differentiation” or “Linear Regression” as a drift removal method.

	- “Moving Average” or “Savitzky Golay” as a noise removal method.

Please note this function might take some time to load. In addition, the python library “pyCompare” must be installed for it to run. If not, an error window will pop up. 

[6] Change Password.

The user is presented with two fields:

	- “User ID” for specifying the user ID that the password change will be applied to. If the user is a 
	regular user, this field can only be the current user, if not, an error window will pop up. If the user 
	is an administrator, he/she can select the user ID the change will apply to.

	- “New Password” for specifying the new password associated to the user ID. 

After clicking confirm, the change will apply. 

[7] Administrator Entry. 

This page is only accessible for administrators. If a regular user tries to enter, an error window will pop up. The administrator is presented with two buttons: 

	- “Manage Account” for adding/deleting new/existing user accounts. The administrator will be 
	presented with two fields:

		- “User ID” for specifying a new/existing user. 

		- “Password” for specifying the corresponding new/existing password. 

	If the administrator tries to add an already existing user, the system will recognise this 
	and offer a password change instead. 

	- “Setting/Log File” for changing settings and accessing the activity log file. The administrator 
	will be presented with two tables:

		- The upper table shows the Lag Time (time passed between skin current concentration 
		data is collected until it arrives to the app), the Permission Time (for allowing input data 
		changes to regular users) and the Calibration Parameters (for converting skin current to 
		skin concentration). The administrator can edit these values by double clicking on them. 

		- The lower table shows the Log File, which contains all activity information. 
    
*IMPORTANT*

The app will automatically save any changes made to the database when either the window in closed or the user pressess the button "Quit" at the log in page.
