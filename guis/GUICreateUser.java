package guis;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import application.*;

public class GUICreateUser extends JPanel
{

	private JTextField username;
	private JPasswordField password;

	private Controller controller;

	public GUICreateUser(Controller controller)
	{

		this.controller = controller;
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.addFields();
		this.addButtons();
      this.setBorder(BorderFactory.createLineBorder(Color.black));

	}

	public void addButtons()
	{

		JPanel btnPanel = new JPanel();
      btnPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		btnPanel.setLayout(new FlowLayout());
		JButton btnLogin = new JButton("Create New User");
		btnLogin.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				boolean verify = controller.verifyDuplicate(username.getText(), new String(password.getPassword()));
				if (verify)
				{
					controller.displayGUIMainMenu();
				}
				else
				{
					controller.displayError("Username already exists.");
				}
			}
		});
		btnPanel.add(btnLogin);

		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				controller.displayGUIWelcome();
			}
		});
		btnPanel.add(btnGoBack);

		this.add(btnPanel);  
	}

	public void addFields()
	{
		JPanel userPanel = new JPanel();
      userPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		userPanel.setLayout(new FlowLayout());
		JLabel user = new JLabel("User Name: ");
		username = new JTextField(20);
		userPanel.add(user);
		userPanel.add(username);

		JPanel passPanel = new JPanel();
      passPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		passPanel.setLayout(new FlowLayout());
		JLabel pass = new JLabel("Password: ");
		password = new JPasswordField(20);
		password.setEchoChar('#');
		passPanel.add(pass);
		passPanel.add(password);

		this.add(userPanel);
		this.add(passPanel);
	}
}