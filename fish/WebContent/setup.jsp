<%@page import="sun.font.Script"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.io.IOException,java.net.InetAddress,java.net.UnknownHostException"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Setup</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<%
	if(session.getAttribute("correct")!=null && session.getAttribute("key")!=null)
	{
		if(session.getAttribute("correct").equals(false)) out.println("<script language=\"JavaScript\">alert(\"Username or password too short or too long!\");</script>");
		if(session.getAttribute("correct").equals(true) && session.getAttribute("key").equals(false)) out.println("<script language=\"JavaScript\">alert(\"Wrong authorization code!\");</script>");
		session.setAttribute("correct",null);
		session.setAttribute("key",null);
	}
%>
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
					<form action="SetupServlet" method="POST">
						<table border="0" >
						<tr>
						<td>
						New username:
						</td>
						<td>
						<input type="text" name="username" />
						</td>
						</tr>
						<tr>
						<td>
						New password:
						</td>
						<td>
						<input type="password" name="password" />
						</td>
						</tr>
						<tr>
						<td>
						Authorization code:
						</td>
						<td>
						<input type="text" name="auth" />
						</td>
						</tr>
						<tr>
						<td>
						</td>
						<td>
						<center><input type="submit" name="submit" value="Register" /></center>
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