package control;

import java.sql.SQLException;

import model.DBManager;

public class DBTest {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		DBTest d=new DBTest();
		DBManager dbman = new DBManager(d);
		dbman.createDB();
		dbman.close();
	}

}
