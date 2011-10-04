<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
import com.bluexml.side.clazz.generator.alfresco.api.service.ValueGenerator

%>

<%script type="ClassPackage" name="getAllSortedAbstractClasses"%>
<%getAllAbstractClasses().nSort("name")%>

<%script type="AbstractClass" name="getAllSortedAttibutes"%>
<%service::getAllSortedAttibutes().nSort("name")%>

<%script type="AbstractClass" name="getAllAbstractClassSortedAttibutes"%>
<%service::getAllAbstractClassSortedAttibutes().nSort("name")%>
<%script type="AbstractClass" name="getAllSearchableSortedAttibutes"%>
<%getSearchableAttibutes().nSort("name")%>

<%script type="AbstractClass" name="getSortedAttibutes"%>
<%attributes.nSort("name")%>

<%script type="Attribute" name="isMultivalued"%>
<%metainfo[key.equalsIgnoreCase("multiple")].nSize()>0%>