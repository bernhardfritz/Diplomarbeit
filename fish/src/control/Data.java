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
	public static String fishconfig;
	public static String fishgraph;
	public static String fishprefix;
	public static int netioport;
	public static int futtermax;
	public static float m;
	public static float r;
	public static float u;
	public static float k;
	public static float d;
	public static float korrektur;
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
		fishconfig = prop.getProperty("fishconfig");
		fishgraph = prop.getProperty("fishgraph");
		fishprefix = prop.getProperty("fishprefix");
		netioport = Integer.parseInt(prop.getProperty("netioport"));
		futtermax = Integer.parseInt(prop.getProperty("futtermax"));
		m = Float.parseFloat(prop.getProperty("m"));
		r = Float.parseFloat(prop.getProperty("r"));
		u = Float.parseFloat(prop.getProperty("u"));
		k = Float.parseFloat(prop.getProperty("k"));
		d = Float.parseFloat(prop.getProperty("d"));
		korrektur = Float.parseFloat(prop.getProperty("korrektur"));
		logger.info("server.properties eingelesen!");
	}
}