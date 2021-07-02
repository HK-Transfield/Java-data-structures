/**
 * A representation of a collection of strings types containing user data 
 * from Australia and Canada. It has a CKey object associated with it, 
 * which is used to calculate an index location in a CHashtable and 
 * uniquely identify it. 
 * 
 * @author HK Transfield
 */
public class CData {
    private String name;
    private String phone;
    private String email;
    private CKey key;

    /**
     * Constructor.
     * 
     * @param name Name of user being stored
     * @param phone Phone number of the user
     * @param email Email address of the user
     * @param selector Used to determine which string to use to generate the CKey
     */
    public CData(String name, String phone, String email, int selector) {
        this.name = name;
        this.phone = phone;
        this.email = email;

        // Generate CKey based on what selector value is passed
        if (selector == 0 || selector > 2)
            this.key = new CKey(name);
        else if (selector == 1)
            this.key = new CKey(phone);
        else
            this.key = new CKey(email);
    }

    /**
     * Gets the private CKey field of the CData.
     * 
     * @return A reference to the private CKey object
     */
    public CKey getKey() {
        return key;
    }

    @Override
    /**
     * Returns a String that is a concatenation of the three values in
     * the String memeber variables, sperated with spaces.
     * 
     * @return All fields of the CData in a single string.
     */
    public String toString() {
        return name + " " + phone + " " + email;
    }
}
