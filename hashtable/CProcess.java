// CProcess:
// Tests student CHashtable class by processing a file of records.
// Data input line is expected to be three comma-separated strings: last_name,phone1,email
//   E.g. "Didio,03-8174-9123,rebbecca.didio@didio.com.au"
//
// This program asks that data filename and hashtable size be supplied as arguments
// The phone_number is the default record key, but changing the "select" variable, near
// the top of the try-catch block, to have the value 0 will use last_name and to
// value 2 will use email ... but you have to recompile, of course :-)
// I'll change this selection index to be an argument when I actually test.
//
// author: Tony C Smith
// date: 28/5/2021
//

import java.io.FileReader;
import java.io.BufferedReader;

class CProcess{
    
    public static void main(String [] args){

	if(args.length!=2){
	    System.err.println("Usage: java CProcess data.csv HASHTABLESIZE");
	    return;
	}
    
	try{
	    // open input
	    BufferedReader br = new BufferedReader(new FileReader(args[0]));
	    // get table size and create a table
	    int size=Integer.parseInt(args[1]);
	    CHashtable table = new CHashtable(size);
	    
	    // read file one line at a time, create a record and put it in the table
	    
	    CData rec; // one for put()
	    CData tmp; // and one for get()
	    int select = 0;  // which data field is the key .. 0, 1 or 2
	    
	    String line; // one line of input
	    String [] f; // data fields parsed out of input line
	    int count=0; // count how many records have been created
	    int preprobe=0; // used to compute how many probes are happening
	    
	    while((line=br.readLine())!=null){
			count++;
			f = line.split(",");
			
			// not expected to test with bad input, but just in case ...
			if(f.length != 3){
				System.err.println("Malformed input, abort [line "+count+"]: "+line);
				return;
			}
			
			rec = new CData(f[0],f[1],f[2],select);
			
			preprobe = table.getProbes();
			table.put(rec.getKey(),rec); //+1 probe
			preprobe = table.getProbes() - preprobe; /// how many probes for this put()

			// Format some performance output, then display
			String countStr = "count: "	+count;
			String loadStr = "  load: "     +String.format("%.2f",table.getLoad());
			String probeStr = " probes: "   +table.getProbes();
			String expectStr = " expected: "+String.format("%.2f", (1+(table.getLoad()/2)));
			String actualStr = " actual: "  +preprobe;
			String aveStr = " average: "    +String.format("%.2f",table.getProbes()/(1.0*count));
			String keystring = rec.getKey().getKeystring();
			
			System.out.println("Put: "      + keystring + " @ " + rec.getKey().hashCode());
			System.out.println(countStr + loadStr + probeStr + expectStr + actualStr + aveStr);
			
			// check the record was inserted, and test get() failures
			tmp = table.get(rec.getKey()); // +1 probe		

			if(tmp==null) 
				System.out.println("Get fails! "+count);
			else
				if(tmp.getKey().equals(rec.getKey())) 
					System.out.println("  Get succeeds.\n");
				else 
					System.out.println("  Got wrong record.\n");

	    }
	}catch(Exception e){
	    System.err.println("Something went wrong! "+e);
	}
    }
    
}

