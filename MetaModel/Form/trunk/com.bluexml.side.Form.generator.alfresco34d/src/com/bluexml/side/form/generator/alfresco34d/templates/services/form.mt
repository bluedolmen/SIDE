<%
metamodel http://www.kerblue.org/form/1.0
%>

<%script type="FormElement" name="getPrefixedQualifiedName"%>
<%getRootContainer().filter("common.NamedModelElement").name%>.<%getFullName()%>