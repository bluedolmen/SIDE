<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import templates.services.lib
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices

%>
<%script type="clazz.ClassPackage" name="validatedFilename"%>
<%if (eContainer() == null) {%><%getProperty("javaWebServicesAPIPath")%>/<%getProperty("javaSource")%>/com/bluexml/side/alfresco/crud/<%service::getRootContainer().name%>/<%eContainer().getQualifiedName().replaceAll("_","/")%>/Test.java<%}%>
<%script type="clazz.ClassPackage" name="alfrescoGenerator" file="<%validatedFilename%>"%>
package com.bluexml.side.alfresco.crud.<%name%>;

import java.io.IOException;
import java.io.Writer;
import java.rmi.RemoteException;
import java.util.List;


import org.alfresco.webservice.authentication.AuthenticationFault;
import org.alfresco.webservice.content.Content;
import org.alfresco.webservice.repository.RepositoryFault;
import org.alfresco.webservice.types.ContentFormat;
import org.alfresco.webservice.types.Version;
import org.alfresco.webservice.util.AuthenticationUtils;
import org.alfresco.webservice.util.Constants;
import org.alfresco.webservice.util.ContentUtils;

<%for (getAllClasses()) {%>
import com.bluexml.side.alfresco.crud.<%service::getRootContainer().name%>.<%eContainer().getQualifiedName().replaceAll("_","\\.")%>.<%name.toU1Case()%>API;
import com.bluexml.side.alfresco.model.<%service::getRootContainer().name%>.<%eContainer().getQualifiedName().replaceAll("_","\\.")%>.<%name.toU1Case()%>;
<%}%>

public class Test {

