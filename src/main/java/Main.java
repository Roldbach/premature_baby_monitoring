import DataHandling.Baby;
import DataHandling.DataBase;
import UI.UIController;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

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
        frame.setSize((int) (1000/0.75),(int)(800/0.75));
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

        /*
        String[] command={"D:/anaconda/python.exe",System.getProperty("user.dir")+"/PythonScript/main.py"};
        try {
            ProcessBuilder processBuilder=new ProcessBuilder();
            processBuilder.command(command);
            Process process= processBuilder.start();
            System.out.println("Finish");
        } catch (IOException e) {
            e.printStackTrace();
        }

         */



    }

}
