<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="css/style.css" />
		<title>Tabelle</title>
	</head>
	<body>
		<div id="wrapper">
			<div id="banner">
				<div id="buttons">
						<center>
							<a href="index.jsp"><img src="img/button-home-grau.png" /></a>
							<a href="graph.jsp"><img src="img/button-graph-grau.png" /></a>
							<a href="tabelle.jsp"><img src="img/button-tabelle.png" /></a>
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
					<form action="DBServlet">
					<input type="submit" value="Tabelle erstellen" />
					</form>
					<br />
					<%
						out.println(session.getAttribute("tabelle"));
					%>
					<br />
		  	</div><!-- Ende content -->
		  </center>
		</div><!-- Ende wrapper -->
	</body>
</html>