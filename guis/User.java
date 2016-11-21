package guis;

public class User {
    private String name;
    private String userID;
    private Permit permit;
    private UserPermissions permissions;

    public User() {
        this.name = "";
        this.userID = "0000";
        this.permit = null;
        this.permissions = UserPermissions.USER;
    }

    /**
     * Constructor that can be used for signing up, logging in, etc.
     * @param uName user name
     * @param uID user pass
     */
    public User(String uName, String uID) {
        this.name = uName;
        this.userID = uID;
        this.permit = null;
        this.permissions = UserPermissions.USER;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Permit getPermit() {
        return permit;
    }

    public void setPermit(Permit permit) {
        this.permit = permit;
    }

    public UserPermissions getPermissions() {
        return permissions;
    }

    public void setPermissions(UserPermissions permissions) {
        this.permissions = permissions;
    }
}
