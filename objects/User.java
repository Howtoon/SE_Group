package objects;
/**
 * File Name: User.java
 * UWF Parking App
 *
 * This class represents the user.
 *
 * @author Will, Julien
 * @version 1.0
 */
public class User
{
    /** the username */
    private String name;

    /** the user password */
    private String userID;

    /** user's permit */
    private Permit permit;

    /** user's permissions (can be user, admin or supervisor) */
    private UserPermissions permissions;

    /**
     * Constructor to create a blank User object.
     */
    public User ()
    {
        this.name = "";
        this.userID = "0000";
        this.permit = new Permit();
        this.permissions = UserPermissions.USER;
    }

    /**
     * Constructor that can be used for signing up, logging in, etc.
     * @param uName user name
     * @param uID user pass
     */
    public User(String uName, String uID)
    {
        this.name = uName;
        this.userID = uID;
        this.permit = new Permit();
        this.permissions = UserPermissions.USER;
    }

    /** Returns the username */
    public String getName() {
        return name;
    }

    /** Sets the username */
    public void setName(String name) {
        this.name = name;
    }

    /** Returns the user password */
    public String getUserID() {
        return userID;
    }

    /** Sets the user password */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /** Returns the user's permit */
    public Permit getPermit() {
        return permit;
    }

    /** Sets the user's permit */
    public void setPermit(Permit permit) {
        this.permit = permit;
    }

    /** Returns the users permissions */
    public UserPermissions getPermissions() {
        return permissions;
    }

    /** Sets the user's permissions */
    public void setPermissions(UserPermissions permissions) {
        this.permissions = permissions;
    }
}
