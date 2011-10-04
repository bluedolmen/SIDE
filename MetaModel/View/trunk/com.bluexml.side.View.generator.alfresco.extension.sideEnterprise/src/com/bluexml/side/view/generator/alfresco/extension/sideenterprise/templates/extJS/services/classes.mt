<% metamodel http://www.kerblue.org/view/1.0 
 
%>

<%script type="view.AbstractViewOf" name="getAllSortedAttibutes"%>
<%getFields().mapTo.filter("clazz.Attribute").nSort("name")%>
<%script type="view.AbstractViewOf" name="getAllSortedAttibutes_"%>
<%getFields()[path == null].mapTo.filter("clazz.Attribute").nSort("name")%>
<%script type="view.AbstractViewOf" name="getClassModel"%>
<%viewOf.getRootContainer().filter("clazz.Model")%>
<%script type="view.AbstractDataTable" name="getMaxResults" post="trim()" %>
<%if (paging != null && paging.maxItems != 0){%>
<%paging.maxItems%>
<%}else{%>
10
<%}%>
