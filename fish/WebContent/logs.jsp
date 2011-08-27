<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,model.*,java.sql.SQLException"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center>
<table border="1">
<th>Wassertemperatur</th>
<th>Lufttemperatur</th>
<th>Wasserstand</th>
<th>Uhrzeit</th>
<th>Datum</th>
<%
DBManager dbman;
List<Daten> erg=null;
try {
	dbman = new DBManager();
	erg=dbman.getAll();
	dbman.close();
} catch (ClassNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
if(erg!=null)
{
	for(Daten d:erg)
	{
		out.println("<tr><td>"+d.getWtemp()+
				"</td><td>"+d.getLtemp()+
				"</td><td>"+d.getWasserstand()+
				"</td><td>"+d.getUhrzeit()+
				"</td><td>"+d.getDatum()+
				"</td></tr>");	
	}
}
%>
</table>
</center>
</body>
</html>