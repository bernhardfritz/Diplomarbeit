package control;

import java.util.Scanner;

public class Launcher{

	
	public static void main(String[] args) {
		MainThread mainThread=new MainThread();
		mainThread.start();
		Scanner sc=new Scanner(System.in);
		if(sc.nextLine().contains("")) {
			mainThread.isRunning=false;
			System.exit(0);
		}
	}
}
