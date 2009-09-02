<%
metamodel http://www.kerblue.org/workflow/1.0
import com.bluexml.side.workflow.generator.alfresco.templates.servicesTemplates.Common
import com.bluexml.side.workflow.generator.alfresco.WorkflowGenerator
%>
<%script type="workflow.Process" name="validatedFilename"%>
<%getTEMP_FOLDER()%>/<%getConfModulePath()%>/web-client-config-custom.xml
<%script type="workflow.Process" name="alfrescoGenerator" file="<%validatedFilename%>"%>
<?xml version='1.0' encoding='iso-8859-1'?>

<alfresco-config>

  	<!-- Configuration for BPM -->

	<%for (startstate) {%>
	<config evaluator="node-type" condition="wfbx<%current("Process").name%>:<%name%>" replace="true">
	   <property-sheet>	      	      	     
	      <show-property name="bpm:workflowDescription" component-generator="TextAreaGenerator" />
	      <show-property name="bpm:workflowPriority" />
	      <show-property name="bpm:workflowDueDate" />
	      <%for (attributes){%>
  	      <show-property name="wfbx<%current("Process").name%>:<%name%>"/>
	      <%}%>
	      <%for (clazz){%>
	      <show-association name="wfbx<%current("Process").name%>:<%current("StartState").name%>_<%name%>"/>
	      <%}%>

		  <%if (current("Process").swimlane[(actorid == null || actorid.length() == 0) && (pooledactors == null || pooledactors.length() == 0) && (name != "initiator")].nSize() > 0){%>
		  	<%if (current("StartState").initiator.actorid != null) {%>
		      	<show-association name="bpm:assignee" />
		  	<%}else{%>
			      <show-association name="bpm:groupAssignee" />
			<%}%>		  	
		  <%}%>
		  	      
	   </property-sheet>
	</config>	   
	<%}%>
	
	<%for (tasknode) {%>
	<config evaluator="node-type" condition="wfbx<%current("Process").name%>:<%name%>" replace="true">
	   <property-sheet>	      	      	     
	      <show-property name="bpm:workflowDescription" component-generator="TextAreaGenerator" />
	      <show-property name="bpm:workflowPriority" />
	      <show-property name="bpm:workflowDueDate" />
	      <%for (attributes){%>
  	      <show-property name="wfbx<%current("Process").name%>:<%name%>"/>
	      <%}%>
	      <%for (clazz){%>
	      <show-association name="wfbx<%current("Process").name%>:<%current("TaskNode").name%>_<%name%>"/>
	      <%}%>
	   </property-sheet>
	</config>	   
	<%}%>

</alfresco-config>