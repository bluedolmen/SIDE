<%
metamodel http://www.kerblue.org/view/1.0
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
%>

<%script type="common.NamedModelElement" name="getQualifiedName"%>
<%getNamedModelElementQName()%>
<%script type="common.NamedModelElement" name="getPrefixedQualifiedName"%>
<%getPrefixedQName()%>
<%script type="common.NamedModelElement" name="getFolder" description="Get the folder to export" %>
<%if (getRootContainer().name != null && getRootContainer().name.length() > 0){%>
<%getRootContainer().name%><%}else{%>
tmp<%}%>
<%script type="clazz.ClassModelElement" name="getNameSpace"%>
<%getNamespaceURI()%>
<%script type="view.AbstractViewOf" name="getAllSortedAttibutes"%>
<%getFields()[path == null].mapTo.filter("clazz.Attribute").nSort("name")%>
<%script type="view.AbstractViewOf" name="getClassModel"%>
<%viewOf.getRootContainer().filter("clazz.Model")%>

<%script type="clazz.Attribute" name="generateAttributeStatement"%>
<#if (<%args(0)%>.properties["<%getRootContainer().name%>:<%eContainer().getQualifiedName()%>_<%name%>"]?exists)>
	<#if <%args(0)%>.properties["<%getRootContainer().name%>:<%eContainer().getQualifiedName()%>_<%name%>"]?is_sequence>
	"<%eContainer().getQualifiedName()%>_<%name%>":"<#list <%args(0)%>.properties["<%getRootContainer().name%>:<%eContainer().getQualifiedName()%>_<%name%>"] as key>${key} </#list>"
	<#else/>
	<%if (typ.toString().equalsIgnoreCase("date")){%>
	"<%eContainer().getQualifiedName()%>_<%name%>":"${<%args(0)%>.properties["<%getRootContainer().name%>:<%eContainer().getQualifiedName()%>_<%name%>"]?string("yyyy-MM-dd'T'HH:mm:ss.SSSZ")!""}"
	<%}else if (typ.toString().equalsIgnoreCase("datetime")){%>
	"<%eContainer().getQualifiedName()%>_<%name%>":"${<%args(0)%>.properties["<%getRootContainer().name%>:<%eContainer().getQualifiedName()%>_<%name%>"]?string("yyyy-MM-dd'T'HH:mm:ss.SSSZ")!""}"
	<%}else{%>
	"<%eContainer().getQualifiedName()%>_<%name%>":"${<%args(0)%>.properties["<%getRootContainer().name%>:<%eContainer().getQualifiedName()%>_<%name%>"]?string!""}"
	<%}%>
	</#if>
<#else/>
	"<%eContainer().getQualifiedName()%>_<%name%>":""
</#if>