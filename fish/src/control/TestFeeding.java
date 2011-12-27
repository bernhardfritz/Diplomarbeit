package control;

import java.io.IOException;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.DBManager;
import model.Daten;
import model.SocketManager;

public class TestFeeding {
	
	public static void main(String args[])
	{
		Tool.feed(new SocketManager(), 1);
	}
}
