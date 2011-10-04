<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import com.bluexml.side.clazz.generator.alfresco.api.service.ValueGenerator
import com.bluexml.side.clazz.generator.alfresco.api.templates.javaAPI.embedded.javaAPI
import com.bluexml.side.clazz.generator.alfresco.api.templates.javaAPI.lib
import com.bluexml.side.clazz.generator.alfresco.api.templates.services.classes
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices


%>
<%script type="clazz.Clazz" name="validatedFilename"%>
<%getProperty("javaEmbeddedAPIPath")%>/<%getProperty("javaSource")%>/com/bluexml/side/alfresco/crud/<%service::getRootContainer().name%>/<%eContainer().getQualifiedName().replaceAll("_","/")%>/<%getJavaAPIName()%>.java
<%script type="clazz.Clazz" name="alfrescoGenerator" file="<%validatedFilename%>"%>
package <%getJavaAPIPackage()%>;

import java.io.Serializable;
import java.util.ArrayList;
<%if (getAllSortedAttibutes().filter("Attribute").typ.nContains("Date") ||  getAllSortedAttibutes().filter("Attribute").typ.nContains("DateTime") ||  getAllSortedAttibutes().filter("Attribute").typ.nContains("Time")){%>
import java.util.Date;
<%}%>

import java.util.HashMap;
<%if (getAllSearchableSortedAttibutes().nSize() > 0){%>
import java.util.Iterator;
<%}%>
import java.util.List;
import java.util.Map;


import org.alfresco.model.ContentModel;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.version.Version;
import org.alfresco.service.namespace.InvalidQNameException;
import org.alfresco.service.namespace.QName;
<%if (getAllSortedAttibutes().filter("Attribute").typ.nContains("Date") ||  getAllSortedAttibutes().filter("Attribute").typ.nContains("DateTime") ||  getAllSortedAttibutes().filter("Attribute").typ.nContains("Time")){%>
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
<%}%>

import <%getProperty("javaPackageAPI")%>.<%getProperty("java.classes.abstractClassFactory")%>;
import <%getProperty("javaPackageAPI")%>.<%service::getRootContainer().name%>.<%getProperty("java.classes.factoryRegistry")%>;
import <%getProperty("javaPackageModel")%>.<%service::getRootContainer().name%>.ModelQNames;
import <%getProperty("javaPackageModel")%>.<%service::getRootContainer().name%>.<%eContainer().getQualifiedName().replaceAll("_","\\.")%>.*;

public class <%getJavaAPIName()%> extends <%getProperty("java.classes.abstractClassFactory")%> {
	
	<%getProperty("java.classes.factoryRegistry")%> <%getProperty("java.classes.factoryRegistry").toL1Case()%>;
<%if (getAllSortedAttibutes().filter("Attribute").typ.nContains("Date") ||  getAllSortedAttibutes().filter("Attribute").typ.nContains("DateTime") ||  getAllSortedAttibutes().filter("Attribute").typ.nContains("Time")){%>
	DateTimeFormatter dateTimeFormatter = ISODateTimeFormat.dateTime();
<%}%>
	public <%getProperty("java.classes.factoryRegistry")%> get<%getProperty("java.classes.factoryRegistry")%>() {
		return <%getProperty("java.classes.factoryRegistry").toL1Case()%>;
	}

	public void set<%getProperty("java.classes.factoryRegistry")%>(<%getProperty("java.classes.factoryRegistry")%> <%getProperty("java.classes.factoryRegistry").toL1Case()%>) {
		this.<%getProperty("java.classes.factoryRegistry").toL1Case()%> = <%getProperty("java.classes.factoryRegistry").toL1Case()%>;
	}
	
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
	 * @throws Exception
	 */
	public <%name.toU1Case()%> create(String parent<%if (getAllSortedAttibutes().nSize() > 0){%>,<%}%> <%getJavaPropertiesMethodSignature()%>) throws InvalidQNameException, Exception {
		<%if (!mustAddcmContent) {%>
		<%name.toU1Case()%> <%name.toLowerCase()%> = new <%name.toU1Case()%>(null, <%if (getAllSortedAttibutes().nSize() >0){%><%for (getAllSortedAttibutes()){%><%name%><%if (i() < current("Clazz").getAllSortedAttibutes().nSize() -1){%>, <%}%><%}%><%}%>);	
		<%}else{%>
		<%name.toU1Case()%> <%name.toLowerCase()%> = new <%name.toU1Case()%>(null, <%if (getAllSortedAttibutes().nSize() >0){%><%for (getAllSortedAttibutes()){%><%name%><%if (i() < current("Clazz").getAllSortedAttibutes().nSize() -1){%>, <%}%><%}%>,<%}%>null);
		<%}%>
		if (parent == null) {
			parent = "/app:company_home";
		}
		// record <%name.toU1Case()%> object in Alfresco repository
<%generatePropertiesStatement()%>


		QName assocTypeQName = ContentModel.ASSOC_CONTAINS;
		// create the node
		NodeRef newNode = createNode(parent, assocTypeQName, null, QName.createQName(ModelQNames.CONTENT_MODEL_TYPE_<%name.toUpperCase()%>.getQnameString()), null, props);
		<%name.toLowerCase()%>.setUuid(newNode.toString());
		return <%name.toLowerCase()%>;
	}
	
