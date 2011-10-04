<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices

%>
<%script type="clazz.ClassPackage" name="validatedFilename"%>
<%if (eContainer() == null) {%><%getProperty("javaEmbeddedAPIPath")%>/<%getProperty("javaSource")%>/com/bluexml/side/alfresco/crud/AbstractFactoryAdaptator.java<%}%>
<%script type="clazz.ClassPackage" name="alfrescoGenerator" file="<%validatedFilename%>"%>
package <%getProperty("javaPackageAPI")%>;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import org.alfresco.repo.jscript.BaseScopableProcessorExtension;
import org.alfresco.repo.template.TemplateNode;
import org.alfresco.repo.web.scripts.RepositoryImageResolver;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.NodeRef;
import org.mozilla.javascript.NativeArray;

public class AbstractFactoryAdaptator extends BaseScopableProcessorExtension {
	protected RepositoryImageResolver imageResolver;
	protected ServiceRegistry serviceRegistry;
	public void setImageResolver(RepositoryImageResolver imageResolver) {
		this.imageResolver = imageResolver;
	}

	public RepositoryImageResolver getImageResolver() {
		return imageResolver;
	}
		
	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}

	public ServiceRegistry getServiceRegistry() {
		return serviceRegistry;
	}
	
	protected TemplateNode getTemplateNode(NodeRef nodeRef) {
		return new TemplateNode(nodeRef, serviceRegistry, imageResolver.getImageResolver());
	}
	
	protected List<TemplateNode> convertToTemplateNode(List<NodeRef> res) {
		List<TemplateNode> ltn = new ArrayList<TemplateNode>();
		for (NodeRef nodeRef : res) {
			ltn.add(getTemplateNode(nodeRef));
		}
		return ltn;
	}
	
	protected List<String> convertToList(NativeArray l) {
		List<String> lt = new ArrayList<String>();
		for (Object o : l.getIds()) {
		    int index = (Integer) o;
		    lt.add((String)l.get(index, null));
		}
		return lt;
	}
	
	public List<String> convertToNodeRefString(List<TemplateNode> res) {
		List<String> ltn = new ArrayList<String>();
		for (TemplateNode nodeRef : res) {
			ltn.add(nodeRef.getNodeRef().toString());
		}
		return ltn;		
	}
	
	public String convertToJson(Object o) {
		JSON jsonO = JSONSerializer.toJSON(o);
		return jsonO.toString();		
	}
	
	public String convertNodesToJson(List<TemplateNode> res) {
		return convertToJson(convertToNodeRefString(res));
	}
}