package control;

import model.*;

public class Main {
	
	public static boolean feeding=true;
	
	public static void main(String[] args)
	{
		new Data();
		SocketManager sman=new SocketManager();
		boolean run=true;
		boolean isReachable=false;
		boolean fetching=true;
		boolean ready=false;
		String[] str;
		String line="";
		int current=Tool.IgetTime("m");
		int previous=Tool.IgetTime("m");
		sman.SETPORT(1,0);
		while(run)
		{
			while(!isReachable)
			{
				isReachable=Tool.ping();
				Tool.wait(5000);
			}
			Data.logger.info("AVR-Net-IO connected!");
			while(fetching)
			{
				current=Tool.IgetTime("m");
				if(current!=previous)
				{
					Data.logger.info("Synchronisierung erfolgreich!");
					Data.logger.info("Datenermittlung wird gestartet!");
					Tool.fetch(sman,1);
					Data.logger.info("Daten wurden erfolgreich in die Datenbank eingetragen!");
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
							Data.logger.info("Fütterung wird gestartet!");
							Tool.feed(sman,1);
							Data.logger.info("Fische wurden erfolgreich gefüttert!");
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
