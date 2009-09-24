<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.services.ProxieServices
%>
<%-- Must be genereated using Portal generator to choose the view --%>


<%script type="common.NamedModelElement" name="getFolder" description="Get the folder to export" %>
<%if (getRootContainer().name != null && getRootContainer().name.length() > 0){%>
<%getRootContainer().name%><%}else{%>
tmp<%}%>
<%script type="clazz.ClassModelElement" name="getNameSpace"%>
http://www.bluexml.com/model/content/<%getFolder()%>/1.0
<%script type="common.NamedModelElement" name="getContentType"%>
<%getFolder()%>:<%getNamedModelElementQName()%>
<%script type="common.NamedModelElement" name="getQualifiedName"%>
<%getNamedModelElementQName()%>