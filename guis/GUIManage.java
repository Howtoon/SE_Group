package guis;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import application.*;
import objects.User;
import objects.UserPermissions;
import database.*;

public class GUIManage extends JPanel
{

    private JTextField username;
	private Controller controller;
	private User user;
	private JFrame frame;
	private JPanel userDisplayPanel;
	private JPanel changeBtnPanel;


	public GUIManage(Controller controller, JFrame frame)
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
      btnPanel.setBackground(Color.CYAN);
		JButton btnLogin = new JButton("Search for User");
		btnLogin.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
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
		btnPanel.add(btnLogin);
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
		JPanel userPanel = new JPanel();
		userPanel.setLayout(new FlowLayout());
      JLabel userLabel = new JLabel("Enter User Name: ");
		username = new JTextField(20);
		userPanel.add(userLabel);
		userPanel.add(username);
      userPanel.setBackground(Color.CYAN);

		this.add(userPanel);

	}

	public void displayUser()
	{
		JLabel userName = new JLabel(user.getName());
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
      changeBtnPanel.setBackground(Color.ORANGE);
		changeBtnPanel.add(changePermission);
		this.add(changeBtnPanel);
		frame.revalidate();
	}
}
