/**
 * Implementation of an associative array abtract data type. This structure 
 * maps a CKey object to a CData object. It computes a index location via
 * a hash function using the CKey object.
 * 
 * @author HK Transfield
 */
public class CHashtable implements CHashInterface {
    private CData[] table;  // an array of CData references (the hash table)
    private int capacity;   // total number of CData the table can store
    private int count;      // number of CData objects currently in the table
    private int probes;     // number of probes that have occurred for all gets, puts, and collisions

    /**
     * Constructor. Creates a new CHashtable with
     * a size that is passed in.
     * 
     * @param size The size of the hashtable
     */
    public CHashtable(int capacity) {
        this.capacity = capacity;
        this.table = new CData[capacity];
    }

     /**
     * Takes a hashcode from a CKey object and then calculates the 
     * index location the CData will placed in.s
     * 
     * @param hashCode The integer value used to calculate the index.
     * @return The index of the table
     */
    private int hash(int hashCode) {
        int hash = hashCode % capacity; // modulo division
    
        // make sure that our hash is not negative
        if (hash < 0)
            hash += capacity;

        return hash;
    }

    /**
     * Adds a CData into the table at a location determined by the hashcode
     * of a CKey after collision resolution, which is completed using
     * linear probing.
     * 
     * @param ck The key associated with the CData being added
     * @param cd The CData to be added to the table
     */
    public void put(CKey ck, CData cd) {
        int index = hash(ck.hashCode());

        if (count != capacity - 1) { // check that this put() will not completely fill the table to max capacity
            while (table[index] != null && !table[index].getKey().equals(ck)) { // loop through table until a free location is found
                probes++; // visited a location in the table
                
                // move to next location
                index = (index + 1) % capacity; // linear probing
            }
            count++;    // increase total number of CData    
            probes++;   // probe empty location
            
            //  found a free location, store CData here
            table[index] = cd;
        }
    }

    /**
     * Finds and returns a reference to the CData stored in the table, or
     * null if the item is not found.
     * 
     * @param ck The CKey used to identify the CData
     * @return The CData stored at the location given by the CKey
     */
    public CData get(CKey ck) {
        int index = hash(ck.hashCode()); 

        while (table[index] != null) {
            probes++;
            
            // found CData with matching CKey
            if (table[index].getKey().equals(ck)) 
                return table[index];

            // move to next location
            index = (index + 1) % capacity; // linear probing
        }
        
        return null;
    }

    /**
     * Returns the count of every attempted access to a
     * table location, including collisions.
     * 
     * @return A reference to the probes field.
     */
    public int getProbes() {
        return probes;
    }

    /** 
     * The load factor is defined as n(count)/S(Capacity). To
     * make sure that our program is performing well, we need
     * to check that probes < 1+load/2. If this is not true,
     * the our hash function is bad.
     * 
     * @return The current load factor of the table.
     */ 
    public double getLoad() {
        return (double)count / (double)capacity;
    }
}
