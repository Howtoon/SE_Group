package guis;

/**
 * Created by julienpugh on 11/16/16.
 */
public enum UserPermissions {
    USER("User"), ADMIN("Admin"), SUPERVISOR("Supervisor");

    private String pString;

    UserPermissions (String p) {
        this.pString = p;
    }

    public String getpString() {
        return pString;
    }

    public void setpString(String pString) {
        this.pString = pString;
    }
}
