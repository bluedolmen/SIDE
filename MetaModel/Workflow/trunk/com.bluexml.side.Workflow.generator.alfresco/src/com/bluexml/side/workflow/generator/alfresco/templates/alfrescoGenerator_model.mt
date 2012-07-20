<%
metamodel http://www.kerblue.org/workflow/1.0
import com.bluexml.side.workflow.generator.alfresco.templates.servicesTemplates.Common
import com.bluexml.side.workflow.generator.alfresco.WorkflowGenerator
%>

<%script type="workflow.Process" name="validatedFilename"%>
<%getTEMP_FOLDER()%>/<%getConfModulePath()%>/bpm/model.xml
<%script type="workflow.Process" name="alfrescoGenerator" file="<%validatedFilename%>"%>
<?xml version="1.0" encoding="ISO-8859-1"?>

<model name="wfbx<%name%>:<%name%>" xmlns="http://www.alfresco.org/model/dictionary/1.0">

  <imports>
  		<!-- Import Alfresco Definitions -->
		<import uri="http://www.alfresco.org/model/dictionary/1.0" prefix="d" />
		<import uri="http://www.alfresco.org/model/content/1.0" prefix="cm" />
		<import uri="http://www.alfresco.org/model/application/1.0" prefix="app"/>
		<import uri="http://www.alfresco.org/model/wcmmodel/1.0" prefix="wcm"/>
      	<import uri="http://www.alfresco.org/model/forum/1.0" prefix="fm"/>
      	<import uri="http://www.alfresco.org/model/bpm/1.0" prefix="bpm" />
      	<import uri="http://www.alfresco.org/model/blogintegration/1.0" prefix="blg"/>
      	<%for (tasknode.clazz){%>
      	<import uri="http://www.bluexml.com/model/content/<%getRootContainer().name%>/1.0" prefix="<%getRootContainer().name%>"/>
      	<%}%>
      	<%for (startstate.clazz){%>
      	<import uri="http://www.bluexml.com/model/content/<%getRootContainer().name%>/1.0" prefix="<%getRootContainer().name%>"/>
      	<%}%>
  </imports>

  <namespaces>
     <namespace uri="http://www.bluexml.com/model/workflow/<%name%>/1.0" prefix="wfbx<%name%>"/>
  </namespaces>
  
	<constraints>
		<constraint name="wfbx<%current("Process").name%>:constraint:mail" type="REGEX">
	        <parameter name="expression"><value>^(|[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]+)$</value></parameter>
	        <parameter name="requiresMatch"><value>true</value></parameter>
	     </constraint>
		 
		 <%for (tasknode){%>
		 	<%for (attributes){%>
		 		<%if (allowedValues.split(",").length() > 0){%>
		 	<constraint name="wfbx<%current("Process").name%>:<%name%>:allowedValues" type="LIST">
		        <parameter name="allowedValues">
		            <list>
		            	<%for (allowedValues.split(",")){%>
		            	<value><%self()%></value>
		            	<%}%>
		            </list>
		        </parameter>
		        <parameter name="caseSensitive"><value>true</value></parameter>
		     </constraint>
		     	<%}%>
		     <%}%>
		 <%}%>
	     
	 </constraints>
      
  <types>

  <%for (startstate){%>
	<type name="wfbx<%current("Process").name%>:<%name%>">
		<parent>bpm:startTask</parent>
			<!-- Properties -->
			<properties>
				<%for (attributes) {%>
				<property name="wfbx<%current("Process").name%>:<%name%>">
					<%if (title != null) {%>
					<title> <%title%> </title>
					<%}%>
					<type><%getPropertyType()%></type>
				</property>
				<%}%>
			</properties>
			
			<!-- Associations -->
			<associations>
			<%for (clazz){%>
				<association name="wfbx<%current("Process").name%>:<%current("StartState").name%>_<%name%>">
					<target>
						<class><%getPrefixedQName()%></class>
					</target>
				</association>
			<%}%>
			</associations>
	</type>
  <%}%>

 <%for (tasknode){%>
	<type name="wfbx<%current("Process").name%>:<%name%>">
		<parent>bpm:workflowTask</parent>
			<!-- Properties -->
			<properties>
				<%for (attributes){%>
				<property name="wfbx<%current("Process").name%>:<%name%>">
					<%if (title != null) {%>
					<title> <%title%> </title>
					<%}%>
					<type><%getPropertyType()%></type>
					
					<%if (allowedValues.split(",").length() > 0){%>
					<constraints>
						<constraint ref="wfbx<%current("Process").name%>:<%name%>:allowedValues"/>
					</constraints>
					<%}%>
				</property>
				
				<%}%>
			</properties>
			
			<!-- Associations -->
			<associations>
			<%for (clazz){%>
				<association name="wfbx<%current("Process").name%>:<%current("TaskNode").name%>_<%name%>">
					<target>
						<class><%getPrefixedQName()%></class>
					</target>
				</association>
			<%}%>
			</associations>
	</type>
  <%}%>
    
  </types>
   
</model>
<%script type="workflow.Attribute" name="getPropertyType"%>
<%if (typ.toString().equalsIgnoreCase("boolean")){%>d:boolean<%}%>
<%if (typ.toString().equalsIgnoreCase("byte")){%>d:int<%}%>
<%if (typ.toString().equalsIgnoreCase("char")){%>d:text<%}%>
<%if (typ.toString().equalsIgnoreCase("date")){%>d:date<%}%>
<%if (typ.toString().equalsIgnoreCase("datetime")){%>d:datetime<%}%>
<%if (typ.toString().equalsIgnoreCase("double")){%>d:double<%}%>
<%if (typ.toString().equalsIgnoreCase("float")){%>d:float<%}%>
<%if (typ.toString().equalsIgnoreCase("int")){%>d:int<%}%>
<%if (typ.toString().equalsIgnoreCase("long")){%>d:long<%}%>
<%if (typ.toString().equalsIgnoreCase("object")){%>d:content<%}%>
<%if (typ.toString().equalsIgnoreCase("short")){%>d:int<%}%>
<%if (typ.toString().equalsIgnoreCase("string")){%>d:text<%}%>
<%if (typ.toString().equalsIgnoreCase("void")){%>d:any<%}%>
<%script type="clazz.ClassModelElement" name="getFolder" description="Get the folder to export" %>
<%if (getRootContainer().name != null && getRootContainer().name.length() > 0){%>
<%getRootContainer().name%><%}else{%>
tmp<%}%>
<%script type="clazz.ClassPackage" name="getFolder" description="Get the folder to export" %>
<%if (getRootContainer().name != null && getRootContainer().name.length() > 0){%>
<%getRootContainer().name%><%}else{%>
tmp<%}%>