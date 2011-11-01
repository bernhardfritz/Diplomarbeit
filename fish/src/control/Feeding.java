package control;

import java.io.IOException;

public class Feeding {
		
	public static String status="start";
	
	public static void main(String[] args) throws IOException
	{
		boolean isReachable=false;
		boolean ready=false;
		boolean feeding=true;
		String[] str;
		String line="";
		int current=Tool.IgetTime("m");
		int previous=Tool.IgetTime("m");
		Tool.connect2("192.168.0.90", 50290, "SETPORT 1.1");
		while(status.equals("start"))
		{
			while(!isReachable)
			{
				isReachable=Tool.ping("192.168.0.90");
				Tool.wait(5000);
			}
			System.out.println("AVR-Net-IO verbunden!");
			while(feeding)
			{
				
				current=Tool.IgetTime("m");
				if(current!=previous)
				{
					str=Tool.read("C:/fishfiles/fishconfig.txt");
					for(int i=0; i<str.length; i++)
					{
						if(str[i]!=null) line=str[i];
						if(line.equals(Tool.SgetTime("HH:mm"))) ready=true;
					}
					if(ready)
					{
						Tool.feed(1);
						previous=current;
						ready=false;
					}
				}
				Tool.wait(1000);
			}
		}
		System.out.println("Programm gestoppt!");
	}
}
