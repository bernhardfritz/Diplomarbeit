<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.io.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Logs</title>
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
							<a href="index.jsp"><img src="img/button-home-grau.png" /></a>
							<a href="graph.jsp"><img src="img/button-graph-grau.png" /></a>
							<a href="DBServlet"><img src="img/button-tabelle-grau.png" /></a>
							<a href="logs.jsp"><img src="img/button-logs.png" /></a>
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
					File file = new File("logs/server.log");
		    		BufferedReader br = new BufferedReader(new FileReader(file));
		    		String line;
		    		while ((line = br.readLine()) != null)
		            {
		                out.println(line);
		            }
		            br.close();
				%>
				<br />
				<br />
		  	</div><!-- Ende content -->
		  </center>
		</div><!-- Ende wrapper -->
	</body>
</html>