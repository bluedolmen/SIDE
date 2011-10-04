<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices

%>
<%script type="clazz.ClassPackage" name="validatedFilename"%>
<%if (eContainer() == null) {%><%getProperty("javaEmbeddedAPIPath")%>/pom.xml<%}%>
<%script type="clazz.ClassPackage" name="alfrescoGenerator" file="<%validatedFilename%>" %>
<project
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd"
	xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
	<modelVersion>4.0.0</modelVersion>
	<groupId><%service::getRootContainer().name%>.embedded</groupId>
	<artifactId><%service::getRootContainer().name%>.embedded</artifactId>
	<packaging>amp</packaging>
	<version>1.0.0</version>
	<description>module that define new beans to have Factory for content model Objects</description>
	<parent>
		<groupId>org.bluexml</groupId>
		<artifactId>superpom</artifactId>
		<version>1.10</version>
	</parent>
	<properties>		
		<config.target.path>alfresco/module/${project.artifactId}</config.target.path>
	</properties>
	<dependencies>
		<dependency>
			<groupId><%service::getRootContainer().name%>.model</groupId>
			<artifactId><%service::getRootContainer().name%>.model</artifactId>
			<version>1.0.0</version>
			<scope>compile</scope>
		</dependency>
		<dependency>
			<groupId>org.alfresco</groupId>
			<artifactId>alfresco-repository</artifactId>
			<version>3.2.0r</version>
			<scope>provided</scope>
			<classifier>enterprise</classifier>
		</dependency>
		<dependency>
			<groupId>net.sf.json-lib</groupId>
			<artifactId>json-lib</artifactId>
			<version>2.2.3</version>
			<type>jar</type>
		</dependency>
		<dependency>
			<groupId>org.alfresco</groupId>
			<artifactId>alfresco-webscript-framework</artifactId>
			<version>3.2.0r</version>
			<classifier>enterprise</classifier>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>org.alfresco</groupId>
			<artifactId>alfresco-core</artifactId>
			<version>3.2.0r</version>
			<classifier>enterprise</classifier>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>org.alfresco</groupId>
			<artifactId>alfresco-remote-api</artifactId>
			<version>3.2.0r</version>
			<type>jar</type>
			<classifier>enterprise</classifier>
			<scope>provided</scope>
		</dependency>				
		<dependency>
			<groupId>commons-logging</groupId>
			<artifactId>commons-logging</artifactId>
			<version>1.1.1</version>
			<type>jar</type>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>rhino</groupId>
			<artifactId>js</artifactId>
			<version>1.6R7</version>
			<type>jar</type>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>joda-time</groupId>
			<artifactId>joda-time</artifactId>
			<version>1.2.1</version>
			<type>jar</type>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>javanettasks</groupId>
			<artifactId>httpunit</artifactId>
			<version>1.7</version>
			<type>jar</type>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>4.8.1</version>
			<type>jar</type>
			<scope>test</scope>
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
			<plugin>
				<groupId>com.bluexml.side.Integration.m2</groupId>
				<artifactId>ampMojo</artifactId>
				<version>1.0.2</version>
				<extensions>true</extensions>
				<configuration>
					<dependentAmpExcludes>**</dependentAmpExcludes>
					<dependentAmpIncludes></dependentAmpIncludes>
					<attachClasses>true</attachClasses>
					<archive>
						<addMavenDescriptor>false</addMavenDescriptor>
					</archive>
					<overlays></overlays>
				</configuration>
			</plugin>
		</plugins>
		<resources>
			<resource>
				<filtering>true</filtering>
				<directory>src/main/resources</directory>
				<excludes>
					<exclude>**README-*</exclude>
				</excludes>
			</resource>
			<!--
				Copies and filters AMP config in the proper package so to enforce
				full module naming single sourcing from POM properties
			-->
			<resource>
				<filtering>true</filtering>
				<directory>src/main/config</directory>
				<targetPath>${config.target.path}</targetPath>
				<excludes>
					<exclude>**README-*</exclude>
				</excludes>
			</resource>
		</resources>
	</build>
</project>