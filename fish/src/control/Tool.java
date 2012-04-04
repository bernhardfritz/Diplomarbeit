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
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.ThermometerPlot;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.data.time.Minute;
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
    
    public static float getTemperatureFromVoltage(float voltage)
    {
    	voltage/=Data.m;
    	float rsensor = (Data.r*voltage)/(5-voltage);
    	float temp = (rsensor-Data.d)/Data.k;
    	temp+=Data.korrektur;
    	return round(temp,2);
    }
    
    public static float getTemperature(SocketManager sman,int adc) {
    	return getTemperatureFromVoltage(getVoltageFromDigital(sman.GETADC(adc)));
    }
    
    public static float round(float d, int decimalPlace){
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace,BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
    
    public static float getVoltageFromDigital(int i)
    {
    	float d=0.0f;
    	float e=i;
    	d=(Data.u*e)/1024;
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
    
    public static void sendMail(String subject, String text){
    	Thread t=new JavaMailThread(subject,text);
    	t.start();
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
    
    public static void fetch(SocketManager sman)
    {
    	float wtemp=getTemperature(sman, Data.adcwasser);
    	float ltemp=getTemperature(sman, Data.adcluft);
    	if(wtemp<Data.tempmin) {
    		sendMail("Automatische Fischfütterungsanlage - Warnung!","Wassertemperatur zu niedrig!\n\nAktuelle Wassertemperatur: "+wtemp+" °C");
    		//sendTwitterMsg("Automatische Fischfütterungsanlage - Warnung!\n\nWassertemperatur zu niedrig!\n\nAktuelle Wassertemperatur: "+wtemp+" °C");
    	}
    	if(wtemp>Data.tempmax) {
    		sendMail("Automatische Fischfütterungsanlage - Warnung!","Wassertemperatur zu hoch!\n\nAktuelle Wassertemperatur: "+wtemp+" °C");
    		//sendTwitterMsg("Automatische Fischfütterungsanlage - Warnung!\n\nWassertemperatur zu hoch!\n\nAktuelle Wassertemperatur: "+wtemp+" °C");
    	}
    	if(ltemp<Data.tempmin) {
    		sendMail("Automatische Fischfütterungsanlage - Warnung!","Lufttemperatur zu niedrig!\n\nAktuelle Lufttemperatur: "+ltemp+" °C");
    		//sendTwitterMsg("Automatische Fischfütterungsanlage - Warnung!\n\nLufttemperatur zu niedrig!\n\nAktuelle Lufttemperatur: "+ltemp+" °C");
    	}
    	if(ltemp>Data.tempmax) {
    		sendMail("Automatische Fischfütterungsanlage - Warnung!","Lufttemperatur zu hoch!\n\nAktuelle Lufttemperatur: "+ltemp+" °C");
    		//sendTwitterMsg("Automatische Fischfütterungsanlage - Warnung!\n\nLufttemperatur zu hoch!\n\nAktuelle Lufttemperatur: "+ltemp+" °C");
    	}
    	Daten d=new Daten(wtemp,ltemp);
    	DBManager dbman=new DBManager(null);
		dbman.speichern(d);
		dbman.close();
    }
    
    public static void feed(SocketManager sman)
    {
    	new Data();
    	long endtime=new Date().getTime()+Data.feedingtime;
		int counter=0;
		sman.SETPORT(Data.portmotor,1);
		Tool.wait(500);
		while(new Date().getTime()<=endtime) {
			if(sman.GETADC(Data.adclicht)<512) {
				counter ++;
				sman.SETPORT(Data.portmotor, 0);
			}
			Tool.wait(10);
		}
		if(counter==0) sman.SETPORT(Data.portmotor,0);
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
    
    public static String getGauge(float temperatur)
    {
    	int temp=(int)round(temperatur,0);
    	int width=temp*10-100;
    	String color;
    	if(temp<20) color = "RoyalBlue";
		else if(temp>25) color = "Red";
		else color = "LawnGreen";
    	String gauge;
		gauge =	"<img src=\"img/transparent.png\" height=15 width=" + width + " alt=\"" + temp + "\" style=\"background-color:" + color +"\" />";
    	return gauge;
    }
    
    public static String md5(String input){
        String res = "";
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(input.getBytes());
            byte[] md5 = algorithm.digest();
            String tmp = "";
            for (int i = 0; i < md5.length; i++) {
                tmp = (Integer.toHexString(0xFF & md5[i]));
                if (tmp.length() == 1) {
                    res += "0" + tmp;
                } else {
                    res += tmp;
                }
            }
        } catch (NoSuchAlgorithmException ex) {}
        return res;
    }
    
    public static Date StringToDate(String s) throws ParseException {
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	return df.parse(s);
    }
    
    public static JFreeChart createThermometer(String s) {
    	DBManager dbman=new DBManager(null);
    	DefaultValueDataset dataset=null;
    	for(Daten d:dbman.getLastEntries(1)) {
    		if(s.equals("Wasser")) dataset = new DefaultValueDataset(d.getWtemp());
    		if(s.equals("Luft")) dataset = new DefaultValueDataset(d.getLtemp());
    	}
    	ThermometerPlot plot = new ThermometerPlot(dataset);
    	JFreeChart chart = new JFreeChart(s+"temperatur", JFreeChart.DEFAULT_TITLE_FONT, plot, false);
    	dbman.close();
    	return chart;
    }
    
    @SuppressWarnings("deprecation")
	public static JFreeChart createGraph() {         		
   		TimeSeries tsw = new TimeSeries("Wassertemperatur in °C", Minute.class);
    	TimeSeries tsl = new TimeSeries("Lufttemperatur in °C", Minute.class);
    	tsw.setMaximumItemCount(60);
    	tsl.setMaximumItemCount(60);
    	DBManager dbman=new DBManager(null);
    	for(Daten d:dbman.getLastEntries(60)) {
    		tsw.add(new Minute(d.getZeitpunkt()), d.getWtemp());
    		tsl.add(new Minute(d.getZeitpunkt()), d.getLtemp());
    	}
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(tsw);
		dataset.addSeries(tsl);
		JFreeChart chart = ChartFactory.createTimeSeriesChart(
		"Wasser- und Lufttemperaturgraph",
		"Zeit",
		"°C",
		dataset,
		true,
		true,
		false);
		dbman.close();
		return chart;
    }
    
    @SuppressWarnings("deprecation")
	public static void createGraph(SocketManager sman) {
    	BufferedImage bi;         		
   		TimeSeries tsw = new TimeSeries("Wassertemperatur in °C", Minute.class);
    	TimeSeries tsl = new TimeSeries("Lufttemperatur in °C", Minute.class);
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
    			new Data();
    	   		float t1=Tool.getTemperature(sman,Data.adcwasser);
    	   		float t2=Tool.getTemperature(sman,Data.adcluft);
    	   		Data.logger.info("Wassertemperatur: "+t1);
    	   		Data.logger.info("Lufttemperatur: "+t2);
    			tsw.addOrUpdate(new Minute(), t1);
    			tsl.addOrUpdate(new Minute(), t2);
    			TimeSeriesCollection dataset = new TimeSeriesCollection();
    			dataset.addSeries(tsw);
    			dataset.addSeries(tsl);
    			JFreeChart chart = ChartFactory.createTimeSeriesChart(
    			"Wasser- und Lufttemperaturgraph",
    			"Sekunden",
    			"°C",
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