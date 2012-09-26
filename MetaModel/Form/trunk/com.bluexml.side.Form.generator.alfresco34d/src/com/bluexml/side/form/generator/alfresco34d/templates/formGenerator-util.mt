<%
metamodel http://www.kerblue.org/form/1.0
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.form.generator.alfresco34d.FormGenerator
import com.bluexml.side.form.generator.alfresco34d.templates.services.form
%>

<%-- getGenericFieldAsText :
	 args(0) : query string
	 args(1) : expected parameter name
	 returns the value of the expected parameter stored in a Generic Text
	 field encoded like a query
	 @deprecated - not used anymore because of the use of getGlobalDependencies
--%>
<%script type="FormCollection" name="getGenericFieldAsText" post="trim()" %>
<%for (args(0).split("&")){%>
<%if (toString().indexOf(args(1)) != -1) {%>
<%toString().substring(toString().indexOf("=") + 1, toString().length())%>
<%}%>
<%}%>

<%-- getGlobalDependencies :
	 args(0) : query string
	 args(1) : expected parameter name
	 returns the value of the expected parameter stored in a Generic Text
	 field encoded like a query
--%>
<%script type="FormCollection" name="getGlobalDependencies" post="trim()" %>
<%for (args(0).split("&")){%>
<%if (toString().indexOf(args(1)) != -1) {%>
<<%args(1)%> src="<%toString().substring(toString().indexOf("=") + 1, toString().length())%>" />
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
<%for (metainfo){%>
<%if (key.toLowerCase() == args(0).toLowerCase()){%>
		<%args(0)%>="<%if (value != null && value != ""){%><%value%><%}%>"
<%}%>
<%}%>

<%script type="FormElement" name="haveXtension" post="trim()" %>
<%metainfo[key.toLowerCase() == args(0).toLowerCase()].nSize() > 0 || Xtension[toString().toLowerCase().startsWith(args(0).toLowerCase())].nSize() > 0%>

<%script type="FormElement" name="getXtensionValue" post="trim()" %>
<%if (haveXtension(args(0))){%>
<%metainfo[key.toLowerCase() == args(0).toLowerCase()].value%>
<%metainfo[key.toLowerCase() == args(0).toLowerCase()].multilineValue%>
<%Xtension[toString().toLowerCase().startsWith(args(0).toLowerCase())]%>
<%}%>

<%-- getXtensionAsControlParam :
	 args(0) : expected parameter name
	 returns the value of the expected parameter stored in Xtension and create control-param element
--%>
<%script type="FormElement" name="getXtensionAsControlParam" post="trim()" %>
<%for (Xtension) {%>
<%--xtension : <%current()%> -->
<!--arg      :<%args(0).toLowerCase()%> -->
<!-- test : <%toString().toLowerCase().startsWith(args(0).toLowerCase())%> --%>
	<%if (toLowerCase().startsWith(args(0).toLowerCase())){%>
		<control-param name="<%args(0)%>"><%toString().substring(toString().indexOf("=") + 1, toString().length())%></control-param>
	<%}%>
<%}%>
<%for (metainfo){%>
<%if (key.toLowerCase() == args(0).toLowerCase()){%>
<control-param name="<%args(0)%>"><![CDATA[<%if (value != null && value != ""){%><%value%><%}else if (multilineValue != null && multilineValue != ""){%><%multilineValue%><%}%>]]></control-param>
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
	<%if (getXtensionValue("pseudo-field").nSize() > 0){%>
<%getXtensionValue("pseudo-field")%>
	<%}else{%>
<%ref.getPrefixedQName()%>
	<%}%>
<%}%>
<%for (ref.filter("clazz.Association")){%>
	<%getPrefixedAssociationQName(getOppositeAssociationEnd(current("ClassReference").real_class))%>
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