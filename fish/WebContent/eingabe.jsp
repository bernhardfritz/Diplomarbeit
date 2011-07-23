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
Aquariumbreite:<input type="text" name="breite"/><br/>
Aquariumlänge:<input type="text" name="laenge"/><br/>
Wasserstand:<input type="text" name="wasserstand"/><br/>
Volumen:<input type="text" name="volumen"/><br/>
Nährstoffgehalt:<input type="text" name="naehrstoffgehalt"/><br/>
Lichtintensität:<input type="text" name="lichtintensitaet"/><br/>
<input type="submit" name="submit"/>
</form>
</body>
</html>