	/**
	 * Request Alfresco repository for <%name.toU1Case()%>
	 * 
	 * @param operator
	 *            lucene query operator ([AND|OR])
<%for (getAllSearchableSortedAttibutes()){%>
	 * @param <%name%>
	 *            <%current("Clazz").name.toU1Case()%> attribute
<%}%>
	 * @return List<<%name.toU1Case()%>>
	 * @throws Exception
	 */
	public List<<%name.toU1Case()%>> request<%name.toU1Case()%>(String operator<%if (getAllSearchableSortedAttibutes().nSize() > 0){%>,<%}%> <%getJavaPropertiesMethodSearchSignature()%>) throws Exception {
		List<NodeRef> resultSet = request(operator<%if (getAllSearchableSortedAttibutes().nSize() > 0){%>,<%}%> <%getJavaPropertiesMethodSearchCall(null)%>);
		return convetResultSet(resultSet);
	}
	
	/**
	 * Request <%name.toU1Case()%>
	 * 
	 * @param operator
	 *            lucene query operator ([AND|OR])
<%for (getAllSearchableSortedAttibutes()){%>
	 * @param <%name%>
	 *            <%current("Clazz").name.toU1Case()%> attribute
<%}%>
	 * @return ResultSet
	 * @throws Exception
	 */
	public List<NodeRef> request(String operator<%if (getAllSearchableSortedAttibutes().nSize() > 0){%>,<%}%> <%getJavaPropertiesMethodSearchSignature()%>) throws Exception {
		String lucene ="+TYPE:\"" + ModelQNames.CONTENT_MODEL_TYPE_<%name.toUpperCase()%>.getQnameString()+"\"";
<%if (getAllSearchableSortedAttibutes().nSize() > 0){%>
		Map<String, List<String>> props = new HashMap<String, List<String>>();
	<%for (getAllSearchableSortedAttibutes()){%>
		if (<%name%> != null) {
		<%if (isMultivalued()){%>
			props.put(ModelQNames.CONTENT_MODEL_ATTRIBUTE_<%eContainer().name.toUpperCase()%>_<%name.toUpperCase()%>.getQnameString(), <%name%>);
		<%}else{%>
			ArrayList<String> arrayList = new ArrayList<String>();
			arrayList.add(<%name%>);
			props.put(ModelQNames.CONTENT_MODEL_ATTRIBUTE_<%eContainer().name.toUpperCase()%>_<%name.toUpperCase()%>.getQnameString(), arrayList);			
		<%}%>	
		}
	<%}%>
		String props_ = "";
		Iterator<Map.Entry<String, List<String>>> it = props.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, List<String>> dataType_ = it.next();
			for (String val : dataType_.getValue()) {
				props_ += " +@" + dataType_.getKey() + ":\"" + val + "\"";
			}
			if (it.hasNext()) {
				props_ +=" "+operator+" ";
			}
		}
		props_ = props_.replaceAll("([{}])", "\\\\$1");
		props_ = props_.replaceAll("(http):", "$1\\\\:");
		lucene+=" "+props_;
<%}%>	
		return requestLucene(lucene);
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
	 */
	public void createAssociation_<%eContainer().getAssociationQName(current("AssociationEnd"))%>(<%linkedClass.name.toU1Case()%> source, <%getOpposite().linkedClass.name.toU1Case()%> target) {
		createAssociation_<%eContainer().getAssociationQName(current("AssociationEnd"))%>(source.getUuid(), target.getUuid());
	}
	
	/**
	 * Method to create <%eContainer().getAssociationQName(current("AssociationEnd"))%> association
	 * 
	 * @param source
	 *            the first end of the association
	 * @param target
	 *            the second end of the association
	 * @return
	 */
	public void createAssociation_<%eContainer().getAssociationQName(current("AssociationEnd"))%>(String source, String target) {
		createSimpleAssociation(new NodeRef(source), QName.createQName(ModelQNames.CONTENT_MODEL_ASSOCIATION_<%eContainer().getAssociationQName(current("AssociationEnd")).toUpperCase()%>), new NodeRef(target));
	}
	
