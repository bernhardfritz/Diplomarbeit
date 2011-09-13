package control;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import model.DBManager;
import model.Daten;

public class Tool {

    public static void write(String path, String[] str) throws IOException
    {
        File file = new File(path);
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
 
        for (int i = 0; i < str.length; i++) {
            if(str[i]!=null)
            {	
            	bw.write(str[i]);
            	bw.newLine();
            }
        }
 
        bw.flush();
        bw.close();
    }
    
    public static String[] read(String path) throws IOException
    {
    	File file = new File(path);
    	BufferedReader br = new BufferedReader(new FileReader(file));
 
        String str[]=new String[99];
    	String line;
    	int i=0;
 
        while ((line = br.readLine()) != null) {
            str[i]=line;
            i++;
        }
 
        br.close();
        return str;
 
    }
    
    public static Double[] Fishdata2Array()
	{
		Double fishdata[]=new Double[51];
		String s[]=new String[51];
		try {
			s=read("C:/fishfiles/fishdata2.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0; i<=50; i++)
		{
			fishdata[i]=new Double(s[i]);
		}
		return fishdata;
	}
    
    public static int getTemperature(double voltage)
    {
		int temp=0;
		double diff;
    	Double fishdata[]=Fishdata2Array();
    	for(int i=0; i<=50; i++)
		{
			diff=voltage-fishdata[i];
			if(diff<=0)
			{
				temp=i;
				break;
			}
		}
    	if(voltage>fishdata[50]) temp=50;
    	return temp;
    }
    
    public static double getVoltage(int i)
    {
    	double d=0.0;
    	double e=i;
    	d=(5*e)/1024;
    	return d;	
    }
    
    public static String connect(String ip,int port,String cmd) throws IOException {
        java.net.Socket socket = new java.net.Socket(ip,port);
        String zuSendendeNachricht = cmd;
        schreibeNachricht(socket, zuSendendeNachricht);
        String empfangeneNachricht = leseNachricht(socket);
        String formatierteNachricht = formatiereNachricht(empfangeneNachricht);
        return formatierteNachricht;
     }
    
    public static void schreibeNachricht(java.net.Socket socket, String nachricht) throws IOException {
         PrintWriter printWriter =
            new PrintWriter(
                new OutputStreamWriter(
                    socket.getOutputStream()));
        printWriter.print(nachricht);
        printWriter.flush();
    }
     
    public static String leseNachricht(java.net.Socket socket) throws IOException {
        BufferedReader bufferedReader =
            new BufferedReader(
                new InputStreamReader(
                    socket.getInputStream()));
        char[] buffer = new char[200];
        int anzahlZeichen = bufferedReader.read(buffer, 0, 200); // blockiert bis Nachricht empfangen
        String nachricht = new String(buffer, 0, anzahlZeichen);
        return nachricht;
    }
    
    public static String formatiereNachricht(String unformatierteNachricht)
    {
    	String formatierteNachricht="";
    	formatierteNachricht=unformatierteNachricht.substring(0,3);
    	return formatierteNachricht;
    }
    
    public static void wait(int milliseconds)
    {
    	try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static boolean ping(String ip)
    {
    	boolean isReachable = false;
		try {
			isReachable = InetAddress.getByName("192.168.0.90").isReachable(5000);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return isReachable;
    }
    
    public static String SgetTime(String format)
    {
    	Date d=new Date();
		Format formatter=new SimpleDateFormat(format);
		String uhrzeit=formatter.format(d);
		return uhrzeit;
    }
    
    public static int IgetTime(String format)
    {
    	Date d=new Date();
		Format formatter=new SimpleDateFormat(format);
		String s=formatter.format(d);
		int uhrzeit=new Integer(s);
		return uhrzeit;
    }
    
    public static void fetch()
    {
    	String s="";
    	int i;
    	double v;
    	int t;
    	try {
			s=connect("192.168.0.90",50290,"GETADC 1");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	i=new Integer(s);
		v=Tool.getVoltage(i);
		t=Tool.getTemperature(v);
    	String wtemp=""+t;
    	String ltemp=""+t;
    	String wasserstand="50";
    	Daten d=new Daten(wtemp,ltemp,wasserstand);
    	DBManager dbman=null;
    	try {
			dbman=new DBManager();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			dbman.speichern(d);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static boolean synchronize(int minutes)
    {
    	if(IgetTime("m")==0||IgetTime("m")%minutes==0) return true;
    	else return false;
    }
    
    public static void createImage(String period)
    {
    	final XYSeries series1=new XYSeries("Temperaturkurve");
    	DBManager dbman=null;
    	try {
			dbman=new DBManager();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<Daten> erg=null;
    	if(period.equals("Stunde"))
    	{
    		try {
				erg=dbman.suche(SgetTime("HH:"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		if(erg!=null)
    		{
    			for(Daten d:erg)
    			{
    				series1.add(Double.parseDouble(d.getUhrzeit().substring(3,5)),Double.parseDouble(d.getLtemp()));
    			}
    		}
    	}
    	if(period.equals("Tag"))
    	{
    		try {
				erg=dbman.suche(SgetTime("dd.MM.yyyy"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		if(erg!=null)
    		{
    			for(Daten d:erg)
    			{
    				series1.add(Double.parseDouble(d.getUhrzeit().substring(0,2)),Double.parseDouble(d.getLtemp()));
    			}
    		}
    	}
    	if(period.equals("Woche"))
    	{
    		//series1.add(0,0);
    	}
    	if(period.equals("Monat"))
    	{
    		try {
				erg=dbman.suche(SgetTime("HH:"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		if(erg!=null)
    		{
    			for(Daten d:erg)
    			{
    				series1.add(Double.parseDouble(d.getUhrzeit().substring(3,5)),Double.parseDouble(d.getLtemp()));
    			}
    		}
    	}
    	final XYSeriesCollection dataset = new XYSeriesCollection();
    	dataset.addSeries(series1);
    	final JFreeChart chart = ChartFactory.createXYLineChart(
                "Temperaturkurve für eine(n) "+period,      // chart title
                "Zeit",                      // x axis label
                "Temperatur",                      // y axis label
                dataset,                  // data
                PlotOrientation.VERTICAL,
                true,                     // include legend
                true,                     // tooltips
                false                     // urls
            );
    	BufferedImage bi=chart.createBufferedImage(800,400);
    	File img=new File("C:/fishfiles/"+period+".png");
    	try {
			ImageIO.write(bi,"png",img);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    }
}