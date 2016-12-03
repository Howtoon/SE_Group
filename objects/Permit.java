package objects;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * File Name: Permit.java
 * UWF Parking App
 *
 * This class represents a user's permit.
 * Currently it is unused but will be considered
 * for full implementation in the next implementation.
 *
 * @author Will, Julien
 * @version 1.0
 */
public class Permit
{
	private String id;
	private String type;
	DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
	Date expirationDate;

	public Permit ()
    {
		this.id = "00000";
		this.type = "Commuter";
		setExpirationDate("01/01/01");
	}

	public Permit (String id, String type, String date)
    {
		this.id = id;
		this.type = type;
		setExpirationDate(date);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date date) {
		this.expirationDate = date;
	}

	public void setExpirationDate (String date)
	{
		try
		{
			this.expirationDate = formatter.parse(date);
		}
		catch (ParseException p)
		{
			System.out.println("exception in parsing date - Permit.java");
		}
	}
}
