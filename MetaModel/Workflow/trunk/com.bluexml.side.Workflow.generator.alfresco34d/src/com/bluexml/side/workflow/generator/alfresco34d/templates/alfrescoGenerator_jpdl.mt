<%--
Copyright (C) BlueXML 2005-2008

This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 2.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Boston, MA 02111.
 --%>
 <%
metamodel http://www.kerblue.org/workflow/1.0
import com.bluexml.side.workflow.generator.alfresco.templates.servicesTemplates.Common
import com.bluexml.side.workflow.generator.alfresco.WorkflowGenerator
import com.bluexml.side.clazz.service.alfresco.CommonServices
%>

<%script type="workflow.Process" name="validatedFilename"%>
<%getTEMP_FOLDER()%>/<%getConfModulePath()%>/bpm/processdefinition.xml
<%script type="workflow.Process" name="alfrescoGenerator" file="<%validatedFilename%>"%>
<?xml version="1.0" encoding="iso-8859-1"?>

<process-definition name="wfbx<%name%>:<%name%>">
<%for (swimlane) {%>
  	<swimlane name="<%name%>">
  		<%if (current("Process").startstate.initiator != current() || name.toLowerCase() != "initiator") {%>
  			<%if (actorid != null){%>
  				<%if (actorid.startsWith("#{")){%>
  			<assignment class="org.alfresco.repo.workflow.jbpm.AlfrescoAssignment">
				<actor><%actorid%></actor>
			</assignment>
  				<%}else{%>
  			<assignment actor-id="<%actorid%>"/>
  				<%}%>
  			<%}%>
  			
  			<%if (pooledactors != null){%>
	  			<%if (actorid.startsWith("#{")){%>
	  		<assignment class="org.alfresco.repo.workflow.jbpm.AlfrescoAssignment">
  				<pooledactors><%pooledactors%></pooledactors>
        	</assignment>
	  			<%}else{%>
	  		<assignment class="org.alfresco.repo.workflow.jbpm.AlfrescoAssignment">
  				<pooledactors>#{people.getGroup('GROUP_<%pooledactors%>')}</pooledactors>
        	</assignment>	
	  			<%}%>  			
  			<%}%>
  		<%}%>
  	</swimlane>
<%}%>
<%for (startstate){%>
	  <start-state name="<%name%>">
	  	<task name="<%getDataTypeQName()%>" swimlane="<%initiator.name%>">
			<%for (event){%>
			<event type="<%type%>">
				<%for (action){%>
				<%generate_action()%>
				<%}%>
			</event>
			<%}%>
	  	</task>	  	
	  	<%generate_transition()%>
	  </start-state>
<%}%>
<%for (tasknode){%>
	<task-node name="<%name%>">
		<task name="<%getDataTypeQName()%>" swimlane="<%swimlane.name%>">
		<%for (event){%>
		<event type="<%type%>">
			<%for (action){%>
			<%generate_action()%>
			<%}%>
		</event>
		<%}%>
		</task>
	<%generate_transition()%>	
	</task-node>
<%}%>
<%for (node){%>
	<node name="<%name%>">
		<%for (event){%>
		<event type="<%type%>">
			<%for (action){%>
			<%generate_action()%>
			<%}%>
		</event>
		<%}%>
	  	<%generate_transition()%>
	</node>
<%}%>
<%for (join){%>
	<join name="<%name%>">
		<%generate_transition()%>
	</join>
<%}%>
<%for (fork){%>
	<fork name="<%name%>">
		<%generate_transition()%>
	</fork>
<%}%>
<%for (decision){%>
	<decision name="<%name%>">
		<%generate_transition()%>
	</decision>
<%}%>
<%for (processstate){%>
	<process-state name="<%name%>">
		<sub-process name="wfbx<%subprocess.name%>:<%subprocess.name%>"/>
		<variable name="bpm_package"/>		
		<%for (variable){%>
		<variable name="<%name%>" access="<%access%>" mapped-name="<%mappedName%>" />
		<%}%>		
		<%generate_transition()%>		
	</process-state>
<%}%>
<%for (endstate){%>
	<end-state name="<%name%>">
		<%for (event){%>
		<event type="<%type%>">
			<%for (action){%>
			<%generate_action()%>
			<%}%>
		</event>
		<%}%>	
	</end-state>
<%}%>
</process-definition>


<%script type="TransitionTask" name="generate_transition"%>
<%generate_transition_timers()%>

<%for (transition.sort("condition").nReverse()){%>
<transition name="<%name%>" to="<%to.name%>">
	<%for (action) {%>
	<%generate_action()%>
	<%}%>
  	<%if (condition != null && condition.length()>0){%>
  	<condition><%condition%></condition>
  	<%}%>
  	<%for (timer){%>
  	<create-timer name="<%current("Transition").name%>_timer_<%i()%>" >
	</create-timer>
  	<%}%>
</transition>
<%}%>

<%script type="TransitionTask" name="generate_transition_timers"%>
<%for (transition){%>
<%for (timer){%>
<timer name="<%current("Transition").name%>_timer_<%i()%>" duedate="<%duedate%>" transition="<%current("Transition").name%>">
<%generate_action()%>
</timer>
<%}%>
<%}%>

<%script type="Action" name="generate_action"%>
<%if (javaClass != null && javaClass != "" || script != null && script.expression != ""){%>
<action <%if (javaClass != null && javaClass != ""){%>class="<%javaClass.replaceAll("\"","")%>"<%}%>>
<%for (script[expression != ""]) {%>
	<script>
	<%for (variable){%>
		<variable name="<%name%>" access="<%access%>"/>
	<%}%>
		<expression>
		<%expression%>
		</expression>
	</script>
<%}%>
</action>
<%}%>
<%script type="UserTask" name="getDataTypeQName" post="trim()" %>
<%if (advancedTaskDefinition != null){%>
	<%advancedTaskDefinition.getPrefixedQName()%>
<%}else{%>
	wfbx<%current("Process").name%>:<%name%>
<%}%>