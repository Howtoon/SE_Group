package application;

import guis.*;

public class Main
{

	/**
	 * Starts the application
	 * @param args
	 */
	public static void main(String args[])
	{
		GUIController gui = new GUIController();
		Controller controller = new Controller(gui);
	}
	
}