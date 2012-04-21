<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.io.IOException,java.net.InetAddress,java.net.UnknownHostException, control.Tool, model.Data"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="refresh" content="60">
<title>Home</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<%
	if(session.getAttribute("login")==null||session.getAttribute("login").equals(false)) response.sendRedirect("login.jsp");
%>
</head>
	<body>
		<div id="wrapper">
			<div id="banner">
				<div id="buttons">
						<center>
							<a href="index.jsp"><img src="img/button-home.png" /></a>
							<a href="graph.jsp"><img src="img/button-graph-grau.png" /></a>
							<a href="DBServlet"><img src="img/button-tabelle-grau.png" /></a>
							<a href="logs.jsp"><img src="img/button-logs-grau.png" /></a>
							<a href="konfiguration.jsp"><img src="img/button-konfiguration-grau.png" /></a>
					  </center>
				</div><!-- Ende buttons -->	
			</div><!-- Ende banner -->
			<center>
				<div id="fenster">
				</div>
				<div id="content">
					<br />
					<%
						new Data();
						if(session.getAttribute("login")!=null&&session.getAttribute("login").equals(true))
						{
							boolean status=Tool.ping(Data.netioip);
							if(status) {
								out.print("<img src=\"img\\greenlight.png\" width=32 height=32/>");
								out.print("ONLINE");
							}
							else {
								out.print("<img src=\"img\\redlight.png\" width=32 height=32/>");
								out.print("OFFLINE");
							}
						}
					%>
					<br />
					<br />
					<img src="WasserThermoServlet" />
					<img src="LuftThermoServlet" />
					<form action="LogoutServlet">
						<input type="submit" value="Logout" />
					</form>
					<br />
		  	</div><!-- Ende content -->
		  </center>
		</div><!-- Ende wrapper -->
	</body>
</html>