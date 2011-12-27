<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="css/style.css" />
		<title>Graph</title>
	</head>
	<body>
		<div id="wrapper">
			<div id="banner">
				<div id="buttons">
						<center>
							<a href="index.jsp"><img src="img/button-home-grau.png" /></a>
							<a href="graph.jsp"><img src="img/button-graph.png" /></a>
							<a href="tabelle.jsp"><img src="img/button-tabelle-grau.png" /></a>
							<a href="logs.jsp"><img src="img/button-logs-grau.png" /></a>
							<a href="konfiguration.jsp"><img src="img/button-konfiguration-grau.png" /></a>
					  </center>
				</div><!-- Ende buttons -->	
			</div><!-- Ende banner -->
			<center>
				<div id="fenster">
				</div>
				<div id="content">
					<p>
						<img src="http://localhost:8080/fish/fishfiles/graph.png" />
						<form action="AquariumServlet?period=this.form.period.options[this.form.period.selectedIndex].value">
						<select name="period">
						<option value="Stunde">Stunde</option>
						<option value="Tag">Tag</option>
						<option value="Woche">Woche</option>
						<option value="Monat">Monat</option>
						</select>
						<input type="submit" value="OK" />
						</form>
					</p>
		  	</div><!-- Ende content -->
		  </center>
		</div><!-- Ende wrapper -->
	</body>
</html>