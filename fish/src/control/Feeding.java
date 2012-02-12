package control;

import java.io.IOException;

import model.SocketManager;

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
		SocketManager sman=new SocketManager();
		
		while(status.equals("start"))
		{
			while(!isReachable)
			{
				isReachable=Tool.ping();
				Tool.wait(5000);
			}
			System.out.println("AVR-Net-IO verbunden!");
			while(feeding)
			{
				
				current=Tool.IgetTime("m");
				if(current!=previous)
				{
					str=Tool.read(Data.fishconfig);
					for(int i=0; i<str.length; i++)
					{
						if(str[i]!=null) line=str[i];
						if(line.equals(Tool.SgetTime("HH:mm"))) ready=true;
					}
					if(ready)
					{
						Tool.feed(sman,1);
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
