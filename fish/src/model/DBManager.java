package model;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class DBManager {
public Connection con;
	
	public DBManager() throws ClassNotFoundException, SQLException
	{
		String myDb = "//localhost/java";
		Class.forName("com.mysql.jdbc.Driver");
		con = (Connection) DriverManager.getConnection("jdbc:mysql:" + myDb,"root","");
	}
	
	public void close() throws SQLException
	{
		con.close();
	}
	
	public void speichern(Daten d) throws SQLException
	{
		String sql="INSERT INTO daten(wtemp,ltemp,wasserstand,uhrzeit,datum) VALUES(" +
				"'"+d.getWtemp()+"',"+
				"'"+d.getLtemp()+"',"+
				"'"+d.getWasserstand()+"',"+
				"'"+d.getUhrzeit()+"',"+
				"'"+d.getDatum()+"')";
		Statement stmt=(Statement) con.createStatement();
		stmt.executeUpdate(sql);
		stmt.close();
	}
	
	public List<Daten> suche(String s) throws SQLException
	{
		List<Daten> erg=new ArrayList<Daten>();
		Statement stmt=(Statement) con.createStatement();
		String sql="SELECT * FROM daten WHERE wtemp LIKE '%"+s+"%' OR " +
				"ltemp LIKE '%"+s+"%' OR "+
				"wasserstand LIKE '%"+s+"%' OR "+
				"uhrzeit LIKE '%"+s+"%' OR "+
				"datum LIKE '%"+s+"%'";
		ResultSet rs=stmt.executeQuery(sql);
		while(rs.next())
		{
			String wtemp=rs.getString("wtemp");
			String ltemp=rs.getString("ltemp");
			String wasserstand=rs.getString("wasserstand");
			String uhrzeit=rs.getString("uhrzeit");
			String datum=rs.getString("datum");
			Daten d=new Daten(wtemp,ltemp,wasserstand,uhrzeit,datum);
			erg.add(d);
		}
		rs.close();
		stmt.close();
		return erg;
	}
	
	public List<Daten> SQL(String sql) throws SQLException
	{
		List<Daten> erg=new ArrayList<Daten>();
		Statement stmt=(Statement) con.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
		while(rs.next())
		{
			int id=rs.getInt("id");
			String wtemp=rs.getString("wtemp");
			String ltemp=rs.getString("ltemp");
			String wasserstand=rs.getString("wasserstand");
			String uhrzeit=rs.getString("uhrzeit");
			String datum=rs.getString("datum");
			Daten d=new Daten(id,wtemp,ltemp,wasserstand,uhrzeit,datum);
			erg.add(d);
		}
		rs.close();
		stmt.close();
		return erg;
	}
	
	public int simpleSQL(String sql) throws SQLException
	{
		Statement stmt=(Statement) con.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
		int id=0;
		while(rs.next())
		{
			id=rs.getInt("id");
			System.out.println(id);
		}
		rs.close();
		stmt.close();
		return id;
	}
	
	public List<Daten> getAll() throws SQLException
	{
		List<Daten> erg=new ArrayList<Daten>();
		Statement stmt=(Statement) con.createStatement();
		String sql="SELECT * FROM daten";
		ResultSet rs=stmt.executeQuery(sql);
		while(rs.next())
		{
			String wtemp=rs.getString("wtemp");
			String ltemp=rs.getString("ltemp");
			String wasserstand=rs.getString("wasserstand");
			String uhrzeit=rs.getString("uhrzeit");
			String datum=rs.getString("datum");
			Daten d=new Daten(wtemp,ltemp,wasserstand,uhrzeit,datum);
			erg.add(d);
		}
		rs.close();
		stmt.close();
		return erg;
	}
}
