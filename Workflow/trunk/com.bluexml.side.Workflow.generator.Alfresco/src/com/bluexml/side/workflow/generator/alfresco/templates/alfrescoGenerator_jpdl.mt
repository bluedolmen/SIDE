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
%>

<%script type="workflow.Process" name="validatedFilename"%>
/shared/classes/alfresco/extension/BxDS/generated/bpm/<%name%>_processdefinition.xml
<%script type="workflow.Process" name="alfrescoGenerator" file="<%validatedFilename%>"%>
<?xml version="1.0" encoding="iso-8859-1"?>

<process-definition xmlns="urn:jbpm.org:jpdl-3.1" name="wfbx:<%name%>">

<%for (swimlane) {%>
  	<swimlane name="<%actorid%>">
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
	  		<%for (transition){%>
	  			<%for (timer){%>
	  			<timer name="Timer" duedate="<%duedate%>" transition="<%current("Transition").name%>">
	  			<%for (current("Transition").action) {%>
	  			<action class="<%javaClass.replaceAll("\"","")%>">
	  			<%for (script) {%>
					<script>
						<%for (variable){%>
							<variable name="<%name%>" access="<%access%>"/>
						<%}%>
						<%expression%>
					</script>
	  			<%}%>
				</action>
				<%}%>
	  			</timer>
	  			<%}%>	  		
	  		<%}%>
	  	</task>

	  	<%for (transition){%>
	  	
	  	<transition name="<%title%>" to="<%to.name%>">
	  		<%for (action) {%>
	  			<action class="<%javaClass.replaceAll("\"","")%>">
	  			<%for (script) {%>
					<script>
						<%for (variable){%>
							<variable name="<%name%>" access="<%access%>"/>
						<%}%>
						<%expression%>
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
						<%expression%>
					</script>
	  			<%}%>
			</action>
			<%}%>
		</event>
		<%}%>

		 <%for (transition){%>
	  			<%for (timer){%>
	  			<timer name="Timer" duedate="<%duedate%>" transition="<%current("Transition").name%>">
	  			<%for (current("Transition").action) {%>
	  			<action class="<%javaClass.replaceAll("\"","")%>">
	  			<%for (script) {%>
					<script>
						<%for (variable){%>
							<variable name="<%name%>" access="<%access%>"/>
						<%}%>
						<%expression%>
					</script>
	  			<%}%>
				</action>
				<%}%>
	  			</timer>
	  			<%}%>	  		
	  		<%}%>
	  	</task>

	  	<%for (transition){%>
	  	
	  	<transition name="<%title%>" to="<%to.name%>">
	  		<%for (action) {%>
	  			<action class="<%javaClass.replaceAll("\"","")%>">
	  			<%for (script) {%>
					<script>
						<%for (variable){%>
							<variable name="<%name%>" access="<%access%>"/>
						<%}%>
						<%expression%>
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
						<%expression%>
					</script>
	  			<%}%>
			</action>
			<%}%>
		</event>
		<%}%>

		 <%for (transition){%>
	  			<%for (timer){%>
	  			<timer name="Timer" duedate="<%duedate%>" transition="<%current("Transition").name%>">
	  			<%for (current("Transition").action) {%>
	  			<action class="<%javaClass.replaceAll("\"","")%>">
	  			<%for (script) {%>
					<script>
						<%for (variable){%>
							<variable name="<%name%>" access="<%access%>"/>
						<%}%>
						<%expression%>
					</script>
	  			<%}%>
				</action>
				<%}%>
	  			</timer>
	  			<%}%>	  		
	  		<%}%>
	  	</task>

	  	<%for (transition){%>
	  	
	  	<transition name="<%title%>" to="<%to.name%>">
	  		<%for (action) {%>
	  			<action class="<%javaClass.replaceAll("\"","")%>">
	  			<%for (script) {%>
					<script>
						<%for (variable){%>
							<variable name="<%name%>" access="<%access%>"/>
						<%}%>
						<%expression%>
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
		<%for (transition){%>
	  	
		<transition name="<%to.name%>" to="<%to.name%>">
	  		<%for (action) {%>
	  			<action class="<%javaClass.replaceAll("\"","")%>">
	  			<%for (script) {%>
					<script>
						<%for (variable){%>
							<variable name="<%name%>" access="<%access%>"/>
						<%}%>
						<%expression%>
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

<%for (join){%>
	<fork name="<%name%>">
		<%for (transition){%>
	  	
		<transition name="<%to.name%>" to="<%to.name%>">
	  		<%for (action) {%>
	  			<action class="<%javaClass.replaceAll("\"","")%>">
	  			<%for (script) {%>
					<script>
						<%for (variable){%>
							<variable name="<%name%>" access="<%access%>"/>
						<%}%>
						<%expression%>
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
		<%for (transition){%>
	  	
		<transition name="<%to.name%>" to="<%to.name%>">
	  		<%for (action) {%>
	  			<action class="<%javaClass.replaceAll("\"","")%>">
	  			<%for (script) {%>
					<script>
						<%for (variable){%>
							<variable name="<%name%>" access="<%access%>"/>
						<%}%>
						<%expression%>
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

</process-definition>