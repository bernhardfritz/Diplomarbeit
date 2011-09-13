<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.io.IOException,java.net.InetAddress,java.net.UnknownHostException"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
boolean status=false;
try {
	status=InetAddress.getByName("192.168.0.90").isReachable(5000);
} catch (UnknownHostException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
if(status) out.println("AVR-Net-IO-Board connected!");
else out.println("AVR-Net-IO-Board not connected...");
%>
<form action="StatusServlet">
<input type="submit" name="start" value="Starte Fütterungsroutine" />
<br />
<input type="submit" name="stop" value="Stoppe Fütterungsroutine" />
</form>
</body>
</html>