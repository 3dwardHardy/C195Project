package Model;

/**
 * Country class
 */
public class Country {
    /**
     * Country id field.
     */
    private int countryIdInfo;
    /**
     * Country name field.
     */
    private String countryInfo;

    /**
     *
     * @param countryIdInfo
     * @param countryInfo
     * Constructor for country.
     */

    public Country(int countryIdInfo, String countryInfo) {
        this.countryIdInfo = countryIdInfo;
        this.countryInfo = countryInfo;
    }

    /**
     *
     * @return
     * Gets the country id.
     */

    public int getCountryIdInfo() {
        return countryIdInfo;
    }

    /**
     *
     * @param countryIdInfo
     * Sets the country id.
     */

    public void setCountryIdInfo(int countryIdInfo) {
        this.countryIdInfo = countryIdInfo;
    }

    /**
     *
     * @return
     * Gets the country.
     */

    public String getCountryInfo() {
        return countryInfo;
    }

    /**
     *
     * @param countryInfo
     * Sets the country.
     */

    public void setCountryInfo(String countryInfo) {
        this.countryInfo = countryInfo;
    }
}
