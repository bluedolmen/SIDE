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
<%getProperty("javaEmbeddedAPIPath")%>/<%getProperty("javaSource")%>/com/bluexml/side/alfresco/crud/<%service::getRootContainer().name%>/<%eContainer().getQualifiedName().replaceAll("_","/")%>/script/<%javaWebScriptFactoryAdapterName()%>.java
<%script type="clazz.Clazz" name="alfrescoGenerator" file="<%validatedFilename%>"%>
package <%getProperty("javaPackageAPI")%>.<%service::getRootContainer().name%>.<%eContainer().getQualifiedName().replaceAll("_","\\.")%>.script;

import java.util.List;
import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.alfresco.repo.template.TemplateNode;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.InvalidQNameException;
<%if (getAllSortedAttibutes().filter("Attribute").isMultivalued().nContains("true")){%>
import org.mozilla.javascript.NativeArray;
<%}%>
import <%getProperty("javaPackageAPI")%>.AbstractFactoryAdaptator;
import <%getProperty("javaPackageAPI")%>.<%service::getRootContainer().name%>.<%eContainer().getQualifiedName().replaceAll("_","\\.")%>.*;
import <%getProperty("javaPackageModel")%>.<%service::getRootContainer().name%>.<%eContainer().getQualifiedName().replaceAll("_","\\.")%>.*;

public class <%javaWebScriptFactoryAdapterName()%> extends AbstractFactoryAdaptator {
	
	<%getJavaAPIName()%> <%getJavaAPIName().toL1Case()%>;

	public <%getJavaAPIName()%> get<%getJavaAPIName()%>() {
		return <%getJavaAPIName().toL1Case()%>;
	}

	public void set<%getJavaAPIName()%>(<%getJavaAPIName()%> <%getJavaAPIName().toL1Case()%>) {
		this.<%getJavaAPIName().toL1Case()%> = <%getJavaAPIName().toL1Case()%>;
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
	public <%name.toU1Case()%> create(String parent<%if (getAllSortedAttibutes().nSize() > 0){%>, <%}%><%getJavaPropertiesMethodSignatureWebscript()%>) throws InvalidQNameException, Exception {
		return <%getJavaAPIName().toL1Case()%>.create(parent<%if (getAllSortedAttibutes().nSize() > 0){%>, <%}%><%getJavaPropertiesMethodCallWebscript("")%>);
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
	public List<<%name.toU1Case()%>> request<%name.toU1Case()%>(String operator<%if (getAllSearchableSortedAttibutes().nSize() > 0){%>,<%}%> <%getJavaPropertiesMethodSearchSignatureWebscript()%>) throws Exception {
		return <%getJavaAPIName().toL1Case()%>.request<%name.toU1Case()%>(operator<%if (getAllSearchableSortedAttibutes().nSize() > 0){%>,<%}%> <%getJavaPropertiesMethodSearchCallWebscript("")%>);
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
	public List<TemplateNode> request(String operator<%if (getAllSearchableSortedAttibutes().nSize() > 0){%>,<%}%> <%getJavaPropertiesMethodSearchSignatureWebscript()%>) throws Exception {
		List<NodeRef> res = <%getJavaAPIName().toL1Case()%>.request(operator<%if (getAllSearchableSortedAttibutes().nSize() > 0){%>,<%}%> <%getJavaPropertiesMethodSearchCallWebscript("")%>);
		List<TemplateNode> ltn = convertToTemplateNode(res);
		return ltn;
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
		<%current("Clazz").getJavaAPIName().toL1Case()%>.createAssociation_<%eContainer().getAssociationQName(current("AssociationEnd"))%>(source, target);
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
		<%current("Clazz").getJavaAPIName().toL1Case()%>.createAssociation_<%eContainer().getAssociationQName(current("AssociationEnd"))%>(source, target);
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
		<%current("Clazz").getJavaAPIName().toL1Case()%>.removeAssociation_<%eContainer().getAssociationQName(current("AssociationEnd"))%>(source, target);
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
		<%current("Clazz").getJavaAPIName().toL1Case()%>.removeAssociation_<%eContainer().getAssociationQName(current("AssociationEnd"))%>(source, target);
	}
	
	/**
	 * Method to get nodes associated by <%eContainer().name%> to a given <%current("Clazz").name.toU1Case()%>
	 * 
	 * @param source a <%linkedClass.name.toU1Case()%>
	 * @return
	 */
	public List<TemplateNode> getAssociatedTarget_<%eContainer().getAssociationQName(current("AssociationEnd"))%>(<%linkedClass.name.toU1Case()%> source) {
		List<NodeRef> res = <%current("Clazz").getJavaAPIName().toL1Case()%>.getAssociatedTarget_<%eContainer().getAssociationQName(current("AssociationEnd"))%>(source);
		List<TemplateNode> ltn = convertToTemplateNode(res);
		return ltn;
	}
	
	/**
	 * Method to get nodes associated by <%eContainer().name%> to a given <%current("Clazz").name.toU1Case()%>
	 * 
	 * @param source a <%linkedClass.name.toU1Case()%>
	 * @return
	 */
	public List<TemplateNode> getAssociatedTarget_<%eContainer().getAssociationQName(current("AssociationEnd"))%>(String source) {
		List<NodeRef> res = <%current("Clazz").getJavaAPIName().toL1Case()%>.getAssociatedTarget_<%eContainer().getAssociationQName(current("AssociationEnd"))%>(source);
		List<TemplateNode> ltn = convertToTemplateNode(res);
		return ltn;
	}
	
	/**
	 * Method to get nodes associated by hasParentpage to a given <%linkedClass.name.toU1Case()%>
	 * 
	 * @param source a <%linkedClass.name.toU1Case()%>
	 * @return List of <%getOpposite().linkedClass.name.toU1Case()%>
	 */
	public List<<%getOpposite().linkedClass.name.toU1Case()%>> getAssociatedTarget_<%eContainer().getAssociationQName(current("AssociationEnd"))%>As<%getOpposite().linkedClass.name.toU1Case()%>(<%linkedClass.name.toU1Case()%> source) {
		return <%current("Clazz").getJavaAPIName().toL1Case()%>.getAssociatedTarget_<%eContainer().getAssociationQName(current("AssociationEnd"))%>As<%getOpposite().linkedClass.name.toU1Case()%>(source);
	}
	
	/**
	 * Method to get nodes associated by hasParentpage to a given <%linkedClass.name.toU1Case()%>
	 * 
	 * @param source a <%linkedClass.name.toU1Case()%>
	 * @return List of <%getOpposite().linkedClass.name.toU1Case()%>
	 */
	public List<<%getOpposite().linkedClass.name.toU1Case()%>> getAssociatedTarget_<%eContainer().getAssociationQName(current("AssociationEnd"))%>As<%getOpposite().linkedClass.name.toU1Case()%>(String source) {
		return <%current("Clazz").getJavaAPIName().toL1Case()%>.getAssociatedTarget_<%eContainer().getAssociationQName(current("AssociationEnd"))%>As<%getOpposite().linkedClass.name.toU1Case()%>(source);
	}
	
<%}%>

	
<%filter("AbstractClass").commonWebScriptMethods()%>
	
	public void delete<%getJavaModelObjectName()%>(String objectId) {
		<%getJavaAPIName().toL1Case()%>.delete(objectId);
	}
	
}