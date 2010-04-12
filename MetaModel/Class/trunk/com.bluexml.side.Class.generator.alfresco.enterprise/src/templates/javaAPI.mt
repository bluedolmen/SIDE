<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import templates.services.lib
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices

%>
<%script type="clazz.Clazz" name="validatedFilename"%>
<%getProperty("javaWebServicesAPIPath")%>/<%getProperty("javaSource")%>/com/bluexml/side/alfresco/crud/<%service::getRootContainer().name%>/<%eContainer().getQualifiedName().replaceAll("_","/")%>/<%name.toU1Case()%>API.java
<%script type="clazz.Clazz" name="alfrescoGenerator" file="<%validatedFilename%>"%>
package com.bluexml.side.alfresco.crud.<%service::getRootContainer().name%>.<%eContainer().getQualifiedName().replaceAll("_","\\.")%>;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.alfresco.webservice.authoring.AuthoringFault;
import org.alfresco.webservice.repository.QueryResult;
import org.alfresco.webservice.repository.RepositoryFault;
import org.alfresco.webservice.repository.UpdateResult;
import org.alfresco.webservice.types.CML;
import org.alfresco.webservice.types.CMLCreate;
import org.alfresco.webservice.types.CMLUpdate;
import org.alfresco.webservice.types.NamedValue;
import org.alfresco.webservice.types.Node;
import org.alfresco.webservice.types.ParentReference;
import org.alfresco.webservice.types.Predicate;
import org.alfresco.webservice.types.Reference;
import org.alfresco.webservice.types.ResultSet;
import org.alfresco.webservice.types.ResultSetRow;
import org.alfresco.webservice.types.ResultSetRowNode;
import org.alfresco.webservice.types.Version;
import org.alfresco.webservice.util.Constants;
import org.alfresco.webservice.util.Utils;
import org.alfresco.webservice.util.WebServiceFactory;

import com.bluexml.side.alfresco.crud.CommonAPI;
import com.bluexml.side.alfresco.model.<%service::getRootContainer().name%>.ModelQNames;
import com.bluexml.side.alfresco.model.<%service::getRootContainer().name%>.<%eContainer().getQualifiedName().replaceAll("_","\\.")%>.*;

public class <%name.toU1Case()%>API extends CommonAPI {
	
	/**
	 * Create a new <%name.toU1Case()%>
	 * 
	 * @param parent
	 *            path to the folder that must contains the new <%name.toU1Case()%>
<%for (getAllSortedAttibutes()){%>
	 * @param <%name%>
	 *            <%current("Clazz").name.toU1Case()%> attribute
<%}%>
	 * @return <%name.toU1Case()%> Object
	 * @throws RepositoryFault
	 * @throws RemoteException
	 */
	public <%name.toU1Case()%> create(String parent<%for (getAllSortedAttibutes()){%>, String <%name%><%}%>) throws RepositoryFault, RemoteException {
		<%name.toU1Case()%> <%name.toLowerCase()%> = new <%name.toU1Case()%>(null, <%if (getAllSortedAttibutes().nSize() >0){%><%for (getAllSortedAttibutes()){%><%name%><%if (i() < current("Clazz").getAllSortedAttibutes().nSize() -1){%>, <%}%><%}%>,<%}%>null);
		if (parent == null) {
			parent = "/app:company_home";
		}
		ParentReference parentReference = new ParentReference(store, null, parent, Constants.ASSOC_CONTAINS, Constants.createQNameString(Constants.NAMESPACE_CONTENT_MODEL, "<%name%>"));

		NamedValue[] properties;

		List<NamedValue> props = new ArrayList<NamedValue>();
	<%for (getAllSortedAttibutes()){%>
		if (<%name%> != null) {
			props.add(Utils.createNamedValue(ModelQNames.CONTENT_MODEL_ATTRIBUTE_<%current("Clazz").name.toUpperCase()%>_<%name.toUpperCase()%>, <%name%>));
		}
	<%}%>

		properties = props.toArray(new NamedValue[props.size()]);

		CMLCreate create = new CMLCreate(null, parentReference, null, null, null, ModelQNames.CONTENT_MODEL_TYPE_<%name.toUpperCase()%>, properties);
		CML cml = new CML();
		cml.setCreate(new CMLCreate[] { create });
		UpdateResult[] results = WebServiceFactory.getRepositoryService().update(cml);
		<%name.toLowerCase()%>.setUuid(results[0].getDestination().getUuid());
		return <%name.toLowerCase()%>;
	}
	
