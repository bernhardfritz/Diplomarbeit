<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Eingabe</title>
</head>
<body>
<form action="DatenServlet">
Wassertemperatur:<input type="text" name="wtemp"/><br/>
Lufttemperatur:<input type="text" name="ltemp"/><br/>
<input type="submit" name="submit"/>
</form>
</body>
</html>