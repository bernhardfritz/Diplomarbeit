package control;

public class GraphThread extends Thread {
	GraphThread() {
		run();
	}
	
	public void run() {
		Tool.createGraph();
	}
}
