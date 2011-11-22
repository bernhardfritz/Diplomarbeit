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
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Minute;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import model.*;

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
    
    public static String[] read(String path)
    {
    	String str[]=new String[99];
    	try
    	{
    	File file = new File(path);
    	BufferedReader br = new BufferedReader(new FileReader(file));
 
    	String line;
    	int i=0;
 
        while ((line = br.readLine()) != null)
        {
            str[i]=line;
            i++;
        }
 
        br.close();
    	}catch(IOException e)
    	{
    		e.printStackTrace();
    	}
        return str;
 
    }
    
    public static Double[] Fishdata2Array()
	{
		Double fishdata[]=new Double[51];
		String s[]=new String[51];
		s=read("C:/fishfiles/fishdata2.txt");
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
        socket.close();
        return formatierteNachricht;
     }
    public static void connect2(String ip,int port,String cmd) throws IOException {
        java.net.Socket socket = new java.net.Socket(ip,port);
        String zuSendendeNachricht = cmd;
        schreibeNachricht(socket, zuSendendeNachricht);
        socket.close();
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
    
    public static void fetch2(SocketManager sman,int adc)
    {
    	String s="";
    	int i;
    	double v;
    	int t;
    	s=sman.GETADC(adc);
    	i=Integer.parseInt(s);
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
    
    public static void feed(int port)
    {
    	try {
			Tool.connect2("192.168.0.90",50290,"SETPORT "+port+".0");
			Tool.wait(5000);
	    	Tool.connect2("192.168.0.90",50290,"SETPORT "+port+".1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void feed2(SocketManager sman,int adc)
    {
    	sman.SETPORT(adc,0);
    	wait(5000);
    	sman.SETPORT(adc,1);
    }
    
    public static String[] calcTime(String period)
    {
    	int hour=IgetTime("H");
		String minutes=SgetTime("mm");
		int day=IgetTime("d");
		int month=IgetTime("M");
		int year=IgetTime("yyyy");
		String Sday="";
		String Smonth="";
		String uhrzeit="";
		String datum="";
		String[] time=new String[2];
    	if(period.equals("Stunde"))
    	{
    		hour--;
    		if(hour<0)
    		{
    			hour+=24;
    			day--;
    			if(day<0)
    			{
    				day+=30;
    				month--;
    				if(month<0)
    				{
    					month+=12;
    					year--;
    				}
    			}
    		} 		
    	}
    	uhrzeit+=hour;
		if(uhrzeit.length()<2) uhrzeit="0"+uhrzeit;
		uhrzeit+=":"+minutes;
		Sday=""+day;
		if(Sday.length()<2) Sday="0"+Sday;
		Smonth=""+month;
		if(Smonth.length()<2) Smonth="0"+Smonth;
		datum=Sday+"."+Smonth+"."+year;
    	time[0]=uhrzeit;
    	time[1]=datum;
    	return time;
    }
    
    public static Calendar calcTime2(String period)
    {
		Calendar cal=Calendar.getInstance();
		if(period.equals("Stunde")) cal.add(Calendar.HOUR_OF_DAY, -1);
		if(period.equals("Tag")) cal.add(Calendar.DAY_OF_MONTH, -1);
		if(period.equals("Woche")) cal.add(Calendar.WEEK_OF_YEAR, -1);
		if(period.equals("Monat")) cal.add(Calendar.MONTH, -1);
		if(period.equals("Jahr")) cal.add(Calendar.YEAR, -1);
    	return cal;
    }
    
    public static void createImage(String period)
    {
    	String[] time=new String[2];
    	int first=0;
    	int last=0;
    	DBManager dbman=null;
    	List<Daten> erg=null;
    	time=calcTime(period);
    	final TimeSeries series1=new TimeSeries("Lufttemperatur");
    	RegularTimePeriod t;
    	try {
			dbman=new DBManager();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		System.out.println(time[0]+" "+time[1]);
    	try {
			//first=dbman.simpleSQL("SELECT id FROM daten WHERE uhrzeit LIKE '%"+time[0]+"%' AND datum LIKE '%"+time[1]+"%' LIMIT 1");
			//last=dbman.simpleSQL("SELECT id FROM daten ORDER BY id DESC LIMIT 1");
			//last=dbman.simpleSQL("SELECT id FROM daten WHERE uhrzeit LIKE '%"+SgetTime("HH:mm")+"%' AND datum LIKE '%"+SgetTime("dd.MM.yyyy")+"%' LIMIT 1");
			first=dbman.simpleSQL("SELECT id FROM daten WHERE uhrzeit LIKE '%20:00%' AND datum LIKE '%13.09.2011%' LIMIT 1");
			last=dbman.simpleSQL("SELECT id FROM daten WHERE uhrzeit LIKE '%21:00%' AND datum LIKE '%13.09.2011%' LIMIT 1");
    		erg=dbman.SQL("SELECT * FROM daten WHERE id>="+first+" AND id<="+last);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/*int m=new Integer(d.getUhrzeit().substring(3,4));
		System.out.println(m);
		int h=new Integer(d.getUhrzeit().substring(0,2));
		System.out.println(h);
		int D=new Integer(d.getDatum().substring(0,2));
		System.out.println(D);
		int M=new Integer(d.getDatum().substring(3,5));
		System.out.println(M);
		int Y=new Integer(d.getDatum().substring(6,10));
		System.out.println(Y);
		System.out.println(d.getLtemp());
		t=new Minute(m,h,D,M,Y);*/
		//t=new Minute(0,20,13,9,2011);
		t=new Minute();
    	for(Daten d:erg)
		{
    		if(d.getLtemp()!=null&&d.getUhrzeit()!=null)
    		{
    			
    			series1.add(t,Double.parseDouble(d.getLtemp()));
    			System.out.println(d.getLtemp());
    			System.out.println(t);
			}
    		t=t.next();
		}
		
    	/*if(period.equals("Stunde"))
    	{
    		try {
				erg=dbman.suche(SgetTime("HH:"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		if(erg!=null)
    		{
    			t=new Minute();
    			for(Daten d:erg)
    			{
    				series1.add(t,Double.parseDouble(d.getLtemp()));
    				t=t.next();
    			}
    		}
    	}*/
    	final TimeSeriesCollection dataset=new TimeSeriesCollection();
    	dataset.addSeries(series1);
    	final JFreeChart chart = ChartFactory.createTimeSeriesChart(
    				"Temperaturkurve für eine(n) "+period,      // chart title
    				"Zeit",                      // x axis label
    				"Temperatur (°C)",                      // y axis label
    				dataset,                  // data
    				false,                     // include legend
    				false,                     // tooltips
    				false                     // urls
    		);
    	BufferedImage bi=chart.createBufferedImage(800,400);
    		File img=new File("C:/fishfiles/graph.png");
    		try {
    			ImageIO.write(bi,"png",img);
    		} catch (IOException e) {
			// 	TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    }
    public static void createImage2(String period)
    {
    	Calendar fromcal=calcTime2(period);
    	Calendar tocal=Calendar.getInstance();
    	String fromtime=timeFormat(fromcal.getTime().getHours())+":"+timeFormat(fromcal.getTime().getMinutes());
    	String fromdate=timeFormat(fromcal.getTime().getDate())+"."+timeFormat((fromcal.getTime().getMonth()+1))+"."+(fromcal.getTime().getYear()+1900);
    	String totime=timeFormat(tocal.getTime().getHours())+":"+timeFormat(tocal.getTime().getMinutes());
    	String todate=timeFormat(tocal.getTime().getDate())+"."+timeFormat((tocal.getTime().getMonth()+1))+"."+(tocal.getTime().getYear()+1900);
    	int fromid;
    	int toid;
    	DBManager dbman=null;
    	List<Daten> erg=null;
    	final TimeSeries series1=new TimeSeries("Lufttemperatur");
    	RegularTimePeriod t;
    	try {
			dbman=new DBManager();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		System.out.println("Von "+fromtime+" "+fromdate);
		System.out.println("Bis "+totime+" "+todate);
    	try {
			fromid=dbman.simpleSQL("SELECT id FROM daten WHERE uhrzeit LIKE '%"+fromtime+"%' AND datum LIKE '%"+fromdate+"%' LIMIT 1");
			toid=dbman.simpleSQL("SELECT id FROM daten ORDER BY id DESC LIMIT 1");
			//toid=dbman.simpleSQL("SELECT id FROM daten WHERE uhrzeit LIKE '%"+totime+"%' AND datum LIKE '%"+todate+"%' LIMIT 1");
			//fromid=dbman.simpleSQL("SELECT id FROM daten WHERE uhrzeit LIKE '%20:00%' AND datum LIKE '%13.09.2011%' LIMIT 1");
			//toid=dbman.simpleSQL("SELECT id FROM daten WHERE uhrzeit LIKE '%21:00%' AND datum LIKE '%13.09.2011%' LIMIT 1");
    		erg=dbman.SQL("SELECT * FROM daten WHERE id>="+fromid+" AND id<="+toid);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//t=new Minute();
		t=new Minute(fromcal.getTime());
		for(Daten d:erg)
		{
			if(d.getLtemp()!=null&&d.getUhrzeit()!=null)
			{    			
				series1.add(t,Double.parseDouble(d.getLtemp()));
				System.out.println(d.getLtemp());
				System.out.println(t);
			}
			t=t.next();
		}
    	final TimeSeriesCollection dataset=new TimeSeriesCollection();
    	dataset.addSeries(series1);
    	final JFreeChart chart = ChartFactory.createTimeSeriesChart(
    				"Temperaturkurve für eine(n) "+period,      // chart title
    				"Zeit",                      // x axis label
    				"Temperatur (°C)",                      // y axis label
    				dataset,                  // data
    				false,                     // include legend
    				false,                     // tooltips
    				false                     // urls
    		);
    	BufferedImage bi=chart.createBufferedImage(800,400);
    		File img=new File("C:/fishfiles/graph.png");
    		try {
    			ImageIO.write(bi,"png",img);
    		} catch (IOException e) {
			// 	TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    }
    public static String timeFormat(int i)
    {
		String s=""+i;
		if(s.length()<2) s="0"+s;
		return s;
    }
}