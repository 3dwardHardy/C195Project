package Model;

/**
 * Contact class
 */
public class Contact {
    private int contactIdInfo;
    private String contactNameInfo;
    private String contactEmailInfo;

    /**
     *
     * @param contactIdInfo
     * @param contactNameInfo
     * @param contactEmailInfo
     * Constructor for contact.
     */

    public Contact(int contactIdInfo, String contactNameInfo, String contactEmailInfo) {
        this.contactIdInfo = contactIdInfo;
        this.contactNameInfo = contactNameInfo;
        this.contactEmailInfo = contactEmailInfo;
    }

    /**
     *
     * @return
     * Gets the contact id.
     */

    public int getContactIdInfo() {
        return contactIdInfo;
    }

    /**
     *
     * @param contactIdInfo
     * Sets the contact id.
     */

    public void setContactIdInfo(int contactIdInfo) {
        this.contactIdInfo = contactIdInfo;
    }

    /**
     *
     * @return
     * Gets the contact name.
     */

    public String getContactNameInfo() {
        return contactNameInfo;
    }

    /**
     *
     * @param contactNameInfo
     * Sets the contact name.
     */

    public void setContactNameInfo(String contactNameInfo) {
        this.contactNameInfo = contactNameInfo;
    }

    /**
     *
     * @return
     * Gets the contact email.
     */

    public String getContactEmailInfo() {
        return contactEmailInfo;
    }

    /**
     *
     * @param contactEmailInfo
     * Sets the contact email.
     */

    public void setContactEmailInfo(String contactEmailInfo) {
        this.contactEmailInfo = contactEmailInfo;
    }
}