	/**
	 * Request Alfresco repository for <%name.toU1Case()%>
	 * 
	 * @param operator
	 *            lucene query operator ([AND|OR])
<%for (getAllSortedAttibutes()){%>
	 * @param <%name%>
	 *            <%current("Clazz").name.toU1Case()%> attribute
<%}%>
	 * @return List<<%name.toU1Case()%>>
	 * @throws RepositoryFault
	 * @throws RemoteException
	 */
	public List<<%name.toU1Case()%>> request<%name.toU1Case()%>(String operator<%for (getAllSortedAttibutes()){%>, String <%name%><%}%>) throws RepositoryFault, RemoteException {
		ResultSet resultSet = requestResultSet(operator<%for (getAllSortedAttibutes()){%>, <%name%><%}%>);
		return convetResultSet(resultSet);
	}
	
	/**
	 * Request <%name.toU1Case()%>
	 * 
	 * @param operator
	 *            lucene query operator ([AND|OR])
<%for (getAllSortedAttibutes()){%>
	 * @param <%name%>
	 *            <%current("Clazz").name.toU1Case()%> attribute
<%}%>
	 * @return ResultSet
	 * @throws RepositoryFault
	 * @throws RemoteException
	 */
	public ResultSet requestResultSet(String operator<%for (getAllSortedAttibutes()){%>, String <%name%><%}%>) throws RepositoryFault, RemoteException {
		String lucene ="+TYPE:\"" + ModelQNames.CONTENT_MODEL_TYPE_<%name.toUpperCase()%>+"\"";
<%if (getAllSortedAttibutes().nSize() > 0){%>
		List<NamedValue> props = new ArrayList<NamedValue>();
	<%for (getAllSortedAttibutes()){%>
		if (<%name%> != null) {
			props.add(Utils.createNamedValue(ModelQNames.CONTENT_MODEL_ATTRIBUTE_<%current("Clazz").name.toUpperCase()%>_<%name.toUpperCase()%>, <%name%>));
		}
	<%}%>
		String props_ = "";
		Iterator<NamedValue> it = props.iterator();
		while (it.hasNext()) {
			NamedValue type = (NamedValue) it.next();
			props_ += " @" + type.getName() + ":" + type.getValue();
			if (it.hasNext()) {
				props_ +=" "+operator+" ";
			}
		}
		props_ = props_.replaceAll("([{}])", "\\\\$1");
		props_ = props_.replaceAll("(http):", "$1\\\\:");
		lucene+=" "+props_;
<%}%>	
		return request(lucene);
	}
	
