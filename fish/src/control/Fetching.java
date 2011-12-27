package control;

import model.SocketManager;

public class Fetching
{	
	public static void main(String[] args)
	{
		boolean run=true;
		boolean isReachable=false;
		boolean fetching=true;
		int current=Tool.IgetTime("m");
		int previous=Tool.IgetTime("m");
		SocketManager sman=new SocketManager();
		while(run)
		{
			while(!isReachable)
			{
				isReachable=Tool.ping();
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
					Tool.fetch(sman,1);
					System.out.println("Daten wurden um "+Tool.SgetTime("HH:mm")+" Uhr erfolgreich in die Datenbank eingetragen!");
					previous=current;
				}
				Tool.wait(1000);
			}
		}
	}
}
