package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Appointment class
 */
public class Appointment {
    /**
     * appointment id field.
     */
    private int appointmentIdInfo;
    /**
     * title field.
     */
    private String titleInfo;
    /**
     * description field.
     */
    private String descriptionInfo;
    /**
     * location field.
     */
    private String locationInfo;
    /**
     * type field.
     */
    private String typeInfo;
    /**
     * start Date field.
     */
    private LocalDate startDateInfo;
    /**
     * Start time field.
     */
    private LocalDateTime startTimeInfo;
    /**
     * end Date field.
     */
    private LocalDate endDateInfo;
    /**
     * end time field.
     */
    private LocalDateTime endTimeInfo;
    /**
     * Customer id field.
     */
    private int customerIdInfo;
    /**
     * User id field.
     */
    private int userIdInfo;
    /**
     * contact field.
     */
    private int contactIdInfo;
    /**
     * contact name field.
     */
    private String contactNameInfo;


    /**
     *
     * @param appointmentIdInfo
     * @param titleInfo
     * @param descriptionInfo
     * @param locationInfo
     * @param typeInfo
     * @param startDateInfo
     * @param startTimeInfo
     * @param endDateInfo
     * @param endTimeInfo
     * @param customerIdInfo
     * @param userIdInfo
     * @param contactIdInfo
     * @param contactNameInfo
     * Constructor for appointment.
     */

    public Appointment(int appointmentIdInfo, String titleInfo, String descriptionInfo, String locationInfo, String typeInfo, LocalDate startDateInfo, LocalDateTime startTimeInfo, LocalDate endDateInfo, LocalDateTime endTimeInfo, int customerIdInfo, int userIdInfo, int contactIdInfo, String contactNameInfo) {
        this.appointmentIdInfo = appointmentIdInfo;
        this.titleInfo = titleInfo;
        this.descriptionInfo = descriptionInfo;
        this.locationInfo = locationInfo;
        this.typeInfo = typeInfo;
        this.startDateInfo = startDateInfo;
        this.startTimeInfo = startTimeInfo;
        this.endDateInfo = endDateInfo;
        this.endTimeInfo = endTimeInfo;
        this.customerIdInfo = customerIdInfo;
        this.userIdInfo = userIdInfo;
        this.contactIdInfo = contactIdInfo;
        this.contactNameInfo = contactNameInfo;
    }

    /**
     *
     * @return
     * Gets the appointment id.
     */

    public int getAppointmentIdInfo() {
        return appointmentIdInfo;
    }

    /**
     *
     * @param appointmentIdInfo
     * Sets the appointment id.
     */

    public void setAppointmentIdInfo(int appointmentIdInfo) {
        this.appointmentIdInfo = appointmentIdInfo;
    }

    /**
     *
     * @return
     * Gets the title.
     */

    public String getTitleInfo() {
        return titleInfo;
    }

    /**
     *
     * @param titleInfo
     * Sets the title.
     */

    public void setTitleInfo(String titleInfo) {
        this.titleInfo = titleInfo;
    }

    /**
     *
     * @return
     * Gets the description.
     */

    public String getDescriptionInfo() {
        return descriptionInfo;
    }

    /**
     *
     * @param descriptionInfo
     * Sets the description.
     */

    public void setDescriptionInfo(String descriptionInfo) {
        this.descriptionInfo = descriptionInfo;
    }

    /**
     *
     * @return
     * Gets the location.
     */

    public String getLocationInfo() {
        return locationInfo;
    }

    /**
     *
     * @param locationInfo
     * Sets the location.
     */

    public void setLocationInfo(String locationInfo) {
        this.locationInfo = locationInfo;
    }

    /**
     *
     * @return
     * Gets the type.
     */

    public String getTypeInfo() {
        return typeInfo;
    }

    /**
     *
     * @param typeInfo
     * Sets the type.
     */

    public void setTypeInfo(String typeInfo) {
        this.typeInfo = typeInfo;
    }

    /**
     *
     * @return
     * Gets the start date.
     */

    public LocalDate getStartDateInfo() {
        return startDateInfo;
    }

    /**
     *
     * @param startDateInfo
     * Sets the start date.
     */

    public void setStartDateInfo(LocalDate startDateInfo) {
        this.startDateInfo = startDateInfo;
    }

    /**
     *
     * @return
     * Gets the start time.
     */

    public LocalDateTime getStartTimeInfo() {
        return startTimeInfo;
    }

    /**
     *
     * @param startTimeInfo
     * Sets the start time.
     */

    public void setStartTimeInfo(LocalDateTime startTimeInfo) {
        this.startTimeInfo = startTimeInfo;
    }

    /**
     *
     * @return
     * Gets the end date.
     */

    public LocalDate getEndDateInfo() {
        return endDateInfo;
    }

    /**
     *
     * @param endDateInfo
     * Sets the end date.
     */

    public void setEndDateInfo(LocalDate endDateInfo) {
        this.endDateInfo = endDateInfo;
    }

    /**
     *
     * @return
     * Gets the end time.
     */

    public LocalDateTime getEndTimeInfo() {
        return endTimeInfo;
    }

    /**
     *
     * @param endTimeInfo
     * Sets the end time.
     */

    public void setEndTimeInfo(LocalDateTime endTimeInfo) {
        this.endTimeInfo = endTimeInfo;
    }

    /**
     *
     * @return
     * Gets the customer id.
     */

    public int getCustomerIdInfo() {
        return customerIdInfo;
    }

    /**
     *
     * @param customerIdInfo
     * Sets the customer id.
     */

    public void setCustomerIdInfo(int customerIdInfo) {
        this.customerIdInfo = customerIdInfo;
    }

    /**
     *
     * @return
     * Gets the user id.
     */

    public int getUserIdInfo() {
        return userIdInfo;
    }

    /**
     *
     * @param userIdInfo
     * Sets the user id.
     */

    public void setUserIdInfo(int userIdInfo) {
        this.userIdInfo = userIdInfo;
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
}
