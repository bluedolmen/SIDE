<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices

%>
<%script type="clazz.ClassPackage" name="validatedFilename"%>
<%if (eContainer() == null) {%><%getProperty("javaEmbeddedAPIPath")%>/<%getProperty("javaSource")%>/com/bluexml/side/alfresco/crud/<%getProperty("java.classes.abstractAspectFactory")%>.java<%}%>
<%script type="clazz.ClassPackage" name="alfrescoGenerator" file="<%validatedFilename%>"%>
package <%getProperty("javaPackageAPI")%>;

import java.io.Serializable;
import java.util.Map;

import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.QName;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public abstract class <%getProperty("java.classes.abstractAspectFactory")%> {
	protected static Log logger = LogFactory.getLog(<%getProperty("java.classes.abstractAspectFactory")%>.class);
	protected NodeService nodeService;
	protected ServiceRegistry serviceRegistry;

	public NodeService getNodeService() {
		return nodeService;
	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	public ServiceRegistry getServiceRegistry() {
		return serviceRegistry;
	}

	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}
	
	
	/**
	 * Method to add an Aspect on a node
	 * @param aspectQName
	 * @param values
	 * @param uuid
	 * @return
	 */
	protected void addAspectTo(QName aspectQName, Map<QName, Serializable> props, NodeRef uuid) {
		serviceRegistry.getNodeService().addAspect(uuid, aspectQName, props);
	}
	
	/**
	 * Method to remove an Aspect from a node 
	 * @param aspectQName
	 * @param uuid
	 * @return
	 */
	protected void removeAspectTo(QName aspectQName, NodeRef uuid) {
		serviceRegistry.getNodeService().removeAspect(uuid, aspectQName);
	}
	
	/**
	 * Updates properties of a node.
	 * 
	 * @param nodeToUpdate
	 * @param properties
	 */
	protected void updateProperties(NodeRef nodeToUpdate, Map<QName, Serializable> properties) {
		for (Map.Entry<QName, Serializable> entry : properties.entrySet()) {
			QName propertyName = entry.getKey();
			Serializable convertedValue = entry.getValue();
			// must convert value to proper one according to properties
			// definitions
			// PropertyDefinition propertyDef =
			// serviceRegistry.getDictionaryService().getProperty(propertyName);
			// convertedValue = makePropertyValue(propertyDef, convertedValue);

			// set property value
			nodeService.setProperty(nodeToUpdate, propertyName, convertedValue);
		}
	}
}