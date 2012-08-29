<%
metamodel http://www.kerblue.org/form/1.0
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.form.generator.alfresco34d.FormGenerator
import com.bluexml.side.form.generator.alfresco34d.templates.formGenerator-util
import com.bluexml.side.form.generator.alfresco34d.templates.services.form
%>

<%script type="FormElement" name="getAssociationControl"%>
<%if (filter("TextField")){%>
	<control template="/org/alfresco/components/form/controls/association.ftl">
		<%getXtensionAsControlParam("compactMode")%>
		<%getXtensionAsControlParam("displayMode")%>
		<%getXtensionAsControlParam("showTargetLink")%>
		<%getXtensionAsControlParam("selectedValueContextProperty")%>
		<%getXtensionAsControlParam("selectActionLabel")%>
		<%getXtensionAsControlParam("selectActionLabelId")%>
		<%getXtensionAsControlParam("forceEditable")%>
		<%getXtensionAsControlParam("startLocation")%>
		<%getXtensionAsControlParam("startLocationParams")%>
		<%getXtensionAsControlParam("allowNavigationToContentChildren")%>
		<%getBehaviour()%>
	</control>
<%}%>

<%script type="FormElement" name="getAuthorityControl"%>
<%if (filter("TextField")){%>
	<control template="/org/alfresco/components/form/controls/authority.ftl">
		<%getXtensionAsControlParam("forceEditable")%>
		<%getXtensionAsControlParam("compactMode")%>
		<%getBehaviour()%>
	</control>
<%}%>

<%script type="FormElement" name="getCategoryControl"%>
<%if (filter("Field") && ref.getPrefixedQName() == "cm:categories"){%>
	<control template="/org/alfresco/components/form/controls/category.ftl">
		<%getXtensionAsControlParam("forceEditable")%>
		<%getXtensionAsControlParam("compactMode")%>
		<%getXtensionAsControlParam("parentNodeRef")%>
		<%getXtensionAsControlParam("showSubCategoriesOption")%>
		<%getBehaviour()%>
	</control>
<%}%>

<%script type="FormElement" name="getTagControl"%>
<%if (filter("Field") && ref.getPrefixedQName() == "cm:taggable"){%>
	<control template="/org/alfresco/components/form/controls/category.ftl">		
	     <control-param name="compactMode">true</control-param>
	     <control-param name="params">aspect=cm:taggable</control-param>
	     <control-param name="createNewItemUri">/api/tag/workspace/SpacesStore</control-param>
	     <control-param name="createNewItemIcon">tag</control-param>        
	</control>
<%}%>

<%script type="FormElement" name="getCheckBoxControl"%>
<%if (filter("BooleanField")){%>
	<control template="/org/alfresco/components/form/controls/checkbox.ftl">
		<%getXtensionAsControlParam("styleClass")%>
		<%getXtensionAsControlParam("style")%>
		<%getXtensionAsControlParam("forceEditable")%>
		<%getBehaviour()%>
	</control>
<%}%>

<%script type="FormElement" name="getContentControl"%>
<%if (filter("TextField")){%>
	<control template="/org/alfresco/components/form/controls/content.ftl">
		<%getXtensionAsControlParam("rows")%>
		<%getXtensionAsControlParam("columns")%>
		<%getXtensionAsControlParam("styleClass")%>
		<%getXtensionAsControlParam("style")%>
		<%getXtensionAsControlParam("editorAppearance")%>
		<%getXtensionAsControlParam("editorParameters")%>
		<%getXtensionAsControlParam("editorHeight")%>
		<%getXtensionAsControlParam("editorWidth")%>
		<%getXtensionAsControlParam("plainMimeTypes")%>
		<%getXtensionAsControlParam("richMimeTypes")%>
		<%getXtensionAsControlParam("forceEditor")%>
		<%getXtensionAsControlParam("forceEditable")%>
		<%getBehaviour()%>
	</control>
<%}%>

<%script type="FormElement" name="getDateControl"%>
<%if (filter("DateField")){%>
	<control template="/org/alfresco/components/form/controls/date.ftl">
		<%getXtensionAsControlParam("showTime")%>
		<%getXtensionAsControlParam("forceEditable")%>
		<%getBehaviour()%>
	</control>
<%}%>

<%script type="FormElement" name="getEncodingControl"%>
<%if (filter("TextField")){%>
	<control template="/org/alfresco/components/form/controls/encoding.ftl">
		<%getXtensionAsControlParam("styleClass")%>
		<%getXtensionAsControlParam("style")%>
		<%getXtensionAsControlParam("property")%>
		<%getBehaviour()%>
	</control>
<%}%>

