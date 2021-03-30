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
        private String key;
        private SNode next;

        /**
         * Constructor.
         * 
         * @param s The data to add to the node
         */
        public SNode(String s) {
            this.key = s;
            this.next = null;
        }

        /**
         * Gets the value of the SNode's key.
         * 
         * @return The String key of the SNode
         */
        public String getKey() {
            return key;
        }

        /**
         * Gets the SNode that the current is pointing to.
         * 
         * @return The next SNode in the SList
         */
        public SNode getNext() {
            return next;
        }

        /**
         * Sets the SNode that is next to this SNode in SList
         */
        public void setNext(SNode n) {
            this.next = n;
        }
    }

    
    /**
     * Adds a new node to the head of the list.
     * 
     * @param s The item that will be added to the node
     */
    public void add(String s) {
        SNode n = new SNode(s);
        
        n.setNext(head);
        head = n;
    }

    /**
     * Checks if the linked contains a node with a string parameter.
     * 
     * @param s The string 
     * @return If the linked list contains node whose string value equals s
     */
    public boolean hasItem(String s) {
        SNode currentSNode = head;
        boolean hasString = true;

        // loop through every node, starting from head node
        while(currentSNode != null) {

            // check if the current node has same string
            if(currentSNode.getKey().equals(s)) {
                hasString = true;
                break;
            }
            else {
                hasString = false;

                // move to next SNode
                currentSNode = currentSNode.getNext();
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
        try {
            if(head == null)
                return;
            if(s == head.getKey()) {
                head = head.getNext();
                return;
            }

            SNode currentSNode = head.getNext();
            SNode previousSNode = head;

            while(currentSNode != null && !currentSNode.getKey().equals(s)) {
                if(currentSNode == null)
                    return;

                previousSNode = currentSNode;
                currentSNode = currentSNode.getNext();
            }
            previousSNode.setNext(currentSNode.getNext());
        } catch(NullPointerException e) {
            return;
        }
    }

    /**
     * Counts the total number of nodes contained within a linked SList instance.
     * 
     * @return An integer, representing the number of nodes in a linked list.
     */
    public int length() {
        SNode currentSNode = head;
        int count = 0;
        
        // loop though every SNode in list. Add to counter
        while(currentSNode != null) {
            count++;

            // move to next SNode
            currentSNode = currentSNode.getNext();
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
        SNode currentSNode = head;

        // check if the list is empty first
        if(isEmpty())
            return;
        else {
            // loop throught each SNode until it reaches the end
            while(currentSNode != null) {
                System.out.println(currentSNode.getKey() + '\n');

                // move to next SNode
                currentSNode = currentSNode.getNext();
            }
        }
    }

    // https://www.netjstech.com/2019/03/sorted-linked-list-in-java.html
    public void insert(String s) {
        // if there are no Snodes in the list, create head
        if(head == null)
            add(s);
        else {
            SNode n = new SNode(s);
            SNode currentSNode = head;
            SNode previousSNode = null;

            while(currentSNode != null && currentSNode.getKey().compareTo(s) < 0) {
                previousSNode = currentSNode;
                currentSNode = currentSNode.getNext();
            }
            previousSNode.setNext(n);
            n.setNext(currentSNode);
        }
    }
}
