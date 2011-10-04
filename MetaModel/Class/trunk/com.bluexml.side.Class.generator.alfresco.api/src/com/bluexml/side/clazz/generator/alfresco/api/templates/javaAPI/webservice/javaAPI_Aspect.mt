<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import com.bluexml.side.clazz.generator.alfresco.api.templates.services.classes
import com.bluexml.side.clazz.generator.alfresco.api.templates.javaAPI.lib
import com.bluexml.side.clazz.generator.alfresco.api.templates.javaAPI.webservice.javaAPI
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices

%>
<%script type="clazz.Aspect" name="validatedFilename"%>
<%getProperty("javaWebServicesAPIPath")%>/<%getProperty("javaSource")%>/com/bluexml/side/alfresco/crud/<%service::getRootContainer().name%>/<%eContainer().getQualifiedName().replaceAll("_","/")%>/<%getJavaAPIName()%>.java
<%script type="clazz.Aspect" name="alfrescoGenerator" file="<%validatedFilename%>"%>
package com.bluexml.side.alfresco.crud.<%service::getRootContainer().name%>.<%eContainer().getQualifiedName().replaceAll("_","\\.")%>;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.alfresco.webservice.repository.RepositoryFault;
import org.alfresco.webservice.repository.UpdateResult;
import org.alfresco.webservice.types.CML;
import org.alfresco.webservice.types.CMLUpdate;
import org.alfresco.webservice.types.NamedValue;
import org.alfresco.webservice.types.Node;
import org.alfresco.webservice.types.Predicate;
import org.alfresco.webservice.types.Reference;
import org.alfresco.webservice.util.Utils;
import org.alfresco.webservice.util.WebServiceFactory;

import com.bluexml.side.alfresco.crud.AbstractAspectFactory;
import com.bluexml.side.alfresco.model.<%service::getRootContainer().name%>.ModelQNames;
import com.bluexml.side.alfresco.model.<%service::getRootContainer().name%>.<%eContainer().getQualifiedName().replaceAll("_","\\.")%>.*;


public class <%getJavaAPIName()%> extends AbstractAspectFactory {

	/**
	 * add Coordonnees <%getJavaModelObjectName()%> to node
	 <%for (getAllSortedAttibutes()){%>
	 * @param <%name%>
	 <%}%>	
	 * @throws RepositoryFault
	 * @throws RemoteException
	 */
	public void addAspectTo(String uuid, <%getJavaPropertiesMethodSignature()%>) throws RepositoryFault, RemoteException {
		NamedValue[] properties;

		List<NamedValue> props = new ArrayList<NamedValue>();
	<%for (getSortedAttibutes()){%>
		if (<%name%> != null) {
			props.add(Utils.createNamedValue(ModelQNames.CONTENT_MODEL_ATTRIBUTE_<%current("Aspect").name.toUpperCase()%>_<%name.toUpperCase()%>.getQnameString(), <%name%><%if (isMultivalued()){%>.toArray(new String[<%name%>.size()])<%}%>));
		}
	<%}%>
		properties = props.toArray(new NamedValue[props.size()]);
		addAspectTo(ModelQNames.CONTENT_MODEL_ASPECT_<%name.toUpperCase()%>.getQnameString(), properties, uuid);
	}
	
	/**
	 * Method to update Commentaire
	 * 
	 * @param uuid
	 *            unique id of the object in repository
	 * @param date
	 *            Commentaire attribute
	 * @param libelle
	 *            Commentaire attribute
	 * @param nom
	 *            Commentaire attribute
	 * @return
	 * @throws RepositoryFault
	 * @throws RemoteException
	 */
	public UpdateResult[] update(String uuid, <%getJavaPropertiesMethodSignature()%>) throws RepositoryFault, RemoteException {
		NamedValue[] properties;

		List<NamedValue> props = new ArrayList<NamedValue>();
	<%for (getAllSortedAttibutes()){%>
		if (<%name%> != null) {
			props.add(Utils.createNamedValue(ModelQNames.CONTENT_MODEL_ATTRIBUTE_<%current("Aspect").name.toUpperCase()%>_<%name.toUpperCase()%>.getQnameString(), <%name%><%if (isMultivalued()){%>.toArray(new String[<%name%>.size()])<%}%>));
		}
	<%}%>
		properties = props.toArray(new NamedValue[props.size()]);

		Reference doc = new Reference(store, uuid, null);		

		// Construct CML statement to update node
		CMLUpdate updateDoc = new CMLUpdate(properties, new Predicate(new Reference[] { doc }, store, null), null);
		// Construct CML Block
		CML cml = new CML();
		cml.setUpdate(new CMLUpdate[] { updateDoc });
		// Execute CML Block
		return WebServiceFactory.getRepositoryService().update(cml);
	}
	
	/**
	 * 
	 * @param uuid
	 * @throws Exception
	 */
	public void removeAspect(String uuid) throws Exception {
		removeAspectTo(ModelQNames.CONTENT_MODEL_ASPECT_<%name.toUpperCase()%>.getQnameString(), uuid);
	}
	
	public boolean is<%getJavaModelObjectName()%>(String uuid) throws Exception {
		Node n = getNode(uuid);
		String[] aspect = n.getAspects();
		for (String string : aspect) {
			if (string.equals(ModelQNames.CONTENT_MODEL_ASPECT_<%name.toUpperCase()%>.getQnameString())) {
				return true;
			}
		}		
		return false;
	}
	
<%commonMethods()%>
}
