<%
metamodel http://www.kerblue.org/form/1.0
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.form.generator.alfresco34d.templates.services.form
import com.bluexml.side.form.generator.alfresco34d.templates.formGenerator-util
%>

<%script type="form.FormCollection" name="generateWorkflowForms"%>
<%for (forms.filter("FormWorkflow")){%>	
	<%generateWorklowConfig("normal")%>
	<%if (ref.filter("workflow.StartState")){%>
	<%-- generate form config to be able to edit startWorkflow nodes --%>
	<%generateWorklowConfig("asUserTask")%>
	<%}%>
<%}%>

<%script type="FormWorkflow" name="generateWorklowConfig"%>
<config evaluator="<%computeEvaluator()%>" condition="<%computeformWorkflowCondition(args(0))%>">
	<forms>
        <form>
        	<%generateFormDefinition()%>
        </form>
        <%if (ref.filter("workflow.StartState") == null){%>
        <form id="workflow-details">
        	<%generateFormDefinition()%>
        </form>
        <%}%>
    </forms>
</config>

<%script type="FormWorkflow" name="generateFormDefinition"%>
<%if (presentation.toString() == "tabbed" || presentation.toString() == "auto"){%>
<edit-form template="/fdk/templates/tab-edit-form.ftl" />
<create-form template="/fdk/templates/tab-edit-form.ftl" />
<view-form template="/fdk/templates/tab-edit-form.ftl" />
<%}%>
<field-visibility>
	<%getDefaultWorkflowFields()%>
<%if (ref.filter("workflow.UserTask").advancedTaskDefinition != null){%>
<%generate_visibilityForClass()%>
<%}else{%>	
<%for (getFields().ref.filter("workflow.Attribute")){%>
	<show id="wfbx<%service::getRootContainer().filter("workflow.Process").name%>:<%name%>" />
<%}%>
<%}%>
</field-visibility>
	
<appearance>
	
<%if (ref.filter("workflow.UserTask").advancedTaskDefinition != null){%>
	<%if (children.filter("Field")[ref.filter("clazz.Attribute") || ref.filter("clazz.Association")].nSize() > 0){%>
	<set id="" label-id="form.set.label.<%getPrefixedQualifiedName()%>" />
	<set id="<%getPrefixedQualifiedName()%>" appearance="" parent="" />
	<%}%>
<%generate_appearanceForClass(getPrefixedQualifiedName())%>
<%getDefaultWorkflowAppearance()%>

<%}else{%>
	<set id="" label-id="<%getGroupLabelId()%>" />
	<%if (getFields()[ref.filter("workflow.Attribute")]){%>
	<set id="<%ref.eContainer().filter("workflow.Process").name%>" appearance="" parent="" />
	<%}%>
	
	<%for (getAllSubGroups()){%>
		<set id="<%getFullName()%>" label-id="<%getGroupLabelId()%>"
		 appearance="<%if (eContainer().filter("FormWorkflow")){%><%}else{%>title<%}%>"
		  <%if (eContainer().filter("FormWorkflow")){%><%}else{%>parent="<%eContainer().filter("FormGroup").getFullName()%>"<%}%> />
		<%for (children.filter("Field")){%>
				 <field id="wfbx<%getFieldId(":")%>"
				 set="<%current("FormGroup").getFullName()%>" 
				 label-id="<%getFieldLabelId("")%>"
				 <%if (current("Field").help_text != null && current("Field").help_text != ""){%>
				 help-id="<%getFieldLabelId("help.")%>"
				 <%}%>
				 <%if (current("Field").description != null && current("Field").description != ""){%>
				 description-id="<%getFieldLabelId("description.")%>"
				 <%}%>
				 />			
		<%}%>
	<%}%>
	
	<%if (children){%>
		<%-- custom fields must be added to dedicated set --%>
		<%for (children[ref.filter("workflow.Attribute")]){%>
			<field id="wfbx<%getFieldId(":")%>" set="<%ref.filter("workflow.Attribute").service::getRootContainer().filter("workflow.Process").name%>" />
		<%}%>
	<%}%>
	
	<%getDefaultWorkflowAppearance()%>
<%}%>
</appearance>

