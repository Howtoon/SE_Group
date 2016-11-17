package guis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import application.Controller;

public class GUIWelcome extends JPanel
{

	private Controller controller;
	
	/**
	 * Setting up the panel and its contents to be displayed
	 */
	public GUIWelcome(Controller controller)
	{
		
		this.controller = controller;
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.addButtons();
		
	}

	public void addButtons()
	{
		
		JButton signUp = new JButton("Signup");
		signUp.setAlignmentX(this.CENTER_ALIGNMENT);
		signUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.displayGUICreateUser();
			}
		});
		
		JButton logIn = new JButton("Login");
		logIn.setAlignmentX(this.CENTER_ALIGNMENT);
		logIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.displayGUILogin();
			}
		});
		
		this.add(signUp);
		this.add(logIn);
		
	}
	
}
