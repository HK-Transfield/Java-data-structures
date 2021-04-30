import java.io.FileReader;
import java.io.BufferedReader;

/**
 * Tests a binary search tree by adding words that appear an odd number of 
 * times in a text file. It will delete words that appear an even number of
 * times. It will the print the contents of the BST to an output stream.
 * 
 * @author HK Transfield
 */
public class OddWords {
    public static void main(String [] args){
        
        if(args.length != 1) { // check that the user has input a text file
          System.err.println("[USAGE]");
          System.err.println("Print to text file: java OddWords <filename> > <filename>");
          System.err.println("Print to console: java OddWords <filename>");
          return;
        }

        // create new instance of the BST
        BSTlex bst = new BSTlex();
    
        try{
          BufferedReader br = new BufferedReader(new FileReader(args[0]));
          String s=br.readLine();
          String [] tokens;

          while(s!=null) { // Loop through the list until we reach end of file
            
            // find words that are a sequence of alpha-numeric characters
            tokens = s.split("[^a-zA-Z0-9]+");
            
            for(int i=0;i < tokens.length;i++) { // loop through each word from the file
              
              // deletes word that appears an even amount 
              if(bst.find(tokens[i].toLowerCase())) {
                bst.remove(tokens[i].toLowerCase());
                System.out.println("DELETED");
    
                
                // print error if failed removed
                if(bst.find(tokens[i].toLowerCase()))
                  System.err.println("Remove failed for: " + tokens[i].toLowerCase());
    
              } else{
                // insert a new word into the BST
                bst.insert(tokens[i].toLowerCase());
                System.out.println("INSERTED");
                
                // print error if insert did not work
                if(!bst.find(tokens[i].toLowerCase()))
                  System.err.println("add failed for: " + tokens[i].toLowerCase());
              }
            }

            // move to next line
            s=br.readLine(); 
          }

          // finished reading the file
          br.close();

        } catch(Exception e) {
            System.err.println("EXCEPTION: " + e);
        }
        
        // print height and all items from the BST
        System.out.println("\nLEXICON:"); 
        bst.dump(); 
        System.out.print("\nHeight: " + bst.height()); 
    }
}
