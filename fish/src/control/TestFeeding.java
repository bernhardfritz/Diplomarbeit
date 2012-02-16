package control;

import model.SocketManager;

public class TestFeeding {
	
	public static void main(String args[])
	{
		Tool.feed(new SocketManager(), 1);
	}
}
