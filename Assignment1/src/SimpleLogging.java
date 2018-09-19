import java.util.logging.Level;
import java.util.logging.Logger;

public class SimpleLogging {

	public static void main(String[] args) {

		final long startTime = System.currentTimeMillis();
			
	      System.setProperty("java.util.logging.config.file",
	              "./logging.properties");
		
		for (int i = 0; i < 1000; i++) {
		  Logger.getLogger("Main").log(Level.INFO,"This is a log message "+i);
		}
		final long endTime = System.currentTimeMillis();

		System.out.println("Total execution time: " + (endTime - startTime) +" ms");

	}

}