<%script type="FormElement" name="getHiddenControl"%>
<%if (filter("TextField")){%>
	<control template="/org/alfresco/components/form/controls/hidden.ftl">
		<%getXtensionAsControlParam("contextProperty")%>
		<%getBehaviour()%>
	</control>
<%}%>

<%script type="FormElement" name="getInfoControl"%>
<%if (filter("TextField")){%>
	<control template="/org/alfresco/components/form/controls/info.ftl">
		<%getBehaviour()%>
	</control>
<%}%>

<%script type="FormElement" name="getMimeTypeControl"%>
<%if (filter("TextField")){%>
	<control template="/org/alfresco/components/form/controls/mimetype.ftl">
		<%getXtensionAsControlParam("styleClass")%>
		<%getXtensionAsControlParam("style")%>
		<%getXtensionAsControlParam("property")%>
		<%getBehaviour()%>
	</control>
<%}%>

<%script type="FormElement" name="getNumberControl"%>
<%if (filter("NumericalField")){%>
	<control template="/org/alfresco/components/form/controls/number.ftl">
		<%getXtensionAsControlParam("styleClass")%>
		<%getXtensionAsControlParam("style")%>
		<%getXtensionAsControlParam("maxLength")%>
		<%getXtensionAsControlParam("size")%>
		<%getXtensionAsControlParam("forceEditable")%>
		<%getBehaviour()%>
	</control>
<%}%>

<%script type="FormElement" name="getPeriodControl"%>
<%if (filter("TextField")){%>
	<control template="/org/alfresco/components/form/controls/period.ftl">
		<%getXtensionAsControlParam("forceEditable")%>
		<%getBehaviour()%>
	</control>
<%}%>

<%script type="FormElement" name="getRichTextControl"%>
<%if (filter("TextField")){%>
	<control template="/org/alfresco/components/form/controls/richtext.ftl">
		<%getXtensionAsControlParam("editorAppearance")%>
		<%getXtensionAsControlParam("editorParameters")%>
		<%getXtensionAsControlParam("editorHeight")%>
		<%getXtensionAsControlParam("editorWidth")%>
		<%getXtensionAsControlParam("forceEditable")%>
		<%getBehaviour()%>
	</control>
<%}%>

<%script type="FormElement" name="getSelectOneControl"%>
<%if (filter("TextField")){%>
	<control template="/org/alfresco/components/form/controls/selectone.ftl">
		<%getXtensionAsControlParam("styleClass")%>
		<%getXtensionAsControlParam("style")%>
		<%getXtensionAsControlParam("options")%>
		<%getXtensionAsControlParam("size")%>
		<%getXtensionAsControlParam("forceEditable")%>
		<%getBehaviour()%>
	</control>
<%}%>

<%script type="FormElement" name="getSelectManyControl"%>
<%if (filter("TextField")){%>
	<control template="/org/alfresco/components/form/controls/selectmany.ftl">
		<%getXtensionAsControlParam("styleClass")%>
		<%getXtensionAsControlParam("style")%>
		<%getXtensionAsControlParam("options")%>
		<%getXtensionAsControlParam("size")%>
		<%getXtensionAsControlParam("forceEditable")%>
		<%getBehaviour()%>
	</control>
<%}%>

<%script type="FormElement" name="getSizeControl"%>
<%if (filter("TextField")){%>
	<control template="/org/alfresco/components/form/controls/size.ftl">
		<%getXtensionAsControlParam("property")%>
		<%getBehaviour()%>
	</control>
<%}%>

<%script type="FormElement" name="getTextAreaControl"%>
<%if (filter("TextField")){%>
	<control template="/org/alfresco/components/form/controls/textarea.ftl">
		<%getXtensionAsControlParam("styleClass")%>
		<%getXtensionAsControlParam("style")%>
		<%getXtensionAsControlParam("rows")%>
		<%getXtensionAsControlParam("columns")%>
		<%getXtensionAsControlParam("activateLinks")%>
		<%getXtensionAsControlParam("forceEditable")%>
		<%getBehaviour()%>
	</control>
<%}%>

<%script type="FormElement" name="getTextFieldControl"%>
<%if (filter("CharField")){%>
	<control template="/org/alfresco/components/form/controls/textfield.ftl">
		<%getXtensionAsControlParam("styleClass")%>
		<%getXtensionAsControlParam("style")%>
		<%getXtensionAsControlParam("maxLength")%>
		<%getXtensionAsControlParam("size")%>
		<%getXtensionAsControlParam("activateLinks")%>
		<%getXtensionAsControlParam("forceEditable")%>
		<%getBehaviour()%>
	</control>
<%}%>

