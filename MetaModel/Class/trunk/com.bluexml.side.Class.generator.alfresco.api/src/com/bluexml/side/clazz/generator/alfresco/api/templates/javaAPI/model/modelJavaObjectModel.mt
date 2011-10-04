<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import com.bluexml.side.clazz.generator.alfresco.api.templates.services.classes
import com.bluexml.side.clazz.generator.alfresco.api.templates.javaAPI.lib
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
import com.bluexml.side.clazz.generator.alfresco.api.service.ValueGenerator
%>
<%script type="clazz.AbstractClass" name="validatedFilename"%>
<%getProperty("javaModelAPIPath")%>/<%getProperty("javaSource")%>/com/bluexml/side/alfresco/model/<%service::getRootContainer().name%>/<%eContainer().getQualifiedName().replaceAll("_","/")%>/<%name.toU1Case()%>.java
<%script type="clazz.AbstractClass" name="alfrescoGenerator" file="<%validatedFilename%>"%>
package <%getJavaModelObjectPackage()%>;
<%if (current().filter("Clazz")){%>
<%if (current().filter("Clazz").getSourceAssociationEnds().nSize() > 0){%>
import java.util.ArrayList;
<%}%>
<%if (current().filter("Clazz").getSourceAssociationEnds().nSize() > 0 || getAllAbstractClassSortedAttibutes().nSize() > 0){%>
import java.util.List;
<%}%>
<%}else{%>
<%if (getAllAbstractClassSortedAttibutes().nSize() > 0){%>
import java.util.List;
<%}%>
<%}%>
import com.bluexml.side.alfresco.model.<%getRootContainer().name.toLowerCase()%>.*;

public class <%name.toU1Case()%> <%if (filter("Clazz").generalizations > 0 && !filter("Clazz").generalizations.nGet(0).isNativeModel()){%>extends <%filter("Clazz").generalizations.nGet(0).name.toU1Case()%> <%}%>{
<%for (getAllAbstractClassSortedAttibutes()){%>
	<%getSignature()%> <%name%> = null;
<%}%>
<%if (filter("Clazz").generalizations.nGet(0).isNativeModel()){%>
<%for (filter("Clazz").getAllInheritedClassAndAspectAttributes()){%>
	<%getSignature()%> <%name%> = null;
<%}%>
<%}%>

<%if (current().filter("Clazz")){%>
<%for (current().filter("Clazz").getSourceAssociationEnds()){%>
	List<String> <%eContainer().getQualifiedName(current())%> = new ArrayList<String>();
<%}%>
<%}%>
<%if (filter("Clazz").generalizations.nSize() == 0 || filter("Clazz").generalizations.nGet(0).isNativeModel()){%>
<%if (mustAddcmContent() == "true"){%>
	String content = null;
<%}%>
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
<%if (mustAddcmContent() == "true"){%>
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
<%}%>
<%}%>

<%for (getAllAbstractClassSortedAttibutes()){%>
	/**
	 * @return the <%name%>
	 */
	public <%getSignature()%> get<%name.toU1Case()%>() {
		return <%name%>;
	}
	
	/**
	 * @param <%name%>
	 *            the <%name%> to set
	 */
	public void set<%name.toU1Case()%>(<%getSignature()%> <%name%>) {
		this.<%name%> = <%name%>;
	}
<%}%>
<%--if (filter("Clazz").generalizations.nGet(0).isNativeModel()){--%>
<%for (filter("Clazz").getAllInheritedClassAndAspectAttributes()){%>
	/**
	 * @return the <%name%>
	 */
	public <%getSignature()%> get<%name.toU1Case()%>() {
		return <%name%>;
	}
	
	/**
	 * @param <%name%>
	 *            the <%name%> to set
	 */
	public void set<%name.toU1Case()%>(<%getSignature()%> <%name%>) {
		this.<%name%> = <%name%>;
	}
<%}%>
<%--}--%>

<%if (current().filter("Clazz")){%>
<%for (current().filter("Clazz").getSourceAssociationEnds()){%>
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
<%}%>
	public <%name.toU1Case()%>() {

	}
	
	public <%name.toU1Case()%>(String uuid, <%if (getAllSortedAttibutes().nSize() >0){%><%for (getAllSortedAttibutes()){%><%getSignature()%> <%name%><%if (i() < current("AbstractClass").getAllSortedAttibutes().nSize() -1){%>, <%}%><%}%><%if (mustAddcmContent()  == "true"){%>,<%}%><%}%><%if (mustAddcmContent() == "true"){%> String content<%}%>) {
		<%if (filter("Clazz").generalizations > 0 && !filter("Clazz").generalizations.nGet(0).isNativeModel()){%>
		super(uuid<%if (filter("Clazz").generalizations.nGet(0).getAllAbstractClassSortedAttibutes().nSize() >0){%>,<%}%> <%filter("Clazz").generalizations.nGet(0).getJavaPropertiesMethodCall("")%><%if (mustAddcmContent()  == "true"){%>, content<%}%>);
		<%}else{%>
		super();
		
		<%}%>
		<%for (getAllAbstractClassSortedAttibutes()){%>
		this.<%name%> = <%name%>;
		<%}%>
		<%if (filter("Clazz").generalizations.nGet(0).isNativeModel()){%>
		<%for (filter("Clazz").getAllInheritedClassAndAspectAttributes()){%>
		this.<%name%> = <%name%>;
		<%}%>
		
		<%}%>
		this.uuid = uuid;
		<%if (mustAddcmContent()  == "true"){%>
		this.content = content;
		<%}%>
		
		
	}

	public String toString() {
		String json = "{";
		json += ModelQNames.addquote("<%name.toU1Case()%>");
		json += ":{";
		json += ModelQNames.addquote("uuid") + ":" + ModelQNames.addquote(this.uuid);
		<%for (getAllSortedAttibutes){%>
		<%if (isMultivalued()){%>
		json +=", " + ModelQNames.addquote("<%name%>") + ":" + ModelQNames.serializeList(this.<%name%>);
		<%}else{%>
		json +=", " + ModelQNames.addquote("<%name%>") + ":" + ModelQNames.addquote(this.<%name%>);
		<%}%>
		<%}%>
		<%if (mustAddcmContent() == "true"){%>
		if (this.content != null) {
			json+=", " + ModelQNames.addquote("content") + ":" + ModelQNames.addquote(this.content.length());	
		} else {
			json+=", " + ModelQNames.addquote("content") + ":" + ModelQNames.addquote("null");
		}
		<%}%>
		json += "}}";
		
		
		return json;
	}
	
	
}
