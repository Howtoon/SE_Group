package guis;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.*;

import application.Controller;
import objects.ParkingLot;

public class GUIReport extends JPanel
{

    private Controller controller;
    private ParkingLot lot;
    private JPanel stats;
    JLabel spaceAvail = new JLabel("Spaces Available: --");
    JLabel violations = new JLabel("Violations: --");
    private JLabel lotImage;

    public GUIReport(Controller controller)
    {

        this.controller = controller;
        this.lotImage = new JLabel("");
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.initializeStats();
        this.addComponents();
        this.add(lotImage);
        this.revalidate();
        this.repaint();

    }

    private void initializeStats()
    {

        stats = new JPanel();
        stats.setLayout(new BoxLayout(stats, BoxLayout.PAGE_AXIS));
        stats.add(spaceAvail);
        stats.add(violations);

        this.add(stats);

    }

    private void viewStats()
    {

        spaceAvail.setText("Spaces Available: " + lot.getAvailable());
        this.revalidate();

    }

    private void updateStats()
    {

        JPanel update = new JPanel();

        JLabel availLabel = new JLabel("Spaces Available: ");
        JTextField availField = new JTextField(20);


        JLabel violationLabel = new JLabel("Violations: ");
        JTextField violationField = new JTextField(20);

        JButton btnUpdate = new JButton("Update Statistics");
        btnUpdate.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {

                try
                {
                    int space = Integer.parseInt(availField.getText());
                    lot = controller.updateLotCars(lot.getLotID(), space);
                    if (lot != null)
                    {
                        controller.displayError("Report was successfully submitted");
                        viewStats();
                    }
                    if (lot == null)
                    {
                        controller.displayError("An error occurred while updating lot information");
                    }

                    repaint();
                }
                catch (Exception e1)
                {
                    e1.printStackTrace();
                }
            }
        });

        update.add(availLabel);
        update.add(availField);
        update.add(violationLabel);
        update.add(violationField);
        update.add(btnUpdate);

        this.add(update);

    }

    /*private void addStatistics()
    {
        JPanel componentPanel = new JPanel();

        JLabel spaceAvail = new JLabel("Enter # of spaces available: ");
        JTextField numSpaces = new JTextField(5);

        JButton btnAddStats = new JButton("Add Statistics");
        btnAddStats.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                int space = Integer.parseInt(numSpaces.getText());
                ParkingLot result = controller.updateLotCars(parkingLotID, space);
                if (result != null)
                {
                    controller.displayError("Report was successfully submitted");
                }
                if (result == null)
                {
                    controller.displayError("An error occurred while updating lot information");
                }
            }
        });

        componentPanel.add(spaceAvail);
        componentPanel.add(numSpaces);
        componentPanel.add(btnAddStats);

        this.add(componentPanel);
        this.revalidate();
    }*/

    private void addComponents()
    {

        JPanel componentPanel = new JPanel();

        JLabel lotID = new JLabel("Lot ID:");
        JTextField lotField = new JTextField(20);

        JButton btnViewLot = new JButton("View Parking Lot");
        btnViewLot.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                lot = controller.getLot(lotField.getText());
                if (lot == null)
                    controller.displayError("There is no lot with that ID");
                else if (lotField.getText().equals(""))
                    controller.displayError("There is not a lot specified");
                else
                {

                    drawParkingLot(lot.getLotID().toLowerCase());
                    viewStats();

                }
                repaint();
            }
        });

        componentPanel.add(lotID);
        componentPanel.add(lotField);
        componentPanel.add(btnViewLot);


        JButton btnGoBack = new JButton("Go Back");
        btnGoBack.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                controller.displayGUIMainMenu();
            }
        });

        this.add(componentPanel);
        this.updateStats();
        this.add(btnGoBack);

    }

    private void drawParkingLot(String lotID)
    {
        BufferedImage bi = null;
        try
        {

            ImageIcon imageicon = new ImageIcon(String.format("resources/%s_lot.png", lotID));

            bi = new BufferedImage(imageicon.getIconWidth(), imageicon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = (Graphics2D) bi.createGraphics();
            g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
            g2d.drawImage(imageicon.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);

        }
        catch (Exception e)
        {

//            System.out.println("Here is the lot id: " + lotID);
            e.printStackTrace();
        }

        lotImage.setText(lotID);
        lotImage.setIcon(new ImageIcon(bi));

        this.revalidate();
    }

    public void paintComponent(Graphics g)
    {

//        System.out.println("Reload");
        if (lotImage.getIcon() != null)
        {
            drawParkingLot(lotImage.getText());
        }

    }


}