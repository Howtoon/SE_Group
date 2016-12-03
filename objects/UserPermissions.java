package objects;
/**
 * File Name: UserPermissions.java
 * UWF Parking App
 *
 * This enum stores the user permission types.
 *
 * @author Julien
 * @version 1.0
 */
public enum UserPermissions
{
	USER("USER"), ADMIN("ADMIN"), SUPERVISOR("SUPERVISOR");

	/** String associated with type */
	private String pString;

	/**
     * Sets the String to match.
     */
	UserPermissions (String p)
    {
		this.pString = p;
	}

	/** Returns the type as String */
	public String getpString() {
		return pString;
	}

	/** Sets the String type*/
	public void setpString(String pString) {
		this.pString = pString;
	}
}
