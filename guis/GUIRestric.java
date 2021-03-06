package guis;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import application.*;
import objects.ParkingLot;
/**
 * File Name: GUIRestric.java
 * UWF Parking App
 *
 * This class handles the gui for the Toggle Parking Lot Status page.
 *
 * @author Nathan, Will
 * @version 1.0
 */
public class GUIRestric extends JPanel
{
    /** the text field to enter in the lot's name */
    private JTextField lotName;

    /** the controller to call this class */
    private Controller controller;

    /** temporary lot object to update and add to the database */
    private ParkingLot lot;

    /** application frame */
    private JFrame frame;

    /** contains the lot display */
    private JPanel lotDisplayPanel;

    /** contains the button to toggle */
    private JPanel changeBtnPanel;

    /**
     * Constructor that prepares the gui
     * @param controller the program controller
     * @param frame application frame
     */
    public GUIRestric (Controller controller, JFrame frame)
    {
        this.frame = frame;
        this.controller = controller;
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.addFields();                                                 //add search field
        this.addButtons();                                                //add search and go-back buttons
        this.lotDisplayPanel = new JPanel();                              //create global var for panel to display lot info
        this.changeBtnPanel = new JPanel();                               //create global var for panel for toggle button
        this.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    /** Adds the buttons */
    public void addButtons ()
    {
        JPanel btnPanel = new JPanel();
        btnPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        btnPanel.setLayout(new FlowLayout());
        btnPanel.setBackground(Color.CYAN);
        JButton btnLookUp = new JButton("Search for Parking Lot");
        btnLookUp.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        lot = controller.getLot(lotName.getText());        //Look up Lot by LotID and assign ParkingLot object to var lot
                        if (lot == null)
                        {
                            controller.displayError("Parking Lot Does Not Exist.");
                        }
                        else
                        {
                            lotDisplayPanel.removeAll();           //Remove all previous lot info
                            changeBtnPanel.removeAll();            //Remove all toggle buttons
                            frame.revalidate();                    //revalidate to ensure everything cleared
                            displayLot();                          //redisplay lot info after change has been made
                            displayModBtn();                       //reload toggle buttons
                            System.out.println("Display Parking Lot");
                        }
                    }
                });
        btnPanel.add(btnLookUp);                        //Add search buttons
        JButton btnGoBack = new JButton("Go Back");     //returns user to GUIMainMenu()
        btnGoBack.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
            {
               controller.displayGUIMainMenu();
            }
                });
        btnPanel.add(btnGoBack);
        this.add(btnPanel);
    }

    /** Adds the text fields */
    public void addFields ()
    {
        JPanel fieldPanel = new JPanel();                           //search field panel
        fieldPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        fieldPanel.setLayout(new FlowLayout());
        JLabel lotLabel = new JLabel("Enter Parking Lot ID: ");
        lotName = new JTextField(20);
        fieldPanel.add(lotLabel);
        fieldPanel.add(lotName);
        fieldPanel.setBackground(Color.CYAN);

        this.add(fieldPanel);
    }

    /** Displays the lot retrieved */
    public void displayLot ()
    {
        String restric;
        JLabel nameLabel = new JLabel(lotName.getText());
        if (lot.isOpen() == true)
        {
            restric = "Open";
            System.out.println("JLabel should say open");
        }
        else
        {
            restric = "Closed";
            System.out.println("JLabel should say closed");
        }
        JLabel lotRestriction = new JLabel(restric);
        lotDisplayPanel.add(nameLabel);
        lotDisplayPanel.add(lotRestriction);
        System.out.println("Updated Open/Closed JLabel");
        this.add(lotDisplayPanel);
        frame.revalidate();
    }

    /** Sets the functionality of the toggle button */
    public void displayModBtn ()
    {
        JButton changeRestriction = new JButton("Toggle Lot Permission");
        changeRestriction.addActionListener(
         new ActionListener()
         {
            public void actionPerformed(ActionEvent e)
            {
               if (lot.isOpen() == true)
               {
                  controller.setOpen(false);
                  controller.updateLotStatus(lot.getLotID(),false);
               }
               else if (lot.isOpen() == false)
               {
                  controller.setOpen(true);
                  controller.updateLotStatus(lot.getLotID(),true);
               }
               String lotNm = lot.getLotID();
               if (lot == null)
               {
                  controller.displayError("Lot Does Not Exist.");
               }
               else
               {
                  lotDisplayPanel.removeAll();
                  changeBtnPanel.removeAll();
                  frame.revalidate();
                  lot = controller.getLot(lotName.getText());
                  displayLot();
                  displayModBtn();
                  System.out.println("Display Lot");
               }
            }
         });
        changeBtnPanel.setBackground(Color.ORANGE);
        changeBtnPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        changeBtnPanel.add(changeRestriction);
        this.add(changeBtnPanel);
        frame.revalidate();
    }
}
