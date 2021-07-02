/**
 * A unique key object to identify a CData object. Using a 
 * keystring passed from the CData, it generates a hashcode 
 * integer.
 * 
 * @author HK Transfield
 */
public class CKey {
    private String keystring;

    /**
     * Constructor.
     * 
     * @param keystring The string used to generate the hashcode
     */
    public CKey(String keystring) {
        this.keystring = keystring;
    }

    /**
     * Gets the private Keystring field of a CKey instance.
     * 
     * @return A reference to the Keystring
     */
    public String getKeystring() {
        return keystring;
    }

    @Override
    /**
     * Takes the keystring field and turns into into an integer representation
     * 
     * @return The hashcode integer of a given string.
     */
    public int hashCode() {
        int hashCode = 0;

        // create an integer representation of the keystring
        for (int i = 0; i < keystring.length(); i++) 
            hashCode = hashCode * 31 + keystring.charAt(i);

        /*--Hashing techniques--*/
        /* uncomment the hashing technique you wish to test */
        
        // digit extraction
        hashCode =
            ((hashCode / 1000000000 * 10000000) +  
            (hashCode / 100000000 % 10 * 1000000) + 
            (hashCode / 10000000 % 10 * 100000) + 
            (hashCode / 1000000 % 10 * 10000) + 
            (hashCode / 100000 % 10 * 1000) + 
            (hashCode / 10000 % 10 * 100) + 
            (hashCode / 1000 % 10 * 10) + 
            (hashCode % 10));

        // // folding
        // hashCode = 
        //     ((hashCode / 100000000) + 
        //     (hashCode / 1000000 % 10000) + 
        //     (hashCode / 10000 % 10000) + 
        //     (hashCode / 100 % 10000) + 
        //     (hashCode % 10000));

        return hashCode;
    }

    @Override
    /**
     * Checks the equailty of an object based on the value held
     * within the keystring.
     * 
     * @param obj The object used to compare equailty with the CKey
     * @return The object matches the CKey
     */
    public boolean equals(Object obj) {
        
        // checks if object is an instance of 'this'
        if (this == obj) 
            return true;

        // checks if null
        if (obj == null) 
            return false;

        // checks if the object is a CKey
        if (getClass() != obj.getClass()) 
            return false;

        CKey other = (CKey)obj;

        // checks the object's keystring
        if (this.getKeystring() == other.getKeystring()) 
            return true;
    
        return false;
    }
}
