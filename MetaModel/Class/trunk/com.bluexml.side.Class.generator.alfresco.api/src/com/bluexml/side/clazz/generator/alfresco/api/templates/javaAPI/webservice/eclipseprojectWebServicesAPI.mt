<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices

%>
<%script type="clazz.ClassPackage" name="validatedFilename"%>
<%if (eContainer() == null) {%><%getProperty("javaWebServicesAPIPath")%>/.project<%}%>
<%script type="clazz.ClassPackage" name="alfrescoGenerator" file="<%validatedFilename%>"%>
<?xml version="1.0" encoding="UTF-8"?>
<projectDescription>
	<name><%getRootPackage().name%>.webServices</name>
	<comment>project that define an api to manage custom types through Alfresco WebServices </comment>
	<projects>
	</projects>
	<buildSpec>
		<buildCommand>
			<name>org.eclipse.jdt.core.javabuilder</name>
			<arguments>
			</arguments>
		</buildCommand>
		<!--<buildCommand>
			<name>org.maven.ide.eclipse.maven2Builder</name>
			<arguments>
			</arguments>
		</buildCommand>-->
	</buildSpec>
	<natures>
		<nature>org.eclipse.jdt.core.javanature</nature>
		<!--<nature>org.maven.ide.eclipse.maven2Nature</nature>-->
	</natures>
</projectDescription>