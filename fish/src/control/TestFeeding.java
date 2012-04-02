package control;

import model.Data;
import model.SocketManager;

public class TestFeeding {
	
	public static void main(String args[])
	{
		new Data();
		Tool.feed(new SocketManager());
	}
}
