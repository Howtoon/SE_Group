package guis;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import application.*;
import objects.User;
import objects.UserPermissions;
import objects.ParkingLot;
import database.*;

public class GUIRestric extends JPanel
{

   private JTextField lotName;
	private Controller controller;
	private ParkingLot lot;
	private JFrame frame;
	private JPanel lotDisplayPanel;
	private JPanel changeBtnPanel;


	public GUIRestric(Controller controller, JFrame frame)
	{
		
		this.frame = frame;
		this.controller = controller;
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.addFields();                                                 //add search field
		this.addButtons();                                                //add search and go-back buttons
		this.lotDisplayPanel = new JPanel();                              //create global var for panel to display lot info
		this.changeBtnPanel = new JPanel();                               //create global var for panel for toggle button

	}

	public void addButtons()
	{
		JPanel btnPanel = new JPanel();
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

	public void addFields()
	{
		JPanel fieldPanel = new JPanel();                           //search field panel
		fieldPanel.setLayout(new FlowLayout());
      JLabel lotLabel = new JLabel("Enter Parking Lot ID: ");     
		lotName = new JTextField(20);
		fieldPanel.add(lotLabel);
		fieldPanel.add(lotName);
      fieldPanel.setBackground(Color.CYAN);

		this.add(fieldPanel);

	}

	public void displayLot()
	{
      String restric;
		JLabel nameLabel = new JLabel(lotName.getText());
		if (lot.isOpen() == true)
      {
         restric = "Open";
      }
      else
      {
         restric = "Closed";
      }
      JLabel lotRestriction = new JLabel(restric);
		lotDisplayPanel.add(nameLabel);
		lotDisplayPanel.add(lotRestriction);

		this.add(lotDisplayPanel);
		frame.revalidate();
	}

	public void displayModBtn()
	{  
		JButton changeRestriction = new JButton("Toggle Lot Permission");
		changeRestriction.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (lot.isOpen() == true)
				{
					controller.setOpen(false);
				}
				else if (lot.isOpen() == false)
				{
					controller.setOpen(true);
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
					displayLot();
					displayModBtn();
               frame.revalidate();
					System.out.println("Display Lot");
				}
			}
		});
      changeBtnPanel.setBackground(Color.ORANGE);
		changeBtnPanel.add(changeRestriction);
		this.add(changeBtnPanel);
		frame.revalidate();
	}
}
