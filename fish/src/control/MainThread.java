package control;

import model.*;

public class MainThread extends Thread{
	
	boolean isRunning=true;
	
	@SuppressWarnings("deprecation")
	public void run()
	{
		new Data();
		DBManager dbman=new DBManager(null);
		dbman.createDB();
		dbman.close();
		PingThread pingThread = new PingThread(Data.netioip);
		pingThread.run();
		
		SocketManager sman=new SocketManager();
		
		int current=Tool.IgetTime("m");
		int previous=Tool.IgetTime("m");
		
		while(isRunning) {
			current=Tool.IgetTime("m");
			if(current!=previous)
			{
				Data.logger.info("Synchronisierung erfolgreich!");
				pingThread.run();
				Data.logger.info("Datenermittlung wird durchgeführt...");
				Tool.fetch(sman);
				Data.logger.info("Daten wurden erfolgreich in die Datenbank eingetragen!");
				if(Tool.isFeedingTime())
				{
					Data.logger.info("Fütterung wird durchgeführt...");
					Tool.feed(sman,Tool.getFuttereinheiten());
					Data.logger.info("Fische wurden erfolgreich gefüttert!");
				}
				previous=current;
			}
			Tool.wait(1000);
		}
		stop();
	}
}
