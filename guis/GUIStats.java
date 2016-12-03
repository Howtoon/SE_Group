package guis;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import application.*;
import objects.ParkingLot;
/**
 * File Name: GUIStats.java
 * UWF Parking App
 *
 * This class handles the gui for the View Parking Statistics page.
 *
 * @author Nathan, Will
 * @version 1.0
 */
public class GUIStats extends JPanel
{
    /** the text field to enter the lot name */
    private JTextField lotName;

    /** the controller to call this class */
    private Controller controller;

    /** temporary Lot object to hold the information retrieved from the database */
    private ParkingLot lot;

    /** application frame */
    private JFrame frame;

    /** contains the lot statistics */
    private JPanel lotDisplayPanel;

    /** contains the buttons needed to search and go back */
    private JPanel changeBtnPanel;

    /** contains map */
    private JLabel lotImage;

    /**
     * Constructor that prepares the gui
     * @param controller the program controller
     * @param frame application frame
     */
    public GUIStats (Controller controller, JFrame frame)
    {
        this.frame = frame;
        this.controller = controller;
        this.lotImage = new JLabel("");
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.addFields();                                                 //add search field
        this.addButtons();                                                //add search and go-back buttons
        this.lotDisplayPanel = new JPanel();                              //create global var for panel to display lot info
        this.lotDisplayPanel.setLayout(new BoxLayout(this.lotDisplayPanel, BoxLayout.PAGE_AXIS));
        this.changeBtnPanel = new JPanel();                               //create global var for panel for toggle button
        this.add(lotDisplayPanel);
        this.add(changeBtnPanel);
        this.add(lotImage);
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
        btnLookUp.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                lot = controller.getLot(lotName.getText());        //Look up Lot by LotID and assign ParkingLot object to var lot
                if (lot == null)
                {
                    controller.displayError("Parking Lot Does Not Exist.");
                } else
                {
                    lotDisplayPanel.removeAll();           //Remove all previous lot info
                    changeBtnPanel.removeAll();            //Remove all toggle buttons
                    frame.revalidate();                    //revalidate to ensure everything cleared
                    displayLot();                          //redisplay lot info after change has been made
                    drawParkingLot(lot.getLotID().toLowerCase());
                    System.out.println("Display Parking Lot");
                }
            }
        });
        btnPanel.add(btnLookUp);                        //Add search buttons
        JButton btnGoBack = new JButton("Go Back");     //returns user to GUIMainMenu()
        btnGoBack.addActionListener(new ActionListener()
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
    public void addFields()
    {
        JPanel fieldPanel = new JPanel();                           //search field panel
        fieldPanel.setLayout(new FlowLayout());
        JLabel lotLabel = new JLabel("Enter Parking Lot ID: ");
        lotName = new JTextField(20);
        fieldPanel.add(lotLabel);
        fieldPanel.add(lotName);
        fieldPanel.setBackground(Color.CYAN);
        fieldPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(fieldPanel);
    }

    /** Displays the lot's statistics */
    public void displayLot ()
    {
        String restric;
        JLabel nameLabel = new JLabel("Lot ID: " + lotName.getText());
        if (lot.isOpen() == true)
        {
            restric = "Open";
        } else
        {
            restric = "Closed";
        }
        JLabel total = new JLabel("Total Spaces: " + lot.getTotal());
        JLabel occupied = new JLabel("Number of Cars: " + lot.getOccupied());
        JLabel free = new JLabel("Available Spaces: " + (lot.getTotal() - lot.getOccupied()));
        JLabel lotRestriction = new JLabel("This lot is: " + restric);
        lotDisplayPanel.add(nameLabel);
        lotDisplayPanel.add(total);
        lotDisplayPanel.add(occupied);
        lotDisplayPanel.add(free);
        lotDisplayPanel.add(lotRestriction);
        lotDisplayPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        //this.add(lotDisplayPanel);
        frame.revalidate();
    }

    /** Displays the lot's map */
    private void drawParkingLot (String lotID)
    {
        BufferedImage bi = null;
        try
        {
            bi = ImageIO.read(new File(String.format("resources/%s_lot.png", lotID.toLowerCase())));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        Image img = bi.getScaledInstance(this.getWidth()/2, this.getHeight()/2, Image.SCALE_FAST);
        ImageIcon icon = new ImageIcon(img);
        lotImage.setIcon(icon);
        lotImage.setAlignmentX(this.CENTER_ALIGNMENT);
        this.revalidate();
    }
}
