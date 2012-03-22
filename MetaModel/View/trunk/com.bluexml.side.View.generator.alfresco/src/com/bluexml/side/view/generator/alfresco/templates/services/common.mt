<%
metamodel http://www.kerblue.org/view/1.0
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
%>

<%script type="common.NamedModelElement" name="getFolder" description="Get the folder to export" %>
<%if (getRootContainer().name != null && getRootContainer().name.length() > 0){%>
<%getRootContainer().name%><%}else{%>
tmp<%}%>
<%script type="view.AbstractViewOf" name="getAllSortedAttibutes"%>
<%getFields()[path == null].mapTo.filter("clazz.Attribute").nSort("name")%>
<%script type="view.AbstractViewOf" name="getClassModel"%>
<%viewOf.getRootContainer().filter("clazz.Model")%>

<%script type="clazz.Attribute" name="generateAttributeStatement"%>
<#if (<%args(0)%>.properties["<%getPrefixedQName()%>"]?exists)>
	<#if <%args(0)%>.properties["<%getPrefixedQName()%>"]?is_sequence>
	"<%getPrefixedQName()%>":"<#list <%args(0)%>.properties["<%getPrefixedQName()%>"] as key>${key} </#list>"
	<#else>
	<%if (typ.toString().equalsIgnoreCase("date")){%>
	"<%getPrefixedQName()%>":"${<%args(0)%>.properties["<%getPrefixedQName()%>"]?string("yyyy-MM-dd'T'HH:mm:ss.SSSZ")!""}"
	<%}else if (typ.toString().equalsIgnoreCase("datetime")){%>
	"<%getPrefixedQName()%>":"${<%args(0)%>.properties["<%getPrefixedQName()%>"]?string("yyyy-MM-dd'T'HH:mm:ss.SSSZ")!""}"
	<%}else{%>
	"<%getPrefixedQName()%>":"${<%args(0)%>.properties["<%getPrefixedQName()%>"]?string!""}"
	<%}%>
	</#if>
<#else>
	"<%getPrefixedQName()%>":""
</#if>

<%script type="clazz.Attribute" name="generateAttributeStatement_csv"%>
<#if (<%args(0)%>.properties["<%getPrefixedQName()%>"]?exists)><#t>
	<#if <%args(0)%>.properties["<%getPrefixedQName()%>"]?is_sequence><#t>
	"<#list <%args(0)%>.properties["<%getPrefixedQName()%>"] as key>${key}<#if key_has_next> </#if></#list>"<#t>
	<#else><#t>
	<%if (typ.toString().equalsIgnoreCase("date")){%><#t>
	"${<%args(0)%>.properties["<%getPrefixedQName()%>"]?string("yyyy-MM-dd'T'HH:mm:ss.SSSZ")!""}"<#t>
	<%}else if (typ.toString().equalsIgnoreCase("datetime")){%>
	"${<%args(0)%>.properties["<%getPrefixedQName()%>"]?string("yyyy-MM-dd'T'HH:mm:ss.SSSZ")!""}"<#t>
	<%}else{%>
	"${<%args(0)%>.properties["<%getPrefixedQName()%>"]?string!""}"<#t>
	<%}%>
	</#if><#t>
<#else><#t>
	""<#t>
</#if><#t>