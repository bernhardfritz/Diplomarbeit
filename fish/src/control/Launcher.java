package control;

import java.util.Scanner;

import model.DBManager;
import model.SocketManager;

public class Launcher{

	
	public static void main(String[] args) {
		MainThread mainThread=new MainThread();
		mainThread.start();
		Scanner sc=new Scanner(System.in);
		String line="";
		while(!(line=sc.nextLine()).equals("exit")) {
			if(line.equals("reset")) {
				DBManager dbman=new DBManager(null);
				dbman.recreateDB();
				dbman.close();
			}
			if(line.equals("feed")) {
				SocketManager sman=new SocketManager();
				Tool.feed(sman);
				sman.close();
			}
		}
		mainThread.isRunning=false;
		System.exit(0);
	}
}
