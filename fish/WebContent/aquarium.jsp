<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
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
</body>
</html>