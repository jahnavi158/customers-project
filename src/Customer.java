import java.io.BufferedReader;
import java.util.logging.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mysql.jdbc.Driver;

public class Customer {

	public static void openFile() throws IOException, SQLException, ClassNotFoundException {
		// private String ""/Users/jahnavi/Documents/Info.csv;
		Logger logger = Logger.getLogger("Customer");
		FileHandler fh;

		BufferedReader csvReader = null;
		try {
			csvReader = new BufferedReader(new FileReader("/Users/jahnavi/Documents/Info.csv"));
		} catch (FileNotFoundException fe) {
			fe.printStackTrace();
		}
		String row;
		int recieved = 0;
		int success = 0;
		int fail = 0;
		List<List<String>> rows = new ArrayList<List<String>>();
		while ((row = csvReader.readLine()) != null) {
			String[] data = row.split(",");
			System.out.println(data[0]);
			// List<List<String>> rows = new ArrayList<List<String>>();
			if (!(data[0].isEmpty()) && !(data[1].isEmpty()) && !(data[2].isEmpty()) && !(data[3].isEmpty())
					&& !(data[4].isEmpty()) && !(data[5].isEmpty()) && !(data[6].isEmpty()) && !(data[7].isEmpty())
					&& !(data[8].isEmpty()) && !(data[9].isEmpty())) {
				// DriverManager.registerDriver(new Driver());
				Class.forName("org.sqlite.JDBC");
				String url = "jdbc:sqlite:CustomerData.db";
				Connection con = DriverManager.getConnection(url);
				Statement stmt = con.createStatement();
				stmt.executeUpdate("insert into cInfo values ('" + data[0] + "','" + data[1] + "','" + data[2] + "','"
						+ data[3] + "','" + data[4] + "','" + data[5] + "','" + data[6] + "','" + data[7] + "','"
						+ data[8] + "','" + data[9] + "')");
				
				success++;
			} else {
				List<String> dt = Arrays.asList(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7],
						data[8], data[9]);
				rows.add(dt);
				fail++;
			}
			recieved++;
			// csvReader.close();

			// do something with the data
		}
		FileWriter csvWriter = new FileWriter("newfile-bad.csv");
		csvWriter.append("A");
		csvWriter.append(",");
		csvWriter.append("B");
		csvWriter.append(",");
		csvWriter.append("C");
		csvWriter.append(",");
		csvWriter.append("D");
		csvWriter.append(",");
		csvWriter.append("E");
		csvWriter.append(",");
		csvWriter.append("F");
		csvWriter.append(",");
		csvWriter.append("G");
		csvWriter.append(",");
		csvWriter.append("H");
		csvWriter.append(",");
		csvWriter.append("I");
		csvWriter.append(",");
		csvWriter.append("J");
		csvWriter.append("\n");

		for (List<String> rowData : rows) {
			csvWriter.append(String.join(",", rowData));
			csvWriter.append("\n");
		}

		csvWriter.flush();
		csvWriter.close();
		
		try {  

	        // This block configure the logger with handler and formatter  
	        fh = new FileHandler("/Users/jahnavi/Documents/Customers/MyLogFile.log");  
	        logger.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter);  

	        // the following statement is used to log any messages  
	        logger.info("# of recieved records " + recieved);  
	        logger.info("# of successful records " + success);
	        logger.info("# of failed records " + fail);

	    } catch (SecurityException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  

	}

	public static void main(String args[]) throws IOException, SQLException, ClassNotFoundException {
		openFile();
	}
}
