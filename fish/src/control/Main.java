package control;

import model.*;

public class Main {
	
	public static boolean feeding=true;
	
	public static void main(String[] args)
	{
		SocketManager sman=new SocketManager();
		boolean run=true;
		boolean isReachable=false;
		boolean fetching=true;
		boolean ready=false;
		String[] str;
		String line="";
		int current=Tool.IgetTime("m");
		int previous=Tool.IgetTime("m");
		sman.SETPORT(1,1);
		while(run)
		{
			while(!isReachable)
			{
				isReachable=Tool.ping("192.168.0.90");
				Tool.wait(5000);
			}
			System.out.println("AVR-Net-IO connected!");
			while(fetching)
			{
				current=Tool.IgetTime("m");
				if(current!=previous)
				{
					System.out.println("Synchronisierung erfolgreich!");
					System.out.println("Es ist "+Tool.SgetTime("HH:mm")+" Uhr. Datenermittlung wird gestartet!");
					//Tool.fetch();
					Tool.fetch2(sman,1);
					System.out.println("Daten wurden um "+Tool.SgetTime("HH:mm")+" Uhr erfolgreich in die Datenbank eingetragen!");
					if(feeding)
					{
						str=Tool.read("C:/fishfiles/fishconfig.txt");
						for(int i=0; i<str.length; i++)
						{
							if(str[i]!=null) line=str[i];
							if(line.equals(Tool.SgetTime("HH:mm"))) ready=true;
						}
						if(ready)
						{
							System.out.println("Es ist "+Tool.SgetTime("HH:mm")+" Uhr. Fütterung wird gestartet!");
							Tool.feed2(sman,1);
							System.out.println("Fische wurden um "+Tool.SgetTime("HH:mm")+" Uhr erfolgreich gefüttert!");
							ready=false;
						}
					}
					previous=current;
				}
				Tool.wait(1000);
			}
		}
	}
}
