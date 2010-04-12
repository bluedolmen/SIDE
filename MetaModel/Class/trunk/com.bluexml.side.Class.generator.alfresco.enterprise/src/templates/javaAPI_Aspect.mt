<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import templates.services.lib
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices

%>
<%script type="clazz.Aspect" name="validatedFilename"%>
<%getProperty("javaWebServicesAPIPath")%>/<%getProperty("javaSource")%>/com/bluexml/side/alfresco/crud/<%service::getRootContainer().name%>/<%eContainer().getQualifiedName().replaceAll("_","/")%>/<%name.toU1Case()%>API.java
<%script type="clazz.Aspect" name="alfrescoGenerator" file="<%validatedFilename%>"%>
package com.bluexml.side.alfresco.crud.<%service::getRootContainer().name%>.<%eContainer().getQualifiedName().replaceAll("_","\\.")%>;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.alfresco.webservice.repository.RepositoryFault;
import org.alfresco.webservice.types.NamedValue;
import org.alfresco.webservice.util.Utils;

import com.bluexml.side.alfresco.crud.CommonAPI;
import com.bluexml.side.alfresco.model.<%service::getRootContainer().name%>.ModelQNames;


public class <%name.toU1Case()%>API extends CommonAPI {

	public void addAspectTo(String uuid, <%for (getSortedAttibutes()){%>String <%name%><%if (i() < current("Aspect").getSortedAttibutes().nSize() -1){%>, <%}%><%}%>) throws RepositoryFault, RemoteException {
		NamedValue[] properties;

		List<NamedValue> props = new ArrayList<NamedValue>();
	<%for (getSortedAttibutes()){%>
		if (<%name%> != null) {
			props.add(Utils.createNamedValue(ModelQNames.CONTENT_MODEL_ATTRIBUTE_<%current("Aspect").name.toUpperCase()%>_<%name.toUpperCase()%>, <%name%>));
		}
	<%}%>
		properties = props.toArray(new NamedValue[props.size()]);
		addAspectTo(ModelQNames.CONTENT_MODEL_ASPECT_<%name.toUpperCase()%>, properties, uuid);
	}
}
