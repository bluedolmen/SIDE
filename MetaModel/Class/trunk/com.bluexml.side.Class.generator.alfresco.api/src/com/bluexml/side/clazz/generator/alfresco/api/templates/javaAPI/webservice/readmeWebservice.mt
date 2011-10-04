<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices

%>
<%script type="clazz.ClassPackage" name="validatedFilename"%>
<%if (eContainer() == null) {%><%getProperty("javaWebServicesAPIPath")%>/README.txt<%}%>
<%script type="clazz.ClassPackage" name="alfrescoGenerator" file="<%validatedFilename%>"%>
This Eclipse project contains generated sources for SIDE Alfresco webservices API.

Project Importation :
In Eclipse use "Import from existing project into workspace", select archive files founded in <generationFolder>/alfresco_<version>_generated_api/.

Project configuration :
Before generated sources can compile, add in BuildPath :
<%getRootPackage().name%>.model project.
SDK AlfrescoRemote (available on sourceforge.net) project.


Alfresco installation :
If your Alfresco installation do not run on localhost:8080,
create an alfresco/webserviceclient.properties in your classPath with following contains (here default values)
repository.location=http://localhost:8080/alfresco/api
repository.webapp=alfresco

JUnit Tests :
add in classPath :
-junit-4.6 jar file (available on sourceforge.net or in SDK AlfrescoEmbedded).

Generated Junit tests, need to connect to an alfresco instance, so please start Alfresco before launch tests. 
If your installation do not have default administrator user (admin@admin),
add in your classPath a test.properties file with :
login=<login>
password=<password>
Use Eclipse to launch tests, see src/main/test