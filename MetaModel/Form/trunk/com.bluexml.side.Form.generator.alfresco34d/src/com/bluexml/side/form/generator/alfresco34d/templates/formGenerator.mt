<%
metamodel http://www.kerblue.org/form/1.0
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.form.generator.alfresco34d.FormGenerator
import com.bluexml.side.form.generator.alfresco34d.templates.services.form
import com.bluexml.side.form.generator.alfresco34d.templates.formGenerator-workflow
%>
  
<%script type="form.FormCollection" name="fileName"%>
<%if (eContainer() == null) {%><%getProperty("alf.share.paths.web-ext")%>/<%getModuleIdService(getRootPackage().name)%>/share-forms-config.xml<%}%>

<%script type="form.FormCollection" name="generate" file="<%fileName()%>" %>
<alfresco-config>
	<!-- Forms from SIDE <%current("FormCollection").name%> model -->
	
	<!-- Input Forms -->
	<%generateInputForms%>
	
	<!-- Search Forms -->
	<%generateSearchForms()%>
	
	<!-- Form Workflows -->
	<%generateWorkflowForms()%>
</alfresco-config>

<%script type="form.FormCollection" name="generateInputForms"%>
<%for (forms.filter("FormClass")){%>
	<!-- SIDE <%id%> Form -->
	<!-- <%real_class.getPrefixedQName()%> CREATE -->
	<config evaluator="model-type" condition="<%real_class.getPrefixedQName()%>">
		<%generate_fromClass()%>
	</config>
	
	<!-- <%real_class.getPrefixedQName()%> EDIT -->
	<config evaluator="node-type" condition="<%real_class.getPrefixedQName()%>">
		<%generate_fromClass()%>
	</config>
<%}%>

<%script type="form.FormCollection" name="generateSearchForms"%>
<%for (forms.filter("FormSearch")){%>
<!-- <%real_class.getPrefixedQName()%> SEARCH -->
	<config evaluator="model-type" condition="<%real_class.getPrefixedQName()%>">
	<%generate_fromClass()%>
	</config>
<%}%>

<%script type="FormContainer" name="generate_fromClass"%>
<forms>
	<form<%if (service::getRootContainer().name.toLowerCase() != "default"){%> id="<%getFormId()%>"<%}%>>
	
		<%generate_templateForClass(presentation.toString())%>
		
		<field-visibility>
			<%generate_visibilityForClass()%>
		</field-visibility>
		<appearance>
		<%if (children.filter("FormElement")[ref.filter("clazz.Attribute") || ref.filter("clazz.Association")].nSize() > 0){%>
			<set id=""
				 label-id="form.set.label.<%getPrefixedQualifiedName()%>" />
			<set id="<%getPrefixedQualifiedName()%>"
				 <%getXtensionAsXMLAttribute("appearance")%>
				 <%getXtensionAsXMLAttribute("template")%>
				 parent="" />
		<%}%>
			<%generate_appearanceForClass(getPrefixedQualifiedName())%>
		</appearance>
	</form>
</forms>

<%script type="FormContainer" name="generate_templateForClass"%>

	 <%if (isSearchForm) {%>
	 	<edit-form template="/side/templates/search-form.ftl" />
	 <%}else if (args(0) == "tabbed" || args(0) == "auto"){%>
		<edit-form template="/fdk/templates/tab-edit-form.ftl" />
		<create-form template="/fdk/templates/tab-edit-form.ftl" />
		<view-form template="/fdk/templates/tab-edit-form.ftl" />
	 <%}%>


<%script type="FormContainer" name="generate_visibilityForClass"%>
<%for (getAllKindFields()){%>
<%if (ref.filter("clazz.Attribute")){%>
<show id="<%ref.getPrefixedQName()%>" force="true" />
<%}%>
<%for (ref.filter("clazz.Association")){%>
	<%if (firstEnd.linkedClass == current("Clazz")){%>
<show id="<%getPrefixedAssociationQName(secondEnd)%>" force="true" />
	<%}else{%>
<show id="<%getPrefixedAssociationQName(firstEnd)%>" force="true" />
	<%}%>
<%}%>

<%}%>

<%script type="FormContainer" name="generate_appearanceForClass"%>
<%-- fields --%>
<%for (children.filter("FormElement")[ref.filter("clazz.Attribute") || ref.filter("clazz.Association")]){%>
<%generate_appearance_field(args(0))%>
<%}%>

<%-- generate sets --%>
<%for (getAllSubGroups()){%>
<set id="<%getPrefixedQualifiedName()%>"
	 <%getXtensionAsXMLAttribute("appearance")%>
	 <%getXtensionAsXMLAttribute("template")%>
	 label-id="<%getGroupLabelId()%>"
	 <%if (eContainer().filter("FormClass")){%><%}else{%> parent="<%eContainer().filter("FormGroup").getPrefixedQualifiedName()%>"<%}%> />

	<%for (children.filter("FormElement")[ref.filter("clazz.Attribute") || ref.filter("clazz.Association")]){%>
<%generate_appearance_field("")%>
	<%}%>						
<%}%>

