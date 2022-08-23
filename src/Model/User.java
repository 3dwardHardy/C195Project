package Model;

/**
 * User class
 */
public class User {

    /**
     * user id field.
     */
     private int userId;
    /**
     * user name field.
     */
    private String usernameInfo;
    /**
     * password field.
     */
    private String passwordInfo;
     /**
     *
     * @param userId
     * @param usernameInfo
     * @param passwordInfo
      * Constructor for user.
     */

    public User(int userId, String usernameInfo, String passwordInfo) {
        this.userId = userId;
        this.usernameInfo = usernameInfo;
        this.passwordInfo = passwordInfo;
    }

    /**
     *
     * @return
     * Gets the user id.
     */

    public int getUserId() {
        return userId;
    }

    /**
     *
     * @param userId
     * Sets the user id.
     */

    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     *
     * @return
     * Gets the username.
     */

    public String getUsernameInfo() {
        return usernameInfo;
    }

    /**
     *
     * @param usernameInfo
     * Sets the username.
     */

    public void setUsernameInfo(String usernameInfo) {
        this.usernameInfo = usernameInfo;
    }

    /**
     *
     * @return
     * Gets the password.
     */

    public String getPasswordInfo() {
        return passwordInfo;
    }

    /**
     *
     * @param passwordInfo
     * Sets the password.
     */

    public void setPasswordInfo(String passwordInfo) {
        this.passwordInfo = passwordInfo;
    }
}
