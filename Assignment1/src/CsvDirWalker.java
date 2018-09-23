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

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class CsvDirWalker {

	private static int dirCount = 0;
	private static int skippedRows = 0;
	private static int validRows = 0;
	private static String FILE_HEADER = "firstName,lastName,streetNumber,street,city,province,postalcode,Country,PhoneNumber,Emailaddress,folderDate";
	private static String folderDate = "";

	public void walk(String path) throws IOException {

		File root = new File(path);
		File[] list = root.listFiles();
		BufferedWriter writer = Files.newBufferedWriter(Paths.get("Assignment1.csv"));

		CSVPrinter csvPrinter = new CSVPrinter(writer,
				CSVFormat.DEFAULT.withHeader("First Name", "Last Name", "Street Number", "Street", "City", "Province",
						"Postal Code", "Country", "Phone Number", "Email Address", "Folder Date"));
		if (list == null)
			return;

		for (File f : list) {
			if (f.isDirectory()) {
				dirCount++;

//	              folderDate= path1[(path1.length)-2];
//	              folderDate=folderDate+"/"+path1[(path1.length)-2];
//	              folderDate=folderDate+"/"+path1[(path1.length)-3];

				System.out.println(folderDate);

				// for(String p:path1)
				// System.out.println(p);
				// String
				// temp=f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("\\")+1);
				// folderDate= folderDate +"/"+temp;
				walk(f.getAbsolutePath());
				// folderDate="";

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
		csvPrinter.close();
	}

	public void csvParser(File f, CSVPrinter csv) {

		int cellIndex = 0;
		Reader in;

//		File filename = new File("Assignment1.csv");

		try {

//			FileWriter fileobject = new FileWriter(filename, true);
//			BufferedWriter writer = Files.newBufferedWriter(Paths.get("Assignment1.csv"));
//
//			CSVPrinter csvPrinter = new CSVPrinter(writer,
//					CSVFormat.DEFAULT.withHeader("First Name", "Last Name", "Street Number", "Street", "City", "Province",
//							"Postal Code", "Country", "Phone Number", "Email Address", "Folder Date"));

//			if (filename.length() == 0)
//				fileobject.write(FILE_HEADER + "\n");

			// if(fileobject.equals(null))
			// fileobject.write(FILE_HEADER+"\n");
			in = new FileReader(f);

			Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
			for (CSVRecord record : records) {

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

				if (fName.equals("") || lName.equals("") || strNum.equals("") || street.equals("") || city.equals("")
						|| province.equals("") || postalCode.equals("") || country.equals("") || phoneno.equals("")
						|| email.equals("")) {
					skippedRows = skippedRows + 1;
				}

				else {

					if (record.getRecordNumber() != 1) {
						String[] dataArray = new String[11];
						for (String data : record) {

//							fileobject.write(data);
							dataArray[cellIndex] = data;

							cellIndex++;

//							if (cellIndex < record.size()) {
//								fileobject.write(",");
//
//							}
						}
						// if(!folderDate.isEmpty())
						// fileobject.write(","+folderDate.substring(1,folderDate.length()));
						dataArray[10] = folderDate;
						csv.printRecord(dataArray);
						cellIndex = 0;

//						CSVPrinter.write(String.format("%n"));
						validRows++;

					}

				} // end of else loop
			}
//			fileobject.close();

		} catch (ArrayIndexOutOfBoundsException ex1) {
			skippedRows = skippedRows + 1;
			ex1.printStackTrace();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}

		catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e2) {
			e2.printStackTrace();
		}

	}

	public static void main(String[] args) throws IOException {
		CsvDirWalker fw = new CsvDirWalker();
		fw.walk("C:\\Users\\chandan\\Documents\\GitHub\\Assignments\\Test");
		System.out.println("Total number of directory:" + dirCount);
		System.out.println("Total number of rows:" + validRows);
		System.out.println("Total number of skipped rows:" + skippedRows);

	}

}