<%script type="FormWorkflow" name="computeEvaluator" post="trim()" %>
<%if (ref.filter("workflow.StartState")){%>
string-compare
<%}else{%>
task-type
<%}%>

<%script type="FormWorkflow" name="computeformWorkflowCondition" post="trim()" %>
<%if (ref.filter("workflow.StartState") && args(0) != "asUserTask"){%>
jbpm$wfbx<%ref.eContainer().filter("workflow.Process").name%>:<%ref.eContainer().filter("workflow.Process").name%>
<%}else{%>
<%if (ref.filter("workflow.UserTask").advancedTaskDefinition != null){%>
<%ref.filter("workflow.UserTask").advancedTaskDefinition.getPrefixedQName()%>
<%}else{%>
wfbx<%ref.eContainer().filter("workflow.Process").name%>:<%ref.filter("workflow.State").name%>
<%}%>
<%}%>

<%script type="FormContainer" name="getDefaultWorkflowFields"%>
<%if (ref.filter("workflow.StartState")){%>
<%getDefaultWorkflowFieldsStart()%>
<%}else{%>
<%getDefaultWorkflowFieldsTask()%>
<%}%>

<%script type="FormContainer" name="getDefaultWorkflowAppearance"%>
<%if (ref.filter("workflow.StartState")){%>
<%getDefaultWorkflowAppearanceStart()%>
<%}else{%>
<%getDefaultWorkflowAppearanceTask()%>
<%}%>

<%script type="FormContainer" name="getDefaultWorkflowFieldsStart"%>
<show id="bpm:workflowDescription" />
<show id="packageItems" />
<show id="bpm:workflowPriority" />
<show id="bpm:workflowDueDate" />

<%script type="FormContainer" name="getDefaultWorkflowAppearanceStart"%>
<set id="general" label-id="form.set.label.<%getPrefixedQualifiedName()%>.general" />
<field id="bpm:workflowPriority" set="general" />
<field id="bpm:workflowDescription" set="general" />
<field id="bpm:workflowDueDate" set="general" />
<field id="packageItems" set="general" />

<%script type="FormContainer" name="getDefaultWorkflowFieldsTask"%>
<show id="bpm:comment" />
<show id="transitions" />

<show id="message" />
<show id="bpm:priority" />
<show id="taskOwner" />
<show id="bpm:taskId" />
<show id="bpm:dueDate" />

<show id="bpm:status" />

<show id="packageItems" />

<%script type="FormContainer" name="getDefaultWorkflowAppearanceTask"%>
<set id="response" appearance="title" label-id="workflow.set.response" parent="" />
<set id="general" label-id="form.set.label.<%getPrefixedQualifiedName()%>.general" />
<set id="info" appearance="title" label-id="workflow.set.task.info" template="/org/alfresco/components/form/3-column-set.ftl" parent="general" />
<set id="progress" appearance="title" label-id="workflow.set.task.progress" parent="general" />
<set id="items" appearance="title" label-id="workflow.set.items" parent="general" />

<field id="message">
	<control template="/org/alfresco/components/form/controls/info.ftl" />
</field>
<field id="bpm:comment" label-id="workflow.field.comment" set="response">
	<control template="/org/alfresco/components/form/controls/textarea.ftl" />
</field>
<field id="transitions" set="response" />
<field id="taskOwner" set="info" />
<field id="bpm:taskId" set="info">
	<control template="/org/alfresco/components/form/controls/info.ftl" />
</field>
<field id="bpm:priority" set="info">
	<control template="/org/alfresco/components/form/controls/workflow/priority.ftl" />
</field>
<field id="bpm:dueDate" set="info" label-id="workflow.field.due">
	<control template="/org/alfresco/components/form/controls/info.ftl" />
</field>
<field id="bpm:status" set="progress" />
<field id="packageItems" set="items" />
