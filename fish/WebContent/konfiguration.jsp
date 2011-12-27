<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Konfiguration</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript">
function myFunction(str)
{
var xmlhttp;
if (str.length==0)
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
    document.getElementById("mydiv").innerHTML=xmlhttp.responseText;
    }
  }
xmlhttp.open("GET","ConfigServlet?str="+str,true);
xmlhttp.send();
}
</script>
</head>
<body>
		<div id="wrapper">
			<div id="banner">
				<div id="buttons">
						<center>
							<a href="index.jsp"><img src="img/button-home-grau.png" /></a>
							<a href="graph.jsp"><img src="img/button-graph-grau.png" /></a>
							<a href="tabelle.jsp"><img src="img/button-tabelle-grau.png" /></a>
							<a href="logs.jsp"><img src="img/button-logs-grau.png" /></a>
							<a href="konfiguration.jsp"><img src="img/button-konfiguration.png" /></a>
					  </center>
				</div><!-- Ende buttons -->	
			</div><!-- Ende banner -->
			<center>
				<div id="fenster">
				</div>
				<div id="content">
					<form action="ConfigServlet2?anzahl=anzahl.value">Anzahl der Fütterungen pro Tag: <input type="text" name="anzahl" onkeyup="myFunction(this.value)"/>
					<div id="mydiv"></div>
					</form>		
		  	</div><!-- Ende content -->
		  </center>
		</div><!-- Ende wrapper -->
</body>
</html>