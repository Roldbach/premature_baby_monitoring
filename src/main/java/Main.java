import DataHandling.DataBase;
import UI.UIController;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
                mainController.saveDataBase(System.getProperty("user.dir")+"\\DataBase",System.getProperty("user.dir")+"\\DataBase\\Baby");
                frame.dispose();
            }
        });

        /*
        JFrame test=new JFrame();
        DataBase dataBase=new DataBase(System.getProperty("user.dir")+"\\DataBase",System.getProperty("user.dir")+"\\DataBase\\Baby");
        String[][] data=dataBase.formatGlucoseConcentration("baby 2");
        String[] name={"Time","Value"};
        JTable table=new JTable(data,name){
            @Override
            public boolean isCellEditable(int row,int column)
            {
                return false;
            }
        };
        table.setColumnSelectionAllowed(false);
        table.setRowSelectionAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount()==2&&!e.isConsumed())
                {
                    String result = null;
                    int row = table.getSelectedRow();
                    int column = table.getSelectedColumn();
                    result = (String) table.getValueAt(row, column);
                    System.out.println("Row: " + row);
                    System.out.println("Column: " + column);
                    System.out.println("Selected: " + result);
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

        test.add(new JScrollPane(table));
        test.setSize(500,200);
        test.setVisible(true);

         */
    }

}
