<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import com.bluexml.side.clazz.generator.alfresco.api.templates.javaAPI.embedded.javaAPI
import com.bluexml.side.clazz.generator.alfresco.api.templates.javaAPI.lib
import com.bluexml.side.clazz.generator.alfresco.api.templates.services.classes
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices

%>
<%script type="clazz.Aspect" name="validatedFilename"%>
<%getProperty("javaEmbeddedAPIPath")%>/<%getProperty("javaSource")%>/com/bluexml/side/alfresco/crud/<%service::getRootContainer().name%>/<%eContainer().getQualifiedName().replaceAll("_","/")%>/script/<%javaWebScriptFactoryAdapterName()%>.java
<%script type="clazz.Aspect" name="alfrescoGenerator" file="<%validatedFilename%>"%>
package <%getProperty("javaPackageAPI")%>.<%service::getRootContainer().name%>.<%eContainer().getQualifiedName().replaceAll("_","\\.")%>.script;

import java.util.List;
import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.alfresco.service.cmr.repository.NodeRef;
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
	 * add Coordonnees <%getJavaModelObjectName()%> to node
	 <%for (getAllSortedAttibutes()){%>
	 * @param <%name%>
	 <%}%>	
	 * @throws RepositoryFault
	 * @throws RemoteException
	 */
	public <%getJavaModelObjectName()%> addAspectTo(String uuid<%if (getAllSortedAttibutes().nSize() > 0){%>, <%}%><%getJavaPropertiesMethodSignatureWebscript()%>) {
		return <%getJavaAPIName().toL1Case()%>.addAspectTo(new NodeRef(uuid)<%if (getAllSortedAttibutes().nSize() > 0){%>, <%}%><%getJavaPropertiesMethodCallWebscript("")%>);
	}
	
		
	/**
	 * 
	 * @param uuid
	 * @throws Exception
	 */
	public void removeAspect(String uuid) throws Exception {
		<%getJavaAPIName().toL1Case()%>.removeAspect(new NodeRef(uuid));
	}
	
	public boolean is<%getJavaModelObjectName()%>(String uuid) throws Exception {
		return <%getJavaAPIName().toL1Case()%>.is<%getJavaModelObjectName()%>(new NodeRef(uuid));
	}
	
<%commonWebScriptMethods()%>
}
