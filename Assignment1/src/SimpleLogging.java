import java.util.logging.Level;
import java.util.logging.Logger;

public class SimpleLogging {

	public static void main(String[] args) {

		final long startTime = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
		  Logger.getAnonymousLogger().log(Level.INFO,"Message"+i);
		}
		final long endTime = System.currentTimeMillis();

		System.out.println("Total execution time: " + (endTime - startTime) +" ms");
		
		

	}

}
