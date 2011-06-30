<%
metamodel http://www.kerblue.org/portal/1.0
%>

<%-- Templates creation --%>
<%script type="Portal" name="createMessages"%>
<%if (eContainer() == null) {%>
<%getProperty("alf.share.paths.web-ext")%>/sharePortal<%name%>Extensions/messages.properties
<%}%>
<%script type="Portal" name="alfrescoGenerator" file="<%createMessages%>"%>

## BlueXML SIDE Custom pages keys
<%for (pageSet){%>
<%ID.toLowerCase().nPut("messages_name")%>
<%ID.nPut("title_name")%>
<%title.nPut("description_name")%>
<%if (current().generate){%>
page.<%nGet("messages_name")%>.title=<%nGet("title_name")%>
page.<%nGet("messages_name")%>.description=<%if (nGet("description_name") !=null){%><%nGet("description_name")%><%}else{%><%}%>
<%}%>

<%}%>
