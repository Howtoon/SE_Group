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
	private JPanel userDisplayPanel;
	private JPanel changeBtnPanel;


	public GUIRestric(Controller controller, JFrame frame)
	{
		
		this.frame = frame;
		this.controller = controller;
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.addFields();
		this.addButtons();
		this.userDisplayPanel = new JPanel();
		this.changeBtnPanel = new JPanel();

	}

	public void addButtons()
	{
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new FlowLayout());
		JButton btnLookUp = new JButton("Search for Parking Lot");
		btnLookUp.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				lot = controller.getLot(lotName.getText());
				if (lot == null)
				{
					controller.displayError("Parking Lot Does Not Exist.");
				}
				else
				{
					userDisplayPanel.removeAll();
					changeBtnPanel.removeAll();
					frame.revalidate();
					displayLot();
					displayModBtn();
					System.out.println("Display Parking Lot");
				}
			}
		});
		btnPanel.add(btnLookUp);
		JButton btnGoBack = new JButton("Go Back");
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
		JPanel lotPanel = new JPanel();
		lotPanel.setLayout(new FlowLayout());
      JLabel lotLabel = new JLabel("Enter Parking Lot ID: ");
		lotName = new JTextField(20);
		lotPanel.add(lotLabel);
		lotPanel.add(lotName);

		this.add(lotPanel);

	}

	public void displayLot()
	{
      ////
		JLabel lotName = new JLabel(user.getName());
		JLabel userPermissions = new JLabel(user.getPermissions().getpString());
		userDisplayPanel.add(userName);
		userDisplayPanel.add(userPermissions);

		this.add(userDisplayPanel);
		frame.revalidate();
	}

	public void displayModBtn()
	{  
		JButton changePermission = new JButton("Toggle User Permission");
		changePermission.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (user.getPermissions().getpString() == "USER")
				{
					controller.updatePermissions (user.getName(), 1);
				}
				else if (user.getPermissions().getpString() == "ADMIN")
				{
					controller.updatePermissions (user.getName(), 0);
				}
				else if (user.getPermissions().getpString() == "SUPERVISOR")
				{
					controller.displayError("You Cannot Change The Supervisor's Permissions!!!");
				}
				user = controller.getUser(username.getText());
				if (user == null)
				{
					controller.displayError("User Does Not Exist.");
				}
				else
				{
					userDisplayPanel.removeAll();
					changeBtnPanel.removeAll();
					frame.revalidate();
					displayUser();
					displayModBtn();
					System.out.println("Display User");
				}
			}
		});
		changeBtnPanel.add(changePermission);
		this.add(changeBtnPanel);
		frame.revalidate();
	}
}
