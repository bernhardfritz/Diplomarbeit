package model;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.sun.jmx.snmp.Timestamp;

import control.*;

public class DBManager {
public Connection con;
	
	public DBManager()
	{
		new Data();
		try {
			Class.forName(Data.driver);
		} catch (ClassNotFoundException e) {
			Data.logger.error(e.getMessage());
		}
		try {
			con = (Connection) DriverManager.getConnection(Data.dburl,Data.user,Data.pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Data.logger.error(e.getMessage());
		}
	}
	
	public void close()
	{
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Data.logger.error(e.getMessage());
		}
	}
	
	public boolean login(String username,String password)
	{
		String sql="SELECT * FROM users WHERE username=? AND password=?";
		boolean login=false;
		try {
			PreparedStatement ps=(PreparedStatement) con.prepareStatement(sql);
			ps.setString(1,username);
			ps.setString(2,password);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) login=true;
			rs.close();
			ps.close();
		} catch (SQLException e) {
			Data.logger.error(e.getMessage());
		}
		return login;
	}
	
	public void speichern(Daten d)
	{
		String sql="INSERT INTO "+Data.table+"(wtemp,ltemp) VALUES(?,?)";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setDouble(1,d.getWtemp());
			ps.setDouble(2,d.getLtemp());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			Data.logger.error(e.getMessage());
		}
	}
	
	public List<Daten> suche(String s)
	{
		List<Daten> erg=new ArrayList<Daten>();
		String sql="SELECT * FROM "+Data.table+" WHERE wtemp LIKE '%?%' OR ltemp LIKE '%?%' OR zeitpunkt LIKE '%?%'";
		try {
			PreparedStatement ps=(PreparedStatement) con.createStatement();
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				double wtemp=rs.getDouble(1);
				double ltemp=rs.getDouble(2);
				Date zeitpunkt=rs.getDate(3);
				Daten d=new Daten(wtemp,ltemp,zeitpunkt);
				erg.add(d);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			Data.logger.error(e.getMessage());
		}
		return erg;
	}
	
	public List<Daten> SQL(String sql)
	{
		List<Daten> erg=new ArrayList<Daten>();
		try {
			Statement stmt=(Statement) con.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				int id=rs.getInt("id");
				double wtemp=rs.getDouble("wtemp");
				double ltemp=rs.getDouble("ltemp");
				Date zeitpunkt=rs.getDate("zeitpunkt");
				Daten d=new Daten(id,wtemp,ltemp,zeitpunkt);
				erg.add(d);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			Data.logger.error(e.getMessage());
		}
		return erg;
	}
	
	public int simpleSQL(String sql)
	{
		int id=0;
		try {
			Statement stmt=(Statement) con.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				id=rs.getInt("id");
				System.out.println(id);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			Data.logger.error(e.getMessage());
		}
		return id;
	}
	
	public List<Daten> getAll()
	{
		List<Daten> erg=new ArrayList<Daten>();
		String sql="SELECT * FROM "+Data.table;
		try {
			Statement ps=(Statement) con.createStatement();
			ResultSet rs=ps.executeQuery(sql);
			while(rs.next())
			{
				int id=rs.getInt(1);
				double wtemp=rs.getDouble(2);
				double ltemp=rs.getDouble(3);
				java.sql.Timestamp sqlzeitpunkt=rs.getTimestamp(4);
				java.util.Date zeitpunkt=new java.util.Date(sqlzeitpunkt.getTime());
				Daten d=new Daten(id,wtemp,ltemp,zeitpunkt);
				erg.add(d);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			Data.logger.error(e.getMessage());
		}
		return erg;
	}
}
