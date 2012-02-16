package control;

import java.sql.SQLException;

import model.DBManager;

public class DBTest {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		DBManager dbman = new DBManager();
		dbman.createDB();
	}

}
