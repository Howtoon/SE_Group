package guis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import application.Controller;

public class GUIWelcomeNew extends JPanel
{

	private Controller controller;
	
	/**
	 * Setting up the panel and its contents to be displayed
	 */
	public GUIWelcomeNew(Controller controller)
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
				
			}
		});
		
		JButton signIn = new JButton("Signin");
		signIn.setAlignmentX(this.CENTER_ALIGNMENT);
		signUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.displayGUILogin();
			}
		});
		
		this.add(signUp);
		this.add(signIn);
		
	}
	
}
