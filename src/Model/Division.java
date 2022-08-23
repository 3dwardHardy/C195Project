package Model;

/**
 * Division class
 */
public class Division {

    /**
     * Division id field.
     */
     private int divisionId;
    /**
     * Division name field.
     */
    private String divisionInfo;
    /**
     * Country id field.
     */
    private int countryId;
     /**
     *
     * @param divisionId
     * @param divisionInfo
     * @param countryId
     * Constructor for division.
     */
    public Division(int divisionId, String divisionInfo, int countryId) {
        this.divisionId = divisionId;
        this.divisionInfo = divisionInfo;
        this.countryId = countryId;
    }

    /**
     *
     * @return
     * Gets the division id.
     */

    public int getDivisionId() {
        return divisionId;
    }

    /**
     *
     * @param divisionId
     * Sets the division id.
     */

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /**
     * @return
     * Gets the division.
     */

    public String getDivisionInfo() {
        return divisionInfo;
    }

    /**
     *
     * @param divisionInfo
     * Sets the division.
     */

    public void setDivisionInfo(String divisionInfo) {
        this.divisionInfo = divisionInfo;
    }

    /**
     *
     * @return
     * Gets the country id.
     */

    public int getCountryId() {
        return countryId;
    }

    /**
     *
     * @param countryId
     * Sets the country id.
     */

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
}