	protected static final String USERNAME = "admin";
	protected static final String PASSWORD = "admin";

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void launchTest(Writer w) throws IOException {
		w.write("=== Start Test ==="+"<br/>");
		boolean ok = true;
		try {
			AuthenticationUtils.startSession(USERNAME, PASSWORD);
<%for (getAllClasses()) {%>
			<%name.toU1Case()%>API <%name.toLowerCase()%>API = new <%name.toU1Case()%>API();
			
			w.write("* Create Nodes"+"<br/>");
			
			<%name.toU1Case()%> obj_<%name%>1 = <%name.toLowerCase()%>API.create(null<%for (getAllSortedAttibutes()){%>, "<%if (valueList){%><%valueList.literals.nGet(0).name%><%}else{%><%name%><%}%>"<%}%>);
			w.write("** created :"+obj_<%name%>1+"<br/>");
			<%name.toU1Case()%> obj_<%name%>2 = <%name.toLowerCase()%>API.create(null<%for (getAllSortedAttibutes()){%>, "<%if (valueList){%><%valueList.literals.nGet(0).name%><%}else{%><%name%><%}%>"<%}%>);
			w.write("** created :"+obj_<%name%>2+"<br/>");
			
			w.write("* Delete Nodes"+"<br/>");
			w.write("UUID to delete " + obj_<%name%>1.getUuid()+"<br/>");
			<%name.toLowerCase()%>API.delete(obj_<%name%>1.getUuid());
			
			w.write("* Add Versionable Aspect"+"<br/>");
			<%name.toLowerCase()%>API.addAspectTo(Constants.ASPECT_VERSIONABLE, null, obj_<%name%>2.getUuid());
<%}%>


<%for (getAllClasses()) {%>
	<%for (getAllSourceAssociationEnds()){%>
			w.write("* Create association :<br/>" + "(" + obj_<%current("Clazz").name%>2 + ",<br/>" + obj_<%getOpposite().linkedClass.name%>2 + ")"+"<br/>");
			<%current("Clazz").name.toLowerCase()%>API.createAssociation_<%eContainer().getAssociationQName(current("AssociationEnd"))%>(obj_<%current("Clazz").name%>2, obj_<%getOpposite().linkedClass.name%>2);
	<%}%>
	<%for (getAllSourceAssociationEnds()){%>
			w.write("* Get associated <%getOpposite().linkedClass.name.toU1Case()%> :" + " (<%eContainer().name%>)"+"<br/>");
			List<<%getOpposite().linkedClass.name.toU1Case()%>> associated_<%eContainer().getAssociationQName(current("AssociationEnd"))%>As<%getOpposite().linkedClass.name.toU1Case()%> = <%current("Clazz").name.toLowerCase()%>API.getAssociatedTarget_<%eContainer().getAssociationQName(current("AssociationEnd"))%>As<%getOpposite().linkedClass.name.toU1Case()%>(obj_<%current("Clazz").name%>2);
			for (<%getOpposite().linkedClass.name.toU1Case()%> <%eContainer().getAssociationQName(current("AssociationEnd"))%>As<%getOpposite().linkedClass.name.toLowerCase()%> : associated_<%eContainer().getAssociationQName(current("AssociationEnd"))%>As<%getOpposite().linkedClass.name.toU1Case()%>) {
				w.write("** "+<%eContainer().getAssociationQName(current("AssociationEnd"))%>As<%getOpposite().linkedClass.name.toLowerCase()%>+"<br/>");
			}
	<%}%>
	<%for (getAllSourceAssociationEnds()){%>
			w.write("Remove association :<br/>" + "(" + obj_<%current("Clazz").name%>2 + ",<br/>" + obj_<%getOpposite().linkedClass.name%>2 + ")"+"<br/>");
			<%current("Clazz").name.toLowerCase()%>API.removeAssociation_<%eContainer().getAssociationQName(current("AssociationEnd"))%>(obj_<%current("Clazz").name%>2, obj_<%getOpposite().linkedClass.name%>2);
	<%}%>
<%}%>

			w.write("* Update Nodes"+"<br/>");
			<%for (getAllClasses()) {%>
			<%name.toLowerCase()%>API.update(obj_<%current("Clazz").name%>2.getUuid()<%for (getAllSortedAttibutes()){%>, "<%if (valueList){%><%valueList.literals.nGet(0).name%><%}else{%><%name%>Updated<%}%>"<%}%>);
			<%}%>
			<%for (getAllClasses()) {%>
			w.write("* Set Content Nodes"+"<br/>");
			<%name.toLowerCase()%>API.setContent(obj_<%current("Clazz").name%>2.getUuid(), "new content".getBytes(), new ContentFormat("text/plain","UTF-8"));
			<%}%>

			// search all Content Types
<%for (getAllClasses()) {%>
			w.write("* Request <%name.toU1Case()%>"+"<br/>");
			List<<%name.toU1Case()%>> result_<%name%>2 = <%name.toLowerCase()%>API.request<%name.toU1Case()%>("AND"<%for (getAllSortedAttibutes()){%>, "<%if (valueList){%><%valueList.literals.nGet(0).name%><%}else{%><%name%>Updated<%}%>"<%}%>);
			for (<%name.toU1Case()%> <%name.toLowerCase()%> : result_<%name%>2) {
				w.write("** " + <%name.toLowerCase()%> + "<br/>");
				w.write("<p>Test Versions/content Access :</p>");
				Version[] vs = <%name.toLowerCase()%>API.getVersions(<%name.toLowerCase()%>.getUuid());
				for (Version version : vs) {
					w.write("<p>Version :");
					w.write("<ul>");
					w.write("<li>");
					w.write("Label :"+version.getLabel());			
					w.write("</li>");
					w.write("<li>");
					w.write("PATH :"+version.getId().getPath());			
					w.write("</li>");
					w.write("<li>");
					w.write("STORE :"+version.getId().getStore().getScheme()+" "+version.getId().getStore().getAddress());			
					w.write("</li>");
					w.write("<li>");
					w.write("UUID :"+version.getId().getUuid());			
					w.write("</li>");			
					w.write("</p></ul>");
				}
				w.write("<p>Access to 1.1 :</p>");
				<%name.toU1Case()%> wc = <%name.toLowerCase()%>API.get<%name.toU1Case()%>Version(<%name.toLowerCase()%>.getUuid(), "1.1");
				w.write(wc.toString());
				w.write("<p>"+wc.toString()+"</p>");
				Content content = <%name.toLowerCase()%>API.getContent(<%name.toLowerCase()%>.getUuid(), "1.1");
				w.write("<p>"+ContentUtils.getContentAsString(content)+"</p>");
			}
<%}%>





		} catch (AuthenticationFault e) {
			e.printStackTrace();
			ok = false;
		} catch (RepositoryFault e) {
			e.printStackTrace();
			ok = false;
		} catch (RemoteException e) {
			e.printStackTrace();
			ok = false;
		}
		if (ok) {
			w.write("=== TEST succeed ==="+"<br/>");
		} else {
			w.write("=== TEST fail (look at stantard output)==="+"<br/>");
		}
		
	}

}