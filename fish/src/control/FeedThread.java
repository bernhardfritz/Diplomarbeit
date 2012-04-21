package control;

import model.Data;
import model.SocketManager;

public class FeedThread extends Thread{
	
	static boolean token;
	static boolean requestToken;
	SocketManager sman;
	
	public FeedThread(SocketManager sman) {
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
			Data.logger.info("F�tterung wird durchgef�hrt...");
			Tool.feed(sman,1);
			Data.logger.info("Fische wurden erfolgreich gef�ttert!");
			token=false;
		}
		stop();
	}
}
