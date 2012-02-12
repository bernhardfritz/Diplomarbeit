package control;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Minute;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.Second;
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
    	new Data();
    	String str[]=new String[Data.futtermax];
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
    	int counter=0;
    	for(int j=0; j<str.length; j++)
    	{
    		if(str[j]!=null)counter++;
    	}
    	String ret[]=new String[counter];
    	for(int k=0; k<counter; k++)
    	{
    		ret[k]=str[k];
    	}
        return ret;
 
    }
    
    public static String[] readFishConfig() {
    	new Data();
    	if(new File(Data.fishconfig).exists()) return read(Data.fishconfig);
    	else return read(Data.fishprefix+Data.fishconfig);
    }
    
    public static void writeFishConfig(String[] str) {
    	new Data();
    	try {
    		if(new File(Data.fishconfig).exists()) write(Data.fishconfig,str);
        	else write(Data.fishprefix+Data.fishconfig,str);
    		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static boolean fishConfigExists() {
    	new Data();
    	boolean exists=false;
    	if(new File(Data.fishconfig).exists()||new File(Data.fishprefix+Data.fishconfig).exists()) exists=true;
    	return exists;
    }
    
    public static double getTemperature(double voltage)
    {
    	double rsensor = (Data.r*voltage)/(Data.u-voltage);
    	double temp = (rsensor-Data.d)/Data.k;
    	temp+=Data.korrektur;
    	//return round(temp,2);
    	return temp;
    }
    
    public static double round(double d, int decimalPlace){
        BigDecimal bd = new BigDecimal(Double.toString(d));
        bd = bd.setScale(decimalPlace,BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
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
    
    public static boolean ping()
    {
    	boolean isReachable = false;
		try {
			isReachable = InetAddress.getByName(Data.netioip).isReachable(5000);
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
    
    public static void fetch(SocketManager sman,int adc)
    {
    	String s="";
    	int i;
    	double v;
    	double t;
    	s=sman.GETADC(adc);
    	i=Integer.parseInt(s);
		v=Tool.getVoltage(i);
		t=Tool.getTemperature(v);
    	double wtemp=t;
    	double ltemp=t;
    	Daten d=new Daten(wtemp,ltemp);
    	DBManager dbman=new DBManager();
		dbman.speichern(d);
    }
    
    public static void feed(SocketManager sman,int adc)
    {
    	sman.SETPORT(adc,1);
    	wait(5000);
    	sman.SETPORT(adc,0);
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
    

    public static String timeFormat(int i)
    {
		String s=""+i;
		if(s.length()<2) s="0"+s;
		return s;
    }
    
    public static String getGauge(double temperatur)
    {
    	int temp=(int)round(temperatur,0);
    	int width=temp*10;
    	String color;
    	if(temp<20) color = "RoyalBlue";
		else if(temp>30) color = "Red";
		else color = "LawnGreen";
    	String gauge;
		gauge =	"<img src=\"img/transparent.png\" height=15 width=" + width + " alt=\"" + temp + "\" style=\"background-color:" + color +"\" />";
    	return gauge;
    }
    
    public static void createGraph() {
    	BufferedImage bi;         		
   		TimeSeries tsw = new TimeSeries("Wassertemperatur in �C", Minute.class);
    	TimeSeries tsl = new TimeSeries("Lufttemperatur in �C", Minute.class);
    	tsw.setMaximumItemCount(60);
    	tsl.setMaximumItemCount(60);
    	Date d=new Date();
    	int current=d.getMinutes();
    	int previous=current;
    	System.out.println(d.toString());
    	while(true) {
    		while(current==previous) {
    			d=new Date();
    			current=d.getMinutes();
    		}
    		if(current!=previous)
    		{
    			System.out.println(d.toString());
    			new Data();
    			SocketManager sman=new SocketManager();
    			String s=sman.GETADC(1);
    	       	int i=Integer.parseInt(s.trim());
    	       	double v=Tool.getVoltage(i);
    	   		double t=Tool.getTemperature(v);
    			tsw.addOrUpdate(new Minute(), t);
    			tsl.addOrUpdate(new Minute(), t);
    			TimeSeriesCollection dataset = new TimeSeriesCollection();
    			dataset.addSeries(tsw);
    			dataset.addSeries(tsl);
    			JFreeChart chart = ChartFactory.createTimeSeriesChart(
    			"Wasser- und Lufttemperaturgraph",
    			"Sekunden",
    			"�C",
    			dataset,
    			true,
    			true,
    			false);
    			bi=chart.createBufferedImage(800,400);
    			previous=current;
    			try {
					ImageIO.write(bi,"png",new File(Data.fishgraph));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
    }
}