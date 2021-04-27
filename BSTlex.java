/**
 * A Java implementation of a binary search tree that can store strings of words.
 * 
 * @author HK Transfield
 */
public class BSTlex {
    
    TNode root; // first TNode in the entire BST

    /**
     * An entry in the BST which stores a string key value. It also
     * stores child TNodes for left and right subtrees.
     */
    private class TNode {
        String key;
        TNode left, right;

        /**
         * Constructor method.
         * 
         * @param s The string value the TNode will store
         */
        public TNode(String s) {
            key = s;
            left = right = null;
        }
    }

     /**
     * Inserts a String value into the BST, based lexicographical value.
     * 
     * @param s The string value to be inserted into the list
     */
    public void insert(String s) {

        TNode temp = new TNode(s);

        if(root == null) { // there is a root TNode in the BST
            root = temp;

        } else { // insert TNode somewhere in the list

            TNode curr = root; // start at root
            
            while(!curr.key.equals(s)) { // loop through BST until new TNode is inserted
                
                if(s.compareTo(curr.key) < 0) { // insert temp based on what 
                    
                    // move to the left subtree
                    if(curr.left == null)
                        curr.left = temp;

                    curr = curr.left;

                } else {

                    // move to the right subtree
                    if(curr.right == null)
                        curr.right = temp;

                    curr = curr.right;
                }
            }
        }
        printPath(s);
    }

    /**
     * Removes a TNode that contains a String value passed in 
     * to the method. It will then restructure the subtree as
     * needed.
     * 
     * @param s The String key value to remove from the BST.
     */
    public void remove(String s) {    
        printPath(s);

        // call remove recursion method
        removeRec(root, s);
    }
    
    /**
     * Recurively loops through the BST to delete the 
     * TNode with the matching key value.
     * 
     * @param root The root TNode of a subtree
     * @param s The String value to remove
     * 
     * @return The root TNode with a modified subtree
     */
    private TNode removeRec(TNode root, String s) {

        TNode curr = root;      // start at root of BST 
        TNode parent = null;    // parent of TNode being removed
        
        while(curr != null && !curr.key.equals(s)) { // loop through list to find TNode with key value
            parent = curr;

            // move down left or right branch by comparing s with curr's key value
            curr = s.compareTo(curr.key) < 0 ? curr.left : curr.right;
        }

        // item is not found in the BST
        if (curr == null) 
            return root;

        /*the subtree must be modified and potentially restructured
        depending which of theses three cases*/
        if(curr.left == null && curr.right == null) { // case 1: leaf TNode, has no children
            
            if(curr != root) { //  if root is not being removed

                // remove the parent's child
                if(parent.left == curr)
                    parent.left = null;
                else
                    parent.right = null;

            } else
                root = null; // remove root

        } else if(curr.left != null && curr.right != null) { // case 2: TNode has 2 children
            
            // traverse down to leftmost TNode in right child
            TNode successor = minimumKey(curr.right);
            
            // recursive loop through list to remove successor from its current position
            removeRec(root, successor.key);

            // replace current's key with it's successor
            curr.key = successor.key;

        } else if(curr.left != null ^ curr.right != null) { // case 3: TNode has 1 child
            
            // find which child is being removed 
            TNode child = curr.left != null ? curr.left : curr.right;

            if(curr != root) { // not removing the root TNode

                // remove the parent's child
                if(parent.left == curr)
                    parent.left = child;
                else
                    parent.right = child;

            } else
                root = child; // replace temp with child
        }
        return root;
    }

    /**
     * Finds leftmost TNode in a subtree with the smallest value
     * from the right child.
     * 
     * @param curr The right TNode of the root
     * 
     * @return The TNode with the smallest key value in a subtree
     */
    private TNode minimumKey(TNode curr) {
        while (curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }

    /**
     * Prints the path from the root TNode of a subtree
     * to a TNode with a passed string value.
     * 
     * @param root The root TNode of the subtree
     * @param s The string value to find in the BST
     */
    private void printPath(String s) {
        if(find(s)) {

            TNode curr = root; // start at root
        
            System.out.print(curr.key + " ");

            /*loop through until the end of the BST has 
            been reached or a matching key value is found*/
            while(curr != null && !curr.key.equals(s)) { 
                curr = s.compareTo(curr.key) < 0 ? curr.left : curr.right;
                
                System.out.print(curr.key + " ");
            }
        } else {
            System.err.println("ERROR: Cannot find " + s);
        }
    }

    /**
     * Checks if the BST is empty by checking 
     * the state of the root of the BST.
     * 
     * @return A boolean representing if the root exists or not.
     */
    public boolean isEmpty() {
        return root==null;
    }

    /**
     * Iterates through the the BST to find a TNode 
     * with a matching key value to s.
     * 
     * @param s The string value to search for
     * @return A boolean representing whether s is found within the BST
     */
    public boolean find(String s) {
        TNode curr = root;
        
         /*loop through the list until we reach the end or
        find a matching key value*/
        while(curr != null && !curr.key.equals(s)) {
            curr = s.compareTo(curr.key) < 0 ? curr.left : curr.right; 
        }
        return !(curr == null);
    }

    /**
     * Prints all key values in the BST based on
     * lexicographical order.
     */
    public void dump() {
        inOrderRecur(root);
    }

    /**
     * Recursively loops through the BST to print out 
     * the key value of each TNode by traversing the
     * left and right subtrees of a root Tnode.
     * 
     * @param root The root TNode of a subtree to traverse
     */
    private void inOrderRecur(TNode root) {
        if(root == null)
            return;
        
        inOrderRecur(root.left);
        System.out.println(root.key);
        inOrderRecur(root.right);
    }

    /**
     * Finds the height of the tree from the lowest TNode
     * to the root TNode of the entire BST.
     * 
     * @return The height of the BST
     */
    public int height() {
        return heightRecur(root);
    }

    /**
     * 
     * @param temp The root TNode of a subtree
     * @return The height of a subtree
     */
    private int heightRecur(TNode temp) {
        
        if(temp == null)
            return 0;

        else {
            // recursively calculate left and right subtree heights
            int leftHeight = heightRecur(temp.left);
            int rightHeight = heightRecur(temp.right);
            
            // returns the subtree with the greater height
            return leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;  
        }
    }
}
