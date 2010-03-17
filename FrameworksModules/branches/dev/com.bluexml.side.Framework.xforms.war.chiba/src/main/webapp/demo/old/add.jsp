<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title></title>
</head>
<body>
<%
	String host = (String) session.getAttribute("xformshost");
	String user = (String) session.getAttribute("username");
	if (host == null) {
		out.println("Adresse de la webapp xforms inconnue.<br/> Indiquer les paramètres pour initialiser l'application.");
		return;
	} 
	if (user == null) {
		out.println("Aucun utilisateur. Veuillez vous identifier.");
		return;
	}
	String formType = request.getParameter("type");
	if (formType == null) {
		out.println("Appel incorrect. Type de contenu non reconnu.");
		return;
	}
	String url = response.encodeURL(host+"/xforms?type=" + formType + "&formType=form&userName="+user);
	out.println("<iframe id=\"bxNavigator\" class =\"mainDisplay\" src= \""+ url +"\" />");
%>
</body>
</html>
