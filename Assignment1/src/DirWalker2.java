import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVPrinter;
import java.text.SimpleDateFormat;


public class DirWalker2 {

	 public static int i=0;
     SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
     private Logger logger = Logger.getAnonymousLogger();
     private int directoryCount = 0;
     private int totalRowCount = 0;
     private static int rowsskipped=0;
     
     public int getDirectoryCount() {
 		return directoryCount;
 	}

 	public void setDirectoryCount(int directoryCount) {
 		this.directoryCount = directoryCount;
 	}

	public int getTotalRowCount() {
		return totalRowCount;
	}

	public void setTotalRowCount(int totalRowCount) {
		this.totalRowCount = totalRowCount;
	}
     
    public int  walk( String path ) {
       // File root = new File("C:\\Users\\chandan\\Documents\\GitHub\\Assignments\\Test" );
        File directory = new File(path); 
    	File[] list = directory.listFiles();
        
        if (list == null) return  0;
      
        for ( File f : list ) {
        	
        	if ( f.isDirectory() ) {
        		setDirectoryCount(getDirectoryCount() + 1);
        	   walk(f.getAbsolutePath());
            }
        	else
        	{
    		writeFile(f);
        	}
        }	
        logger.log(Level.INFO, "Directorycount - " + directoryCount + "\t Total Row Count - " + totalRowCount );
       return i;
    }
    public void writeFile(File f){
    	try {
			int cellIndex = 0;
			Reader in;
        	FileWriter fileobject = new FileWriter("Demo.csv", true);
			in= new FileReader(f);
			
			Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
			for (CSVRecord record : records) {
				
				 /*if (record[j].isEmpty())
				   {
					 rowsskipped = rowsskipped+1; 
				     System.out.println("skiiping the row " +data );
				    } */
				
				if (record.getRecordNumber() != 1) {
					for (String data : record) {
						      
							 fileobject.write(data);
						       cellIndex++;
						
						     if (cellIndex < record.size()) {
							   fileobject.write(",");
							
						    }}
					}setTotalRowCount(getTotalRowCount() + 1);
					cellIndex = 0;
					
					fileobject.write(String.format("%n"));
					
				}
			    i++;
			   
			
			 
			fileobject.close();			
		} 
		catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
		catch ( IOException e) {
			e.printStackTrace();
		}
		
    	System.out.println("Last Modified Time: " + sdf.format(f.lastModified()));
    }

    public static void main(String[] args) {
    	
    	final long startTime = System.currentTimeMillis();
    	DirWalker2 fw = new DirWalker2();
    	
        fw.walk("C:\\Users\\chandan\\Documents\\GitHub\\Assignments\\Test" );
		final long endTime = System.currentTimeMillis();
		
		System.out.println("Total execution time: " + (endTime - startTime) +" ms");
		System.out.println("Total number of rows:" +i);
		System.out.println("Total number of rows skipped" + rowsskipped);
		
    }

}