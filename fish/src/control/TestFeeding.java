package control;

import java.io.IOException;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.DBManager;
import model.Daten;

public class TestFeeding {
	
	public static String status="stop";
	
	public static void main(String args[])
	{
		//if(args[0]!=null) status=args[0];
		Date d;
		Format formatter=new SimpleDateFormat("HH:mm");
		String uhrzeit;
		String[] str;
		String line="";
		Daten daten;
		DBManager dbman;
		while(status.equals("start"))
		{
			d=new Date();
			uhrzeit=formatter.format(d);
			str=new String[99];
			try {
				str=Tool.read("C:/fishconfig.txt");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	    	for (int i=0; i<str.length; i++) {
	            if(str[i]!=null)
	            {	
	            	line=str[i];
	            	if(uhrzeit.equals(line))
		            {
		            	daten=new Daten("20","25","50");
		            	try {
		        			dbman = new DBManager();
		        			dbman.speichern(daten);
		        			dbman.close();
		        		} catch (ClassNotFoundException e) {
		        			// TODO Auto-generated catch block
		        			e.printStackTrace();
		        		} catch (SQLException e) {
		        			// TODO Auto-generated catch block
		        			e.printStackTrace();
		        		}
		            }
	            }
	        }
	            
	        System.out.println(uhrzeit);
	        try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
