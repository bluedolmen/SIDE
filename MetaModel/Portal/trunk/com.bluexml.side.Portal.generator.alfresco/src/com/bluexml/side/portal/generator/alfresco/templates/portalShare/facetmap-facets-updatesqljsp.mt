[%
metamodel http://www.kerblue.org/portal/1.0
%]

[%script type="Page" name="facetmapfacetsupdatesql"%]
[%if (eContainer() != null) {%]
[%ID.toLowerCase().nPut("jsp_name")%]
[%for (portlets){%]
[%for (associationPortlet){%]
[%if (isPortletInternal != null && isPortletInternal.view != null){%]
[%for (isPortletInternal.view){%]
[%if (current().startsWith("view.FacetMap")){%]
[%getProperty("facetmap.webapps")%]facetmap-facets-[%nGet("jsp_name").substring(nGet("jsp_name").lastIndexOf("-") +1)%]/browseSimple-update-SQL.jsp
[%}%]
[%}%]
[%}%]
[%}%]
[%}%]
[%}%]
[%script type="Page" name="facetmapGenerator" file="[%facetmapfacetsupdatesql%]" post="trim()"%]
[%if (eContainer() != null) {%]
[%ID.toLowerCase().nPut("jsp_name")%]
[%for (portlets){%]
[%for (associationPortlet){%]
[%if (isPortletInternal != null && isPortletInternal.view != null){%]
[%for (isPortletInternal.view){%]
[%if (current().startsWith("view.FacetMap")){%]
<%@ page import="
java.io.File,
org.apache.tools.ant.Project,
org.apache.tools.ant.ProjectHelper;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileOutputStream;
"
contentType="text/html; charset=ISO-8859-1" 
%>
<%
//Update code
Project ant = new Project();
//add a listener to see ant's log
org.apache.tools.ant.DefaultLogger log = new org.apache.tools.ant.DefaultLogger();  
log.setOutputPrintStream(System.out);
log.setMessageOutputLevel(Project.MSG_INFO);
ant.addBuildListener(log);
String path = System.getProperty("catalina.home")+"/webapps/facetmap-facets-[%nGet("jsp_name").substring(nGet("jsp_name").lastIndexOf("-") +1)%]/WEB-INF/";
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
response.sendRedirect("servletParameters.jsp"); %>
[%}%]
[%}%]
[%}%]
[%}%]
[%}%]
[%}%]