import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class DirWalker_f{

	private static int dirCount = 0;
	private static int skippedRows = 0;
	private static int validRows = 0;
	
	private static String folderDate = "";
	private static Logger logger = Logger.getAnonymousLogger();
	static long writeStartTime;
	 static long writeEndTime;

	public void walk(String path,CSVPrinter csvPrinter) throws IOException {

		File root = new File(path);
		File[] list = root.listFiles();
		if (list == null)
			return;

		for (File f : list) {
			if (f.isDirectory()) {
				dirCount++;
				walk(f.getAbsolutePath(),csvPrinter);

			} else {
			    String[] path1 = f.getAbsolutePath().toString().split(File.separator + File.separator);
				folderDate = (path1[(path1.length) - 4]) + "/"
						+ (path1[(path1.length) - 3].length() > 1 ? path1[(path1.length) - 3]
								: "0" + path1[(path1.length) - 3])
						+ "/" + (path1[(path1.length) - 2].length() > 1 ? path1[(path1.length) - 2]
						: "0" + path1[(path1.length) - 2]);
				csvParser(f, csvPrinter);
			
			}
		}

	}

	public void csvParser(File f, CSVPrinter csv) {

		int cellIndex = 0;
		Reader in;

		try {

			in = new FileReader(f);
			
			Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
			for (CSVRecord record : records) {
				if (record.getRecordNumber() == 1)
					continue;
				String fName = record.get(0);
				String lName = record.get(1);
				String strNum = record.get(2);
				String street = record.get(3);
				String city = record.get(4);
				String province = record.get(5);
				String postalCode = record.get(6);
				String country = record.get(7);
				String phoneno = record.get(8);
				String email = record.get(9);
				
				
				if (fName.trim().equals("") || lName.trim().equals("") || strNum.equals("") || street.equals("") || city.equals("")
						|| province.equals("") || postalCode.equals("") || country.equals("") || phoneno.equals("")
						|| email.equals("")) {
					skippedRows = skippedRows + 1;
				} else {


						String[] dataArray = new String[] {fName,lName,strNum,street,city,province,postalCode,country,phoneno,email,folderDate};
		
						csv.printRecord(dataArray);
						
						
						
						cellIndex = 0;
						validRows++;

				}
			}

		} catch (ArrayIndexOutOfBoundsException ex1) {
			skippedRows++;
			logger.log(Level.SEVERE, ex1.getLocalizedMessage());
			ex1.printStackTrace();	
		} catch (FileNotFoundException ex) {
			skippedRows++;
			ex.printStackTrace();
			logger.log(Level.SEVERE, ex.getLocalizedMessage());
		} catch (IOException e) {
			skippedRows++;
			e.printStackTrace();
			logger.log(Level.SEVERE,e.getMessage());
		} catch (Exception e2) {
			skippedRows++;
			logger.log(Level.SEVERE, e2.getLocalizedMessage());
			e2.printStackTrace();
		}

	}
	
	public static void writeFileLog()
	{
		try {
			 int totalRows =validRows+skippedRows;
			FileWriter fileobject = new FileWriter("logs.txt", false);
			fileobject.write("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			fileobject.write(String.format("%n") + "Total Stats of Scan : " + String.format("%n"));
			fileobject.write("\t\t Total Files - " + totalRows + String.format("%n"));
			fileobject.write("\t\t Total Directories - " + dirCount + String.format("%n"));
			fileobject.write("\t\t Total Rows - " + validRows+ String.format("%n"));
			fileobject.write("\t\t Total Cells - " + skippedRows+ String.format("%n"));
			fileobject.write("\t\t Total Write Time(time it takes to write the files to a file) - "
					+ (writeEndTime - writeStartTime) + " ms" + String.format("%n"));
			fileobject.close();
		}
		catch(Exception e) {
			logger.log(Level.SEVERE, e.getLocalizedMessage());
			e.printStackTrace();
		}
	
	}

	public static void main(String[] args) throws IOException {
		DirWalker_f fw = new DirWalker_f();
		 writeStartTime = System.currentTimeMillis();

		BufferedWriter writer = Files.newBufferedWriter(Paths.get("Assignment1.csv"));

		CSVPrinter csvPrinter = new CSVPrinter(writer,
				CSVFormat.DEFAULT.withHeader("First Name", "Last Name", "Street Number", "Street", "City", "Province",
						"Postal Code", "Country", "Phone Number", "Email Address", "Folder Date"));
		
		fw.walk("C:\\Users\\chandan\\Documents\\GitHub\\Assignments\\Sample Data\\Sample Data", csvPrinter);
		csvPrinter.flush();
		csvPrinter.close();
		 writeEndTime = System.currentTimeMillis();
		 writeFileLog();
		logger.log(Level.INFO, "Time taken to walk through and write - " +(writeEndTime - writeStartTime) + " ms");
		logger.log(Level.INFO, "Valid Number of Rows Read  - " + validRows);
		logger.log(Level.INFO, " Skipped Number of  Rows  - " +skippedRows);
	}

}
