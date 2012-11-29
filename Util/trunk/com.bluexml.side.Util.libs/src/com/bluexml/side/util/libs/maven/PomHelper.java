package com.bluexml.side.util.libs.maven;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.bluexml.side.util.libs.maven.pom.Model;

public class PomHelper {

	private static final String JAXB_PACKAGE = "com.bluexml.side.util.libs.maven.pom";

	public static JAXBElement<Model> load(File file) throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance(JAXB_PACKAGE, PomHelper.class.getClassLoader());
		Unmarshaller unm = jaxbContext.createUnmarshaller();
		Object object = unm.unmarshal(file);
		return (JAXBElement<Model>) object;
	}

	public static void save(JAXBElement<Model> project, File file) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(JAXB_PACKAGE);

		Marshaller alfrescoMarshaller = jaxbContext.createMarshaller();
		alfrescoMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		alfrescoMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		alfrescoMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd");
		alfrescoMarshaller.marshal(project, file);
	}
}
