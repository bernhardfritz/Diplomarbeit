<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.io.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
File dir1 = new File (".");
File dir2 = new File ("..");
try {
  out.println ("Current dir : " + dir1.getCanonicalPath());
  out.println ("Parent  dir : " + dir2.getCanonicalPath());
  }
catch(Exception e) {
  e.printStackTrace();
  }
%>
</body>
</html>