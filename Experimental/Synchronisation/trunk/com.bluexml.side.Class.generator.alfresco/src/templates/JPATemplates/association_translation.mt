<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import templates.servicesTemplates.Attribute
import templates.servicesTemplates.Association

import com.bluexml.side.clazz.generator.alfresco.services.AttributeServices
import com.bluexml.side.clazz.generator.alfresco.services.AssociationServices
import com.bluexml.side.clazz.generator.alfresco.services.ClassServices
%>

<%script type="clazz.ClassPackage" name="association_translation" file="association_translation.properties"%>

<%for (classSet){%>
<%for (getSourceAssociations()){%>
<%getQualifiedName(current("Clazz"))%>=<%name%>
<%}%>
<%}%>