	/**
	 * Method to update <%name.toU1Case()%>
	 * 
	 * @param uuid
	 *            unique id of the object in repository
<%for (getAllSortedAttibutes()){%>
	 * @param <%name%>
	 *            <%current("Clazz").name.toU1Case()%> attribute
<%}%>
	 * @return
	 * @throws RepositoryFault
	 * @throws RemoteException
	 */
	public UpdateResult[] update(String uuid<%for (getAllSortedAttibutes()){%>, String <%name%><%}%>) throws RepositoryFault, RemoteException {
<%if (getAllSortedAttibutes().nSize() > 0){%>
		NamedValue[] properties;
		// Create an array of NamedValue objects with the props to set
		List<NamedValue> props = new ArrayList<NamedValue>();
		<%for (getAllSortedAttibutes()){%>
		if (<%name%> != null) {
			props.add(Utils.createNamedValue(ModelQNames.CONTENT_MODEL_ATTRIBUTE_<%current("Clazz").name.toUpperCase()%>_<%name.toUpperCase()%>, <%name%>));
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
<%}else{%>
		// nothing do to
		return null;
<%}%>
	}
<%for (getAllSourceAssociationEnds()){%>
	/**
	 * Method to create <%eContainer().getAssociationQName(current("AssociationEnd"))%> association
	 * 
	 * @param source
	 *            the first end of the association
	 * @param target
	 *            the second end of the association
	 * @return
	 * @throws RepositoryFault
	 * @throws RemoteException
	 */
	public UpdateResult[] createAssociation_<%eContainer().getAssociationQName(current("AssociationEnd"))%>(<%linkedClass.name.toU1Case()%> source, <%getOpposite().linkedClass.name.toU1Case()%> target) throws RepositoryFault, RemoteException {
		return createAssociation(source.getUuid(), ModelQNames.CONTENT_MODEL_ASSOCIATION_<%eContainer().getAssociationQName(current("AssociationEnd")).toUpperCase()%>, target.getUuid());
	}
	
	/**
	 * Method to remove <%eContainer().name%> association
	 * 
	 * @param source
	 *            the first end of the association
	 * @param target
	 *            the second end of the association
	 * @return
	 * @throws RepositoryFault
	 * @throws RemoteException
	 */
	public UpdateResult[] removeAssociation_<%eContainer().getAssociationQName(current("AssociationEnd"))%>(<%linkedClass.name.toU1Case()%> source, <%getOpposite().linkedClass.name.toU1Case()%> target) throws RepositoryFault, RemoteException {
		return removeAssociation(source.getUuid(), ModelQNames.CONTENT_MODEL_ASSOCIATION_<%eContainer().getAssociationQName(current("AssociationEnd")).toUpperCase()%>, target.getUuid());
	}
	
	/**
	 * Method to get nodes associated by <%eContainer().name%> to a given <%current("Clazz").name.toU1Case()%>
	 * 
	 * @param source a <%linkedClass.name.toU1Case()%>
	 * @return
	 * @throws RepositoryFault
	 * @throws RemoteException
	 */
	public QueryResult getAssociatedTarget_<%eContainer().getAssociationQName(current("AssociationEnd"))%>(<%linkedClass.name.toU1Case()%> source) throws RepositoryFault, RemoteException {
		return getAssociatedNode(source.getUuid(), ModelQNames.CONTENT_MODEL_ASSOCIATION_<%eContainer().getAssociationQName(current("AssociationEnd")).toUpperCase()%>, "target");
	}
	
	/**
	 * Method to get nodes associated by hasParentpage to a given <%linkedClass.name.toU1Case()%>
	 * 
	 * @param source a <%linkedClass.name.toU1Case()%>
	 * @return List of <%getOpposite().linkedClass.name.toU1Case()%>
	 * @throws RepositoryFault
	 * @throws RemoteException
	 */
	public List<<%getOpposite().linkedClass.name.toU1Case()%>> getAssociatedTarget_<%eContainer().getAssociationQName(current("AssociationEnd"))%>As<%getOpposite().linkedClass.name.toU1Case()%>(<%linkedClass.name.toU1Case()%> source) throws RepositoryFault, RemoteException {
		QueryResult qr = getAssociatedNode(source.getUuid(), ModelQNames.CONTENT_MODEL_ASSOCIATION_<%eContainer().getAssociationQName(current("AssociationEnd")).toUpperCase()%>, "target");
		return new <%getOpposite().linkedClass.name.toU1Case()%>API().convetResultSet(qr.getResultSet()); 
	}
	
<%}%>


	/**
	 * Method that convert a resultSet as List of <%name.toU1Case()%>
	 * 
	 * @param resultSet
	 *            the result set to convert
	 * @return List of <%name.toU1Case()%>
	 * @throws RemoteException
	 * @throws RepositoryFault
	 */
	public List<<%name.toU1Case()%>> convetResultSet(ResultSet resultSet) throws RemoteException, RepositoryFault {
		List<<%name.toU1Case()%>> results<%name.toU1Case()%> = new ArrayList<<%name.toU1Case()%>>();
		ResultSetRow[] rows = resultSet.getRows();
		if (rows != null) {
			for (ResultSetRow resultSetRow : rows) {
				<%name.toU1Case()%> obj = new <%name.toU1Case()%>();
				
				ResultSetRowNode node = resultSetRow.getNode();
				String uuid=node.getId();
				// set uuid
				obj.setUuid(uuid);
<%if (getAllSortedAttibutes().nSize() > 0){%>
				// get node properties
				Reference r = new Reference(store, uuid, null);
				Node[] n = WebServiceFactory.getRepositoryService().get(new Predicate(new Reference[]{r}, store, null));				
				
				setProperties(obj, n[0]);
<%}%>
				results<%name.toU1Case()%>.add(obj);
			}
		}
		return results<%name.toU1Case()%>;
	}
	
	public <%name.toU1Case()%> get<%name.toU1Case()%>Version(String uuid, String label) throws AuthoringFault, RemoteException {
		Version v = getVersion(uuid, label);
		Reference ref = v.getId();
		Predicate pre = new Predicate(new Reference[] {ref}, v.getId().getStore(), null);
		Node[] n = WebServiceFactory.getRepositoryService().get(pre);
		if (n.length > 0) {
			<%name.toU1Case()%> obj = new <%name.toU1Case()%>();
			setProperties(obj, n[0]);
			return obj;
		}
		return null;
	}


	/**
	 * update properties object from given node
	 * 
	 * @param obj
	 * @param n
	 */
	private void setProperties(<%name.toU1Case()%> obj, Node n) {
		NamedValue[] values = n.getProperties();
		for (NamedValue namedValue : values) {
			String propName = namedValue.getName();
			String value = namedValue.getValue();
			// TODO : manage multivalued attributes
		<%for (getAllSortedAttibutes()){%>
			<%if (i() != 0){%>} else<%}%> if (propName.equals(ModelQNames.CONTENT_MODEL_ATTRIBUTE_<%current("Clazz").name.toUpperCase()%>_<%name.toUpperCase()%>)) {
				obj.set<%name.toU1Case()%>(value);
				<%if (i() == current("Clazz").getAllSortedAttibutes().nSize() -1){%>
			}
			<%}%>
		<%}%>

		}
	}
}

