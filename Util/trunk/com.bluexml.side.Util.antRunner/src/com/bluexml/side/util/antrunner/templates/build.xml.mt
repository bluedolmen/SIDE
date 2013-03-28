<%--
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
--%>
<%
metamodel http://www.bluexml.com/application/1.0/
import com.bluexml.side.util.antrunner.AntFileGeneratorAction

%>

<%script type="application.Application" name="file"%>
build.xml

<%script type="application.Application" name="gen" file="<%file()%>" %>
<?xml version="1.0" encoding="UTF-8"?>
<project name="project" default="<%elements.filter("Configuration").nGet(0).name%>">
    <description>
     Generated ant build file from SIDE application model
            <%if (description != null){%>
            <%description%>
            <%}%>            
    </description>
	
	<eclipse.convertPath resourcepath="<%getSelection()%>" property="application" />
	
	<%for (elements.filter("Configuration")){%>
    <!-- ================================= 
          target: <%current("Configuration").name%>              
         ================================= -->
    <target name="<%name%>" depends="" description="launch side configuration <%name%>">
    	
    	<antcall target="<%name%>-clean" />
    	
    	<antcall target="<%name%>-generate" />
		
		<antcall target="<%name%>-deploy" />
    	
    	<!--
    	<side.clean applicationfile="${application}" configurationname="<%name%>" />
    	-->
    	
    </target>
    
    <target name="<%name%>-clean" depends="">
    	<%if (cleanAllGeneration()){%>
    	<side.clean applicationfile="${application}" configurationname="<%name%>" />
    	<%}%>
    </target>
    
    <target name="<%name%>-generate" depends="">
    	<side.validate applicationfile="${application}" />    	
    	<%for (generatorConfigurations[activated(current("Configuration"))]){%>
    	<side.generate applicationfile="${application}" configurationname="<%current("Configuration").name%>" componentid="<%id%>" />
    	<%}%>
    </target>
    
    <target name="<%name%>-deploy" depends="">
	    <%for (deployerConfigurations[activated(current("Configuration"))]){%>
	    <side.deploy applicationfile="${application}" configurationname="<%current("Configuration").name%>" componentid="<%id%>" />
    	<%}%>    	
    	<side.report applicationfile="${application}" configurationname="<%name%>" />
    </target>
	<%}%>

</project>

<%script type="Configuration" name="cleanAllGeneration"%>
<%parameters[key == "generation.options.clean" && value == "true"].nSize() > 0%>
<%script type="ComponantConfiguration" name="isDocumentation"%>
<%id.endsWith(".documentation")%>
<%script type="ComponantConfiguration" name="activated"%>
<%!isDocumentation() || (isDocumentation() && args(0).filter("Configuration").parameters[key == "generation.options.documentation" && value == "true"].nSize() > 0)%>
