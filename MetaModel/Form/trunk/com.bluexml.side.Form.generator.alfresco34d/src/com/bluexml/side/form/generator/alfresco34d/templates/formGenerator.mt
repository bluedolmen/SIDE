<%
metamodel http://www.kerblue.org/form/1.0
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.form.generator.alfresco34d.FormGenerator
import com.bluexml.side.form.generator.alfresco34d.templates.formGenerator-util
import com.bluexml.side.form.generator.alfresco34d.templates.formGenerator-control
import com.bluexml.side.form.generator.alfresco34d.templates.services.form
import com.bluexml.side.form.generator.alfresco34d.templates.formGenerator-workflow
%>

<%script type="form.FormCollection" name="fileName"%>
<%if (eContainer() == null) {%><%getProperty("alf.share.paths.web-ext")%>/<%getModuleIdService(getRootPackage().name)%>/share-forms-config.xml<%}%>

<%script type="form.FormCollection" name="generate" file="<%fileName()%>" %>
<?xml version="1.0" encoding="iso-8859-1"?>
<alfresco-config>
	<!-- Forms from SIDE <%current("FormCollection").name%> model -->
	<%generateGlobalConfig%>

	<!-- Input Forms -->
	<%generateInputForms%>
	
	<!-- Search Forms -->
	<%generateSearchForms()%>
	
	<!-- Form Workflows -->
	<%generateWorkflowForms()%>
</alfresco-config>

<%script type="form.FormCollection" name="generateGlobalConfig"%>
	<%if (current("FormCollection").documentation != ""){%>
		<config>
	      <forms>
				<dependencies>
					<%if (current("FormCollection").documentation.indexOf("css") != -1){%>
						<%getGlobalDependencies(current("FormCollection").documentation, "css")%>
					<%}%>
					<%if (current("FormCollection").documentation.indexOf("js") != -1){%>
						<%getGlobalDependencies(current("FormCollection").documentation, "js")%>
					<%}%>
				</dependencies>
			</forms>
		</config>
	<%}%>
		
