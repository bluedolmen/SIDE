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