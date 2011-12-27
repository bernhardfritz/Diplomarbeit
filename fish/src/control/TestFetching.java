package control;

import java.io.IOException;

import model.SocketManager;

public class TestFetching {
	public static void main(String args[])
	{
		Tool.fetch(new SocketManager(), 1);	
	}
}
