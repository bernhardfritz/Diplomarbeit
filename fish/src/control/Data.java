package control;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

public class Data {

	public static String driver;
	public static String dburl;
	public static String user;
	public static String pass;
	public static String table;
	public static String netioip;
	public static int netioport;
	public static int futtermax;
	public static double r;
	public static double u;
	public static double k;
	public static double d;
	public static double korrektur;
	public static Logger logger;
	
	public Data()
	{
		logger = Logger.getLogger("Logger");
		Properties prop = new Properties();
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("server.properties");
		try {
			prop.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver = prop.getProperty("driver");
		dburl = prop.getProperty("dburl");
		user = prop.getProperty("user");
		pass = prop.getProperty("pass");
		table = prop.getProperty("table");
		netioip = prop.getProperty("netioip");
		netioport = Integer.parseInt(prop.getProperty("netioport"));
		futtermax = Integer.parseInt(prop.getProperty("futtermax"));
		r = Double.parseDouble(prop.getProperty("r"));
		u = Double.parseDouble(prop.getProperty("u"));
		k = Double.parseDouble(prop.getProperty("k"));
		d = Double.parseDouble(prop.getProperty("d"));
		korrektur = Double.parseDouble(prop.getProperty("korrektur"));
		logger.info("server.properties eingelesen!");
	}
}