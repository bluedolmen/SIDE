<%
metamodel http://www.kerblue.org/workflow/1.0
%>
<%script type="workflow.Process" name="validatedFilename"%>
shared/classes/alfresco/extension/<%name%>_web-client-config.xml.temporary
<%script type="workflow.Process" name="alfrescoGenerator" file="<%validatedFilename%>"%>
<?xml version='1.0' encoding='iso-8859-1'?>

<alfresco-config>

  	<!-- Configuration for BPM -->

	<%for (startstate) {%>
	<config evaluator="node-type" condition="wfbx:<%name%>" replace="true">
	   <property-sheet>	      	      	     
	      <show-property name="bpm:workflowDescription" component-generator="TextAreaGenerator" />
	      <show-property name="bpm:workflowPriority" />
	      <show-property name="bpm:workflowDueDate" />
	      <%for (attributes){%>
  	      <show-property name="wfbx:<%name%>"/>
	      <%}%>
	      <%for (clazz){%>
	      <show-association name="wfbx:<%current("StartState").name%>_<%name%>"/>
	      <%}%>

		  <%if (current("Process").swimlane[(actorid == null || actorid.length() == 0) && (pooledactors == null || pooledactors.length() == 0) && (name != "initiator")].nSize() > 0){%>
		  	<%if (assignmentType.toString().equalsIgnoreCase("one user")) {%>
		      	<show-association name="bpm:assignee" />
		  	<%}else{%>
			      <show-association name="bpm:groupAssignee" />
			<%}%>		  	
		  <%}%>
		  	      
	   </property-sheet>
	</config>	   
	<%}%>
	
	<%for (tasknode) {%>
	<config evaluator="node-type" condition="wfbx:<%name%>" replace="true">
	   <property-sheet>	      	      	     
	      <show-property name="bpm:workflowDescription" component-generator="TextAreaGenerator" />
	      <show-property name="bpm:workflowPriority" />
	      <show-property name="bpm:workflowDueDate" />
	      <%for (attributes){%>
  	      <show-property name="wfbx:<%name%>"/>
	      <%}%>
	      <%for (clazz){%>
	      <show-association name="wfbx:<%current("TaskNode").name%>_<%name%>"/>
	      <%}%>
	   </property-sheet>
	</config>	   
	<%}%>

</alfresco-config>