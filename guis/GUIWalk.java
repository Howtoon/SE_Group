package guis;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import application.Controller;

public class GUIWalk extends JPanel
{

	Controller controller;

	public GUIWalk(Controller controller)
	{

		this.controller = controller;
		this.addButtons();

	}

	private void addButtons()
	{

		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				controller.displayGUIMainMenu();
			}
		});
		this.add(btnGoBack);

	}


	public void paintComponent(Graphics g)
	{
		
		BufferedImage bi = null;
		try
		{

			ImageIcon imageicon = new ImageIcon("resources/walking_times.png");
			bi = new BufferedImage(imageicon.getIconWidth(), imageicon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics2D g2d = (Graphics2D) bi.createGraphics();
			g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
			g2d.drawImage(imageicon.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		g.drawImage(bi, 0, 0, this);

	}

}