<%script type="FormElement" name="getFileFieldControl"%>
<%if (filter("FileField") && ref.getPrefixedQName() == "cm:content"){%>
	<control template="/side/controls/upload.ftl">
		<%getBehaviour()%>	
	</control>
<%}%>

<%--
	ModelChoiceField represent associations. Following types are available:
	* Inline
	* ItemSelector (default)
	* Search
	args(0) : indicate if multiple selection is allowed
	
	Type may be ModelChoiceField or ModelChoiceSearchField which
	both intherits FormElement
--%>
<%script type="FormElement" name="getModelChoiceFieldControl"%>
<!--<extension><%Xtension%></extension>-->
<!-- widget :<%widget.toString()%> -->
<%if (widget.toString() == "Search"){%>
	<control template="/side/controls/association-search.ftl" >
		<%if (args(0) == "multiple"){%>
		 	<control-param name="multipleSelectMode">true</control-param>		
		<%}%>
		<%getXtensionAsControlParam("showTargetLink")%>
		<%getPickerControlParams()%>
		<%getBehaviour()%>
	</control>
<%}else if (widget.toString() == "Select"){%>
	<control template="/side/controls/association-select.ftl" >
		<%if (args(0) == "multiple"){%>
		<control-param name="multipleSelectMode">true</control-param>		
		<%}else{%>
		<%getXtensionAsControlParam("multipleSelectMode")%>
		<%}%>
		<%if (filter("ClassReference").real_class.filter("clazz.Aspect") != null){%>
		<control-param name="selectableTypeIsAspect">true</control-param>
		<%}else{%>
		<control-param name="selectableTypeIsAspect">false</control-param>
		<%}%>
		<%getXtensionAsControlParam("filterTerm")%>
		<%getXtensionAsControlParam("advancedQuery")%>
		<%getXtensionAsControlParam("maxResults")%>
		<%getXtensionAsControlParam("startLocation")%>
		<%getXtensionAsControlParam("getDataSource")%>
		
		<%if (filter("ModelChoiceField").association_formClass.nSize() == 1){%>		
			<%if (getXtensionValue("allowToCreate") != "false"){%>
		<control-param name="addNewConfig">true</control-param>
			<%}%>
			<%if (getXtensionValue("allowToEdit") != "false"){%>
		<control-param name="editConfig">true</control-param>
			<%}%>
			<%if (filter("ModelChoiceField").association_formClass.nGet(0).eContainer().filter("FormCollection").name != "default"){%>
		<control-param name="targetFormId"><%filter("ModelChoiceField").association_formClass.nGet(0).eContainer().filter("FormCollection").name%></control-param>		
		<control-param name="itemType"><%filter("ModelChoiceField").association_formClass.nGet(0).real_class.getPrefixedQName()%></control-param>		
			<%}%>
		<%}else{%>
		<%getXtensionAsControlParam("addNewConfig")%>
		<%getXtensionAsControlParam("targetFormId")%>
		<%getXtensionAsControlParam("itemType")%>
		<%}%>
		<%getXtensionAsControlParam("targetItemKind")%>
		<%getXtensionAsControlParam("targetFormMode")%>
		<%getXtensionAsControlParam("targetFormSubmitType")%>		
		<%getXtensionAsControlParam("targetDestination")%>
		<%getXtensionAsControlParam("hideSelector")%>
		<%getXtensionAsControlParam("showTargetLink")%>
		<%getBehaviour()%>
	</control>
<%}else if (widget.toString() == "Inline"){%>
	 	<%-- TODO--%> 
<%}else if (widget.toString() == "ItemSelector"){%>
	<!-- default widget (itemSelector) -->
	<%if (Xtension.nSize() > 0){%>
	<control>
		<%if (args(0) == "multiple"){%>
		 	<control-param name="multipleSelectMode">true</control-param>		
		<%}%>
		<%getPickerControlParams()%>
		<%getBehaviour()%>
	</control>
	<%}%>

<%}%>

<%script type="FormElement" name="getPickerControlParams"%>
<%getXtensionAsControlParam("compactMode")%>
<%getXtensionAsControlParam("forceEditable")%>
<%getXtensionAsControlParam("startLocation")%>
<%getXtensionAsControlParam("selectedValueContextProperty")%>
<%getXtensionAsControlParam("valueType")%>
<%getXtensionAsControlParam("selectActionLabel")%> 
<%getXtensionAsControlParam("multipleSelectMode")%>
<%getXtensionAsControlParam("showTargetLink")%>
<%getXtensionAsControlParam("displayMode")%>

