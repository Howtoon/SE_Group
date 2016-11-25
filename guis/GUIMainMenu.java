package guis;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import application.*;
import objects.User;
import objects.UserPermissions;

public class GUIMainMenu extends JPanel
{
	private Controller controller;
	private User user;

	public GUIMainMenu(Controller controller, User user)
	{  
		this.controller = controller;
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));         
		this.setBackground(Color.WHITE);
		this.user = user;
		this.addButtons();
	}

	public void addButtons()
	{
		JButton btnPortal = new JButton("Parking Transaction Portal");
		btnPortal.setAlignmentX(this.CENTER_ALIGNMENT);
		btnPortal.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					String URL = "https://uwfparking.t2hosted.com/cmn/auth_ext.aspx";
					java.awt.Desktop.getDesktop().browse(java.net.URI.create(URL));
				}
				catch (Exception a)
				{
					JOptionPane.showMessageDialog(null,a.getMessage());
				}
			}
		});
		this.add(btnPortal);

		JButton btnRegs = new JButton("View Parking Regulations");
		btnRegs.setAlignmentX(this.CENTER_ALIGNMENT);
		btnRegs.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				controller.displayGUIRegs();
			}
		});
		this.add(btnRegs);

		JButton btnStats = new JButton("View Parking Statistics");
		btnStats.setAlignmentX(this.CENTER_ALIGNMENT);
		btnStats.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				controller.displayGUIReport();
			}
		});
		this.add(btnStats);

		JButton btnWalk = new JButton("View Walking Times");
		btnWalk.setAlignmentX(this.CENTER_ALIGNMENT);
		btnWalk.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				controller.displayGUIWalk();
			}
		});
		this.add(btnWalk);

		JButton btnMaps = new JButton("View Parking Lot Maps");
		btnMaps.setAlignmentX(this.CENTER_ALIGNMENT);
		btnMaps.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				controller.displayGUIMaps();
			}
		});
		this.add(btnMaps);

		JButton btnRestric = new JButton("View Parking Lot Restrictions");
		btnRestric.setAlignmentX(this.CENTER_ALIGNMENT);
		btnRestric.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

			}
		});
		this.add(btnRestric);

		if (user.getPermissions()== UserPermissions.SUPERVISOR || user.getPermissions()==UserPermissions.ADMIN)
		{
			JButton btnReport = new JButton("Create Parking Lot Report");
			btnReport.setAlignmentX(this.CENTER_ALIGNMENT);
			btnReport.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					controller.displayGUIReport();
				}
			});
			this.add(btnReport);

			JButton btnViolate = new JButton("Record Parking Violation");
			btnViolate.setAlignmentX(this.CENTER_ALIGNMENT);
			btnViolate.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{

				}
			});
			this.add(btnViolate);
		}
		if (user.getPermissions()==UserPermissions.SUPERVISOR)
		{

			JButton btnManage = new JButton("Manage Administrators");
			btnManage.setAlignmentX(this.CENTER_ALIGNMENT);
			btnManage.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					controller.displayGUIManage();
				}
			});
			this.add(btnManage);

		}

		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setAlignmentX(this.CENTER_ALIGNMENT);
		btnLogOut.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				user = null;
				controller.logOut();
			}
		});
		this.add(btnLogOut);

	}
}