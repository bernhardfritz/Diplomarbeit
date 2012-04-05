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
		PingThread pingThread = new PingThread();
		pingThread.run();
		
		Data.logger.info("Verbindungsaufbau...");
		SocketManager sman=new SocketManager();
		Data.logger.info("Verbindung hergestellt!");
		
		int current=Tool.IgetTime("m");
		int previous=Tool.IgetTime("m");
		
		while(isRunning) {
			current=Tool.IgetTime("m");
			if(current!=previous)
			{
				Data.logger.info("Synchronisierung erfolgreich!");
				Data.logger.info("Datenermittlung wird durchgef�hrt...");
				Tool.fetch(sman);
				Data.logger.info("Daten wurden erfolgreich in die Datenbank eingetragen!");
				if(Tool.isFeedingTime())
				{
					Data.logger.info("F�tterung wird durchgef�hrt...");
					Tool.feed(sman);
					Data.logger.info("Fische wurden erfolgreich gef�ttert!");
				}
				previous=current;
			}
			Tool.wait(1000);
		}
		stop();
	}
}
