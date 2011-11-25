<%
metamodel http://www.kerblue.org/form/1.0
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.form.generator.alfresco34d.FormGenerator
import com.bluexml.side.form.generator.alfresco34d.templates.services.form
%>

<%-- getGenericAsValue :
	 args(0) : query string
	 args(1) : expected parameter name
	 returns the value of the expected parameter stored in a Generic Text
	 field encoded like a query
--%>
<%script type="FormCollection" name="getGenericFieldAsText" post="trim()" %>
<%for (args(0).split("&")){%>
<%if (toString().indexOf(args(1)) != -1) {%>
<%toString().substring(toString().indexOf("=") + 1, toString().length())%>
<%}%>
<%}%>

<%-- getXtensionAsXMLAttribute :
	 args(0) : expected parameter name
	 returns the value of the expected parameter stored in Xtension
--%>
<%script type="FormElement" name="getBehaviour" post="trim()" %>
<%getXtensionAsControlParam("preRule")%>
<%getXtensionAsControlParam("postRule")%>

<%-- getXtensionAsXMLAttribute :
	 args(0) : expected parameter name
	 returns the value of the expected parameter stored in Xtension
--%>
<%script type="FormElement" name="getXtensionAsXMLAttribute" post="trim()" %>
<%for (Xtension) {%>
	<%if (toLowerCase().startsWith(args(0).toLowerCase())){%>
		<%args(0)%>="<%toString().substring(toString().indexOf("=") + 1, toString().length())%>"
	<%}%>
<%}%>
<%-- getXtensionAsControlParam :
	 args(0) : expected parameter name
	 returns the value of the expected parameter stored in Xtension and create control-param element
--%>
<%script type="FormElement" name="getXtensionAsControlParam" post="trim()" %>
<%for (Xtension) {%>
	<%if (toLowerCase().startsWith(args(0).toLowerCase())){%>
		<control-param name="<%args(0)%>"><%toString().substring(toString().indexOf("=") + 1, toString().length())%></control-param>
	<%}%>
<%}%>

<%-- Why doesn't this work? --%>
<%--
<%script type="FormElement" name="getKey" post="trim()" %>
<%args(0).substring(0, args(0).indexOf("="))%>

<%script type="FormElement" name="getValue" post="trim()" %>
<%args(0).substring(args(0).indexOf("="), toString().length()-1)%>
--%>

<%script type="FormElement" name="getSetId" post="trim()" %>
<%if (eContainer().filter("FormContainer")){%><%args(0)%><%}else{%><%current("FormGroup").getPrefixedQualifiedName()%><%}%>
<%script type="FormElement" name="getFieldLabelId" post="trim()" %>
form.field.label.<%args(0)%><%getPrefixedQualifiedName()%>
<%script type="FormGroup" name="getGroupLabelId" post="trim()" %>
form.set.label.<%getPrefixedQualifiedName()%>
<%script type="FormElement" name="getFieldId" post="trim()" %>
<%for (ref.filter("workflow.Attribute")){%>
<%service::getRootContainer().filter("workflow.Process").name%>:<%name%>
<%}%>
<%if (ref.filter("clazz.Attribute")){%>
	<%ref.getPrefixedQName()%>
<%}%>
<%for (ref.filter("clazz.Association")){%>
	<%if (firstEnd.linkedClass == current("Clazz")){%>
		<%getPrefixedAssociationQName(secondEnd)%>
	<%}else{%>
		<%getPrefixedAssociationQName(firstEnd)%>
	<%}%>
<%}%>
<%script type="FormElement" name="isSearchForm"%>
<%service::getRootContainer().filter("SearchFormCollection").nSize() > 0%>
<%--
<%script type="FormContainer" name="getFormId" post="trim()" %>
<%if (isSearchForm){%>
search
<%}else{%>
form.<%current("ClassFormCollection").name%>
<%}%>
--%>