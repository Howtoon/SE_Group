package guis;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.*;

import application.Controller;
import objects.ParkingLot;

public class GUIReport extends JPanel
{

	private Controller controller;
	private ParkingLot lot;
	private JLabel lotImage;
	

	public GUIReport(Controller controller)
	{

		this.controller = controller;
		this.lotImage = new JLabel("");
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.addComponents();
		this.addStatistics();
		this.add(lotImage);

	}

	private void addComponents()
	{

		JPanel componentPanel = new JPanel();

		JLabel lotID = new JLabel("Lot ID:");
		JTextField lotField = new JTextField(20);

		JButton btnViewLot = new JButton("View Parking Lot");
		btnViewLot.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				lot = controller.getLot(lotField.getText());
				if(lot == null)
					controller.displayError("There is no lot with that ID");
				else if(lotField.getText().equals(""))
					controller.displayError("There is not a lot specified");
				else
					drawParkingLot(lot.getLotID().toLowerCase());
				
			}

		});

		componentPanel.add(lotID);
		componentPanel.add(lotField);
		componentPanel.add(btnViewLot);
		
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				controller.displayGUIMainMenu();
			}
		});
		
		this.add(componentPanel);
		this.add(btnGoBack);

	}
	
	private void addStatistics()
	{
		
		
		
	}
	
	private void drawParkingLot(String lotID)
	{
		
		
		BufferedImage bi = null;
		try
		{

			ImageIcon imageicon = new ImageIcon(String.format("resources/%s_lot.png", lotID));
			
			bi = new BufferedImage(imageicon.getIconWidth(), imageicon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics2D g2d = (Graphics2D) bi.createGraphics();
			g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
			g2d.drawImage(imageicon.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);

		}
		catch(Exception e)
		{

			System.out.println("Here is the lot id: " + lotID);
			e.printStackTrace();
		}

		lotImage.setIcon(new ImageIcon(bi));
		
		this.revalidate();
		
	}
	

}