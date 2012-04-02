package control;

import model.Data;

public class PingThread extends Thread {
	
	boolean isConnected=false;
	
	@SuppressWarnings("deprecation")
	public void run() {
		int counter=0;
		Data.logger.info("Checking connectivity...");
		while(!isConnected) {
			counter++;
			Data.logger.info("Try "+counter+": ");
			isConnected=Tool.ping();
			if(isConnected) Data.logger.info("Ping successful!");
			else System.out.println("Ping failed!");
		}
		Data.logger.info("AVR-Net-IO is connected!");
		stop();
	}
}
