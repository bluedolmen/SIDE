<%
metamodel /com.bluexml.side.Common/model/Common.ecore

import templates.servicesTemplates.Common
import com.bluexml.side.clazz.generator.alfresco.api.templates.javaAPI.lib
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
%>

<%script type="common.NamedModelElement" name="getQName" post="trim()" %>
new ModelQNames("<%getPrefix()%>", "<%getNamespaceURI()%>", "<%args(0)%>")
