import UI.UIController;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main
{
    public static void main(String[] args){
        /*
            Initiate the main controller and the frame

            The default directory of database is under: Base\DataBase
            The default directory of baby is under: Base\DataBase\Baby
         */

        UIController mainController=new UIController(System.getProperty("user.dir")+"/DataBase",System.getProperty("user.dir")+"/DataBase/Baby");
        JFrame frame=new JFrame("Premature Baby Monitoring App");
        //The window is modified to fit the pixel value
        frame.setSize(1000,800);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(mainController.getMainPanel());
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //Save the data before closing the frame
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                mainController.saveDataBase(System.getProperty("user.dir")+"/DataBase",System.getProperty("user.dir")+"/DataBase/Baby");
                frame.dispose();
            }
        });
    }

}
