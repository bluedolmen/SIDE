<%
metamodel http://XML
%>
<%script type="XML.Element" name="createElement"%>
<<%name%> 
<%for (children){%>
<%if (current().eClass().name.equalsIgnoreCase("Attribute")){%>
<%name%>="<%value%>"
<%}%>
<%}%>>
<%for (children) {%>
<%if (current().eClass().name.equalsIgnoreCase("Element")){%>
  <%current().createElement()%>
<%}%>
<%}%>
</<%name%>>
<%script type="XML.Root" name="default" file="out.mm"%>
<?xml version="1.0" encoding="ISO-8859-1"?>
<%current().createElement()%>