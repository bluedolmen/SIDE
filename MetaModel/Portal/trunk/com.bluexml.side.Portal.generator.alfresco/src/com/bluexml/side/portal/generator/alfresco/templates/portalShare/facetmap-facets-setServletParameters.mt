[%
metamodel http://www.kerblue.org/portal/1.0
%]

[%script type="Page" name="facetmapfacetssetServletParameters"%]
[%if (eContainer() != null) {%]
[%ID.toLowerCase().nPut("setparam_name")%]
[%for (portlets){%]
[%for (associationPortlet){%]
[%if (isPortletInternal != null && isPortletInternal.view != null){%]
[%for (isPortletInternal.view){%]
[%if (current().startsWith("view.FacetMap")){%]
[%getProperty("facetmap.webapps")%]facetmap-facets-[%nGet("setparam_name").substring(nGet("setparam_name").lastIndexOf("-") +1)%]/setServletParameters.jsp
[%}%]
[%}%]
[%}%]
[%}%]
[%}%]
[%}%]
[%script type="Page" name="alfrescoGenerator" file="[%facetmapfacetssetServletParameters%]" post="trim()"%]
[%if (eContainer() != null) {%]
[%ID.toLowerCase().nPut("setparam_name")%]
[%for (portlets){%]
[%for (associationPortlet){%]
[%if (isPortletInternal != null && isPortletInternal.view != null){%]
[%for (isPortletInternal.view){%]
[%if (current().startsWith("view.FacetMap")){%]
<%@
page import="
	java.io.File,
	org.apache.tools.ant.Project,
	org.apache.tools.ant.ProjectHelper;
    import java.io.IOException,
    java.util.Collection,
    java.util.Enumeration,
    java.util.Iterator,
    java.util.Vector,
    com.facetmap.Map,
    com.facetmap.InternalException,
    com.facetmap.servlet.FacetMapServlet;
    import java.util.Properties;
	import java.io.FileInputStream;
	import java.io.FileOutputStream;"
	errorPage="error.jsp" contentType="text/html; charset=ISO-8859-1" 
%><%

//Debut modification

//Update code
Project ant = new Project();
//add a listener to see ant's log
org.apache.tools.ant.DefaultLogger log = new org.apache.tools.ant.DefaultLogger();  
log.setOutputPrintStream(System.out);
log.setMessageOutputLevel(Project.MSG_INFO);
ant.addBuildListener(log);
String path = System.getProperty("catalina.home")+"/webapps/facetmap-facets-[%nGet("setparam_name").substring(nGet("setparam_name").lastIndexOf("-") +1)%]/WEB-INF/";
//Getting host
FileInputStream fin = new FileInputStream(path + "build.properties");
Properties p = new Properties();
p.load(fin);
p.setProperty("ant.host",request.getHeader("host"));
FileOutputStream fout = new FileOutputStream(path + "build.properties");
p.store(fout,null);
//building ant script
File buildFile = new File(path + "buildsql.xml");
ant.init();
ProjectHelper.configureProject(ant, buildFile);
ant.executeTarget("main");

//Fin modification

FacetMapServlet fms = (FacetMapServlet)getServletContext().getAttribute("com.facetmap.servlet");
Collection exceptions = new Vector();
Enumeration names = request.getParameterNames();
while (names.hasMoreElements()) {
    try {
        String name = names.nextElement().toString();
        fms.setParameter(name, request.getParameter(name));
    } catch (Exception exc) {
        exceptions.add("Unexpected error: " + exc.toString());
    }
}
try {
    fms.configure();
} catch (Exception exc) {
    exceptions.add("Unexpected error: " + exc.toString());
    exceptions.add("Your changes were not applied.");
    try {
        fms.readProps();
        fms.configure();
    } catch (Exception exc2) {
        exceptions.add("Error reverting to previous configuration: " + exc.toString());
        exceptions.add("FacetMap may be unstable.");
    }
}
if (exceptions.size() == 0) {
    try {
        fms.writeProps("Modified by setServletParameters.jsp");
    } catch (IOException exc) {
        exceptions.add("Couldn't write changes to disk. File permissions may be incorrect.");
    }
}
if (exceptions.size() == 0) {
    response.sendRedirect("browseSimple.jsp");
}
// else print page of exceptions
%>
<html>
<head>
<title>Configuration Problems</title>
<link rel="stylesheet" type="text/css" href="facetmap_manual.css">
</head>

<body class="manual">
<p>
FacetMap encountered these warnings/errors:
<p>
<table>
<%
Iterator excIter = exceptions.iterator();
while (excIter.hasNext()) {
%>
    <tr><td style="font-size: 80%; border-top: 1px solid black; padding: 0.5em"><%= excIter.next().toString() %></td></tr>
<%
}
%>
</table>

<form action="browseSimple.jsp">
    <input type="submit" value="Continue">
</form>

</body>
</html>
[%}%]
[%}%]
[%}%]
[%}%]
[%}%]
[%}%]

