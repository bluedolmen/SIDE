<%
metamodel http://www.kerblue.org/view/1.0
%>

<%script type="common.NamedModelElement" name="getQualifiedName"%>
<%getFullName().replaceAll("\.","_")%>
<%script type="common.NamedModelElement" name="getPrefixedQualifiedName"%>
<%getRootContainer().name%>:<%getQualifiedName()%>