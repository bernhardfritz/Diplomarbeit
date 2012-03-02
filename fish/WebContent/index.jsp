<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.io.IOException,java.net.InetAddress,java.net.UnknownHostException"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
						if(session.getAttribute("login")!=null&&session.getAttribute("login").equals(true))
						{
							boolean status=false;
							try {
								status=InetAddress.getByName("192.168.0.90").isReachable(5000);
							} catch (UnknownHostException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}
							if(status) out.println("AVR-Net-IO-Board connected!");
							else out.println("AVR-Net-IO-Board not connected...");
						}
					%>
					<br />
					<form action="LogoutServlet">
						<input type="submit" value="Logout" />
					</form>
					<br />
		  	</div><!-- Ende content -->
		  </center>
		</div><!-- Ende wrapper -->
	</body>
</html>