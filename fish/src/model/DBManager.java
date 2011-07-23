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
		String sql="INSERT INTO daten(wtemp,ltemp,breite,laenge,wasserstand,volumen,naehrstoffgehalt,lichtintensitaet) VALUES(" +
				"'"+d.getWtemp()+"',"+
				"'"+d.getLtemp()+"',"+
				"'"+d.getBreite()+"',"+
				"'"+d.getLaenge()+"',"+
				"'"+d.getWasserstand()+"',"+
				"'"+d.getVolumen()+"',"+
				"'"+d.getNaehrstoffgehalt()+"',"+
				"'"+d.getLichtintensitaet()+"')";
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
				"breite LIKE '%"+s+"%' OR "+
				"laenge LIKE '%"+s+"%' OR "+
				"wasserstand LIKE '%"+s+"%' OR "+
				"volumen LIKE '%"+s+"%' OR "+
				"naehrstoffgehalt LIKE '%"+s+"%' OR "+
				"lichtintensitaet LIKE '%"+s+"%'";
		ResultSet rs=stmt.executeQuery(sql);
		while(rs.next())
		{
			String wtemp=rs.getString("wtemp");
			String ltemp=rs.getString("ltemp");
			String breite=rs.getString("breite");
			String laenge=rs.getString("laenge");
			String wasserstand=rs.getString("wasserstand");
			String volumen=rs.getString("volumen");
			String naehrstoffgehalt=rs.getString("naehrstoffgehalt");
			String lichtintensitaet=rs.getString("lichtintensitaet");
			Daten d=new Daten(wtemp,ltemp,breite,laenge,wasserstand,volumen,naehrstoffgehalt,lichtintensitaet);
			erg.add(d);
		}
		rs.close();
		stmt.close();
		return erg;
	}
}
