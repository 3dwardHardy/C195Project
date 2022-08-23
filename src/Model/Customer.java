package Model;

/**
 * Customer class
 */
public class Customer {
    /**
     * Customer id field.
     */
    private int CustomerIdInfo;
    /**
     * Customer name field.
     */
    private String customerNameInfo;
    /**
     * Address field.
     */
    private String addressInfo;
    /**
     * Postal code field.
     */
    private String postalCodeInfo;
    /**
     * Phone Number field.
     */
    private String phoneNumberInfo;
    /**
     * Discount field.
     */
    private String divisionInfo;
    /**
     * Country field.
     */
    private String countryInfo;
    /**
     * Discount field.
     */
    private int divisionIdInfo;


    /**
     *
     * @param customerIdInfo
     * @param customerNameInfo
     * @param addressInfo
     * @param postalCodeInfo
     * @param phoneNumberInfo
     * @param divisionInfo
     * @param countryInfo
     * @param divisionIdInfo
     * Constructor for customer.
     */

    public Customer(int customerIdInfo, String customerNameInfo, String addressInfo, String postalCodeInfo, String phoneNumberInfo, String divisionInfo, String countryInfo, int divisionIdInfo) {
        CustomerIdInfo = customerIdInfo;
        this.customerNameInfo = customerNameInfo;
        this.addressInfo = addressInfo;
        this.postalCodeInfo = postalCodeInfo;
        this.phoneNumberInfo = phoneNumberInfo;
        this.divisionInfo = divisionInfo;
        this.countryInfo = countryInfo;
        this.divisionIdInfo = divisionIdInfo;
    }

    /**
     *
     * @return
     * Gets the customer id.
     */

    public int getCustomerIdInfo() {
        return CustomerIdInfo;
    }

    /**
     *
     * @param customerIdInfo
     * Sets the customer id.
     */

    public void setCustomerIdInfo(int customerIdInfo) {
        CustomerIdInfo = customerIdInfo;
    }

    /**
     *
     * @return
     * Gets the customer name.
     */

    public String getCustomerNameInfo() {
        return customerNameInfo;
    }

    /**
     *
     * @param customerNameInfo
     * Sets the customer name.
     */

    public void setCustomerNameInfo(String customerNameInfo) {
        this.customerNameInfo = customerNameInfo;
    }

    /**
     *
     * @return
     * Gets the address.
     */

    public String getAddressInfo() {
        return addressInfo;
    }

    /**
     *
     * @param addressInfo
     * Sets the address.
     */

    public void setAddressInfo(String addressInfo) {
        this.addressInfo = addressInfo;
    }

    /**
     *
     * @return
     * Gets the postal code.
     */

    public String getPostalCodeInfo() {
        return postalCodeInfo;
    }

    /**
     *
     * @param postalCodeInfo
     * Sets the postal code.
     */

    public void setPostalCodeInfo(String postalCodeInfo) {
        this.postalCodeInfo = postalCodeInfo;
    }

    /**
     *
     * @return
     * Gets the phone number.
     */

    public String getPhoneNumberInfo() {
        return phoneNumberInfo;
    }

    /**
     *
     * @param phoneNumberInfo
     * Sets the phone number.
     */

    public void setPhoneNumberInfo(String phoneNumberInfo) {
        this.phoneNumberInfo = phoneNumberInfo;
    }

    /**
     *
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

    /**
     *
     * @return
     * Gets the division id.
     */

    public int getDivisionIdInfo() {
        return divisionIdInfo;
    }

    /**
     *
     * @param divisionIdInfo
     * Sets the division id.
     */

    public void setDivisionIdInfo(int divisionIdInfo) {
        this.divisionIdInfo = divisionIdInfo;
    }
}
