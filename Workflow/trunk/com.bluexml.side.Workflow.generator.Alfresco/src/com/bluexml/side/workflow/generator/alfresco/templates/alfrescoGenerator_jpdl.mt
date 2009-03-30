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
import com.bluexml.side.workflow.generator.alfresco.WorkflowGenerator
%>

<%script type="workflow.Process" name="validatedFilename"%>
<%getTEMP_FOLDER()%>/shared/classes/alfresco/extension/generated/bpm/<%name%>_processdefinition.xml
<%script type="workflow.Process" name="alfrescoGenerator" file="<%validatedFilename%>"%>
<?xml version="1.0" encoding="iso-8859-1"?>

<process-definition xmlns="urn:jbpm.org:jpdl-3.1" name="wfbx:<%name%>">

<%for (swimlane) {%>
  	<swimlane name="<%name%>">
  		<%if (current("Process").startstate.initiator != current()) {%>
  			<%if (actorid != null){%>
  			<assignment actor-id="<%actorid%>"/>
  			<%}%>
  			
  			<%if (pooledactors != null){%>
  			<assignment class="org.alfresco.repo.workflow.jbpm.AlfrescoAssignment">
  				<pooledactors><%pooledactors%></pooledactors>  				
        	</assignment>   
  			<%}%>
  		<%}%>
  	</swimlane>
<%}%>

<%for (startstate){%>
	  <start-state name="<%name%>">
	  	<task name="wfbx:<%name%>" swimlane="<%initiator.actorid%>">
			<%for (event){%>
			<event type="<%type%>">
				<%for (action){%>
				<action class="<%javaClass.replaceAll("\"","")%>">
		  			<%for (script) {%>
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
			</event>
			<%}%>	  	
	  		<%for (transition){%>
	  			<%for (timer){%>
	  			<timer name="Timer" duedate="<%duedate%>" transition="<%current("Transition").name%>">
	  			<action class="<%javaClass.replaceAll("\"","")%>">
	  			<%for (script) {%>
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
	  			</timer>
	  			<%}%>	  		
	  		<%}%>
	  	</task>

	  	<%for (transition.sort("condition")){%>
	  	
	  	<transition name="<%name%>" to="<%to.name%>">
	  		<%for (action) {%>
	  			<action class="<%javaClass.replaceAll("\"","")%>">
	  			<%for (script) {%>
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

	  	<%if (condition != null && condition.length()>0){%>
	  	<condition><%condition%></condition>
      	<%}%>
	  	</transition>
	  	<%}%>
	  </start-state>
<%}%>

<%for (endstate){%>
	<end-state name="<%name%>"/>
<%}%>

<%for (tasknode){%>
	<task-node name="<%name%>">
		<task name="wfbx:<%name%>" swimlane="<%swimlane.name%>">
		<%for (event){%>
		<event type="<%type%>">
			<%for (action){%>
			<action class="<%javaClass.replaceAll("\"","")%>">
	  			<%for (script) {%>
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
		</event>
		<%}%>

		 <%for (transition){%>
	  			<%for (timer){%>
	  			<timer name="Timer" duedate="<%duedate%>" transition="<%current("Transition").name%>">
	  			<action class="<%javaClass.replaceAll("\"","")%>">
	  			<%for (script) {%>
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
	  			</timer>
	  			<%}%>	  		
	  		<%}%>
	  	</task>

	  	<%for (transition.sort("condition")){%>
	  	
	  	<transition name="<%name%>" to="<%to.name%>">
	  		<%for (action) {%>
	  			<action class="<%javaClass.replaceAll("\"","")%>">
	  			<%for (script) {%>
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

	  	<%if (condition != null && condition.length()>0){%>
	  	<condition><%condition%></condition>
      	<%}%>
	  	</transition>
	  	<%}%>
	</task-node>
<%}%>

<%for (node){%>
	<node name="<%name%>">
		<%for (event){%>
		<event type="<%type%>">
			<%for (action){%>
			<action class="<%javaClass.replaceAll("\"","")%>">
	  			<%for (script) {%>
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
		</event>
		<%}%>

		 <%for (transition){%>
	  			<%for (timer){%>
	  			<timer name="Timer" duedate="<%duedate%>" transition="<%current("Transition").name%>">
	  			<action class="<%javaClass.replaceAll("\"","")%>">
	  			<%for (script) {%>
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
	  			</timer>
	  			<%}%>	  		
	  		<%}%>

	  	<%for (transition.sort("condition")){%>
	  	
	  	<transition name="<%name%>" to="<%to.name%>">
	  		<%for (action) {%>
	  			<action class="<%javaClass.replaceAll("\"","")%>">
	  			<%for (script) {%>
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

	  	<%if (condition != null && condition.length()>0){%>
	  	<condition><%condition%></condition>
      	<%}%>
	  	</transition>
	  	<%}%>
	</node>
<%}%>

<%for (join){%>
	<join name="<%name%>">
		<%for (transition.sort("condition")){%>
	  	
		<transition name="<%name%>" to="<%to.name%>">
	  		<%for (action) {%>
	  			<action class="<%javaClass.replaceAll("\"","")%>">
	  			<%for (script) {%>
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
	  	<%if (condition != null && condition.length()>0){%>
	  	<condition><%condition%></condition>
      	<%}%>
	  	</transition>
	  	<%}%>
	</join>
<%}%>

<%for (fork){%>
	<fork name="<%name%>">
		<%for (transition.sort("condition")){%>
	  	
		<transition name="<%to.name%>" to="<%to.name%>">
	  		<%for (action) {%>
	  			<action class="<%javaClass.replaceAll("\"","")%>">
	  			<%for (script) {%>
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
	  	<%if (condition != null && condition.length()>0){%>
	  	<condition><%condition%></condition>
      	<%}%>
	  	</transition>
	  	<%}%>
	</fork>
<%}%>


<%for (decision){%>
	<decision name="<%name%>">
		<%for (transition.sort("condition")){%>
	  	
		<transition name="<%to.name%>" to="<%to.name%>">
	  		<%for (action) {%>
	  			<action class="<%javaClass.replaceAll("\"","")%>">
	  			<%for (script) {%>
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
	  	<%if (condition != null && condition.length()>0){%>
	  	<condition><%condition%></condition>
      	<%}%>
	  	</transition>
	  	<%}%>
	</decision>
<%}%>

<%for (processstate){%>
	<process-state name="<%name%>">
		<sub-process name="wfbx:<%subprocess.name%>"/>
		<variable name="bpm_package"/>
		<%for (transition.sort("condition")){%>
	  	
		<transition name="<%to.name%>" to="<%to.name%>">
	  		<%for (action) {%>
	  			<action class="<%javaClass.replaceAll("\"","")%>">
	  			<%for (script) {%>
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
	  	<%if (condition != null && condition.length()>0){%>
	  	<condition><%condition%></condition>
      	<%}%>
	  	</transition>
	  	<%}%>
	</process-state>
<%}%>

</process-definition>