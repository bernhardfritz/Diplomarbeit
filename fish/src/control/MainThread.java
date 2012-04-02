package control;

import model.*;

public class MainThread extends Thread{
	
	boolean isRunning=true;
	
	@SuppressWarnings("deprecation")
	public void run()
	{
		new Data();
		PingThread pingThread = new PingThread();
		pingThread.run();
		
		Data.logger.info("Verbindungsaufbau...");
		SocketManager sman=new SocketManager();
		Data.logger.info("Verbindung hergestellt!");
		
		boolean ready=false;
		int current=Tool.IgetTime("m");
		int previous=Tool.IgetTime("m");
		
		while(isRunning) {
			current=Tool.IgetTime("m");
			if(current!=previous)
			{
				Data.logger.info("Synchronisierung erfolgreich!");
				Data.logger.info("Datenermittlung wird durchgeführt...");
				Tool.fetch(sman);
				Data.logger.info("Daten wurden erfolgreich in die Datenbank eingetragen!");
				for(String line:Tool.read(Data.fishconfig))
				{
					if(line.equals(Tool.SgetTime("HH:mm"))) ready=true;
				}
				if(ready)
				{
					Data.logger.info("Fütterung wird durchgeführt...");
					Tool.feed(sman);
					Data.logger.info("Fische wurden erfolgreich gefüttert!");
					ready=false;
				}
				previous=current;
			}
			Tool.wait(1000);
		}
		stop();
	}
}
