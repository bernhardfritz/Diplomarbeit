<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.io.IOException,java.net.InetAddress,java.net.UnknownHostException"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
	<body>
		<div id="wrapper">
			<div id="banner">
				<div id="buttons">
						<center>
							<a href="index.jsp"><img src="img/button-home-grau.png" /></a>
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
						if(session.getAttribute("login")!=null)
						{
							if(session.getAttribute("login").equals(false)) out.println("Login failed!");
						}
					%>
					<form action="LoginServlet" method="POST">
						<table border="0" >
						<tr>
						<td>
						Username:
						</td>
						<td>
						<input type="text" name="username" />
						</td>
						</tr>
						<tr>
						<td>
						Password:
						</td>
						<td>
						<input type="password" name="password" />
						</td>
						</tr>
						<tr>
						<td>
						</td>
						<td>
						<input type="submit" name="submit" />
						</td>
						</tr>
						</table>
					</form>
					<br />
		  	</div><!-- Ende content -->
		  </center>
		</div><!-- Ende wrapper -->
	</body>
</html>