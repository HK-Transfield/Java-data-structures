import java.net.*;
import java.io.*;
import java.util.*;


/**

Reads a text file, one word at a time, 

check to see if each word is already in your SList. 
If not, the word is added to the list, otherwise it is removed. 

After the file has been processed, 
you can print out the list and check that it contains all and only those words 
that appeared an odd number of times in the text file;

confirm that the length of the list matches how many words are printed. 

 */
public class SListTest {
    public static void main(String [] args) {
        SList sl = new SList();

        try {
            sl.insert("goodness");
            sl.insert("hi");
            sl.insert("sigh");
            sl.insert("bye");

            System.out.println("----CURRENTLY CONTAINS----");
            sl.dump();
            // System.out.println(sl.length());

            // System.out.println("\n----REMOVED----");
            // sl.remove("sigh");
            // sl.dump();

            // System.out.println(sl.length());


        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
