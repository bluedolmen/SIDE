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
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices

%>
<%script type="clazz.ClassPackage" name="validatedFilename"%>
<%if (eContainer() == null) {%><%getProperty("javaEmbeddedAPIPath")%>/README.txt<%}%>
<%script type="clazz.ClassPackage" name="alfrescoGenerator" file="<%validatedFilename%>"%>
This Eclipse project contains generated sources for SIDE Alfresco embedded API.

Project Importation :
In Eclipse use "Import from existing project into workspace", select archive files founded in <generationFolder>/alfresco_<version>_generated_api/.

Eclipse jdt Project configuration :
Before generated sources can compile, add in BuildPath :
<%getRootPackage().name%>.model project.
-SDK AlfrescoEmbedded project (available on http://sourceforge.net/alfresco/SDK).
-json-lib 2.2.3 (net.sf.json)


Build and Installation :
-Use amp packaging format to build this project,
take a look at alfresco wiki.
Amp packaging can be done using maven or Ant for example
- To install builded amp use alfresco-mmt.jar, or past amp file in alfresco home amps directory and run apply_amps.sh script

JUnit Tests :
-set src/test/java as source folder
-add in classPath :
 junit-4.6 (available on sourceforge.net) jar file.
 httpunit 1.7
 
Use with maven :
generated project contains a pom.xml file it can be used to build project as amp file.
to build package :
mvn clean package -Dmaven.test.skip=true

so you can install generated amp using SIDE (copy amp in <generationFolder>/alfresco_3.x) or using alfresco-mmt.jar

junit tests can be run after alfresco startup :
mvn test

Eclipse can manage maven project by installing m2eclipse plugin. 


Usage :
Generated Classes are designed to work inside Alfresco as springs beans, they are two king of classes :
- In ../script/* are dedicated to extends alfresco webScripts by adding new function,
this is done by adding springs configuration, see /src/main/resources/
after that methods can be used in all alfresco webscripts.
- Other can be used by yours springs beans (alfresco actions ...)
