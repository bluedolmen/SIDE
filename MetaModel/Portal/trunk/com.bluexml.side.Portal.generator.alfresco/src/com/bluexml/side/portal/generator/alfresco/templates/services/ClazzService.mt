<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
%>
<%-- Must be genereated using Portal generator to choose the view --%>


<%script type="common.NamedModelElement" name="getFolder" description="Get the folder to export" %>
<%if (getRootContainer().name != null && getRootContainer().name.length() > 0){%>
<%getRootContainer().name%><%}else{%>
tmp<%}%>
<%script type="clazz.ClassModelElement" name="getNameSpace"%>
<%getNamespaceURI()%>
<%script type="common.NamedModelElement" name="getContentType"%>
<%getPrefixedQName()%>
<%script type="common.NamedModelElement" name="getQualifiedName"%>
<%getNamedModelElementQName()%>

<%script type="portal.Page" name="isDefaultSharePage"  post="trim()"%>
<%if (ID == "documentlibrary"
|| ID == "calendar"
|| ID == "wiki-page"
|| ID == "discussions-topiclist"
|| ID == "blog-postlist"
){%>
true
<%}else{%>
false
<%}%>
<%script type="portal.Page" name="isDefaultPublicSharePage"  post="trim()"%>
<%if (ID == "documentlibrary"
|| ID == "calendar"
|| ID == "wiki-page"
|| ID == "discussions-topiclist"
|| ID == "blog-postlist"
){%>
true
<%}else{%>
false
<%}%>
<%script type="portal.Portlet" name="isDefaultShareComponent" post="trim()"%>
<%if (name == "documentlibrary"
|| name == "documentdetails"
){%>
true
<%}else{%>
false
<%}%>