	/**
	 * Method to remove <%eContainer().name%> association
	 * 
	 * @param source
	 *            the first end of the association
	 * @param target
	 *            the second end of the association
	 * @return
	 */
	public void removeAssociation_<%eContainer().getAssociationQName(current("AssociationEnd"))%>(<%linkedClass.name.toU1Case()%> source, <%getOpposite().linkedClass.name.toU1Case()%> target) {
		removeAssociation_<%eContainer().getAssociationQName(current("AssociationEnd"))%>(source.getUuid(), target.getUuid());
	}
	
	/**
	 * Method to remove <%eContainer().name%> association
	 * 
	 * @param source
	 *            the first end of the association
	 * @param target
	 *            the second end of the association
	 * @return
	 */
	public void removeAssociation_<%eContainer().getAssociationQName(current("AssociationEnd"))%>(String source, String target) {
		nodeService.removeAssociation(new NodeRef(source), new NodeRef(target), QName.createQName(ModelQNames.CONTENT_MODEL_ASSOCIATION_<%eContainer().getAssociationQName(current("AssociationEnd")).toUpperCase()%>));
	}
	
	/**
	 * Method to get nodes associated by <%eContainer().name%> to a given <%current("Clazz").name.toU1Case()%>
	 * 
	 * @param source a <%linkedClass.name.toU1Case()%>
	 * @return
	 */
	public List<NodeRef> getAssociatedTarget_<%eContainer().getAssociationQName(current("AssociationEnd"))%>(<%linkedClass.name.toU1Case()%> source) {
		return getAssociatedTarget_<%eContainer().getAssociationQName(current("AssociationEnd"))%>(source.getUuid());
	}
	
	/**
	 * Method to get nodes associated by <%eContainer().name%> to a given <%current("Clazz").name.toU1Case()%>
	 * 
	 * @param source a <%linkedClass.name.toU1Case()%>
	 * @return
	 */
	public List<NodeRef> getAssociatedTarget_<%eContainer().getAssociationQName(current("AssociationEnd"))%>(String source) {
		return getAssociatedNode(new NodeRef(source), QName.createQName(ModelQNames.CONTENT_MODEL_ASSOCIATION_<%eContainer().getAssociationQName(current("AssociationEnd")).toUpperCase()%>));
	}
	
	/**
	 * Method to get nodes associated by hasParentpage to a given <%linkedClass.name.toU1Case()%>
	 * 
	 * @param source a <%linkedClass.name.toU1Case()%>
	 * @return List of <%getOpposite().linkedClass.name.toU1Case()%>
	 */
	public List<<%getOpposite().linkedClass.name.toU1Case()%>> getAssociatedTarget_<%eContainer().getAssociationQName(current("AssociationEnd"))%>As<%getOpposite().linkedClass.name.toU1Case()%>(<%linkedClass.name.toU1Case()%> source) {
		return getAssociatedTarget_<%eContainer().getAssociationQName(current("AssociationEnd"))%>As<%getOpposite().linkedClass.name.toU1Case()%>(source.getUuid()); 
	}
	
	/**
	 * Method to get nodes associated by hasParentpage to a given <%linkedClass.name.toU1Case()%>
	 * 
	 * @param source a <%linkedClass.name.toU1Case()%>
	 * @return List of <%getOpposite().linkedClass.name.toU1Case()%>
	 */
	public List<<%getOpposite().linkedClass.name.toU1Case()%>> getAssociatedTarget_<%eContainer().getAssociationQName(current("AssociationEnd"))%>As<%getOpposite().linkedClass.name.toU1Case()%>(String source) {
		List<NodeRef> qr = getAssociatedNode(new NodeRef(source), QName.createQName(ModelQNames.CONTENT_MODEL_ASSOCIATION_<%eContainer().getAssociationQName(current("AssociationEnd")).toUpperCase()%>));
		return get<%getProperty("java.classes.factoryRegistry")%>().get<%getOpposite().linkedClass.getJavaAPIName()%>().convetResultSet(qr); 
	}
	
<%}%>


	/**
	 * Method that convert a resultSet as List of <%name.toU1Case()%>
	 * 
	 * @param resultSet
	 *            the result set to convert
	 * @return List of <%name.toU1Case()%>
	 */
	protected List<<%name.toU1Case()%>> convetResultSet(List<NodeRef> resultSet) {
		List<<%name.toU1Case()%>> results<%name.toU1Case()%> = new ArrayList<<%name.toU1Case()%>>();
		for (NodeRef node : resultSet) {
			// create object
			<%name.toU1Case()%> obj = get<%name.toU1Case()%>(node);
			results<%name.toU1Case()%>.add(obj);
			
		}
		return results<%name.toU1Case()%>;
	}
	
<%filter("AbstractClass").commonMethods()%>
	
}