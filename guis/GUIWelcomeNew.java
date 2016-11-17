package guis;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class GUIWelcomeNew extends JPanel
{

	/**
	 * Setting up the panel and its contents to be displayed
	 */
	public GUIWelcomeNew()
	{
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.addButtons();
		
	}

	public void addButtons()
	{
		
		JButton signUp = new JButton("Signup");
		signUp.setAlignmentX(this.CENTER_ALIGNMENT);
		
		JButton signIn = new JButton("Signin");
		signIn.setAlignmentX(this.CENTER_ALIGNMENT);
		
		this.add(signUp);
		this.add(signIn);
		
	}
	
}
