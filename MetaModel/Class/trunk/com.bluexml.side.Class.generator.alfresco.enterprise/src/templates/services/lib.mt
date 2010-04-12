<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices

%>
<%script type="Clazz" name="getAllSortedAttibutes"%>
<%getAllAttributes().nSort("name")%>
<%script type="AbstractClass" name="getSortedAttibutes"%>
<%attributes.nSort("name")%>