<%script type="form.FormCollection" name="generateInputForms"%>
<%for (forms.filter("FormClass")){%>
	<!-- SIDE <%id%> Form -->
	<%if (real_class.filter("clazz.Aspect")){%>
	<!-- <%real_class.getPrefixedQName()%> ASPECT -->
	<config evaluator="aspect" condition="<%real_class.getPrefixedQName()%>">
		<%generate_fromClass()%>
	</config>
	<%}else{%>
	<!-- <%real_class.getPrefixedQName()%> CREATE -->
	<config evaluator="model-type" condition="<%real_class.getPrefixedQName()%>">
		<%generate_fromClass()%>
	</config>
	
	<!-- <%real_class.getPrefixedQName()%> EDIT -->
	<config evaluator="node-type" condition="<%real_class.getPrefixedQName()%>">
		<%generate_fromClass()%>
	</config>
	<%}%>
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
	<form<%if (service::getRootContainer().name.toLowerCase() != "default"){%>
		id="<%if (isSearchForm){%>search<%}else{%><%current("ClassFormCollection").name%><%}%>"<%}%>>
	
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
<!--<%Xtension%>-->
<!-- <%Xtension[toString().startsWith("template")].nSize()%>-->
<%if (haveXtension("template")){%>
	<edit-form <%getXtensionAsXMLAttribute("template")%> />
	<create-form <%getXtensionAsXMLAttribute("template")%> />
	<view-form <%getXtensionAsXMLAttribute("template")%> />
<%}else{%>
<%if (isSearchForm) {%>
 	<edit-form template="/side/templates/search-form.ftl" />
<%}else if (args(0) == "tabbed" || args(0) == "auto"){%>
<%-- <%if (args(0) == "tabbed" || args(0) == "auto"){%> --%>
	<edit-form template="/fdk/templates/tab-edit-form.ftl" />
	<create-form template="/fdk/templates/tab-edit-form.ftl" />
	<view-form template="/fdk/templates/tab-edit-form.ftl" />
<%}%>

<%}%>


<%script type="FormContainer" name="generate_visibilityForClass"%>
<%for (getAllKindFields()[ref.filter("clazz.Attribute") || ref.filter("clazz.Association")]){%>
<show id="<%getFieldId()%>" force="true" />
<%}%>

<%script type="FormContainer" name="generate_appearanceForClass"%>
<%-- fields --%>
<%for (children.filter("FormElement")[ref.filter("clazz.Attribute") || ref.filter("clazz.Association")]){%>
<%generate_appearance_field(args(0))%>
<%}%>
<%generate_set()%>

<%script type="FormContainer" name="generate_set"%>
<%-- generate sets --%>
<%for (getAllSubGroups()){%>
<set id="<%getPrefixedQualifiedName()%>"
	 <%getXtensionAsXMLAttribute("appearance")%>
	 <%getXtensionAsXMLAttribute("template")%>
	 label-id="<%getGroupLabelId()%>"
	 <%if (eContainer().filter("FormClass") || eContainer().filter("FormSearch") ){%><%}else{%> parent="<%eContainer().filter("FormGroup").getPrefixedQualifiedName()%>"<%}%> />

	<%for (children.filter("FormElement")[ref.filter("clazz.Attribute") || ref.filter("clazz.Association")]){%>
		<%generate_appearance_field("")%>
	<%}%>						
<%}%>

<%--
	args(0) : FormElement to generate view appearance for
--%>
<%script type="FormElement" name="generate_appearance_field"%>
<%-- set="<%getSetId(args(0))%>" --%>
<field id="<%getFieldId()%>"
	set="<%if (eContainer().filter("FormContainer")){%><%args(0)%><%}else{%><%current("FormGroup").getPrefixedQualifiedName()%><%}%>"
	label-id="<%getFieldLabelId("")%>"
	<%if (help_text != null && help_text != ""){%>
	help-id="<%getFieldLabelId("help.")%>"
	<%}%>
	<%if (description != null && description != ""){%>
	description-id="<%getFieldLabelId("description.")%>"
	<%}%>
	<%if (filter("Field")){%>
	mandatory="<%filter("Field").mandatory%>"
	read-only="<%filter("Field").disabled%>"	
	<%}%>
>

<%if (haveXtension("template")) {%>
<control <%getXtensionAsXMLAttribute("template")%>>
	<%for (Xtension[!toString().startsWith("template")]){%>
	<%current("FormElement").getXtensionAsControlParam(toString().substring(0, toString().indexOf("=")))%>
	<%}%>
	<%for (metainfo[key.toLowerCase() != "template"]){%>
	<%current("FormElement").getXtensionAsControlParam(key)%>
	<%}%>
</control>
<%}else{%>
	<%if (filter("Field") && ref.getPrefixedQName() == "cm:content"){%>
		<%getContentControl()%>
	<%}else if (filter("Field") && ref.getPrefixedQName() == "cm:taggable"){%>
		<%getTagControl()%>
	<%}else if (filter("Field") && ref.getPrefixedQName() == "cm:categories"){%>
		<%getCategoryControl()%>
	<%}else if (filter("FileField") && ref.getPrefixedQName() == "cm:content"){%>
		<%getFileFieldControl()%>	
	<%}else if (filter("BooleanField")){%>
		<%getCheckBoxControl()%>
	<%}else if (filter("TextField")){%>
		<%-- Be careful: TextField for SIDE is ContentField|RichText|TextArea for Alfresco --%>
		<%for (filter("TextField")) {%>
			<%if (widget.toLowerCase() == "richtext") {%>
				<%getRichTextControl()%>
			<%}else{%>
				<%-- TextAreaEditor --%>
				<%getTextAreaControl()%>
			<%}%>
		<%}%>
	<%}else if (filter("CharField")){%>
		<%-- Be careful: TextField for Alfresco is CharField for SIDE --%>
		<%getTextFieldControl()%>
	<%}else if (filter("DateField")){%>
		<%getDateControl()%>
	<%}else if (filter("HiddenField")){%>
		<%getHiddenControl()%>
	<%}else if (filter("NumericalField")){%>
		<%getNumberControl()%>	
	<%}else if (filter("ModelChoiceField")){%>
		 <%getModelChoiceFieldControl("")%>
	<%}else if (isSearchForm){%>
		<%for (filter("DateField")){%>
			<%if (Xtension[toString().startsWith("template")].nSize() > 0) {%>
			<control <%getXtensionAsXMLAttribute("template")%>>
				<%for (Xtension[!toString().startsWith("template")]){%>
				<%current("FormElement").getXtensionAsControlParam(toString().substring(0, toString().indexOf("=")))%>
				<%}%>
				<control-param name="forceEditable">true</control-param>
			</control>
			<%}else{%>
			<control template="/org/alfresco/components/form/controls/daterange.ftl">
				<%for (Xtension[!toString().startsWith("template")]){%>
				<%current("FormElement").getXtensionAsControlParam(toString().substring(0, toString().indexOf("=")))%>
				<%}%>
				<control-param name="forceEditable">true</control-param>
			</control>
			<%}%>
		<%}%>

		<%for (ref.filter("clazz.Attribute")){%>
			<%if (getShareSearchFormControl() != ""){%>
			<control template="<%getShareSearchFormControl()%>" >
				<control-param name="forceEditable">true</control-param>
				<control-param name="search">true</control-param>
			</control>
			<%}else{%>
			<control>
				<control-param name="forceEditable">true</control-param>
			</control>
			<%}%> 
		<%}%>
		<%if (filter("ModelChoiceSearchField")){%>
		<%getModelChoiceFieldControl("multiple")%>
		<%}%>
	<%}%>
<%}%>
</field>
