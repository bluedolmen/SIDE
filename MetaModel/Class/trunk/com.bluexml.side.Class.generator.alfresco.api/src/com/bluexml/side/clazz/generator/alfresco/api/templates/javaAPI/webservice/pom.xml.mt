<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices

%>
<%script type="clazz.ClassPackage" name="validatedFilename"%>
<%if (eContainer() == null) {%><%getProperty("javaWebServicesAPIPath")%>/pom.xml<%}%>
<%script type="clazz.ClassPackage" name="alfrescoGenerator" file="<%validatedFilename%>"%>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId><%service::getRootContainer().name%>.webServices</groupId>
	<artifactId><%service::getRootContainer().name%>.webServices</artifactId>
	<version>1.0.0</version>
	<dependencies>
		<dependency>
			<groupId><%service::getRootContainer().name%>.model</groupId>
			<artifactId><%service::getRootContainer().name%>.model</artifactId>
			<version>1.0.0</version>
			<scope>compile</scope>
		</dependency>
		<dependency>
			<groupId>org.alfresco</groupId>
			<artifactId>alfresco-web-service-client</artifactId>
			<version>3.2r2</version>
			<classifier>community</classifier>
			<scope>compile</scope>
		</dependency>
		<dependency>
			<groupId>axis</groupId>
			<artifactId>axis</artifactId>
			<version>1.4</version>
			<type>jar</type>
			<scope>compile</scope>
		</dependency>
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>4.8.1</version>
			<type>jar</type>
			<scope>test</scope>
		</dependency>		
		<dependency>
			<groupId>javax.activation</groupId>
			<artifactId>activation</artifactId>
			<version>1.1</version>
			<type>jar</type>
			<scope>compile</scope>
		</dependency>
		<dependency>
			<groupId>xerces</groupId>
			<artifactId>xercesImpl</artifactId>
			<version>2.8.0</version>
			<type>jar</type>
			<scope>compile</scope>
		</dependency>
		<dependency>
			<groupId>xalan</groupId>
			<artifactId>xalan</artifactId>
			<version>2.7.0</version>
			<type>jar</type>
			<scope>compile</scope>
		</dependency>
		<dependency>
			<groupId>org.apache.santuario</groupId>
			<artifactId>xmlsec</artifactId>
			<version>1.4.1</version>
			<type>jar</type>
			<scope>compile</scope>
		</dependency>
		<dependency>
			<groupId>org.apache.ws.security</groupId>
			<artifactId>wss4j</artifactId>
			<version>1.5.4</version>
			<type>jar</type>
			<scope>compile</scope>
		</dependency>
	</dependencies>
	<build>
		<plugins>
			<plugin>
				<artifactId>maven-compiler-plugin</artifactId>
				<configuration>
					<source>1.5</source>
					<target>1.5</target>
				</configuration>
			</plugin>
		</plugins>
	</build>
</project>