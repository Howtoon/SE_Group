package guis;

import application.Controller;
import objects.ParkingLot;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by natha on 11/26/2016.
 */
public class GUIAddLot extends JPanel
{

    private Controller controller;
    private ParkingLot lot;

    public GUIAddLot(Controller controller)
    {

//        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.controller = controller;
        this.addComponents();

    }

    private void addComponents()
    {

        JLabel idLabel = new JLabel("Lot ID:");
        JTextField idText = new JTextField(20);
        this.add(idLabel);
        this.add(idText);

        JLabel totalLabel = new JLabel("Number of Spaces: ");
        JTextField totalText = new JTextField(20);
        this.add(totalLabel);
        this.add(totalText);

        JLabel reservedLabel = new JLabel("Number of Reserved:");
        JTextField reservedText = new JTextField(20);
        this.add(reservedLabel);
        this.add(reservedText);

        JLabel handicapLabel = new JLabel("Number of Handicap:");
        JTextField handicapText = new JTextField(20);
        this.add(handicapLabel);
        this.add(handicapText);

        JLabel commuterLabel = new JLabel("Number of Commuter:");
        JTextField commuterText = new JTextField(20);
        this.add(commuterLabel);
        this.add(commuterText);

        JLabel residentLabel = new JLabel("Number of Resident:");
        JTextField residentText = new JTextField(20);
        this.add(residentLabel);
        this.add(residentText);

        JLabel staffLabel = new JLabel("Number of Staff:");
        JTextField staffText = new JTextField(20);
        this.add(staffLabel);
        this.add(staffText);

        JLabel visitorLabel = new JLabel("Number of Visitor:");
        JTextField visitorText = new JTextField(20);
        this.add(visitorLabel);
        this.add(visitorText);

        JLabel motorcycleLabel = new JLabel("Number of Motorcycle:");
        JTextField motorcycleText = new JTextField(20);
        this.add(motorcycleLabel);
        this.add(motorcycleText);

        JButton btnCreateLot = new JButton("Create Parking Lot");
        btnCreateLot.setAlignmentX(this.CENTER_ALIGNMENT);
        btnCreateLot.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {

                try
                {
                    controller.createLot(idText.getText(), Integer.parseInt(totalText.getText()),
                            Integer.parseInt(reservedText.getText()), Integer.parseInt(handicapText.getText()),
                            Integer.parseInt(commuterText.getText()), Integer.parseInt(residentText.getText()),
                            Integer.parseInt(staffText.getText()), Integer.parseInt(visitorText.getText()),
                            Integer.parseInt(motorcycleText.getText()), true);
                            controller.displayError("The Parking Lot was Successfully Created");
                }
                catch (Exception e1)
                {

                    e1.printStackTrace();
                    controller.displayError("An Error Occurred While Creating The Parking Lot");

                }
            }

        });
        this.add(btnCreateLot);

        JButton btnGoBack = new JButton("Go Back");     //returns user to GUIMainMenu()
        btnGoBack.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                controller.displayGUIMainMenu();
            }
        });
        this.add(btnGoBack);

    }

}
