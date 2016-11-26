package guis;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import application.*;

public class GUILogin extends JPanel
{
	private JTextField username;
	private JPasswordField password;
	private Controller controller;

	public GUILogin(Controller controller)
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
		btnPanel.setLayout(new FlowLayout());
		JButton btnLogin = new JButton("Submit for Login");
		btnLogin.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (controller.verifyLogin(username.getText(), new String(password.getPassword())))
				{
					controller.displayGUIMainMenu();
				}
				else
				{
					controller.displayError("Incorrect Username/Password combination.");
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
      btnPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		this.add(btnPanel);
	}

	public void addFields()
	{
		JPanel userPanel = new JPanel();
		userPanel.setLayout(new FlowLayout());
		JLabel user = new JLabel("User Name: ");
		username = new JTextField(20);
		userPanel.add(user);
		userPanel.add(username);

		JPanel passPanel = new JPanel();
		passPanel.setLayout(new FlowLayout());
		JLabel pass = new JLabel("Password: ");
		password = new JPasswordField(20);
		password.setEchoChar('#');
		passPanel.add(pass);
		passPanel.add(password);
      userPanel.setBorder(BorderFactory.createLineBorder(Color.black));
      passPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		this.add(userPanel);
		this.add(passPanel);
	}
}
