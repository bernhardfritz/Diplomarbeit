package control;

import model.SocketManager;

public class GraphThread extends Thread {
	SocketManager sman;
	
	GraphThread(SocketManager sman) {
		this.sman=sman;
	}
	
	@SuppressWarnings("deprecation")
	public void run() {
		Tool.createGraph(sman);
		stop();
	}
}
