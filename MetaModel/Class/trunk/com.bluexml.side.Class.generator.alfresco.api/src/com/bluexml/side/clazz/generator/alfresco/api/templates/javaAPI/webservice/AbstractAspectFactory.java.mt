<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices

%>
<%script type="clazz.ClassPackage" name="validatedFilename"%>
<%if (eContainer() == null) {%><%getProperty("javaWebServicesAPIPath")%>/<%getProperty("javaSource")%>/com/bluexml/side/alfresco/crud/AbstractAspectFactory.java<%}%>
<%script type="clazz.ClassPackage" name="alfrescoGenerator" file="<%validatedFilename%>"%>
package com.bluexml.side.alfresco.crud;

import java.rmi.RemoteException;

import org.alfresco.webservice.repository.RepositoryFault;
import org.alfresco.webservice.repository.UpdateResult;
import org.alfresco.webservice.types.CML;
import org.alfresco.webservice.types.CMLAddAspect;
import org.alfresco.webservice.types.CMLRemoveAspect;
import org.alfresco.webservice.types.NamedValue;
import org.alfresco.webservice.types.Node;
import org.alfresco.webservice.types.Predicate;
import org.alfresco.webservice.types.Reference;
import org.alfresco.webservice.types.Store;
import org.alfresco.webservice.util.Constants;
import org.alfresco.webservice.util.WebServiceFactory;

public abstract class AbstractAspectFactory {
protected Store store = null;
	
	public AbstractAspectFactory() {
		store = new Store(Constants.WORKSPACE_STORE, "SpacesStore");
	}
	
	public AbstractAspectFactory(Store store) {
		this.store = store;
	}
	
	
	/**
	 * Method to add an Aspect on a node
	 * @param aspectQName
	 * @param values
	 * @param uuid
	 * @return
	 * @throws RepositoryFault
	 * @throws RemoteException
	 */
	protected UpdateResult[] addAspectTo(String aspectQName, NamedValue[] values, String uuid) throws RepositoryFault, RemoteException {
		Reference r = new Reference(store, uuid, null);
		Predicate pre= new Predicate(new Reference[]{r}, store, null);
		CMLAddAspect addAspect = new CMLAddAspect(aspectQName, values, pre, null);
        CML cml = new CML();        
        cml.setAddAspect(new CMLAddAspect[]{addAspect});
        
        // Execute CML Block
        return WebServiceFactory.getRepositoryService().update(cml);
	}
	
	/**
	 * Method to remove an Aspect from a node 
	 * @param aspectQName
	 * @param uuid
	 * @return
	 * @throws RepositoryFault
	 * @throws RemoteException
	 */
	protected UpdateResult[] removeAspectTo(String aspectQName, String uuid) throws RepositoryFault, RemoteException {
		Reference r = new Reference(store, uuid, null);
		Predicate pre= new Predicate(new Reference[]{r}, store, null);
		CMLRemoveAspect addAspect = new CMLRemoveAspect(aspectQName, pre, null);
        CML cml = new CML();        
        cml.setRemoveAspect(new CMLRemoveAspect[]{addAspect});
        
        // Execute CML Block
        return WebServiceFactory.getRepositoryService().update(cml);        
	}
	
	/**
	 * get Node object
	 * @param uuid
	 * @return
	 * @throws RemoteException
	 * @throws RepositoryFault
	 */
	protected Node getNode(String uuid) throws RemoteException, RepositoryFault {
		Reference r = new Reference(store, uuid, null);
		Node[] n = WebServiceFactory.getRepositoryService().get(new Predicate(new Reference[]{r}, store, null));
		return n[0];
	}
}
