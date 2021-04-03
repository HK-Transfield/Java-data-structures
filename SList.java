/**
 * Implements a dynamic linked list of strings
 * 
 * @author HK T
 */
public class SList {
    private SNode head; // the first node in the linked list

    /**
     * Basic unit of a SList. Contains a string value and 
     * points to the next SNode.
     * 
     * @author HK T
     */
    private class SNode {
        String key;
        SNode next;

        /**
         * Constructor.
         * 
         * @param s The data to add to the SNode
         */
        public SNode(String s) {
            this.key = s;
            this.next = null;
        }
    }

    
    /**
     * Adds a new SNode to the head of the Slist.
     * 
     * @param s The item that will be added to the node
     */
    public void add(String s) {
        SNode n = new SNode(s);
        
        n.next = head;
        head = n;
    }

    /**
     * Checks if the linked contains a node with a string parameter.
     * 
     * @param s The string 
     * @return If the linked list contains node whose string value equals s
     */
    public boolean has(String s) {
        SNode curr = head;
        boolean hasString = false;

        // loop through every node, starting from head node
        while(curr != null) {

            // check if the current node has same string
            if(curr.key.equals(s)) {

                // we have found matching string
                hasString = true;
                break;

            } else {
                // move to next SNode
                curr = curr.next;
            }
        }
        return hasString;
    }

    /**
     * Finds the first node whose string value equals s and
     * removes that node. It does not affact the order of 
     * remaining nodes.
     * 
     * @param s The item used to compare to node string values
     */
    public void remove(String s) {
        
        SNode curr = head;     // set current SNode to node after the head
        SNode prev;

        // check if there is a head node in the list
        if(curr == null)
            return;
        else if (curr != null) {

            // check if s is in the head
            if(s == curr.key) {
                head = head.next;
                return;
            } else {
                curr = head.next;
                prev = head;          // set previous SNode to head

                // loop through SList until we either reach
                while(curr != null && !curr.key.equals(s)) {
                    prev = curr;
                    curr = curr.next;

                    // if we reach end of SList without finding s, return
                    if(curr == null)
                        return;
                }
                prev.next = curr.next;
            }     
        } 
    }

    /**
     * Counts the total number of nodes contained within a linked SList instance.
     * 
     * @return An integer, representing the number of nodes in a linked list.
     */
    public int length() {
        SNode curr = head;
        int count = 0;
        
        // loop though every SNode in list. Add to counter
        while(curr != null) {
            count++;

            // move to next SNode
            curr = curr.next;
        }
        return count;        
    }

    /**
     * Checks if the SList is empty.
     * 
     * @return A boolean indicating if the SList is empty.
     */
    public boolean isEmpty() {
        return length() == 0 ? true : false;
    }

    /**
    * Print each node in the linked SList to the output stream.
    */
    public void dump() {
        SNode curr = head;

        // check if the list is empty first
        if(isEmpty())
            return;
        else {
            // loop throught each SNode until it reaches the end
            while(curr != null) {
                // print the String key value of the SNode to the console
                System.out.println(curr.key + ' ');

                // move to next SNode
                curr = curr.next;
            }
        }
    }

    /**
    * Inserts a new Snode into the list 
    * 
    * @param s The string value to be inserted into the
    */
    public void insert(String s){
        SNode n = new SNode(s);
        SNode curr = head;
        SNode prev = null;
        
        // as long as there is 
        while(curr != null && n.key.compareTo(curr.key) > 0){
            prev = curr;
            curr = curr.next;
        }

        // insert SNode at head if theres no node before it
        if(prev == null){
            head = n;
        }else{
            prev.next = n;
        }
        n.next = curr;
    }
}
