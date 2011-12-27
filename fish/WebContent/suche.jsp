<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Suche</title>
</head>
<body>
<form action="SuchServlet">
Suchbegriff:<input type="text" name="suchbegriff"/><br/>
<input type="submit" name="submit"/><br/></form>
<table border="1">
<th>Wassertemperatur</th>
<th>Lufttemperatur</th>
<th>Wasserstand</th>
<th>Uhrzeit</th>
<th>Datum</th>
<%
List<Daten> erg=(List<Daten>)session.getAttribute("erg");
if(erg!=null)
{
	for(Daten d:erg)
	{
		out.println("<tr><td>"+d.getWtemp()+
				"</td><td>"+d.getLtemp()+
				"</td><td>"+d.getZeitpunkt()+
				"</td></tr>");	
	}
}
%>
</table>
</body>
</html>