<%-- getXtensionAsXMLAttribute :
	 args(0) : expected parameter name
	 returns the value of the expected parameter stored in Xtension
--%>
<%script type="FormElement" name="getXtensionAsXMLAttribute" post="trim()" %>
<%for (Xtension) {%>
	<%if (toLowerCase().startsWith(args(0).toLowerCase())) {%>
		<%args(0)%>="<%toString().substring(toString().indexOf("=") + 1, toString().length())%>"
	<%}%>
<%}%>

<%-- getXtensionAsControlParam :
	 args(0) : expected parameter name
	 returns the value of the expected parameter stored in Xtension and create control-param element
--%>
<%script type="FormElement" name="getXtensionAsControlParam" post="trim()" %>
<%for (Xtension) {%>
	<%if (toLowerCase().startsWith(args(0).toLowerCase())) {%>
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

<%script type="FormElement" name="generate_appearance_field"%>
<field id="<%getFieldId()%>" set="<%if (eContainer().filter("FormContainer")){%><%args(0)%><%}else{%><%current("FormGroup").getPrefixedQualifiedName()%><%}%>" 
label-id="<%getFieldLabelId("")%>"
 <%if (help_text != null && help_text != ""){%>
 help-id="<%getFieldLabelId("help.")%>"
 <%}%>
 <%if (description != null && description != ""){%>
 description-id="<%getFieldLabelId("description.")%>"
 <%}%>
 >
 
 <%if (filter("FileField") && ref.getPrefixedQName() == "cm:content"){%>
 <control template="/side/controls/upload.ftl">
 </control>
 <%}%>
 
 <%if (isSearchForm){%>
 <%for (ref.filter("clazz.Attribute")){%>
 <%if (getShareSearchFormControl() != ""){%>
 <control template="<%getShareSearchFormControl()%>" >
 	<control-param name="forceEditable">true</control-param>
 </control>
 <%}else{%>
 <control>
 	<control-param name="forceEditable">true</control-param>
 </control>
 <%}%> 
 <%}%>
 <%}else{%>
 
 <%}%>
 
 <%for (filter("ModelChoiceField")){%>
 <!--extension><%Xtension%></extension-->
 	<%if (widget.toString() == "Search"){%>
 <control template="/side/controls/association-search.ftl" >
 <%getControlParamsForPicker()%>
 </control>
 	<%}%>
 	<%if (widget.toString() == "Select"){%>
 <control template="/side/controls/association-select.ftl" >
 <%getXtensionAsControlParam("multipleSelectMode")%>
 <%getXtensionAsControlParam("filterTerm")%>
 <%getXtensionAsControlParam("maxResults")%> 		
 </control>
 	<%}%>
 	<%if (widget.toString() == "Inline"){%>
 	<%-- TODO--%> 
 	<%}%>
 	<%if (widget.toString() == "ItemSelector"){%>
 	<!-- default widget (itemSelector) -->
 	<%if (Xtension.nSize() > 0){%>
 	<control>
 	<%getControlParamsForPicker()%>
 	</control>
 	<%}%>
 	
 	<%}%>
 <%}%>
 <%for (filter("ModelChoiceSearchField")){%>
 	<%if (widget.toString() == "Search"){%>
 <control template="/side/controls/association-search.ftl" >
 <%getControlParamsForPicker()%>
 	<control-param name="multipleSelectMode">true</control-param>
 </control>
 	<%}%>
 	<%if (widget.toString() == "Select"){%>
 <control template="/side/controls/association-select.ftl" >
 	<control-param name="multipleSelectMode">true</control-param>
 <%getXtensionAsControlParam("filterTerm")%>
 <%getXtensionAsControlParam("maxResults")%>
 </control>
 	<%}%>
 	<%if (widget.toString() == "Inline"){%>
 	<%-- TODO--%> 
 	<%}%>
 	<%if (widget.toString() == "ItemSelector"){%>
 	<!-- default widget (itemSelector) -->
 	<%if (Xtension.nSize() > 0){%>
 	<control>
 		<control-param name="multipleSelectMode">true</control-param>
 	<%getControlParamsForPicker()%> 	
 	</control>
 	<%}%>
 	<%}%>
 <%}%>
 </field>

<%script type="FormElement" name="getControlParamsForPicker" post="trim()" %>
 <%getXtensionAsControlParam("compactMode")%>
 <%getXtensionAsControlParam("forceEditable")%>
 <%getXtensionAsControlParam("startLocation")%>
 <%getXtensionAsControlParam("selectedValueContextProperty")%>
 <%getXtensionAsControlParam("valueType")%>
 <%getXtensionAsControlParam("selectActionLabel")%> 
 <%getXtensionAsControlParam("multipleSelectMode")%>
 <%getXtensionAsControlParam("showTargetLink")%>
 <%getXtensionAsControlParam("displayMode")%>
 
 
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
<%script type="FormContainer" name="getFormId" post="trim()" %>
<%if (isSearchForm){%>
search
<%}else{%>
form.<%current("ClassFormCollection").name%>
<%}%>