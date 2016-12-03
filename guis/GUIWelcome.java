package guis;

import java.awt.event.*;
import javax.swing.*;

import application.Controller;
/**
 * File Name: GUIWelcome.java
 * UWF Parking App
 *
 * This class handles the gui for the welcome page.
 *
 * @author Nathan, Will
 * @version 1.0
 */
public class GUIWelcome extends JPanel
{
	/** the Controller used to call this class */
	private Controller controller;

	/**
	 * Setting up the panel and its contents to be displayed
     * @param controller main controller
	 */
	public GUIWelcome (Controller controller)
	{
		this.controller = controller;
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.addButtons();
	}

    /** Method used to add the buttons */
	public void addButtons ()
	{
		JButton signUp = new JButton("Signup");
		signUp.setAlignmentX(this.CENTER_ALIGNMENT);
		signUp.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				controller.displayGUICreateUser();
			}
		});

		JButton logIn = new JButton("Login");
		logIn.setAlignmentX(this.CENTER_ALIGNMENT);
		logIn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				controller.displayGUILogin();
			}
		});

		this.add(signUp);
		this.add(logIn);
	}
}
