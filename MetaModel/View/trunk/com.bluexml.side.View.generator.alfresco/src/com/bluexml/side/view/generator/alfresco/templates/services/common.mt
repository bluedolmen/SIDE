<%
metamodel http://www.kerblue.org/view/1.0
%>

<%script type="common.NamedModelElement" name="getQualifiedName"%>
<%getFullName().replaceAll("\.","_")%>
<%script type="common.NamedModelElement" name="getPrefixedQualifiedName"%>
<%getRootContainer().name%>:<%getQualifiedName()%>
<%script type="clazz.Attribute" name="getFtlTypeConverter"%>
<%if (typ == "boolean"){%>?boolean<%}else{%>
<%if (typ == "byte"){%>?int<%}else{%>
<%if (typ == "char"){%>?string<%}else{%>
<%if (typ == "double"){%>?double<%}else{%>
<%if (typ == "float"){%>?float<%}else{%>
<%if (typ == "int"){%>?int<%}else{%>
<%if (typ == "long"){%>?long<%}else{%>
<%if (typ == "short"){%>?long<%}else{%>
<%if (typ == "String"){%>?string<%}else{%>
<%if (typ == "Date"){%>?date<%}else{%>
<%if (typ == "Object"){%>?string<%}else{%>
<%if (typ == "DateTime"){%>?datetime<%}else{%>
<%if (typ == "Time"){%>?time<%}else{%>?string<%}%>
<%}%>
<%}%>
<%}%>
<%}%>
<%}%>
<%}%>
<%}%>
<%}%>
<%}%>
<%}%>
<%}%>
<%}%>

