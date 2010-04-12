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
<%getProperty("javaModelAPIPath")%>/<%getProperty("javaSource")%>/com/bluexml/side/alfresco/model/<%service::getRootContainer().name%>/<%eContainer().getQualifiedName().replaceAll("_","/")%>/<%name.toU1Case()%>.java
<%script type="clazz.Clazz" name="alfrescoGenerator" file="<%validatedFilename%>"%>
package com.bluexml.side.alfresco.model.<%service::getRootContainer().name%>.<%eContainer().getQualifiedName().replaceAll("_","\\.")%>;

import java.util.ArrayList;
import java.util.List;

public class <%name.toU1Case()%> {
<%for (getAllSortedAttibutes()){%>
	String <%name%> = null;
<%}%>

<%for (getAllSourceAssociationEnds()){%>
	List<String> <%eContainer().getQualifiedName(current())%> = new ArrayList<String>();
<%}%>

	String content = null;
	String uuid = null;

	/**
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * @param uuid
	 *            the uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
<%for (getAllSortedAttibutes()){%>
	/**
	 * @return the <%name%>
	 */
	public String get<%name.toU1Case()%>() {
		return <%name%>;
	}
	
	/**
	 * @param <%name%>
	 *            the <%name%> to set
	 */
	public void set<%name.toU1Case()%>(String <%name%>) {
		this.<%name%> = <%name%>;
	}
<%}%>	
	
	
<%for (getAllSourceAssociationEnds()){%>
	/**
	 * @return the <%eContainer().getQualifiedName(current())%>
	 */
	public List<String> get<%eContainer().getQualifiedName(current()).toU1Case()%>() {
		return <%eContainer().getQualifiedName(current())%>;
	}
	
	/**
	 * @param <%eContainer().getQualifiedName(current())%>
	 *            the <%eContainer().getQualifiedName(current())%> to set
	 */
	public void set<%eContainer().getQualifiedName(current()).toU1Case()%>(List<String> <%eContainer().getQualifiedName(current())%>) {
		this.<%eContainer().getQualifiedName(current())%> = <%eContainer().getQualifiedName(current())%>;
	}
<%}%>
	
	public <%name.toU1Case()%>() {

	}
	
	public <%name.toU1Case()%>(String uuid, <%if (getAllSortedAttibutes().nSize() >0){%><%for (getAllSortedAttibutes()){%>String <%name%><%if (i() < current("Clazz").getAllSortedAttibutes().nSize() -1){%>, <%}%><%}%>,<%}%>String content) {
		super();
		<%for (getAllSortedAttibutes()){%>
		this.<%name%> = <%name%>;
		<%}%>
		this.uuid = uuid;
		this.content = content;
	}

	public String toString() {
		String st = "<%name.toU1Case()%> (" +
		"uuid :" + this.uuid;
		<%for (getAllSortedAttibutes()){%>
		st += ", <%name%> :" + this.<%name%>;		
		<%}%>
		if (this.content != null) {
			st+=", content length :" + this.content.length() + ")";	
		} else {
			st+=", content :" + null + ")";
		}
		return st;
	}
}
