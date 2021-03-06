package model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.sql.*;

import javax.naming.*;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

import control.*;

public class DBManager {
public Connection con;
	
	public DBManager(Object o)
	{
		new Data();
		if(!(o instanceof HttpServlet)) {
			try {
				Class.forName(Data.driver);
			} catch (ClassNotFoundException e) {
				Data.logger.error(e.getMessage());
			}
			try {
				con = (Connection) DriverManager.getConnection(Data.dburl,Data.user,Data.pass);
			} catch (SQLException e) {
				Data.logger.error(e.getMessage());
			}
		}
		else {
			try {
				InitialContext ic = new InitialContext();
				DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/fish");
				con = ds.getConnection();
			} catch(NamingException e) {
				Data.logger.error(e.getMessage());
			} catch(SQLException e) {
				Data.logger.error(e.getMessage());
			}
		}
	}
	
	public void createDB() {
		Statement stat;
		try {
			stat = con.createStatement();
			stat.executeUpdate("create table if not exists sensordaten ( id integer primary key autoincrement, wassertemperatur float default 0, lufttemperatur float default 0, zeitpunkt timestamp default current_timestamp);");
			stat.executeUpdate("create table if not exists users ( id integer primary key autoincrement, username varchar(8) not null, password varchar(32) not null);");
			stat.executeUpdate("create table if not exists konfiguration ( id integer primary key autoincrement, string varchar(999) not null);");
			stat.close();
			PreparedStatement prep = con.prepareStatement("insert into users (username, password) values (?, ?);");
			prep.setString(1, "foo");
			prep.setString(2, Tool.md5("bar"));
			prep.execute();
			prep.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Data.logger.error(e.getMessage());
		}
	}
	
	public void recreateDB() {
		Statement stat;
		try {
			stat = con.createStatement();
			stat.executeUpdate("drop table if exists sensordaten;");
			stat.executeUpdate("drop table if exists users;");
			stat.executeUpdate("drop table if exists konfiguration;");
			stat.executeUpdate("create table if not exists sensordaten ( id integer primary key autoincrement, wassertemperatur float default 0, lufttemperatur float default 0, zeitpunkt timestamp default current_timestamp);");
			stat.executeUpdate("create table if not exists users ( id integer primary key autoincrement, username varchar(8) not null, password varchar(32) not null);");
			stat.executeUpdate("create table if not exists konfiguration ( id integer primary key autoincrement, string varchar(999) not null);");
			stat.close();
			PreparedStatement prep = con.prepareStatement("insert into users (username, password) values (?, ?);");
			prep.setString(1, "foo");
			prep.setString(2, Tool.md5("bar"));
			prep.execute();
			prep.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Data.logger.error(e.getMessage());
		}
	}
	
	public void setup(String username, String password) {
		try {
			Statement stat=con.createStatement();
			stat.executeUpdate("drop table if exists users;");
			stat.executeUpdate("create table if not exists users ( id integer primary key autoincrement, username varchar(8) not null, password varchar(32) not null);");
			stat.close();
			PreparedStatement prep = con.prepareStatement("insert into users (username, password) values (?, ?);");
			prep.setString(1, username);
			prep.setString(2, Tool.md5(password));
			prep.execute();
			prep.close();
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
		boolean login=false;
		String sql="SELECT * FROM users WHERE username=? AND password=?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,username);
			ps.setString(2,Tool.md5(password));
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
		String sql="INSERT INTO "+Data.table+"(wassertemperatur,lufttemperatur) VALUES(?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setFloat(1,d.getWtemp());
			ps.setFloat(2,d.getLtemp());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			//Data.logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public List<Daten> suche(String s)
	{
		List<Daten> erg=new ArrayList<Daten>();
		String sql="SELECT * FROM "+Data.table+" WHERE wassertemperatur LIKE '%?%' OR lufttemperatur LIKE '%?%' OR zeitpunkt LIKE '%?%'";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery(sql);
			while(rs.next())
			{
				float wtemp=rs.getFloat(1);
				float ltemp=rs.getFloat(2);
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
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				int id=rs.getInt("id");
				float wtemp=rs.getFloat("wassertemperatur");
				float ltemp=rs.getFloat("lufttemperatur");
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
			Statement stmt=con.createStatement();
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
			Statement ps=con.createStatement();
			ResultSet rs=ps.executeQuery(sql);
			while(rs.next())
			{
				int id=rs.getInt(1);
				float wtemp=rs.getFloat(2);
				float ltemp=rs.getFloat(3);
				String s=rs.getString(4);
				Date zeitpunkt=new Date();
				try {
					zeitpunkt = Tool.StringToDate(s);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
	
	public List<Daten> getLastEntries(int count)
	{
		List<Daten> erg=new ArrayList<Daten>();
		String sql="SELECT * FROM "+Data.table+" WHERE id > ((SELECT MAX(id) FROM "+Data.table+")-"+count+")";
		try {
			Statement ps=con.createStatement();
			ResultSet rs=ps.executeQuery(sql);
			while(rs.next())
			{
				int id=rs.getInt(1);
				float wtemp=rs.getFloat(2);
				float ltemp=rs.getFloat(3);
				String s=rs.getString(4);
				Date zeitpunkt=new Date();
				try {
					zeitpunkt = Tool.StringToDate(s);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
	
	public String[] getConfig() {
		
		String sql="SELECT string FROM konfiguration WHERE id=?";
		String string="";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1,1);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				string=rs.getString(1);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			Data.logger.error(e.getMessage());
		}
		int anzahl=string.length()/6;
		String ret[]=new String[anzahl];
		for(int i=0; i<anzahl; i++)
		{
			ret[i]=string.substring(i*6,(i+1)*6);
		}
		return ret;
	}
	
	public void setConfig(String s[]) {
		String result="";
		String sql="INSERT INTO konfiguration(string) VALUES(?)";
		for(String temp:s) {
			result+=temp;
		}
		try {
			Statement stat = con.createStatement();
			stat.executeUpdate("drop table if exists konfiguration;");
			stat.executeUpdate("create table konfiguration ( id integer primary key autoincrement, string varchar(999) not null);");
			stat.close();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,result);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			Data.logger.error(e.getMessage());
		}
	}
	
	public boolean konfigurationExists() {
		String sql="SELECT count() FROM konfiguration";
		int count=0;
		boolean exists=false;
		try {
			Statement stat=con.createStatement();
			ResultSet rs=stat.executeQuery(sql);
			while(rs.next()) {
				count=rs.getInt(1);
			}
			rs.close();
			stat.close();
		} catch (SQLException e) {
			Data.logger.error(e.getMessage());
		}
		if(count>0) exists=true;
		return exists;
	}
}
