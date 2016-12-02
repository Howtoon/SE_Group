package application;

import guis.*;
/**
 * File Name: Main.java
 * UWF Parking App
 *
 * Simple class to hold the main method.
 * Creates GUI Controller and Controller.
 *
 * @author Nathan, Will
 * @version 1.0
 */
public class Main
{
	/**
	 * Starts the application
	 * @param args
	 */
	public static void main (String args[])
	{
		GUIController gui = new GUIController();
		Controller controller = new Controller(gui);
	}
}