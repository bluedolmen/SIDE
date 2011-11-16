<%
metamodel http://www.kerblue.org/form/1.0
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.form.generator.alfresco34d.FormGenerator
import com.bluexml.side.form.generator.alfresco34d.templates.formGenerator-util
import com.bluexml.side.form.generator.alfresco34d.templates.formGenerator-control
import com.bluexml.side.form.generator.alfresco34d.templates.services.form
%>
<%--
import com.bluexml.side.form.generator.alfresco34d.templates.formGenerator-workflow
--%>
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
	<%--generateWorkflowForms()--%>
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
	<form<%if (service::getRootContainer().name.toLowerCase() != "default"){%>
		id="<%if (isSearchForm){%>search<%}else{%>form.<%current("ClassFormCollection").name%><%}%>"<%}%>>
	
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
>

<%if (Xtension[toString().startsWith("template")].nSize() > 0) {%>
<control <%getXtensionAsXMLAttribute("template")%>>
	<%for (Xtension[!toString().startsWith("template")]){%>
	<%current("FormElement").getXtensionAsControlParam(toString().substring(0, toString().indexOf("=")))%>
	<%}%>
</control>
<%}else{%>
	<%if (filter("BooleanField")){%>
		<%getCheckBoxControl()%>
	<%}else if (filter("TextField")){%>
		<%for (filter("TextField")) {%>
			<%if (widget.toLowerCase() == "richtext") {%>
				<%getRichTextControl()%>
			<%}else{%>
				<%-- TextAreaEditor --%>
				<%getTextAreaControl()%>
			<%}%>
		<%}%>
	<%}else if (filter("CharField")){%>
		<%getContentControl()%>
	<%}else if (filter("DateField")){%>
		<%getDateControl()%>
	<%}else if (filter("HiddenField")){%>
		<%getHiddenControl()%>
	<%}else if (filter("NumericalField")){%>
		<%getNumberControl()%>
	<%}else if (filter("FileField") && ref.getPrefixedQName() == "cm:content"){%>
		<%getFileFieldControl()%> 
	<%}else if (isSearchForm){%>
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
		<%if (filter("ModelChoiceField")){%>
		 <%getModelChoiceFieldControl("")%>
		<%}else if(filter("ModelChoiceSearchField")){%>
		<%getModelChoiceFieldControl("multiple")%>
		<%}%>
	<%}%>
<%}%>




</field>
