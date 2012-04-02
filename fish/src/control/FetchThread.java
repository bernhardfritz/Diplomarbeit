package control;

import model.Data;
import model.SocketManager;

public class FetchThread extends Thread{
	
	static boolean token;
	static boolean requestToken;
	SocketManager sman;
	
	public FetchThread(SocketManager sman) {
		token=false;
		requestToken=false;
		this.sman=sman;
	}
	
	@SuppressWarnings("deprecation")
	public void run() {
		requestToken=true;
		while(!token) {
			Tool.wait(1000);
		}
		if(token) {
			Data.logger.info("Datenermittlung wird durchgeführt...");
			Tool.fetch(sman);
			Data.logger.info("Daten wurden erfolgreich in die Datenbank eingetragen!");
			token=false;
		}
		stop();
	}
}
