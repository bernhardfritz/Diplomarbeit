<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
function myFunction(period)
{
var xmlhttp;
if (period.length==0)
  { 
  document.getElementById("mydiv").innerHTML="";
  return;
  }
if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
  xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
xmlhttp.onreadystatechange=function()
  {
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
	  alert(xmlhttp.responseText);
    document.getElementById("mydiv").innerHTML=xmlhttp.responseText;
    }
  }
xmlhttp.open("GET","AquariumServlet?period="+period,true);
xmlhttp.send();
}
</script>
</head>
<body>
<div id="mydiv"></div>
<form action="">
<select name="period" onchange="myFunction(this.form.period.options[this.form.period.selectedIndex].value)">
<option value="Stunde">Stunde</option>
<option value="Tag">Tag</option>
<option value="Woche">Woche</option>
<option value="Monat">Monat</option>
</select>
</form>
</body>
</html>