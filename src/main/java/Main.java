import UI.UIController;

import javax.swing.*;

public class Main
{
    public static void main(String[] args)
    {
        /*
            Initiate the main controller and the frame

            The default directory of database is under: Base\DataBase
            The default directory of baby is under: Base\DataBase\Baby
         */
        UIController mainController=new UIController(System.getProperty("user.dir")+"\\DataBase",System.getProperty("user.dir")+"\\DataBase\\Baby");
        JFrame frame=new JFrame();
        frame.setSize(1000,800);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(mainController.getMainPanel());
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
